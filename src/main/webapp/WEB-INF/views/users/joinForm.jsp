<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form><!-- onsubmit이 false를 리턴하면 action이 실행되지 않음 -->
		<div class="mb-3 mt-3">
			<input id="username" type="text" class="form-control" placeholder="Enter username">
			<button id="btnUsernameSameCheck" class="btn btn-warning" type="button">유저네임 중복체크</button>
			<!-- 타입 안넣으면 자동으로 submit 타입이 됨-->
		</div>
		<div class="mb-3">
			<input id="password" type="password" class="form-control" placeholder="Enter password">
		</div>
		<div class="mb-3">
			<input id="checkPassword" type="password" class="form-control" placeholder="Enter password">
		</div>
		<div class="mb-3">
			<input id="email" type="email" class="form-control" placeholder="Enter email">
		</div>
		<button id="btnJoin" type="button" class="btn btn-primary">회원가입</button>
	</form>
</div>

<script>
	let isUsernameSameCheck = false;
	
	// 회원가입
	$("#btnJoin").click(()=>{
		if(isUsernameSameCheck == false){
			alert("아이디 중복체크를 진행해주세요");
			return; // return; -> 메서드 종료
		}
		
		
		// 0. 통신 오브젝트 생성
		let data = {
				username: $("#username").val(),
				password: $("#password").val(),	
				checkPassword: $("#checkPassword").val(),
				email: $("#email").val()
		}
			
		if(data.password != data.checkPassword){
			alert("입력한 비밀번호가 다릅니다.");
			return;
		}
		

		
		$.ajax("/join",{
			type: "POST",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type" : "application/json; charset=utf-8"
			} 
		}).done((res)=>{
			if(res.code == 1){
				location.href = "/loginForm";
			}
		});
	});
	
	

	// 유저네임 중복체크
	$("#btnUsernameSameCheck").click(()=>{
	
		// body 데이터가 없으니 json으로 날릴 필요가 없음 (json은 바디 데이터를 날리는데에 사용)
		
		// 1. 사용자가 적은 username값 가져오기
		let username = $("#username").val();

		// 2. Ajax 통신, DB에 해당 username값이 있는지 확인
		$.ajax("/users/usernameSameCheck?username="+username,{
			type:"GET", // 디폴트가 get임 (get 할거면 생략해도 된다는 뜻)
			dataType:"json",
			async: true // 거의 무조건 true로 둔다고 보면 됨
		}).done((res)=>{
			console.log(res);
			if(res.code==1){
				// alert("통신성공");
				if(res.data== false){
					alert("중복되는 아이디가 없습니다.");
					isUsernameSameCheck = true;
				}else{
					alert("아이디가 중복됩니다. 다른 아이디를 사용해주십시오.");
					isUsernameSameCheck = false;
					$("#username").val("");
				}
			}
		});
	});

	
	
	
</script>

<%@ include file="../layout/footer.jsp"%>

