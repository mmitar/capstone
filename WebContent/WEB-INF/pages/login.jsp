<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>


     <div class="login-page">
        
      <div class="login-form">
        
      
      <form:form class="form" modelAttribute="user" action="${pageContext.request.contextPath}/user/validateUser" method="POST">
        <h1>Login</h1>
        
        <div class="newline-elements">
	       	<form:errors class="form-error" path="username"/>
	        <div class="inline-elements">
		        <i class="btn-black icon fas fa-user-tag"></i>
		        <form:input path="username" minlength="5" placeholder="username" class="input" required="true" type="text" value="" maxlength="20" />
	        </div>
        </div>
        
        <div class="newline-elements">
	        <form:errors class="form-error" path="password"/>
	        
	        <div class="inline-elements">
		        <i class="btn-black icon fas fa-key"></i>
		        <form:input path="password" minlength="5" placeholder="password" type="password" class="input" required="true" value="" maxlength="20" />
	        </div>
        </div>
        
        <input class="btn-green font-bold mgn-col-10" type="submit" value="Login" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      </form:form>

      <div style="color: red"></div>
      <div style="color: green"></div>

      </div>
      
      <div class="login-features">
        
        <div class="feature-content">
        <h1>Features</h1>
        
        <h3>Lorem Ipsum</h3>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Netus et malesuada fames ac turpis egestas sed tempus urna. Nunc mi ipsum faucibus vitae aliquet nec ullamcorper sit. Nisl rhoncus mattis rhoncus urna neque. Ac placerat vestibulum lectus mauris ultrices eros in cursus.
        
        <h3>Product Fibma</h3>
        Pretium fusce id velit ut tortor pretium. Cras fermentum odio eu feugiat pretium nibh ipsum. Pellentesque id nibh tortor id aliquet lectus proin. Diam sollicitudin tempor id eu nisl nunc mi. Vitae ultricies leo integer malesuada nunc. Tincidunt ornare massa eget egestas purus viverra. Metus dictum at tempor commodo ullamcorper a lacus vestibulum sed. Sit amet facilisis magna etiam tempor. Turpis egestas pretium aenean pharetra magna ac.
        
        
        <h3>Product Fibma</h3>
        Pretium fusce id velit ut tortor pretium. Cras fermentum odio eu feugiat pretium nibh ipsum. Pellentesque id nibh tortor id aliquet lectus proin. Diam sollicitudin tempor id eu nisl nunc mi. Vitae ultricies leo integer malesuada nunc. Tincidunt ornare massa eget egestas purus viverra. Metus dictum at tempor commodo ullamcorper a lacus vestibulum sed. Sit amet facilisis magna etiam tempor. Turpis egestas pretium aenean pharetra magna ac.
        
        
        <h3>Product Fibma</h3>
        Pretium fusce id velit ut tortor pretium. Cras fermentum odio eu feugiat pretium nibh ipsum. Pellentesque id nibh tortor id aliquet lectus proin. Diam sollicitudin tempor id eu nisl nunc mi. Vitae ultricies leo integer malesuada nunc. Tincidunt ornare massa eget egestas purus viverra. Metus dictum at tempor commodo ullamcorper a lacus vestibulum sed. Sit amet facilisis magna etiam tempor. Turpis egestas pretium aenean pharetra magna ac.
        
        
        <h3>Product Fibma</h3>
        Pretium fusce id velit ut tortor pretium. Cras fermentum odio eu feugiat pretium nibh ipsum. Pellentesque id nibh tortor id aliquet lectus proin. Diam sollicitudin tempor id eu nisl nunc mi. Vitae ultricies leo integer malesuada nunc. Tincidunt ornare massa eget egestas purus viverra. Metus dictum at tempor commodo ullamcorper a lacus vestibulum sed. Sit amet facilisis magna etiam tempor. Turpis egestas pretium aenean pharetra magna ac.
        </div>
        
      </div>
        
    </div>