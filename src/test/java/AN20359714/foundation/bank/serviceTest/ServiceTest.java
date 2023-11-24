package AN20359714.foundation.bank.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import AN20359714.foundation.bank.dto.createAccount;
import AN20359714.foundation.bank.entity.AccountDetails;
import AN20359714.foundation.bank.entity.AccountType;
import AN20359714.foundation.bank.entity.CustomerDetails;
import AN20359714.foundation.bank.exception.BusinessException;
import AN20359714.foundation.bank.repository.AccountRepository;
import AN20359714.foundation.bank.repository.CustomerRepository;
import AN20359714.foundation.bank.service.bank2service;

@ExtendWith(MockitoExtension.class)
class ServiceTest
{

	
	@Mock	
	private CustomerRepository customerRepository;
	
	@Mock
	private AccountRepository accountRepository;
	
	@Autowired
	private bank2service bankservice;
	
	@BeforeEach
	void setUp()
	{
		this.bankservice=new bank2service(customerRepository, accountRepository);
	}
	
	@Test
	public void getAllCustomer()
	{
		bankservice.getAllOrders();
		verify(customerRepository).findAll();
	}


	@Test
	public void save()
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		createAccount ca=new createAccount(customerDetails);
		
		when(customerRepository.save(ArgumentMatchers.any(CustomerDetails.class))).thenReturn(customerDetails);
		
		bankservice.saveAccountDetails(ca); 
		verify(customerRepository).save(customerDetails);
	}
	@Test
	public void updateAccountDetails()
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		createAccount ca=new createAccount(customerDetails);
		when(customerRepository.save(ArgumentMatchers.any(CustomerDetails.class))).thenReturn(customerDetails);
		assertEquals(customerDetails,bankservice.updateAccountDetails(ca));
	}
	@Test
	public void deleteDetails()
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		
		when(customerRepository.getReferenceById((long) 0)).thenReturn(customerDetails);
		bankservice.deleteDetails((long) 0); 
		verify(customerRepository).delete(customerDetails);
	}
	
	
	@Test
	public void getCustomerById()
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		
		
		when(customerRepository.findById((long) 1)).thenReturn(customerDetails);
		bankservice.findDetailsByCustomerId(1);
		
		assertEquals(0,bankservice.findDetailsByCustomerId(1).getCustomerId());
	}
	@Test
	public void findDetailsByAccountId()
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		when(accountRepository.findByAccountNumber((long)121)).thenReturn(accountDetails);
		assertEquals(121,bankservice.findDetailsByAccountId(121).getAccountNumber());
		assertEquals(20000,bankservice.findDetailsByAccountId(121).getBalance());
	}
	

	@Test
	public void proofOfFundsTrue()
	{
		AccountDetails accountDetails=new AccountDetails(101, AccountType.CURRENT, 20000);
		
		when(accountRepository.getReferenceById(101)).thenReturn(accountDetails);
		Boolean b=bankservice.proofOfFunds(101, 102, 10000);
		assertEquals(true,b);
	}
	@Test
	public void proofOfFundsFalse()
	{
		AccountDetails accountDetails=new AccountDetails(101, AccountType.CURRENT, 5000);
		
		when(accountRepository.getReferenceById(101)).thenReturn(accountDetails);
		
		Exception exception = assertThrows(BusinessException.class, () -> {
			bankservice.proofOfFunds(101, 102, 10000);
	    });
		 String expectedMessage = "INSUFFICIENT FUNDS";
		 String actualMessage = exception.getMessage();

		 assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	public void proofOfFundsCantBeSame()
	{
		AccountDetails accountDetails=new AccountDetails(101, AccountType.CURRENT, 5000);
		
		when(accountRepository.getReferenceById(101)).thenReturn(accountDetails);
		
		Exception exception = assertThrows(BusinessException.class, () -> {
			bankservice.proofOfFunds(101, 101, 10000);
	    });
		 String expectedMessage = "BOTH ACCOUNTS CANT BE THE SAME";
		 String actualMessage = exception.getMessage();

		 assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void transferToAccountSuccess()
	{
		AccountDetails fromAccountDetails=new AccountDetails(101, AccountType.CURRENT, 5000);
		AccountDetails toAccountDetails=new AccountDetails(103, AccountType.CURRENT, 5000);

		when(accountRepository.getReferenceById(101)).thenReturn(fromAccountDetails);
		when(accountRepository.getReferenceById(103)).thenReturn(toAccountDetails);
		String expectedMessage = "TRANSACTION SUCCESS";
		String actualMessage = bankservice.transferToAccount(101, 103, 500);
		assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	public void transferToAccountFailure1()
	{
		
		AccountDetails toAccountDetails=new AccountDetails(103, AccountType.CURRENT, 5000);
		when(accountRepository.getReferenceById(103)).thenReturn(toAccountDetails);
		Exception exception = assertThrows(BusinessException.class, () -> {
			bankservice.transferToAccount(103, 103, 500);
	    });
		String expectedMessage = "BOTH ACCOUNTS CANT BE THE SAME";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	public void transferToAccountFailure2()
	{
		AccountDetails fromAccountDetails=new AccountDetails(101, AccountType.CURRENT, 5000);
		when(accountRepository.getReferenceById(101)).thenReturn(fromAccountDetails);
		Exception exception = assertThrows(BusinessException.class, () -> {
			bankservice.transferToAccount(101, 103, 50000);
	    });
		String expectedMessage = "INSUFFICIENT FUNDS";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
}
