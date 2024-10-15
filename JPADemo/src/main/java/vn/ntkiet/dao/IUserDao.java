package vn.ntkiet.dao;

import java.util.List;

import vn.ntkiet.entity.User;

public interface IUserDao {

	List<User> findAll();
	User findByID(int id);
	User findByUsername(String username);
	void insert(User user);
	void update(User user);
	void delete(int id) throws Exception;
	void insertRegister(User user);
	boolean checkExistEmails(String email);
    boolean checkExistPhones(String phone);
    void updatePassword(String username, String password);
	void updateAccount(String username, String fullname, String phone);
	void updateFile(String username, String images);
	int count();
}
