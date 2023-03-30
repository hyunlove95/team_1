<%@page import="team1.project.bookshop.domain.Inquiry_category"%>
<%@page import="team1.project.bookshop.domain.MemberForm"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	MemberForm member=(MemberForm)session.getAttribute("member");
%>
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
                        <a href="/"><img src="/resources/shop/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="/bookshop/">Home</a></li>
                            <% %>
                            <%%>
                            <li><a href="#"><%%></a></li>
                            <% %>
                            
                            <li><a href="#">Shop</a></li>
                            <li><a href="#">고객센터</a>
                                <ul class="dropdown">
                                    <li><a href="./product-details.html">상품문의</a></li>
                                    <li><a href="/inquiry/registform">1:1문의 접수</a></li>
                                    <li><a href="/inquiry/list">1:1문의 내역</a></li>
                                    <li><a href="/notice/list">공지사항</a></li>      
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                        <div class="header__right__auth">
                       		<%if(member==null){ %>
                            <a href="/member/login">Login</a>
                            <a href="/member/join">Register</a>
                           <%}else{ %>
                            <a href="/member/mypage"><%=member.getName()%>님</a>
                            <a href="/member/logout">Logout</a>
                           <%} %>
                        </div>
                        <ul class="header__right__widget">
                            <li><span class="icon_search search-switch"></span></li>
                            <li><a href="#"><span class="icon_heart_alt"></span>
                                <div class="tip">2</div>
                            </a></li>
                            <li>
                             	<%if(member==null){ %>
	                            <a href="javascript:alert('로그인이 필요한 서비스입니다');">
								<%}else{ %>
	                            <a href="/payment/cartlist.jsp">
	                            <%} %>
	                            <span class="icon_bag_alt"></span>
	                                <div class="tip">2</div>
	                            </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>