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
    /**
     * 将List集合放入Page中
     * @param currentPage   当前页数
     * @param pageSize  每一页的数据条数
     * @param list  要进行分页的数据列表
     * @return  当前页要展示的数据
     */
    public static Page list2Page(List list,Integer currentPage, Integer pageSize) {
        Page page = new Page();
        int size = list.size();

        if(pageSize > size) {
            pageSize = size;
        }

        // 求出最大页数，防止currentPage越界
        if(size > 0) {
            int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
            if (currentPage > maxPage) {
                currentPage = maxPage;
            }else{
                currentPage = currentPage > 0 ? currentPage  : 1;
            }
        }

        // 当前页第一条数据的下标
        int curIdx = currentPage > 1 ? (currentPage - 1) * pageSize : 0;

        List pageList = new ArrayList();

        // 将当前页的数据放进pageList
        for(int i = 0; i < pageSize && curIdx + i < size; i++) {
            pageList.add(list.get(curIdx + i));
        }

        page.setCurrent(currentPage).setSize(pageSize).setTotal(list.size()).setRecords(pageList);
        return page;
    }
}