<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<%@ include file="../inc/header.jsp" %>
<style type="text/css">
p{
	color:red;
}
</style>
</head>

<body>
    <!-- Page Preloder -->
	<%@ include file="../inc/preloader.jsp" %>

    <!-- Offcanvas Menu Begin -->
    <%@ include file="../inc/main_navi.jsp"%>    
    <!-- Offcanvas Menu End -->

    <!-- Header Section End -->
    <section>
    	<div class="container">
    		<div class="row m-5">
    			<div class="col">
				    <form id="form1">
				        <div class="form-group">
				        	<label>그 동안 1조 온라인 서점을 이용해주셔서 감사합니다!</label>
				            <p>${message}</p>
				        </div>
				        <button type="button" class="btn btn-light" id="bt_main">메인으로 돌아가기</button>
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


$(function(){
	$("#bt_main").click(function(){
		location.href="/";
	});
});
</script>
</body>

</html>