package cafe.jjdev.springboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.springboard.service.BoardService;
import cafe.jjdev.springboard.vo.Board;

@Controller
public class BoardController {
	
    @Autowired // 오토와이어링 수행을 하도록 Autowired Annotation을 사용한다.
    private BoardService boardService;
    
    // 목록
    @GetMapping("/boardList")
    public String boardList(Model model, 			// boardList를 조회하기 위해 Model클래스의 model변수를 담고 호출
				@RequestParam(value="currentPage",	// 화면에서 현재 페이지를 담아 온다.
							  required=false,		// int타입의 currentPage변수를 선언
							  defaultValue="1") int currentPage) {
    	
    // HashMap을 사용하여 map객체참조변수에 boardService클래스의 selectBoardList메서드를 호출해 주소값을 담는다.
	Map<String, Object> map = boardService.selectBoardList(currentPage);
	
	// Model인터페이스의 메서드인 addAttribute메서드를 사용해 map객체를 map이름으로,
	// currentPage변수를 currentPage이름으로 추가한다.
	model.addAttribute("map", map);
	model.addAttribute("currentPage", currentPage);
	// 실행이 되어가는지 콘솔창에 확인
	System.out.println("Request : /boardList");
	
	// template폴더의 boardList.html로 리턴 값을 가지고 이동한다.
	return "boardList";
	}
    
    // 입력 폼
    @GetMapping(value="addBoard")
    public String boardAdd() { // Get방식으로 addBoard값을 받으면 이 메서드를 실행한다
    	
    	// template폴더의 addBoard.html로 리턴 값을 가지고 이동한다.
		return "addBoard";
    }
    
    // 입력 액션
    @PostMapping("addBoard")
    public String boardAdd(Board board) {	// 매핑 주소가 중복되는 것을 방지하기 위해 메서드에 화면에서 받아온 board의 주소값을 선언
    	// Controller가 잘 작동 되었는지 콘솔창에 확인한다.
    	System.out.println("addBoard Action");
    	// boardService클래스의 addBoard메서드에 Board클래스의 board객체참조변수를 넣고 호출한다.
    	boardService.addBoard(board);
    	
    	// 리턴 값을 boardList의 주소로 redirect시킨다.
    	return "redirect:/boardList";
    }
    
    // 수정 폼
    @GetMapping("/modifyBoard")
    public String modifyBoard(	// 수정 화면으로 넘어가기 위해 필요한 boardNo값을
    							// RequestParam Annotation을 사용해 값을 받아와 int 타입의 변수로 선언
    							// boardNo값을 기반으로 하나의 목록을 가져와야 하므로
    							// Model클래스의 model객체참조변수도 같이 선언한다.
    		@RequestParam(value="boardNo")		int boardNo,
    		@RequestParam(value="currentPage")	int currentPage,
    											Model model) {
    	// Board클래스의 객체참조변수 board에 boardService클래스의 getBoard메서드에 boardNo값을 넣고 호출한다.
    	Board board = boardService.getBoard(boardNo);
    	// Model인터페이스의 메서드인 addAttribute메서드를 사용해 board객체를 board이름으로 추가한다.
    	model.addAttribute("board", board);
    	model.addAttribute("currentPage", currentPage);
    	
    	// template폴더의 modifyBoard.html로 리턴 값을 가지고 이동한다.
    	return "modifyBoard";
    }
    
    // 수정 액션
    @PostMapping("/modifyBoard")
    public String modifyBoard(Board board) { // 수정을 완료하기 위해 화면에서 board변수를 선언하고 주소값을 받아온다.
    	// Controller가 잘 진행되는지 콘솔창에 확인
    	System.out.println("modifyBoard Action");
    	// int타입의 result변수에 sampleService클래스의 modifyBoard메서드에 board의 값을 넣고 호출하고 주소값을 담는다.
    	int result = boardService.modifyBoard(board);
    	// result변수가 1이 나온다면 호출 성공, 0이라면 호출이 실패한다.
    	System.out.println(result + "Modify Result");
    	
    	// 리턴 값을 boardList의 주소로 redirect시킨다.
    	return "redirect:/boardList";
    }
    
    // 삭제 폼
    @GetMapping("deleteBoard")
    public String deleteBoard(
    		@RequestParam(value="boardNo")		int boardNo,
    		@RequestParam(value="currentPage")	int currentPage,
    											Model model) {
    	Board board = boardService.getBoard(boardNo);
    	model.addAttribute("board", board);
    	model.addAttribute("currentPage", currentPage);
    	
    	return "deleteBoard";
    }
    
    // 삭제 액션
    @PostMapping("deleteBoard")
    public String deleteBoard(Board board) {
    	System.out.println("Delete Controller");
    	System.out.println(board.getBoardPw() + " <- boardPw");
    	System.out.println(board.getBoardNo() + " <- boardNo");
    	// int타입의 result변수에 boardService클래스의 removeBoard메서드에 boardNo값을 넣고 호출하고 주소값을 담는다. 
    	int result = boardService.removeBoard(board);
    	// result변수가 1이 나온다면 호출 성공, 0이라면 호출이 실패한다.
    	System.out.println(result + " <- Delete Result");
    	
    	// 리턴 값을 boardList의 주소로 redirect시킨다.
    	return "redirect:/boardList";
    }
    
    // 글 상세
    @GetMapping("detailBoard")
    public String detailBoard(
    		@RequestParam(value="boardNo")		int boardNo,
    		@RequestParam(value="currentPage")	int currentPage,
    											Model model) {
    	Board board = boardService.getBoard(boardNo);
    	model.addAttribute("board", board);
    	model.addAttribute("currentPage", currentPage);
    	
    	return "detailBoard";
    }
}