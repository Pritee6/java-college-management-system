package college;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
					frame.getAccessibleContext().setAccessibleName("Admin");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin() {
		setPreferredSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(600, 400));
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
				
				Login login = new Login();
				login.show();
				dispose();
			}
		});
		goBackButton.setBounds(23, 11, 86, 35);
		panel.add(goBackButton);
		
		JLabel lblNewLabel = new JLabel("Which operation do you want to perform?");
		lblNewLabel.setBounds(32, 50, 539, 60);
		lblNewLabel.setPreferredSize(new Dimension(200, 60));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add Student");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent addstudent = new AddStudent();
				addstudent.show();
				dispose();
			}
		});
		btnNewButton.setBounds(102, 123, 147, 51);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Student");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteStudent deletestudent = new DeleteStudent();
				deletestudent.show();
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(351, 123, 141, 51);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Search Student");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchStudent searchstudent = new SearchStudent();
				searchstudent.show();
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.setBounds(102, 240, 141, 51);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Update Student");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateStudent updatestudent = new UpdateStudent();
				updatestudent.show();
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setBounds(351, 240, 141, 51);
		panel.add(btnNewButton_3);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome welcome= new Welcome();
				welcome.show();
				dispose();
			}
		});
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logoutButton.setBounds(485, 304, 86, 35);
		panel.add(logoutButton);
		
		
		//Set the size or bounds of your frame
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Center the frame on the screen
        setLocationRelativeTo(null); 

        //Set visible
        setVisible(true);
	}
}
