package AN20359714.foundation.bank.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AN20359714.foundation.bank.entity.AccountDetails;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public interface AccountRepository extends JpaRepository<AccountDetails, Integer>{

	//Optional<Details> findByAccountType();

	//Optional<AccountDetails> findByName(String name);
	
	AccountDetails findByAccountNumber(long accountNumber);
	

}
