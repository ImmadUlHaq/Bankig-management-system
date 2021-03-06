import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class TransactionClass extends JFrame implements ActionListener{
	Data Account;
	JFrame frame;
	JPanel panel;
	JTextArea Transactionmoney;
	JButton Transactionbutton , backbutton;
	
	public void TransactingBalance(Data Account ) {
		
		this.Account = Account;
		frame = new JFrame("Transactions");
		frame.setSize(650,500);
		
		panel = new JPanel();
		frame.add(panel);
		placeComponents();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void placeComponents() {
		panel.setLayout(null);
		JLabel howmuch = new JLabel("How much money do you want to Withdraw : "+Account.username);
		howmuch.setBounds(150,60,215,25);
		panel.add(howmuch);
		
		Transactionmoney = new JTextArea("");
		Transactionmoney.setBounds(300,90,215,25);
		panel.add(Transactionmoney);
		
		Transactionbutton = new JButton("Withdraw");
		Transactionbutton.setBounds(450,60,100,25);
		panel.add(Transactionbutton);
		Transactionbutton.addActionListener(this);
	
		backbutton = new JButton("Back");
		backbutton.setBounds(450,120,100,25);
		panel.add(backbutton);
		backbutton.addActionListener(this);
	backbutton.setVisible(false);
		
	}
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == Transactionbutton)
		{
			double temp;
			try {
				temp= Double.parseDouble(Transactionmoney.getText());
				backbutton.setVisible(true);
				  }
				catch(Exception e1){
					JOptionPane.showMessageDialog(null,"Invalid Entry");
					return ;
				}  
			Account.MoneyList.add(-temp);
			
			//ADD TRANSACTION MONEY IN FILE
			BufferedWriter bw = null;
			 try {
			     
				 
				 File file = new File("D:/Bankig management system/File_Data",Account.userID+".java");

				  FileWriter fw = new FileWriter(file,true);
				  bw = new BufferedWriter(fw);
				  bw.write(-temp+"\n");

			      } catch (IOException ioe) {
				   ioe.printStackTrace();
				}
				finally
				{ 
				   try{
				      if(bw!=null)
					 bw.close();
				   }catch(Exception ex){
				       System.out.println("Error in closing the BufferedWriter"+ex);
				    }
				}
			
			System.out.println(-temp);
			System.out.println(Transactionmoney.getText());
		}
		
		if(e.getSource() == backbutton)
		{
			frame.setVisible(false);
		}
		
	}

}

