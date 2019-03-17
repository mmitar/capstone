<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<!-- HTML tags itemscope itemtype="http://localhost:8080/DreamStream/" -->
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0"/>
	<!-- 
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.0/css/responsive.dataTables.css">
	 -->
	 
	<!-- Resolves favicon.ico -->
	<link rel="icon" href="data:;base64,iVBORw0KGgo="/>
	
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
	 
	<!-- && INTERAL RESOURCES -->
	
	<link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/resources/css/header.css">
	<link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/resources/css/layouts.css">
	<link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
	<link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/resources/css/loginview.css">
	<link rel="stylesheet" media="all" type="text/css" href="${pageContext.request.contextPath}/resources/css/logbox.css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/navigation.js"></script>
	
	<!-- ## INTERNAL RESOURCES -->
	
	<!-- && EXTERNAL SCRIPTS -->
		
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
	<script type="text/javascript"  src=" https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	
	<title>Dream Stream</title>
	
</head>

<body>

<c:choose>
	<c:when test="${not empty userToken}">
	
	<div class="container">
	
	<!-- Header **FOR VALIDATED USERS** -->
		<div class="header">
			<tiles:insertAttribute name="header" />
		</div>
		
	</c:when>
	<c:otherwise>
		<div class="container-login"> 

	</c:otherwise>
	</c:choose>
		
		<!-- Body Page -->
		<div class="body">
			<tiles:insertAttribute name="body" />
		</div>
	
		<!-- Footer Page **EXCLUDED FOR NOW** -->
	<%-- 	<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div> --%>
		
	</div>
</body>

</html>