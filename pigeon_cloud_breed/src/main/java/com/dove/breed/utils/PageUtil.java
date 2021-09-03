package com.dove.breed.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-09-03-20:46
 */
public interface PageUtil {

    /**
     *
     * @param list     List数据
     * @param pageable 分页参数.
     * @param <T>     包含数据
     * @return page
     */
    static <T> Page<T> createPageFromList(List<T> list, Pageable pageable) {
        if (list == null) {
            throw new IllegalArgumentException("list不能为空");
        }

        int startOfPage = (pageable.getPageNumber() - 1) * pageable.getPageSize();
        if (startOfPage > list.size()) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }

        int endOfPage = Math.min(startOfPage + pageable.getPageSize(), list.size());
        return new PageImpl<>(list.subList(startOfPage, endOfPage), pageable, list.size());
    }
}