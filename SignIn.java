import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class SignIn implements ActionListener {
Data balance;
String n , s;
int id , index ;
Data data = new Data();
public Data Account;

int Id , customerID;               //Initializing the components that are usually use in different functions
JFrame frame , checkbal;
JPanel panel , panel2;
String customername1;
JButton create ,checkbalance , depositbalance , transaction , transfer , back;
JLabel l,  wellcome , account_info , money_info;
JTextArea TakingName , takingID;
JPasswordField password;


		public void Signingin() {  //function create frame and panel
				frame = new JFrame("Sign In");
				frame.setSize(650,500);
				
				panel = new JPanel();
				frame.add(panel);
				placeComponents();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	
		private void placeComponents() {                // function puts components in panel
			panel.setLayout(null);
			JLabel signintro = new JLabel("Please enter Your ID :");
                        signintro.setFont(new Font("Italic", Font.BOLD,14));
			signintro.setBounds(50,60,415,25);
			panel.add(signintro);
			
			password = new JPasswordField(20);
			password.setBounds(290,60,150,25);
			panel.add(password);
			
			wellcome = new JLabel("");
                        wellcome.setFont(new Font("Italic", Font.BOLD,14));
			wellcome.setBounds(180,100,415,25);
			panel.add(wellcome);
		
			
			create = new JButton("Sign in");
			create.setBounds(450,60,100,25);
			panel.add(create);
			
			checkbalance = new JButton("Check Balance");
                 	checkbalance.setBounds(180,170,130,25);
			panel.add(checkbalance);
			checkbalance.setVisible(false);
			
			depositbalance = new JButton("Deposit Balance");
			depositbalance.setBounds(320,170,130,25);
			panel.add(depositbalance);
			depositbalance.setVisible(false);
			
			
			transaction = new JButton("Transactions");
			transaction.setBounds(180,200,130,25);
			panel.add(transaction);
			transaction.setVisible(false);
			
			transfer = new JButton("Transfer Balance");
			transfer.setBounds(320,200,130,25);
			panel.add(transfer);
			transfer.setVisible(false);

			create.addActionListener(this);
			checkbalance.addActionListener(this);
			depositbalance.addActionListener(this);
			transaction.addActionListener(this);
			transfer.addActionListener(this);
			
		}
		
		public void actionPerformed(ActionEvent ae) {    //Actions performed when buttons are pressed
			
			if(ae.getSource() == create)
			{
				try {
					String myPass=String.valueOf(password.getPassword());
					customerID = Integer.parseInt(myPass);  
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null,"Invalid ID");
						return ;
					}
			     	boolean user = false;
				   
			     	//GET ID OF FILE 
			     	File[] files = new File("D:/Bankig management system/File_Data").listFiles();
					//If this pathname does not denote a directory, then listFiles() returns null. 

					for (File file : files) {
					    if (file.isFile()) {
					    	
					        String filename = file.getName();
					        filename = filename.replace(".java","");
					        
					        int temp_id = Integer.parseInt(filename);
					        if(customerID == temp_id) {
					        	user = true;
					        }
					        
					    }
					}
					
					
					 if(user == true) 
					 {
						 
						 
						 try{
							 
							   FileReader fileReader = new FileReader("D:/Bankig management system/File_Data/"+customerID+".java");
					           BufferedReader bufferedReader = new BufferedReader(fileReader);   
					           
					          customerID = Integer.parseInt(bufferedReader.readLine());
					          customername1 = bufferedReader.readLine();
					          
					          Account = new Data(customername1,customerID);
					          
					          String line = bufferedReader.readLine();
					          while(line != null) {
					        	  
					        	  double money = Double.parseDouble(line);
					        	  Account.MoneyList.add(money);
					        	  line = bufferedReader.readLine();
					        
					          }
					          
					          bufferedReader.close();
							  }
					        catch(IOException e)
					        {
					            System.out.println("INVALID ....");
					        }
						
						  wellcome.setText("ID confermed : Wellcome : "+Account.username+"     What do you want to do : "+Account.username); 
					        checkbalance.setVisible(true);
							depositbalance.setVisible(true);
							transaction.setVisible(true);
							transfer.setVisible(true);
					 }

					 if(user == false) 
					 {
						 JOptionPane.showMessageDialog(null,"ID Not confermed Sir Please enter correct ID");
					 }
				}
			
			if(ae.getSource() == checkbalance)
			{
				frame.setVisible(false);
				CheckBalance();
			}
			if(ae.getSource() == depositbalance) {   //it opens another class and make depositbalance functionality there
				DepositBalance db = new DepositBalance();
				 db.DepositingBalance(Account);
				 
			}
			if(ae.getSource() == transaction) {   //same
				TransactionClass wd = new TransactionClass();
				wd.TransactingBalance(Account);
				System.out.println("transaction ");
			}
			if(ae.getSource() == transfer) {
				MoneyTransfer mt = new MoneyTransfer();
				mt.Transfering_money(Account );
				System.out.println("transfer ");
			}
			if(ae.getSource() == back) {
				checkbal.setVisible(false);
				System.out.println("transfer ");
			}
		}

		public void CheckBalance() {       //I have done checkbalance part in this class's function because it has small code with rispect to other buttons like deposit etc
			checkbal = new JFrame("Check Balance");  //create new frame
			checkbal.setSize(650,500);
			
			
			
			panel2 = new JPanel();                 //with seperate panel
			panel2.setLayout(null);

			back = new JButton("Back");
			back.setBounds(320,150,130,25);
			panel2.add(back);
			back.setVisible(true);
			
			checkbal.add(panel2);
			JLabel account_info = new JLabel(Account.username +" your transactions are :\n");

			account_info.setBounds(150,60,215,25);
			panel2.add(account_info);
			data.totalbalance = 0;
			for(int j = 0 ; j<Account.MoneyList.size() ; j++) {                    // total balance calculation
				data.totalbalance = Account.MoneyList.get(j) + data.totalbalance;
			}
			
			money_info = new JLabel("Your Money List is : "+Account.MoneyList+"           And Total balance "+ data.totalbalance);  // printing all transactions of customer that is signed in

			money_info.setBounds(150,100,21500,25);
			panel2.add(money_info);
			back.addActionListener(this);
			checkbal.setVisible(true);  
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}


