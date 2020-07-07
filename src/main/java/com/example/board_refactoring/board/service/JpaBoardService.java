package com.example.board_refactoring.board.service;

import com.example.board_refactoring.board.dto.BoardDto;
import com.example.board_refactoring.board.entity.BoardEntity;
import com.example.board_refactoring.board.entity.BoardFileEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface JpaBoardService {

    List<BoardEntity> selectBoardList() throws Exception;

    void saveBoard(BoardEntity boardEntity, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    BoardEntity selectBoardDetail(int boardIdx) throws Exception;

    void deleteBoard(int boardIdx) throws Exception;

    BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception;
}
