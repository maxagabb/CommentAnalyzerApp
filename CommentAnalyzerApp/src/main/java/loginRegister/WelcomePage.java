package loginRegister;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePage extends JPanel{
	/**
	 * @invariant this != null
	 * @precondition frame != null
	 * @postcondition this != null
	 * @param frame
	 */
	public WelcomePage(JFrame frame) {
		this.frame = frame;
		setPage();
	}
	/**
	 * sets buttons redirecting to login and 
	 * register pages
	 * @precondition this != null
	 * @postcondition frame = null
	 */
	private void setPage() {
		this.setLayout(new BorderLayout());
		JLabel welcome = new JLabel("Welcome Screen");
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		this.add(welcome, BorderLayout.NORTH);
		JButton login = new JButton("login");
		login.addActionListener(e ->{
			JFrame nextFrame = new JFrame();
			nextFrame.setBounds(frame.getX(), frame.getY(), 
					frame.getWidth(), frame.getHeight());
			frame.dispose();
			nextFrame.add(new LoginPage(nextFrame));
			nextFrame.setVisible(true);
			nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		});
		JButton register = new JButton("register");
		register.addActionListener(e ->{
			JFrame nextFrame = new JFrame();
			nextFrame.setBounds(frame.getX(), frame.getY(), 
					frame.getWidth(), frame.getHeight());
			frame.dispose();
			nextFrame.add(new RegisterPage(nextFrame));
			nextFrame.setVisible(true);
			nextFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(login,BorderLayout.CENTER);
		buttonPanel.add(register,BorderLayout.CENTER);
		this.add(buttonPanel);
	}
	private JFrame frame;
}
