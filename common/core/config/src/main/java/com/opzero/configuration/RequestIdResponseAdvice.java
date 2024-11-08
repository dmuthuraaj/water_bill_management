//package com.opzero.configuration;
//
//import com.opzero.core.dto.CommonResponse;
//import com.opzero.core.exception.handler.GlobalExceptionHandler;
//import org.jboss.logging.MDC;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//@RestControllerAdvice
//public class RequestIdResponseAdvice implements ResponseBodyAdvice<Object> {
//
//    private static final String REQUEST_ID_KEY = "requestId";
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class converterType) {
//        return true; // Apply this advice to all controller responses
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
//                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//
//        // Check if body is a ResponseEntity, unwrap if necessary
//        if (body instanceof ResponseEntity<?>) {
//            body = ((ResponseEntity<?>) body).getBody();
//        }
//
//        // Add requestId to the response body
//        if (body instanceof GlobalExceptionHandler.ErrorResponse data) {
//            data.setRequestId((String) MDC.get(REQUEST_ID_KEY));
//           return data;
//        }
//        if (body instanceof CommonResponse<?> data){
//            data.setRequestId((String) MDC.get(REQUEST_ID_KEY));
//            return data;
//        }
//
//
//        return body;
//    }
//}