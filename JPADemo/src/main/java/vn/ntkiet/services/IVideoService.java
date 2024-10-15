package vn.ntkiet.services;

import java.util.List;

import vn.ntkiet.entity.Video;

public interface IVideoService {
	 List<Video> findAll();
	 Video findById(int videoId);
	 void insert(Video video);
	 void update(Video video);
	 void delete(int videoId);
	 List<Video> findTitle(String keyword);
	 void updateStatus(int videoId, int status);
}
