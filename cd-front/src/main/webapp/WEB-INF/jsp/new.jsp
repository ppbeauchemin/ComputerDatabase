<%@page import="com.excilys.formation.utils.Var"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Computers database</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="./css/main.css">
</head>
<body>
	<header class="topbar">
		<h1 class="fill">
			<a href="computers.htm">Play 2.0 sample application &mdash;
				Computer database </a>
		</h1>
	</header>
	<section id="main">
		<h1>${mode} a computer</h1>
		<form:form modelAttribute="computer" action="computers.htm" method="POST">
			<fieldset>
				<div class="clearfix ${errors.name}">
					<form:label path="name">Computer name</form:label>
					<div class="input">
						<form:input path="name" value="${computer.name}" />
						<span class="help-inline">Required</span>
					</div>
				</div>
				<div class="clearfix ${errors.introduced}">
					<form:label path="introduced">Introduced date</form:label>
					<div class="input">
						<form:input path="introduced" value="${computer.introduced}" />
						<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
					</div>
				</div>
				<div class="clearfix ${errors.discontinued}">
					<form:label path="discontinued">Discontinued date</form:label>
					<div class="input">
						<form:input path="discontinued" value="${computer.discontinued}" />
						<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;)</span>
					</div>
				</div>
				<div class="clearfix">
					<form:label path="company">Company</form:label>
					<div class="input">
						<form:select path="company">
							<form:option cssClass="blank" value="" label="-- Choose a company --" />
							<form:options items="${companies}" itemValue="companyId" itemLabel="name" />
						</form:select>
						<span class="help-inline"></span>
					</div>
				</div>
			</fieldset>
			<div class="actions">
				<input type="submit" value="${mode} this computer"
					class="btn primary"> or <a href="computers.htm" class="btn">Cancel</a>
			</div>
		</form:form>
		<c:set var="UPDATE" value="<%=Var.UPDATE%>" />
		<c:if test="${mode eq UPDATE}">
			<form:form modelAttribute="computer" action="delete.htm?computerId=${computer.computerId}"
				method="POST" cssClass="topRight">
				<input type="submit" value="Delete this computer" class="btn danger">
			</form:form>
		</c:if>
	</section>
</body>
</html>
