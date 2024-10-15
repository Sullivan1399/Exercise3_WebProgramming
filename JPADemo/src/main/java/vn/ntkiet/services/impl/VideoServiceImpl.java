package vn.ntkiet.services.impl;

import java.util.List;

import vn.ntkiet.dao.IVideoDao;
import vn.ntkiet.dao.impl.VideoDaoImpl;
import vn.ntkiet.entity.Video;
import vn.ntkiet.services.IVideoService;

public class VideoServiceImpl implements IVideoService {

	public IVideoDao videoDAO = new VideoDaoImpl();

    @Override
    public List<Video> findAll() {
        return videoDAO.findAll();
    }

    @Override
    public Video findById(int videoId) {
        return videoDAO.findById(videoId);
    }

    @Override
    public void insert(Video video) {
        videoDAO.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDAO.update(video);
    }

    @Override
    public void delete(int videoId) {
        videoDAO.delete(videoId);
    }

    @Override
    public List<Video> findTitle(String keyword) {
        return videoDAO.findTitle(keyword);
    }

    @Override
    public void updateStatus(int videoId, int status) {
        videoDAO.updateStatus(videoId, status);
    }

    public static void main(String[] args) {
        VideoServiceImpl videoService = new VideoServiceImpl();
        List<Video> videos = videoService.findAll();
        for (Video video : videos) {
            System.out.println(video);
        }
    }
}
