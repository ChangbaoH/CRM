package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("Permission")
@Getter@Setter
public class Permission {
    private Long id;
    private String name;
    private String resource;
}