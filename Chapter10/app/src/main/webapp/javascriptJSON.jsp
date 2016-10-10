<html>
<head>
    <title>JavaScript JSON</title>

    <script type="text/javascript">
        function loadRemotely() {

            var kw = {
                url: "/app/services/json/recentEvents.action",
                mimetype: "text/json",
                method: "GET",
                load: function(type, json, evt) {

                    display = "";
                    for( var e=0; e<json.length; e++ ){
                        var name = json[e].eventName;
                        var startTime = json[e].startTime;
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