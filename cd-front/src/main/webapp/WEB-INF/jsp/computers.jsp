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
		<a href="computers.htm">Play 2.0 sample application &mdash; Computer
			database</a>
	</h1>
	</header>

	<section id="main">

	<h1 id="homeTitle">${nbComputers} computers found</h1>

	<c:if test="${newComputer ne null}">
		<div class="alert-message warning">
			<strong>Done!</strong> Computer ${newComputer} has been ${mode}
		</div>
	</c:if>

	<div id="actions">
		<form action="computers.htm" method="GET">
			<input type="search" id="searchbox" name="f" value=""
				placeholder="Filter by computer name..."> <input
				type="submit" id="searchsubmit" value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="new.htm">Add a new computer</a>
	</div>

	<table class="computers zebra-striped">
		<thead>
			<tr>
				<th class="name header ${params.nameHeader}"><a
					href="computers.htm${params.name}">Computer name</a></th>
				<th class="introduced header ${params.introducedHeader}"><a
					href="computers.htm${params.introduced}">Introduced</a></th>
				<th class="discontinued header ${params.discontinuedHeader}"><a
					href="computers.htm${params.discontinued}">Discontinued</a></th>
				<th class="company_name header ${params.companyNameHeader}"><a
					href="computers.htm${params.companyName}">Company</a></th>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${computers}" var="computer">
				<tr>
					<td><a href="update.htm?computerId=${computer.computerId}">${computer.name}</a></td>
					<td><c:choose>
							<c:when test="${computer.introduced eq null}">
								<em>-</em>
							</c:when>
							<c:otherwise>
								${computer.introduced}
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${computer.discontinued eq null}">
								<em>-</em>
							</c:when>
							<c:otherwise>
								${computer.discontinued}
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${computer.company eq null}">
								<em>-</em>
							</c:when>
							<c:otherwise>
								${computer.company.name}
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

	<div id="pagination" class="pagination">
		<ul>
			<li class="prev ${params.prevDisabled}"><a ${params.prevUrl}>&larr;
					Previous</a></li>
			<li class="current"><a>Displaying ${indexcomputer} to ${maxcomputer} of ${nbComputers}</a></li>
			<li class="next ${params.nextDisabled}"><a ${params.nextUrl}>Next
					&rarr;</a></li>

		</ul>
	</div>

	</section>

</body>
</html>