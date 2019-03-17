<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<!-- <h1>The Dream Stream</h1> -->

<c:choose>
	<c:when test="${not empty userToken}">
	
	<c:choose>
	<c:when test="${userToken.permission == 464646 || userToken.permission == 131313}">
	
	<div class="topnav" id="myTopnav">
	
		<a href="#home">DreamStream</a>
		<a href="${pageContext.request.contextPath}/activity/listLog">Activity</a>
		<a href="#scalesConfig">Scales</a>
		
		<div class="dropdown">
			<button class="dropbtn">Inventory 
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="${pageContext.request.contextPath}/liquor/listLiquor">View All</a>
				<a href="${pageContext.request.contextPath}/liquor/gotoAddLiquor">Add New</a>
				<a href="${pageContext.request.contextPath}/liquor/inventory">Search</a>
			</div>
		</div>
		
		<a class="topnav-right active" href="${pageContext.request.contextPath}/user/logout">${userToken.username}&nbsp;<i class="fas fa-sign-out-alt"></i></a>
		
		<a href="javascript:void(0);" style="font-size:15px;" class="topnav-icon" onclick="myFunction()">&#9776;</a>
	</div>

	</c:when>
	</c:choose>
	
	<c:choose>
	<c:when test="${userToken.permission == 797979}">
	
	<c:choose>
	<c:when test="${not empty locationToken && userToken.permission == 797979 }">
	<%-- Vendor Location Selected Header --%>
	
	<div class="topnav" id="myTopnav">
		
		<a href="#home">DreamStream</a>
		<a href="#selectLocation">Select Location</a>
		<a href="#manage">Manage</a>
		<a href="#metrics">Metrics</a>
		<a href="#alerts">Alerts</a>
		
		<a class="topnav-right active" href="${pageContext.request.contextPath}/user/logout">${userToken.username}&nbsp;<i class="fas fa-sign-out-alt"></i></a>
		
		<a href="javascript:void(0);" style="font-size:15px;" class="topnav-icon" onclick="myFunction()">&#9776;</a>
	</div>

	</c:when>
	<c:otherwise>
	<%-- Vendor Select Location Header --%>
	
	<div class="topnav" id="myTopnav">

		<a href="#home">DreamStream</a>
		<a href="#selectLocation">Select Location</a>
	
	<a class="topnav-right active" href="${pageContext.request.contextPath}/user/logout">${userToken.username}&nbsp;<i class="fas fa-sign-out-alt"></i></a>

	<a href="javascript:void(0);" style="font-size:15px;" class="topnav-icon" onclick="myFunction()">&#9776;</a>

	</div>
	<%-- End of Vendor Select Location Header --%>
	</c:otherwise>
	</c:choose>
	
	<%-- End of Vendor Header --%>
	</c:when>
	</c:choose>
	
<%-- 	<c:otherwise>
	No User Session Exists
	</c:otherwise> --%>
	
	<%-- End of User Token exists --%>
	</c:when>
</c:choose>
