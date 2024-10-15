<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<h2><a href="<c:url value="/admin/category/add"></c:url>">Add Category</a></h2>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>Category Name</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr>
			<td align="center">${STT.index+1}</td>
			<c:if test="${cate.images.substring(0,5) == 'https' }">
				<c:url value="${cate.images }" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.images.substring(0,5) != 'https' }">
				<c:url value="/image?fname=${cate.images }" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}"/></td>
			<td>${cate.categoryName }</td>
			<td>
			<c:if test="${cate.status == 1 }">
				<span>Active</span>
			</c:if>
			<c:if test="${cate.status != 1 }">
				<span>Inactive</span>
			</c:if>
			</td>
			<td><a
				href="<c:url value='/admin/category/edit?id=${cate.categoryId }'/>">Edit</a>
				| <a
				href="<c:url value='/admin/category/delete?id=${cate.categoryId }'/>">Delete</a></td>
		</tr>
	</c:forEach>
</table>