<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
            <script src="dist/js/bootstrap-switch.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    <body >
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12 header" id="header">
                        <h1 id="logo">
                            <img id="logo" class="logo" src="logo/Logo/W6.png"/>
                        </h1>
                        <h2 id="site-name">W6 assess</h2>
                        <h4 id="site-slogan">news based security profiling</h4>
                    </div>
                    <div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">


                                    <form id="fake_textarea" class="form-control" style="height:450px">
                                        <input type='hidden' id='fake_textarea_content' name='foobar' /></form>

                                    <script>
                                        function addCell(cell, text) {
                                            if (text != null)
                                                cell.innerHTML =  
                                                    text +
                                                    "<input id=\"switch-state\" type=\"checkbox\">"
                                        }
                                        window.onload = function () {
                                            var i, output;
                                            var json = ${response};
                                            output = "";
                                            for (i = 0; i < json.text.length; i++) {
                                                var word = json.text[i].content;
                                                var tag = json.text[i].tag;
                                                if (tag == "weapon")
                                                {
                                                    output +=
                                                            "<span style='color:blue'>" + word + "</span> ";
                                                } else
                                                if (tag == "what")
                                                {
                                                    output +=
                                                            "<span style='color:purple'>" + word + "</span> ";
                                                } else
                                                if(tag == "where")
                                                {
                                                    output += 
                                                            "<span style='color:orange'>" + word + "</span> ";
                                                } else 
                                                if(tag == "who")
                                                {
                                                    output += 
                                                            "<span style='color:green'>" + word + "</span> ";
                                                } else 
                                                if(tag == "whom")
                                                {
                                                    output += 
                                                            "<span style='color:red'>" + word + "</span> ";
                                                } else {
                                                    output += 
                                                            "<span style='color:black'>" + word + "</span> ";
                                                }
                                            }
                                            document.getElementById("fake_textarea").innerHTML = output;
                                            var i;
                                            var table = json.table;
                                            var who = table.who;
                                            var what = table.what;
                                            var weapon = table.weapon;
                                            var whom = table.whom;
                                            var when = table.when;
                                            var where = table.where;
                                            var tableLength = Math.max(who.length, what.length, weapon.length, whom.length, when.length, where.length);
                                            for (i = 0; i < tableLength; i++) {
                                                var table = document.getElementById("myTable");
                                                var x = document.getElementById("myTable").rows.length;
                                                var row = table.insertRow(x);
                                                addCell(row.insertCell(0), who[i]);
                                                addCell(row.insertCell(1), weapon[i]);
                                                addCell(row.insertCell(2), what[i]);
                                                addCell(row.insertCell(3), whom[i]);
                                                addCell(row.insertCell(4), where[i]);
                                                addCell(row.insertCell(5), when[i]);
                                            }
                                        }
                                    </script>

                                </div>
                            </div>
                            <div class="col-sm-6" >
                                <table id="myTable" class="table" onload=myFunction()>
                                    <thead>
                                        <tr>
                                            <th ><span class="fa fa-circle who headertable" aria-hidden="true"></span> Who</th>
                                            <th ><span class="fa fa-circle weapon headertable" aria-hidden="true"></span> Weapon</th>
                                            <th ><span class="fa fa-circle what headertable" aria-hidden="true"></span> What</th>
                                            <th ><span class="fa fa-circle whom headertable" aria-hidden="true"></span> Whom</th>
                                            <th ><span class="fa fa-circle where headertable" aria-hidden="true"></span> Where</th>
                                            <th ><span class="fa fa-circle when headertable" aria-hidden="true"></span> When</th>
                                        </tr>
                                    </thead>
                                </table>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>