package com.endava.jiracollector.JiraCollector.repository;

import com.endava.jiracollector.JiraCollector.entity.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findBoardByBoardType(String BoardType);
}
