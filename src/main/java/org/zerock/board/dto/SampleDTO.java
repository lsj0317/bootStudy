package org.zerock.board.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data // getter, setter, toString, Equals.........
// 빌더패턴 (객체 집어넣을때 .찍어서 값 전체 넣기)
@Builder(toBuilder = true)
public class SampleDTO {
    // dto는 프론트에서 자바까지 객체를 담당한다.
    // 엔티티는 DB에서 자바까지 영속성을 담당한다.
    // 나중에는 DTOtoEntity, EntitytoDTO라는 메서드가 이 2개를 전이 역할을 진행함.

    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;

}
