<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<%@ include file="../inc/header.jsp" %>
<style type="text/css">
p{
	color: red;
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
    <%//@ include file="../inc/header_section.jsp"%>
    <!-- Header Section End -->
    <section>
    	<div class="container">
    		<div class="row m-5">
    			<div class="col">
    			   	<div>
			    	<h3>추가 정보를 입력해주세요</h3>
			    	</div>
    			    <form id="form1">
				    	<div class="form-group">
				            <input type="hidden" class="form-control" name="member_idx" value="${member_idx}" readonly="readonly">
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="주소를 입력해주세요" name="address">
				            <p name="address"></p>
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="상세주소를 입력해주세요" name="address_detail">
				            <p name="address_detail"></p>
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="우편번호를 입력해주세요" name="address_number">
				            <p name="address_number"></p>
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="휴대폰 번호를 입력해주세요" name="phone_number">
				            <p name="phone_number"></p>
				        </div>
				        <button type="button" class="btn btn-primary" id="bt_regist">회원 가입</button>
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

function regist(){
	console.log("비동기 sns계정 info 등록 호출");
	let formData=$("#form1").serialize();
	
	console.log(formData);
	$.ajax({
		url:"/rest/member/sns/setInfo",
		type:"POST",
		data:formData,
		success:function(result, status, xhr){
			alert(result.msg);
			//console.log("결과 코드는", result.code);
			if(result.code==0){
				location.replace("/member/login");
			}
			$("#form1 p").text('');
			$.each(result, function(key, value){
			    $("#form1 p[name="+key+"]").text(value);
			});
		},
		error:function(xhr, status, err){
			console.log(xhr.responseText);
			let json=JSON.parse(xhr.responseText);
			alert(json.msg);
		}
	});
	
}

$(function(){
	$("#bt_id_check").click(function(){
		idCheck();
	});
	$("#bt_regist").click(function(){
		regist();
	});
});
</script>
</body>

</html>