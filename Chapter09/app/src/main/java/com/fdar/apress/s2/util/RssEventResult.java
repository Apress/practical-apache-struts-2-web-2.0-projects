package com.fdar.apress.s2.util;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.TextProvider;
import com.opensymphony.xwork2.Result;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.feed.module.georss.GeoRSSModule;
import com.sun.syndication.feed.module.georss.W3CGeoModuleImpl;
import com.sun.syndication.feed.module.georss.geometries.Position;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import com.fdar.apress.s2.domain.Event;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.net.URL;

import org.apache.struts2.StrutsStatics;
import sun.net.www.protocol.http.HttpURLConnection;

public class RssEventResult implements Result {

    private static final String MIME_TYPE = "application/xml";

    private String inputName = "inputList";
    private String feedType = "rss_2.0";

    private String title;
    private String link;
    private String description;

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void execute(ActionInvocation invocation) throws Exception {

        HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
        response.setContentType(MIME_TYPE);

        List<Event> events = (List<Event>) invocation.getStack().findValue(inputName);

        try {

            SyndFeedOutput feedOutput = new SyndFeedOutput();
            feedOutput.output(createFeed(events,feedType, (TextProvider)invocation.getAction()),response.getWriter());
            response.getWriter().flush();

        } finally {
            if( response.getWriter() != null ) {
                response.getWriter().close();
            }
        }

    }

    protected SyndFeed createFeed(List<Event> events,String feedType, TextProvider textProvider) throws IOException,FeedException {

        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType(feedType);

        feed.setTitle(title);
        feed.setLink(link);
        feed.setDescription(description);

        List entries = new ArrayList();
        for( Event next: events ) {
            entries.add( createEntry(next,textProvider) );
        }

        feed.setEntries(entries);
        return feed;
    }

    private SyndEntry createEntry(Event event, TextProvider textProvider) {

        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle(event.getName());
        entry.setLink(link.substring(0,link.lastIndexOf("/")+1)+"api/event/"+event.getId());
        entry.setPublishedDate(event.getStartTime());

        SyndContent description = new SyndContentImpl();
        description.setType("text/html");
        description.setValue(createDescription(event,textProvider));
        entry.setDescription(description);

        entry.getModules().add(geoCodeLocation(event));

        return entry;
    }

    private String createDescription(Event event, TextProvider textProvider) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();

        sb.append("<ul>");
        sb.append("<li>")
                .append(textProvider.getText("event.startDate"))
                .append(": ")
                .append(df.format(event.getStartTime()))
                .append("</li>");
        sb.append("<li>")
                .append(textProvider.getText("event.timeZoneOffset"))
                .append(": ")
                .append(event.getTimeZoneOffset())
                .append("</li>");
        sb.append("<li>")
                .append(textProvider.getText("event.duration"))
                .append(": ")
                .append(event.getDuration())
                .append("</li>");
        sb.append("<li>")
                .append(textProvider.getText("event.location"))
                .append(": ")
                .append(event.getLocation().getCity())
                .append(", ")
                .append(event.getLocation().getState())
                .append("</li>");
        sb.append("</ul>");

        return sb.toString();
    }

    private GeoRSSModule geoCodeLocation(Event event) {

        // GeoCode the address
        StringBuilder sb = new StringBuilder()
                .append("http://maps.google.com/maps/geo?q=")
                .append(event.getLocation().getCity().replaceAll(" ","+"))
                .append(",")
                .append(event.getLocation().getState().replaceAll(" ","+"))
                .append("&output=csv")
                .append("&key={googleKey}");

        String[] data = null;
        try {
            HttpURLConnection url = new HttpURLConnection(new URL(sb.toString()),"maps.google.com",80);
            url.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.getInputStream()));

            String buffer = br.readLine();
            data = buffer.split(",");
        } catch (Exception e) {
            e.printStackTrace();
        }

        double latitude = Double.parseDouble(data[2]);
        double longitude = Double.parseDouble(data[3]);

        // Add RSS GeoCoding
        GeoRSSModule geoRSSModule = new W3CGeoModuleImpl();
        Position pos = new Position(latitude,longitude);
        geoRSSModule.setPosition(pos);

        return geoRSSModule;
    }

}
