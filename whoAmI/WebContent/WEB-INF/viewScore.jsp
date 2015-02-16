<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="databean.FriendHelp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<jsp:include page="template-top.jsp" />


<div>
	<jsp:include page="nav.jsp" />

	<div class="panel panel-default" align="left">

		<div class="panel-heading">
			<h3 class="panel-title">Current Score:</h3>
		</div>
		<h3 class="panel-body">${score}</h3>


		<div class="panel-body">
			<table class="table table-hover">

				<h3>All your friends' help</h3>
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

</div>
<jsp:include page="template-bottom.jsp" />