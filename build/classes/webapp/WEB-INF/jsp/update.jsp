<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<a href="computers"> Play 2.0 sample application &mdash; Computer
			database </a>
	</h1>
	</header>
	<section id="main">
	<h1>Edit computer</h1>
	<form action="computers" method="POST">
		<fieldset>
			<div class="clearfix ${errors.name}">
				<label for="name">Computer name</label>
				<div class="input">
					<input type="text" id="name" name="name" value="${computer.name}"> <span
						class="help-inline">Required</span>
				</div>
			</div>
			<div class="clearfix ${errors.introduced}">
				<label for="introduced">Introduced date</label>
				<div class="input">
					<input type="text" id="introduced" name="introduced" value="${computer.introduced}">
					<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;) or
						DateTime (&#x27;yyyy-MM-dd hh:mm:ss&#x27;)</span>
				</div>
			</div>
			<div class="clearfix ${errors.discontinued}">
				<label for="discontinued">Discontinued date</label>
				<div class="input">
					<input type="text" id="discontinued" name="discontinued" value="${computer.discontinued}">
					<span class="help-inline">Date (&#x27;yyyy-MM-dd&#x27;) or
						DateTime (&#x27;yyyy-MM-dd hh:mm:ss&#x27;)</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company_id">Company</label>
				<div class="input">
					<select id="company_id" name="company.id">
						<option class="blank" value="">-- Choose a company --</option>
						<c:forEach items="${companies}" var="company">
							<c:choose>
								<c:when test="${company.companyId eq computer.company.companyId}">
									<option value="${company.companyId}" selected="selected">${company.name}</option>
								</c:when>
								<c:otherwise>
								<option value="${company.companyId}">${company.name}</option>
								</c:otherwise>
							
							</c:choose>
							
						</c:forEach>
					</select> <span class="help-inline"></span>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Save this computer" class="btn primary">
			or <a href="computers" class="btn">Cancel</a>
		</div>
	</form>
	<form action="delete?computerId=${computer.computerId}" method="POST" class="topRight">
		<input type="submit" value="Delete this computer" class="btn danger">
	</form>
	</section>
</body>
</html>