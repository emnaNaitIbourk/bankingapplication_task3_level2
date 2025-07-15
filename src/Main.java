//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BankAccount b_acct1=new BankAccount();
        b_acct1.setAccountHolder("Emna Rejeb");
        b_acct1.setAccountNumber("09648294");

        b_acct1.deposit(1200.350);
        b_acct1.withdraw(100);
        if(b_acct1.getBalance()==0){
            System.out.println("Your balance is empty:(");

        }
        else{
            System.out.println("Your balance is $"+b_acct1.getBalance());

        }
        BankAccount b_acct2=new BankAccount();
        b_acct2.setAccountHolder("");
        b_acct2.setAccountNumber("1289");
        b_acct2.deposit(-300.269);
        b_acct2.deposit(1500);
        b_acct2.withdraw(500);
        b_acct2.withdraw(1100);
        if(b_acct2.getBalance()==0){
            System.out.println("Your balance is empty:(");

        }
        else{
            System.out.println("Your balance is $"+b_acct2.getBalance());

        }
    }
}