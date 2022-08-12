package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter@Setter
@Alias("Menu")
public class Menu {
    private Long id;
    private String text;
    private String state;
    private Boolean checked;
    private String attributes;
    private String resource;
    private List<Menu> children;

}