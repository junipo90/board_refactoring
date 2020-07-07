package com.example.board_refactoring.board.service;

import com.example.board_refactoring.board.entity.BoardEntity;
import com.example.board_refactoring.board.entity.BoardFileEntity;
import com.example.board_refactoring.board.repository.JpaBoardRepository;
import com.example.board_refactoring.common.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

@Service
public class JpaboardServicImpl implements JpaBoardService {

    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    @Autowired
    FileUtils fileUtils;

    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
    }

    @Override
    public void saveBoard(BoardEntity boardEntity, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        boardEntity.setCreatorId("admin");
        List<BoardFileEntity> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
        if (CollectionUtils.isEmpty(list) == false) {
            boardEntity.setFileList(list);
        }
        jpaBoardRepository.save(boardEntity);
    }

    @Override
    public BoardEntity selectBoardDetail(int boardIdx) throws Exception {
        Optional<BoardEntity> optional = jpaBoardRepository.findById(boardIdx);
        if (optional.isPresent()) {
            BoardEntity boardEntity = optional.get();
            boardEntity.setHitCnt(boardEntity.getHitCnt() + 1);
            jpaBoardRepository.save(boardEntity);

            return boardEntity;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteBoard(int boardIdx) throws Exception {
        jpaBoardRepository.deleteById(boardIdx);
    }

    @Override
    public BoardFileEntity selectBoardFileInformation(int boardIdx,int idx) throws Exception {
            BoardFileEntity boardFileEntity = jpaBoardRepository.findBoardFile(boardIdx, idx);
        return boardFileEntity;
    }
}
