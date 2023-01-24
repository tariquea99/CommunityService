package com.technical99.response;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler 
{
	public static ResponseEntity<Object> generateResp(Object obj, String message, HttpStatus status, Integer size) 
	{
		Map<String, Object> map =new LinkedHashMap<String, Object>();            
	           
        map.put("data", obj);
        map.put("message", message);
        map.put("status", status.value()); 
        map.put("size", size);
        return new ResponseEntity<Object>(map,status);
    }
	
	public static ResponseEntity<Object> exceptionResp(Object obj, String message, HttpStatus status) 
	{
		Map<String, Object> map =new LinkedHashMap<String, Object>();            
	           
        map.put("exception", obj);
        map.put("message", message);
        map.put("status", status.value());
        return new ResponseEntity<Object>(map,status);
    }
	
	public static ResponseEntity<Object> generateMsgResp(String message, HttpStatus status) 
	{
		Map<String, Object> map =new LinkedHashMap<String, Object>();            
	           
        map.put("message", message);
        map.put("status", status.value()); 
        return new ResponseEntity<Object>(map,status);
    }
}
