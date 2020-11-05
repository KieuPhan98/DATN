<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/png"
	href="/resources/assets/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Light Bootstrap Dashboard by Creative Tim</title>

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
							<h4 class="title">ĐƠN ĐẶT HÀNG ĐẾN NHÀ CUNG CẤP</h4>
							<button class="btn btn-info btn-fill pull-left" style="margin-top: 10px; margin-bottom: 10px">
								<a href="/admin/addOrderForSupplier" style="color: white" >+ Tạo Phiếu Đặt</a>
							</button>
						</div>
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-body tabs">
								<ul class="nav nav-tabs">
									<li class="active">
										<a href="#taborder1" data-toggle="tab">Đơn chưa nhập hàng</a>
									</li>
									<li>
										<a id="orderForPrvider" href="#taborder2" data-toggle="tab">Đơn đã nhập hàng</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane fade in active" id="taborder1">
										<h4>Đơn chưa nhập hàng</h4>
												<table class="table table-striped table-hover">
													<thead>
														<tr style="font-weight: bold;">
															<th class="col-sm-1">Mã phiếu đặt</th>
															<th class="col-sm-2">Nhà cung cấp</th>
															<th class="col-sm-2">Nhân viên lập</th>
															<th class="col-sm-2">Ngày tạo</th>
															<th class="col-sm-1"></th>
															<th class="col-sm-1"></th>
															<!-- <th class="col-sm-1"></th> -->
														</tr>
													</thead>
													<tbody>
														<c:forEach var="item" items="${listOrder}">
															<tr>
																<th class="col-sm-1">${item.id }</th>
																<th class="col-sm-2">${item.supplier.name }</th>
																<th class="col-sm-2">${item.employee1.FirstName}</th>
																<th class="col-sm-2">${item.createDate }</th>
																<!-- <th class="col-sm-1">
																	<a class="btn btn-primary" href="admin/addOrderForSupplierDetail">Thêm chi tiết</a>
																</th> -->
																<th class="col-sm-1">
																	<a class="btn btn-primary" href="/admin/detailOrderForSupplier?id=${item.id }">Xem chi tiết</a>
																</th>
																<th class="col-sm-1">
																	<a class="btn btn-primary btn-export">Xuất file Excel</a>
																</th>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
									<div class="tab-pane fade" id="taborder2">
										<h4>Đơn đã nhập hàng</h4>
										<table class="table table-striped table-hover">
											<thead>
												<tr style="font-weight: bold;">
													<th class="col-sm-1">Mã phiếu đặt</th>
													<th class="col-sm-3">Nhà cung cấp</th>
													<th class="col-sm-3">Nhân viên lập</th>
													<th class="col-sm-3">Ngày tạo</th>
													<th class="col-sm-3"></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${listOrder1}">
													<tr>
														<th class="col-sm-3">${item.id }</th>
														<th class="col-sm-3">${item.supplier.name }</th>
														<th class="col-sm-2">${item.employee1.FirstName }</th>
														<th class="col-sm-2">${item.createDate }</th>
														<th class="col-sm-2"><a class="btn btn-primary" href="/admin/detailOrderForSupplier?id=${item.id }">Xem chi tiết</a></th>
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
					</div>
				</div>
			</div>
		</div>
	</div>

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
