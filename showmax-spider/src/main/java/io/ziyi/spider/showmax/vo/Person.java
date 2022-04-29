package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.util.Objects;

public class Person extends CommonVO {

    private static final long serialVersionUID = 0xdf9b4185f15c451eL;

    @JsonProperty("name")
    private String name;

    @JsonProperty("role")
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonIgnore
    public boolean isRole(String role) {
        return Objects.equals(this.role, role);
    }
}
