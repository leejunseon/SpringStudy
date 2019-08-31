<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@include file="../includes/header.jsp" %>
<sec:authentication property="principal" var="pinfo"/>

<div class="container-fluid">
	<h1 class="h3 mb-2 text-gray-800">Board Read</h1>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
        	<h6 class="m-0 font-weight-bold text-primary">Board Read Page</h6>
        </div>
        <div class="card-body">
        	<div class="form-group">
       			<label>Bno</label>
       			<input class="form-control" name='bno' value='<c:out value="${board.bno }"/>' readonly="readonly">
       		</div>
       		<div class="form-group">
       			<label>Title</label>
       			<input class="form-control" name='title' value='<c:out value="${board.title }"/>' readonly="readonly">
       		</div>
       		<div class="form-group">
       			<label>Text area</label>
       			<textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value="${board.content }"/></textarea>
       		</div>
       		<div class="form-group">
       			<label>Writer</label>
       			<input class="form-control" name='writer' value='<c:out value="${board.writer }"/>' readonly="readonly">
       		</div>
       		
       		<sec:authorize access="isAuthenticated()">
	       		<c:if test="${pinfo.username eq board.writer }">
		       		<button data-oper='modify' class="btn btn-primary btn-icon-split">
		       			<span class="text">Modify</span>
		       		</button>
	       		</c:if>
       		</sec:authorize>
       		<button data-oper='list' class="btn btn-info btn-icon-split">
       			<span class="text">List</span>
       		</button>
       		     		
       		<form id='operForm' action="/board/modify" method="get">
       			<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno }"/>'>
       		</form>
        </div>
	</div>
	<div class="card shadow mb-4">
		<div class="card-header py-3">
        	<i class="fa fa-comments fa-fw"></i>Reply
        </div>
         <div class="card-body">
			<sec:authorize access="isAuthenticated()">
				<div class="form-group">
					<textarea class="form-control" id='reply' rows="3" placeholder="Leave comments here"></textarea>
				</div>
				<button id='replyRegisterBtn' type="submit" class="btn btn-success btn-icon-split fa-pull-right">
					<span class="text"><i class="fa fa-share"></i> Register</span>
				</button>
			</sec:authorize>
		</div>
		<hr>
        <div class="card-body">
        	<ul class="chat" style="list-style-type:none; padding-left:15px;">
        	</ul>
        	<div class="panel-footer">
        	</div>
        </div>
    </div>
</div>
 
