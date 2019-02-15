package cafe.jjdev.springboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.springboard.mapper.BoardMapper;
import cafe.jjdev.springboard.vo.Board;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public Board getBoard(int boardNo) {
		
		return boardMapper.selectBoard(boardNo);
	}
	
	public int getBoardCount() {
		
		return boardMapper.selectBoardCount();
	}
	
	public int addBoard(Board board) {
		
		return boardMapper.insertBoard(board);
	}
	
	public int removeBoard(Board board) {
		
		return boardMapper.deleteBoard(board);
	}
	
	public int modifyBoard(Board board) {
		
		return boardMapper.updateBoard(board);
	}
	
	public Map<String, Object> selectBoardList(int currentPage) {
		// 페이지를 보여줄 값은 항상 변해야 하지 않으므로 10이라는 상수값을 지정한다.
		final int ROW_PER_PAGE = 10;
		// 쿼리문장에 뿌려하는 게시물의 시작점을 잡아야 하므로 int타입의 startPage변수를 선언한다.
		int startPage = (currentPage-1)*ROW_PER_PAGE;
		// HashMap을 이용해 객체참조변수 map을 선언 
		Map<String, Integer> map = new HashMap<String, Integer>();
		// map변수에 currentpage, rowPerPage, startPage라는 각각의 이름을 가진 변수들을 넣는다.
		map.put("currentPage", currentPage);
		map.put("rowPerPage", ROW_PER_PAGE);
		map.put("startPage", startPage);
		
		// 페이지 작업을 하기 위해 int타입의 boardCount변수와 lastPage변수를 선언
		int boardCount = boardMapper.selectBoardCount();
		int lastPage = (int)(Math.ceil(boardCount / ROW_PER_PAGE));
		// 마지막 페이지와 게시물의 총 갯수가 잘 나오는 확인
		System.out.println(boardCount + " <- boardCount");
		System.out.println(lastPage + " <- lastPage");
		
		// 컨트롤러에 리턴을 하기 위해 returnMap 객체참조변수를 선언
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// map변수에 list, boardCount, lastPage라는 각각의 이름을 가진 변수들을 넣는다.
		returnMap.put("list", boardMapper.selectBoardList(map));
		returnMap.put("boardCount", boardCount);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
}
