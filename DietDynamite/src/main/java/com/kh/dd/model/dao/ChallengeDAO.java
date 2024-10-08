package com.kh.dd.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.dd.model.dto.Board;
import com.kh.dd.model.dto.Pagination;

@Repository
public class ChallengeDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	//게시판 종류 목록 조회
	public List<Map<String, Object>> selectChallengeTypeList() {
		return sqlSession.selectList("challengeMapper.selectChallengeTypeList");
	}	

	//게시글 수 조회
	public int getListCount(int challengeNo) {
		return sqlSession.selectOne("challengeMapper.getListCount", challengeNo);
	}


	//현재 페이지에 해당하는 게시글 목록
	public List<Board> selectChallengeList(int challengeNo, Pagination pagination) {

		int offset = (pagination.getCurrentPage()-1)* pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		return sqlSession.selectList("challengeMapper.selectChallengeList", challengeNo, rowBounds);
	}


	//게시글 상세조회
	public Board selectBoard(Map<String, Object> map) {
		return sqlSession.selectOne("challengeMapper.selectBoard", map);
	}


	//조회수 업데이트
	public int updateReadCnt(int boardNo) {
		return sqlSession.update("challengeMapper.updateReadCnt", boardNo);

	}


	//게시글 수 조회(검색)
	public int getListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("challengeMapper.getListCountForSearch", paramMap);
	}

	//게시글 목록 조회
	public List<Board> selectChallengeList(Map<String, Object> paramMap, Pagination pagination) {

		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		return sqlSession.selectList("challengeMapper.selectBoardListForSearch", paramMap, rowBounds);

	}

	//좋아요 여부확인
	public int boardLikeCheck(Map<String, Object> map) {
		return sqlSession.selectOne("challengeMapper.boardLikeCheck", map);

	}


	//좋아요 삽입
	public int insertBoardLike(Map<String, Integer> paramMap) {
		return sqlSession.insert("challengeMapper.insertBoardLike", paramMap);
	}

	//좋아요삭제
	public int deleteBoardLike(Map<String, Integer> paramMap) {
		return sqlSession.delete("challengeMapper.deleteBoardLike", paramMap);
	}

	//좋아요 개수 조회
	public int countBoardLike(int boardNo) {
		return sqlSession.selectOne("challengeMapper.countBoardLike", boardNo);
	}


	//게시글 삽입
	public int challengeInsert(Board board) {

		int result = sqlSession.insert("challengeMapper.boardInsert", board);
		if(result > 0) result = board.getBoardNo();

		return result;
	}


	//게시글 이미지 삽입
	public int insertBoardImage(Board board) {
		return sqlSession.update("challengeMapper.updateBoardImage", board);
	}


	//게시글 수정
	public int challengeUpdate(Board board) {
		return sqlSession.update("challengeMapper.updateChallenge", board);
	}


	//이미지삭제
	public int imageDelete(Map<String, Object> deleteMap) {
		return sqlSession.update("challengeMapper.imageDelete", deleteMap);
	}

	//삭제한이미지업로드
	public int updateBoardImagePath(Board board) {
		return sqlSession.update("challengeMapper.updateBoardImagePath", board);
	}


	//게시글삭제
	public int challengeDelete(Map<String, Object> map) {
		return sqlSession.update("challengeMapper.challengeDelete",map);
	}

}