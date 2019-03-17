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
      
      
<c:forEach items="${locations}" var="location" varStatus="status">
	<c:set var="locationFormId" value="location${status.index}"/>
	<c:if test="${not empty location}">
	
		<div class="item mgn-col-10">
    <div class="item-head">
      
      <div class="newline-elements">
        <div class="inline-elements" style="justify-content: space-between">
          <div>
            <i title="The location identificating name" class="item-icon btn-blue fas fa-building"></i>         
            <span style="padding-left: 10px; font-size: 1.3rem">${location.locationId}</span>
          </div>
          <div class="item-menu pad-row-10" style="font-size: 1.5rem">
            &bull;&bull;&bull;
          </div>
        </div>
    </div>

    <div class="item-body">

	<div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        <i title="" class="btn-blue item-icon fa fa-map-marker"></i>
        <span class="pad-10">${location.address}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">address</span>
      </div>
      
      
	<div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        <i title="" class="btn-blue item-icon fa fa-map-marker"></i>
        <span class="pad-10">${location.zipcode}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">zipcode</span>
      </div>
      
      
	<div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        <i title="" class="btn-blue item-icon fa fa-map-marker"></i>
        <span class="pad-10">${location.state}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">state</span>
      </div>
      
      
	<div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        <i title="" class="btn-blue item-icon fa fa-map-marker"></i>
        <span class="pad-10">${location.country}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">country</span>
      </div>
      
      
	<div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        <i title="" class="btn-blue item-icon fas fa-address-card"></i>
        <span class="pad-10">${location.licenseNumber}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">license number</span>
      </div>
      
       
	<div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        <i title="" class="btn-blue item-icon fas fa-address-card"></i>
        <span class="pad-10">${location.licenseDate}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">license date</span>
      </div>
      
      <div class="newline-elements">
      <div class="inline-items mgn-10 bs-bot pad-row-10">
        <i title="" class="btn-blue item-icon fas fa-address-card"></i>
        <span class="pad-10">${location.description}</span>
      </div>
      	<span style="font-size: 0.7em; text-align:center">description</span>
      </div>
      
    </div>
  </div>

</div>
  

	</c:if>
</c:forEach>

</div>
	