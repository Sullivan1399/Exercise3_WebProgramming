<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<form action="<c:url value="/admin/category/update"></c:url>" method="post" enctype="multipart/form-data">
	<input type="text" id="categoryId" name="categoryId" value="${cate.categoryId}" hidden="hidden"><br>
	<label for="fname">Category name:</label><br> 
	<input type="text" id="categoryName" name="categoryName" value="${cate.categoryName}"><br>
	
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
	
	<label for="status">Status: </label><br>
	<input type="radio" id="active" name="status" value="1" ${cate.status==1?'checked':'' }>
	<label for="active">Active</label><br> 
	<input type="radio" id="inactive" name="status" value="0" ${cate.status!=1?'checked':'' }>
	<label for="inactive">Inactive</label><br>
	<input type="submit" value="Update"> 
	
</form>