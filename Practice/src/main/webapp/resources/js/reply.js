console.log("Reply Module");

var replyService=(function(){
	
	function add(reply,callback){
		console.log("add reply");
		
		$.ajax({
			type:'post',
			url:'/replies/new',
			data:JSON.stringify(reply),
			contentType:"application/json;charset=utf-8",
			"beforeSend" : function(xhr){   
		 		//데이터를 전송하기 전에 헤더에 csrf값을 설정한다.
                xhr.setRequestHeader(reply.csrf_header, reply.csrf_token);
            },
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function getReplies(param,callback,error){
		console.log("get replies");
		
		$.ajax({
			type:'get',
			url:'/replies/pages/'+param.bno+'/'+param.page,
			dataType:'json',
			success:function(result){
				if(callback)
					callback(result.replyCnt,result.list);
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function remove(rno,callback,error){
		console.log("remove reply");
		
		$.ajax({
			type:'delete',
			url:'/replies/'+rno.rno,
			"beforeSend" : function(xhr){   
		 		//데이터를 전송하기 전에 헤더에 csrf값을 설정한다.
                xhr.setRequestHeader(rno.csrf_header, rno.csrf_token);
            },
			success:function(deleteResult,status,xhr){
				if(callback){
					callback(deleteResult);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function update(reply,callback,error){
		console.log("update reply");
		
		$.ajax({
			type:'put',
			url:'/replies/'+reply.rno,
			data:JSON.stringify(reply),
			"beforeSend" : function(xhr){   
		 		//데이터를 전송하기 전에 헤더에 csrf값을 설정한다.
                xhr.setRequestHeader(reply.csrf_header, reply.csrf_token);
            },
			contentType:'application/json; charset=utf-8',
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	return {
		add:add,
		getReplies:getReplies,
		remove:remove,
		update:update
	};
})();