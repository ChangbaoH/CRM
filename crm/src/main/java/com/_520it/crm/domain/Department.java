package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("Department")
@Getter@Setter
public class Department {
    private Long id;
    private String sn;
    private String name;
    private Employee manager;
    private Department parent;
    private Boolean state;

}