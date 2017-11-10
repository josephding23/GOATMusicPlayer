package BankAccount;
import java.util.Scanner;
public class BankAccount {
	BankAccount(String AccountName, String Name, String OpenDate, String id) {
		accountName = AccountName;
		name = Name;
		openDate = OpenDate;
		ID = id;
		totalMoney = 2000;
	}
	private String openDate;
	private String name;
	private String ID;
	private String accountName;
	private double totalMoney;
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String AccountName) {
		accountName = AccountName;
	}
	public String getName() {
		return name;
	}
	public void setName(String Name) {
		name = Name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public double getAmount() {
		return totalMoney;
	}
	public static void main(String arg[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Your name: ");
		String owner = sc.nextLine();
		BankAccount Account = new BankAccount("123", owner, "20171001", "66666");
		System.out.println("This is the information of your card: ");
		Account.showInfo();
		System.out.println("\nHow much do you want to deposit?");
		double depo = sc.nextDouble();
		Account.deposit(depo);
		System.out.println("\nHow much do you want to withdrawl?");
		double withd1 = sc.nextDouble();
		Account.withdrawl(withd1);
		System.out.println("\nHow much do you want to withdrawl?");
		double withd2 = sc.nextDouble();
		Account.withdrawl(withd2);
		System.out.println("\nThis is the information of your card afterwards: ");
		Account.showInfo();
		return;
	}
	public void withdrawl(double amount) {
		if(amount <= totalMoney) {
			totalMoney -= amount;
			System.out.println("Withdrawl " + amount + " , now your account has " + totalMoney );
			return;
		}
		else {
			System.out.println("Unable to withdrawl, now your account only has " + totalMoney);
			return;
		}
	}
	public void deposit(double amount) {
		totalMoney += amount;
		System.out.println("Deposit " + amount + ", now your account has " +  totalMoney);
		return;
	}
	public void showInfo() {
		System.out.println("Account Name: " + accountName);
		System.out.println("Owner Name: " + name);
		System.out.println("Open Date: " + openDate);
		System.out.println("Card ID: " + ID);
		System.out.println("Total money: " + totalMoney);
		return;
	}
}
