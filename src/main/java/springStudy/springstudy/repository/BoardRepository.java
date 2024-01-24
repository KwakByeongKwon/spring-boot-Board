package springStudy.springstudy.repository;

import springStudy.springstudy.domain.Board;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    // 삽입
    int insert(Board board);

    // 조회
    Optional<Board> selectOne(int num);

    List<Board> selectAll();

    // 조회수 증가
    int plsCnt(int num);

    // 수정
    int update(Board board);

    // 삭제
    int delete(int num);
}
