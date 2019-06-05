package com.endava.jiracollector.JiraCollector.assembler;

import java.util.List;

public interface Assembler<DTO, ENTITY> {

    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);

    List<DTO> toDtos(List<ENTITY> entities);

    List<ENTITY> toEntities(List<DTO> dtos);

}
