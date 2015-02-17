<%@ page import="databean.questionBean"%>
<%@ page import="databean.PostTwit"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>


<jsp:include page="template-top.jsp" />
<jsp:include page="nav.jsp"></jsp:include>
<div class="row">
	<div class="col-md-8" role="main">
		<div>
			<form action="firstpage.do" method="post">
				<table>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
							name="post" value="Answer" class="btn btn-success ">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
							name="post" value="Ask for help!" class="btn btn-primary">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</form>

							<form action="./displayChart.do" method="post">
								<input type="submit" name="post" value="Give up!"
									class="btn btn-danger">
							</form>
						</td>
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
					<tr align="left">
						<td align="left"><img border="0" src=${question.url
							}
							width="500" align="left"><br></td>
					</tr>

				</table>
		</div>
	</div>
	<div class="col-md-4" role="main">

		<div class="panel panel-default" align="left">
			<div class="panel-heading">
				<h3 class="panel-title">Feedback</h3>
			</div>
			<div class="panel-body">
				<h3 class="panel-title" style="color: green">${msg}</h3>
				<h3 class="panel-title" style="color: red">${errmsg}</h3>
			</div>


			<tag:posted>
				<div class="panel-heading">
					<h3 class="panel-title">Latest 5 posts</h3>
				</div>

				<c:forEach var="post" items="${posts}">

					<div class="panel-body">
						<a href=${post.userUrl}><img src=${post.profileImageUrl}></a><font size="3">${post.userName}</font> <a href=${post.userUrl}>@${post.screenName}</a>
						<br><br>
						<a href="https://twitter.com/hashtag/WhoIsThis?src=hash">#WhoIsThis?</a>
						HELP ME!!! do you know who is this? <br> 
						<br>
						<img src="twittericon.png" width="150" align="left"><div align="right"><a href=${post.twitUrl}><font size="2">view more</font></a>&#12288;&#12288;</div>
						<br>
						<a href=${post.twitUrl}><img
							src=${post.picUrl} width="250"></a>
							<hr>
					</div>
				</c:forEach>
			</tag:posted>
		</div>

	</div>


	<jsp:include page="template-bottom.jsp" />