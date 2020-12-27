<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" type="image/png" href="/resources/assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Chi tiết đơn hàng</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>

    <!-- Bootstrap core CSS     -->
    <link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Animation library for notifications   -->
    <link href="/resources/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="/resources/assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="/resources/assets/css/demo.css" rel="stylesheet"/>

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="/resources/assets/css/pe-icon-7-stroke.css" rel="stylesheet"/>
    
     <link href="/resources/css/bootstrap1.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="content">
    <div class="container-fluid">
        <div class="row">
        	<div class="col-md-1">
			</div>
					
            <div class="col-md-10">
                <div class="card">
                    <div class="header">
                        <h3 class="title" style = "text-align: center; font-size: 30px; color: blue">Chi Tiết Đơn Hàng</h3>
                        <h4 class="title" style = "margin-bottom: 10px">ĐỊA CHỈ NHẬN HÀNG</h4>
                        <h4 class="title">Tên    : ${nameReceiver}</h4>
                        <h4 class="title">SĐT    : ${phoneReceiver}</h4>
                        <h4 class="title">Địa chỉ: ${addressReceiver}</h4>
                    </div>
                    <div class="content table-responsive table-full-width">
                        <table class="table table-hover table-striped">
                            <thead>

	                            <th>Hình ảnh</th>
	                            <th>Tên Sản Phẩm</th>
	                            <th>Số lượng</th>
	                            <th>Đơn giá</th>
	                            <th>Thành tiền</th>

                            </thead>
                            <tbody>
                            <c:forEach items="${orderDetails}" var="orderDetails">
                                <tr>
                                    <%-- <td>${orderDetails.order.id}</td> --%>
                                    <td class="invert-image"><a href="single.html"><img
                                		src="/resources/images/${orderDetails.product.image}" alt=" " class="img-responsive" style="width: 150px; height: 150px;"/></a>
                            		</td> 
                                    <td>${orderDetails.product.name}</td>
                                    <td>${orderDetails.quantity}</td>
                                    <td>$ <f:formatNumber value="${orderDetails.product.unitPrice}" pattern="#,###.00"/></td>
                                    <td>$ <f:formatNumber value="${orderDetails.quantity * orderDetails.product.unitPrice}" pattern="#,###.00"/></td> 
                                    <%-- <td><a href="/admin/editorder?id=${orderDetails.id}"> Edit</a></td> --%>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--   Core JS Files   -->
<script src="/resources/assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="/resources/assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Charts Plugin -->
<script src="/resources/assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="/resources/assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="/resources/assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="/resources/assets/js/demo.js"></script>

</body>
</html>
