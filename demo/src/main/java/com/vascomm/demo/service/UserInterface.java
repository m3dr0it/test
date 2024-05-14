package com.vascomm.demo.service;

import com.vascomm.demo.exception.NotFoundException;
import com.vascomm.demo.model.BaseResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserInterface {
    BaseResponse getUserList(Pageable pageable, Map<String,String> params);
    void addUser(Map<String,String> userPayload);
    void updateUser(Map<String,String> payload) throws NotFoundException;
    void deleteUser(Map<String,String> id);
}
