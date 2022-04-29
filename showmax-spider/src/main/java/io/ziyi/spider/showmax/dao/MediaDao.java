package io.ziyi.spider.showmax.dao;

import io.ziyi.spider.showmax.model.Movie;
import io.ziyi.spider.showmax.model.MovieVideo;
import io.ziyi.spider.util.MapUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MediaDao extends CommonDao {

    @Transactional(rollbackFor = Exception.class)
    public void saveMovie(Movie movie) {
        save(movie);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveVideo(MovieVideo video) {
        save(video);
    }

    @Transactional(readOnly = true)
    public Movie findMovie(String id) {
        return super.find(Movie.class, id);
    }

    @Transactional(readOnly = true)
    public List<MovieVideo> findMovieVideos(String movieId) {
        return super.query(MovieVideo.class, MapUtils.build("movieId", movieId, "usage", "main"), null);
    }

}
