package com.endava.jiracollector.JiraCollector.repository;

import com.endava.jiracollector.JiraCollector.entity.Sprint;
import org.springframework.data.repository.CrudRepository;

public interface SprintRepository extends CrudRepository<Sprint, Long> {
}
