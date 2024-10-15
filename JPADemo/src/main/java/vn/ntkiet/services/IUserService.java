package vn.ntkiet.services;

import vn.ntkiet.entity.User;

public interface IUserService {
	User checkLogin(String username, String password);
	User findByUsername(String username);
	void insertUser(User user);
    boolean registerUser(String username, String password, String email, int roleId, String phone);
    User checkExistUser(String username);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
    void updatePassword(String username, String password);
    void updateAccount(String username, String fullname, String phone);
    void updateFile(String username, String images);
}



