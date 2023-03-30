<%@page import="team1.project.bookshop.util.PageManager"%>
<%@page import="team1.project.bookshop.domain.Inquiry"%>
<%@page import="team1.project.bookshop.domain.Inquiry_category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Inquiry> inquiryList=(List)request.getAttribute("inquiryList");
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
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
   <%@ include file="../inc/header_section.jsp"%>
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
            <div class="row m-5">
                <%@ include file="../inc/sidebar_left.jsp" %>
                <div class="col-lg-9 col-md-9">
					<div class="container">
						<table class="table table-hover">
							<thead>
								<tr>
									<th style="width: 15%">No</th>
									<th style="width: 50%">문의 제목</th>
									<th style="width: 30%">날짜</th>
								</tr>
							</thead>
							<tbody>
								<%for (int i = 0; i < inquiryList.size(); i++) {%>
								<%Inquiry inquiry=(Inquiry)inquiryList.get(i); %>
								<tr>
									<td><%=i%></td>
									<td><a href="/inquiry/detail?inquiry_idx=<%=inquiry.getInquiry_idx()%>"><%=inquiry.getTitle() %></a></td>
									<td><%=inquiry.getRegdate().substring(0, 10) %></td>
								</tr>
								<%} %>
							</tbody>
							<tbody>
								<tr>
									<td></td>
								</tr>
							</tbody>
						</table>
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


</script>
</body>
</html>
