<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html>
<head>

<title>Oscars Celebrity Guessing Application</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js">
</script>
<style>
div.col-md-2{
	  background: rgb(244,244,244);
	  height: 800px;
	}
	
div.footer{
	position: relative;
    bottom:0;
}
#menu{

}
body{
position: absolute;
height: auto !important;
height: 100%;
position:relative; z-index:0;
background-image:url(oscar.jpg);
opacity:0.95;
}
h2{
	font-size: 42px;
	color: rgb(255, 128, 0);
    text-shadow: 4px 4px 3px rgba(204, 0, 0, 1);
    font-weight: bold;
}
</style>
<meta charset="UTF-8">

</head>

<%
        String menu = request.getParameter("COLOR");       
        if (menu == null)
          menu = "black";        
     %>

<body>
	

		<div id ="menu" class="row">
			<div class="col-md-2" role="complementary">
				<div class="sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header"><h4>Scores</h4></li>
						<li class="active"><a href="viewScore.do">My Score</a></li>
						<li class="active"><a href="scoreboard.do">Scoring Board</a></li>
					</ul><br>
					<ul class="nav nav-list">
						<li class="nav-header"><h4>Game</h4></li>
						<li><a href="firstpage.do">Play Game!</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-10" role="main">

				<td>
					<!-- Padding (blank space) between navbar and content -->
				</td>