package com.endava.jiracollector.JiraCollector.assembler.impl;


import com.endava.jiracollector.JiraCollector.assembler.IssueAssembler;
import com.endava.jiracollector.JiraCollector.dto.IssueDto;
import com.endava.jiracollector.JiraCollector.entity.Issue;

public class IssueAssemblerImpl extends BaseAssembler<IssueDto, Issue> implements IssueAssembler {

    @Override
    public IssueDto toDto(Issue issue){
        final IssueDto result  = new IssueDto();

        result.setBoardType(issue.getBoardType());
        result.setName(issue.getName());
        result.setUrl(issue.getUrl());
        result.setIdentifier(issue.getIdentifier());
        result.setAssignee(issue.getAssignee());
        result.setModificationDate(issue.getModificationDate());
        result.setRegistrationDate(issue.getRegistrationDate());

        return result;
    }

    @Override
    public Issue toEntity(IssueDto issueDto){
        final Issue result = new Issue();

        result.setName(issueDto.getName());
        result.setBoardType(issueDto.getBoardType());
        result.setUrl(issueDto.getUrl());
        result.setIdentifier(issueDto.getIdentifier());
        result.setAssignee(issueDto.getAssignee());
        result.setRegistrationDate(issueDto.getRegistrationDate());
        result.setModificationDate(issueDto.getModificationDate());

        return result;
    }

}
