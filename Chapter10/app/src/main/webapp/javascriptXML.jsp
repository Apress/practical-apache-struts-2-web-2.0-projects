<html>
<head>
    <title>JavaScript XML</title>

    <script type="text/javascript">
        function loadRemotely() {

            var kw = {
                url: "/app/services/xml/recentEventsXML.action",
                mimetype: "text/xml",
                method: "GET",
                load: function(type, xml, evt) {

                    var entries = xml.getElementsByTagName("item");
                    var display = "<h3>Events</h3>";

                    for(var e=0;e<entries.length;e++){

                        var entry = entries[e];
                        var name = entry.getElementsByTagName("eventName")[0].firstChild.nodeValue;
                        var startTime = entry.getElementsByTagName("startTime")[0].firstChild.nodeValue;

                        display += "<b>"+name+"</b> - "+startTime+"<br/>";
                    }

                    dojo.byId("main").innerHTML = display;
                }
            };
            dojo.io.bind(kw);
        }
    </script>
</head>

<body onload="loadRemotely()">
</body>
</html>