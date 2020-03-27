package com.fengwenyi.mybatis_plus_example;

import com.fengwenyi.mybatis_plus_example.service.MPStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoWireTest {
    @Autowired
    private MPStudentService mpStudentService;

    @Test
    public void queryTest() {
        mpStudentService.test3();
    }

}
