
<%@ page import="databean.questionBean"%>
<jsp:include page="template-top.jsp" />


				<div>
				<jsp:include page="nav.jsp"></jsp:include>
					<tag:loggedin>

						<h2>&nbsp;&nbsp;&nbsp; Game Play</h2>
						<h4 class="page-body" style="color: green">${msg}</h4>
						<jsp:include page="error-list.jsp" />

						<label>&nbsp;&nbsp;&nbsp;&nbsp; Please enter the
							celebrity's name in the field:</label>
						<br>
						<br>
						<form action="answer.do" method="post">
							<table>
								<tr>
									<td><img border="0" src = ${question.url}
										align="left"><br></td>
								</tr>
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td><input name="text"
										placeholder="Type in name of this celebrity"
										class="form-control"></td>
										<input type = "hidden" name = "id" value =  ${question.id}>
								</tr>
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="submit" name="post" value="Answer"
										class="btn btn-primary">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
										type="submit" name="post" value="Ask for help!"
										class="btn btn-success">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</form>

										<form action="./displayChart.do" method="post">
											<input type="submit" name="post" value="Give up!"
												class="btn btn-danger">
										</form>
									</td>
								</tr>
							</table>
					</tag:loggedin>
				</div>
				
<jsp:include page="template-bottom.jsp" />
