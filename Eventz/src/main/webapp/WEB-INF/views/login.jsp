<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>View Page</title>
<base href="${pageContext.request.contextPath}">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/login.css" />
<script type="text/javascript"	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</head>
<body>
	
	<jsp:include page="common/header.jsp"></jsp:include>

	<div class="content" id="wrapper">
	
		<div id="login" class="animate form">
			<form action="<%=request.getContextPath()%>/account" method="POST" autocomplete="on">
				<h1>Log in</h1>
				<div class="errors">
					<ul>
						<c:forEach items="${errors}" var="current">
							<li>${current}</li>
						</c:forEach>
					</ul>
				</div>
				<p>
					<label for="email" class="uname" data-icon="u"> Your
						email </label> <input id="username" name="email"
						required="required" type="text"
						placeholder="mymail@mail.com">
				</p>
				<p>
					<label for="password" class="youpasswd" data-icon="p"> Your
						password </label> <input id="password" name="password" required="required"
						type="password" placeholder="eg. X8df!90EO">
				</p>
				<p class="keeplogin">
					<input type="checkbox" name="loginkeeping" id="loginkeeping"
						value="loginkeeping"> <label for="loginkeeping">Keep
						me logged in</label>
				</p>
				<p class="login button">
					<input type="submit" name="submit" value="Login">
				</p>
				<p class="change_link">
					Not a member yet ? <a style="cursor:pointer;" onclick="toggleForms()" class="to_register">Join
						us</a>
				</p>
			</form>
		</div>
		<div id="register" class="animate form">
			<form action="<%=request.getContextPath()%>/account" method="POST" autocomplete="on">
				<h1>Sign up</h1>
				<div class="errors">
					<ul>
						<c:forEach items="${errors}" var="current">
							<li>${current}</li>
						</c:forEach>
					</ul>
				</div>
				<p>
					<label for="usernamesignup" class="uname" data-icon="u">Your
						first name</label> <input id="fnamesignup" name="fname"
						required="required" type="text" placeholder="John">
				</p>
				<p>
					<label for="emailsignup" class="youmail" data-icon="e">
						Your email</label> <input id="emailsignup" name="email"
						required="required" type="email"
						placeholder="mysupermail@mail.com">
				</p>
				<p>
					<label for="passwordsignup" class="youpasswd" data-icon="p">Your
						password </label> <input id="passwordsignup" name="password" maxlength="16" pattern="^\S{6,}$"
						required="required" type="password" placeholder="eg. X8df!90EO">
				</p>
				<p>
					<label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Please
						confirm your password </label> <input id="passwordsignup_confirm" maxlength="16" pattern="^\S{6,}$"
						name="password_confirm" required="required" type="password"
						placeholder="eg. X8df!90EO">
				</p>
				<p class="signin button">
					<input type="submit" name="submit" value="Sign up">
				</p>
				<p class="change_link">
					Already a member ? <a style="cursor:pointer;" onclick="toggleForms()" class="to_register"> Go
						and log in </a>
				</p>
			</form>
		</div>
	</div>
	
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/common/common.js"></script>
	<script type="text/javascript">
		if (<%= "Sign up".equalsIgnoreCase(request.getParameter("submit"))%> )
			toggleForms(true);
	</script>
	
</body>
</html>
