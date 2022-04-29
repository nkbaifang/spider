package io.ziyi.spider.showmax.model;

import io.ziyi.spider.showmax.vo.Language;
import io.ziyi.spider.showmax.vo.Video;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(
        name = "movie_video",
        indexes = {
                @Index(name = "IDX_movie_video_movie", columnList = "movie_id"),
                @Index(name = "IDX_movie_video_language", columnList = "language"),
                @Index(name = "IDX_movie_video_usage", columnList = "usage")
        }
)
public class MovieVideo extends BaseModel<String> {

    @Id
    @Column(name = "id", nullable = false, length = 40)
    private String id;

    @Column(name = "movie_id", nullable = false, length = 40)
    private String movieId;

    @Column(name = "language", length = 8)
    private String language;

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

    @Column(name = "duration", precision = 14, scale = 2)
    private BigDecimal duration;

    @Column(name = "usage", length = 20)
    private String usage;

    @Column(name = "ending_time", precision = 14, scale = 2)
    private BigDecimal endingTime;

    @Column(name = "link", length = 240)
    private String link;

    public MovieVideo() {
    }

    public MovieVideo(Video video) {
        this.id = video.getId();
        Language language = video.getLanguage();
        if ( language != null ) {
            this.language = language.getCode();
        }
        this.height = video.getHeight();
        this.width = video.getWidth();
        this.duration = video.getDuration();
        this.usage = video.getUsage();
        this.endingTime = video.getEndingTime();
        this.link = video.getLink();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String videoId) {
        this.movieId = videoId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public BigDecimal getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(BigDecimal endingTime) {
        this.endingTime = endingTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
