<%@ page import="databean.PostTwit"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="nav.jsp"></jsp:include>
<div class="row">

	<div class="col-md-4" role="main">
		<h2>${yes}</h2>

		<table>
			<c:forEach var="post" items="${posts}">
				<tr>
					<td><a href="http://twitter.com/">profile</a></td>
					<td>${post.screenName}</td>
				</tr>
				<tr>
					<td>#Whoisthis?</td>
				</tr>
				<tr>
				</tr>
			</c:forEach>
		</table>


	</div>

</div>


<jsp:include page="template-bottom.jsp" />
