<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ping Shop</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Super Market Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <!-- //for-mobile-apps -->
    <link href="/resources/css/bootstrap1.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- font-awesome icons -->
    <link href="/resources/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js -->
    <script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>
    <!-- //js -->
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="/resources/js/move-top.js"></script>
    <script type="text/javascript" src="/resources/js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <!-- start-smoth-scrolling -->
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="index.html"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
            <li class="active">Check Out</li>
        </ol>
    </div>
</div>
<!-- //breadcrumbs -->
<!-- register -->
<div class="register">
    <div class="container">
        <h2><spring:message code="checkout1" text="default text"/></h2>
        <div class="login-form-grids">
            <h5><spring:message code="PROFILE" text="default text"/></h5>
           <form:form action="checkout" method="post" modelAttribute="orderModel"> 
                <form:input path="receiver" placeholder="Tên Người nhận"  required="true" value="${fullName}"></form:input>
                <form:input path="address" placeholder="Địa Chỉ..." required="true" value="${address}"></form:input>
                
                <%-- <form:select itemValue="district" path="district" class="form-control">	
					<form:option value="Quận 1">Quận 1</form:option>
					<form:option value="Quận 2">Quận 2</form:option>
					<form:option value="Quận 3">Quận 3</form:option>
					<form:option value="Quận 4">Quận 4</form:option>
					<form:option value="Quận 5">Quận 5</form:option>
					<form:option value="Quận 6">Quận 6</form:option>
					<form:option value="Quận 7">Quận 7</form:option>
					<form:option value="Quận 8">Quận 8</form:option>
					<form:option value="Quận 9">Quận 9</form:option>
					<form:option value="Quận 10">Quận 10</form:option>
					<form:option value="Quận 11">Quận 11</form:option>
					<form:option value="Quận 12">Quận 12</form:option>
					<form:option value="Quận Thủ Đức">Quận Thủ Đức </form:option>
					<form:option value="Quận Tân Phú">Quận Tân Phú</form:option>
					<form:option value="Quận Gò Vấp">Quận Gò Vấp</form:option>
					<form:option value="Quận Tân Bình">Quận Tân Bình</form:option>
					<form:option value="Quận Bình Thạnh">Quận Bình Thạnh</form:option>
					<form:option value="Quận Bình Tân">Quận Bình Tân</form:option>
					<form:option value="Quận Phú Nhuận">Quận Phú Nhuận</form:option>
				</form:select> --%>
				
				<form:select 
					items="${districtList}" itemLabel="name" 
					itemValue="name" path="district" class="form-control">
                </form:select>
				
                <form:input path="phone" placeholder="Số diện thoại..." required="true" value="${phone}"></form:input>
               <form:input path="description" placeholder="Mô Tả" ></form:input>
                <input type="submit" value = "Thanh toán">
            </form:form> 
               
        </div>
        <div class="register-home">
            <a href="/">Trang chủ</a>
        </div>
    </div>
</div>
<!-- //register -->

<jsp:include page="footer.jsp"></jsp:include>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/js/bootstrap.min.js"></script>

<!-- top-header and slider -->
<!-- here stars scrolling icon -->
<script type="text/javascript">
    $(document).ready(function () {
        /*
            var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
            };
        */

        $().UItoTop({easingType: 'easeOutQuart'});

    });
</script>
<!-- //here ends scrolling icon -->
<script src="/resources/js/minicart.min.js"></script>
<script>
    // Mini Cart
    paypal.minicart.render({
        action: '#'
    });

    if (~window.location.search.indexOf('reset=true')) {
        paypal.minicart.reset();
    }
</script>
<!-- main slider-banner -->
<script src="/resources/js/skdslider.min.js"></script>
<link href="/resources/css/skdslider.css" rel="stylesheet">
<script type="text/javascript">
    jQuery(document).ready(function () {
        jQuery('#demo1').skdslider({
            'delay': 5000,
            'animationSpeed': 2000,
            'showNextPrev': true,
            'showPlayButton': true,
            'autoSlide': true,
            'animationType': 'fading'
        });

        jQuery('#responsive').change(function () {
            $('#responsive_wrapper').width(jQuery(this).val());
        });

    });
</script>
<!-- //main slider-banner -->
</body>
</html>
