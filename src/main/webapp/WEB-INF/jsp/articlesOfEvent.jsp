<html lang="en">
    <head>
        <title>W6 analysis</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    </head>

    <body >
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12 header" id="header">
                        <h1 id="logo">
                            <img id="logo" class="logo" src="../logo/Logo/W6.png"/>
                        </h1>
                        <h2 id="site-name">W6 assess</h2>
                        <h4 id="site-slogan">news based security profiling</h4>
                    </div>
                         <div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
									
                                    <form id="event_form" action="../update/event" method="POST">
                                        <br>Event id:<br>
                                        <textarea id = "eventId" name="eventId" cols="40" rows="1" readonly></textarea>
                                        <br>Event title:<br>
                                        <textarea id = "eventTitle" name="eventTitle" cols="40" rows="1"></textarea>
                                        <br>Date:<br>
                                        <textarea id = "eventDate" name="eventDate" cols="40" rows="1"></textarea>
                                        <br>Description:<br>
                                        <textarea id = "eventDesc" name="eventDesc" cols="40" rows="1"></textarea>
                                        <br>Region:<br>
                                        <textarea id = "eventReg" name="eventReg" cols="40" rows="1"></textarea>
                                        <br>Country:<br>
                                        <textarea id = "eventCountry" name="eventCountry" cols="40" rows="1"></textarea>
                                        <br>
                                        <input type="submit" value="Update event" />
                                        <br>
                                    </form>
            
                                    <script>
                                        function addCell(cell, text) {
                                            if (text != null)
                                                cell.innerHTML =  
                                                    "<label style = \"font-weight: normal;\">" + text + "</label>"
                                                    ;
                                        }
                                        window.onload = function () {
                                            var myEvent = ${event};
                                            var articlesArray = ${docList};
                                            console.log(myEvent);
							
                                                $("#event_form > #eventId").val(myEvent.id);
                                                $("#event_form > #eventTitle").val(myEvent.title);
                                                $("#event_form > #eventDate").val(myEvent.date);
                                                $("#event_form > #eventDesc").val(myEvent.description);
                                                $("#event_form > #eventReg").val(myEvent.region);
                                                $("#event_form > #eventCountry").val(myEvent.country);

                                                var i;
                                                for (i = 0; i < articlesArray.length; i++) {
                                                    var table = document.getElementById("articleTable");
                                                    var x = table.rows.length;
                                                    var row = table.insertRow(x);
                                                    addCell(row.insertCell(0), articlesArray[i].id);
                                                    addCell(row.insertCell(1), articlesArray[i].sourse);
                                                    addCell(row.insertCell(2), articlesArray[i].title);
                                                    addCell(row.insertCell(3), articlesArray[i].text);
                                                }
											
                                        }
                                    </script>
									
                                    <div class="col-sm-6">
                                        <table id="articleTable" class="table" style = "overflow:scroll">
                                                <thead>
                                                        <tr>
                                                                <th ><span class="fa fa-circle id headertable" aria-hidden="true"></span> Id</th>
                                                                <th ><span class="fa fa-circle source headertable" aria-hidden="true"></span> Sourse</th>
                                                                <th ><span class="fa fa-circle title headertable" aria-hidden="true"></span> Title</th>
                                                                <th ><span class="fa fa-circle text headertable" aria-hidden="true"></span> Text</th>

                                                        </tr>
                                                </thead>
                                        </table>
                                    </div>
                                </div>
            
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </body>
</html>
