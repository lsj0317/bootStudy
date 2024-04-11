package org.zerock.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @EntityListeners(value = {AuditingEntityListener.class})
public class BoardApplication {

    // 부트는 여기서 부터시작
    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

}
