package org.zerock.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.eclipse.jdt.internal.compiler.env.IModule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.GuestbookDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.service.GuestbookService;

@Controller // 컨트롤러 역할 지정
@RequestMapping("/guestbook") // http://localhost/guestbook/????
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 Annotaion
public class GuestbookController {

    private final GuestbookService service; // final로 선언

    @GetMapping( "/")
    public String index() {

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list................" + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    //    @GetMapping({"/list", "/"})
    //    public String list() {
    //        log.info("GuestbookController.list()메서드 실행.........");
    //
    //
    //        return "/guestbook/list"; // list.html
    //    }

    // ---------------------------------------------------------

    @GetMapping("/register")
    public void register() {
        log.info("register get.........");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {

        log.info("dto..." + dto);

        // 새로 추가된 엔티티의 번호
        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    // @GetMapping("/read")
    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO
            requestDTO, Model model) {

        log.info("gno : " + gno);

        GuestbookDTO dto = service.read(gno);

        model.addAttribute("dto", dto);

    }

}
