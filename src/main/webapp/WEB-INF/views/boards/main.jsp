<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br/>
	<div class="d-flex justify-content-end">
		<div style="width: 300px">
			<form class="d-flex" method="get" action="/">
				<input class="form-control me-2" type="text" placeholder="Search" name="keyword">
				<button class="btn btn-primary" type="submit">Search</button>
			</form>
		</div>
	</div>



	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="boards" items="${pagingDto.mainDtos}">
				<tr>
					<td>${boards.id}</td>
					<td><a href="/boards/${boards.id}">${boards.title}</a></td>
					<td>${boards.username}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



	<div class="d-flex justify-content-center">
		<ul class="pagination">
			<li class='page-item ${pagingDto.first ? "disabled" : ""}'><a class="page-link"
				href="?page=${pagingDto.currentPage-1}&keyword=${pagingDto.keyword}">Prev</a></li>

			<c:forEach var="num" begin="${pagingDto.startPageNum}" end="${pagingDto.lastPageNum}" step="1">
				<li class='page-item ${pagingDto.currentPage == num-1 ? "active" : ""}'><a class="page-link"
					href="?page=${num-1}&keyword=${pagingDto.keyword}">${num}</a></li>
			</c:forEach>

			<li class='page-item ${pagingDto.last ? "disabled" : ""}'><a class="page-link"
				href="?page=${pagingDto.currentPage+1}&keyword=${pagingDto.keyword}">Next</a></li>
		</ul>
	</div>

<%-- <div style="background-color: 'grey';">
		<h3>blockCount : ${pagingDto.blockCount}</h3>
		<h3>currentBlock : ${pagingDto.currentBlock}</h3>
		<h3>startPageNum : ${pagingDto.startPageNum}</h3>
		<h3>lastPageNum : ${pagingDto.lastPageNum}</h3>
		<h3>totalCount : ${pagingDto.totalCount}</h3>
		<h3>totalPage : ${pagingDto.totalPage}</h3>
		<h3>currentPage : ${pagingDto.currentPage}</h3>
		<h3>isLast : ${pagingDto.last}</h3>
		<h3>isFirst : ${pagingDto.first}</h3>
	</div> --%>

</div>

<%@ include file="../layout/footer.jsp"%>

