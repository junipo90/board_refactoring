package com.example.board_refactoring.board.controller;

import com.example.board_refactoring.board.entity.BoardEntity;
import com.example.board_refactoring.board.entity.BoardFileEntity;
import com.example.board_refactoring.board.service.JpaBoardService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class RestBoardController {

    @Autowired
    private JpaBoardService jpaBoardService;

//    @RequestMapping("/board/openBoardList.do")
    @GetMapping("/jpa/board")
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("board/jpaBoardList");

        List<BoardEntity> list = jpaBoardService.selectBoardList();
        mv.addObject("list", list);
        return mv;
    }

//    @RequestMapping("/board/openBoardWrite.do")
    @GetMapping("/jpa/board/write")
    public String openBoardWrite() throws Exception {
        return "/board/jpaBoardWrite";
    }

//    @RequestMapping("/board/insertBoard.do")
    @PostMapping("/jpa/board/write")
    public String insertBoard(BoardEntity boardEntity, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        jpaBoardService.saveBoard(boardEntity, multipartHttpServletRequest);
        return "redirect:/jpa/board";
    }

//    @RequestMapping("/board/openBoardDetail.do")
    @GetMapping("/jpa/board/{boardIdx}")
    public ModelAndView openBoardDetail(@PathVariable int boardIdx) throws Exception {
        ModelAndView mv = new ModelAndView("/board/jpaBoardDetail");

        BoardEntity boardEntity = jpaBoardService.selectBoardDetail(boardIdx);
        mv.addObject("board", boardEntity);

        return mv;
    }

//    @PutMapping("/jpa/board/{boardIdx}")
    @RequestMapping(value = "/jpa/board/{boardIdx}", method = {RequestMethod.PUT, RequestMethod.POST})
    public String updateBoard(BoardEntity boardEntity) throws Exception {
        jpaBoardService.saveBoard(boardEntity, null);
        return "redirect:/jpa/board";
    }

//    @DeleteMapping("/jpa/board/{boardIdx}")
    @RequestMapping(value = "/jpa/board/delete/{boardIdx}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteBoard(@PathVariable int boardIdx) throws Exception {
        jpaBoardService.deleteBoard(boardIdx);
        return "redirect:/jpa/board";
    }

//    @RequestMapping("/board/downloadBoardFile.do")
    @GetMapping("/jpa/board/file")
    public void downloadBoardFile(@RequestParam int boardIdx, @RequestParam int idx,
                                  HttpServletResponse response) throws Exception{
        BoardFileEntity boardFileEntity = jpaBoardService.selectBoardFileInformation(boardIdx, idx);
        if (!ObjectUtils.isEmpty(boardFileEntity)){
            String fileName = boardFileEntity.getOriginalFileName();

            byte[] files = FileUtils.readFileToByteArray(new File(boardFileEntity.getStoredFilePath()));

            response.setContentType("application/octet-stream");
            response.setContentLength(files.length);
            response.setHeader("Content-Disposition", "attachment; fileName=\"" +
                    URLEncoder.encode(fileName, "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            response.getOutputStream().write(files);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}
