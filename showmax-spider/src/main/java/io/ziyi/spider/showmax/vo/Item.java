package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import io.ziyi.spider.common.CommonVO;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Item extends CommonVO {

    private static final long serialVersionUID = 0x3e9703ea86280bc7L;

    public static final TypeReference<Item> TYPE = new TypeReference<Item>() {};

    @JsonProperty("id")
    private String id;

    @JsonProperty("audio_languages")
    private List<Language> audioLanguages;

    @JsonProperty("section")
    private String section;

    @JsonProperty("rating")
    private Rating rating;

    @JsonProperty("cast")
    private List<Person> cast;

    @JsonProperty("type")
    private String type;

    @JsonProperty("duration")
    private BigDecimal duration;

    @JsonProperty("valid_subscription_statuses")
    private List<String> validSubscriptionStatuses;

    @JsonProperty("link")
    private String link;

    @JsonProperty("categories")
    private List<Category> categories;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("metadata_direction")
    private String metadataDirection;

    @JsonProperty("metadata_language")
    private Language metadataLanguage;

    @JsonProperty("title")
    private String title;

    @JsonProperty("crew")
    private List<Person> crew;

    @JsonProperty("images")
    private List<Image> images;

    @JsonProperty("age_range_min")
    private Integer ageRangeMin;

    @JsonProperty("age_range_max")
    private Integer ageRangeMax;

    @JsonProperty("videos")
    private List<Video> videos;

    @JsonProperty("description")
    private String description;

    @JsonProperty("has_download_policy")
    private Boolean hasDownloadPolicy;

    @JsonProperty("vod_model")
    private String vodModel;

    @JsonProperty("subtitles_languages")
    private List<Language> subtitlesLanguages;

    @JsonProperty("website_url")
    private String websiteUrl;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("license_types")
    private List<String> licenseTypes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Language> getAudioLanguages() {
        return audioLanguages;
    }

    public void setAudioLanguages(List<Language> audioLanguages) {
        this.audioLanguages = audioLanguages;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<Person> getCast() {
        return cast;
    }

    public void setCast(List<Person> cast) {
        this.cast = cast;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public List<String> getValidSubscriptionStatuses() {
        return validSubscriptionStatuses;
    }

    public void setValidSubscriptionStatuses(List<String> validSubscriptionStatuses) {
        this.validSubscriptionStatuses = validSubscriptionStatuses;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getMetadataDirection() {
        return metadataDirection;
    }

    public void setMetadataDirection(String metadataDirection) {
        this.metadataDirection = metadataDirection;
    }

    public Language getMetadataLanguage() {
        return metadataLanguage;
    }

    public void setMetadataLanguage(Language metadataLanguage) {
        this.metadataLanguage = metadataLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Person> getCrew() {
        return crew;
    }

    public void setCrew(List<Person> crew) {
        this.crew = crew;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Integer getAgeRangeMin() {
        return ageRangeMin;
    }

    public void setAgeRangeMin(Integer ageRangeMin) {
        this.ageRangeMin = ageRangeMin;
    }

    public Integer getAgeRangeMax() {
        return ageRangeMax;
    }

    public void setAgeRangeMax(Integer ageRangeMax) {
        this.ageRangeMax = ageRangeMax;
    }

    public List<Video> getVideos() {
        return videos == null ? Collections.emptyList() : Collections.unmodifiableList(videos);
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasDownloadPolicy() {
        return hasDownloadPolicy;
    }

    public void setHasDownloadPolicy(Boolean hasDownloadPolicy) {
        this.hasDownloadPolicy = hasDownloadPolicy;
    }

    public String getVodModel() {
        return vodModel;
    }

    public void setVodModel(String vodModel) {
        this.vodModel = vodModel;
    }

    public List<Language> getSubtitlesLanguages() {
        return subtitlesLanguages;
    }

    public void setSubtitlesLanguages(List<Language> subtitlesLanguages) {
        this.subtitlesLanguages = subtitlesLanguages;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getLicenseTypes() {
        return licenseTypes;
    }

    public void setLicenseTypes(List<String> licenseTypes) {
        this.licenseTypes = licenseTypes;
    }
}
