package AN20359714.foundation.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AN20359714.foundation.bank.dto.createAccount;
import AN20359714.foundation.bank.entity.AccountDetails;
import AN20359714.foundation.bank.entity.CustomerDetails;
import AN20359714.foundation.bank.exception.BusinessException;
import AN20359714.foundation.bank.repository.AccountRepository;
import AN20359714.foundation.bank.repository.CustomerRepository;

@Service
public class bank2service {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public CustomerDetails saveAccountDetails(createAccount ca) 
	{
		
		return customerRepository.save(ca.getCustomerDetails());
	}
	
	public Boolean proofOfFunds(int fromAccountNumber, int toAccountNumber, int amount)
    {
        int senderBalance=accountRepository.getReferenceById(fromAccountNumber).getBalance();

        if(fromAccountNumber==toAccountNumber)
        	throw new BusinessException("BOTH ACCOUNTS CANT BE THE SAME");
        else if(senderBalance<amount)
            throw new BusinessException("INSUFFICIENT FUNDS");
        else
        	return true;
        	
    }
	
	 

    public String transferToAccount(int fromAccountNumber, int toAccountNumber, int amount)
    {
        while(proofOfFunds(fromAccountNumber,toAccountNumber,amount))
        {
            int senderBalance=accountRepository.getReferenceById(fromAccountNumber).getBalance();
            senderBalance-=amount;
            accountRepository.getReferenceById(fromAccountNumber).setBalance(senderBalance);
            accountRepository.save(accountRepository.getReferenceById(fromAccountNumber));

            int recieverBalance=accountRepository.getReferenceById(toAccountNumber).getBalance();
            recieverBalance+=amount;
            accountRepository.getReferenceById(toAccountNumber).setBalance(recieverBalance);
            accountRepository.save(accountRepository.getReferenceById(toAccountNumber));

            return "TRANSACTION SUCCESS";


        }
        return "TRANSACTION FAILED";

    }
    
    public List<CustomerDetails> getAllOrders()
    {

        return customerRepository.findAll();
    }
    
    public AccountDetails findDetailsByAccountId(int accountNumber)
	{
		return accountRepository.findByAccountNumber(accountNumber);
		
	}

	public CustomerDetails findDetailsByCustomerId(long accountNumber)
	{
		
		return customerRepository.findById(accountNumber);
	}
	public CustomerDetails updateAccountDetails(createAccount ca)
	{
		return customerRepository.save(ca.getCustomerDetails());
	}

	public String deleteDetails(Long customerId)
	{
		customerRepository.delete(customerRepository.getReferenceById(customerId));
		return "DELETED";
	}

	public bank2service(CustomerRepository customerRepository, AccountRepository accountRepository) {
		super();
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
	}
	
	
}
	