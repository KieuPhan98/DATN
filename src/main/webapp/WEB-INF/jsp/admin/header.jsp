<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="wrapper">
    <div class="sidebar" data-color="purple" data-image="/resources/assets/img/sidebar-5.jpg">

        <!--   you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple" -->


        <div class="sidebar-wrapper">
            <div class="logo">
                <a  class="simple-text">
                    Yumi Shop
                </a>
            </div>

            <ul class="nav">
                <li>
                    <a href="/admin/dashboard">
                        <i class="pe-7s-graph3"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                
                <li class="active">
                    <a href="/admin/addcategory">
                        <i class="pe-7s-plus"></i>
                        <p>Thêm Danh Mục</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/addproduct">
                        <i class="pe-7s-plus"></i>
                        <p>THÊM SẢN PHẨM</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/addsuppliers">
                        <i class="pe-7s-plus"></i>
                        <p>THÊM THƯƠNG HIỆU</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/tablelist">
                        <i class="pe-7s-news-paper"></i>
                        <p>LOẠI SP & THƯƠNG HIỆU</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/listproduct">
                        <i class="pe-7s-news-paper"></i>
                        <p>DANH SÁCH SẢN PHẨM</p>
                    </a>
                </li>
                
                <li>
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
                </li>
                
                <li>
                    <a href="/admin/importOrderFromSupplier">
                        <i class="pe-7s-note"></i>
                        <p>PHIẾU NHẬP HÀNG</p>
                    </a>
                </li>
                
                <li>
                    <a href="/admin/reportmonth">
                        <i class="pe-7s-pin"></i>
                        <p>THỐNG KÊ THEO THÁNG</p>
                    </a>
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
