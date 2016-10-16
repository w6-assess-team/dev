<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
    <head>
        <title>W6 analysis</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    </head>

    <body style="background-color: #E5E4E2">
         <div class="container "  style="width:80%">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-12 header" id="header">
        <h1 id="logo">
          <img id="logo" class="logo" src="logo/Logo/W6.png"/>
        </h1>
        <h2 id="site-name">W6 assess</h2>
        <h4 id="site-slogan">news based security profiling</h4>
        <br><br>
        <nav class="navbar navbar-inverse" style="background-color:black">
    
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="#">Input</a></li>
      <li><a href="#">reports</a></li>
      <li><a href="#">Add Events</a></li>
    </ul>
</nav>
      </div> <div id="main_wrapper" class="myMainWrapper">
    <div class="wrapper noMainRow">
        <div class="container-fluid">
            <div class="row">
                <div class="" id="main-title" >
                    <h1 style="color:#39B7CD" >Event</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12  index-main-row">
                    <div class="row row-index">
                        
                            <div  class="col-lg-8  col-md-3 col-xs-12"  >
                                <div class="panel panel-primary" style="background:#E5E4E2; margin-left:-10px">
                                <div class="panel-heading" style="background-color:#39B7CD">Panel Heading</div>
                                
                                <div class="panel-body">
  
 
                                    <form data-spy="scroll"  action="parse" method="POST">

                                    <table id="myTable" class="table" >
                                        <thead>
                                            <tr>
                                                <th class="col-sm-3" ><span class="  fa fa-circle who headertable" aria-hidden="true"></span> Who</th>
                                                <th class="col-sm-3"><span class=" fa fa-circle weapon headertable" aria-hidden="true"></span> Weapon</th>
                                                <th class="col-sm-3"><span class=" fa fa-circle what headertable" aria-hidden="true"></span> What</th>
                                                <th class="col-sm-3"><span class=" fa fa-circle whom headertable" aria-hidden="true"></span> Whom</th>
                                                <th class="col-sm-3" ><span class=" fa fa-circle where headertable" aria-hidden="true"></span> Where</th>
                                                <th class="col-sm-3"><span class=" fa fa-circle when headertable" aria-hidden="true"></span> When</th>
                                            </tr>
                                        </thead>
                                    </table>
                                    <input name="id" class="col-sm-2" id="id" hidden="hidden">
                                    <select class="form-control" id = "event_select" name="event_select">
                                        <option value="-1"> Create new event<option/>
                                    </select>
                                    <p>Title: <textarea class="form-control" id = "title" name="title" cols="140" rows="1"></textarea> </p>
                                    <p>Date: <input type="text" class="form-control" id="date" name = "date"></p>                                            
                                    <p>Region: <textarea class="form-control" id = "region" name="region" cols="140" rows="1"></textarea> </p>
                                    <p><select class="form-control bfh-countries" data-country="US" id="countries" name="countries">
                                        <option value="-1">Country</option>
                                        <option value="">Country...</option>
