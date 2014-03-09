<%--
  Created by IntelliJ IDEA.
  User: Danyang
  Date: 10/10/13
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>


<head>
    <c:url value="css/bootstrap.css" var="cssURL" />
    <c:url value="css/customized.css" var="cssURL1" />
    <c:url value="css/carousel.css" var="cssURL2" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />


    <link rel="stylesheet" type="text/css" media="screen" href="${cssURL}"/>
    <link rel="stylesheet" type="text/css" media="screen" href="${cssURL1}"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet"> <!-- for boostrap icons -->
    <%--<link rel="stylesheet" type="text/css" media="screen" href="${cssURL2}"/>--%>

    <c:url value="js/jquery.js" var="jsURL" />
    <c:url value="js/bootstrap.min.js" var="jsURL1" />
    <c:url value="js/maps.google.polygon.containsLatLng.js" var="jsURL2" />
    <script type="text/javascript" src="${jsURL}"></script>
    <script type="text/javascript" src="${jsURL1}"></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAs2yRcXM_Q_Ub8h5iTf5FP36f2RoiWO7Y&sensor=true&region=SG"> </script><!-- localization -->
    <script type="text/javascript" src="${jsURL2}"></script> <%-- depends on the google map --%>



    <title>Map</title>
    <style type="text/css">
        html { height: 100% }
        body { height: 100%; margin: 0; padding: 0 }
        #map-canvas { height: 90% }
    </style>
