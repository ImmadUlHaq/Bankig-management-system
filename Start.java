
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Start extends JFrame implements ActionListener {
	JLabel l1 , l2 , l3;
	JButton Signingup , Signingin , delete , Yes , No;
	String Opt;
	SignUp obj1 = new SignUp();
	SignIn obj2 = new SignIn();
	DeleteAccounts obj3 = new DeleteAccounts();
	JPanel panel;
	JFrame frame;
	
	public void Home() {
		
		frame = new JFrame("lets start");
		frame.setSize(650,500);
		
		panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	private void placeComponents(JPanel panel2) {
		panel.setLayout(null);
		l1 = new JLabel("Select the following Options:  ");
		l1.setBounds(150,60,215,25);
		panel.add(l1);
		
		l2 = new JLabel("Select Your Options:  ");
		l2.setBounds(150,100,215,25);
		panel.add(l2);
		
		
		l3 = new JLabel("Press Yes to continue and No to exit:  ");
		l3.setBounds(150,190,215,25);
		panel.add(l3);
		
		
		Signingup = new JButton("SigningUp");
		Signingup.setBounds(150,150,120,25);
		panel.add(Signingup);
		
		Signingin = new JButton("SigningIn");
		Signingin.setBounds(270,150,120,25);
		panel.add(Signingin);
		
		delete = new JButton("Delete Account");
		delete.setBounds(390,150,120,25);
		panel.add(delete);
		
		Yes = new JButton("Yes");
		Yes.setBounds(150,240,100,25);
		panel.add(Yes);
		
		No = new JButton("No");
		No.setBounds(270,240,100,25);
		panel.add(No);
		
		
		Signingup.addActionListener(this);
		Signingin.addActionListener(this);
		delete.addActionListener(this);
		Yes.addActionListener(this);
		No.addActionListener(this);
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		
	}

	public void actionPerformed(ActionEvent ae) {
    
		
		if(ae.getSource() == Signingup)
		{
			obj1.Signingup();
			//System.out.println("Thanks for Making an accounts : ");
		}
		if(ae.getSource() == Signingin)
			
		{	obj2.Signingin();}
		if(ae.getSource() == delete)
				{	
			obj3.Deleting();
				}	
		if(ae.getSource() == Yes) {
			Opt = "yes";
		}
		if(ae.getSource() == No) {
			Opt = "No";
			setVisible(false);}
	}

}