<option value="Afganistan">Afghanistan</option>
<option value="Albania">Albania</option>
<option value="Algeria">Algeria</option>
<option value="American Samoa">American Samoa</option>
<option value="Andorra">Andorra</option>
<option value="Angola">Angola</option>
<option value="Anguilla">Anguilla</option>
<option value="Antigua &amp; Barbuda">Antigua &amp; Barbuda</option>
<option value="Argentina">Argentina</option>
<option value="Armenia">Armenia</option>
<option value="Aruba">Aruba</option>
<option value="Australia">Australia</option>
<option value="Austria">Austria</option>
<option value="Azerbaijan">Azerbaijan</option>
<option value="Bahamas">Bahamas</option>
<option value="Bahrain">Bahrain</option>
<option value="Bangladesh">Bangladesh</option>
<option value="Barbados">Barbados</option>
<option value="Belarus">Belarus</option>
<option value="Belgium">Belgium</option>
<option value="Belize">Belize</option>
<option value="Benin">Benin</option>
<option value="Bermuda">Bermuda</option>
<option value="Bhutan">Bhutan</option>
<option value="Bolivia">Bolivia</option>
<option value="Bonaire">Bonaire</option>
<option value="Bosnia &amp; Herzegovina">Bosnia &amp; Herzegovina</option>
<option value="Botswana">Botswana</option>
<option value="Brazil">Brazil</option>
<option value="British Indian Ocean Ter">British Indian Ocean Ter</option>
<option value="Brunei">Brunei</option>
<option value="Bulgaria">Bulgaria</option>
<option value="Burkina Faso">Burkina Faso</option>
<option value="Burundi">Burundi</option>
<option value="Cambodia">Cambodia</option>
<option value="Cameroon">Cameroon</option>
<option value="Canada">Canada</option>
<option value="Canary Islands">Canary Islands</option>
<option value="Cape Verde">Cape Verde</option>
<option value="Cayman Islands">Cayman Islands</option>
<option value="Central African Republic">Central African Republic</option>
<option value="Chad">Chad</option>
<option value="Channel Islands">Channel Islands</option>
<option value="Chile">Chile</option>
<option value="China">China</option>
<option value="Christmas Island">Christmas Island</option>
<option value="Cocos Island">Cocos Island</option>
<option value="Colombia">Colombia</option>
<option value="Comoros">Comoros</option>
<option value="Congo">Congo</option>
<option value="Cook Islands">Cook Islands</option>
<option value="Costa Rica">Costa Rica</option>
<option value="Cote DIvoire">Cote D'Ivoire</option>
<option value="Croatia">Croatia</option>
<option value="Cuba">Cuba</option>
<option value="Curaco">Curacao</option>
<option value="Cyprus">Cyprus</option>
<option value="Czech Republic">Czech Republic</option>
<option value="Denmark">Denmark</option>
<option value="Djibouti">Djibouti</option>
<option value="Dominica">Dominica</option>
<option value="Dominican Republic">Dominican Republic</option>
<option value="East Timor">East Timor</option>
<option value="Ecuador">Ecuador</option>
<option value="Egypt">Egypt</option>
<option value="El Salvador">El Salvador</option>
<option value="Equatorial Guinea">Equatorial Guinea</option>
<option value="Eritrea">Eritrea</option>
<option value="Estonia">Estonia</option>
<option value="Ethiopia">Ethiopia</option>
<option value="Falkland Islands">Falkland Islands</option>
<option value="Faroe Islands">Faroe Islands</option>
<option value="Fiji">Fiji</option>
<option value="Finland">Finland</option>
<option value="France">France</option>
<option value="French Guiana">French Guiana</option>
<option value="French Polynesia">French Polynesia</option>
<option value="French Southern Ter">French Southern Ter</option>
<option value="Gabon">Gabon</option>
<option value="Gambia">Gambia</option>
<option value="Georgia">Georgia</option>
<option value="Germany">Germany</option>
<option value="Ghana">Ghana</option>
<option value="Gibraltar">Gibraltar</option>
<option value="Great Britain">Great Britain</option>
<option value="Greece">Greece</option>
<option value="Greenland">Greenland</option>
<option value="Grenada">Grenada</option>
<option value="Guadeloupe">Guadeloupe</option>
<option value="Guam">Guam</option>
<option value="Guatemala">Guatemala</option>
<option value="Guinea">Guinea</option>
<option value="Guyana">Guyana</option>
<option value="Haiti">Haiti</option>
<option value="Hawaii">Hawaii</option>
<option value="Honduras">Honduras</option>
<option value="Hong Kong">Hong Kong</option>
<option value="Hungary">Hungary</option>
<option value="Iceland">Iceland</option>
<option value="India">India</option>
<option value="Indonesia">Indonesia</option>
<option value="Iran">Iran</option>
<option value="Iraq">Iraq</option>
<option value="Ireland">Ireland</option>
<option value="Isle of Man">Isle of Man</option>
<option value="Israel">Israel</option>
<option value="Italy">Italy</option>
<option value="Jamaica">Jamaica</option>
<option value="Japan">Japan</option>
<option value="Jordan">Jordan</option>
<option value="Kazakhstan">Kazakhstan</option>
<option value="Kenya">Kenya</option>
<option value="Kiribati">Kiribati</option>
<option value="Korea North">Korea North</option>
<option value="Korea Sout">Korea South</option>
<option value="Kuwait">Kuwait</option>
<option value="Kyrgyzstan">Kyrgyzstan</option>
<option value="Laos">Laos</option>
<option value="Latvia">Latvia</option>
<option value="Lebanon">Lebanon</option>
<option value="Lesotho">Lesotho</option>
<option value="Liberia">Liberia</option>
<option value="Libya">Libya</option>
<option value="Liechtenstein">Liechtenstein</option>
<option value="Lithuania">Lithuania</option>
<option value="Luxembourg">Luxembourg</option>
<option value="Macau">Macau</option>
<option value="Macedonia">Macedonia</option>
<option value="Madagascar">Madagascar</option>
<option value="Malaysia">Malaysia</option>
<option value="Malawi">Malawi</option>
<option value="Maldives">Maldives</option>
<option value="Mali">Mali</option>
<option value="Malta">Malta</option>
<option value="Marshall Islands">Marshall Islands</option>
<option value="Martinique">Martinique</option>
<option value="Mauritania">Mauritania</option>
<option value="Mauritius">Mauritius</option>
<option value="Mayotte">Mayotte</option>
<option value="Mexico">Mexico</option>
<option value="Midway Islands">Midway Islands</option>
<option value="Moldova">Moldova</option>
<option value="Monaco">Monaco</option>
<option value="Mongolia">Mongolia</option>
<option value="Montserrat">Montserrat</option>
<option value="Morocco">Morocco</option>
<option value="Mozambique">Mozambique</option>
<option value="Myanmar">Myanmar</option>
<option value="Nambia">Nambia</option>
<option value="Nauru">Nauru</option>
<option value="Nepal">Nepal</option>
<option value="Netherland Antilles">Netherland Antilles</option>
<option value="Netherlands">Netherlands (Holland, Europe)</option>
<option value="Nevis">Nevis</option>
<option value="New Caledonia">New Caledonia</option>
<option value="New Zealand">New Zealand</option>
<option value="Nicaragua">Nicaragua</option>
<option value="Niger">Niger</option>
<option value="Nigeria">Nigeria</option>
<option value="Niue">Niue</option>
<option value="Norfolk Island">Norfolk Island</option>
<option value="Norway">Norway</option>
<option value="Oman">Oman</option>
<option value="Pakistan">Pakistan</option>
<option value="Palau Island">Palau Island</option>
<option value="Palestine">Palestine</option>
<option value="Panama">Panama</option>
<option value="Papua New Guinea">Papua New Guinea</option>
<option value="Paraguay">Paraguay</option>
<option value="Peru">Peru</option>
<option value="Phillipines">Philippines</option>
<option value="Pitcairn Island">Pitcairn Island</option>
<option value="Poland">Poland</option>
<option value="Portugal">Portugal</option>
<option value="Puerto Rico">Puerto Rico</option>
<option value="Qatar">Qatar</option>
<option value="Republic of Montenegro">Republic of Montenegro</option>
<option value="Republic of Serbia">Republic of Serbia</option>
<option value="Reunion">Reunion</option>
<option value="Romania">Romania</option>
<option value="Russia">Russia</option>
<option value="Rwanda">Rwanda</option>
<option value="St Barthelemy">St Barthelemy</option>
<option value="St Eustatius">St Eustatius</option>
<option value="St Helena">St Helena</option>
<option value="St Kitts-Nevis">St Kitts-Nevis</option>
<option value="St Lucia">St Lucia</option>
<option value="St Maarten">St Maarten</option>
<option value="St Pierre &amp; Miquelon">St Pierre &amp; Miquelon</option>
<option value="St Vincent &amp; Grenadines">St Vincent &amp; Grenadines</option>
<option value="Saipan">Saipan</option>
<option value="Samoa">Samoa</option>
<option value="Samoa American">Samoa American</option>
<option value="San Marino">San Marino</option>
<option value="Sao Tome &amp; Principe">Sao Tome &amp; Principe</option>
<option value="Saudi Arabia">Saudi Arabia</option>
<option value="Senegal">Senegal</option>
<option value="Serbia">Serbia</option>
<option value="Seychelles">Seychelles</option>
<option value="Sierra Leone">Sierra Leone</option>
<option value="Singapore">Singapore</option>
<option value="Slovakia">Slovakia</option>
<option value="Slovenia">Slovenia</option>
<option value="Solomon Islands">Solomon Islands</option>
<option value="Somalia">Somalia</option>
<option value="South Africa">South Africa</option>
<option value="Spain">Spain</option>
<option value="Sri Lanka">Sri Lanka</option>
<option value="Sudan">Sudan</option>
<option value="Suriname">Suriname</option>
<option value="Swaziland">Swaziland</option>
<option value="Sweden">Sweden</option>
<option value="Switzerland">Switzerland</option>
<option value="Syria">Syria</option>
<option value="Tahiti">Tahiti</option>
<option value="Taiwan">Taiwan</option>
<option value="Tajikistan">Tajikistan</option>
<option value="Tanzania">Tanzania</option>
<option value="Thailand">Thailand</option>
<option value="Togo">Togo</option>
<option value="Tokelau">Tokelau</option>
<option value="Tonga">Tonga</option>
<option value="Trinidad &amp; Tobago">Trinidad &amp; Tobago</option>
<option value="Tunisia">Tunisia</option>
<option value="Turkey">Turkey</option>
<option value="Turkmenistan">Turkmenistan</option>
<option value="Turks &amp; Caicos Is">Turks &amp; Caicos Is</option>
<option value="Tuvalu">Tuvalu</option>
<option value="Uganda">Uganda</option>
<option value="Ukraine">Ukraine</option>
<option value="United Arab Erimates">United Arab Emirates</option>
<option value="United Kingdom">United Kingdom</option>
<option value="United States of America">United States of America</option>
<option value="Uraguay">Uruguay</option>
<option value="Uzbekistan">Uzbekistan</option>
<option value="Vanuatu">Vanuatu</option>
<option value="Vatican City State">Vatican City State</option>
<option value="Venezuela">Venezuela</option>
<option value="Vietnam">Vietnam</option>
<option value="Virgin Islands (Brit)">Virgin Islands (Brit)</option>
<option value="Virgin Islands (USA)">Virgin Islands (USA)</option>
<option value="Wake Island">Wake Island</option>
<option value="Wallis &amp; Futana Is">Wallis &amp; Futana Is</option>
<option value="Yemen">Yemen</option>
<option value="Zaire">Zaire</option>
<option value="Zambia">Zambia</option>
<option value="Zimbabwe">Zimbabwe</option>
</select>
                                    </select></p>
                                    <input type="submit "  class="btn btn-primary" value="Upload document" />

                                </form>
