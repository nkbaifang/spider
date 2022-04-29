package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import io.ziyi.spider.common.CommonVO;

import java.util.List;

public class Asset extends CommonVO {

    private static final long serialVersionUID = 0xbd2d7a84af8d31ecL;

    public static final TypeReference<Asset> TYPE = new TypeReference<Asset>() {};

    @JsonProperty("count")
    private int count;

    @JsonProperty("total")
    private int total;

    @JsonProperty("remaining")
    private int remaining;

    @JsonProperty("items")
    private List<Item> items;

    @JsonProperty("facets")
    private Facets facets;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Facets getFacets() {
        return facets;
    }

    public void setFacets(Facets facets) {
        this.facets = facets;
    }
}
