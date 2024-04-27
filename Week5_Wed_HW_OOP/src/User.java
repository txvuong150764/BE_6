public class User {
    private String cardNo;
    private String PIN;
    private Account account;
    public User(String cardNo, String PIN, Account account) {
        this.cardNo = cardNo;
        this.PIN = PIN;
        this.account = account;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getPIN() {
        return PIN;
    }
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public void viewBalance() {
        System.out.println("Account Balance: " + this.account.getBalance());
    }
    public void deposit(int amount) {
        this.account.deposit(amount);
    }
    public void withdraw(int amount) {
        this.account.withdraw(amount);
    }
    public void transfer(int amount, User receiver) {
        this.account.transfer(amount, receiver.account);
    }
}
