package io.swagger.model;

import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import org.springframework.validation.annotation.Validated;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;

/**
 * Raport
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-23T20:13:09.961561888Z[GMT]")


public class Raport   {
  @JsonProperty("raportid")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)
  // FAIL setting if the value is null
  private Integer raportid = null;

  @JsonProperty("userid")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String userid = null;

  @JsonProperty("content")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String content = null;

  @JsonProperty("raport_date")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private LocalDateTime reportDate = null;


  public Raport raportid(Integer raportid) { 

    this.raportid = raportid;
    return this;
  }

  /**
   * Get raportid
   * @return raportid
   **/
  
  @Schema(description = "")
  
  public Integer getRaportid() {  
    return raportid;
  }



  public void setRaportid(Integer raportid) { 
    this.raportid = raportid;
  }

  public Raport userid(String userid) { 

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

  public Raport content(String content) { 

    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
   **/
  
  @Schema(description = "")
  
  public String getContent() {  
    return content;
  }



  public void setContent(String content) { 
    this.content = content;
  }

  public Raport reportDate(LocalDateTime reportDate) {

    this.reportDate = reportDate;
    return this;
  }

  /**
   * Get reportDate
   * @return reportDate
   **/
  
  @Schema(description = "")
  
@Valid
  public LocalDateTime getReportDate() {
    return reportDate;
  }



  public void setReportDate(LocalDateTime reportDate) {
    this.reportDate = reportDate;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Raport raport = (Raport) o;
    return Objects.equals(this.raportid, raport.raportid) &&
        Objects.equals(this.userid, raport.userid) &&
        Objects.equals(this.content, raport.content) &&
        Objects.equals(this.reportDate, raport.reportDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(raportid, userid, content, reportDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Raport {\n");
    
    sb.append("    raportid: ").append(toIndentedString(raportid)).append("\n");
    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    reportDate: ").append(toIndentedString(reportDate)).append("\n");
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
