package io.swagger.api;

import io.swagger.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Raport;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.threeten.bp.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-23T20:13:09.961561888Z[GMT]")
@RestController
public class EventsApiController implements EventsApi {

    private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EventsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> eventsEventIdDelete(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("eventId") Integer eventId
) {
        String query = "DELETE FROM event WHERE eventid = ?;";
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Ustawienie parametrów dla zapytania
            preparedStatement.setString(1, eventId.toString());

            // Wykonanie zapytania
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return new ResponseEntity<>(HttpStatus.OK); // Zwracamy 201 Created, jeśli dodano rekord
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Błąd, jeśli nie dodano rekordu
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Błąd serwera
        }
    }

    public ResponseEntity<Event> eventsEventIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("eventId") Integer eventId
) {
        String accept = "application/json";
        if (accept != null && accept.contains("application/json")) {
            Event event = new Event();
            String query = "SELECT * FROM event WHERE eventid = ?;";

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
                 PreparedStatement preparedStatement = connection.prepareStatement(query);){
                preparedStatement.setInt(1, eventId);
                try(ResultSet resultSet = preparedStatement.executeQuery();) {

                    while (resultSet.next()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        event.setEventid(resultSet.getInt("eventid"));
                        event.setUserid(resultSet.getString("userid"));
                        event.setEventname(resultSet.getString("eventname"));
                        event.setDescription(resultSet.getString("description"));
                        event.setStartdate(LocalDateTime.parse( resultSet.getString("startdate"),formatter));
                        event.setEndtime(LocalDateTime.parse( resultSet.getString("endtime"),formatter));
                    }

                    return new ResponseEntity<>(event, HttpStatus.OK);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<Void> eventsEventIdPut(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("eventId") Integer eventId
,@Parameter(in = ParameterIn.DEFAULT, description = "Updated event object", required=true, schema=@Schema()) @Valid @RequestBody Event body
) {
        String query = "UPDATE event SET startdate = ?,endtime = ?,eventname = ?,description = ?,userid = ? WHERE eventid = ?;";

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Ustawienie parametrów dla zapytania
            preparedStatement.setString(1, body.getStartdate().toString());
            preparedStatement.setString(2, body.getEndtime().toString());
            preparedStatement.setString(3, body.getEventname());
            preparedStatement.setString(4, body.getDescription());
            preparedStatement.setString(5, body.getUserid());
            preparedStatement.setString(6, eventId.toString());

            // Wykonanie zapytania
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return new ResponseEntity<>(HttpStatus.OK); // Zwracamy 201 Created, jeśli zmodyfikowano rekord
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Błąd, jeśli nie zmodyfikowano rekordu
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Błąd serwera
        }
    }

    public ResponseEntity<List<Event>> eventsGet() {
        String accept = "application/json";
        if (accept != null && accept.contains("application/json")) {
            List<Event> events = new ArrayList<>();
            String query = "SELECT * FROM event;";

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Event event = new Event();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    event.setEventid(resultSet.getInt("eventid"));
                    event.setUserid(resultSet.getString("userid"));
                    event.setEventname(resultSet.getString("eventname"));
                    event.setDescription(resultSet.getString("description"));
                    event.setStartdate(LocalDateTime.parse( resultSet.getString("startdate"),formatter));
                    event.setEndtime(LocalDateTime.parse( resultSet.getString("endtime"),formatter));
                    events.add(event);
                }

                return new ResponseEntity<>(events, HttpStatus.OK);

            } catch (SQLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    public ResponseEntity<Void> eventsPost(@Parameter(in = ParameterIn.DEFAULT, description = "Event object to be created", required=true, schema=@Schema()) @Valid @RequestBody Event body
)     {
        String query = "INSERT INTO event (startdate, endtime, eventname, description, userid) VALUES ( ?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Ustawienie parametrów dla zapytania

            preparedStatement.setString(1, body.getStartdate().toString());
            preparedStatement.setString(2, body.getEndtime().toString());
            preparedStatement.setString(3, body.getEventname());
            preparedStatement.setString(4, body.getDescription());
            preparedStatement.setString(5, body.getUserid());

            // Wykonanie zapytania
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return new ResponseEntity<>(HttpStatus.CREATED); // Zwracamy 201 Created, jeśli dodano rekord
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Błąd, jeśli nie dodano rekordu
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Błąd serwera
        }
    }
}
