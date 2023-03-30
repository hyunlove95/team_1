<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../inc/header.jsp"%>
</head>
<body>
    <%@ include file="../inc/preloader.jsp" %>

    <!-- Offcanvas Menu Begin -->
    <%@ include file="../inc/main_navi.jsp"%>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <%@ include file="../inc/header_section.jsp"%>
    <!-- Header Section End -->

    <!-- Breadcrumb Begin -->
  	<div class="container">
    <!-- 주문완료 -->
    <div class="orderInfo">
        <p>
            <strong>고객님의 주문이 완료 되었습니다.</strong>
            주문내역 및 배송에 관한 안내는 <a href="/myshop/order/list.html">주문조회</a> 를 통하여 확인 가능합니다.
        </p>
        <ul>
            <li>주문번호 : <strong>{$order_id}</strong></li>
            <li>주문일자 : <span>{$order_date}</span></li>
        </ul>
    </div>
    
    <!-- 결제정보 -->
    <div class="orderArea">
        <div class="title">
            <h3>결제정보</h3>
        </div>
        <div class="boardView">
            <table border="1" summary="">
            <caption>결제정보</caption>
            <tbody>
                <tr>
                    <th scope="row">최종결제금액</th>
                    <td class="price">
                        {$result_order_price_front_head}<strong>{$result_order_price_front}</strong><strong class="tail">{$result_order_price_front_tail}</strong> <span class="tail {$result_order_ref_display|display}">{$result_order_price_back_head}{$result_order_price_back}{$result_order_price_back_tail}</span>
                    </td>
                </tr>
                <tr>
                    <th scope="row">결제수단</th>
                    <td>
                        <strong>{$paymethod_name}</strong>
                    </td>
                </tr>
            </tbody>
            </table>
        </div>
    </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->

    <!-- Instagram Begin -->
    <%@ include file="../inc/insta.jsp" %>
    <!-- Instagram End -->

    <!-- Footer Section Begin -->
    <%@ include file="../inc/footer.jsp" %>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <%@ include file="../inc/search.jsp" %>
    <!-- Search End -->

    <!-- Js Plugins -->
    <%@ include file="../inc/footer_link.jsp" %>
</body>
</html>