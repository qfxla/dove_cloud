package com.dove.processing.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 小亮
 * @Time 2021/02/23 21:36
 * @Descripe 将一个List放入Page中
 * 有个地方不太好，要全部查出来才能分页，尽量不要用
 */
public class MyPage {
    /**
     * 将List集合放入Page中
     * @param currentPage   当前页数
     * @param pageSize  每一页的数据条数
     * @param list  要进行分页的数据列表
     * @return  当前页要展示的数据
     */
    public static Page list2Page(int currentPage, int pageSize, List list) {
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

    public static Page list2Page(int currentPage, int pageSize, int total, List list) {
        Page page = new Page();
//        int size = list.size();


        // 求出最大页数，防止currentPage越界
        if(total > 0) {
            int maxPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
            if (currentPage > maxPage) {
                currentPage = maxPage;
            }
        }

        // 当前页第一条数据的下标
        int curIdx = currentPage > 1 ? (currentPage - 1) * pageSize : 0;

//        List pageList = new ArrayList();

//        // 将当前页的数据放进pageList
//        for(int i = 0; i < pageSize && curIdx + i < total; i++) {
//            pageList.add(list.get(curIdx + i));
//        }

        page.setCurrent(currentPage).setSize(pageSize).setTotal(total).setRecords(list);
        return page;
    }
}
