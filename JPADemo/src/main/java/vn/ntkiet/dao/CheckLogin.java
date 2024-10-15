package vn.ntkiet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.ntkiet.configs.JPAConfig;
import vn.ntkiet.dao.impl.UserDaoImpl;
import vn.ntkiet.entity.User;

public class CheckLogin extends JPAConfig{

	public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    public boolean checkLogin(String username, String password) {
    	EntityManager enma = JPAConfig.getEntityManager();
        try {
            if (username == null || password == null) {
                throw new Exception("Username or password is null.");
            }

            // Tạo truy vấn JPA để kiểm tra người dùng theo tên đăng nhập và mật khẩu
            String jpql = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            // Kiểm tra xem truy vấn có trả về kết quả nào không
            List<User> result = query.getResultList();
            if (!result.isEmpty()) {
                return true;  // Người dùng tồn tại và mật khẩu đúng
            } else {
                return false;  // Không tìm thấy người dùng hoặc mật khẩu sai
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            enma.close();
        }

    }

    public static void main(String[] args) {
        CheckLogin checkLogin = new CheckLogin();
        System.out.println(checkLogin.checkLogin("NTK", "123"));
    }
}