</head>
<body>
    <!-- nav bar -->
    <div class="row navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">SafeGuard Map View</a>
            </div>
            <div class="navbar-collapse collapse navbar-responsive-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown" id="weather">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-info-sign"></span> Weather<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <core:if test="${not empty weather}">
                                <li class="dropdown-header">Location</li>
                                <li><a href="#"><span class="glyphicon glyphicon-globe"></span> ${weather.location}</a></li>
                                <li class="divider"></li>

                                <li class="dropdown-header">Weather</li>
                                <li><a href="#">Weather Type: ${weather.simpleWeather.weatherType}</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-cloud-upload"></span> Temperature High: ${weather.simpleWeather.highTemperature} <span class="unit">C</span></a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-cloud-download"></span> Temperature Low: ${weather.simpleWeather.lowTemperature} <span class="unit">C</span></a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-calendar"></span> Timestamp: ${weather.simpleWeather.timeStamp}</a></li>
                                <li class="divider"></li>

                                <li class="dropdown-header">Wind</li>
                                <li><a href="#"><span class="glyphicon glyphicon-leaf"></span> speed: ${weather.wind.speed} <span class="unit">km/h</span></a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-fullscreen"></span> direction: ${weather.wind.direction} <span class="unit">degree</span></a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-asterisk"></span> chill: ${weather.wind.chill}</a></li>
                                <li class="divider"></li>

                                <li class="dropdown-header">Astronomy</li>
                                <li><a href="#"><span class="glyphicon glyphicon-circle-arrow-up"></span> sunrise: ${weather.sunrise}</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-circle-arrow-down"></span> sunset: ${weather.sunset}</a></li>
                            </core:if>
                        </ul>
                    </li>


                    <li class="dropdown" id="haze">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cloud"></span> Haze Info<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <!-- debug -->
                            <%--<li class="dropdown-header">South</li>--%>
                            <%--<li><a href="#"><span class="glyphicon glyphicon-dashboard"></span> PSI: 36 </a></li>--%>
                            <%--<li><a href="#"><span class="glyphicon glyphicon-tasks"></span> Classification: Good</a></li>--%>
                            <%--<li><a href="#"><span class="glyphicon glyphicon-calendar"></span> Timestamp: 2013-11-09 15:37:03.012</a></li>--%>
                            <%--<li class="divider"></li>--%>
                            <!-- END debug -->
                            <core:forEach items="${hazeMap}" var="haze_map">
                                <li class="dropdown-header">${haze_map.key}</li>
                                <li><a href="#"><span class="glyphicon glyphicon-dashboard"></span> PSI: ${haze_map.value.psi} </a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-tasks"></span> Classification: ${haze_map.value.classification}</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-calendar"></span> Timestamp: ${haze_map.value.timestamp}</a></li>
                                <li class="divider"></li>
                            </core:forEach>
                        </ul>
                    </li>


                    <li><a id="toggle_trigger" onclick="toggleOverlays()">Toggle Regions</a></li>
                </ul>
            </div><!-- /.nav-collapse -->
        </div><!-- /.container -->
    </div>
    <%--main body--%>
    <div class="row">
        <div id="map-canvas" class="col-lg-9"></div>

        <div class="col-lg-3">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#southWest">
                            South West Area
                        </a>
                    </h4>
                </div>
                <div id="southWest" class="panel-collapse collapse in">
                    <%--<div class="panel-body">--%>
                        <%--Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.--%>
                    <%--</div>--%>
                </div>
            </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#northWest">
                                North West Area
                            </a>
                        </h4>
                    </div>
                    <div id="northWest" class="panel-collapse collapse">
                        <%--<div class="panel-body">--%>
                            <%--Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.--%>
                        <%--</div>--%>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#centralSingapore">
                                Central Singapore Area
                            </a>
                        </h4>
                    </div>
                    <div id="centralSingapore" class="panel-collapse collapse">
                        <%--<div class="panel-body">--%>
                            <%--Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.--%>
                        <%--</div>--%>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#northEast">
                                North East Area
                            </a>
                        </h4>
                    </div>
                    <div id="northEast" class="panel-collapse collapse">
                        <%--<div class="panel-body">--%>
                            <%--Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.--%>
                        <%--</div>--%>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#southEast">
                                South East Area
                            </a>
                        </h4>
                    </div>
                    <div id="southEast" class="panel-collapse collapse">
                        <%--<div class="panel-body">--%>
                            <%--Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%--footer--%>
    <div class="footerWrapper" style="min-height: 80px">
        <div class="row" >
            <div class="col-lg-8 col-lg-offset-2" style="padding: 20px;">
                <p class="contrast">&copy; 2013 SafeGuard, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a> <span class="pull-right"><a href="#">Back to top</a></span></p>
            </div>
        </div>
    </div>

    <c:url value="js/maps_coords/northWest.js" var="northWest" />
    <script type="text/javascript" src="${northWest}"></script>
    <c:url value="js/maps_coords/northEast.js" var="northEast" />
    <script type="text/javascript" src="${northEast}"></script>
    <c:url value="js/maps_coords/southEast.js" var="southEast" />
    <script type="text/javascript" src="${southEast}"></script>
    <c:url value="js/maps_coords/southWest.js" var="southWest" />
    <script type="text/javascript" src="${southWest}"></script>
    <c:url value="js/maps_coords/centralSingapore.js" var="centralSingapore" />
    <script type="text/javascript" src="${centralSingapore}"></script>

    <!-- google map -->
    <script type="text/javascript">
        /* map initialize  */

        /* create map */
        google.maps.visualRefresh = true;
        var singapore = new google.maps.LatLng(1.3467, 103.820);
        var geocoder = new google.maps.Geocoder();

        var mapOptions = {
            center: singapore,
            zoom: 12,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"),
                mapOptions);


        function initialize() {
            /* five regions */
            // Construct the polygon.
            northwest = new google.maps.Polygon({
                paths: northWestCoords,
                strokeColor: '#a8ddad',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#a8ddad',
                fillOpacity: 0.35
            });

            northwest.setMap(map);

            northEast = new google.maps.Polygon({
                paths: northEastCoords,
                strokeColor: '#ff7db8',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#ff7db8',
                fillOpacity: 0.35
            });

            northEast.setMap(map);

            southWest = new google.maps.Polygon({
                paths: southWestCoords,
                strokeColor: '#d739e5',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#d739e5',
                fillOpacity: 0.35
            });

            southWest.setMap(map);

            centralSingapore = new google.maps.Polygon({
                paths: centralSingaporeCoords,
                strokeColor: '#eac97f',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#eac97f',
                fillOpacity: 0.35
            });

            centralSingapore.setMap(map);

            southEast = new google.maps.Polygon({
                paths: southEastCoords,
                strokeColor: '#1d92eb',
                strokeOpacity: 0.8,
                strokeWeight: 2,
                fillColor: '#1d92eb',
                fillOpacity: 0.35
            });

            southEast.setMap(map);





            /* BEGIN hit map */
            <core:forEach items="${records}" var="record" varStatus="records">
                var address = "${record.address}"; /* quotes please */
                geocoder.geocode( { 'address': address}, function(results, status) { /* call back function, passed as parameter */
                    if (status == google.maps.GeocoderStatus.OK) {

                        /* marker */
                        marker_${records.index} = new google.maps.Marker({
                            position: results[0].geometry.location, /* convert address to geocode */
                            map: map,
                            title: '${record.typeName}; Level: ${record.level}; <br />Address: ${record.address}; <br />Timestamp: ${record.timestamp.time} <br />',
                            animation: google.maps.Animation.DROP
                        });

                        /* icon for marker */
                        marker_${records.index}.setIcon(getMakerIconUrl("${record.typeName}"));

                        /* info window */
                        var contentString_${records.index} =
                                '<div id="content">'+
                                        '<div id="siteNotice">'+
                                        '</div>'+
                                        '<h1 id="firstHeading" class="firstHeading">${record.typeName}</h1>'+
                                        '<div id="bodyContent">'+
                                        '<p>Description: ${record.description}</p>'+
                                        '<p>Time: ${record.timestamp.time}</p>'+
                                        '<p>Level: ${record.level}</p>'+
                                        '<p>Location: ${record.address}</p>'+
                                        '</div>'+
                                        '</div>';

                        var infowindow_${records.index} = new google.maps.InfoWindow({
                            content: contentString_${records.index}
                        });

                        /* event listener */
                        google.maps.event.addListener(marker_${records.index}, 'click', function() {
                            infowindow_${records.index}.open(map, marker_${records.index});
                        });

                        /* check if in polygon */
                        regionalize(northwest, marker_${records.index}, "northWest");
                        regionalize(northEast, marker_${records.index}, "northEast");
                        regionalize(southEast, marker_${records.index}, "southEast");
                        regionalize(southWest, marker_${records.index}, "southWest");
                        regionalize(centralSingapore, marker_${records.index}, "centralSingapore");

                    } /* END if google.maps.GeocoderStatus.OK */
                });
            </core:forEach> /* END hit map */
        } /* END initialize() */

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
    <script type="text/javascript">
        function getMakerIconUrl(typeName){
            if (typeName == "${typeNames[0]}") color = "red";
            else if (typeName == "${typeNames[1]}") color = "blue"; // will return null if not exist
            else if (typeName == "${typeNames[2]}") color = "green";
            else if (typeName == "${typeNames[3]}") color = "purple";
            else color = "yellow";
            return "http://www.google.com/intl/en_us/mapfiles/ms/micons/" + color + "-dot.png"
        }
        function regionalize(polygon, marker, div_id){
            /* check if in polygon */
            var isWithinPolygon = polygon.containsLatLng(marker.position);
            if(isWithinPolygon){
                var newdiv = document.createElement('div');
                newdiv.setAttribute('class','panel-body');
                newdiv.innerHTML = marker.title+"<a href='#' onclick='centerMap"+marker.position+"'>show</a> "; //  marker.position with bracket (passing the function parameter

                var panel = document.getElementById(div_id);
                panel.appendChild(newdiv);
            }
        }

        function centerMap(x, y){
            var centerCoordinate = new google.maps.LatLng(x, y);
            window.map.setCenter(centerCoordinate);
        }

    </script>
    <script>
        function toggleOverlays() {
            var the_array = [southWest, northwest, centralSingapore, northEast, southEast];
            for(i=0;i<the_array.length;i++) {
                var current = the_array[i];
                if (current.getMap() == null)
                    current.setMap(map); // marker isn't visible on map, so make it visible
                else
                    current.setMap(null);
            }

        }
    </script>

</body>
</html>