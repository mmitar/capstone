<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<div class="pad-20">
	<h1>${status}</h1>
	<br/>
	<hr/>
	<h4>${error}</h4>
	
	<br/>
	<img style="max-width:100%" src="${image}">
	<br/>
	<br/>
	<a href="${pageContext.request.contextPath}/user/login">Back to Home</a>
</div>