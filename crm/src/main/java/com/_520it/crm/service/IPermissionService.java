package com._520it.crm.service;

import com._520it.crm.domain.Permission;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;


public interface IPermissionService {

    PageResult queryByCondition(QueryObject qo);

    // 根据资源
    Permission queryByResource(String function);

    List<Permission> queryPermissionByEid(Long id);

/*    void reload();*/


}
