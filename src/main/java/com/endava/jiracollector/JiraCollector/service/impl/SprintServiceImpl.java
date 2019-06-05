package com.endava.jiracollector.JiraCollector.service.impl;

import com.endava.jiracollector.JiraCollector.config.DownStreamUrlConfig;
import com.endava.jiracollector.JiraCollector.config.JiraConfig;
import com.endava.jiracollector.JiraCollector.entity.Board;
import com.endava.jiracollector.JiraCollector.entity.Sprint;
import com.endava.jiracollector.JiraCollector.repository.BoardRepository;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SprintServiceImpl implements CollectorService {

    private static final String authorizationHeader = "Authorization";
    private final JiraConfig jiraConfig;
    private final DownStreamUrlConfig downStreamUrlConfig;
    private final BoardRepository boardRepository;
    private final SprintRepository sprintRepository;
    private static final String boardType = "scrum";


    public SprintServiceImpl(DownStreamUrlConfig downStreamUrlConfig, JiraConfig jiraConfig, BoardRepository boardRepository, SprintRepository sprintRepository) {
        this.downStreamUrlConfig = downStreamUrlConfig;
        this.jiraConfig = jiraConfig;
        this.boardRepository = boardRepository;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public boolean saveContent(){

        return populateSprintInformation();

    }

    private boolean populateSprintInformation(){

        List<Board> result = getScrumBoards();

        if(!result.isEmpty())
            result.forEach(board -> {
                try {
                    saveSprint(getSprintRequest(board.getIdentifier()).get(), board);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

        return true;
    }

    private Optional<JSONObject> getSprintRequest(String boardIdentifier){

        try {
            final RequestEntity getBoardInformation = RequestEntity
                    .get(URI.create(downStreamUrlConfig.getBoardUrl() + "/" + boardIdentifier+ "/sprint?state=active"))
                    .header(authorizationHeader, jiraConfig.getAuthenticationToken())
                    .accept(MediaType.APPLICATION_JSON)
                    .build();

            final ResponseEntity<String> response = newRestClient().exchange(getBoardInformation, String.class);

            if(response.getStatusCode() == HttpStatus.OK)
                return Optional.of(new JSONObject(response.getBody()));

        }catch (Exception e){
            log.error("Error Sprint API request: "+e.getMessage());
        }


        return Optional.empty();
    }

    private List<Board> getScrumBoards(){
        return boardRepository.findBoardByBoardType(boardType);
    }

    private void saveSprint(JSONObject result, Board board) throws JSONException {
        JSONArray data = result.getJSONArray("values");
        JSONObject values = data.getJSONObject(data.length() - 1);

        Sprint sprint = new Sprint();
        sprint.setBoardId(board);
        sprint.setBoardType(board.getBoardType());
        sprint.setIdentifier(values.getString("id"));
        sprint.setName(values.getString("name"));
        sprint.setUrl(values.getString("self"));
        sprint.setSprintStatus(true);
        sprint.setStartDate(values.getString("startDate"));
        sprint.setEndDate(values.getString("endDate"));

        sprintRepository.save(sprint);

    }

    private RestTemplate newRestClient(){ return new RestTemplate();}

}
