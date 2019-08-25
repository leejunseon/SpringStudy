<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="java.util.*" %>
   
<%@include file="includes/header.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid">
        
          <!-- 404 Error Text -->
          <div class="text-center">
            <div class="error mx-auto" data-text="Access Denied">Access Denied</div>
            <p class="lead text-gray-800 mb-5"><c:out value="${msg }"/></p>
            <p class="text-gray-500 mb-0">It looks like you found a glitch in the matrix...</p>
            <a href="/board/list">&larr; Back to Dashboard</a>
          </div>

        </div>
        <!-- /.container-fluid -->

<%@include file="includes/footer.jsp" %>
