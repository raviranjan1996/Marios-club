<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function onload() {

		$("#password").keyup(checkPassword);
		$("#confirmpass").keyup(checkPassword);

		$("#details").submit(cantSubmit);

	}

	function cantSubmit() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		if (password != confirmpass) {
			alert("password not matched")
			return false;
		} else {
			return true;
		}
	}

	function checkPassword() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password.length > 3 || confirmpass.length > 3) {
			if (password == confirmpass) {
				$("#matchpass")
						.text(
								"<fmt:message key='MatchedPassword.user.password'></fmt:message>");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("error");
			} else {
				$("#matchpass")
						.text(
								"<fmt:message key='UnmatchedPassword.user.password'></fmt:message>");
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("valid");
			}
		}
	}
	$(document).ready(onload);
</script>