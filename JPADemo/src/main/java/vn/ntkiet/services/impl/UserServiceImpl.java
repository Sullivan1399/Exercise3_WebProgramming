package vn.ntkiet.services.impl;

import vn.ntkiet.configs.JPAConfig;
import vn.ntkiet.dao.CheckLogin;
import vn.ntkiet.dao.IUserDao;
import vn.ntkiet.dao.impl.UserDaoImpl;
import vn.ntkiet.entity.User;
import vn.ntkiet.services.IUserService;

public class UserServiceImpl implements IUserService {
	// Lấy toàn bộ hàm trong tầng Dao của user
	IUserDao userDao = new UserDaoImpl();

	@Override
	public User checkLogin(String username, String password) {
		CheckLogin checkLogin = new CheckLogin();
		if (checkLogin.checkLogin(username, password)) {
			return userDao.findByUsername(username);
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void insertUser(User User) {
		userDao.insert(User);
	}

	@Override
	public boolean registerUser(String username, String password, String email, int roleId, String phone) {
		if (checkExistUser(username).getUserId() != 0 || checkExistEmail(email) || checkExistPhone(phone)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setRoleId(roleId);
        newUser.setPhone(phone);

        insertUser(newUser);
		return true;

	}

	@Override
	public User checkExistUser(String username) {
        return userDao.findByUsername(username);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmails(email);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		 return userDao.checkExistPhones(phone);
	}

	@Override
	public void updatePassword(String username, String password) {
		userDao.updatePassword(username, password);
	}

	public static void main(String[] args) {
		IUserService userService = new UserServiceImpl();
		User user = userService.checkLogin("admin", "123");
		if (user != null) {
			System.out.println("Đăng nhập thành công với tài khoản: " + user.getUsername());
		} else {
			System.out.println("Đăng nhập thất bại");
		}
		
//		User user2 = userService.findByUsername("guest");
//		if (user2.getId() != 0)
//			System.out.println(user2);
//		else
//			System.out.println("false");
//		String username, String password, String fullname, String email, String image, int roleId, String phone
		if (userService.registerUser("ntk", "123", "ntk@gmail.com", 1, "0989821812"))
			System.out.println("true");
		else
			System.out.println("false");
	}

	public void updateAccount(String username, String fullname, String phone) {
		userDao.updateAccount(username, fullname, phone);
	}

	@Override
	public void updateFile(String username, String images) {
		userDao.updateFile(username, images);
		
	}
}
