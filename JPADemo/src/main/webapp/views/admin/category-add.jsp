<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
	<input type="text" id="categoryId" name="categoryId" hidden="hidden"
         value="${cate.categoryId}">
	<label for="categoryName">Category name:</label><br> 
	<input type="text" id="categoryName" name="categoryName"><br> 
	
    <label for="images">Images:</label><br>
    <input type="file" onchange="chooseFile(this)" id="images" name="images">
    <br/>
    <c:if test="${cate.images.substring(0,5)=='https'}">
     <c:url value="${cate.images}" var="imgUrl"></c:url>
    </c:if>

    <c:if test="${cate.images.substring(0,5)!='https'}">
      <c:url value="/image?fname=${cate.images}" var="imgUrl" />
    </c:if>

    <td><img id="imagess" height="150" width="200" src="${imgUrl}" /></td>
    <br/>
   
	<h3> Status</h3>
	<input type="radio" id="active" name="status" value="1" checked>
	<label for="active">Active</label><br> 
	<input type="radio" id="inactive" name="status" value="0">
	<label for="inactive">Inactive</label><br>
	<input type="submit" value="Insert">
</form>