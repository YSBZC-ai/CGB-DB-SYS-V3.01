package com.cy.sys.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/20 09:26
 * @Description:
 */
@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable {

    private static final long serialVersionUID = 6898199562073161708L;
    /**
     * 当前页的页码值
     */
    private Long pageCurrent;
    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 总行数(通过查询获得)
     */
    private Long rowCount;
    /**
     * 总页数(通过计算获得)
     */
    private Long pageCount;
    /**
     * 封装查询到的当前记录
     */
    private List<T> records;

    public PageObject(Long pageCurrent, Integer pageSize, Long rowCount, List<T> records) {
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
        /*this.rowCount = rowCount;
                this.pageCount=rowCount/pageSize;
                if(rowCount%pageSize!=0) {
                        pageCount++;
                }*/
        this.rowCount = rowCount;
        this.pageCount = (rowCount - 1) / pageSize + 1;
        this.records = records;
    }
}
