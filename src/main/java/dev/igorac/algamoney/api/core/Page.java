package dev.igorac.algamoney.api.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Page<T> extends ArrayList<T> {

    private final Long total;
    private final Paging pagingInfo;

    public Page(Collection<? extends T> c, Paging pagingInfo, Long total) {
        super(c);
        this.total = total;
        this.pagingInfo = pagingInfo;
    }
}
