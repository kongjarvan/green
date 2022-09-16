<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/boards" method="post">
		<div class="mb-3 mt-3">
			<input id="title" type="text" class="form-control" placeholder="Enter title">
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8"></textarea>
		</div>
		<button id="btnWrite" type="button" class="btn btn-primary">글쓰기완료</button>
	</form>
</div>

<script>
$("#btnWrite").click(()=>{
	save();
})

function save(){
 	let data = {
 			title: $("#title").val(),
 			content: $("#content").val()
 		};

 		$.ajax("/boards", {
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
</script>


<script>
$('#content').summernote({
    height: 400
    });
</script>

<%@ include file="../layout/footer.jsp"%>

