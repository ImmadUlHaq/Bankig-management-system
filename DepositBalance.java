import java.awt.Font;
import java.awt.Panel;
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

public class DepositBalance extends JFrame implements ActionListener{  

	Data Account;        //initializaing quantities that are use globely
	JFrame frame;
	JPanel panel;
	int index;
	JTextArea depositmoney;
	JButton depositbutton , bye;
	Data data = new Data();
	JLabel seeof;
	
	public void DepositingBalance(Data Account) {  // function accepting ArrayList of Data type , index having current index of entered ID and Object of Data type 
		this.Account = Account;   //copying Arraylist of Data type to this ArrayList of Datatype
		frame = new JFrame("Deposit Balance");  
		frame.setSize(650,500);
		
		panel = new JPanel();
		frame.add(panel);
		placeComponents();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void placeComponents() {
		panel.setLayout(null);
		JLabel howmuch = new JLabel("Hello "+Account.username+" How much money do you want to deposit :");
                howmuch.setFont(new Font("Italic", Font.BOLD,12));
		howmuch.setBounds(50,30,599,25);
		panel.add(howmuch);
		
		seeof = new JLabel("");          //Label use for bye message
		seeof.setBounds(250,120,415,25);
		panel.add(seeof);
		
	
		depositmoney = new JTextArea("");        //use to take money for deposit 
		depositmoney.setBounds(290,60,150,25);
		panel.add(depositmoney);
		
		bye = new JButton("close ");  // use for hiding or closing Frame
		bye.setBounds(450,150,100,25);  
		panel.add(bye);
		bye.setVisible(false);
		
		depositbutton = new JButton("Deposit");
		depositbutton.setBounds(450,60,100,25);
		panel.add(depositbutton);
		depositbutton.addActionListener(this);
		bye.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == depositbutton)         
		{
			double temp;      
			try {
				temp= Double.parseDouble(depositmoney.getText());  //in case user entered invalid amount 
				bye.setVisible(true);
				}
				    catch(Exception e1){
					JOptionPane.showMessageDialog(null,"Invalid Amount");
					return ;
				}  
			Account.MoneyList.add(temp); 
			
			//ADD DEPOSIT MONEY IN FILE
			BufferedWriter bw = null;
			 try {
			     
				 
				 File file = new File("D:/Bankig management system/File_Data",Account.userID+".java");

				  FileWriter fw = new FileWriter(file,true);
				  bw = new BufferedWriter(fw);
				  bw.write(temp+"\n");

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
			 
			seeof.setText("Thanks for depositing : "+temp+" Rs");
		}
		if(e.getSource() == bye) {
			frame.setVisible(false);
		}	
	}
}
