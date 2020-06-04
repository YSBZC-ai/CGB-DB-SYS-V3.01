package com.cy.sys.service;

import com.cy.sys.common.vo.Node;
import com.cy.sys.entity.SysDept;

import java.util.List;
import java.util.Map;

public interface SysDeptService {
	 List<Map<String,Object>> findObjects();
	 List<Node> findZTreeNodes();
	 int saveObject(SysDept entity);
	 int updateObject(SysDept entity);
	 int deleteObject(Integer id);
}
