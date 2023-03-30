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
    				<h3>아래 내용을 꼭 확인해 주세요.</h3>
    				<div class="card bg-light text-dark">
						<div class="card-body">회원탈퇴 즉시 회원정보는 모두 삭제되며, 재가입시에도 복원되지 않습니다.</div>
					</div>
				    <form id="form1">
				    	<h4>본인확인을 위해 비밀번호를 입력해주세요</h4>
				        <div class="form-group">
				            <input type="hidden" class="form-control" name="member_idx" value="<%=member.getMember_idx() %>" readonly="readonly">
				        </div>
				        <div class="form-group">
				            <span>아이디</span>
				            <input type="text" class="form-control" name="id" value="<%=member.getId() %>" readonly="readonly">
				        </div>
				        <%if(member.getSns_idx()==4){ %>
				        <div class="form-group">
				        	<span>비밀번호 확인</span>
				            <input type="text" class="form-control" placeholder="비밀번호를 입력해주세요" name="pass">
				            <p>${message}</p>
				        </div>
				        <% }%>
				        <button type="button" class="btn btn-danger" id="bt_withdraw">회원탈퇴</button>
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

function withdraw(){
	//동기방식
	if(!confirm("정말로 아이디를 삭제하시겠습니까?")){
		return;
	}
	$("#form1").attr({
		action:"/member/withdraw",
		method:"post"
	});
	$("#form1").submit();
}


$(function(){

	$("#bt_withdraw").click(function(){
		withdraw();
	});
	
	$("#bt_cancel").click(function(){
		location.href="/member/mypage";
	});
	
});
</script>
</body>

</html>