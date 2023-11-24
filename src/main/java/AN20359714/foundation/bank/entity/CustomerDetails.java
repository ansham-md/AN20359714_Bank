package AN20359714.foundation.bank.entity;

import java.util.List;

import AN20359714.foundation.bank.repository.CustomerRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class CustomerDetails {

	@Id
	@GeneratedValue
	@Column(name="Id")
	public long customerId;

	@Column(name="Name")
	public String name;

	@OneToMany(targetEntity=AccountDetails.class,cascade=CascadeType.ALL)
	@JoinColumn(name="cp_fk",referencedColumnName="Id")
	List<AccountDetails> accountDetails;

	

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AccountDetails> getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(List<AccountDetails> accountDetails) {
		this.accountDetails = accountDetails;
	}
	
	public CustomerDetails(long customerId, String name, List<AccountDetails> accountDetails) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.accountDetails = accountDetails;
	}

	
	
	
	

}
