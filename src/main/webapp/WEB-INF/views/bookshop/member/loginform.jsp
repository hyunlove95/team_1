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
				            <input type="text" class="form-control" placeholder="Enter ID" name="id">
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="Enter Pass" name="pass">
				            <p>${message}</p>
				        </div>
				        <button type="button" class="btn btn-primary" id="bt_login">로그인</button>
				        <button type="button" class="btn btn-primary" id="bt_regist">신규가입</button>
				        <button type="button" class="btn btn-info" id="bt_google">구글 로그인</button>
				        <button type="button" class="btn btn-success" id="bt_naver">네이버 로그인</button>
				        <button type="button" class="btn btn-warning" id="bt_kakao">카카오 로그인</button>
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

function login(){
	//동기방식
	console.log("로그인 호출");
	$("#form1").attr({
		action:"/member/login",
		method:"post"
	});
	$("#form1").submit();
}

function regist(){
	location.href="/member/join";
}

function gotoAuthForm(sns){
	$.ajax({
		url:"/rest/member/authform/"+sns,
		type:"get",
		success:function(result, status, xhr){
			console.log("인증주소는 ", result.msg);
			location.href=result.msg;
		}
	});
}

$(function(){

	$("#bt_login").click(function(){
		login();
	});
	
	$("#bt_regist").click(function(){
		regist();
	});
	
	$("#bt_google").click(function(){
		gotoAuthForm("google");
	});
	
	$("#bt_naver").click(function(){
		gotoAuthForm("naver");
	});
	
	$("#bt_kakao").click(function(){
		gotoAuthForm("kakao");
	});
	
	$("#bt_main").click(function(){
		location.href="/";
	});
	
	
	
	
	
});
</script>
</body>

</html>