package com.vascomm.demo.controller;

import com.vascomm.demo.exception.NotFoundException;
import com.vascomm.demo.model.BaseResponse;
import com.vascomm.demo.service.ProductInterface;
import com.vascomm.demo.service.ProductService;
import com.vascomm.demo.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductInterface productInterface;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> getUserList(Pageable pageable, @RequestParam Map<String,String> params){
        BaseResponse response = productInterface.getList(pageable,params);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @RolesAllowed("admin")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> addUser(@RequestBody Map<String,String> payload){
        productInterface.add(payload);
        return new ResponseEntity<>(new BaseResponse(201,"Success",""), HttpStatus.OK);
    }

//    @RolesAllowed("admin")
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseResponse> updateUser(@RequestBody Map<String,String> payload) throws NotFoundException {
        productInterface.update(payload);
        return new ResponseEntity<>(new BaseResponse(201,"Success",""), HttpStatus.OK);
    }

}
