package com.shopping.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorResponse {
	private String type;
	private String message;
	private String errorCode;

}
