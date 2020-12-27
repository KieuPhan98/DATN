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

<title>Danh sách đơn hàng</title>

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


    <link href="/resources/css/bootstrap1.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="header">
							<h4 class="title">DANH SÁCH ĐƠN ĐẶT HÀNG</h4>
						</div>
				<div class="row">
					
					<div class="col-md-1">
					</div>
					
					<div class="col-md-10">
						<div class="panel panel-default">
							<div class="panel-body tabs">
								<ul class="nav nav-tabs">
									<li class="active">
										<a href="#taborder1" data-toggle="tab">Chờ xác nhận</a>
									</li>
									<li>
										<a id="orderForPrvider" href="#taborder2" data-toggle="tab">Đang giao</a>
									</li>
									<li>
										<a id="orderForPrvider" href="#taborder3" data-toggle="tab">Đã giao</a>
									</li>
									<li>
										<a id="orderForPrvider" href="#taborder4" data-toggle="tab">Đã hủy</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane fade in active" id="taborder1">
												<table class="table table-striped table-hover">
													<thead>
														<tr style="font-weight: bold;">
															<th class="col-sm-1">Mã đơn hàng</th>
															<th class="col-sm-2">Ngày mua</th>
															<th class="col-sm-2">Tổng tiền</th>
															<th class="col-sm-2">Chi tiết đơn hàng</th>
															<th class="col-sm-1"></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="listordercus" items="${listordercus}">
															<tr>
																<th class="col-sm-1">${listordercus.id}</th>
																<th class="col-sm-2"><f:formatDate value="${listordercus.orderDate}" pattern="dd/MM/yyyy" /></th>
																<th class="col-sm-2">$ <f:formatNumber value="${listordercus.totalPrice}" pattern="#,###.00"/></th>
																<th class="col-sm-2"><a href="/detailOrder?id=${listordercus.id}" style="color: blue">Xem CTDH</a></th>
																<th class="col-sm-1"><a onclick="if (!(confirm('Bạn chắc chắn muốn HỦY đơn hàng này ?'))) return false" 
																						href="/cancelOrder?id=${listordercus.id}" style="color: red">Hủy Đơn</a></th>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
									<div class="tab-pane fade" id="taborder2">
										<table class="table table-striped table-hover">
											<thead>
												<tr style="font-weight: bold;">
													<th class="col-sm-1">Mã đơn hàng</th>
													<th class="col-sm-2">Ngày mua</th>
													<th class="col-sm-2">Tổng tiền</th>
													<th class="col-sm-2">Chi tiết đơn hàng</th>
												</tr>
											</thead>
													<tbody>
														<c:forEach var="listordercus" items="${listordercus1}">
															<tr>
																<th class="col-sm-1">${listordercus.id}</th>
																<th class="col-sm-2"><f:formatDate
																		value="${listordercus.orderDate}" pattern="dd/MM/yyyy" /></th>
																<th class="col-sm-2">$ <f:formatNumber
																		value="${listordercus.totalPrice}" pattern="#,###.00" /></th>
																<th class="col-sm-2"><a
																	href="/detailOrder?id=${listordercus.id}"
																	style="color: blue">Xem CTDH</a></th>
															</tr>
														</c:forEach>
													</tbody>
												</table>
									</div>
									
									<div class="tab-pane fade" id="taborder3">
										<table class="table table-striped table-hover">
											<thead>
												<tr style="font-weight: bold;">
													<tr style="font-weight: bold;">
													<th class="col-sm-1">Mã đơn hàng</th>
													<th class="col-sm-2">Ngày mua</th>
													<th class="col-sm-2">Tổng tiền</th>
													<th class="col-sm-2">Chi tiết đơn hàng</th>
												</tr>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="listordercus" items="${listordercus2}">
													<tr>
														<th class="col-sm-1">${listordercus.id}</th>
														<th class="col-sm-2"><f:formatDate
																value="${listordercus.orderDate}" pattern="dd/MM/yyyy" /></th>
														<th class="col-sm-2">$ <f:formatNumber
																value="${listordercus.totalPrice}" pattern="#,###.00" /></th>
														<th class="col-sm-2"><a
															href="/detailOrder?id=${listordercus.id}"
															style="color: blue">Xem CTDH</a></th>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									
									<div class="tab-pane fade" id="taborder4">
										<table class="table table-striped table-hover">
											<thead>
												<tr style="font-weight: bold;">
													<tr style="font-weight: bold;">
													<th class="col-sm-1">Mã đơn hàng</th>
													<th class="col-sm-2">Ngày mua</th>
													<th class="col-sm-2">Tổng tiền</th>
													<th class="col-sm-2">Chi tiết đơn hàng</th>
												</tr>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="listordercus" items="${listordercus3}">
													<tr>
														<th class="col-sm-1">${listordercus.id}</th>
														<th class="col-sm-2"><f:formatDate
																value="${listordercus.orderDate}" pattern="dd/MM/yyyy" /></th>
														<th class="col-sm-2">$ <f:formatNumber
																value="${listordercus.totalPrice}" pattern="#,###.00" /></th>
														<th class="col-sm-2"><a
															href="/detailOrder?id=${listordercus.id}"
															style="color: blue">Xem CTDH</a></th>
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

</body>
</html>
