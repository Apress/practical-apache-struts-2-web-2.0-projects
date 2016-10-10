<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <title><s:text name="home.title" /></title>

    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key={googleKey}"
            type="text/javascript"></script>
    <script type="text/javascript">

        //<![CDATA[

        function load() {

            if (GBrowserIsCompatible()) {

                var map = new GMap2(document.getElementById("map"));
                map.setCenter(new GLatLng(41.875696,-87.624207), 3);
                map.addControl(new GLargeMapControl());

                GDownloadUrl("http://localhost:8080/app/rss.action", function(data, responseCode) {

                    var xml = GXml.parse(data);
                    var items = xml.documentElement.getElementsByTagName("item");

                    for (var i = 0; i < items.length; i++) {
                        var point = new GLatLng(
                                parseFloat(GXml.value(items.item(i).getElementsByTagName("lat")[0])),
                                parseFloat(GXml.value(items.item(i).getElementsByTagName("long")[0])));
                        var title = GXml.value(items.item(i).getElementsByTagName("title")[0]);
                        var contents = GXml.value(items.item(i).getElementsByTagName("description")[0]);
                        var marker = createMarker(point,"<h3>"+title+"</h3>"+contents);
                        map.addOverlay(marker);
                    }
                });
            }
        }

        function createMarker(point,html) {
            var marker = new GMarker(point);
            GEvent.addListener(marker, "click", function() {
                marker.openInfoWindowHtml(html);
            });
            return marker;
        }

        //]]>
    </script>

</head>
<body onload="load()" onunload="GUnload()">

<p><s:text name="home.welcomeText"/></p>

<div>
    <p><s:text name="home.mostRecentTen"/></p>
    <div id="map" style="width: 500px; height: 300px"></div>
</div>

</body>
</html>