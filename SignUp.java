import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SignUp implements ActionListener {
	
	int Id , customerID;
	JFrame frame;
	JPanel panel;
	String customername1;
	JButton yes1 ,yes2 ,Nothanks1 , cancel;
	JLabel l,l2 , bye1;
	JTextArea TakingName , takingID;
	String line;
	
	public ArrayList<Data> Accounts;
	SignUp ()
	{
		Accounts = new ArrayList<Data>();
	}
	
public void Signingup()  {
	
	frame = new JFrame("Sign Up");
	frame.setSize(650,500);
	
	panel = new JPanel();
	frame.add(panel);
	placeComponents(panel);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

private void placeComponents(JPanel panel) {
	panel.setLayout(null);
	JLabel Name = new JLabel("Please enter Your Name :");
        Name.setFont(new Font("Italic", Font.BOLD,14));
	Name.setBounds(30,60,515,25);
	panel.add(Name);
	
	TakingName = new JTextArea("");
	TakingName.setBounds(290,60,150,25);
	panel.add(TakingName);
	
	JLabel ID = new JLabel("Please enter Your ID :");
        ID.setFont(new Font("Italic", Font.BOLD,14));
	ID.setBounds(30,90,515,25);
	panel.add(ID);
	
	takingID = new JTextArea("");
	takingID.setBounds(290,90,150,25);
	panel.add(takingID);

	yes1 = new JButton("Create");
	yes1.setBounds(450,60,100,25);
	panel.add(yes1);
	
	bye1 = new JLabel("");
        bye1.setFont(new Font("Italic", Font.BOLD,14));
	bye1.setBounds(10,120,915,25);
	panel.add(bye1);
	
	
	
	yes2 = new JButton("Yes");
	yes2.setBounds(350,150,100,25);
	panel.add(yes2);
	yes2.setVisible(false);
	

	Nothanks1 = new JButton("No Thanks ");
	Nothanks1.setBounds(450,90,100,25);
	panel.add(Nothanks1);

	
	l = new JLabel("  ");
        l.setFont(new Font("Italic", Font.BOLD,14));
	l.setBounds(170,120,800,200);
	panel.add(l);
	
	l2 = new JLabel("  ");
        l2.setFont(new Font("Italic", Font.BOLD,14));
	l2.setBounds(170,150,800,200);
	panel.add(l2);
	
	yes1.addActionListener(this);
	Nothanks1.addActionListener(this);
	yes2.addActionListener(this);
	
}
public void actionPerformed(ActionEvent ae) {
	
	if(ae.getSource() == yes1)
	{
		boolean ID;
		customername1 = TakingName.getText();
		l.setText("Customer Name :  "+customername1);
		try {
		customerID = Integer.parseInt(takingID.getText());
		 ID = true; 
		    }
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Invalid ID");
			return ;
		}  
		
		
		
		File[] files = new File("D:/Bankig management system/File_Data").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile()) {
		    	
		        String filename = file.getName();
		        filename = filename.replace(".java","");
		        
		        int temp_id = Integer.parseInt(filename);///
		        
		        if(customerID == temp_id) {
		        	ID = false;
					  JOptionPane.showMessageDialog(null,"ID Already exists");
					  return;	
		        }
		        
		    }
		}
		
		if(ID == true) {
			yes2.setVisible(true);
			l2.setText("Customer ID :  "+takingID.getText());
			bye1.setText("Thanks for Signing up : Do you want to make another account :");
			//Accounts.add(new Data(customername1 , customerID));  // Talha Do filing here           <<<<<<<>>>>>>>>
		
// Filing start			
			BufferedWriter bw = null;
			 try {
			     
				 
				 File file = new File("D:/Bankig management system/File_Data",customerID+".java");

				 if (!file.exists()) {
				     file.createNewFile();
				  }

				  FileWriter fw = new FileWriter(file);
				  bw = new BufferedWriter(fw);
				  bw.write(customerID+"\n"+customername1+"\n");
			          System.out.println("File written Successfully");

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
		}
	
	if(ae.getSource() == yes2)
	{
		frame.setVisible(false);
		Signingup();
	}
	if(ae.getSource() == Nothanks1) {
		frame.setVisible(false);
	}
  }
}
	

