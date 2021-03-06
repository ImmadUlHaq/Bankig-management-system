
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class DeleteAccounts extends JFrame implements ActionListener{
Data del = new Data();

JFrame frame;                //These are the Jcomponents that are used in different functions
JPanel panel;
JPasswordField password;
JButton Enter , bye , delete ;
JLabel Accessgranted , ID , InfoMessage;
int IDForDelete;
JTextArea IDEntered;

public void Deleting() {

	
	frame = new JFrame("Delete Account");   // These are the JComponents
	frame.setSize(650,500);                  // it is like a Wndow box you can drag it or what ever you like 
	
	panel = new JPanel();                    //It fits inside the box different components like buttons etc placed on it
	
	frame.add(panel);
	placeComponents();
	frame.setVisible(true);                       
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
}

	private void placeComponents() {
		panel.setLayout(null); 
        //we are setting sizes by our choice using bound statement
JLabel Adminpassword = new JLabel(" Enter The Admin Password   "); // It is just like a System.out.println
        Adminpassword.setBounds(50,60,600,25);        //setting  50,60 show location on panel and 515,25 show the size of it on panel
        Adminpassword.setFont(new Font("Italic", Font.BOLD,24));
panel.add(Adminpassword);

password = new JPasswordField(20);     //password will enter here
password.setBounds(300,150,150,25);
panel.add(password);
 

Enter = new JButton("Enter");   //button
Enter.setBounds(450,150,100,25);
panel.add(Enter);

Accessgranted = new JLabel("Access Granted"); // It is just like a System.out.println
Accessgranted.setBounds(300,170,415,25);
panel.add(Accessgranted);
Accessgranted.setVisible(false);

ID = new JLabel("Please Enter ID  to delete  "); // It is just like a System.out.println
  
ID.setBounds(200,230,500,25);
              ID.setFont(new Font("Italic", Font.BOLD,14));
panel.add(ID);
ID.setVisible(false);

IDEntered = new JTextArea("");        //use to take money for ID for delete
IDEntered.setBounds(290,250,150,25);
panel.add(IDEntered);
IDEntered.setVisible(false);


delete = new JButton("Delete");
delete.setBounds(450,250,100,25);
panel.add(delete);
delete.setVisible(false);


InfoMessage = new JLabel("The Entered ID has been deleted : "); // It is just like a System.out.println
InfoMessage.setBounds(180,310,415,25);
panel.add(InfoMessage);
InfoMessage.setVisible(false);



bye = new JButton("close ");  // use for hiding or closing Frame
bye.setBounds(450,290,100,25);  
panel.add(bye);
bye.setVisible(false);

Enter.addActionListener(this);
bye.addActionListener(this);
delete.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {   //when ever button will pressed program comes here
		
		if(e.getSource() == Enter)                //if  entered button pressed
		{
			String myPass=String.valueOf(password.getPassword());    //it take value you entered in password
			int password = Integer.parseInt(myPass); 
			
			if(password==Data.Passward) {       //if Entered password maches with Admin password
				Accessgranted.setVisible(true);	
				ID.setVisible(true);
				delete.setVisible(true);
				IDEntered.setVisible(true);		
			}
		}
		if(e.getSource() == bye)               //if bye button pressed
		{
			frame.setVisible(false);           //it will just close//hide the whole Frame/box 
		}
		if(e.getSource() == delete)            // if delete button pressed
		{
			
			IDForDelete= Integer.parseInt(IDEntered.getText());   
			
			File[] files = new File("D:/Bankig management system/File_Data").listFiles(); //Find the names of the files we have
			//If this pathname does not denote a directory, then listFiles() returns null. 
			
			
			for (File file : files) {   //loop runs the times no of files in the dir
			    if (file.isFile()) {    //check if file exists 
			    	
			        String filename = file.getName();    //saving the filename in string
			        filename = filename.replace(".java","");  //we save the file with names as numbers like 49 but full file nave would be 49.java
			                                                  //in order to remove java from it we write filename.replace(".java","")
			        int temp_id = Integer.parseInt(filename);  //Now filename is 49 but it is in String form so we convert it to int 
			        
			        if(IDForDelete == temp_id) {
			        	file.delete();
			        	InfoMessage.setVisible(true);
			        	bye.setVisible(true);
			        }
			        
			    }
			}
		}
	}

}