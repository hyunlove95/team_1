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
				    <form id="form1">
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="아이디를 입력해주세요" name="id">
				            <button type="button" class="btn btn-secondary" id="bt_id_check">아이디 중복 확인</button>
				            <p name="id"></p>
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="비밀번호를 입력해주세요" name="pass">
				            <p name="pass"></p>
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="이름을 입력해주세요" name="name">
				            <p name="name"></p>
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="이메일을 입력해주세요" name="email">
				            <p name="email"></p>
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
				            <input type="text" class="form-control" placeholder="우편번호 5자리를 입력해주세요" name="address_number">
				            <p name="address_number"></p>
				        </div>
				        <div class="form-group">
				            <input type="text" class="form-control" placeholder="휴대폰 번호 11자리를 입력해주세요" name="phone_number">
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

function idCheck(){
	console.log("아이디 중복 검사 호출");
	let id=$("#form1 input[name=id]").val();
	console.log(id);
	
	//비동기 요청
	$.ajax({
		url:"/rest/member/idCheck",
		type:"POST",
		data:{id:id},
		success:function(result, status, xhr){
			console.log(result);
			$("#form1 p[name='id']").text(result.msg);
			//같은 아이디를 입력해도 성공이 떠버린다..
		},
		error:function(xhr, status, err){
			alert("아이디체크 실패");
			/*나중에 찾아보기
			let json=JSON.parse(xhr.responseText);
			console.log("json");
			*/
			console.log("xhr.status : ",xhr.status);
			console.log("xhr.responseText : ", xhr.responseText);
			console.log("error : ", err);
		}
	});
}

function regist(){
	console.log("비동기 회원가입 호출");
	let formData=$("#form1").serialize()+"&sns_idx=4";
	
	console.log(formData);
	$.ajax({
		url:"/rest/member/join",
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