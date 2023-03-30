<%@page import="team1.project.bookshop.domain.Inquiry_category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Inquiry_category> inquiry_categoryList=(List)request.getAttribute("inquiry_categoryList");
%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<%@ include file="../inc/header.jsp" %>
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
				<!-- Main content -->
				<section class="content" id="app1">
					<div class="container-fluid">

						<!-- Main row -->
						<div class="row">
							<div class="col">

								<div class="form-group row">
									<div class="col" style="text-align: center">
										<label>문의 유형</label>
									</div>
									<div class="col">
										<select class="form-control" name="inquiry_category_idx">
											<option value="0">문의 유형</option>
											<%for (Inquiry_category inquiry_category : inquiry_categoryList) {%>
											<option value="<%=inquiry_category.getInquiry_category_idx()%>"><%=inquiry_category.getInquiry_category_name()%></option>
											<%}%>
										</select>
									</div>
								</div>

								<div class="form-group row">
									<div class="col" style="text-align: center">
										<label>제목</label>
									</div>
									<div class="col">
										<input type="text" class="form-control" name="title"
											placeholder="제목">
									</div>
								</div>
								
								<div class="form-group row">
									<div class="col" style="text-align: center">
										<label>내용</label>
									</div>
									<div class="col" style="height:400">
										<textarea rows="10" cols="70" class="form-control" name="content"></textarea>
									</div>
								</div>

								<div class="form-group row">
									<div class="col" style="text-align: center">
										<label>사진첨부</label>
									</div>
									<div class="col">
										<input type="file" class="form-control" name="file" multiple>
									</div>
								</div>

								<div class="form-group row">
									<div class="col">
										<template v-for="json in imageList">
											<imagebox :key="json.key" :obj="json" />
										</template>
									</div>
								</div>

								<div class="form-group row">
									<div class="col" style="text-align: center">
										<button type="button" class="btn btn-danger" id="bt_cancel">취소</button>
										<button type="button" class="btn btn-danger" id="bt_inquiry">문의 접수</button>
									</div>
								</div>

							</div>
						</div>
						<!-- /.row (main row) -->
					</div>

					<!-- /.container-fluid -->

				</section>
				<!-- /.content -->
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
let app1;
let key=0;

const imagebox={
	template:`
		<div class="box-style">
			<div v-on:click="del(json)">X</div>
			<img :src="json.binary" />
		</div>
	`,
	props:["obj"],
	data(){
		return{
			json:this.obj
		};
	},
	methods:{
		del(json){
			console.log("선택한 이미지에 대한 json 은", json);
			
			//선택한 이미지에 대한 json이 배열에 있는지
			//없으면 -1, 있으면 0 이상의 수(즉 발견된 index 번째)
			let index=app1.imageList.indexOf(json);
			console.log("index is", index);
			
			//Vue는 데이터만 지워도, UI  가 반을을 보인다..(즉 자동으로 동기화) bind 되어있다
			app1.imageList.splice(index, 1);
		}
	}
};

app1=new Vue({
	el:"#app1",
	components:{
		imagebox
	},
	data:{
		count:5,
		imageList:[]  //files(read only) 배열의 정보를  담아놓을 배열
	}
});

/*------------------------------------------
중복된 이미지체크
------------------------------------------*/
function checkDuplicate(filename){
	let count=0;
	
	for(let i=0;i<app1.imageList.length;i++){
		let json=app1.imageList[i];
		if(json.name==filename){ //중복발견..
			count++;
			break;
		}
	}
	return count;
}

/*------------------------------------------
미리보기
------------------------------------------*/
function preview(files){
	
	//이미지 화면에 출력
	for(let i=0;i<files.length;i++){
		let file = files[i];
		
		if(checkDuplicate(file.name) <1){ //중복된 이미지가 없을때만...
			let reader = new FileReader();//스트림 생성
			reader.onload=(e)=>{
				
				key++; //사용자가 이미지를 선택할때마다 1씩 증가하여 중복을 불허한다
				
				let json=[]; // imageList배열에 복합적인 정보를 담아놓기 위해 
				json['key']=key;//추후 이미지 삭제시 기준값으로 사용예정 
				json['name']=file.name; //중복이미지가 추가되지 않도록... 
				json['binary']=e.target.result; //src에 대입할 바이너리 정보 
				json['file']=file; //전송할때 파라미터에 심을 파일
				
				app1.imageList.push(json);
			};
			reader.readAsDataURL(file);//파일읽기
		}
	}
	
}

/*------------------------------------------
등록
------------------------------------------*/
function regist(){
	//파일업로드를 커스터마이징 시켰기 때문에...
	let formData = new FormData();
	
	formData.append("inquiry_category.inquiry_category_idx", $("select[name='inquiry_category_idx']").val());
	formData.append("title", $("input[name='title']").val());
	formData.append("content", $("textarea[name='content']").val());
	
	//선택한 이미지 수만큼  formData 에 추가 
	for(let i=0;i<app1.imageList.length;i++){
		let json=app1.imageList[i];
		formData.append("photo", json.file);
	}
	
	$.ajax({
		url:"/rest/inquiry", 
		type:"post", 
		data:formData, 
		processData:false, /*query string 사용여부*/
		contentType:false, /* application/x-www-form~~ 사용여부*/
		success:function(result, status, xhr){
			alert(result.msg);
			location.href="/inquiry/list";
		},
		error:function(xhr, status, err){
			console.log("xhr", xhr);
			console.log("status", status);
			console.log("err", err);
			
			//JSON.stringify()   json --> string  왜 바꿨나? 전송할려고...,
			let json=JSON.parse(xhr.responseText);  //string --> json
			alert(json.msg);
		}
		
	});
}


$(function(){
	
	//파일에 이벤트 연결 
	$("input[name='file']").change(function(){
		console.log(this.files);
		preview(this.files);
	});
	
	$("#bt_cancel").click(function(){
		location.href="/inquiry/list";
	});
	
	//등록 이벤트 연결 
	$("#bt_inquiry").click(function(){
		regist();
	});
	
});

</script>
</body>
</html>
