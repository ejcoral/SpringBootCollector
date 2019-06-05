package com.endava.jiracollector.JiraCollector.assembler.impl;

import com.endava.jiracollector.JiraCollector.assembler.BoardParametricAssembler;
import com.endava.jiracollector.JiraCollector.dto.BoardParametricDto;
import com.endava.jiracollector.JiraCollector.entity.BoardParametric;

public class BoardParametricAssemblerImpl extends BaseAssembler<BoardParametricDto, BoardParametric> implements BoardParametricAssembler {

    @Override
    public BoardParametricDto toDto(BoardParametric boardParametric){
        final BoardParametricDto result  = new BoardParametricDto();

        result.setName(boardParametric.getName());
        result.setIdentifier(boardParametric.getIdentifier());
        result.setRegistrationDate(boardParametric.getRegistrationDate());
        result.setModificationDate(boardParametric.getRegistrationDate());

        return result;
    }

    @Override
    public BoardParametric toEntity(BoardParametricDto boardParametricDto){
        final BoardParametric result = new BoardParametric();

        result.setIdentifier(boardParametricDto.getIdentifier());
        result.setName(boardParametricDto.getName());
        result.setRegistrationDate(boardParametricDto.getRegistrationDate());
        result.setModificationDate(boardParametricDto.getRegistrationDate());


        return result;
    }

}
