package springStudy.springstudy.service;

import springStudy.springstudy.domain.Board;
import springStudy.springstudy.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public int postUploader(Board board){
        return boardRepository.insert(board);
    }

    public List<Board> findAll(){
        return boardRepository.selectAll();
    }
    public Optional<Board> findOne(int num){
        return boardRepository.selectOne(num);
    }

    public int postEditor(Board board){
        return boardRepository.update(board);
    }

    public int postDelete(int num){
        return boardRepository.delete(num);
    }

    public int cntPls(int num){
        return boardRepository.plsCnt(num);
    }
}
