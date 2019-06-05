package com.endava.jiracollector.JiraCollector.assembler.impl;


import com.endava.jiracollector.JiraCollector.assembler.SprintAssembler;
import com.endava.jiracollector.JiraCollector.dto.SprintDto;
import com.endava.jiracollector.JiraCollector.entity.Sprint;

public class SprintAssemblerImpl extends BaseAssembler<SprintDto, Sprint> implements SprintAssembler {

    @Override
    public SprintDto toDto(Sprint sprint){
        final SprintDto result  = new SprintDto();

        result.setIdentifier(sprint.getIdentifier());
        result.setName(sprint.getName());
        result.setBoardType(result.getBoardType());
        result.setSprintStatus(sprint.getSprintStatus());
        result.setUrl(sprint.getUrl());
        result.setStartDate(sprint.getStartDate());
        result.setEndDate(sprint.getEndDate());
        result.setRegistrationDate(sprint.getRegistrationDate());
        result.setModificationDate(sprint.getModificationDate());


        return result;
    }

    @Override
    public Sprint toEntity(SprintDto sprintDto){
        final Sprint result = new Sprint();

        result.setIdentifier(sprintDto.getIdentifier());
        result.setName(sprintDto.getName());
        result.setSprintStatus(sprintDto.getSprintStatus());
        result.setUrl(sprintDto.getUrl());
        result.setBoardType(sprintDto.getBoardType());
        result.setModificationDate(sprintDto.getModificationDate());
        result.setRegistrationDate(sprintDto.getRegistrationDate());
        result.setStartDate(sprintDto.getStartDate());
        result.setEndDate(sprintDto.getEndDate());

        return result;
    }

}
