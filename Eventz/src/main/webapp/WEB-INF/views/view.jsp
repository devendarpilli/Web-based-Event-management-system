<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>View Page</title>
<base href="${pageContext.request.contextPath}">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/main.css" />
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
					<input id="search" name="q" type="text" placeholder="Type here">
					<input	id="submit1" type="submit" value="Search" class="button">
				</div>
			</form>
			<div id="results" class="event-display">
				<div class="title">
					<h2>${event.title}</h2>
					<span>${event.description}</span>
					<div id="fb-root"></div>
					<div class="fb-like" data-send="true" data-width="450" data-show-faces="true"></div>
				</div>
				<div class="venue">
					<h3>Venue :</h3>
					<span>${event.venue_name}</span>
					<span>${event.address}</span>
					<br/>
					<span>${event.city}</span>
					<span>${event.country}</span>
					<br/><br/>
					<div id="map"></div>
				</div>
				<div class="reviews">
					<div class="post">
						<form id="searchbox" action="<%=request.getContextPath()%>/view/${event.id}"	method="post">
							<textarea name="review_text" required placeholder="Type your comment here.."></textarea>
							<br><input type="submit" name="submit" value="Post" class="button">
						</form>
					</div>
					<div class="user-reviews">
						<h3>User Reviews</h3>
						<div class="reviewcontent">
							<c:forEach items="${reviews}" var="review">
								<div class="review">
									<span class="title">Submitted by : ${review.userName}</span><br/>
									<span>${review.reviewText}</span>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Footer script includes -->
	
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/common/common.js"></script>
	<script type="text/javascript">
		var event = ${event};
		mapPosition({
			latitude : event.latitude,
			longitude : event.longitude
		});
	</script>
</body>
</html>
