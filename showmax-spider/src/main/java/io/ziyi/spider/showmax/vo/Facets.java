package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.util.List;

public class Facets extends CommonVO {

    private static final long serialVersionUID = 0x38b98a23610f740fL;

    @JsonProperty("audio_languages")
    private List<Language> audioLanguages;

    @JsonProperty("categories")
    private List<Category> categories;

    @JsonProperty("sections")
    private List<AggeratedCount> sections;

    @JsonProperty("valid_subscription_statuses")
    private List<AggeratedCount> validSubscriptionStatuses;

    @JsonProperty("subtitles_languages")
    private List<Language> subtitlesLanguages;

    @JsonProperty("types")
    private List<AggeratedCount> types;

    @JsonProperty("vod_models")
    private List<AggeratedCount> vodModels;

    public List<Language> getAudioLanguages() {
        return audioLanguages;
    }

    public void setAudioLanguages(List<Language> audioLanguages) {
        this.audioLanguages = audioLanguages;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<AggeratedCount> getSections() {
        return sections;
    }

    public void setSections(List<AggeratedCount> sections) {
        this.sections = sections;
    }

    public List<AggeratedCount> getValidSubscriptionStatuses() {
        return validSubscriptionStatuses;
    }

    public void setValidSubscriptionStatuses(List<AggeratedCount> validSubscriptionStatuses) {
        this.validSubscriptionStatuses = validSubscriptionStatuses;
    }

    public List<Language> getSubtitlesLanguages() {
        return subtitlesLanguages;
    }

    public void setSubtitlesLanguages(List<Language> subtitlesLanguages) {
        this.subtitlesLanguages = subtitlesLanguages;
    }

    public List<AggeratedCount> getTypes() {
        return types;
    }

    public void setTypes(List<AggeratedCount> types) {
        this.types = types;
    }

    public List<AggeratedCount> getVodModels() {
        return vodModels;
    }

    public void setVodModels(List<AggeratedCount> vodModels) {
        this.vodModels = vodModels;
    }
}
