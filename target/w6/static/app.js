function addCell(cell, text) {
    if (text != null)
        cell.innerHTML = text;
}

(function (angular, SockJS, Stomp, _) {
    
    angular.module("w6", [])
        .controller('ConsumerController', function ($scope, $http) {
            $scope.name = 'Gunmen eats brocoli';
            $scope.callServer = function () {
                $http.post('/w6/rest-call/' + $scope.name).success(function (data) {
                    var i, output;
                    console.log(data);
                    var json =  JSON.parse(data.payload);//Todo:get rid of parsing  
                    console.log(json);
                    output = "";
                    for (i = 0; i < json.text.length; i++) 
                    {
                        if (json.text[i].tag == "who")
                        {
                          output += "<span style='color:green'>" + json.text[i].content + "</span> ";
                        } 
                        else if (json.text[i].tag == "what")
                        {
                            output += "<span style='color:blue'>" + json.text[i].content + "</span> ";
                        }
                        else if (json.text[i].tag == "NN")
                        {
                            output += "<span style='color:red'>" + json.text[i].content + "</span> ";
                        }
                        else 
                        {
                            output += "<span style='color:black'>" + json.text[i].content + "</span> ";
                        }
                    }
                    console.log(output);
                    document.getElementById("textarea").innerHtml = output;
                                   

                    var i;
                    var table = json.table;
                    var who = table.who;
                    var what = table.what;
                    var weapon = table.weapon;
                    var whom = table.whom;
                    var when = table.when;
                    var where = table.where;
                    var tableLength = Math.max(who.length, what.length, weapon.length, whom.length, when.length, where.length);
                    for(i = 0; i < tableLength; i++)
                    {
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
              });
            };
        });
    
})(angular, SockJS, Stomp, _);