package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import io.ziyi.spider.common.CommonVO;

import java.util.List;
import java.util.Map;

public class Play extends CommonVO {

    private static final long serialVersionUID = 0xf9f739a7fda2b42cL;

    public static final TypeReference<Play> TYPE = new TypeReference<Play>() {};

    @JsonProperty("encoding")
    private String encoding;

    @JsonProperty("url")
    private List<String> urls;

    @JsonProperty("usage")
    private String usage;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("widevine_certificate")
    private byte[] widevineCertificate;

    @JsonProperty("restrictions")
    private Map<String, Object> restrictions;

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public byte[] getWidevineCertificate() {
        return widevineCertificate;
    }

    public void setWidevineCertificate(byte[] widevineCertificate) {
        this.widevineCertificate = widevineCertificate;
    }

    public Map<String, Object> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Map<String, Object> restrictions) {
        this.restrictions = restrictions;
    }
}
