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
							Hello ${twitter.screenName} <a href="./logout"><button
									class="btn btn-link" type="button">Logout</button></a>
						</p>
					</div>
				</tag:loggedin>
			</nav>
		</div>

		<div class="row">
			<div class="col-md-10" role="main" align="center">
				<div>
					<tag:loggedin>
						<jsp:include page="template-cus.jsp" />

						<h2>&nbsp;&nbsp;&nbsp; Game Play</h2>
						<h4 class="page-body" style="color: green">${msg}</h4>
						<jsp:include page="error-list.jsp" />

						<label>&nbsp;&nbsp;&nbsp;&nbsp; Please enter the
							celebrity's name in the field:</label>
						<br>
						<br>
						<form action="./post" method="post">
							<table>
								<tr>
									<td><img border="0" src="katy-perry-press-2013-650.jpg"
										align="left"><br></td>
								</tr>
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td><input name="text"
										placeholder="Type in name of this celebrity"
										class="form-control"></td>
								</tr>
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="submit" name="post" value="Answer"
										class="btn btn-primary">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="submit" name="post" value="Ask for help!"
										class="btn btn-success">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</form>
										

										<form action="./displayChart.do" method="post">
											<input type="submit" name="post" value="Give up!"
												class="btn btn-danger">
										</form>
									</td>
								</tr>
							</table>
					</tag:loggedin>
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


