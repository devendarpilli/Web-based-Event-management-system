<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Choose Your City</title>
<base href="${pageContext.request.contextPath}">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/main.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
<script type="text/javascript"	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/common/jquery-ui-min.js"></script>

</head>
<body>

	<div class="content" style="margin-top: 200px;">
		<form id="searchbox" class="center" action="<%=request.getContextPath()%>/change/city" method="get">
			<div class="left" style="margin: 50px 20px 10px 100;">
				<span>Your city is : </span>
				<input class="city" type="text" placeholder="Detecting..." disabled="disabled">
				<input name="city" class="city" type="hidden">
				<input type="submit"  name="submit" id="city-submit" value="Continue" disabled="disabled" class="button disabled">
			</div>
			<div class="right new">
				<span>Use different city : </span>
				<input id="city-manual-input" name="city2" type="text" placeholder="Type here.." onkeyup="enableSubmitButton(this.value)">
				<input id="city-manual-submit" name="submit" type="submit" value="Save" disabled="disabled" class="button disabled">
			</div>
		</form>
	</div>
	

	<!-- Footer script includes -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/common/common.js"></script>
	<script type="text/javascript">
		getGeoLocation();
		$("#city-manual-input").autocomplete({
			source: "<%=request.getContextPath()%>/complete",
			minLength: 1,
			select: function( event, ui ) {
				this.value = ui.item.value;
			}
		});
	</script>
</body>
</html>
