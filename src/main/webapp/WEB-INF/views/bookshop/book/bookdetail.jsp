<%@page import="team1.project.bookshop.domain.MemberForm"%>
<%@page import="team1.project.bookshop.domain.Book"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	int member_idx=0;
%>
<%
	Book book = (Book)request.getAttribute("book");
	
	if(session.getAttribute("member")!=null){
		MemberForm member = (MemberForm)session.getAttribute("member");
		member_idx = member.getMember_idx();
	}

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


    <!-- Categories Section Begin -->
  
<!-- Categories Section End -->

<!-- Product Section Begin -->


	<div class="container mt-5">
		<div class="row">
			
			<div class="col-lg-12 col-me-3">
				<div class="container">
					<div class="row">
						<div class="col-6">
						<img  src="/resources/data/<%=book.getFilename()%>">
							<!-- 종합되면 나중에 패스 설정을 해주자  src="/img/book/"-->
						</div>
						<div class="col-6">
							<div class="product__details__text">
								<input type="hidden" value="<%=member_idx%>" name="member_idx" id="member_idx">
								<div>
									<input type="hidden" id="book_idx">
								</div>
								<div>
									<h3><%=book.getBook_name() %></h3>
								</div>
								<div class="product__details__price">
									<%=book.getDiscount() %>원 <span><%=book.getPrice() %></span>
								</div>
								<p><%=book.getSummary() %></p>
								<div class="product__details__button">
									<a  onclick="addcart(<%=book.getBook_idx() %>)" class="cart-btn">장바구니에 담기</a>
								</div>
								<div class="product__details__widget">
									<ul>
										<li>
											<span>저자:</span>
											<div class="color__checkbox">
												<label><%=book.getWriter() %></label>
											</div>
										</li>
										<li><span>출판사:</span>
											<div class="size__btn">
												<label><%=book.getPublisher() %></label>
											</div></li>
										<li><span>Type:</span>
												<div >
													<label><%=book.getBookType().getType() %></label>
												</div></li>
									</ul>
								</div>
							</div>
	
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
				

<!-- Product Section End -->

<!-- Banner Section Begin -->

<!-- Banner Section End -->

<!-- Trend Section Begin -->

<!-- Trend Section End -->

<!-- Discount Section Begin -->

<!-- Discount Section End -->

<!-- Services Section Begin -->

<!-- Services Section End -->

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
</body>
<script type="text/javascript">




	function addcart(book_idx){

	if($("#member_idx").val()==0){
		//로그인이 안되어있다면...
		alert("로그인이 필요한 서비스 입니다!");
	
		location.href="/member/login";
	}else{
		
		//로그인이 된 상태라면...
		let formdata = new FormData();
		formdata.append("member_idx", $("#member_idx").val());
		formdata.append("book_idx", book_idx);
	
		
		$.ajax({
			url:"/rest/cart_shop",
			type:"POST",
			data:formdata,
			success:function(result, status, xhr){
				console.log(result);
				alert("장바구니 담기 완료");
			}
			
		});

		
	}
	}
	
	


</script>

</html>