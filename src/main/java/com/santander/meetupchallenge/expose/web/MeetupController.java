package com.santander.meetupchallenge.expose.web;

import com.santander.meetupchallenge.domain.Meetup;
import com.santander.meetupchallenge.request.CreateMeetupRequest;
import com.santander.meetupchallenge.service.MeetupService;
import com.santander.meetupchallenge.service.UserMeetupService;
import com.santander.meetupchallenge.service.UserService;
import com.santander.meetupchallenge.util.exception.MeetupException;
import com.santander.meetupchallenge.util.exception.UserException;
import com.santander.meetupchallenge.util.exception.UserMeetUpException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class MeetupController {
    private static final String ERROR_MESSAGE_INTERNAL = "Internal error";

    @Autowired
    private MeetupService meetupService;

    @Autowired
    UserMeetupService userMeetupService;

    @Autowired
    UserService userService;

    @GetMapping("/weather/{meetupId}")
    @PreAuthorize("hasRole('ROLE_USER') ")
    @ApiOperation(value = "Metodo encargado de devolver el clima del dia de una meetUp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> weather(@PathVariable("meetupId") Long meetupId) {
        try {
            return new ResponseEntity<>(userMeetupService.getTemperature(meetupId),HttpStatus.OK);
        }catch(MeetupException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);

        }
    }

    @GetMapping("/supply/beer/{meetUpId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Metodo encargado de devolver el clima del dia de una meetUp")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> supplyBeer(@PathVariable("meetUpId") Long meetupId) {
        try {
            return new ResponseEntity<>(userMeetupService.getAmountBeer(meetupId),HttpStatus.OK);
        }catch(MeetupException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);
        }
    }


    @PostMapping("/createmeetup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Metodo para crear meetups")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> createMeetUp(@RequestBody CreateMeetupRequest meetupRequest) {
        try {
            Meetup meetup = new Meetup();
            meetup.setAddress(meetupRequest.getAddress());
            meetup.setDate(meetupRequest.getDate());
            meetupService.save(meetup);
            return new ResponseEntity<>(meetupRequest, HttpStatus.OK);

        } catch (MeetupException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);
        }

    }


    @PutMapping("/register/{meetUpId}/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "Metodo para inscribirse en una meetup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> registerUser(@PathVariable("meetUpId") Long meetupId, @PathVariable("userId") Long userId) {
        try {
            userMeetupService.save(userId, meetupId);
        } catch (MeetupException | UserMeetUpException | UserException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);
        }
        return new ResponseEntity<>("Se ha cargado correctamente", HttpStatus.OK);
    }


    @PutMapping("/confirmassistance/{meetUpId}/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "Metodo para confirmar que estuve en la meetup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> confirmAssistance(@PathVariable("meetUpId") Long meetupId, @PathVariable("userId") Long userId) {
        try {
            userMeetupService.registerUser(userId, meetupId,true);
            System.out.println(meetupId);
        } catch (UserMeetUpException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ERROR_MESSAGE_INTERNAL);
        }
        return new ResponseEntity<>("Se ha cargado correctamente", HttpStatus.OK);

    }
}

