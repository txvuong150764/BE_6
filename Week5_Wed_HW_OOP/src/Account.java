public class Account {
    private int balance;
    public Account(){}
    public Account(int balance) {
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void deposit(int amount) {
        this.balance += amount;
        System.out.println("Deposit successfully!!!");
    }
    public void withdraw(int amount) {
        if(this.balance < amount){
            System.out.println("You do not have enough money to withdraw. Please try again!!!!");
            return;
        }

        this.balance -= amount;
        System.out.println("Withdraw successfully!!!");
    }
    public void transfer(int amount, Account receiveAccount) {
        if(this.balance < amount) {
            System.out.println("You do not have enough money to transfer. Please try again!!!!");
            return;
        }

        this.balance -= amount;
        receiveAccount.setBalance(receiveAccount.getBalance() + amount);
        System.out.println("Transfer successfully!!!");
    }
}
