package com.zky.health.entity;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.entity
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-23 10:46
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
import java.io.Serializable;

/**
 * 封装查询条件
 */
public class QueryPageBean implements Serializable{
    private Integer currentPage;//页码
    private Integer pageSize;//每页记录数
    private String queryString;//查询条件

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
