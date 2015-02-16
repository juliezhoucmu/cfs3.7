<jsp:include page="template-top.jsp" />
				<div>
						<jsp:include page="nav.jsp" />

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