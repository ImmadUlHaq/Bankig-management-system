import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MoneyTransfer extends JFrame implements ActionListener
{
	Data Account;
	JFrame frame;
	JPanel panel;
	JTextArea ID , Transferamount;
	JButton transferbutton , Enter , Backbutton;
	JLabel howmuch;
	int IDoftransact=0;
	
	public void Transfering_money(Data Account ) {
		
		
		this.Account = Account;
		frame = new JFrame("Transfer Money");
		frame.setSize(650,500);
		
		panel = new JPanel();
		frame.add(panel);
		placeComponents();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void placeComponents() {
		panel.setLayout(null);
		JLabel AskingID = new JLabel("Enter an ID in which you want to transfer your balance : ");
		AskingID.setBounds(5,50,800,30);
                AskingID.setFont(new Font("Italic", Font.BOLD,14));
		panel.add(AskingID);
	
		ID = new JTextArea("");
		ID.setBounds(300,80,215,25);
		panel.add(ID);
		
		howmuch = new JLabel("");
                howmuch.setFont(new Font("Italic", Font.BOLD,12));
		howmuch.setBounds(50,110,500,25);
		panel.add(howmuch);
	
		Transferamount = new JTextArea("");
		Transferamount.setBounds(300,150,215,25);
		panel.add(Transferamount);
		Transferamount.setVisible(false);
		
		Enter = new JButton("Enter");
                
		Enter.setBounds(450,180,100,25);
		panel.add(Enter);
		
		transferbutton = new JButton("Transfer");
		transferbutton.setBounds(450,210,100,25);
		panel.add(transferbutton);
		transferbutton.setVisible(false);
                
                Backbutton= new JButton("Back");
               Backbutton.setBounds(450,250,100,25);
		panel.add(Backbutton);
		
		Enter.addActionListener(this);
		transferbutton.addActionListener(this);
        Backbutton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == Enter)
		{System.out.println("came in enter ");
			try {
				IDoftransact= Integer.parseInt(ID.getText());
				System.out.println(IDoftransact+" this one");
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null,"Invalid ID");
					return ;
				}  
		boolean user;
		
		//GET ID OF FILE
     	File[] files = new File("D:/Bankig management system/File_Data").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {   //reading
		    if (file.isFile()) {
		    	
		        String filename = file.getName();
		        filename = filename.replace(".java","");
		        
		        int temp_id = Integer.parseInt(filename);
		        if(IDoftransact == temp_id) {
		        	
		        	user = true;
		        }
		        
		    }
		}
		
				if(user = true) {
					System.out.println("How much money do you want to transfer in this account ");
					 howmuch.setText("How much money do you want to transfer in this account "); 
					 Transferamount.setVisible(true);
					 transferbutton.setVisible(true);
					 Enter.setVisible(false);
				}
				if(user = false) {
					JOptionPane.showMessageDialog(null,"ID not found Make sure you enter correct ID : ");
					return ;
				}
			}	
			
		if(e.getSource() == transferbutton) {
			double transferamount;
			try {
				System.out.println("came in transaction button ");
				transferamount= Double.parseDouble(Transferamount.getText());
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null,"Invalid Amount");
					return ;
				}
			System.out.println("end ");
			
			//ADD TRANSFER MONEY IN ACCOUNT FILE OF userID == temp
			BufferedWriter bw = null;
			 try {
				 File file = new File("D:/Bankig management system/File_Data",IDoftransact+".java");
				 System.out.println(IDoftransact+"  ");
				  FileWriter fw = new FileWriter(file,true);
				  bw = new BufferedWriter(fw);
				  bw.write(transferamount+"\n");

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
			 
				   
			//data.totalbalance = data.totalbalance-data.deposit;
			Account.MoneyList.add(-transferamount);	
			
			//REMOVE TRANSFER MONEY IN CURRENT ACCOUNT
			 try {
				 
				 File file = new File("D:/Bankig management system/File_Data",Account.userID+".java");
				 System.out.println(Account.userID);
				  FileWriter fw = new FileWriter(file,true);
				  bw = new BufferedWriter(fw);
				  bw.write(-transferamount+"\n");

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
		}
		if(e.getSource() == Backbutton) {
			frame.setVisible(false);
		}
	}

}
