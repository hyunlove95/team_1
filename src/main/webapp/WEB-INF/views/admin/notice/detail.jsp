<%@page import="team1.project.bookshop.domain.Notice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
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
							<h1 class="m-0">공지 상세내용</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">고객센터> 공지 상세내용</li>
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
								<input type="hidden" name="notice_idx" value="<%=notice.getNotice_idx()%>">
								<div class="form-group row">
									<div class="col">
										<input type="text" class="form-control" name="title" placeholder="공지제목" value="<%=notice.getTitle()%>">
									</div>
								</div>
								<div class="form-group row">
									<div class="col">
										<input type="text" class="form-control" name="writer" placeholder="작성자" value="<%=notice.getWriter()%>">
									</div>
								</div>

								<div class="form-group row">
									<div class="col">
										<textarea class="form-control" id="content" name="content"><%=notice.getContent()%></textarea>
									</div>
								</div>

								<div class="form-group row">
									<div class="col">
										<button type="button" class="btn btn-danger" id="bt_edit">수정</button>
										<button type="button" class="btn btn-danger" id="bt_del">삭제</button>
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


		/*------------------------------------------
		수정
		------------------------------------------*/
		$(function(){
			$("#bt_edit").click(function(){
				if(confirm("수정하실래요?")){
					$("#form1").attr({
						action:"/bookshop/admin/notice/edit",
						method:"post"
					});
					$("#form1").submit();		
				}
			});
		});	
				
		/*------------------------------------------
		삭제
		------------------------------------------*/
		$("#bt_del").click(function(){
			if(confirm("삭제하시겠어요?")){
				$("#form1").attr({
					action:"/bookshop/admin/notice/delete",
					method:"post"
				});
				$("#form1").submit();		
				
			}
		});
		
		//서머노트 적용하기 
		$(function(){
			$("#content").summernote({
				height:200
			});

					
			//목록 이벤트 연결 
			$("#bt_list").click(function(){
				location.href="/bookshop/admin/notice/list";
			});
		});
	</script>
</body>
</html>
