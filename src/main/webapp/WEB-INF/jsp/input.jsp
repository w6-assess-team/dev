<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../ls/table.js"></script>
  <script type="text/javascript" src="../ls/readTextFile.js"></script>
</head>
<body >


<div class="container">
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
        <form action="parse" method="POST">
            <input id="text" name="text" style="height:450px" contenteditable/>
            <input type="submit" value="Submit" />
        </form>
      </div>
    </div>
  </div>
</div>
 
</body>
</html>