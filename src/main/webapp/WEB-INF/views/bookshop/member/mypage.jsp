<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<%@ include file="../inc/header.jsp" %>
<style>
.nav-link{
	font-size:55pt;
}
</style>
</head>

<body>
    <!-- Page Preloder -->
	<%@ include file="../inc/preloader.jsp" %>

    <!-- Offcanvas Menu Begin -->
    <%@ include file="../inc/main_navi.jsp"%>    
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <%@ include file="../inc/header_section.jsp"%>
    <!-- Header Section End -->
    
    <section>
    	<div class="container">
    		<div class="row m-5">
   				<%@ include file="../inc/sidebar_left.jsp" %>
    			<div class="col">
				    <form id="form1">
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="상담하실 메시지 입력" id="t_send">
				        </div>
				        <div class="form-group">
				            <textarea class="form-control" id="t_receive"></textarea>
				        </div>
				        <button type="button" class="btn btn-primary" id="bt_send">Send</button>
				    </form>
    			
    			</div>
    		</div>
    	</div>
    </section>
    
    
    
    
    
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

<script type="text/javascript">

</script>
</body>

</html>