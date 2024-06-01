package com.shopping.service.filter;

import java.io.IOException;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.shopping.service.model.User;
import com.shopping.service.respository.UserRepository;
import com.shopping.service.util.Base64EncodeDecode;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@Component
@WebFilter
public class UserFilter implements Filter{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String AUTH = "Auth";
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    Enumeration<String> headerNames = httpRequest.getHeaderNames();
	    
	    System.out.println(httpRequest.getRequestURI());
	    System.out.println(httpRequest.getRequestURL());
	    System.out.println(httpRequest.getUserPrincipal());
	    
//	    String headerName = null;

	    if (headerNames != null) {
	            while (headerNames.hasMoreElements()) {
	            	String headerName = headerNames.nextElement();
	            	System.out.println(headerName + " : " +httpRequest.getHeader(headerName));

	            			
//	                    System.out.println("Header: " + httpRequest.getHeaderNames() + httpRequest.getHeader(headerNames.nextElement()));
	            }
	    }
	    
	    if(httpRequest.getHeader(AUTH) != null) {
	    	Base64EncodeDecode decode = new Base64EncodeDecode();
	    	String email = decode.decode(httpRequest.getHeader(AUTH));
	    	System.out.println("got email "+ email);
	    	User user = userRepository.findByEmail(email);
	    	System.out.println("got user " +  user);
	    	 
	    	if(user == null) {
	    		throw new RuntimeException("Unauthorized user");
	    	}	    	
	    	
	    }
	    else {
	    throw new RuntimeException("Unauthorized user");
	    }

	    //doFilter
	    chain.doFilter(httpRequest, response);
		
	}

}
