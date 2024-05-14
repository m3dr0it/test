package com.vascomm.demo.model;

import lombok.Data;

@Data
public class BaseResponse {
        int status;
        String message;
        Object data;
        public BaseResponse(int status, String message, Object data){
                this.status = status;
                this.message = message;
                this.data = data;
        }
}
