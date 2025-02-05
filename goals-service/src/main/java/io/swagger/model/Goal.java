package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.Document;
import org.threeten.bp.LocalDate;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Goal
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-24T19:33:46.963266381Z[GMT]")

@Document(collection = "goals")
public class Goal   {
  @JsonProperty("EventId")

  private Integer eventId = null;

  @JsonProperty("StartDate")

  private LocalDate startDate = null;

  @JsonProperty("EndDate")

  private LocalDate endDate = null;

  @JsonProperty("GoalName")

  private String goalName = null;

  @JsonProperty("Description")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String description = null;

  @JsonProperty("UserId")

  private String userId = null;


  public Goal eventId(Integer eventId) { 

    this.eventId = eventId;
    return this;
  }

  /**
   * Unique identifier of the event associated with the goal
   * @return eventId
   **/
  
  @Schema(required = true, description = "Unique identifier of the event associated with the goal")
  
  @NotNull
  public Integer getEventId() {  
    return eventId;
  }



  public void setEventId(Integer eventId) { 

    this.eventId = eventId;
  }

  public Goal startDate(LocalDate startDate) { 

    this.startDate = startDate;
    return this;
  }

  /**
   * Start date of the goal
   * @return startDate
   **/
  
  @Schema(required = true, description = "Start date of the goal")
  
@Valid
  @NotNull
  public LocalDate getStartDate() {  
    return startDate;
  }



  public void setStartDate(LocalDate startDate) { 

    this.startDate = startDate;
  }

  public Goal endDate(LocalDate endDate) { 

    this.endDate = endDate;
    return this;
  }

  /**
   * End date of the goal
   * @return endDate
   **/
  
  @Schema(required = true, description = "End date of the goal")
  
@Valid
  @NotNull
  public LocalDate getEndDate() {  
    return endDate;
  }



  public void setEndDate(LocalDate endDate) { 

    this.endDate = endDate;
  }

  public Goal goalName(String goalName) { 

    this.goalName = goalName;
    return this;
  }

  /**
   * Name of the goal
   * @return goalName
   **/
  
  @Schema(required = true, description = "Name of the goal")
  
  @NotNull
@Size(max=255)   public String getGoalName() {  
    return goalName;
  }



  public void setGoalName(String goalName) { 

    this.goalName = goalName;
  }

  public Goal description(String description) { 

    this.description = description;
    return this;
  }

  /**
   * Description of the goal
   * @return description
   **/
  
  @Schema(description = "Description of the goal")
  
@Size(max=500)   public String getDescription() {  
    return description;
  }



  public void setDescription(String description) { 
    this.description = description;
  }

  public Goal userId(String userId) { 

    this.userId = userId;
    return this;
  }

  /**
   * ID of the user who created the goal
   * @return userId
   **/
  
  @Schema(required = true, description = "ID of the user who created the goal")
  
  @NotNull
@Size(max=255)   public String getUserId() {  
    return userId;
  }



  public void setUserId(String userId) { 

    this.userId = userId;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Goal goal = (Goal) o;
    return Objects.equals(this.eventId, goal.eventId) &&
        Objects.equals(this.startDate, goal.startDate) &&
        Objects.equals(this.endDate, goal.endDate) &&
        Objects.equals(this.goalName, goal.goalName) &&
        Objects.equals(this.description, goal.description) &&
        Objects.equals(this.userId, goal.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventId, startDate, endDate, goalName, description, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Goal {\n");
    
    sb.append("    eventId: ").append(toIndentedString(eventId)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    goalName: ").append(toIndentedString(goalName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
