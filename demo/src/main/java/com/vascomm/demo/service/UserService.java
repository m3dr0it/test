package com.vascomm.demo.service;

import com.vascomm.demo.exception.NotFoundException;
import com.vascomm.demo.model.BaseResponse;
import com.vascomm.demo.model.PageableResponse;
import com.vascomm.demo.model.User;
import com.vascomm.demo.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserInterface{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BaseResponse getUserList(Pageable pageable, Map<String,String> params) {
        Page<User> userPages = userRepository.findAll(pageable);
        PageableResponse pageableResponse = new PageableResponse();
        pageableResponse.setTotalPage(userPages.getTotalPages());
        pageableResponse.setTotalData(userPages.getTotalElements());
        pageableResponse.setData(userPages.toList());
        return new BaseResponse(200,"Success",pageableResponse);
    }

    @Override
    public void addUser(Map<String,String> userPayload) {
        User user = new User();
        user.setEmail(userPayload.get("email"));
        user.setRole(userPayload.get("role"));
        user.setUsername(userPayload.get("username"));
        user.setIsActive(true);
        userRepository.save(user);
    }

    @Override
    @SneakyThrows
    public void updateUser(Map<String,String> payload) {
        String id = payload.get("id");
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty()){
            throw new NotFoundException(id);
        }
        User user = userOpt.get();
        user.setUsername(payload.get("username"));
        user.setEmail(payload.get("email"));
        user.setRole(payload.get("role"));
        user.setIsActive(Boolean.getBoolean(payload.get("isActive")));
        userRepository.save(user);
    }

    @Override
    @SneakyThrows
    public void deleteUser(Map<String,String> payload) {
        String id = payload.get("id");
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty()){
            throw new NotFoundException(id);
        }

        User user = userOpt.get();
        user.setIsActive(Boolean.FALSE);
        userRepository.save(user);
    }
}
