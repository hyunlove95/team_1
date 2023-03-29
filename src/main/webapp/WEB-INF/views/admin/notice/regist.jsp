<%@page import="team1.project.bookshop.domain.Notice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Notice> noticeList = (List)request.getAttribute("noticeList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard</title>
<%@ include file="../inc/header_link.jsp"%>
<style type="text/css">
.box-style{
	width:90px;
	height:95px;
	border:1px solid #ccc;
	display:inline-block;
	margin:5px;
}
.box-style img{
	width:75px;
	height:70px;
}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<%@ include file="../inc/preloader.jsp" %>
		
		<!-- Navbar -->
		<%@ include file="../inc/navbar.jsp" %>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="../inc/sidebar_left.jsp" %>
		
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">공지등록</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">고객센터> 공지등록</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content" id="app1">
				<div class="container-fluid">
				
					<!-- Main row -->
					<div class="row">
						<div class="col">
							<form id="form1">
								<div class="form-group row">
									<div class="col">
										<input type="text" class="form-control" name="title"
											placeholder="공지제목">
									</div>
								</div>
								<div class="form-group row">
									<div class="col">
										<input type="text" class="form-control" name="writer"
											placeholder="작성자">
									</div>
								</div>

								<div class="form-group row">
									<div class="col">
										<textarea class="form-control" id="content" name="content"></textarea>
									</div>
								</div>

								<div class="form-group row">
									<div class="col">
										<button type="button" class="btn btn-danger" id="bt_regist">등록</button>
										<button type="button" class="btn btn-danger" id="bt_list">목록</button>
									</div>
								</div>
							</form>	
						</div>
					</div>
					<!-- /.row (main row) -->
				</div>
				
				<!-- /.container-fluid -->
			
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		
		<%@ include file="../inc/footer.jsp" %>		

		<!-- Control Sidebar -->
		<%@ include file="../inc/sidebar_right.jsp" %>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->
	<%@ include file="../inc/footer_link.jsp" %>
	<script type="text/javascript">
		let app1;
		let key=0;
		
		
		app1=new Vue({
			el:"#app1",
			components:{
				
			},
			data:{
				count:5,
				imageList:[]  //files(read only) 배열의 정보를  담아놓을 배열
			}
		});
	
		
		/*------------------------------------------
		등록
		------------------------------------------*/
		function regist(){
			
			//querystring 화 시킴	
			let formData=$("#form1").serialize();  //title=ddf&writer=aaaa&content=66
			console.log(formData);
			
			//비동기로 등록요청
			
			$.ajax({
				url:"/admin/rest/notice",
				type:"POST", 
				data:formData,
				success:function(result, status, xhr){
					location.href="/admin/notice/list";
				}
			});
			
		}
		
		//서머노트 적용하기 
		$(function(){
			$("#content").summernote({
				height:400
			});
						
			//등록 이벤트 연결 
			$("#bt_regist").click(function(){
				regist();
			});
			
			$("#bt_list").click(function(){
				location.href="/admin/notice/list";
			});
			
		});
	
	</script>
</body>
</html>
