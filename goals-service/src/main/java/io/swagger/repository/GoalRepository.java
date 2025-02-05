package io.swagger.repository;

import io.swagger.model.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface GoalRepository extends MongoRepository<Goal, String> {
    List<Goal> findByUserId(String userId);
    List<Goal> findByGoalNameContaining(String goalName);
    List<Goal> findByStartDateBefore(Date date);
    List<Goal> findByEndDateAfter(Date date);
    List<Goal> findByStartDateBetween(Date start, Date end);
    List<Goal> findByEndDateBetween(Date start, Date end);
    List<Goal> findByUserIdAndEndDateAfter(String userId, Date date);
    List<Goal> findByUserIdAndStartDateBetween(String userId, Date start, Date end);
    List<Goal> findByEventIdAndUserId(Integer eventId, String userId);

    // Usuwanie
    void deleteByUserId(String userId);
    void deleteByEndDateBefore(Date date);
}
