<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header class="body">
		<h1><a href="<%=request.getContextPath()%>">Welcome Home!</a></h1>
		<c:choose>
    		<c:when  test="${empty user}">
				<span>Welcome guest!. <a href="<%=request.getContextPath()%>/account">Click here to login / sign up.</a></span>
			</c:when>
			<c:otherwise>
				<span>Welcome ${user.firstName}  &nbsp;&nbsp;&nbsp; <a href="<%=request.getContextPath()%>/logout">logout</a></span>
			</c:otherwise>
		</c:choose>
</header>