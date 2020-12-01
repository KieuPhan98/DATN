<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- new -->
<div class="newproducts-w3agile">
    <div class="container">
        <h3><spring:message code="order" text="default text"/></h3>
        <div class="agile_top_brands_grids">
            <c:forEach items="${listTest1}" var="report">
                <div class="col-md-3 top_brand_left-1">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="agile_top_brand_left_grid_pos">
                                <img src="/resources/images/offer.png" alt=" " class="img-responsive">
                            </div>
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/detail?id=${report[3]}">
                                            	<img title=" " alt=" " src="/resources/images/${report[2]}" style="width: 150px; height: 150px;">
                                            </a>
                                            <p><a href="/detail?id=${report[3]}">${report[0]}</a></p>
                                            <div class="stars">
                                                <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                                <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                                <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                                <i class="fa fa-star blue-star" aria-hidden="true"></i>
                                                <i class="fa fa-star gray-star" aria-hidden="true"></i>
                                            </div>
                                            <h4>$ <f:formatNumber value="${report[5]}" pattern="#,###.00"/></h4>
                                        </div>
                                          <c:if test="${report[1] eq 0}">
                                               <span> Hết hàng</span>
                                          </c:if>		
                                          <c:if test="${report[1] ne 0  }">
                                              <div class="snipcart-details top_brand_home_details">
                                                   <a href="/addToCart?id=${report[3]}">
                                                   		<img src="/resources/images/cart.svg" width="50" height="50" alt=" " class="img-responsive"/>
                                                   </a>
                                              </div>  
                                          </c:if>	                                                                              
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>



            <div class="clearfix"></div>
        </div>
    </div>
</div>
</body>
</html>
