package vn.ntkiet.dao;

import java.util.List;

import vn.ntkiet.entity.Video;

public interface IVideoDao {
	void insert(Video video);
    void update(Video video);
    void delete(int videoId);
    List<Video> findTitle(String keyword);
    void updateStatus(int videoId, int status);
    Video findById(int videoId);
    List<Video> findAll();
    List<Video> findAll(int page, int pageSize);
    int count();
}
