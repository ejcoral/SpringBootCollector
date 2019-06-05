package com.endava.jiracollector.JiraCollector.service.impl;

import com.endava.jiracollector.JiraCollector.config.DownStreamUrlConfig;
import com.endava.jiracollector.JiraCollector.config.JiraConfig;
import com.endava.jiracollector.JiraCollector.entity.Board;
import com.endava.jiracollector.JiraCollector.entity.Issue;
import com.endava.jiracollector.JiraCollector.entity.Sprint;
import com.endava.jiracollector.JiraCollector.repository.IssueRepository;
import com.endava.jiracollector.JiraCollector.repository.SprintRepository;
import com.endava.jiracollector.JiraCollector.service.CollectorService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IssueServiceImpl implements CollectorService {

    private final DownStreamUrlConfig downStreamUrlConfig;
    private static final String authorizationHeader = "Authorization";
    private final JiraConfig jiraConfig;
    private final SprintRepository sprintRepository;
    private final IssueRepository issueRepository;

    public IssueServiceImpl(DownStreamUrlConfig downStreamUrlConfig, JiraConfig jiraConfig, SprintRepository sprintRepository, IssueRepository issueRepository) {
        this.downStreamUrlConfig = downStreamUrlConfig;
        this.jiraConfig = jiraConfig;
        this.sprintRepository = sprintRepository;
        this.issueRepository = issueRepository;
    }

    @Override
    public boolean saveContent(){
        return populateIssueInformation();

    }

    private boolean populateIssueInformation(){
        List<Sprint> result = getSprints();
        result.forEach(sprint -> {
            try {
                saveIssue(getIssueRequest(sprint.getBoardId().getIdentifier(), sprint.getIdentifier()).get(), sprint.getBoardId().getBoardType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        return true;
    }

    private List<Sprint> getSprints(){
        List<Sprint> sprints = new ArrayList<>();
        sprintRepository.findAll().forEach(sprint -> sprints.add(sprint));
        return sprints;


    }

    private Optional<JSONObject> getIssueRequest(String boardIdentifier, String sprintId){

        try {
            final RequestEntity getBoardInformation = RequestEntity
                    .get(URI.create(downStreamUrlConfig.getBoardUrl() + "/" + boardIdentifier+ "/sprint/"+sprintId+"/issue?fields=summary,status,assignee"))
                    .header(authorizationHeader, jiraConfig.getAuthenticationToken())
                    .accept(MediaType.APPLICATION_JSON)
                    .build();

            final ResponseEntity<String> response = newRestClient().exchange(getBoardInformation, String.class);

            if(response.getStatusCode() == HttpStatus.OK)
                return Optional.of(new JSONObject(response.getBody()));

        }catch (Exception e){
            log.error("" +
                    "Error Issue API request: "+e.getMessage());
        }


        return Optional.empty();
    }

    private void saveIssue(JSONObject result, String boardType) throws JSONException {
        JSONArray data = result.getJSONArray("issues");

        for (int i=0; i< data.length(); i++) {
            JSONObject values = data.getJSONObject(i);

            Issue issue = new Issue();
            issue.setBoardType(boardType);
            issue.setName(values.getJSONObject("fields").getString("summary"));
            issue.setUrl(values.getString("self"));
            issue.setIdentifier(values.getString("key"));
            issue.setAssignee((values.getJSONObject("fields").optJSONObject("assignee")== null)? "" : values.getJSONObject("fields").getJSONObject("assignee").getString("name"));

            issueRepository.save(issue);
        }

    }


    private RestTemplate newRestClient(){ return new RestTemplate();}

}
