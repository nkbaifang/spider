package io.ziyi.spider.viu.common;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Page<E> {

    private static final Page<?> empty = new Page<>(Collections.emptyList(), 0);

    private final int totalCount;
    private final List<E> list;

    public Page(List<E> list, int totalCount) {
        Objects.requireNonNull(list);
        this.list = list;
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<E> getList() {
        return Collections.unmodifiableList(list);
    }

    public int getPageCount(int pageSize) {
        int pageCount = totalCount / pageSize;
        if ( totalCount % pageSize != 0 ) {
            pageCount++;
        }
        return pageCount;
    }

    public <D> Page<D> toPage(Function<E, D> mapper) {
        List<D> list = this.list.stream().map(mapper).collect(Collectors.toList());
        return new Page<>(list, this.totalCount);
    }

    @SuppressWarnings("unchecked")
    public static <E> Page<E> empty() {
        return (Page<E>) empty;
    }
}
