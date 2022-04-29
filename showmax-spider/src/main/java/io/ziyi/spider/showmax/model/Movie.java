package io.ziyi.spider.showmax.model;

import io.ziyi.spider.showmax.vo.Item;
import io.ziyi.spider.showmax.vo.Language;
import io.ziyi.spider.showmax.vo.Person;
import io.ziyi.spider.showmax.vo.Rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "movie",
        indexes = {
                @Index(name = "IDX_movie_type", columnList = "type"),
                @Index(name = "IDX_movie_meta_lang", columnList = "metadata_language"),
                @Index(name = "IDX_movie_rating", columnList = "rating_level"),
                @Index(name = "IDX_movie_provider", columnList = "provider"),
                @Index(name = "IDX_movie_director", columnList = "director")
        }
)
public class Movie extends BaseModel<String> {

    @Id
    @Column(name = "id", nullable = false, length = 40)
    private String id;

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "metadata_language", length = 8)
    private String metadataLanguage;

    @Column(name = "rating_level", length = 40)
    private String ratingLevel;

    @Column(name = "type", length = 16)
    private String type;

    @Column(name = "website_url", length = 240)
    private String websiteUrl;

    @Column(name = "provider", length = 40)
    private String provider;

    @Column(name = "slug", length = 40)
    private String slug;

    @Column(name = "duration", scale = 2, precision = 14)
    private BigDecimal duration;

    @Column(name = "year")
    private Integer year;

    @Column(name = "director")
    private String director;

    public Movie() {
    }

    public Movie(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.type = item.getType();
        this.provider = item.getProvider();
        Language language = item.getMetadataLanguage();
        if ( language != null ) {
            this.metadataLanguage = language.getCode();
        }
        this.duration = item.getDuration();
        List<Person> crew = item.getCrew();
        if ( crew != null && crew.size() > 0 ) {
            this.director = crew.stream().filter(c -> c.isRole("director")).map(Person::getName).collect(Collectors.joining("/"));
        }
        this.slug = item.getSlug();
        this.year = item.getYear();
        Rating rating = item.getRating();
        if ( rating != null ) {
            this.ratingLevel = rating.getLevel();
        }
        this.websiteUrl = item.getWebsiteUrl();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetadataLanguage() {
        return metadataLanguage;
    }

    public void setMetadataLanguage(String metadataLanguage) {
        this.metadataLanguage = metadataLanguage;
    }

    public String getRatingLevel() {
        return ratingLevel;
    }

    public void setRatingLevel(String ratingLevel) {
        this.ratingLevel = ratingLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
