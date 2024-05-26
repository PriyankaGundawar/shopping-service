package com.shopping.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailParams {
	String emailTo;
	String subject;
	String emailBody;

}
