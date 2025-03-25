package com.rgonz17.project_kata_rgonz.domain.dto;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagedResponse<T> {
    private int page;
    private int limit;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
    @JsonIgnore
    private String contentKey;
    private List<T> content;

    public PagedResponse(String contentKey, List<T> content, int page, int limit, long totalElements) {
        this.contentKey = contentKey;
        this.content = content;
        this.page = page;
        this.limit = limit;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / limit);
        this.hasNext = page < totalPages - 1;
        this.hasPrevious = page > 0;
    }

    public int getPage() { return page; }
    public int getLimit() { return limit; }
    public long getTotalElements() { return totalElements; }
    public int getTotalPages() { return totalPages; }
    public boolean isHasNext() { return hasNext; }
    public boolean isHasPrevious() { return hasPrevious; }

    @JsonAnyGetter
    public Map<String, Object> getDynamicContent() {
        Map<String, Object> map = new HashMap<>();
        map.put(contentKey, content);
        return map;
    }
}

