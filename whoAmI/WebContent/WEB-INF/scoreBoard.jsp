<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="databean.TwitterUser"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<jsp:include page="template-top.jsp" />


<div>
	<jsp:include page="nav.jsp" />

	<div class="row">
		<div class="col-md-8" role="main">
			<div class="panel panel-default" align="left">

				<div class="panel-heading">
					<div class="panel-title"><b>Your score is:   <font size="5" color="green">${score}</font></b></div>
				</div>
				<p class="panel-body">
					You are the top  <font size="4"><b>${top}%</b></font>  player
					<br>
					You have beaten <font size="4"><b>${beat}</b></font>  players
					
				</p>
				<div class="panel-heading">
					<h3 class="panel-title">Top 10 Players</h3>
				</div>
				<div class="panel-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Rank</th>
								<th>Name</th>
								<th>Score</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="player" items="${topten}" varStatus="status" >
								<tr>
									<td>${status.index+1}</td>
									<td><img src=${player.profileImgUrl}><font size="4"><b>${player.userName}</b></font>  @${player.screenName}</td>
									<td>${player.score}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-md-4" role="main"></div>
	</div>



</div>
<jsp:include page="template-bottom.jsp" />