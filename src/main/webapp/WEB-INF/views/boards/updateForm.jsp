<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input id="id" type="hidden" value="${boards.id}" /> 
		<input id="keyword" type="hidden"value="${search.keyword}" /> 
		<input id="page" type="hidden" value="${search.currentPage}" />

		<div class="mb-3 mt-3">
			<input id="title" type="text" class="form-control" placeholder="Enter title"
				value="${boards.title}" required="이 입력란을 작성하세요" maxlength="50">
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8" required="이 입력란을 작성하세요">${boards.content}</textarea>
		</div>
		<button id="btnUpdateBoards" type="button" class="btn btn-primary">수정완료</button>
	</form>
</div>

<script>
	$('#content').summernote({
		height : 400
	});
</script>

<script src="/js/boards.js">
	
</script>





<%@ include file="../layout/footer.jsp"%>

