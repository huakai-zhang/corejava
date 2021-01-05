package com.spring.servlet;

import com.spring.http.SpringRequest;
import com.spring.http.SpringResponse;
import com.spring.http.SpringServlet;

import java.io.UnsupportedEncodingException;

public class MyServlet extends SpringServlet {

    @Override
    public void doGet(SpringRequest request, SpringResponse response) {
        try {
            response.write(request.getParameter("name"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(SpringRequest request, SpringResponse response) {
        doGet(request, response);
    }
}
