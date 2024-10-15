<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="<c:url value='/admin/video/update'></c:url>" method="post" enctype="multipart/form-data">

  <!-- Trường hidden để lưu videoId -->
  <input type="text" id="videoId" name="videoId" hidden="hidden" value="${video.videoId}">

  <!-- Nhập tiêu đề video -->
  <label for="title">Video Title:</label><br>
  <input type="text" id="title" name="title" value="${video.title}"><br>

  <!-- Nhập mô tả video -->
  <label for="description">Description:</label><br>
  <textarea id="description" name="description">${video.description}</textarea><br>

  <!-- Tải lên poster -->
  <label for="poster">Poster:</label><br>
  <input type="file" onchange="chooseFile(this)" id="poster" name="poster"><br/>

  <!-- Hiển thị poster hiện tại -->
  <c:if test="${video.poster.substring(0, 5) == 'https'}">
    <c:url value="${video.URL}" var="poster"></c:url>
  </c:if>

  <c:if test="${video.poster.substring(0, 5) != 'https'}">
    <c:url value="/image?fname=${video.poster}" var="poster"></c:url>
  </c:if>

  <img id="posters" height="150" width="200" src="${poster}" /><br/>

  <!-- Tải lên file video -->
  <label for="video">Video File:</label><br>
  <input type="file" id="video" name="video"><br/><br/>

  <!-- Hiển thị trạng thái video (Active / Inactive) -->
  <input type="radio" id="active" name="active" value="1" ${video.active == 1 ? 'checked' : ''}>
  <label for="active">Active</label><br>

  <input type="radio" id="inactive" name="active" value="0" ${video.active == 0 ? 'checked' : ''}>
  <label for="inactive">Inactive</label><br>

  <!-- Nút submit để cập nhật video -->
  <input type="submit" value="Edit">
</form>