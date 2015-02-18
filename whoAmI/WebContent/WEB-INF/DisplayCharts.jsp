
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="databean.Comment"%>
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
    <div id="barchart_material" style="width: 900px; height: 500px;"></div>
  </body>

	<td valign="top"></td>
<jsp:include page="template-bottom.jsp" />