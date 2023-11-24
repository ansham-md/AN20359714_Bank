package AN20359714.foundation.bank.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import AN20359714.foundation.bank.entity.AccountDetails;
import AN20359714.foundation.bank.entity.AccountType;
import jakarta.transaction.Transactional;



class AccountRepositoryTest
{
	@InjectMocks
	AccountRepository accountRepository;
	
	
	@Test
	public void findByAccountNumber()
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		accountRepository.save(accountDetails);
		AccountDetails result = accountRepository.findByAccountNumber(accountDetails.getAccountNumber());
	    assertEquals(accountDetails.getAccountNumber(), result.getAccountNumber());
	}
	/*
	private AccountDetails getAccountDetails()
	{
		
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		return accountDetails;
	}*/

}
