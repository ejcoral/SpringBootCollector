package com.endava.jiracollector.JiraCollector.assembler.impl;

import com.endava.jiracollector.JiraCollector.assembler.Assembler;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAssembler<DTO, ENTITY> implements Assembler<DTO, ENTITY> {

    @Override
    public List<DTO> toDtos(List<ENTITY> entities) {
        final List<DTO> result = new ArrayList<>(entities.size());
        entities.forEach(entity -> result.add(toDto(entity)));
        return result;
    }

    @Override
    public List<ENTITY> toEntities(List<DTO> dtos) {
        final List<ENTITY> result = new ArrayList<>(dtos.size());
        dtos.forEach(dto -> result.add(toEntity(dto)));
        return result;
    }
}
