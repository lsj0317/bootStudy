package org.zerock.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_memo") // da table명을 지정
@ToString // 객체가 아닌 문자열로
@Getter
@Builder // method.field(value).field(value).builder; (builder patten)
// builder patten을 사용하기 위해 AllArgsConstructor, NoArgsConstructor는 필수로 선언해야한다.
@AllArgsConstructor // new class(모든 필드값 파라미터로 만듦);
@NoArgsConstructor // new class();
public class Memo {

    // entity는 database의 table과 field를 생성시켜 관리하는 객체
    // entity를 이용해서 jpa를 활성화 하려면 application.property에 필수 항목 추가
    @Id // 기본키를 명시한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno; // 자바빈즈 규약에 따라 필드는 private으로 선언한다.

    @Column(length = 200, nullable = false) // 글자길이, not null 처리
    private String memoText;
}
