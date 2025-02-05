package io.swagger.api;

import io.swagger.model.Goal;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.repository.GoalRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-24T19:33:46.963266381Z[GMT]")
@RestController
public class GoalsApiController implements GoalsApi {

    private static final Logger log = LoggerFactory.getLogger(GoalsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final GoalRepository goalRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public GoalsApiController(ObjectMapper objectMapper, HttpServletRequest request, GoalRepository goalRepository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.goalRepository = goalRepository;
    }

    @PostMapping("/goals")
    public ResponseEntity<Goal> createGoal(
            @Parameter(in = ParameterIn.DEFAULT, description = "Goal object that needs to be created", required=true, schema=@Schema())
            @Valid @RequestBody Goal body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Goal savedGoal = goalRepository.save(body);

                return new ResponseEntity<>(savedGoal, HttpStatus.CREATED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Goal>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Goal>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Void> deleteGoal(
            @Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema())
            @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");

        try{
            if(goalRepository.existsById(id))
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            goalRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error("Error while deleting goal: " + id, e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/goals")
    public ResponseEntity<List<Goal>> getAllGoals() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Goal> goals = goalRepository.findAll();

                return new ResponseEntity<>(goals, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Goal>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Goal>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/goals/{id}")
    public ResponseEntity<Goal> getGoalById(@Parameter(in = ParameterIn.PATH, description = "ID of the goal", required=true, schema=@Schema()) @PathVariable("id") String id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Goal>(objectMapper.readValue("{\n  \"StartDate\" : \"2000-01-23\",\n  \"Description\" : \"Description\",\n  \"UserId\" : \"UserId\",\n  \"EventId\" : 0,\n  \"GoalName\" : \"GoalName\",\n  \"EndDate\" : \"2000-01-23\"\n}", Goal.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Goal>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Goal>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Goal> updateGoal(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id
,@Parameter(in = ParameterIn.DEFAULT, description = "Goal object that needs to be updated", required=true, schema=@Schema()) @Valid @RequestBody Goal body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Goal>(objectMapper.readValue("{\n  \"StartDate\" : \"2000-01-23\",\n  \"Description\" : \"Description\",\n  \"UserId\" : \"UserId\",\n  \"EventId\" : 0,\n  \"GoalName\" : \"GoalName\",\n  \"EndDate\" : \"2000-01-23\"\n}", Goal.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Goal>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Goal>(HttpStatus.NOT_IMPLEMENTED);
    }

}
