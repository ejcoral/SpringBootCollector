package com.endava.jiracollector.JiraCollector.assembler.impl;

import com.endava.jiracollector.JiraCollector.assembler.BoardAssembler;
import com.endava.jiracollector.JiraCollector.dto.BoardDto;
import com.endava.jiracollector.JiraCollector.entity.Board;

public class BoardAssemblerImpl extends BaseAssembler<BoardDto, Board> implements BoardAssembler {

    @Override
    public BoardDto toDto(Board board){
        final BoardDto result  = new BoardDto();
        result.setIdentifier(board.getIdentifier());
        result.setBoardType(board.getBoardType());
        result.setName(board.getName());
        result.setUrl(board.getUrl());
        result.setRegistrationDate(board.getRegistrationDate());
        result.setModificationDate(board.getModificationDate());

        return result;
    }

    @Override
    public Board toEntity(BoardDto boardDto){
        final Board result = new Board();
        result.setIdentifier(boardDto.getIdentifier());
        result.setName(boardDto.getName());
        result.setUrl(boardDto.getUrl());
        result.setRegistrationDate(boardDto.getRegistrationDate());
        result.setBoardType(boardDto.getBoardType());
        result.setModificationDate(boardDto.getModificationDate());

        return result;
    }

}
