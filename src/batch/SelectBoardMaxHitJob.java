package batch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.BoardDAO;
import dto.BoardDTO;

public class SelectBoardMaxHitJob implements Job {

	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		/*
		List<BoardDTO> list = BoardDAO.getInstance().selectMaxHit();
		for (BoardDTO boardDTO : list) { 	
		// 이건 리스트를 쓸 필요가 없다. :: 게시글 하나만 가져오는거니까.==> BoardDAO, for문 부분 수정
		}
		*/
		BoardDTO boardDTO = BoardDAO.getInstance().selectMaxHit();
		System.out.println("=====최대 조회수 게시글=====");
		System.out.println("제목 : " + boardDTO.getTitle());
		System.out.println("내용 : " + boardDTO.getContent());
		System.out.println("조회수 : " + boardDTO.getHit());
		
	}

}
