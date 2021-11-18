package batch;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.BoardDAO;
import dto.BoardDTO;

public class SelectBoardMaxHitJob implements Job {

	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		List<BoardDTO> list = BoardDAO.getInstance().selectMaxHit();
		for (BoardDTO boardDTO : list) {
		System.out.println("=====최대 조회수 게시글=====");
		System.out.println("제목 : " + boardDTO.getTitle());
		System.out.println("내용 : " + boardDTO.getContent());
		System.out.println("조회수 : " + boardDTO.getHit());
		}
		
	}

}
