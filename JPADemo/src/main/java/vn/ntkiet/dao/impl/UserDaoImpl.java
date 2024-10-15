package vn.ntkiet.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import vn.ntkiet.configs.JPAConfig;
import vn.ntkiet.dao.ICategoryDao;
import vn.ntkiet.dao.IUserDao;
import vn.ntkiet.entity.Category;
import vn.ntkiet.entity.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User findByID(int id) {
		try {
			EntityManager enma = JPAConfig.getEntityManager();
			User user = enma.find(User.class, id);
			return user;
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public User findByUsername(String username) {
		try{
            EntityManager enma = JPAConfig.getEntityManager();
            TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, id);
			if (user != null) {
				enma.remove(user);
			} else {
				throw new Exception("Not found!");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void insertRegister(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkExistEmails(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
		query.setParameter("email", email);
		List<User> user = query.getResultList();
		return !user.isEmpty();
	}

	@Override
	public boolean checkExistPhones(String phone) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createQuery("SELECT u FROM User u WHERE u.phone = :phone", User.class);
		query.setParameter("phone", phone);
		List<User> users = query.getResultList();
		return !users.isEmpty();
	}

	@Override
	public void updatePassword(String username, String password) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, username);
			if (user != null) {
				user.setPassword(password);
				enma.merge(user);
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
		List<User> users = userDao.findAll();

		for (User user : users) {
			System.out.println(user);
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input Username: ");
		String x = scanner.next();
		User user = userDao.findByUsername(x);
		System.out.println(user);
	}

	@Override
	public void updateAccount(String username, String fullname, String phone) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, username);
			if (user != null) {
				user.setFullName(fullname);
				user.setPhone(phone);
				enma.merge(user);
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void updateFile(String username, String image) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, username);
			if (user != null) {
				user.setImage(image);
				enma.merge(user);
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Long> query = enma.createQuery("SELECT COUNT(u) FROM User u", Long.class);
		return query.getSingleResult().intValue();
	}
}
