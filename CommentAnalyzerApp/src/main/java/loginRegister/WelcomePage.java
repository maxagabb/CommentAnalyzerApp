package loginRegister;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

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
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.PAGE_AXIS));
		login.setPreferredSize(register.getPreferredSize());
		JPanel pane2 = new JPanel();pane2.add(register);
		JPanel pane = new JPanel();pane.add(login);
		buttonPanel.add(Box.createRigidArea(new Dimension(120,20)));
		buttonPanel.add(pane);
		buttonPanel.add(Box.createRigidArea(new Dimension(120,40)));
		buttonPanel.add(pane2);
		buttonPanel.add(Box.createRigidArea(new Dimension(120,20)));
		JPanel buttonPanel2 = new JPanel();
		buttonPanel2.add(buttonPanel);
		buttonPanel.setBorder(new EtchedBorder());
		JPanel buttonPanel3 = new JPanel();
		buttonPanel3.setLayout(new GridBagLayout());
		buttonPanel3.add(buttonPanel2);
		this.add(buttonPanel3);
	}
	private JFrame frame;
}
