package com.task.sample.security.jwt;

import com.task.sample.common.message.MessageCode;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");

        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", "9999");
        resultMap.put("message", MessageCode.INVALID_AUTH_TOKEN.getMessage());
        resultMap.put("errorCode", HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject resultJson = new JSONObject(resultMap);

        PrintWriter printWriter = response.getWriter();
        printWriter.print(resultJson);
    }

}