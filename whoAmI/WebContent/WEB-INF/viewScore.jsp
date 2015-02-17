<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="databean.FriendHelp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<jsp:include page="template-top.jsp" />


<div>
	<jsp:include page="nav.jsp" />

	<div class="row">
		<div class="col-md-8" role="main">
			<div class="panel panel-default" align="left">

				<div class="panel-heading">
					<h3 class="panel-title">Current Score:</h3>
				</div>
				<div class="panel-body"><font size="5" color="green">${score}</font></div>

				<div class="panel-heading">
					<h3 class="panel-title">All your friends' help</h3>
				</div>


				<div class="panel-body">
					<table class="table table-hover">


						<thead>
							<tr>
								<th>Friend</th>
								<th>Question</th>
								<th>Answer</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="friendhelp" items="${friendhelp}">
								<tr>
									<td>${friendhelp.friend}</td>
									<td><a href=${friendhelp.picUrl}><img border="0"
											src=${friendhelp.picUrl } align="left" height=100></a></td>
									<td>${friendhelp.answer}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>


			</div>
			<div class="col-md-4" role="main"></div>
		</div>

	</div>
	<jsp:include page="template-bottom.jsp" />