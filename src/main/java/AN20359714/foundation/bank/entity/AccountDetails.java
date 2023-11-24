package AN20359714.foundation.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails
{
	@Id
	@Column(name="AccNo")
	public long accountNumber;
	
	@Column(name="AccType")
	public AccountType accountType;
	
	@Column(name="Balanace")
	public int balance;
	
	
	
	
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
	public AccountDetails(long accountNumber, AccountType accountType, int balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "AccountDetails [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance="
				+ balance + "]";
	}
	
	
	
	
	
	
	
	
}