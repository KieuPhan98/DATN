<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="icon" type="image/png" href="/resources/assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Đơn Hàng Mới</title>

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
    <script src=/resources/assets/js/jquery.validate.min.js"></script>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <!-- <div class="header">
                        <h4 class="title">Update Order</h4>
                    </div> -->
                    <div class="content">
                        <form:form action="/admin/updateStatusToShipping" method="post" modelAttribute="order1" >
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Mã đơn</label>
                                        <form:input path="id" class="form-control" name="id"  readonly="true"></form:input>
                                    </div>
                                </div>
                   
                                 <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Khách hàng</label>
                                        <form:input path="customer.id" class="form-control" readonly="true" ></form:input>
                                       
                                    </div>                                                                    
                                    
                                </div>
                                
                                 <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Số điện thoại</label>
                                        <form:input type="text" path="phone" class="form-control" required="true" readonly="true"></form:input>

                                    </div>
                                </div>
                               
                            </div>

                            <div class="row">
                               <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Ngày đặt</label>                                       
                                        <form:input path="orderDate" class="form-control" readonly="true"></form:input>
                                    </div>
                                </div> 
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Người nhận</label>
                                        <form:input type="text" path="receiver" class="form-control"></form:input>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Địa chỉ</label>                                         
                                        <form:input type="text" path="address" class="form-control" required="true" ></form:input>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Quận</label>                                         
                                       <%--  <form:input type="text" path="district" class="form-control" required="true" ></form:input> --%>
											<form:select itemValue="district" path="district"
												class="form-control">
												<form:option value="${districts}">${districts}</form:option>
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
											</form:select>
										</div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Mô tả</label>                                         
                                        <form:input type="text" path="description" class="form-control" ></form:input>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group" style="display:none">
                                        <label>bill_Id</label>                                         
                                        <form:input type="text" path="bill_Id" class="form-control" required="true"></form:input>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Người duyệt đơn hàng</label>                                         
                                        <form:input type="text" path="employeeId" class="form-control" value = "${FullName}" readonly="true"></form:input>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Người giao hàng</label>                                         
                                        <form:select items="${listShipper}" itemLabel="fullname" itemValue="id" 
                                        			path="shipperId" class="form-control"></form:select>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="row">
                               
                                <div class="col-md-6">

                                    <div class="form-group">
                                        <label>Tổng tiền</label>
                                        <form:input type="text" path="totalPrice" class="form-control" required="true"  readonly="true"></form:input>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">
									<div class="form-group">
										<label>Trạng thái</label>
										<form:select itemValue="status" path="status" class="form-control">												
											<form:option value="Dang giao">DUYỆT</form:option>
											<form:option value="Da huy">HỦY</form:option>
										</form:select>
									</div>
								</div>
                            </div>
                            <button type="submit" class="btn btn-info btn-fill pull-right">Update Profile</button>
                            <div class="clearfix"></div>
                        </form:form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</div>
</div>

</body>

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

<script type="text/javascript">
    $(document).ready(function () {

        demo.initChartist();

        $.notify({
            icon: 'pe-7s-gift',
            message: "Chào Mừng Bạn <b>Đến Với Trang Thống Kê</b> Chúc cửa hàng bạn luôn thành công."

        }, {
            type: 'info',
            timer: 4000
        });

    });
</script>

</html>
