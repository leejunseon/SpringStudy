<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Register</title>

  <!-- Custom fonts for this template-->
  <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
          <div class="col-lg-7">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
              </div>
              
              <form:form modelAttribute="memberVO" class="user" method='post' action="/member/memberRegister">
                <div class="form-group">
					<form:input path="userName" type="text" class="form-control form-control-user" placeholder="Name"/>
					<form:errors path="userName" class="text-xs font-weight-bold text-danger col-auto"/>
                </div>
                <div class="form-group">
                	<form:input path="userid" type="text" class="form-control form-control-user" placeholder="ID"/>
					<form:errors path="userid" class="text-xs font-weight-bold text-danger col-auto"/>
                </div>
                <div class="form-group">
                	<form:input path="email" type="text" class="form-control form-control-user" placeholder="Email"/>
					<form:errors path="email" class="text-xs font-weight-bold text-danger col-auto"/>
                </div>
                <div class="form-group row">
	                <div class="col-sm-6">
	                	<form:input path="userpw" type="password" class="form-control form-control-user" placeholder="Password"/>
						<form:errors path="userpw" class="text-xs font-weight-bold text-danger col-auto"/>
	                </div>
	                <div class="col-sm-6">
	                    <input id="repeatpw" name="repeatpw" type="password" class="form-control form-control-user" placeholder="Repeat Password">
	                    <span id="repeat" class="text-xs font-weight-bold text-danger col-auto"></span>
	                </div>
                </div>
                
                <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
                <button type="submit" id="submit" class="btn btn-primary btn-user btn-block">
                  Register Account
                </button>
                <!-- <hr>
                <a href="index.html" class="btn btn-google btn-user btn-block">
                  <i class="fab fa-google fa-fw"></i> Register with Google
                </a>
                <a href="index.html" class="btn btn-facebook btn-user btn-block">
                  <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                </a> -->
              </form:form>
              
              <hr>
              <div class="text-center">
                <a class="small" href="/member/findPassword">Forgot Password?</a>
              </div>
              <div class="text-center">
                <a class="small" href="/customLogin">Already have an account? Login!</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="/resources/js/sb-admin-2.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var result='<c:out value="${result}"/>';
	if(result!==''){
		alert(result);	
	}
})

$("#repeatpw").on("propertychange change keyup paste input", function() {
    var repeat=$(this).val();
    var pw=$("input[name=userpw]").val();
    
    if(pw!=repeat){
    	$("#repeat").html("비밀번호가 일치하지 않습니다.");
    }else{
    	$("#repeat").html("");
    }
});

</script>

</body>

</html>
