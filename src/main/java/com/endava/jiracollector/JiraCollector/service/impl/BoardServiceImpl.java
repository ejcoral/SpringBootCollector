package com.endava.jiracollector.JiraCollector.service.impl;

import com.endava.jiracollector.JiraCollector.config.DownStreamUrlConfig;
import com.endava.jiracollector.JiraCollector.config.JiraConfig;
import com.endava.jiracollector.JiraCollector.entity.Board;
import com.endava.jiracollector.JiraCollector.repository.BoardParametricRepository;
import com.endava.jiracollector.JiraCollector.repository.BoardRepository;
import com.endava.jiracollector.JiraCollector.service.CollectorService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BoardServiceImpl implements CollectorService {

    private static final String authorizationHeader = "Authorization";
    private  final DownStreamUrlConfig downStreamUrlConfig;
    private final JiraConfig jiraConfig;
    private final BoardParametricRepository boardParametricRepository;
    private final BoardRepository boardRepository;


    @Autowired
    public BoardServiceImpl(DownStreamUrlConfig downStreamUrlConfig, JiraConfig jiraConfig, BoardParametricRepository boardParametricRepository, BoardRepository boardRepository) {
        this.downStreamUrlConfig = downStreamUrlConfig;
        this.jiraConfig = jiraConfig;
        this.boardParametricRepository = boardParametricRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public boolean saveContent(){
        return populateBoardInformation();

    }

    private boolean populateBoardInformation(){

        List<String> result = getActiveBoards();

        if (!result.isEmpty()){
            result.forEach(idBoard-> getBoardRequest(idBoard).ifPresent(item -> {
                try {
                    saveBoard(item);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }));
            return true;
        }

        return false;
    }

    private Optional<JSONObject> getBoardRequest(String boardIdentifier){

        try {
            final RequestEntity getBoardInformation = RequestEntity
                    .get(URI.create(downStreamUrlConfig.getBoardUrl() + "/" + boardIdentifier))
                    .header(authorizationHeader, jiraConfig.getAuthenticationToken())
                    .accept(MediaType.APPLICATION_JSON)
                    .build();

            final ResponseEntity<String> response = newRestClient().exchange(getBoardInformation, String.class);

            if(response.getStatusCode() == HttpStatus.OK)
                    return Optional.of(new JSONObject(response.getBody()));

        }catch (Exception e){
            log.error("Error Board API request: "+e.getMessage());
        }


        return Optional.empty();
    }

    private List<String> getActiveBoards(){
        List<String> boards = new ArrayList<>();
        boardParametricRepository.findAll().forEach(board -> boards.add(board.getIdentifier()));
        return boards;
    }


    private void saveBoard(JSONObject result) throws JSONException {

        Board board = new Board();
        board.setName(result.getString("name"));
        board.setUrl(result.getString("self"));
        board.setBoardType(result.getString("type"));
        board.setIdentifier(result.getString("id"));
        boardRepository.save(board);

    }

    private RestTemplate newRestClient(){ return new RestTemplate();}

}
