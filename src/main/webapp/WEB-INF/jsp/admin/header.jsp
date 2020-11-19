<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    
    <style type="text/css">
    	.sidebar .sidebar-wrapper ul li ul .submenu{
    		list-style: none; visibility: hidden; top: 5em; opacity: 0; position: absolute;
    	}
    </style>
    
</head>
<body>
<div class="wrapper">
    <div class="sidebar" data-color="purple" data-image="/resources/assets/img/sidebar-5.jpg">

        <!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->

        <div class="sidebar-wrapper">
            <div class="logo">
                <a  class="simple-text">Yumi Shop</a>
            </div>

            <ul class="nav">
                <li>
                    <a href="/admin/dashboard">
                        <i class="pe-7s-graph3"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                
                <li class = "menu1">
                	<a href="#">
                        <i class="pe-7s-plus"></i>
                        <p>QUẢN LÝ DANH MỤC</p>
                    </a>
                    
                    <ul class = "submenu">
                    	<!-- style="list-style: none; visibility: hidden; top: 5em; opacity: 0; position: absolute;" -->
                    	<li>
		                    <a href="/admin/listSupplier">
		                        <p>NHÀ CUNG CẤP</p>
		                    </a>
                		</li>
                
		                <li>
		                    <a href="/admin/listCategory">
		                        <p>LOẠI SẢN PHẨM</p>
		                    </a>
		                </li>
                
		                <li>
		                    <a href="/admin/listproduct">
		                        <p>SẢN PHẨM</p>
		                    </a>
		                </li>
		                
                    </ul>
                </li>
                
                <!-- <li>
                    <a href="/admin/listSupplier">
                        <i class="pe-7s-plus"></i>
                        <p>NHÀ CUNG CẤP</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/listCategory">
                        <i class="pe-7s-news-paper"></i>
                        <p>LOẠI SẢN PHẨM</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/listproduct">
                        <i class="pe-7s-news-paper"></i>
                        <p>SẢN PHẨM</p>
                    </a>
                </li> -->
                
                <li>
                    <a href="#">
                        <i class="pe-7s-diskette"></i>
                        <p>QUẢN LÝ ĐƠN HÀNG</p>
                    </a>
                    
                    <ul>
                    	<li>
                    	<a href="/admin/orderNew">
                        	<p>MỚI</p>
                    	</a>
                		</li>
                
		                <li>
		                    <a href="/admin/orderShipping">
		                        <p>ĐANG GIAO</p>
		                    </a>
		                </li>
		                
		                 <li>
		                    <a href="/admin/orderFinish">
		                        <p>HOÀN TẤT</p>
		                    </a>
		                </li>
		                
		                <li>
		                    <a href="/admin/orderCancel">
		                        <p>ĐÃ HỦY</p>
		                    </a>
		                </li>
		                
                    </ul>
                </li>
                
                <!-- <li>
                    <a href="/admin/orderNew">
                        <i class="pe-7s-diskette"></i>
                        <p>ĐƠN HÀNG MỚI</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/orderShipping">
                        <i class="pe-7s-paper-plane"></i>
                        <p>ĐƠN HÀNG ĐANG GIAO</p>
                    </a>
                </li>
                
                 <li>
                    <a href="/admin/orderFinish">
                        <i class="pe-7s-check"></i>
                        <p>ĐƠN HÀNG HOÀN TẤT</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/orderCancel">
                        <i class="pe-7s-junk"></i>
                        <p>ĐƠN HÀNG ĐÃ HỦY</p>
                    </a>
                </li> -->
                
                 <li>
                    <a href="/admin/createOrderForSupplier">
                        <i class="pe-7s-note"></i>
                        <p>PHIẾU ĐẶT HÀNG</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/importOrderFromSupplier">
                        <i class="pe-7s-note"></i>
                        <p>PHIẾU NHẬP HÀNG</p>
                    </a>
                </li>
                
                <li>
                    <a href="#">
                        <i class="pe-7s-pin"></i>
                        <p>BÁO CÁO THỐNG KÊ</p>
                    </a>
                    
                    <ul>
                    	<li>
		                    <a href="/admin/reportmonth">
		                        <p>DOANH THU</p>
		                    </a>
                		</li>
                		
                		<li>
		                    <a href="#h">
		                        <p>LỢI NHUẬN</p>
		                    </a>
                		</li>
                		
                		<li>
		                    <a href="#">
		                        <p>TỒN KHO</p>
		                    </a>
                		</li>
                    </ul>
                </li>
                
            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                 </div>
                <div class="collapse navbar-collapse">
                 
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="">
                                <p>Account</p>
                            </a>
                        </li>
                       
                        <li>
                            <a href="/logout">
                                <p>Log out</p>
                            </a>
                        </li>
                        <li class="separator hidden-lg hidden-md"></li>
                    </ul>
                </div>
            </div>
        </nav>
</body>
</html>
