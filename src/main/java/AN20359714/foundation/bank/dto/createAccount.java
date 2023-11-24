package AN20359714.foundation.bank.dto;

import AN20359714.foundation.bank.entity.CustomerDetails;

public class createAccount {

	public CustomerDetails customerDetails;

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}
	
	public createAccount(CustomerDetails customerDetails) {
		super();
		this.customerDetails = customerDetails;
	}
}
