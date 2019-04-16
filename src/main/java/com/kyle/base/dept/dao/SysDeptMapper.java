package com.kyle.base.dept.dao;

import java.util.List;
import java.util.Map;

import com.kyle.base.dept.entity.SysDept;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDept record);

    List<SysDept> getDeptList(Map<String,Object> param);
    
    List<SysDept> getAllDept();
    
    
}