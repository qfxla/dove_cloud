package com.dove.breed.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.breed.entity.CageReal;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-09-03-20:46
 */
public interface PageUtil {

//    static <T> Page<T> createPageFromList(List<T> list, Pageable pageable) {
//        if (list == null) {
//            throw new IllegalArgumentException("list不能为空");
//        }
//
//            int startOfPage = pageable.getPageNumber()* pageable.getPageSize();
//        if (startOfPage > list.size()) {
//            return new PageImpl<>(new ArrayList<>(), pageable, 0);
//        }
//
//        int endOfPage = Math.min(startOfPage + pageable.getPageSize(), list.size());
//        return new PageImpl<>(list.subList(startOfPage, endOfPage), pageable, list.size());
//    }
    static <T> org.springframework.data.domain.Page<T> createPageFromList(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }

    static <T> Page<T> myPage(List<T> list,int pageNum,int pageSize){
        ArrayList<T> list1 = new ArrayList<>();
        int count = list.size();
        Page<T> page = new Page<>(pageNum, pageSize);
        int currentId = pageNum > 1?(pageNum -1)*pageSize:0;
        for (int i = 0;i < pageSize && i< count- currentId;i++){
            list1.add(list.get(currentId+1));
        }

        page.setSize(pageSize);
        page.setCurrent(pageNum);
        page.setTotal(count);
        //计算分页总页数
        page.setPages(count %10 == 0 ? count/10 :count/10+1);
        page.setRecords(list1);
        return page;
    }

    static <T> Page<T> fourMyPage(List<T> list,int pageNum,int pageSize){
        ArrayList<T> list1 = new ArrayList<>();
        int count = list.size();
        Page<T> page = new Page<>(pageNum, pageSize);
        int currentId = pageNum > 1?(pageNum -4)*pageSize:0;
        for (int i = 0;i < pageSize && i< count- currentId;i++){
            list1.add(list.get(currentId+1));
        }

        page.setSize(pageSize);
        page.setCurrent(pageNum);
        page.setTotal(count);
        //计算分页总页数
        page.setPages(count %10 == 0 ? count/10 :count/10+1);
        page.setRecords(list1);
        return page;
    }

}