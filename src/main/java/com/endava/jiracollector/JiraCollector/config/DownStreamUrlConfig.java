package com.endava.jiracollector.JiraCollector.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DownStreamUrlConfig {

    private final String boardUrl;
    private final String kanbanBoardUrl;

    public DownStreamUrlConfig(@Value("${jira.base-url}") String boardUrl, @Value("${jira.base-url-kanban}") String kanbanBoardUrl) {
        this.boardUrl = boardUrl;
        this.kanbanBoardUrl = kanbanBoardUrl;
    }

    public String getBoardUrl() {
        return boardUrl;
    }
    public String getKanbanBoardUrl() { return kanbanBoardUrl; }

}
