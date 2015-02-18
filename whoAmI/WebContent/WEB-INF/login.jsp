<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html >
<html>
<head>
<title>Oscars Celebrity Guessing Application</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<style>
form {
	margin: 0px;
	display: inline
}

nav {
	width: 100%
}

div.box {
	position: absolute;
	height: 550px;
	position: relative;
	z-index: 0;
	background-image: url(people.png);
	opacity: 0.95;
	background-size: cover;
}

h2 {
	font-family: "Comic Sans MS", cursive, sans-serif;
	font-size: 50px;
	color: #FF0000;
	text-shadow: 4px 4px 3px rgba(204, 0, 0, 1);
	font-weight: bold;
}

h4 {
	font-family: "Comic Sans MS", cursive, sans-serif;
	font-size: 22px;
	color: yellow;
	font-weight: bold;
}
</style>
</head>
<body>

	<h2>
		<p style="text-align: center">Who is this?
	</h2>
	<div class="container">
		<div class="row">



			<div class="box">

				<br>
				<h4>
					<p style="text-align: center">

						&nbsp;&nbsp;&nbsp;&nbsp;<br> <br>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
						<br>

					</p>
				</h4>
				<div style align="center">
					<p style="text-align: center">
						<a href="signin"><img
							src="./images/sign.png" width="100" /></a>
					</p>
				</div>

			</div>


		</div>
		<div>
			<div align="center">Copyright &copy; 2015 Oscars</div>
			<div>
				<br>
			</div>
		</div>
</body>
</html>

