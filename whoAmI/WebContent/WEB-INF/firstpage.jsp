<%@ page import="databean.questionBean"%>
<%@ page import="databean.PostTwit"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="nav.jsp"></jsp:include>
<div class="row">
	<div class="col-md-8" role="main">
		<div>
			<form action="firstpage.do" method="post">
				<table>
					<tr align="left">
						<td align="left"><img border="0" 
							src=${question.url} height="350" align="left"><br></td>
					</tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td><input name="text"
							placeholder="Type in name of this celebrity" class="form-control"></td>
						<input type="hidden" name="id" value=${question.id}>
					</tr>
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
							name="post" value="Answer" class="btn btn-primary">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
							name="post" value="Ask for help!" class="btn btn-success">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</form>

							<form action="./displayChart.do" method="post">
								<input type="submit" name="post" value="Give up!"
									class="btn btn-danger">
							</form>
						</td>
					</tr>
					<tr>
						<td><h4 class="page-body" style="color: green">${msg}</h4></td>
					</tr>
				</table>
		</div>
	</div>
	<div class="col-md-4" role="main">

		<tag: notPosted>
			<h1>Not posted yet</h1>
		</tag: notPosted>

		<tag: posted>
			<p>posted</p>
		
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
		</tag: posted>

	</div>

</div>


<jsp:include page="template-bottom.jsp" />
