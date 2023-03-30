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
    <%@ include file="../inc/header_section.jsp"%>
    <!-- Header Section End -->
	
    <section>
    	<div class="container">
    		<div class="row m-5">
   				<%@ include file="../inc/sidebar_left.jsp" %>
    			<div class="col">
				    <form id="form1">
				    	<h3>본인확인을 위해 비밀번호를 입력해주세요</h3>
				        <div class="form-group">
				            <input type="hidden" class="form-control" name="member_idx" value="<%=member.getMember_idx() %>" readonly="readonly">
				        </div>
				        <div class="form-group">
				            <span>아이디</span>
				            <input type="text" class="form-control" name="id" value="<%=member.getId() %>" readonly="readonly">
				        </div>
				        <div class="form-group">
				        	<span>비밀번호 확인</span>
				            <input type="text" class="form-control" placeholder="비밀번호를 입력해주세요" name="pass">
				            <p name="pass"></p>
				        </div>
				        <button type="button" class="btn btn-primary" id="bt_check">확인</button>
						<button type="button" class="btn btn-secondary float-right" id="bt_cancel">취소</button>
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

function passcheck(){
	//비동기방식
	console.log("비동기 비밀번호 확인 호출");
	let formData=$("#form1").serialize();
	
	$.ajax({
		url:"/rest/member/pass_check",
		type:"POST",
		data:formData,
		success:function(result, status, xhr){
			console.log(result);
			if(result.code==0){
				location.href="/member/info";
			}else if(result.code==1){
				$("#form1 p[name='pass']").text(result.msg);
				alert(result.msg);
			}else{
				alert(result.msg);
				location.href="/";
			}
		},
		error:function(xhr, status, err){
			//페이지는 그대로지만, 로그아웃되었을 때
			alert("세션이 만료되었습니다. 다시 로그인 해주세요.");
			location.href="/";
		}
	});
	
}


$(function(){

	$("#bt_check").click(function(){
		passcheck();
	});
	
	$("#bt_cancel").click(function(){
		location.href="/member/mypage";
	});
	
});
</script>
</body>

</html>