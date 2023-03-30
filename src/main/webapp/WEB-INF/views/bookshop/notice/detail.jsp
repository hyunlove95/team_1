<%@page import="java.util.List"%>
<%@page import="team1.project.bookshop.domain.Notice"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Notice notice=(Notice)request.getAttribute("notice");
	List<Notice> noticeList=(List)request.getAttribute("noticeList");
%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<%@ include file="../inc/header.jsp" %>
</head>

<body>
    <!-- Page Preloder -->
	<%@ include file="../inc/preloader.jsp" %>

    <!-- Offcanvas Menu Begin -->
    <!-- 
    	jsp자체에서 지원하는 태그 
     	왜 써야 하나? 사실 jsp는 디자인 영역이므로, 개발자만 사용하는 것이
     	아니라 퍼블리셔, 웹디자이너와 공유를 한다..이때  java 에 대한 
     	非전문가들은 java 코드를 이해할 수 없기 때문에, 그들이 좀더 쉽게
     	다가갈 수 있도록 태그를 지원해준다 ( 협업 때문에 )
     -->
	<%@ include file="../inc/main_navi.jsp"%>    
	<%@ include file="../inc/header_section.jsp"%>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
   <%%>
    <!-- Header Section End -->
    <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/"><i class="fa fa-home"></i> Home</a>
                        <span>BookShop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Shop Section Begin -->
	    <section class="shop spad">
        <div class="container">
            <div class="row">
                <%@ include file="../inc/sidebar_left.jsp" %>
                <div class="col-lg-9 col-md-9">
					<div class="container">
						<table class="table table-hover">
							<tbody>
								<tr>
									<td>제목</td>
									<td><%=notice.getTitle()%></p></td>
									<td> </td>
									<td> </td>
									<td> </td>
									<td> </td>
								</tr>
							</tbody>
							<tbody>
								<tr>
									<td>작성자</td>
									<td><%=notice.getWriter() %></td>
									<td>작성일</td>
									<td><%=notice.getRegdate() %></td>
									<td>조회수</td>
									<td><%=notice.getHit()%> </td>
								</tr>
							</tbody>
							<tbody>
								<tr>
									<td colspan="5" style="text-align: center">내용</td>
								</tr>
							</tbody>
							<tbody>
								<tr>
									<td><%=notice.getContent()%></td>
								</tr>
							</tbody>
							<tbody>
								<tr>
									
								</tr>
							</tbody>
						</table>
					</div>
					<div class="form-group row text-center">
						<div class="col">
							<button type="button" class="btn btn-danger" id="bt_list">공지사항 목록</button>
						</div>
					</div>
				</div>
			</div>
        </div>
    </section>
    
	<!-- Shop Section End -->
    
    
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

//목록 이벤트 연결 
$("#bt_list").click(function(){
	location.href="/notice/list";
});

</script>
</body>
</html>
