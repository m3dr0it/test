package com.vascomm.demo.controller;

import com.vascomm.demo.exception.NotFoundException;
import com.vascomm.demo.model.BaseResponse;
import com.vascomm.demo.service.UserInterface;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserInterface userInterface;

//    @RolesAllowed("user")
@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> getUserList(Pageable pageable, @RequestParam Map<String,String> params){
        BaseResponse response = userInterface.getUserList(pageable,params);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RolesAllowed("admin")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> addUser(@RequestBody Map<String,String> payload){
        userInterface.addUser(payload);
        return new ResponseEntity<>(new BaseResponse(201,"Success",""), HttpStatus.OK);
    }

    @RolesAllowed("admin")
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> updateUser(@RequestBody Map<String,String> payload) throws NotFoundException {
        userInterface.updateUser(payload);
        return new ResponseEntity<>(new BaseResponse(201,"Success",""), HttpStatus.OK);
    }

    @RolesAllowed("admin")
    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> deleteUser(@RequestBody Map<String,String> payload){
        userInterface.deleteUser(payload);
        return new ResponseEntity<>(new BaseResponse(201,"Success",""), HttpStatus.OK);
    }
}
