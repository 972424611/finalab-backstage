package com.cslg.finalab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Twilight
 * @date 2019-02-10 09:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMyBatisForeach {

    /*@Autowired
    private TestMapper testMapper;

    List<com.cslg.finalab.model.Test> testList1 = new ArrayList<>();

    List<com.cslg.finalab.model.Test> testList2 = new ArrayList<>();

    List<com.cslg.finalab.model.Test> testList3 = new ArrayList<>();

    public void testInsert(List<com.cslg.finalab.model.Test> testList) {
        testMapper.batchInsert(testList);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void entry() throws InterruptedException {
        for(int i = 1; i <= 100; i++) {
            testList1.add(new com.cslg.finalab.model.Test("Thread1--" + i));
        }
        for(int i = 1; i <= 100; i++) {
            testList2.add(new com.cslg.finalab.model.Test("Thread2--" + i));
        }
        for(int i = 1; i <= 100; i++) {
            testList3.add(new com.cslg.finalab.model.Test("Thread3--" + i));
        }

        new Thread(() -> testInsert(testList1)).start();
        new Thread(() -> testInsert(testList2)).start();
        new Thread(() -> testInsert(testList3)).start();
        Thread.sleep(10000);
    }*/
}
