<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="board_wrap">
		<div class="board_title">
			<strong>자유게시판</strong>
			<p>자유게시판입니다.</p>
		</div>
		<div class="board_view_wrap"> 
			<div class="board_view">
				<div class="title">${board.title}</div>
				<div class="info" style="position: relative;">
					<dl>
						<dt>번호</dt>
						<dd>${board.board_no}</dd>
					</dl>
					<dl>
						<dt>글쓴이</dt>
						<dd>${board.user_id}</dd>
					</dl>
					<dl>
						<dt>작성일</dt>
						<dd>${board.reg_date}</dd>
					</dl>
					<dl>
						<dt>조회</dt>
						<dd>${board.views}</dd>
					</dl>
					<dl style="position: absolute; right: 0;">
						<dt><a onclick="chkDelete(${board.board_no}); return false;">삭제하기</a></dt>
					</dl>
				</div>
				<!-- 
				white-space: 공백, 줄바꿈 규칙 적용 (작성한 본문 내용에 대해 줄바꿈 적용)
				- 속성: pre-wrap(작성한 그대로 출력), nowrap(텍스트 일자로 출력), normal 등
				- white-space:pre-wrap: div 영역 안에서 감싸면서 줄바꿈, 공백 적용 
				-->
				<div class="cont" style="white-space:pre-wrap;">${board.content}</div>
				<c:if test="${board.img != null}">
					<div class="cont"><img src="${board.img}" alt="업로드 이미지"></div>
				</c:if>			
			</div>	
			<div class="bt_wrap">
				<a href="index" class="on">목록</a>
				<a href="edit?board_no=${board.board_no}">수정</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="./js/script.js"></script>
</body>
</html>