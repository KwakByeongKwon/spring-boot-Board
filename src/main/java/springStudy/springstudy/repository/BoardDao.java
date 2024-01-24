package springStudy.springstudy.repository;

import springStudy.springstudy.domain.Board;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardDao implements BoardRepository{
    private final DataSource dataSource;
    public BoardDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public int insert(Board board) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "INSERT INTO \"BOARD\" (\"NUM\",\"TITLE\",\"WRITER\",\"CONTENT\",\"REGDATE\",\"CNT\") VALUES (\"BOARD_SEQ\".nextval,?,?,?,sysdate,0)";
        int rs = -1;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,board.getTitle());
            pstmt.setString(2,board.getWriter());
            pstmt.setString(3,board.getContent());
            rs = pstmt.executeUpdate();
            return rs;
        } catch (SQLException e){
            throw new IllegalStateException(e);
        } finally {
            close2(con,pstmt);
        }
    }

    @Override
    public Optional<Board> selectOne(int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM \"BOARD\" WHERE \"NUM\" = ?";
        ResultSet rs = null;
        Board board = null;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,num);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                board = new Board(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getInt(6)
                );
            }
            return Optional.ofNullable(board);
        } catch (SQLException e){
            throw new IllegalStateException(e);
        } finally {
            close3(con,pstmt,rs);
        }
    }

    @Override
    public List<Board> selectAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = "select * from \"BOARD\"";
        ArrayList<Board> boards = new ArrayList<Board>();
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Board vo = new Board(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getInt(6)
                );
                boards.add(vo);
            }
            return boards;
        } catch (SQLException e){
            throw new IllegalStateException(e);
        } finally {
            close3(con,pstmt,rs);
        }
    }

    @Override
    public int update(Board board) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE \"BOARD\" set \"TITLE\"=?,\"CONTENT\"=? WHERE \"NUM\"=?";
        int ret = -1;
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,board.getTitle());
            pstmt.setString(2,board.getWriter());
            pstmt.setInt(3,board.getNum());
            ret = pstmt.executeUpdate();
            return ret;
        }catch (SQLException e){
            throw new IllegalStateException(e);
        } finally {
            close2(con,pstmt);
        }
    }

    @Override
    public int plsCnt(int num){
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = "UPDATE \"BOARD\" SET \"CNT\" = \"CNT\" + 1 WHERE \"NUM\"=?";
        int ret = -1;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,num);
            ret = pstmt.executeUpdate();
            return ret;
        } catch (SQLException e){
            throw new IllegalStateException(e);
        } finally {
            close2(con,pstmt);
        }
    }
    @Override
    public int delete(int num) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int ret = -1;
        String query = "DELETE FROM \"BOARD\" WHERE \"NUM\"=?";
        try{
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,num);
            ret = pstmt.executeUpdate();

            return ret;
        } catch (SQLException e){
            throw new IllegalStateException(e);
        } finally {
            close2(con,pstmt);
        }
    }

    public void close3(Connection con, PreparedStatement pstmt, ResultSet rs){
        try{
            con.close();
            pstmt.close();
            rs.close();
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
    public void close2(Connection con, PreparedStatement pstmt){
        try{
            con.close();
            pstmt.close();
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
}
