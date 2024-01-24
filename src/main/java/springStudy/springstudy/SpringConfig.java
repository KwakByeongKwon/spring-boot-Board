package springStudy.springstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springStudy.springstudy.repository.BoardDao;
import springStudy.springstudy.repository.BoardRepository;
import springStudy.springstudy.service.BoardService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public BoardService boardService(){
        return new BoardService(boardRepository());
    }

    @Bean
    public BoardRepository boardRepository(){
        return new BoardDao(dataSource);
    }
}
