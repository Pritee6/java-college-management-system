package college;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userPassword;
	private JTextField userName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setPreferredSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 128));
		panel.setPreferredSize(new Dimension(600, 400));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Welcome welcome = new Welcome();
				welcome.show();
				dispose();
			}
		});
		goBackButton.setBounds(24, 5, 86, 35);
		panel.add(goBackButton);
		
		
		JLabel lblNewLabel = new JLabel("Admin Login Page");
		lblNewLabel.setBounds(176, 5, 284, 60);
		lblNewLabel.setPreferredSize(new Dimension(200, 60));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setPreferredSize(new Dimension(200, 60));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(45, 89, 178, 60);
		panel.add(lblUsername);
		
		userName = new JTextField("");
		userName.setPreferredSize(new Dimension(200, 60));
		userName.setHorizontalAlignment(SwingConstants.LEFT);
		userName.setForeground(new Color(192, 192, 192));
		userName.setFont(new Font("Tahoma", Font.BOLD, 16));
		userName.setBounds(248, 89, 290, 60);
		panel.add(userName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setPreferredSize(new Dimension(200, 60));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(45, 174, 178, 60);
		panel.add(lblPassword);
		
		userPassword = new JTextField("");
		userPassword.setPreferredSize(new Dimension(200, 60));
		userPassword.setHorizontalAlignment(SwingConstants.LEFT);
		userPassword.setForeground(new Color(192, 192, 192));
		userPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		userPassword.setBounds(248, 174, 290, 60);
		panel.add(userPassword);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String username = "username";
				String psw = "password";
				
				String userInputName = userName.getText();
				String userInputPassword = userPassword.getText();
				
				if(userInputName.equals(username) && userInputPassword.equals(psw)) {
					
					Admin admin = new Admin();
					admin.show();
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(loginButton, "Incorrect username or password");
				}
			}
		});
		loginButton.setBounds(279, 272, 117, 44);
		panel.add(loginButton);
		

	        //Set the size or bounds of your frame
	        setBounds(100, 100, 600, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Center the frame on the screen
	        setLocationRelativeTo(null); 

	        //Set visible
	        setVisible(true);
	    }
}
