package dev.igorac.algamoney.api.core;

import lombok.Data;

@Data
public class Paging {

    private int size;
    private int page;

    public int getFirstResult() {
        return size * page;
    }

}
