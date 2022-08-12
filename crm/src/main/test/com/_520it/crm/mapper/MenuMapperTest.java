package com._520it.crm.mapper;

import com._520it.crm.domain.Menu;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class MenuMapperTest {

    @Autowired
    private MenuMapper mapper;

    @Test
    public void testSelectRoot() throws Exception {
        List<Menu> menus = mapper.selectRoot();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(menus);
        System.out.println(s);

    }
}