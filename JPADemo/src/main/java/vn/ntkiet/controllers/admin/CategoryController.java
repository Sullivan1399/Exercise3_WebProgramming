package vn.ntkiet.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.ntkiet.entity.Category;
import vn.ntkiet.services.ICategoryService;
import vn.ntkiet.services.impl.CategoryServiceImpl;
import vn.ntkiet.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/edit", "/admin/category/update", "/admin/category/insert", 
		"/admin/category/add", "/admin/category/delete"})
public class CategoryController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public ICategoryService catService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("categories")) {
			List<Category> list = catService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/category/edit")) {
			int id = Integer.parseInt(req.getParameter("categoryId"));
			Category category = catService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/category/delete")) {
			int id = Integer.parseInt(req.getParameter("categoryId"));
			try {
				catService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/admin/category/update")) {
			int catId = Integer.parseInt(req.getParameter("categoryId"));
			String catName = req.getParameter("categoryName");
			int status = Integer.parseInt(req.getParameter("status"));
			
			Category category = new Category();
			category.setCategoryId(catId);
			category.setCategoryName(catName);
			category.setStatus(status);
			
			// Lưu hình cũ
			Category cateOld = catService.findById(catId);
			String fileOld = cateOld.getImages();
			// Xử lý images
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// Upload file
					part.write(uploadPath + "/" + fname);
					// Ghi tên file vào data
					category.setImages(fname);
				} else {
					category.setImages(fileOld);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			catService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
		else if (url.contains("/admin/category/insert")) {
			String catName = req.getParameter("categoryName");
			int status = Integer.parseInt(req.getParameter("status"));
			
			Category category = new Category();
			category.setCategoryName(catName);
			category.setStatus(status);
			
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// Upload file
					part.write(uploadPath + "/" + fname);
					// Ghi tên file vào data
					category.setImages(fname);
				} else {
					category.setImages(
							"https://cdn.tuoitre.vn/471584752817336320/2024/7/2/z559539615845014287a04412880cfc0fdbbc3e030d19a-1719920965742739381220.jpg");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			catService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}