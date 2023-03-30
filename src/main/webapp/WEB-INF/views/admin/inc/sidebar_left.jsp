<%@page import="team1.project.bookshop.domain.Admin"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%Admin admin = (Admin)session.getAttribute("admin"); %>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="/admin/main" class="brand-link"> <img
		src="/resources/admin/dist/img/AdminLTELogo.png" alt="AdminLTE Logo"
		class="brand-image img-circle elevation-3" style="opacity: .8">
		<span class="brand-text font-weight-light">AdminLTE 3</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="/resources/admin/dist/img/user2-160x160.jpg"
					class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">

				<a href="/admin/main" class="d-block"><%=admin.getAdmin_name()%></a>
			</div>
			<div class="info">
				<a href="/admin/logout" class="d-block">로그아웃</a>
			</div>
		</div>
		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library
                
               nav-item menu-open : 메뉴 열려있게..
               -->
				<li class="nav-item"><a href="#" class="nav-link active">
						<i class="nav-icon fas fa-tachometer-alt"></i>
						<p>
							상품관리 <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="/admin/category/top"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>상위 카테고리관리</p>
						</a></li>
						<li class="nav-item"><a href="/admin/category/sub"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>하위 카테고리관리</p>
						</a></li>
						<li class="nav-item"><a href="/admin/book/registform" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>도서 등록</p>
						</a></li>
						
					</ul></li>


				<li class="nav-item"><a href="#"
					class="nav-link active"> <i
						class="nav-icon fas fa-tachometer-alt"></i>
						<p>
							회원관리 <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="/admin/member/list"
							class="nav-link active"> <i class="far fa-circle nav-icon"></i>
								<p>회원목록</p>
						</a></li>
					</ul></li>

				<li class="nav-item"><a href="#"
					class="nav-link active"> <i
						class="nav-icon fas fa-tachometer-alt"></i>
						<p>
							주문관리 <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="/order/list"
							class="nav-link active"> <i class="far fa-circle nav-icon"></i>
								<p>주문 목록 관리</p>
						</a></li>
					</ul></li>

				<li class="nav-item"><a href="#"
					class="nav-link active"> <i
						class="nav-icon fas fa-tachometer-alt"></i>
						<p>
							고객센터 <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="/admin/inquiry_category/main" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>1:1 문의 카테고리</p>
						</a></li>
						<li class="nav-item"><a href="/admin/inquiry/list" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>1:1 문의 접수 리스트</p>
						</a></li>
						<li class="nav-item"><a href="/admin/notice/list" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>공지사항</p>
						</a></li>
					</ul></li>


			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>