/*package AN20359714.foundation.bank.controller;

import java.util.List;
import java.util.Optional;

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

import AN20359714.foundation.bank.entity.AccountDetails;
import AN20359714.foundation.bank.service.bankService;

@RestController
@RequestMapping(path = "/idbi")

public class bankController
{


	@Autowired
	private bankService BankService;
	
	@PostMapping("/")
	public AccountDetails saveAccount(@RequestBody AccountDetails details)
	{
		
		return BankService.saveAccountDetails(details);
		
	}
	@PostMapping("/Internaltransfer")
	public String transferToAccount(@RequestParam("fromAccount") int fromAccountNumber,
			@RequestParam("toAccount") int toAccountNumber,
			@RequestParam("amount") int amount)
	{
		return BankService.transferToAccount(fromAccountNumber,toAccountNumber,amount);
	}
	
	@PostMapping("/Incomingtransfer")
	public String incomingTransfer(@RequestParam("fromAccount") int AccountNumber,
			@RequestParam("amount") int amount)
	{
		return BankService.cerditToAccount(AccountNumber, amount);
	}
	
	@GetMapping("/")
	public List<AccountDetails> findAllDepartments()
	{
		return BankService.getDetails();
	}
	
	@GetMapping("/{accountNumber}")
	public Optional<AccountDetails> findDetailsById(@PathVariable("accountNumber") int AccountNumber)
	{
		return BankService.findDetailsById(AccountNumber);
	}
	
	/*@GetMapping("/{AccountType}")
	public  Optional<Details> findDetailsByAccountType(@PathVariable("AccountType") String AccountType)
	{
		return BankService.findDetailsByAccountType(AccountType);
	}*/
	
	/*@GetMapping("/{name}")
	public  Optional<AccountDetails> findDetailsByName(@PathVariable("name") String name)
	{
		return BankService.findDetailsByName(name);
	}*/
	
/*
	@PutMapping("/")
	public AccountDetails updateAccount(@RequestBody AccountDetails details)
	{
		
		return BankService.updateAccountDetails(details);
		
	}
	
	@DeleteMapping("/{accountNumber}")
	public String deleteDetails(@PathVariable("accountNumber") int AccountNumber)
	{
		return BankService.deleteDetails(AccountNumber);
	}
	
}

*/