/* 

*/

let isUsernameSameCheck = false;


// 회원가입
$("#btnJoin").click(() => {
	joinValidate();
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




function join() {

	if (isUsernameSameCheck == false) {
		alert("유저네임 중복체크를 해주세요")
		return;
	}


	if (koreanCheck() == true) {
		alert("유저네임에 한글이 있으면 안됩니다.")
		return;
	}

	if (capitalCheck() == false) {
		alert("유저네임에 대문자가 최소 하나는 있어야 합니다.")
		return;
	}

	if (spaceCheck() == true) {
		alert("공백은 입력 할 수 없습니다.")
		return;
	}


	if (emailCheck() == false) {
		alert("이메일을 잘못 입력하셨습니다.")
		return;
	}


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



	$.ajax("/api/join", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/loginForm";
		}else{
			alert(res.msg);
			history.back;
		}
	});
}


function checkSame() {

	// body 데이터가 없으니 json으로 날릴 필요가 없음 (json은 바디 데이터를 날리는데에 사용)

	// 1. 사용자가 적은 username값 가져오기
	let username = $("#username").val();

	// 2. Ajax 통신, DB에 해당 username값이 있는지 확인
	$.ajax(`/api/users/usernameSameCheck?username=${username}`, {
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



function login() {
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		remember: $("#remember").prop("checked")
	};

	$.ajax("/api/login", {
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


function resign() {
	let id = $("#id").val();

	$.ajax("/s/api/users/" + id + "/delete", {
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


function update() {
	let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};
	let id = $("#id").val();

	$.ajax("/s/api/users/" + id, {
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

function joinValidate(){
	koreanCheck();
	upperCheck();
	emailCheck();
	spaceCheck();
}
/*	
	
	

	 */

function koreanCheck() {
	let username = $("#username").val();
	let password = $("#password").val();
	let email = $("#email").val();

	var korRule = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	if (korRule.test(username)) {
		return true;
	}
	
	if (korRule.test(password)) {
		return true;
	}

	if (korRule.test(email)) {
		return true;
	}else {
		return false;
	}
}


function upperCheck() {
	let username = $("#username").val();
	var upperRule = /[A-Z]/;
	if (upperRule.test(username)) {
		return true;
	} else {
		return false;
	}
}


function emailCheck() {
	let email = $("#email").val();
	var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if (emailRule.test(email)) {
		return true;
	} else {
		return false;
	}
}



function spaceCheck() {
	let username = $("#username").val();
	let password = $("#password").val();
	let email = $("#email").val();

	var spaceRule = /[ ]/;
	if (spaceRule.test(username)) {
		return true;
	}

	if (spaceRule.test(password)) {
		return true;
	}
	
	if (spaceRule.test(email)) {
		return true;
	} else {
		return false;
	}
}


/* function spaceCheck() {
	let data = {
	username: $("#username").val(),
	password: $("#password").val(),
	email: $("#email").val()
	};
	
	var spaceRule = /[ ]/;
	
} */