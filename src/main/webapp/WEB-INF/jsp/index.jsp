<!DOCTYPE html>
<html>
<head>
	
	<title>Quick Start - Leaflet</title>

	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link rel="shortcut icon" type="image/x-icon" href="docs/images/favicon.ico" />

	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.1/dist/leaflet.css" />
	<script src="https://unpkg.com/leaflet@1.0.1/dist/leaflet.js"></script>
        
        <style>
            #mapid{
                width: 1200px; height: 700px;
            }
        </style>
</head>
<body>



<div id="mapid" ></div>
<script>

	var map = L.map('mapid').setView([39.74739, -105], 2);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
			'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="http://mapbox.com">Mapbox</a>',
		id: 'mapbox.light'
	}).addTo(map);

        var features = [];
        var articles = ${articles};
        console.log(articles);
        articles.forEach(function(article)
        {
            if (article.hasOwnProperty('location'))
            {
                var location = JSON.parse(article.location);
                console.log(location);  
                features.push(
                    {
                        "geometry": {

                            "type": "Point", 

                            "coordinates": [
                                location.lng, 
                                location.lat
                            ]
                        }, 

                        "type": "Feature", 

                        "properties": {

                            "type_of_area": "Road", 

                            "event_description": "2 MDM aid workers are abducted by Somali gunmen", 

                            "location": "at the border village of Laas Caanood, Ogaden Region", 

                            "perpetratordetails": "Clan militia"

                        }
                    }
                );
            }
        });
        console.log(features);
        var events = {

            "type": "FeatureCollection",

            "features": features
        
        };
	L.geoJSON(events, {

		filter: function (feature, layer) {
			if (feature.properties) {
				// If the property "underConstruction" exists and is true, return false (don't render features under construction)
				return feature.properties.underConstruction !== undefined ? !feature.properties.underConstruction : true;
			}
			return false;
		},

		// onEachFeature: onEachFeature,
                

		pointToLayer: function (feature, latlng) {
			return L.circleMarker(latlng, {
				radius: 8,
				fillColor: "#ff7800",
				color: "#000",
				weight: 1,
				opacity: 1,
				fillOpacity: 0.8
			});
                    }
	}).addTo(map);

</script>



</body>
</html>
