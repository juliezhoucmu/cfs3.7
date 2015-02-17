<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html >
<html>
<head>
<title>Oscars Celebrity Guessing Game</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<style>
form{margin:0px; display:inline}
nav{width:100%}


</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<nav class="navbar navbar-default" role="main">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Guess Who is this?</a>
				</div>
				<tag:notloggedin>
					<div>
						<p class="navbar-text" align="right">
							<a href="signin"><img
								src="./images/Sign-in-with-Twitter-darker.png" /></a>
						</p>
					</div>
				</tag:notloggedin>

				<tag:loggedin>
					<div>
						<p class="navbar-text" align="right">
							Hello ${twitteruser.userName} <a href="./logout"><button
									class="btn btn-link" type="button">Logout</button></a>
						</p>
					</div>
				</tag:loggedin>
			</nav>
		</div>

		<div class="row">
			<div class="col-md-12" role="main" align="center">