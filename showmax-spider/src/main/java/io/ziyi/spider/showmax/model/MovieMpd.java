package io.ziyi.spider.showmax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(
        name = "movie_mpd",
        indexes = {
                @Index(name = "IDX_movie_mpd_movie", columnList = "movie_id"),
                @Index(name = "IDX_movie_mpd_sha1", columnList = "file_sha1")
        }
)
public class MovieMpd extends BaseModel<String> {

    @Id
    @Column(name = "video_id", nullable = false, length = 40)
    private String videoId;

    @Column(name = "movie_id", nullable = false, length = 40)
    private String movieId;

    @Column(name = "download_time", nullable = false, columnDefinition = "timestamp(3) not null")
    private Date downloadTime;

    @Column(name = "length", nullable = false, columnDefinition = "bigint not null default 0")
    private long length;

    @Column(name = "file_sha1", length = 48)
    private String fileSha1;

    @Override
    public String getId() {
        return getVideoId();
    }

    public void setId(String id) {
        setVideoId(id);
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Date getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getFileSha1() {
        return fileSha1;
    }

    public void setFileSha1(String fileSha1) {
        this.fileSha1 = fileSha1;
    }
}
