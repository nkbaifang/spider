package io.ziyi.spider.showmax.dao;

import io.ziyi.spider.showmax.model.Movie;
import io.ziyi.spider.showmax.model.MovieMpd;
import io.ziyi.spider.showmax.model.MovieVideo;
import io.ziyi.spider.util.MapUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.Collections;
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
    public MovieVideo findMovieVideo(String id) {
        return super.find(MovieVideo.class, id);
    }

    @Transactional(readOnly = true)
    public List<MovieVideo> findMovieVideos(String movieId) {
        return super.query(MovieVideo.class, MapUtils.build("movieId", movieId, "usage", "main"), null);
    }

    @Transactional(readOnly = true)
    public List<Movie> findAllMovies() {
        return super.query(Movie.class, Collections.emptyMap(), null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveMovieMpd(MovieMpd mpd) {
        super.save(mpd);
    }

    @Transactional(readOnly = true)
    public MovieMpd findMovieMpd(String videoId) {
        return super.unique(MovieMpd.class, MapUtils.singleMap("videoId", videoId), false);
    }
}
