package com.cy.sys.dao;

import com.cy.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2020/3/18 18:34
 * @Description:
 */
@Mapper
public interface SysLogDao {

    Long getRowCont(String username);

    List<SysLog> findPageObjects(String username, Long startIndex, Integer pageSize);

    int deleteObjects(Long...ids);

    int insertObject(SysLog entity);
}
