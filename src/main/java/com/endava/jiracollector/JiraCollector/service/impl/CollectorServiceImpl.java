package com.endava.jiracollector.JiraCollector.service.impl;

import com.endava.jiracollector.JiraCollector.repository.BoardRepository;
import com.endava.jiracollector.JiraCollector.repository.IssueRepository;
import com.endava.jiracollector.JiraCollector.repository.SprintRepository;
import com.endava.jiracollector.JiraCollector.service.CollectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CollectorServiceImpl implements CollectorService {

    private  final BoardServiceImpl boardService;
    private  final SprintServiceImpl sprintService;
    private  final IssueServiceImpl issueService;
    private  final BoardRepository boardRepository;
    private  final SprintRepository sprintRepository;
    private  final IssueRepository issueRepository;


    public CollectorServiceImpl(BoardServiceImpl boardService, SprintServiceImpl sprintService, IssueServiceImpl issueService, BoardRepository boardRepository, SprintRepository sprintRepository, IssueRepository issueRepository) {
        this.boardService = boardService;
        this.sprintService = sprintService;
        this.issueService = issueService;
        this.boardRepository = boardRepository;
        this.sprintRepository = sprintRepository;
        this.issueRepository = issueRepository;
    }

    @Override
    public boolean saveContent(){
        deleteContent();
        boardService.saveContent();
        sprintService.saveContent();
        issueService.saveContent();
        return true;

    }

    private void deleteContent(){
        issueRepository.deleteAll();
        sprintRepository.deleteAll();
        boardRepository.deleteAll();
    }


}
