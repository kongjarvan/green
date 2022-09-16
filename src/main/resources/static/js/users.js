/* 

*/

let isUsernameSameCheck = false;


// 회원가입
$("#btnJoin").click(() => {
	join();
	});


$("#btnUsernameSameCheck").click(() => {
	checkSame();
});


$("#btnLogin").click(() => {
	login();
});


$("#btnDelete").click(() => {
	resign();
});


$("#btnUpdate").click(() => {
	update();
});


//-------------------function---------------------//


function join(){
		let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		
		email: $("#email").val()
	}
	let checkPassword = $("#checkPassword").val();

	if (data.password != checkPassword) {
		alert("입력한 비밀번호가 다릅니다.");
		return;
	}



	$.ajax("/join", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/loginForm";
		}
	});
}


function checkSame(){
	
	// body 데이터가 없으니 json으로 날릴 필요가 없음 (json은 바디 데이터를 날리는데에 사용)

	// 1. 사용자가 적은 username값 가져오기
	let username = $("#username").val();

	// 2. Ajax 통신, DB에 해당 username값이 있는지 확인
	$.ajax(`/users/usernameSameCheck?username=${username}`,{
		type: "GET", // 디폴트가 get임 (get 할거면 생략해도 된다는 뜻)
		dataType: "json",
		async: true // 거의 무조건 true로 둔다고 보면 됨
	}).done((res) => {
		if (res.code == 1) {
			// alert("통신성공");
			if (res.data == false) {
				alert("중복되는 아이디가 없습니다.");
				isUsernameSameCheck = true;
			} else {
				alert("아이디가 중복됩니다. 다른 아이디를 사용해주십시오.");
				isUsernameSameCheck = false;
				$("#username").val("");
			}
		}
	});
}


function login(){
		let data = {
		username: $("#username").val(),
		password: $("#password").val()
	};

	$.ajax("/login", {
		type: "POST",
		dataType: "json", // 응답 데이터
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/";
		} else {
			alert("로그인 실패, 아이디 및 패스워드를 확인해 주세요");
		}
	});
}


function resign(){
		let id = $("#id").val();

	$.ajax("/users/" + id, {
		type: "DELETE",
		dataType: "json" // 응답 데이터
	}).done((res) => {
		if (res.code == 1) {
			alert("회원탈퇴 완료");
			location.href = "/"; // 새로고침(f5) 과 같은 기능
		} else {
			alert("회원탈퇴에 실패하였습니다.");
		}
	});
}


function update(){
		let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};
	let id = $("#id").val();

	$.ajax("/users/" + id, {
		type: "PUT",
		dataType: "json", // 응답 데이터
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("회원수정 완료");
			location.reload(); // 새로고침(f5) 과 같은 기능
		} else {
			alert("업데이트에 실패하였습니다.");
		}
	});
}