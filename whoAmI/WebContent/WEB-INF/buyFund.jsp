<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-cus.jsp" />


<%@ page import="databean.FundItem"%>


<ul class="breadcrumb">

	<li class="active"><a href="#">Funds</a> <span class="divider">/
	</span></li>

	<li><a href="buyFund.do">Buy Fund</a> <span class="divider">/
	</span></li>
</ul>




<h2>Current valid balance is: ${balance} $</h2>
<h4 class="page-body"  style="color:green">${msg}</h4>
 <jsp:include page="error-list.jsp" />
 
<form method="post">
 <table class="table table-bordered">
    <thead>
      <tr>
        <th>Fund Name</th>
        <th>Fund ID</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      
     <c:forEach var="f" items="${fundTable}">    
      <tr>
        <td>${f.name }</td>
        <td>${f.symbol }</td>
        <td align="center"><input type="radio" name="fund_id" value="${f.fund_id }"></td>
      </tr>
	</c:forEach>
    </tbody>
    
  </table>

 <br />
 <h4 align="center">   Amount to buy: 
      <input type="text" id="buyAmount" name="buyAmount"> 
      <input class="btn btn-default" name="Action" type="submit" value="Buy">
 </h4>
 <br />
 <br />
 <br />
</form>
<jsp:include page="template-bottom.jsp" />
