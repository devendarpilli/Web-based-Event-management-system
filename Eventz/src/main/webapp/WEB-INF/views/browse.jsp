<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Search Page</title>
<base href="${pageContext.request.contextPath}">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/simplePagination.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/zebra_datepicker.css" />
<script type="text/javascript"	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</head>
<body>

	<jsp:include page="common/header.jsp"></jsp:include>

	<div class="content">
		<div class="browse">
			<h3>Categories:</h3>
			<ul class="categories">
				<c:forEach items="${categories}" var="category">
					<li><a href="<%=request.getContextPath()%>/browse/${category.id}"> ${category.name} </a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="right">
			<form id="searchbox" action="<%=request.getContextPath()%>/search"
				method="get">
				<input type="hidden" name="page" id="search-page">
				<div class="search_box">
					<input id="search" name="q" type="text" placeholder="Type here"
						value="${results.searchRequest.searchTerm}"> <input
						id="submit1" type="submit" value="Search" class="button">
				</div>
			</form>
			<div id="results"></div>
			<div id="pagination"></div>
		</div>
	</div>
	


	<!-- Footer script includes -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/common/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/common/results.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/common/jquery.simplePagination.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/common/zebra_datepicker.js"></script>
	
	<script type="text/javascript">
		var results = ${results.results};
		$(function() {
			$("#pagination").pagination({
		        items: ${results.totalItemsCount},
		        itemsOnPage: ${results.pageSize},
		        cssStyle: 'light-theme',
		        currentPage: ${results.page},
		        onClick: function(index, item) {
		        	document.location = "<%=request.getContextPath()%>/browse/${results.searchRequest.category}";
		        	return false;
		        },
		    });
			$("#results").drawResults({data : results});
			$('#filter-date').Zebra_DatePicker({
			    direction: [1, 100]
			});
		});
	</script>
</body>
</html>
