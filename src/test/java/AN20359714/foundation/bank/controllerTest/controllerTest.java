package AN20359714.foundation.bank.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import AN20359714.foundation.bank.controller.bank2Controller;
import AN20359714.foundation.bank.dto.createAccount;
import AN20359714.foundation.bank.entity.AccountDetails;
import AN20359714.foundation.bank.entity.AccountType;
import AN20359714.foundation.bank.entity.CustomerDetails;
import AN20359714.foundation.bank.service.bank2service;

@RunWith(SpringRunner.class)
@WebMvcTest(bank2Controller.class)
class controllerTest
{
	//AccountDetails accountDetails=new AccountDetails();
	//@org.springframework.boot.test.web.server.LocalServerPort
	//private int port;
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private bank2service BankService;
	
	@Autowired
	private bank2Controller bankController;

	@Test
	public void saveAccount() throws Exception
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		createAccount ca=new createAccount(customerDetails);
		when(BankService.saveAccountDetails(ca)).thenReturn(customerDetails);
		
		CustomerDetails cd1=bankController.saveAccount(ca);
		assertEquals(accountDetailsList,cd1.getAccountDetails());
		assertEquals("Ansham",cd1.getName());
	}
	
	@Test
	public void transferFunds() throws Exception
	{
		when(BankService.transferToAccount(1,2,1000)).thenReturn("TRANSACTION SUCCESS");
		RequestBuilder request = MockMvcRequestBuilders.post("/idbi/Internaltransfer").queryParam("fromAccount","1").queryParam("toAccount","2").queryParam("amount","1000");
		
		MvcResult result = mockmvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200,response.getStatus());
	}
	
	
	@Test
	public void getAllAccount() throws Exception
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		List<CustomerDetails> customerDetailsList = new ArrayList<CustomerDetails>();
		customerDetailsList.add(customerDetails);

		
		
		when(BankService.getAllOrders()).thenReturn(customerDetailsList);
		
		MvcResult result = mockmvc.perform(MockMvcRequestBuilders.get("/idbi/")).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200,response.getStatus());
		
		
	}
	
		
	@Test
	public void findDetailsByAccountId() throws Exception
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		when(BankService.findDetailsByAccountId(121)).thenReturn(accountDetails);
		RequestBuilder request = MockMvcRequestBuilders.get("/idbi/AccountNumber/121");
		MvcResult result = mockmvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200,response.getStatus());
		AccountDetails ad1=bankController.findDetailsByAccountId(121);
		assertEquals(121,ad1.getAccountNumber());		
	}
	
	@Test
	public void findDetailsByCustomerId() throws Exception
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		
		when(BankService.findDetailsByCustomerId(0)).thenReturn(customerDetails);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/idbi/CustomerId/0");
		MvcResult result = mockmvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200,response.getStatus());
		
		CustomerDetails cd1=bankController.findDetailsByCustomerId(0);
		assertEquals(0,cd1.getCustomerId());
		assertEquals("Ansham",cd1.getName());

	}
	
	@Test
	public void updateAccount() throws Exception
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		createAccount ca=new createAccount(customerDetails);

		when(BankService.updateAccountDetails(ca)).thenReturn(customerDetails);
		
		CustomerDetails cd1=bankController.updateAccount(ca);
		assertEquals(0,cd1.getCustomerId());
		assertEquals("Ansham",cd1.getName());
	  }
	
	@Test
	public void deleteAccount() throws Exception
	{
		
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		when(BankService.deleteDetails((long) 121)).thenReturn("DELETED");
				
		RequestBuilder request = MockMvcRequestBuilders.delete("/idbi/121").param("customerId", "121");
		
		MvcResult result = mockmvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200,response.getStatus());
	}
	
	
}
	
	