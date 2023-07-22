package com.jyhmm.cmp.common.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2773519546319835974L;

    List<?> list;
    int currentPage;
    int maxPageSize;
    int numberOfElements;
    int totalPages;
    long totalElements;

    public PageDTO(List<?> list, Page<?> page) {
        this.list = list;
        this.currentPage = page.getNumber();
        this.maxPageSize = page.getSize();
        this.numberOfElements = page.getNumberOfElements();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
