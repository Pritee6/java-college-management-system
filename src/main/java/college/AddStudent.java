package college;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AddStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentClass;
	private JTextField studentRollNo;
	private JTable sTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudent frame = new AddStudent();
					frame.setVisible(true);
					frame.getAccessibleContext().setAccessibleName("AddStudent");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddStudent() {
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
				
				Admin admin = new Admin();
				admin.show();
				dispose();
			}
		});
		goBackButton.setBounds(24, 5, 86, 35);
		panel.add(goBackButton);
		
		JLabel lblNewLabel = new JLabel("Student Name");
		lblNewLabel.setBounds(81, 61, 115, 24);
		lblNewLabel.setPreferredSize(new Dimension(200, 60));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JTextField studentName = new JTextField("");
		studentName.setPreferredSize(new Dimension(200, 60));
		studentName.setHorizontalAlignment(SwingConstants.LEFT);
		studentName.setForeground(new Color(192, 192, 192));
		studentName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentName.setBounds(246, 61, 290, 24);
		panel.add(studentName);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Class");
		lblNewLabel_1.setPreferredSize(new Dimension(200, 60));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1.setBounds(53, 96, 143, 24);
		panel.add(lblNewLabel_1);
		
		studentClass = new JTextField("");
		studentClass.setPreferredSize(new Dimension(200, 60));
		studentClass.setHorizontalAlignment(SwingConstants.LEFT);
		studentClass.setForeground(Color.LIGHT_GRAY);
		studentClass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentClass.setBounds(246, 96, 290, 24);
		panel.add(studentClass);
		
		JLabel lblNewLabel_1_1 = new JLabel("Roll No.");
		lblNewLabel_1_1.setPreferredSize(new Dimension(200, 60));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(53, 131, 143, 24);
		panel.add(lblNewLabel_1_1);
		
		studentRollNo = new JTextField("");
		studentRollNo.setPreferredSize(new Dimension(200, 60));
		studentRollNo.setHorizontalAlignment(SwingConstants.LEFT);
		studentRollNo.setForeground(Color.LIGHT_GRAY);
		studentRollNo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentRollNo.setBounds(246, 131, 290, 24);
		panel.add(studentRollNo);
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sName = studentName.getText();
				String sClass = studentClass.getText();
				String sRollNo = studentRollNo.getText();
				int sRollNumber = Integer.parseInt(sRollNo);
				
				try {
					String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; 
    	            String url = "jdbc:mysql://localhost:3306/college"; 
    	            String user = "root";        
    	            String password = "***"; //enter your own password here

    	            Class.forName(mysqlJDBCDriver);
    	            
    	            //Create Connection
      	            Connection con = DriverManager.getConnection(url, user, password);
      	            
      	            String query = "INSERT INTO student VALUES(?,?,?)";
      	            PreparedStatement pstatement = con.prepareStatement(query);
      	            pstatement.setInt(1, sRollNumber);
      	            pstatement.setString(2, sName);
      	            pstatement.setString(3, sClass);
        			pstatement.executeUpdate();
        			
        			JOptionPane.showInternalMessageDialog(btnAddStudent, "Data Inserted Successfully!");
        			
        			
				}catch(Exception err) {
					JOptionPane.showMessageDialog(btnAddStudent, err);
				}
			}
		});
		btnAddStudent.setBounds(153, 166, 147, 35);
		panel.add(btnAddStudent);
		
		JLabel lblNewLabel_2 = new JLabel("Add Student Form");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(128, 0, 64));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblNewLabel_2.setBounds(153, 5, 290, 35);
		panel.add(lblNewLabel_2);
		
		//ADD JTABLE & JSCROLLPANE HERE (GUI SETUP)
	     
        String[] columns = {"Roll Number", "Student Name", "Class"};
        
        // Initialize DefaultTableModel with 0 initial rows
        DefaultTableModel model = new DefaultTableModel(null, columns);
        sTable = new JTable(model);

        // Wrap sTable inside JScrollPane
        JScrollPane scrollPane = new JScrollPane(sTable);
        scrollPane.setBounds(81, 211, 455, 136); // Positioning on panel
        panel.add(scrollPane);
        
		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		try {
        			//Load Driver
        			String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; 
    	            String url = "jdbc:mysql://localhost:3306/college"; 
    	            String user = "root";        
    	            String password = "***"; //enter your own password here

    	            Class.forName(mysqlJDBCDriver);
    	            
    	            //Create Connection
      	            Connection con = DriverManager.getConnection(url, user, password);
      	            
      	            //Create Query
      	            String query = "SELECT * FROM student";
      	            PreparedStatement preparedStatement = con.prepareStatement(query);
      	            
      	            //Execute Query
      	            ResultSet resultSet = preparedStatement.executeQuery();
      	            
      	            DefaultTableModel dmodel = (DefaultTableModel)sTable.getModel();
      	            dmodel.setRowCount(0); // Clear existing table rows before adding new data (prevents duplicates)
      	            
      	            while(resultSet.next()) {
      	            	int sRollNumber = resultSet.getInt("RollNumber");
      	            	String studentRollNumber = Integer.toString(sRollNumber);
      	            	String studentName = resultSet.getString("StudentName");
      	            	String className = resultSet.getString("Class");
      	            	String row[] = {studentRollNumber, studentName, className};
      	            	dmodel.addRow(row);	
      	            }
      	            con.close();  
      	            
        		}catch(Exception error) {
        			JOptionPane.showMessageDialog(btnLoadData, error.getMessage());
        		}
        	}
		});
		btnLoadData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLoadData.setBounds(329, 166, 147, 35);
		panel.add(btnLoadData);
	
		
		 //Set the size or bounds of your frame
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Center the frame on the screen
        setLocationRelativeTo(null); 

        //Set visible
        setVisible(true);

	}
}
