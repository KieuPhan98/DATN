</html>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" type="image/png" href="/resources/assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Report Doanh Thu</title>

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
    
    <style>
		input[type=date] {
			/* display: block; */
			padding: 0 15px;
			color: #373d54;
			width: 250px;
			background: #f8fafc;
			font-size: 14px;
			height: 40px;
			border: 1px solid #e0e4f6;
			transition: all 0.2s;
		}
		[type="date"] {
		  background:#f8fafc url(https://cdn1.iconfinder.com/data/icons/cc_mono_icon_set/blacks/16x16/calendar_2.png)  97% 50% no-repeat !important ;
		  
		}
		[type="date"]::-webkit-inner-spin-button {
		  display: none;
		}
		[type="date"]::-webkit-calendar-picker-indicator {
		  opacity: 0;
		}
	</style>
	
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="content">
    <div class="container-fluid">
        <div class="row">
        	<div class="col-sm-12 ">
        		<h3 class="title" style="margin-left: 470px; margin-bottom: 20px">THỐNG KÊ DOANH THU</h3>
				<form action="/admin/reportDoanhThuTest" method="post">
					<label style="margin-left: 270px;">Từ ngày : </label>
					<input type = "date" id="fromdate" name="fromdate" value="${datefrom }" required="required">  	
					
					<label>Đến ngày : </label>
					<input type = "date" id="todate" name="todate" value="${dateto }" required="required">
					
					<button type="submit" class="btn btn-primary"> OK </button>
				</form>		
				<form action="admin/exportExcelDoanhThu" method="post">
					<input type = "date" id="fromdate" name="fromdate" value="${datefrom }" style="display: none"> 
					<input type = "date" id="todate" name="todate" value="${dateto }" style="display: none">
							
					<c:if test = "${datefrom != null }">
						<button type="submit" class="btn btn-primary btnExport" style="float: right; margin-right: 60px; margin-top: -20px;" > Xuất file excel </button>
						<h5 style="font-weight: bold;">Tổng Doanh Thu: $ <f:formatNumber value="${tongDT}" pattern="#,###.00" /></h5>
					</c:if>
				</form>
			</div>
        	<br>
        	<c:if test = "${datefrom != null }">
        		<div class="col-md-12">
                <div class="card">
                    <div class="content table-responsive table-full-width">
                       <table class="table table-hover table-striped">
                            <thead>
	                            <th>Hình Ảnh</th>
	                            <th>Mã Sản Phẩm</th>
	                            <th>Tên Sản Phẩm</th>
	                            <th>Tổng Số Lượng Bán</th>      
	                            <th>Doanh Thu</th>       
                            </thead>
                            <tbody>
                            <c:forEach items="${listDoanhThu}" var="report">
                                <tr>
                                	<td class="invert-image">
                                    	<a href="single.html">
                                    		<img src="/resources/images/${report[0]}" alt=" " class="img-responsive" style="width: 150px; height: 150px;"/>
                                		</a>
                            		</td>
                                    <td>${report[1]}</td>                                   
                                    <td>${report[2]}</td>
                                    <td>${report[3]}</td>
                                    <td>$ <f:formatNumber value="${report[4]}" pattern="#,###.00" /></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table> 
                    </div>
                </div>
            </div>
        	</c:if>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
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
