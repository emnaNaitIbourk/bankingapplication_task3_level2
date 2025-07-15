
    public class BankAccount {
        private String accountHolder;
        private String accountNumber;
        private double balance;

        public String getAccountHolder() {

            return accountHolder;
        }

        public void setAccountHolder(String name) {
            if(name!=null &&!name.isEmpty()){
                this.accountHolder = name;

            }
            else{

                System.out.println("Account holder is empty");

            }

        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String number) {
            if(number.matches("\\d{8}")){
                this.accountNumber = number;

            }
            else{
                System.out.println("Account number must contain exactly 8 digits!");

            }
        }

        public double getBalance() {
            return balance;
        }
        public void deposit(double amount){
            if(amount>0){
                this.balance+=amount;
            }
            else{
                System.out.println("Deposit amount must be positive !");
            }
        }
        public boolean withdraw(double amount ){
            if(amount<=this.balance && amount>0){
                this.balance-=amount;
                return true;
            }
            else{
                System.out.println("You can't withdraw more than $"+this.balance);
                return false;
            }

        }


    }

