package org.zerock.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno; // 게시물 번호

    private String title; // 게시물 제목

    private String content; // 게시물 내용

    private String writerEmail; // 작성자의 이메일

    private String writerName; // 작성자의 이름

    private LocalDateTime regDate; // 작성일

    private LocalDateTime modDate; // 수정일

    private int replyCount; // 해당 게시글의 댓글 수

}
