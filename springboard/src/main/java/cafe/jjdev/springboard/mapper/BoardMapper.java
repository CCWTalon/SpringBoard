package cafe.jjdev.springboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.springboard.vo.Board;

@Mapper
public interface BoardMapper {
	int Stop();
	int indexBoard();
	int insertBoard(Board board);
	Board selectBoard(int boardNo);
	List<Board> selectBoardList(Map<String, Integer> map);
	int selectBoardCount();
	int updateBoard(Board board);
	int deleteBoard(Board board);
}
