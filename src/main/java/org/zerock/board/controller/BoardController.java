package org.zerock.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시물 목록 출력 / 목록 화면
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list 메뉴 실행............." + pageRequestDTO);

        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    // 게시글 작성 페이지 이동
    @GetMapping("/register")
    public void register() {
        log.info("register get........");
    }

    // 게시글 작성 메서드
    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {

        log.info("dto.... " + dto);
        // 새로 추가된 엔티티의 번호
        Long bno = boardService.register(dto);

        log.info("BNO : " + bno + " 번");

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    // 한개의 게시글 조회  / 수정 화면 처리 메서드
    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO
                                 pageRequestDTO, Long bno, Model model) {

        log.info("조회한 게시물의 bno : " + bno + " 번");

        BoardDTO boardDTO = boardService.get(bno);

        log.info(boardDTO);

        model.addAttribute("dto", boardDTO);

    }

    // 한 개의 게시물 삭제 메서드
    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {

        log.info("삭제한 게시물의 bno : " + bno + " 번");

        boardService.removeWithReplies(bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    // 한 개의 게시물 수정 메서드
    @PostMapping("/modify")
    public String modify(BoardDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {

        log.info("post modify..........................");
        log.info("dto : " + dto);

        boardService.modify(dto);

        redirectAttributes.addFlashAttribute("page", requestDTO.getPage());
        redirectAttributes.addFlashAttribute("type", requestDTO.getType());
        redirectAttributes.addFlashAttribute("keyword", requestDTO.getKeyword());

        redirectAttributes.addFlashAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }

}
