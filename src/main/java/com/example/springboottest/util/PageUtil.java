package com.example.springboottest.util;


/**
 * 用于处理分页数据
 */
public class PageUtil {


    public static Integer initPageNo(Integer pageNo) {
        if (pageNo == null || pageNo < 1) {// 默认第一页
            pageNo = 1;
        }
        return pageNo;
    }

    public static Integer initPageSize(Integer pageSize, Integer basePageSize) {
        if (pageSize == null || pageSize < 1) {
            return basePageSize;
        }
        return pageSize;
    }

    /**
     * 获取分页开始下标标记
     *
     * @param pageNo
     * @param pageSzie
     * @return
     */
    public static int getStartIndex(String pageNo, String pageSzie) {
        return (Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSzie);
    }

    /**
     * 获取分页结束下标标记
     *
     * @param pageNo
     * @param pageSzie
     * @return
     */
    public static int getEndIndex(String pageNo, String pageSzie) {
        return Integer.parseInt(pageNo) * Integer.parseInt(pageSzie);
    }


    /**
     * 获取总页数
     * @param rows  每页多少条
     * @param totalRows  总条数
     * @return
     */
    public static int getTotalPage(int rows ,int totalRows){
        int totalPage = 0;
        if(totalRows % rows == 0){
            totalPage = totalRows / rows;
        }else{
            totalPage = totalRows / rows + 1;
        }
        return totalPage ;
    }


}
