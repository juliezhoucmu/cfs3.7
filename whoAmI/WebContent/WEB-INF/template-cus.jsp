<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="databean.Customer"%>

<html>
<head>

<title>Oscars Celebrity Guessing Application</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js">
</script>
<meta charset="UTF-8">

</head>

<%
        String menu = request.getParameter("COLOR");       
        if (menu == null)
          menu = "black";        
     %>

<body>
	<div class="container">
		<div class="row" align="center">
			<nav class="navbar navbar-default" role="main">
				<div class="navbar-header">
					<a class="navbar-brand" href="customermanage.do">Oscars Celebrity Guessing Application</a>
				</div>
				<div>
					<p class="navbar-text" align="right">Welcome: ${customer.username}</p>
				</div>
				<div>
					<p class="navbar-text" align="right"><a href="logout.do"> Logout</a></p>
				</div>
			</nav>
		</div>

		<div id ="menu" class="row">
			<div class="col-md-2" role="complementary">
				<div class="sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">Account</li>
						<li class="active"><a href="changePassword.do">Change
								Password</a></li>
						<li><a href="viewAccAction.do">View Account</a></li>
					</ul>
					<ul class="nav nav-list">
						<li class="nav-header">Scoring Board</li>
						<li class="active"><a href="transactionHistory.do">Gameplay History</a></li>
					</ul>
					<ul class="nav nav-list">
						<li class="nav-header">Game</li>
						<li><a href="gameplay.do">Play Game!</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-10" role="main">

				<td>
					<!-- Padding (blank space) between navbar and content -->
				</td>