</div>
                        </div>
                        
                            </div>
                        
                                     <div class="col-lg-4  col-md-3 col-xs-12 col-md-offset-0 index-img-client">
<div class="panel panel-primary">
  <div class="panel-heading" style="background-color:#39B7CD">Panel Heading</div>
  <div class="panel-body" style="height:100%;">
                   <div class="form-group">


                                    <form id="fake_textarea" class="form-control" style="height:100%; font-size: 16px; border:none">
                                        <input class="form-control" type='hidden' id='fake_textarea_content' name='foobar' /></form>
</div>
</div>
                                    <script>
                                        function addCell(cell, text) {
                                            if (text != null)
                                                cell.innerHTML =  
                                                    "<label style = \"font-weight: normal;\"><input type=\"checkbox\" name=\"checkbox\" value=\"value\";>&nbsp;" + text + "</label>"
                                                    ;
                                        }
                                        window.onload = function () {
                                            var i, output;
                                            var json = ${article};
                                            var events = ${events};
                                            output = "";
                                            var response = JSON.parse(json.response);

                                            var text = response.text;
                                            console.log(text);
                                            for (i = 0; i < text.length; i++) {
                                                var word = text[i].content;
                                                var tag = text[i].tag;
                                                if (tag == "weapon")
                                                {
                                                    output +=
                                                            "<span style='background:blue;color:white'>" + word + "</span> ";
                                                } else
                                                if (tag == "what")
                                                {
                                                    output +=
                                                            "<span style='background:purple;color:white'>" + word + "</span>  ";
                                                } else
                                                if(tag == "where")
                                                {
                                                    output += 
                                                            "<span style='background:orange;color:white'>" + word + "</span> ";
                                                } else 
                                                if(tag == "who")
                                                {
                                                    output += 
                                                            "<span style='background:green;color:white'>" + word + "</span> ";
                                                } else 
                                                if(tag == "whom")
                                                {
                                                    output += 
                                                            "<span style='background:red;color:white'>" + word + "</span> ";
                                                } else 
                                                if(tag == "when")
                                                {
                                                    output += 
                                                            "<span style='background:yellow;color:black'>" + word + "</span> ";
                                                } else {
                                                    output += 
                                                            "<span style='color:black'>" + word + "</span> ";
                                                }
                                            }
                                            document.getElementById("fake_textarea").innerHTML = output;
                                            var i;
                                            var table = response.table;
                                            var who = table.who;
                                            var what = table.what;
                                            var weapon = table.weapon;
                                            var whom = table.whom;
                                            var when = table.when;
                                            var where = table.where;
                                            var countries = table.country;
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
                                            for (i = 0; i < events.length; ++i)
                                            {
                                                $('#event_select')
                                                    .append($("<option></option>")
                                                    .attr("value", events[i].id)
                                                    .text(events[i].title)); 
                                            }
                                            $("#selectable").selectable();      
                                            $("#id").attr("value",${id});
                   
                                            $("#event_select").val(json.eventId);
                                            $( "#date" ).datepicker({ dateFormat: 'yy-mm-dd' });

                                            for (i = 0; i < countries.length; ++i) {
                                                $('#countries')
                                                        .append($("<option></option>")
                                                                .attr("value", i.toString())
                                                                .text(countries[i]));
                                            }
                                        }
                                    </script>

                                </div>
                            </div>
                        </div>
                       <div id="footer" style="font-size: 15px;" >
      <div class="container">
        <p class="muted credit">W6 project copyright <a href="">2016 </p>
      </div>
    </div>                     
                    </div>
                </div>
            </div>
    </body>
</html>
