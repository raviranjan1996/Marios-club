<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<h1>Create Account</h1>

	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		modelAttribute="user">

		<table class="formtable">
			<tr>
				<td class="label">UserName:</td>
				<td><sf:input class="control" path="username" name="username"
						type="text"  placeholder="Username" /><br />
				<div class="error">
						<sf:errors path="username"></sf:errors>
					</div></td>
			</tr>
			
			
			<tr>
				<td class="label">Name:</td>
				<td><sf:input class="control" path="name" name="name"
						type="text"  placeholder="name" onfocus="this.value=''"/><br />
				<div class="error">
						<sf:errors path="name"></sf:errors>
					</div></td>
			</tr>
			
			
			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" name="email"
						type="text" /><br />
				<div class="error">
						<sf:errors path="email"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input id="password" class="control" path="password"
						name="password" type="password" value="" placeholder="Password"/><br />
				<div class="error">
						<sf:errors path="password"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Confirm Password:</td>
				<td><input id="confirmpass" class="control" name="confpass"
					type="password" /><br>
				<br>
				<div id="matchpass">
						<sf:errors></sf:errors>
					</div>
					<br /></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="Create Account" type="submit" /></td>
			</tr>
		</table>

	</sf:form>

