package springStudy.springstudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springStudy.springstudy.controller.form.*;
import springStudy.springstudy.domain.Board;
import springStudy.springstudy.service.BoardService;

import java.util.List;

@Controller
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시판 목록
    @GetMapping("/board")
    public String list(Model model){
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards",boards);
        return "board/boardList";
    }

    // 글등록
    @GetMapping("/board/new")
    public String createForm(){
        return "board/createForm";
    }

    @PostMapping("/board/new")
    public String create(BoardRegisterForm form){
        Board board = new Board();
        board.setTitle(form.getTitle());
        board.setWriter(form.getWriter());
        board.setContent(form.getContent());
        boardService.postUploader(board);
        return "redirect:/board";
    }

    // 글 조회
    @GetMapping("/board/detail/{id}")
    public String detail(@PathVariable("id") int num,Model model){
        Board board = boardService.findOne(num).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
        boardService.cntPls(num);
        model.addAttribute("board",board);
        return "board/boardDetail";
    }

    // 글 수정
    @GetMapping("/board/edit/{num}")
    public String editForm(@PathVariable("num") int num, Model model){
        Board board = boardService.findOne(num).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
        model.addAttribute("board",board);
        return "board/boardEdit";
    }

    @PostMapping("/board/edit/{num}")
    public String edit(@PathVariable("num") int num, BoardEditForm form){
        Board board = new Board();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        board.setNum(num);
        boardService.postEditor(board);
        return "redirect:/board/detail/" +  num;
    }

    @GetMapping("/board/delete/{num}")
    public String deleteForm(@PathVariable("num")int num, Model model){
        Board board = boardService.findOne(num).orElseThrow(() -> new IllegalStateException("존재하지 않는 게시글 입니다."));
        model.addAttribute("board",board);
        return "board/boardDelete";
    }

    @PostMapping("board/delete/{num}")
    public String delete(@PathVariable("num") int num){
        boardService.postDelete(num);
        return "redirect:/board";
    }
}
