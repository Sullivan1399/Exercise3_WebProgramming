package vn.ntkiet.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.ntkiet.configs.JPAConfig;
import vn.ntkiet.dao.IVideoDao;
import vn.ntkiet.entity.Video;

public class VideoDaoImpl implements IVideoDao{

	@Override
    public void insert(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            enma.persist(video);
            trans.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally
        {
            enma.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            enma.merge(video);
            trans.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally
        {
            enma.close();
        }
    }

    @Override
    public void delete(int videoId) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try
        {
            trans.begin();
            Video video = enma.find(Video.class, videoId);
            enma.remove(video);
            trans.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally
        {
            enma.close();
        }
    }

    @Override
    public List<Video> findTitle(String keyword) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :videoTitle";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setParameter("videoTitle", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public void updateStatus(int videoId, int status) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try{
            trans.begin();
            Video video = enma.find(Video.class, videoId);
            video.setActive(status);
            enma.merge(video);
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
    public Video findById(int videoId) {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.find(Video.class, videoId);
    }

    @Override
    public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findAll(int page, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(v) FROM Video v";
        TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
        return query.getSingleResult().intValue();
    }
}
