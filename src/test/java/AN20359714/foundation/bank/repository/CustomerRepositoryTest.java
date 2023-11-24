package AN20359714.foundation.bank.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import AN20359714.foundation.bank.entity.AccountDetails;
import AN20359714.foundation.bank.entity.AccountType;
import AN20359714.foundation.bank.entity.CustomerDetails;

@RunWith(SpringRunner.class)
class CustomerRepositoryTest 
{

	
	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void findById()
	{
		AccountDetails accountDetails=new AccountDetails(121, AccountType.CURRENT, 20000);
		List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
		accountDetailsList.add(accountDetails);
		CustomerDetails customerDetails=new CustomerDetails(0,"Ansham",accountDetailsList);
		
		customerRepository.save(customerDetails);
		
		CustomerDetails result=customerRepository.findById(0);
		assertEquals(0, result.getCustomerId());
		
	}
}
