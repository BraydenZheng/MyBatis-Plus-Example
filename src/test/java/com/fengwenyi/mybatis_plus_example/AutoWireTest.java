package com.fengwenyi.mybatis_plus_example;

import com.fengwenyi.mybatis_plus_example.service.MPStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoWireTest {
    @Autowired
    private MPStudentService mpStudentService;

    @Test
    public void queryTest() {
        mpStudentService.test3();
    }

    @Test
    public void catchTest() {
        try {
            int[] myNumbers = {1, 2, 3};
//            System.out.println(myNumbers[10]);
            throw IOException e = new IOException(" dkf");
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
        System.out.println("yeah");
    }
}
