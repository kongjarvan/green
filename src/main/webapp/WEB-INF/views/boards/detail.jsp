<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<input id="page" type="hidden" value="${search.currentPage}">
<input id="keyword" type="hidden" value="${search.keyword}">
<div class="container">
	<br /> <br /> 
	<input id="id" type="hidden" value="${detailDto.id}" /> 
	<input id="lovesId" type="hidden" value="${detailDto.lovesId}" />
	<div class="d-flex">
		<c:if test="${!empty sessionScope.principal}">
			<a href="/s/boards/${detailDto.id}/updateForm" class="btn btn-warning">수정하러가기</a>
			<form>
				<button id="btnDeleteBoards" class="btn btn-danger">삭제</button>
			</form>
		</c:if>
	</div>
	<br />
	<div class="d-flex justify-content-between">
		<h3>${detailDto.title}</h3>
		<div>
			좋아요수 : <span id="countLove">${detailDto.loveCount}</span> <i id="iconLove"
				class='${detailDto.loved ? "fa-solid" : "fa-regular"} fa-heart my_pointer my_red'></i>
		</div>
	</div>
	<hr />

	<div>${detailDto.content}</div>
</div>

<script src="/js/boards.js">
	
</script>


<script>
	
</script>

<%@ include file="../layout/footer.jsp"%>
