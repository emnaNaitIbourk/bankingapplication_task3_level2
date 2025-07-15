import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;




public class ATM_GUI extends JFrame{

    BankAccount bankAccount=new BankAccount();

    JTextField nameField =new JTextField(15);
    JTextField numberField=new JTextField(8);
    JTextField depoField=new JTextField(10);
    JTextField withField=new JTextField(10);
    JLabel nameError=new JLabel("");
    JLabel numbError=new JLabel("");
    JLabel balanceLabel=new JLabel("Your balance is $0.00");

    JLabel messageLabel=new JLabel("");

    public ATM_GUI(){


        setTitle("Simple Banking System");
        setSize(480,560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        JLabel nameLabel=new JLabel("Name:");
        nameLabel.setBounds(30,20,100,25);
        nameField.setBounds(160,20,200,25);
        nameField.setMargin(new Insets(1,10,1,10));
        nameError.setBounds(160,40,150,25);
        JLabel numberLabel=new JLabel("Account Number:");
        numberLabel.setBounds(30,70,130,25);
        numberField.setBounds(160,70,200,25);
        numberField.setMargin(new Insets(1,7,1,7));
        numbError.setBounds(160,100,260,25);
        JButton setAcctBtn=new JButton("Set Account");
        setAcctBtn.setBounds(200,140,120,30);
        balanceLabel.setBounds(180,170,250,25);
        Font myFont=new Font("Comic Sans Ms",Font.BOLD,12);
        balanceLabel.setForeground(Color.BLUE);
        nameLabel.setFont(myFont);
        numberLabel.setFont(myFont);
        setAcctBtn.setFont(myFont);
        balanceLabel.setFont(myFont);
        JLabel depoLabel= new JLabel("Deposit Amount:");
        depoLabel.setBounds(30,220,100,25);
        depoLabel.setFont(myFont);
        depoField.setBounds(160,220,150,25);
        depoField.setMargin(new Insets(1,7,1,7));
        JButton depoBtn=new JButton("Deposit");
        depoBtn.setBounds(320,220,100,25);

        JLabel withLabel=new JLabel("Withdraw Amount:");
        withLabel.setBounds(30,280,150,25);
        withLabel.setFont(myFont);
        withField.setBounds(160,280,150,25);
        withField.setMargin(new Insets(1,7,1,7));
        JButton withBtn=new JButton("Withdraw");
        withBtn.setBounds(320,280,100,25);

        messageLabel.setBounds(100,330,500,25);
        messageLabel.setFont(myFont);
        setAcctBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=nameField.getText();
                String number=numberField.getText();
                boolean isValid;
                if(name.isEmpty()){
                    nameError.setText("Fill in your name please!");
                    nameError.setForeground(Color.red);
                    isValid=false;
                }
                else{
                    nameError.setText("Valid name:)");
                    nameError.setForeground(Color.green);
                    isValid=true;
                }
                if(number.isEmpty()){
                    numbError.setText("Fill in your account number!");
                    numbError.setForeground(Color.red);
                    isValid=false;
                }
                if(!number.isEmpty()&& !number.matches("\\d{8}")){
                    numbError.setText("Account number must contain exactly 8 digits");
                    numbError.setForeground(Color.RED);
                    isValid=false;
                }
                else if(!number.isEmpty()&&number.matches("\\d{8}")){
                    numbError.setText("Valid account number" );
                    numbError.setForeground(Color.green);
                    isValid=true;

                }
                if(isValid==true&&!name.isEmpty()&&!number.isEmpty()&&number.matches("\\d{8}")){
                    messageLabel.setText("Your Bank Account has been created successfully ✅  "+name+".");
                    messageLabel.setForeground(Color.GREEN);
                }
                else{
                    messageLabel.setText("Your bank account creation has failed❌");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });
        depoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(depoField.getText());
                    if (amount > 0) {
                        bankAccount.deposit(amount);
                        updateBalance();



                        JLabel successMsg=new JLabel("Your deposit Transaction is successfully completed ");
                        successMsg.setFont(myFont);
                        successMsg.setForeground(Color.BLUE);
                        JButton successOk=new JButton("ok");
                        successOk.setFocusPainted(false);
                        Object[] successOptions={successOk};
                        final JOptionPane op_pane=new JOptionPane(successMsg,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,successOptions,successOk);
                        JDialog d=op_pane.createDialog("Success Deposit");
                        successOk.addActionListener(s->d.dispose());
                        d.setVisible(true);
                    }
                    else{
                        throw  new NumberFormatException();

                    }
                } catch (NumberFormatException ex){
                    Toolkit.getDefaultToolkit().beep();
                    JLabel depoErrorLabel=new JLabel("Invalid Amount!Your deposit transaction failed X");
                    depoErrorLabel.setForeground(Color.red);
                    JButton okErrorbtn=new JButton("Ok");
                    okErrorbtn.setFocusPainted(false);
                    Object[] buttons={okErrorbtn};
                    final JOptionPane option_pane=new JOptionPane(depoErrorLabel,JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,buttons,okErrorbtn);
                    final JDialog dial=option_pane.createDialog("Error");
                    okErrorbtn.addActionListener(er ->dial.dispose());
                    dial.setVisible(true);
                    depoField.setText("");


                }
            }
        });
        withBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double amount=Double.parseDouble(withField.getText());
                    if(bankAccount.withdraw(amount)==true){
                        updateBalance();


                        JLabel withdrawMsg=new JLabel("Your withdraw transaction is successfully completed ");
                        withdrawMsg.setFont(myFont);
                        withdrawMsg.setForeground(Color.BLUE);
                        JButton withOk=new JButton("Ok");
                        withOk.setFocusPainted(false);
                        Object[] withOptions={withOk};
                        final JOptionPane withoptionPane=new JOptionPane(withdrawMsg,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,withOptions,withOk);
                        final JDialog withdialog=withoptionPane.createDialog("Success");
                        withOk.addActionListener(withsuc -> withdialog.dispose());
                        withdialog.setVisible(true);

                    }
                    else{
                        Toolkit.getDefaultToolkit().beep();

                        JLabel withInsuf=new JLabel("Insufficient Funds X");
                        withInsuf.setFont(myFont);
                        withInsuf.setForeground(Color.red);
                        JButton witherrok= new JButton("Ok");
                        witherrok.setFocusPainted(false);
                        Object[] op={witherrok};
                        final JOptionPane errorPane=new JOptionPane (withInsuf, JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,op,witherrok);
                        final JDialog di=errorPane.createDialog("Error");
                        witherrok.addActionListener(x->di.dispose());
                        di.setVisible(true);
                        withField.setText("");
                    }
                }catch(NumberFormatException excep){
                    Toolkit.getDefaultToolkit().beep();

                    JLabel withdrEr=new JLabel("Invalid withdrawal amount  X Try again!");
                    withdrEr.setFont(myFont);
                    withdrEr.setForeground(Color.red);
                    JButton okWithd= new JButton("Ok");
                    okWithd.setFocusPainted(false);
                    Object[] op={okWithd};
                    final JOptionPane errorPane=new JOptionPane (withdrEr, JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,op,okWithd);
                    final JDialog di=errorPane.createDialog("Error");
                    okWithd.addActionListener(x->di.dispose());
                    di.setVisible(true);
                    withField.setText("");
                }
            }
        });




        add(nameLabel);
        add(nameField);
        add(nameError);
        add(numberLabel);
        add(numberField);
        add(numbError);
        add(setAcctBtn);
        add(balanceLabel);
        add(depoLabel);
        add(depoField);
        add(depoBtn);

        add(withLabel);
        add(withField);
        add(withBtn);

        add(messageLabel);





        setAcctBtn.setFocusable(false);
        depoBtn.setFocusable(false);
        withBtn.setFocusable(false);
        setVisible(true);
    }




    private void updateBalance(){

        balanceLabel.setText("Your Balance is $"+bankAccount.getBalance());


    }



    public static void main (String[] args) {
        new ATM_GUI();
    }

}

