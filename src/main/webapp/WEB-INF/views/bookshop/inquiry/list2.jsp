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
<%@ include file="../inc/header_link.jsp"%>

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
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="shop__sidebar">
                        <div class="sidebar__categories">
                            <div class="section-title">
                                <h4>Categories</h4>
                            </div>
                            <div class="categories__accordion">
                                <div class="accordion" id="accordionExample">

                                </div>
                            </div>
                        </div>
                        <div class="sidebar__filter">
                            <div class="section-title">
                                <h4>Shop by price</h4>
                            </div>
                            <div class="filter-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                data-min="33" data-max="99"></div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <p>Price:</p>
                                        <input type="text" id="minamount">
                                        <input type="text" id="maxamount">
                                    </div>
                                </div>
                            </div>
                            <a href="#">Filter</a>
                        </div>
                        <div class="sidebar__sizes">
                            <div class="section-title">
                                <h4>Shop by size</h4>
                            </div>
                            <div class="size__list">
                                <label for="xxs">
                                    xxs
                                    <input type="checkbox" id="xxs">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="xs">
                                    xs
                                    <input type="checkbox" id="xs">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="xss">
                                    xs-s
                                    <input type="checkbox" id="xss">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="s">
                                    s
                                    <input type="checkbox" id="s">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="m">
                                    m
                                    <input type="checkbox" id="m">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="ml">
                                    m-l
                                    <input type="checkbox" id="ml">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="l">
                                    l
                                    <input type="checkbox" id="l">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="xl">
                                    xl
                                    <input type="checkbox" id="xl">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>
                        <div class="sidebar__color">
                            <div class="section-title">
                                <h4>Shop by size</h4>
                            </div>
                            <div class="size__list color__list">
                                <label for="black">
                                    Blacks
                                    <input type="checkbox" id="black">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="whites">
                                    Whites
                                    <input type="checkbox" id="whites">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="reds">
                                    Reds
                                    <input type="checkbox" id="reds">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="greys">
                                    Greys
                                    <input type="checkbox" id="greys">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="blues">
                                    Blues
                                    <input type="checkbox" id="blues">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="beige">
                                    Beige Tones
                                    <input type="checkbox" id="beige">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="greens">
                                    Greens
                                    <input type="checkbox" id="greens">
                                    <span class="checkmark"></span>
                                </label>
                                <label for="yellows">
                                    Yellows
                                    <input type="checkbox" id="yellows">
                                    <span class="checkmark"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                
				<div class="container col-lg-9 col-md-9">


						<div class="" >
							<div >
								<div class="col-5" >
									<div class="input-group mb-3" >
										<input type="text" class="form-control"
											placeholder="제목으로 검색" id="keyword">
										<div class="input-group-prepend">
											<button class="btn btn-outline-primary" type="button"
												id="bt_search" >검색</button>
										</div>
									</div>
								</div>
							</div>
					



							<div id="app1">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>No</th>
											<th>카테고리</th>
											<th>제목</th>
											<th>날짜</th>
										</tr>
									</thead>
									<tbody>
										<!-- template는 자체로는 아무런 효과가 없고, 그냥 뷰의 영역임을 지정한다 -->
										<template v-for="(inquiry, i) in currentList">
											<inquiry :key="inquiry.inquiry_idx" :num="num-i" :obj="inquiry" />
										</template>
										<tr>
											<td id="paging-area" colspan="5" style="text-align: center">
											</td>
										</tr>
									</tbody>
									<tbody>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
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
	<script type="text/javascript" src="/resources/js/Pager.js"></script>
	<script type="text/javascript">
		let pager = new Pager(); //인스턴스 생성
		let currentPage=1; //현재 보고있는 페이지
		
		let app1;
		let key=0;
		
		const inquiry={
				template:`
                <tr>
	                <td>{{n}}</td>
	                <td>{{json.inquiry_category.inquiry_category_name}}</td>
	                <td @click="getDetail(obj.inquiry_idx)"><a href="#">{{json.title}}</a></td>
	                <td>{{json.regdate.substring(0, 10)}}</td>
                </tr>
			`,
			props:["obj", "num"], //props 오직 외부에서 전달되는 데이터 받기 위함
			data(){ //자바로 비유하면 인스턴스 변수영역
				return{
					json:this.obj,
					n:this.num
				};
			},
			methods:{
				getDetail:function(inquiry_idx){
					location.href="/inquiry/detail?inquiry_idx="+inquiry_idx;
				}
			}
		};
		
		app1=new Vue({
			el:"#app1",
			components:{
				inquiry
			},
			data:{
				count:5,
				inquiryList:[], //전체배열
				currentList:[], //페이지당 보여질 배열
				num:0 //페이지당 시작 번호를 뷰 컴포넌트에서 접근할 수 있도록...
			}
		});
		
		function pageLink(n) {
			//서버에서 가져온 데이터를 대상으로 페이징 로직을 적용해보기
			pager.init(app1.inquiryList, n);
			
			console.log("totalRecord=", pager.totalRecord);
			console.log("pageSize=", pager.pageSize);
			console.log("totalPage=", pager.totalPage);
			console.log("blockSize=", pager.blockSize);
			console.log("currentPage=", pager.currentPage);
			console.log("firstPage=", pager.firstPage);
			console.log("lastPage=", pager.lastPage);
			console.log("curPos=", pager.curPos);
			console.log("num=", pager.num);
			
			app1.num=pager.num;
			
			//넘겨받은 페이지 번호를 이용하여, 해당 페이지에 보여질 배열을 생성 후
			//currentList 에 대입(Vue의 변수인 currentList 만 제어하면 디자인은 자동으로 변경)
			
			app1.currentList.splice(0, app1.currentList.length);//싹 비우고...
			
			for(let i=pager.curPos; i<pager.curPos+pager.pageSize;i++){
				//num이 1보다 작아지면 멈춤
				if(pager.num<1)break;
				pager.num--;
				
				//전체 배열에서, 일부 배열로 옮겨 심기
				app1.currentList.push(app1.inquiryList[i]);
			}
		}
		
		//서버에서 상품목록 가져오기
		function getList() {
			$.ajax({
				url:"/rest/inquiry",
				type:"get",
				success:function(result, status, xhr){
					//서버에서 가져온 json 배열을 뷰의 템플릿이 바라보고 있는 productList 에 대입만하면
					//디자인은 알아서 변경된다...(개발자는 데이터 제어에만 집중하면 됨...디자인 신경꺼라)
					app1.inquiryList = result;
					console.log(result);
					
					pageLink(currentPage); //페이징 처리 계산 수행
					
					//넘버링 출력
					for(let i=pager.firstPage; i<=pager.lastPage;i++){
						if(i > pager.totalPage)break; //내가 가진 페이지수를 넘어서면 반복문 빠져나오기
						$("#paging-area").append("<a href='javascript:pageLink("+i+")' style='margin:3px'>"+i+"</a>");
					}
					
				}
			});
		}
		
		function search() {
			app1.currentList.splice(0,app1.currentList.length);
			let keyword=$("#keyword").val();
			$.ajax({
				url:"/rest/searchInquiry?title="+keyword,
				type:"get",
				success:function(result, status, xhr){
					console.log(result);
					app1.currentList=result;
					
				}
			});
		}
		
		$(function(){			
			//등록 이벤트 연결 
			$("#bt_regist").click(function(){
				regist();
			});
			
			
			$("#bt_search").click(function(){
				search();
			});
			
			getList();
			
		});
	
	</script>
</body>
</html>
