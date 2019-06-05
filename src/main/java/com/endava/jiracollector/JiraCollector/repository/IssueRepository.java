package com.endava.jiracollector.JiraCollector.repository;

import com.endava.jiracollector.JiraCollector.entity.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepository extends CrudRepository<Issue , Long> {
}
