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
				        <div class="form-group">
				            <input type="hidden" class="form-control" name="member_idx" value="<%=member.getMember_idx() %>" readonly="readonly">
				        </div>
				        <div class="form-group">
				            <input type="hidden" class="form-control" name="sns_idx" value="<%=member.getSns_idx() %>" readonly="readonly">
				        </div>
				        <div class="form-group">
				            <span>아이디</span>
				            <input type="text" class="form-control" name="id" value="<%=member.getId() %>" readonly="readonly">
				        </div>
				        <%if(member.getSns_idx()==4){ %>
				        <div class="form-group">
				        	<span>새 비밀번호</span>
				            <input type="text" class="form-control" placeholder="입력하지 않을 시 기존의 비밀번호로 유지됩니다." name="pass" >
				        </div>
				        <div class="form-group">
				        	<span>새 비밀번호 확인</span>
				            <input type="text" class="form-control" placeholder="새 비밀번호를 확인해주세요" name="newpasscheck">
				            <p name="newpasscheck"></p>
				        </div>
				        <div class="form-group">
					        <span>이름</span>
				            <input type="text" class="form-control" placeholder="이름을 입력해주세요" name="name" value="<%=member.getName() %>">
				            <p name="name"></p>
				        </div>
				        <div class="form-group">
				        	<span>이메일</span>
				            <input type="text" class="form-control" placeholder="이메일을 입력해주세요" name="email" value="<%=member.getEmail() %>">
				            <p name="email"></p>
				        </div>
				        <%} %>
				        <div class="form-group">
				        	<span>주소</span>
				            <input type="text" class="form-control" placeholder="주소를 입력해주세요" name="address" value="<%=member.getAddress() %>">
				            <p name="address"></p>
				        </div>
				        <div class="form-group">
				        <span>상세주소</span>
				            <input type="text" class="form-control" placeholder="상세주소를 입력해주세요"  name="address_detail" value="<%=member.getAddress_detail() %>">
				            <p name="address_detail"></p>
				        </div>
				        <div class="form-group">
				        	<span>우편번호</span>
				            <input type="text" class="form-control" placeholder="우편번호를 입력해주세요" name="address_number" value="<%=member.getAddress_number() %>">
				            <p name="address_number"></p>
				        </div>
				        <div class="form-group">
				        	<span>휴대폰번호</span>
				            <input type="text" class="form-control" placeholder="휴대폰 번호를 입력해주세요" name="phone_number" value="<%=member.getPhone_number() %>">
				            <p name="phone_number"></p>
				        </div>
				        <button type="button" class="btn btn-primary" id="bt_edit">회원 정보 수정</button>
				        <button type="button" class="btn btn-secondary float-right" id="bt_cancel">취소</button>
				    </form>
				    <form id="form2">
					    <div class="form-group">
				            <input type="hidden" class="form-control" name="newpass">
				        </div>
					    <div class="form-group">
				            <input type="hidden" class="form-control" name="newpasscheck">
				        </div>
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

//비동기방식의 수정 요청
function edit(){
	if(!confirm("회원정보를 수정하시겠습니까?")){
		return;
	}
	let formData;
	<%if(member.getSns_idx()==4){%>
	//만약 pass의 길이가 0이면 비밀번호를 변경하지 않고 기존의 비밀번호를 사용하는 것으로 판단하겠다.
	if(
			$("#form1 input[name='pass']").val().length==0 &&
			$("#form1 input[name='newpasscheck']").val().length==0
			){
		$("#form1 input[name='pass']").attr("disabled", true);
		formData=$("#form1").serialize()+"&pass=<%=member.getPass()%>";
		$("#form1 input[name='pass']").attr("disabled", false);
	} else if($("#form1 input[name='pass']").val()==$("#form1 input[name='newpasscheck']").val()){
		//새 비밀번호와 확인의 값이 같다면
		formData=$("#form1").serialize();
	} else{
		alert("비밀번호가 일치하지 않습니다.");
		newpasscheck();
		return;
	}
		
	console.log(formData);
	
	$.ajax({
		url:"/rest/member/update",
		type:"POST",
		data:formData,
		success:function(result, status, xhr){
			console.log("result : ", result);
			console.log("result.code : ", result.code);
			alert(result.msg);
			if(result.code==0){
				location.replace("/member/mypage");
			}
			$("#form1 p").text('');
			$.each(result, function(key, value){
			    $("#form1 p[name="+key+"]").text(value);
			});
		},
		error:function(xhr, status, err){
			//rest 방식은 ajax에서 직접 링크를 걸어줘야한다.
			location.replace("/");
			alert("세션이 만료되었습니다.");
		}
	});
	<%} else {%>
	//소셜 로그인 시 (pass가 없다)
	$("#form1 input[name='id']").attr("disabled", true);
	formData=$("#form1").serialize();
	console.log(formData);
	$("#form1 input[name='id']").attr("disabled", false);
		$.ajax({
			url:"/rest/member/info_update",
			type:"POST",
			data:formData,
			success:function(result, status, xhr){
				console.log(result);
				alert(result.msg);
				if(result.code==0){
					location.replace("/member/mypage");
				}
				$("#form1 p").text('');
				$.each(result, function(key, value){
				    $("#form1 p[name="+key+"]").text(value);
				});
			}, 
			error:function(xhr, status, err){
				location.replace("/");
				alert("실패");
			}
		});
	<%}%>
}

function cancel(){
	location.href("/member/mypage");
}

function newpasscheck(){

	$("#form2 input[name='newpass']").val($("#form1 input[name='pass']").val());
	$("#form2 input[name='newpasscheck']").val($("#form1 input[name='newpasscheck']").val());
	let formData=$("#form2").serialize();
	
	$.ajax({
		url:"/rest/member/newpass_check",
		type:"POST",
		data:formData,
		success:function(result, status, xhr){
			$("#form1 p[name='newpasscheck']").text(result.msg);
			code=result.code; 
			//비밀번호 변경 시 비동기 방식으로 내려받은 코드를 활용하여 변경하는 방식은 
			//로직에 문제가 없더라도 오류가 너무 많이 나므로 사용할 수 없었다.
		},error:function(xhr, status, err){
			console.log(xhr.responseText);
			$("#form1 p[name='newpasscheck']").text('세션이 만료되었습니다. 다시 로그인 해주세요.');
		}
	});
	
}

$(function(){
	$("#bt_edit").click(function(){
		edit();
	});
	$("#bt_cancel").click(function(){
		cancel();
	});
	$("#form1 input[name='newpasscheck']").on('keyup', function(){
		newpasscheck();
	});
});
</script>
</body>

</html>