
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="databean.Comment"%>
<%@ page import="databean.Star"%>
<jsp:include page="template-top.jsp" />
<div>
	<jsp:include page="nav.jsp" />





	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
      google.load("visualization", "1.1", {packages:["bar"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Date');
          data.addColumn('number', 'Positive Comments');
          data.addColumn('number', 'Negative Comments');
          data.addColumn('number', 'Neutral Comments');
          
        	  var commentArray = new Array();
    			<c:forEach var="comment" items="${CommentList}">

        	    commentArray[${comment.id}] = "${comment}";
        	    
        	    data.addRow(['${comment.date}',${comment.positiveCon},${comment.negativeCon},${comment.neutralCon}]);
        	    
        	   </c:forEach>

        var options = {
          chart: {
            title: 'Celebrity Performance',
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

        var chart = new google.charts.Bar(document.getElementById('barchart_material'));

        chart.draw(data, options);
      }
    </script>
	</head>
	<body>
		<div class="col-lg-12">

			<div class="panel panel-default" align="left">


				<div class="panel-heading">
					<div class="panel-title">
						the <b>answer</b> is <font size="4" color="green"><b>${star.name}</b></font>
					</div>
				</div>
				<div class="panel-body">
					<table>
						<tr>
							<td><img src=${star.profileImgURL } width="300"></td>
							<td>&#12288;&#12288;&#12288;&#12288;</td>
							<td><div>

									<hr>
									<font size="4" color="green"><b>${star.name}</b></font> on <a
										href=${star.twitterUrl}><img
										src="./images/Twitter_logo_blue.png" height="30"></a> <b><font
										size="4">${star.twitterName}</font></b> <a href=${star.twitterUrl}>${star.screenName}</a>
									<hr>
									<p>
										<font size="3">${star.description}</font><br>
									</p>
								</div></td>
						</tr>
					</table>
				</div>



				<div class="panel-heading">
					<div class="panel-title">
						How people think of <b>${star.name}</b></font> on Twitter?
					</div>
				</div>

				<div class="panel-body">
					
				</div>
				<div id="barchart_material" style="width: 900px; height: 500px;"></div>
			</div>
		</div>

	</body>

	<td valign="top"></td>
	<jsp:include page="template-bottom.jsp" />