<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp" %>
<sec:authentication property="principal" var="pinfo"/>

<div class="container-fluid">
	<h1 class="h3 mb-2 text-gray-800">Board Read</h1>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
        	<h6 class="m-0 font-weight-bold text-primary">Board Read Page</h6>
        </div>
        <div class="card-body">
        	<form role="form" action="/board/modify" method="post">
	        	<div class="form-group">
	       			<label>Bno</label>
	       			<input class="form-control" name='bno' value='<c:out value="${board.bno }"/>' readonly="readonly">
	       		</div>
	       		<div class="form-group">
	       			<label>Title</label>
	       			<input class="form-control" name='title' value='<c:out value="${board.title }"/>'>
	       		</div>
	       		<div class="form-group">
	       			<label>Text area</label>
	       			<textarea class="form-control" rows="3" name='content'><c:out value="${board.content }"/></textarea>
	       		</div>
	       		<div class="form-group">
	       			<label>Writer</label>
	       			<input class="form-control" name='writer' value='<c:out value="${board.writer }"/>' readonly="readonly">
	       		</div>
	       		<div class="form-group">
	       			<label>RegDate</label>
	       			<input class="form-control" name='regDate' value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.regdate }"/>' readonly="readonly">
	       		</div>
	       		<div class="form-group">
	       			<label>Update Date</label>
	       			<input class="form-control" name='updateDate' value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate }"/>' readonly="readonly">
	       		</div>
	       		
	       		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
	       		<sec:authorize access="isAuthenticated()">
		       		<c:if test="${pinfo.username eq board.writer }">
			       		<button type="submit" class="btn btn-primary btn-icon-split">
			       			<span class="text">Modify</span>
			       		</button>
			       		<button type="submit" id='remove' class="btn btn-danger btn-icon-split">
			       			<span class="text">Remove</span>
			       		</button>
		       		</c:if>
	       		</sec:authorize>
	       		<button type="submit" id='list' class="btn btn-info btn-icon-split">
	       			<span class="text">List</span>
	       		</button>
	       	</form>
        </div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	
	var formObj=$("form");
	
	$('#remove').on("click",function(e){		
		formObj.attr("action","/board/remove");
		formObj.submit();
	});
	
	$('#list').on("click",function(e){
		formObj.attr("action","/board/list").attr("method","get");
		formObj.empty();
		formObj.submit();
	});
});
</script>

<%@include file="../includes/footer.jsp" %>
