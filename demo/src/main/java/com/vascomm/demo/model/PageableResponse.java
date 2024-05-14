package com.vascomm.demo.model;

import lombok.Data;

@Data
public class PageableResponse {
    int totalPage;
    long totalData;
    Object data;
}
