package org.zerock.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

    private Long rno; // 댓글 번호

    private String text; //  댓글 내용

    private String replyer; // 댓글 작성자

    private Long bno; // 게시글 번호

    private LocalDateTime regDate, modDate; // 작성일, 수정일

}
