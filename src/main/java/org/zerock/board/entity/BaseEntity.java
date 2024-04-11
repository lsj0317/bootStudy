package org.zerock.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 직접 테이블용은 아님을 명시한다.
@Getter // getter
// 세터 대신에 감시용 코드(데이터 변경을 감지하여 적용 -> main메서드에 추가코드를 넣어줘야함)
@EntityListeners(value = {AuditingEntityListener.class})
abstract class BaseEntity { // 상속간의 추상클래스로 동작
    // 테이블에 공통되는 부분을 상속해줄 클래스

    @CreatedDate // 게시물 생성할 때 동작
    @Column(name = "regdate", updatable = false) // DB테이블의 필드명 지정, 업데이트 안됨
    private LocalDateTime regDate; // 게시물 등록시간

    @LastModifiedDate // 게시물 수정될 때 동작
    @Column(name = "moddate") // DB테이블의 필드명 지정
    private LocalDateTime modDate; // 게시물 수정시간
}
