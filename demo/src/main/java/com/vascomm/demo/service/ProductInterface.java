package com.vascomm.demo.service;

import com.vascomm.demo.exception.NotFoundException;
import com.vascomm.demo.model.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ProductInterface {
    BaseResponse getList(Pageable pageable, Map<String,String> params);
    void add(Map<String,String> userPayload);
    void update(Map<String,String> payload) throws NotFoundException;
}
