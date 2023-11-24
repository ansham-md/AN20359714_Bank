package AN20359714.foundation.bank.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import AN20359714.foundation.bank.dto.createAccount;
import AN20359714.foundation.bank.entity.AccountDetails;
import AN20359714.foundation.bank.entity.CustomerDetails;
import AN20359714.foundation.bank.service.bank2service;
@RestController
@RequestMapping(path = "/idbi")
public class bank2Controller
{


	@Autowired
	private bank2service BankService;
	
	@PostMapping("/")
	public CustomerDetails saveAccount(@RequestBody createAccount ca)
	{
		return BankService.saveAccountDetails(ca);
	}
	
	@PostMapping("/Internaltransfer")
	public String transferToAccount(@RequestParam("fromAccount") int fromAccountNumber,
													  @RequestParam("toAccount") int toAccountNumber,
													  @RequestParam("amount") int amount)
	{
		return BankService.transferToAccount(fromAccountNumber, toAccountNumber, amount);
	}
	
	@GetMapping("/")
	public List<CustomerDetails> getAllOrders()
	{
		
		return BankService.getAllOrders();
	}
	
	@GetMapping("/AccountNumber/{accountNumber}")
	public AccountDetails findDetailsByAccountId(@PathVariable("accountNumber") int AccountNumber)
	{
		return BankService.findDetailsByAccountId(AccountNumber);
	}
	
	@GetMapping("/CustomerId/{customerId}")
	public CustomerDetails findDetailsByCustomerId(@PathVariable("customerId") int AccountNumber)
	{
		return BankService.findDetailsByCustomerId(AccountNumber);
	}
	
	@PutMapping("/")
	public CustomerDetails updateAccount(@RequestBody createAccount ca)
	{
		
		return BankService.updateAccountDetails(ca);
		
	}
	
	@DeleteMapping("/{accountNumber}")
	public String deleteDetails(@PathVariable("accountNumber") long customerId)
	{
		return BankService.deleteDetails(customerId);
	}
	
}
