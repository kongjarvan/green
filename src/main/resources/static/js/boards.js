$("#btnWrite").click(() => {
	write();
})


$("#btnUpdateBoards").click(() => {
	updateBoards();
})


$("#btnDeleteBoards").click(() => {
	deleteBoards();
})


// 하트 아이콘을 클릭했을때의 로직
$("#iconLove").click(() => {
	let isLovedState = $("#iconLove").hasClass("fa-solid");
	if (isLovedState) {
		deleteLove();
	} else {
		insertLove();
	}
});


//-------------------function---------------------//




function write() {
	let data = {
		title: $("#title").val(),
		content: $("#content").val(),

	};

	$.ajax("/s/api/boards", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/";
		}
	});
}


function updateBoards() {
	let data = {
		title: $("#title").val(),
		content: $("#content").val(),
	};
	let id = $("#id").val();
	let keyword = $("#keyword").val();
	let page = $("#page").val();


	$.ajax("/s/api/boards/" + id + "/update", {
		type: "PUT",
		dataType: "json", // 응답 데이터
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("게시글 수정 완료");
			location.href = "/?page=" + page + "&keyword=" + keyword;   // 새로고침(f5) 과 같은 기능
		} else {
			alert("수정에 실패하였습니다.");
		}
	});
}


function deleteBoards() {
	let id = $("#id").val();
	let keyword = $("#keyword").val();
	let page = $("#page").val();

	$.ajax("/s/api/boards/" + id + "/delete", {
		type: "DELETE",
		dataType: "json" // 응답 데이터
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/?page=" + page + "&keyword=" + keyword;
			//document.referer로 하는 방법도 있음. 
		} else {
			alert("삭제에 실패하였습니다.");
		}
	});
}


// DB에 insert 요청하기
function insertLove() {
	let id = $("#id").val();

	$.ajax("/s/api/boards/" + id + "/loves", {
		type: "POST",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			renderLoves();
			let count = $("#countLove").text();
			$("#countLove").text(Number(count) + 1);
			$("#lovesId").val(res.data.id);
		} else {
			alert(res.msg);
		}
	});
}


// DB에 delete 요청하기
function deleteLove() {
	let id = $("#id").val();
	let lovesId = $("#lovesId").val();

	$.ajax("/s/api/boards/" + id + "/loves/" + lovesId, {
		type: "DELETE",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			renderCancelLoves();
			let count = $("#countLove").text();
			$("#countLove").text(Number(count) - 1); // 부분리로딩
		} else {
			alert(res.msg);
		}
	});
}



function renderLoves() {
	$("#iconLove").removeClass("fa-regular");
	$("#iconLove").addClass("fa-solid");
}



function renderCancelLoves() {
	$("#iconLove").removeClass("fa-solid");
	$("#iconLove").addClass("fa-regular");
}