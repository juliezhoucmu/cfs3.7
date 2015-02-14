

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="databean.Comment"%>
<!DOCTYPE html >
<html>
<head>
<title>Oscars Celebrity Guessing Application</title>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<style>form{margin:0px; display:inline}</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<nav class="navbar navbar-default" role="main">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Guess Who is this?</a>
				</div>
				<div>
						<p class="navbar-text" align="right">
							Hello ${twitter.screenName} <a href="./logout"><button
									class="btn btn-link" type="button">Logout</button></a>
						</p>
					</div>
					</nav>
		</div>
		<div class="row">
			<div class="col-md-10" role="main" align="center">
				<div>
						<jsp:include page="template-cus.jsp" />

<div class="col-lg-6">

		<div class="form-group">



				
			<table class="table table-bordered table-hover">
			
				<tbody>
				<c:forEach var="comment" items="${CommentList}">

						<tr>
							<td>${comment.name}"</td>
							<td>${comment.positiveCon}</td>
							<td>${comment.negativeCon}</td>
							<td>${comment.neutralCon}</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
  <script type="text/javascript">
    google.load('visualization', '1.1', {packages: ['line']});
    google.setOnLoadCallback(drawChart);

    function drawChart() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', 'Day');
      data.addColumn('number', 'Positive Comments');
      data.addColumn('number', 'Neutral Comments');
      data.addColumn('number', 'Negative Comments');
      
    	  var commentArray = new Array();
			<c:forEach var="comment" items="${CommentList}">

    	    commentArray[${comment.id}] = "${comment}";
    	    
    	    data.addRow([${comment.id},${comment.positiveCon},${comment.negativeCon},${comment.neutralCon}]);
    	    
    	   </c:forEach>
    	
  
     
     

      var options = {
        chart: {
          title: 'comments on twitter for',
          subtitle: 'katy perry'
        },
        width: 900,
        height: 500,
        axes: {
          x: {
            0: {side: 'top'}
          }
        }
      };

      var chart = new google.charts.Line(document.getElementById('line_top_x'));

      chart.draw(data, options);
    }
  </script>
</head>
<body>
  <div id="line_top_x"></div>
</body>

	<td valign="top"></td>
<jsp:include page="template-bottom.jsp" />