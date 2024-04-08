package org.zerock.board;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2 // log.info 사용가능하도록
class BoardApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Hello World!");
        // log.info("양지웅씨~~");
    }

}
