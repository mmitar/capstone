<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<div class="newline-elements pad-20">

	<div class="form">
   	  <div class="inline-elements">
        <i class="icon btn-blue fas fa-search"></i>
<!--         <input id="filter" type="text" placeholder="filter inventory" oninput="javascript:filterInput(this)"/>
 -->        <input id="filter" type="text" placeholder="filter inventory"/>
      </div>
     </div>
      
      
<c:forEach items="${liquors}" var="liquor" varStatus="status">
	<c:set var="liquorFormId" value="liquor${status.index}"/>
	<c:if test="${not empty liquor}">
	
		<div class="item mgn-col-10">
    <div class="item-head">
      
      <div class="newline-elements">
        <div class="inline-elements" style="justify-content: space-between">
          <div>
            <i title="The brand name of the liquor" class="item-icon btn-blue fas fa-beer"></i>         
            <span style="padding-left: 10px; font-size: 1.3rem">${liquor.brandName}</span>
          </div>
          <div class="item-menu pad-row-10" style="font-size: 1.5rem">
            &bull;&bull;&bull;
          </div>
          
        </div>
        <div class="inline-elements" style="justify-content: space-between">
        <div class="item-attribute"> <i title="A brief description of the alcohol" class="item-icon btn-black fas fa-wine-bottle"></i>
          <span class="pad-10">${liquor.alcoholType}</span>
        </div>
        <div class="item-menu"  style="z-index: 2">
                          <a class="item--menu--option btn-black btn" href="${pageContext.request.contextPath}/liquor/gotoEditLiquor/${liquor.liquorCode}">Edit</a>

          </div>
      </div>
    </div>

    <div class="item-body">

	<div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        <i title="The liquid volume of your liquor" class="btn-blue item-icon fas fa-weight"></i>
        <span class="pad-10">${liquor.liquidVolume}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">liquid volume</span>
      </div>
      
      <div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        		<i title="The current in-stock quantity" class="btn-blue item-icon fas fa-cubes"></i>
        <span class="pad-10">${liquor.overflow}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">overflow</span>
      </div>
      
      <div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        		<i title="The overflow level to be alerted at if reached" class="btn-blue item-icon fas fa-exclamation-triangle"></i>

        <span class="pad-10">${liquor.alertLevel}</span>
      </div>
      	<span style="font-size:0.7em; text-align:center">alert level</span>
      </div>
    </div>
  </div>

</div>
  

	</c:if>
</c:forEach>

</div>
	