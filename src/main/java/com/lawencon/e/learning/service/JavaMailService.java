package com.lawencon.e.learning.service;

import com.lawencon.e.learning.pojo.SendEmailPojo;

public interface JavaMailService {
	void sendEmail(SendEmailPojo createdAccountPojo);
}
