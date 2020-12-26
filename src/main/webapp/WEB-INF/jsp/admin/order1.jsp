<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/png"
	href="/resources/assets/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Đơn hàng mới</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />


<!-- Bootstrap core CSS     -->
<link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="/resources/assets/css/animate.min.css" rel="stylesheet" />

<!--  Light Bootstrap Table core CSS    -->
<link href="/resources/assets/css/light-bootstrap-dashboard.css?v=1.4.0"
	rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="/resources/assets/css/demo.css" rel="stylesheet" />


<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300'
	rel='stylesheet' type='text/css'>
<link href="/resources/assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="header">
							<h4 class="title">ĐƠN HÀNG MỚI</h4>
							<%--<p class="category">Here is a subtitle for this table</p>--%>
						</div>
						<div class="content table-responsive table-full-width">
							<table class="table table-hover table-striped">
								<thead>
									<th>Số đơn hàng</th>
									<th>Khách hàng</th>
									<th>Ngày đặt</th>
									<th>Người nhận</th>
									<th>Số điện thoại</th>
									<th>Địa chỉ</th>
									<th>Tổng tiền</th>
								</thead>
								<tbody>
									<c:forEach items="${orders}" var="orders">
										<tr>
											<td>${orders.id}</td>	
											<td>${orders.customer.fullname}</td>
											<%-- <td>${orders.orderDate}</td> --%>
											<td><f:formatDate value="${orders.orderDate}" pattern="dd/MM/yyyy" /></td>
											<td>${orders.receiver}</td>
											<td>${orders.phone}</td>
											<td>${orders.address} - ${orders.district}</td>
											<td>$ <f:formatNumber value="${orders.totalPrice}" pattern="#,###.00" /></td>
											<td>
												<button class="btn btn-info btn-fill pull-right">
													<a href="/admin/updateStatusToShipping?id=${orders.id}"
														style="color: white">DUYỆT</a>
												</button>
											</td>
											
											<td>
												<button class="btn btn-info btn-fill pull-right">
													<a href="/admin/detailOrder?id=${orders.id}"
														style="color: white">CTDH</a>
												</button>
											</td>
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

	<jsp:include page="footer.jsp"></jsp:include>
	<!--   Core JS Files   -->
	<script src="/resources/assets/js/jquery.3.2.1.min.js"
		type="text/javascript"></script>
	<script src="/resources/assets/js/bootstrap.min.js"
		type="text/javascript"></script>

	<!--  Charts Plugin -->
	<script src="/resources/assets/js/chartist.min.js"></script>

	<!--  Notifications Plugin    -->
	<script src="/resources/assets/js/bootstrap-notify.js"></script>

	<!--  Google Maps Plugin    -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

	<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="/resources/assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="/resources/assets/js/demo.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {

			demo.initChartist();

			$.notify({
				icon : 'pe-7s-gift',
				message : "Chào Mừng Bạn đến với trang Admin"

			}, {
				type : 'info',
				timer : 4000
			});

		});
	</script>

</body>
</html>
