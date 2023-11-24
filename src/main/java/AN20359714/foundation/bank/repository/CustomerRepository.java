package AN20359714.foundation.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AN20359714.foundation.bank.dto.createAccount;
import AN20359714.foundation.bank.entity.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Long>{

	//CustomerDetails save(CustomerDetails ca);
	CustomerDetails findById(long customerId);
}
