package org.zerock.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.board.entity.Guestbook;
import org.zerock.board.entity.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest // 인터페이스 자동 주입
public class GuestbookRepositoryTests {

    @Autowired // 인터페이스 자동주입
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies() { // 테이블에 더미데이터 300개 추가
        IntStream.rangeClosed(1,300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("제목....." + i)
                    .content("내용....." + i)
                    .writer("user"+(i%10)) // user 0 ~ user 30 ?
                    .build(); // 빌더 패턴은 엔티티 @Builder 필수
            System.out.println(guestbookRepository.save(guestbook));
            // jpa save 메서드를 실행하여 출력까지 진행
        });
    }

    @Test
    public void updateTest() {
        // 게시물을 수정하는 테스트 진행
        Optional<Guestbook> result = guestbookRepository.findById(300L);
        // 300번의 게시물을 찾아본다. 찾아온 게시물의 엔티티가 result에 들어감

        if(result.isPresent()) { // 만약에 객체가 있으면
            Guestbook guestbook = result.get(); // 엔티티를 가져와 변수에 넣음
            System.out.println(guestbook.getTitle()); // 게시물 300번의 제목 출력
            System.out.println(guestbook.getContent()); // 게시물 300번의 내용 출력
            System.out.println(guestbook.getWriter()); // 게시물 300번의 작성자 출력
            guestbook.changeTitle("수정된 제목....."); // 엔티티에 만든 메서드
            guestbook.changeContent("수정된 내용....."); // 엔티티에 만든 메서드
            guestbookRepository.save(guestbook); // 있으면 update, 없으면 insert
        }
    }

    @Test
    public void testQuery1() { // 단일 조건으로 쿼리 생성 + 페이징 + 정렬
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        // 페이지 타입으로 요청을 처리함(0번 페이지에 10개씩 객체 생성, gno를 기준으로 내림차순)
        QGuestbook qGuestbook = QGuestbook.guestbook; // Querydsl용 객체 생성(동적 처리용)

        String keyword = "9"; // 프론트 페이지에서 1번을 찾겠다 라는 변수

        BooleanBuilder builder = new BooleanBuilder(); // 다중 조건 처리용 객체
        BooleanExpression expressionTitle = qGuestbook.title.contains(keyword);
        BooleanExpression expressionContent = qGuestbook.content.contains(keyword);
        BooleanExpression exAll = expressionTitle.or(expressionContent); // 두 개의 조건을 합친다.
        // expression(표현) title = 9 이라는 표현식이 생성됨

        builder.and(exAll); // 다중 조건 처리용 객체의 표현식을 밀어 넣음.
        builder.and(qGuestbook.gno.gt(0L)); // where문 추가 (gno > 0)

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        // 페이지 타입의 객체가 나온다 .findAll()은 검색된 모든 객체가 나온다.

        result.stream().forEach(guestbook -> {
            System.out.println("검색된 결과 : " + guestbook);
        });
    }

}
