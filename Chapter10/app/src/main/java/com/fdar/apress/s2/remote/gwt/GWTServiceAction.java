package com.fdar.apress.s2.remote.gwt;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.fdar.apress.s2.client.service.GWTService;
import com.fdar.apress.s2.services.EventService;
import com.fdar.apress.s2.client.GWTEventDTO;
import com.fdar.apress.s2.client.GWTLocationDTO;
import com.fdar.apress.s2.domain.Event;
import com.fdar.apress.s2.domain.Location;
import com.fdar.apress.s2.domain.Address;
import com.fdar.apress.s2.domain.Broadcast;

import java.util.List;
import java.util.ArrayList;
import java.text.DateFormat;

public class GWTServiceAction {

    protected EventService service;

    public void setEventService(EventService service) {
        this.service = service;
    }

    public GWTEventDTO[] findRecentEvents(Integer max) {
        List<Event> list = service.findAllEvents(max);
        GWTEventDTO[] data = new GWTEventDTO[list.size()];
        for( int i=0; i<list.size(); i++ ) {
            data[i] = createDTO(list.get(i));
        }
        return data;
    }

    private GWTEventDTO createDTO( Event event ) {

        GWTEventDTO result = new GWTEventDTO();

        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
        result.setId(event.getId());
        result.setEventName(event.getName());
        result.setStartTime(df.format(event.getStartTime()));
        result.setTimeZoneOffset(event.getTimeZoneOffset());
        result.setVotingStartTime(df.format(event.getVotingStartTime()));
        result.setDuration(event.getDuration());
        result.setLastUpdateTime(df.format(event.getLastUpdateTime()));
        result.setLocation(createLocationDTO(event.getLocation()));
        result.setStatus(event.getStatus().name());

        return result;
    }

    private GWTLocationDTO createLocationDTO( Location loc ) {

        GWTLocationDTO result = new GWTLocationDTO();

        result.setId(loc.getId());
        String cn = loc.getClass().getName();
        result.setType(cn.substring(cn.lastIndexOf(".")+1,cn.length()));
        result.setName(loc.getName());
        result.setCity(loc.getCity());
        result.setState(loc.getState());

        if( loc instanceof Address) {
            result.setAddress(((Address)loc).getAddress());
            result.setZipcode(((Address)loc).getZipcode());
        }

        if( loc instanceof Broadcast) {
            result.setStationIdentifier(((Broadcast)loc).getStationIdentifier());
            result.setNetwork(((Broadcast)loc).getNetwork());
        }

        return result;
    }
}