<script src="https://momentjs.com/downloads/moment.js"></script>
<script type="text/javascript" src="/resources/js/reply.js?ver=1"></script>
<script type="text/javascript">
$(document).ready(function(){

	var operForm=$("#operForm");
	var bnoValue='<c:out value="${board.bno}"/>';
	
	showList(1);
	
	$("button[data-oper='modify']").on("click",function(e){
		operForm.attr("action","/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click",function(e){
		operForm.find("#bno").remove();
		operForm.attr("action","/board/list");
		operForm.submit();
	});	
	
	var replyer=null;
    <sec:authorize access="isAuthenticated()">
    	replyer='<sec:authentication property="principal.username"/>';
    </sec:authorize>
	$("#replyRegisterBtn").on("click",function(e){
		var reply={
				reply:$("#reply").val(),
				replyer:replyer,
				bno:bnoValue,
				csrf_header:"${_csrf.headerName}",
				csrf_token:"${_csrf.token }"
				};
		
		replyService.add(
			reply
			,
			function(result){
				alert(result);
				$("#reply").val("");
				showList(-1);
			}
		);
	});
	
	replyPageFooter.on("click","li a",function(e){
		e.preventDefault();
		console.log("page click");
		
		var targetPageNum=$(this).attr("href");
		console.log("targetPageNum: "+targetPageNum);
		pageNum=targetPageNum;
		showList(pageNum);
	});
	
});


var bnoValue='<c:out value="${board.bno}"/>';
var replyUL=$(".chat");
var replyer=null;
<sec:authorize access="isAuthenticated()">
	replyer='<sec:authentication property="principal.username"/>';
</sec:authorize>
function showList(page){
	console.log("show replies "+page);
	replyService.getReplies(
		{bno:bnoValue,page:page||1}
		,
		function(replyCnt,list){
			var replyCnt=replyCnt;
			var list=list;
			
			console.log("replyCnt: "+replyCnt);
			console.log("replies:");
			console.log(list);
			
			if(page==-1){
				pageNum=Math.ceil(replyCnt/10.0);
				showList(pageNum);
				return;
			}
			
			var str="";
			if(list==null||list.length==0){
				replyUL.html("");
				return;
			}
			for(var i=0,len=list.length||0;i<len;i++){
				var date=displayTime(list[i].replyDate);
				str+="<li id='"+list[i].rno+"'>";
				str+="<div><div class='header'>";
				str+="<strong class='primary-font'>"+list[i].replyer+"</strong>";
				if(list[i].replyer==replyer){
					str+="<a href='javascript:void(0)' onclick='editReply("+list[i].rno+",\""+list[i].replyer+"\",\""+list[i].reply+"\")' style='padding-left:15px'>수정</a>";
					str+="<a href='javascript:void(0)' onclick='removeReply("+list[i].rno+")' style='padding-left:15px;'>삭제</a>";
				}
				str+="<small class='fa-pull-right text-muted'>"+date+"</small></div>";
				str+="<p>"+list[i].reply+"</p><hr></div></li>";
			}
			replyUL.html(str);
			showReplyPage(replyCnt);
		}
	);
}

//댓글 페이징
var pageNum=1;
var replyPageFooter=$(".panel-footer");
function showReplyPage(replyCnt){
	var endNum=Math.ceil(pageNum/10.0)*10;
	var startNum=endNum-9;
	var prev=startNum!=1;
	var next=false;
	
	if(endNum*10>=replyCnt){
		endNum=Math.ceil(replyCnt/10.0);
	}
	
	if(endNum*10<replyCnt){
		next=true;
	}
	
	var str="<ul class='pagination fa-pull-right'>";
	
	if(prev){
		str+="<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>";
	}
	
	for(var i=startNum;i<=endNum;i++){
		var active=pageNum==i?"active":"";
		
		str+="<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
	}
	
	if(next){
		str+="<li class='page-item "+active+" '><a class='page-link' href='"+(endNum+1)+"'>"+Next+"</a></li>";
	}
	
	str+="</ul></div>";
		
	replyPageFooter.html(str);	
}

function displayTime(time){
	var today=new Date();
	var gap=today.getTime()-time;
	var dateObj=new Date(time);
			
	if(gap<(1000*60*60*24)){
		return moment(time).format('h:mm:ss a');
	}else{
		return moment(time).format('YYYY-MM-DD');
	}
}

function editReply(rno,writer,content){
	var str="";
	var reply=$("#"+rno);
	
	str+="<li id='"+rno+"'>";
	str+="<div class='header'>";
	str+="<strong class='primary-font'>"+writer+"</strong>";
	str+="<a href='javascript:void(0)' onclick='updateReply("+rno+")' style='padding-left:15px;'>저장</a>";
	str+="<a href='javascript:void(0)' onclick='showList(1)' style='padding-left:15px;'>취소</a>";
	str+="<div class='form-group'><textarea id='updated' class='form-control'>"+content+"</textarea></div><hr></div></li>";
	
	reply.html(str);
}

function removeReply(rno){
	var reply={
		rno:rno,
		csrf_header:"${_csrf.headerName}",
		csrf_token:"${_csrf.token }"
	};
	replyService.remove(
		reply
		,
		function(result){
			alert(result);
			$("#reply").val("");
			showList(1);
		}
	);
}

function updateReply(rno){
	var reply={
		rno:rno,
		reply:$("#updated").val(),
		bno:bnoValue,
		csrf_header:"${_csrf.headerName}",
		csrf_token:"${_csrf.token }"
	};
	replyService.update(
		reply
		,
		function(result){
			alert(result);
			$("#reply").val("");
			showList(1);
		}
	);
}

</script>
<%@include file="../includes/footer.jsp" %>

