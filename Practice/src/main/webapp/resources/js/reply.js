console.log("Reply Module");

var replyService=(function(){
	
	function add(reply,callback){//reply,callback을 파라미터로 받음
		console.log("add reply");
		
		$.ajax({
			type:'post',
			url:'/replies/new',
			data:JSON.stringify(reply),
			contentType:"application/json;charset=utf-8",
			success:function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr,status,error){
				if(error){
					error(error);
				}
			}
		});
	}
	
	function getList(param,callback,error){
		console.log("get replies");
		
		$.ajax({
			type:'get',
			url:'/replies/pages/'+param.bno+'/'+param.page,
			dataType:'json',
			success:function(result){
				if(callback)
					callback(result);
			},
			error:function(xhr,status,error){
				if(error){
					error(error);
				}
			}
		});
	}
	
	return {
		add:add,
		getList:getList
	};
})();