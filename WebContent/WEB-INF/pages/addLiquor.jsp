<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<div class="pad-20">
	
	<form:form class="form" method="POST" modelAttribute="liquor" action="${pageContext.request.contextPath}/liquor/addLiquor">
	<h2>Add New Liquor</h2>
		
		<div class="newline-elements">
		<form:errors class="form-error" path="liquorCode"/>
		<div class="inline-elements">
		<i title="The unique liquor identifier" class="btn-blue icon fas fa-hashtag"></i>
		<form:input path="liquorCode" placeholder="Liquor Code" minlength="5" maxlength="10" required="true" />
		</div>
		</div>
		
		<div class="newline-elements">
		<form:errors class="form-error" path="brandName"/>
		<div class="inline-elements">
		<i title="The brand name of the liquor" class="btn-blue icon fas fa-tag"></i>
		<form:input path="brandName" placeholder="Brand Name" maxlength="25" required="true" />
		</div>
		</div>
		
		<div class="newline-elements">
		<form:errors class="form-error" path="alcoholType"/>
		<div class="inline-elements">
		<i title="A brief description of the alcohol" class="btn-blue icon fas fa-wine-bottle"></i>
		<form:input path="alcoholType" placeholder="Alcohol Type" maxlength="25" required="true" />
		</div>
		</div>
		
		<div class="newline-elements">
		<form:errors class="form-error" path="liquidVolume"/>
		<div class="inline-elements">
		<i title="The liquid volume of your liquor" class="btn-blue icon fas fa-weight"></i>
		<form:input  type="number" min="0" max="1.5" step="0.01" path="liquidVolume" placeholder="Liquid Volume (liters)" required="true" value="_blank()" />
		</div>
		</div>
		
		<div class="newline-elements">
		<form:errors class="form-error" path="overflow"/>
		<div class="inline-elements">
		<i title="The current in-stock quantity" class="btn-blue icon fas fa-cubes"></i>
		<form:input type="number" min="0" max="100" path="overflow" placeholder="Overflow Quantity" required="true" value="_blank()" />
		</div>
		</div>
		
		<div class="newline-elements">
		<form:errors class="form-error" path="alertLevel"/>
		<div class="inline-elements">
		<i title="The overflow level to be alerted at if reached" class="btn-blue icon fas fa-exclamation-triangle"></i>
		<form:input type="number" min="0" max="100" path="alertLevel" placeholder="Alert Level Quantity" required="true" value="_blank()" />
		</div>
		</div>
		
		<input class="btn-green font-bold mgn-col-10" type="submit" value="Add"/>
		<div class="btn btn-red mgn-col-10">
		<a class="font-white font-bold" href="${pageContext.request.contextPath}/liquor/listLiquor">Cancel</a>
		</div>
		
	</form:form>

	<c:choose>
		<c:when test="${not empty error}">
			<div class="error">${error}</div>
		</c:when>
	</c:choose>
	
</div>