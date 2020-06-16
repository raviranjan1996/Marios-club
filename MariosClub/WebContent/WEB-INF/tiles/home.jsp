<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<link href="${pageContext.request.contextPath}/static/css/datatables.min.css" rel="stylesheet" type=text/css>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/datatables.min.js"></script>

<table class="offers" id="myTable">
<thead>
	<tr>
		<td>Name</td>
		<td>Email</td>
		<td>Offer</td>
		<td>Contact</td>
	</tr>
</thead>
<tbody>
	<c:forEach var="offer" items="${offers}">
		<tr class="width">

			<td ><c:out value="${offer.user.name}"></c:out></td>

			<td ><c:out value="${offer.user.email}"></c:out></td>

			<td><c:out value="${offer.text}"></c:out></td>
			
			<td><a href="<c:url value='/message?uid=${offer.username}'/>">Contact</a></td>

		</tr>
	</c:forEach>
</tbody>
</table>


<script type="text/javascript">

$(document).ready( function () {
     $('#myTable').DataTable();
  
});
</script>

<script type="text/javascript">

function updateMessageLink(data){
	$("#numbermessages").text(data.number);
}

function onLoad(){
	updatePage();
	window.setInterval(updatePage ,5000);
}

function updatePage(){
	$.getJSON("<c:url value="/getmessages"></c:url>" , updateMessageLink);
}

$(document).ready(onLoad);

</script>



