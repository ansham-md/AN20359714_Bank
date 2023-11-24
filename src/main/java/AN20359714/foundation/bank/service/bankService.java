/*package AN20359714.foundation.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AN20359714.foundation.bank.dto.createAccount;
import AN20359714.foundation.bank.entity.AccountDetails;
import AN20359714.foundation.bank.entity.UserDetails;
import AN20359714.foundation.bank.exception.BusinessException;
import AN20359714.foundation.bank.repository.AccountRepository;
import AN20359714.foundation.bank.repository.UserRepository;
@Service
public class bankService {

	@Autowired
	private UserRepository BankRepository;
	
	public UserDetails saveAccountDetails(AccountDetails details) 
	{
		//ca.setBalance(0);
		return BankRepository.save(details);
	}
	
	public Optional<AccountDetails> getAccountDetails(int accountNumber)
	{
		return BankRepository.findById(accountNumber);
	}
	
	public Boolean proofOfFunds(int fromAccountNumber, int amount)
	{
		int senderBalance=BankRepository.getReferenceById(fromAccountNumber).getBalance();
		if(senderBalance>=amount)
			return true;
		else
			
			throw new BusinessException("INSUFFICIENT FUNDS");
			
		
	}

	public String transferToAccount(int fromAccountNumber, int toAccountNumber, int amount)
	{
		
		while(proofOfFunds(fromAccountNumber,amount))
		{
			int senderBalance=BankRepository.getReferenceById(fromAccountNumber).getBalance();
			senderBalance-=amount;
			BankRepository.getReferenceById(fromAccountNumber).setBalance(senderBalance);
			BankRepository.save(BankRepository.getReferenceById(fromAccountNumber));
			
			int recieverBalance=BankRepository.getReferenceById(toAccountNumber).getBalance();
			recieverBalance+=amount;
			BankRepository.getReferenceById(toAccountNumber).setBalance(recieverBalance);
			BankRepository.save(BankRepository.getReferenceById(toAccountNumber));
			
			return "TRANSACTION SUCCESS";
			
			
		}
		return "TRANSACTION FAILED";
	}

	public String cerditToAccount(int accountNumber, int amount)
	{
		int currentBalance=BankRepository.getReferenceById(accountNumber).getBalance();
		currentBalance+=amount;
		BankRepository.getReferenceById(accountNumber).setBalance(currentBalance);
		BankRepository.save(BankRepository.getReferenceById(accountNumber));
		return "SUCCESS";
	}

	public List<AccountDetails> getDetails()
	{
		return BankRepository.findAll();
	}

	public Optional<AccountDetails> findDetailsById(int accountNumber)
	{
		return BankRepository.findById(accountNumber);
	}
	/*
	public Optional<Details> findDetailsByAccountType(String accountType)
	{
		return BankRepository.findByAccountType();
	}*/

	
	/*
	public Optional<AccountDetails> findDetailsByName(String name) {
		
		return BankRepository.findByName(name);
	}*/
	
	/*
	
	public AccountDetails updateAccountDetails(AccountDetails details)
	{
		return BankRepository.save(details);
	}

	public String deleteDetails(int accountNumber)
	{
		BankRepository.delete(BankRepository.getReferenceById(accountNumber));
		return "DELETED";
	}
	

}

*/

