package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter@Setter
@Alias("Role")
public class Role {
    private Long id;
    private String name;
    private String sn;
    private List<Permission> permission;
}