package com.nt.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankRestController {
   @GetMapping("/home")
	public String home() {
		String msg="Welcome to YNTBR Bank....!!!";
		return msg;
	}
   @GetMapping("/user/balance")
	public String getBalance() {
		String msg="Yours current balance is : 478INR";
		return msg;
	}
   @GetMapping("/user/statement")
	public String getStmt() {
		String msg="Your Statement Generate and sent to your email id";
		return msg;
	}
   
   @GetMapping("/user/myloan")
	public String getMyLoan() {
		String msg="Your loan amount due:45000 INr";
		return msg;
	}
   
   @GetMapping("/contact")
	public String getContact() {
		String msg="Thankyou for contact customer support , we will get back to soon";
		return msg;
	}
	
   @GetMapping("/admin/interest")
	public String getinterestRate() {
		String msg="Interest rate 1Lakh is 2%";
		return msg;
	}
}
