package com.spring.tomcat.servlet;

import com.spring.tomcat.http.SpringRequest;
import com.spring.tomcat.http.SpringResponse;
import com.spring.tomcat.http.SpringServlet;

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
