package com.tutorial.springdatamongodbdynamicqueries.helpers;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResponse<T> {

    private int totalPages;
    private long totalItems;
    private int currentPage;
    private int firstPage;
    private int nextPage;
    private int previousPage;
    private boolean first;
    private boolean last;
    private boolean hasNext;
    private boolean hasPrevious;
    private int itemsPerPage;
    private int pageSize;
    private Sort sort;
    private List<T> items;

    public void setPage(Page<?> pg, List<T> elements) {
        first = pg.isFirst();
        last = pg.isLast();
        hasNext = pg.hasNext();
        hasPrevious = pg.hasPrevious();
        currentPage = pg.getNumber() + 1;
        pageSize = pg.getSize();
        totalPages = pg.getTotalPages();
        totalItems = pg.getTotalElements();
        sort = pg.getSort();
        firstPage = pg.getPageable().first().getPageNumber() + 1;
        nextPage = pg.hasNext() ? pg.getPageable().next().getPageNumber() + 1 : pg.getTotalPages();
        previousPage = pg.getPageable().previousOrFirst().getPageNumber() + 1;
        itemsPerPage = pg.getNumberOfElements();
        items = elements;
    }
}