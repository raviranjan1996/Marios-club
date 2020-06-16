<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<h1 class="header">Details of all Members</h1>

<table class="formtable">
<tr><td>User Name</td><td>Email</td><td>Role</td><td>Enabled</td><td>Name</td></tr>
<c:forEach var="user" items="${users}">

<tr><td><c:out value="${user.username}"></c:out>
<td><c:out value="${user.email}"></c:out></td>
<td><c:out value="${user.authority}"></c:out></td>
<td><c:out value="${user.enabled}"></c:out></td>
<td><c:out value="${user.name}"></c:out></td>

</tr>

</c:forEach>

</table>

