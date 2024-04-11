package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // 테이블을 자동으로 관리
@Getter
@Builder
@AllArgsConstructor // @Builder에 필수 (세트)
@NoArgsConstructor  // @Builder에 필수 (세트)
@ToString           // 객체가 아닌 문자열로 변환
public class Guestbook extends BaseEntity {
    // extends BaseEntity를 상속받아서 날짜, 시간 자동처리
    @Id // guestbook 테이블에 pk를 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 마리아 DB용 자동번호
    private Long gno;        // 방명록에서 사용할 번호

    @Column(length = 100, nullable = false) // 문자 100글자, not null
    private  String title;   // 제목

    @Column(length = 1500, nullable = false) // 문자 1500글자, not null
    private  String content; // 내용

    @Column(length = 50, nullable = false) // 문자 50글자, not null
    private  String writer;  // 작성자

    public void changeTitle(String title) {
        this.title = title; // setter 역할 (수정 할 때 사용)
    }

    public void changeContent(String content) {
        this.content = content; // setter 역할 (수정 할 때 사용)
    }
}
