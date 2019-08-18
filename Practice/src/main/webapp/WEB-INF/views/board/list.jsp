<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Tables</h1>
          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              	<h6 class="m-1 font-weight-bold text-primary fa-pull-left">Board List Page</h6>
              	<button id='regBtn' type="button" class="btn btn-secondary btn-icon-split btn-sm fa-pull-right">
            		<span class="icon text-white-50">
            			<i class="fas fa-arrow-right"></i>
            		</span>
            		<span class="text">Register New Board</span>
            	</button>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>#번호</th>
                      <th>제목</th>
                      <th>작성자</th>
                      <th>작성일</th>
                      <th>수정일</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>#번호</th>
                      <th>제목</th>
                      <th>작성자</th>
                      <th>작성일</th>
                      <th>수정일</th>
                    </tr>
                  </tfoot>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->
        
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  			<div class="modal-dialog" role="document">
  				<div class="modal-content">
     				<div class="modal-header">
        				<h5 class="modal-title">등록 완료</h5>
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
         					<span aria-hidden="true">&times;</span>
        				</button>
      				</div>
			 	<div class="modal-body">
			    	<p>처리가 완료되었습니다.</p>
			    </div>
      			<div class="modal-footer">
      				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        			<button type="button" class="btn btn-primary">Save changes</button>
      			</div>
    		</div>
  		</div>
	</div>

<script src="https://momentjs.com/downloads/moment.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var result='<c:out value="${result}"/>';
		
		checkModal(result);
		history.replaceState({},null,null);//register직후 아니면 모달 창 띄울 필요 없다는 표시
		
		//게시물 등록 시 Modal
		function checkModal(result){
			if(result===''||history.state){
				return;
			}
			
			if(parseInt(result)>0){
				$(".modal-body").html("게시글 "+parseInt(result)+" 번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		
		//Register button 이벤트
		$("#regBtn").on("click",function(){
			self.location="/board/register";
		});
		
		/* var csrfParameter=${_csrf.parameterName};
		var token=${_csrf.token}; */
		
		//dataTables 서버사이드 구현
		$('#dataTable').dataTable( {	
			"stateSave": true,
			"serverSide":true,
			"processing":true,
			"order": [],
			"pagingType":"full_numbers",
			"ajax":{
				"url":"/board/tableSetting",
				"type":"POST",
			 	"beforeSend" : function(xhr){   
			 		//데이터를 전송하기 전에 헤더에 csrf값을 설정한다.
                    xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
                },
				"dataSrc":function(res){
					var data=res.data;
					return data;
				}
			},
			//JSON 데이터를 데이터 그리드에 표시
			"columns":[
				{"data":"bno"},
				{"data": "title",
			        "render": function(data, type, row, meta){
						if(type === 'display'){
			            	data = '<a href="/board/get?bno=' + row.bno + '">' + data + '</a><b> ['+row.replyCnt+']</b>';
			        	}
			            return data;
			    	}
			    } ,
				{"data":"writer"},
				{"data":"regdate",type: 'date-dd-mmm-yyyy',targets: 0 },
				{"data":"updateDate"}
			],
			"columnDefs":[
				{"targets":[3,4], 
					render:function(data){
			      		return moment(data).format('YYYY-MM-DD');
			    	}
				},
			]
		} );
	});
	
	
</script>

<%@include file="../includes/footer.jsp" %>
