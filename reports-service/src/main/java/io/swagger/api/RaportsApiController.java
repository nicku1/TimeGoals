package io.swagger.api;

import io.swagger.model.Raport;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.threeten.bp.LocalDate;
import javax.sql.DataSource;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-23T20:13:09.961561888Z[GMT]")
@RestController
public class RaportsApiController implements RaportsApi {

    private static final Logger log = LoggerFactory.getLogger(RaportsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;


    @org.springframework.beans.factory.annotation.Autowired
    public RaportsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Raport>> raportsGet() {
        String accept = "application/json";
        if (accept != null && accept.contains("application/json")) {
            List<Raport> raports = new ArrayList<>();
            String query = "SELECT * FROM raports;";

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Raport raport = new Raport();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    raport.setRaportid(resultSet.getInt("raportid"));
                    raport.setUserid(resultSet.getString("userid"));
                    raport.setContent(resultSet.getString("content"));
                    raport.setReportDate(LocalDateTime.parse( resultSet.getString("raport_date"),formatter));
                    raports.add(raport);
                }

                return new ResponseEntity<>(raports, HttpStatus.OK);

            } catch (SQLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    public ResponseEntity<Void> raportsPost(@Parameter(in = ParameterIn.DEFAULT, description = "Raport object to be created", required=true, schema=@Schema()) @Valid @RequestBody Raport body) {
        {
            String query = "INSERT INTO raports (userid, content, raport_date) VALUES (?, ?, ?);";

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                // Ustawienie parametrów dla zapytania
                preparedStatement.setString(1, body.getUserid());
                preparedStatement.setString(2, body.getContent());
                preparedStatement.setString(3, body.getReportDate().toString());

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

    public ResponseEntity<Void> raportsRaportIdDelete(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("raportId") Integer raportId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Raport> raportsRaportIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("raportId") Integer raportId) {
        String accept = "application/json";
        if (accept != null && accept.contains("application/json")) {
            Raport raport = new Raport();
            String query = "SELECT * FROM raports WHERE raportid = ?;";

            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.221.133:3306/TimeGoals?user=kochammichalka&password=JARANIE420");
                 PreparedStatement preparedStatement = connection.prepareStatement(query);){
                 preparedStatement.setInt(1, raportId);
                 try(ResultSet resultSet = preparedStatement.executeQuery();) {

                     while (resultSet.next()) {
                         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                         raport.setRaportid(resultSet.getInt("raportid"));
                         raport.setUserid(resultSet.getString("userid"));
                         raport.setContent(resultSet.getString("content"));
                         raport.setReportDate(LocalDateTime.parse(resultSet.getString("raport_date"), formatter));
                     }

                     return new ResponseEntity<>(raport, HttpStatus.OK);
                 }
                } catch (SQLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<Void> raportsRaportIdPut(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("raportId") Integer raportId
,@Parameter(in = ParameterIn.DEFAULT, description = "Updated raport object", required=true, schema=@Schema()) @Valid @RequestBody Raport body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
