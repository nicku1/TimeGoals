package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * Event
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-23T20:13:09.961561888Z[GMT]")


public class Event   {
  @JsonProperty("eventid")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Integer eventid = null;

  @JsonProperty("startdate")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private LocalDate startdate = null;

  @JsonProperty("endtime")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private LocalDate endtime = null;

  @JsonProperty("eventname")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String eventname = null;

  @JsonProperty("description")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String description = null;

  @JsonProperty("userid")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String userid = null;


  public Event eventid(Integer eventid) { 

    this.eventid = eventid;
    return this;
  }

  /**
   * Get eventid
   * @return eventid
   **/
  
  @Schema(description = "")
  
  public Integer getEventid() {  
    return eventid;
  }



  public void setEventid(Integer eventid) { 
    this.eventid = eventid;
  }

  public Event startdate(LocalDate startdate) { 

    this.startdate = startdate;
    return this;
  }

  /**
   * Get startdate
   * @return startdate
   **/
  
  @Schema(description = "")
  
@Valid
  public LocalDate getStartdate() {  
    return startdate;
  }



  public void setStartdate(LocalDate startdate) { 
    this.startdate = startdate;
  }

  public Event endtime(LocalDate endtime) { 

    this.endtime = endtime;
    return this;
  }

  /**
   * Get endtime
   * @return endtime
   **/
  
  @Schema(description = "")
  
@Valid
  public LocalDate getEndtime() {  
    return endtime;
  }



  public void setEndtime(LocalDate endtime) { 
    this.endtime = endtime;
  }

  public Event eventname(String eventname) { 

    this.eventname = eventname;
    return this;
  }

  /**
   * Get eventname
   * @return eventname
   **/
  
  @Schema(description = "")
  
  public String getEventname() {  
    return eventname;
  }



  public void setEventname(String eventname) { 
    this.eventname = eventname;
  }

  public Event description(String description) { 

    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  
  @Schema(description = "")
  
  public String getDescription() {  
    return description;
  }



  public void setDescription(String description) { 
    this.description = description;
  }

  public Event userid(String userid) { 

    this.userid = userid;
    return this;
  }

  /**
   * Get userid
   * @return userid
   **/
  
  @Schema(description = "")
  
  public String getUserid() {  
    return userid;
  }



  public void setUserid(String userid) { 
    this.userid = userid;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.eventid, event.eventid) &&
        Objects.equals(this.startdate, event.startdate) &&
        Objects.equals(this.endtime, event.endtime) &&
        Objects.equals(this.eventname, event.eventname) &&
        Objects.equals(this.description, event.description) &&
        Objects.equals(this.userid, event.userid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventid, startdate, endtime, eventname, description, userid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    eventid: ").append(toIndentedString(eventid)).append("\n");
    sb.append("    startdate: ").append(toIndentedString(startdate)).append("\n");
    sb.append("    endtime: ").append(toIndentedString(endtime)).append("\n");
    sb.append("    eventname: ").append(toIndentedString(eventname)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
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
