package com.gistmap.ddbeat.user;

import com.gistmap.common.json.JsonListResponseEntity;
import com.gistmap.ddbeat.user.persistence.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudServiceUserApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
//        String name = "";
//        Specification<User> specification = Specifications.<User>and()
//                .eq(StringUtils.isNoneBlank(name),"name", name).build();
//        userRepository.findAll(specification);
    }

    @Test
    public void test(){
        JsonListResponseEntity<String> response = JsonListResponseEntity.of();
        List<String> list = Lists.newArrayList();
        list.add("hello");
        response.setContent(list).addExtra("key","value");
        System.out.println(response);
    }

}
