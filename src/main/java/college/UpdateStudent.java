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

public class UpdateStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentRollNumber;
	private JTable sTable;
	private JTextField studentName;
	private JTextField studentClass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent();
					frame.setVisible(true);
					frame.getAccessibleContext().setAccessibleName("UpdateStudent");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateStudent() {
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
		
		JLabel lblNewLabel_1_1 = new JLabel("Roll No.");
		lblNewLabel_1_1.setPreferredSize(new Dimension(200, 60));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(60, 45, 143, 29);
		panel.add(lblNewLabel_1_1);
		
		studentRollNumber = new JTextField("");
		studentRollNumber.setPreferredSize(new Dimension(200, 60));
		studentRollNumber.setHorizontalAlignment(SwingConstants.LEFT);
		studentRollNumber.setForeground(Color.LIGHT_GRAY);
		studentRollNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentRollNumber.setBounds(200, 45, 290, 29);
		panel.add(studentRollNumber);
		
		 JButton btnNewButton_1 = new JButton("Search");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        		
	        		String studentRollnumber = studentRollNumber.getText();
	        		DefaultTableModel model = (DefaultTableModel) sTable.getModel();
	        		
	        		
	        		if(studentRollnumber.isEmpty()) {
	        			JOptionPane.showMessageDialog(btnNewButton_1, "Please enter roll number");
	        		}
	        		
	        		try {
	        			model.setRowCount(0);
	        			//Load Driver
	        			String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; 
	    	            String url = "jdbc:mysql://localhost:3306/college"; 
	    	            String user = "root";        
	    	            String password = "***"; //enter your own password here

	    	            Class.forName(mysqlJDBCDriver);
	    	            
	    	            //Create Connection
	      	            Connection con = DriverManager.getConnection(url, user, password);
	      	            
	      	            String query = "SELECT * FROM student WHERE RollNumber=?";
	      	            PreparedStatement pstatement = con.prepareStatement(query);
	      	            pstatement.setString(1, studentRollnumber); //If there are two ? in query then we have to add 2 instead of 1.
	        			ResultSet rset = pstatement.executeQuery();
	        			
	        			boolean hasResult = false;
	        			
	        			while(rset.next()) {
	        				hasResult = true;
	        				int sRollnumber = rset.getInt("RollNumber");
	        				String sRollno = Integer.toString(sRollnumber);
	        				String sName = rset.getString("StudentName");
	        				String sClass = rset.getString("Class");
	        				String row[] = {sRollno, sName, sClass};
	        				model.addRow(row);	
	        			}
	        			
	        			if(!hasResult) {
	        				JOptionPane.showMessageDialog(btnNewButton_1, "Roll Number does not exist!");
	        			}
	        			
	        		
	        				      	            
	        		}catch(Exception err) {
	        			JOptionPane.showMessageDialog(btnNewButton_1, err);
	        		}
	        		
	        	}
	        });
	        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        btnNewButton_1.setBounds(60, 165, 133, 23);
	        panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Update Student Form");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(128, 0, 64));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_2.setBounds(149, 0, 290, 34);
		panel.add(lblNewLabel_2);		
		       	
		//ADD JTABLE & JSCROLLPANE HERE (GUI SETUP)
	     
        String[] columns = {"Roll Number", "Student Name", "Class"};
        
        // Initialize DefaultTableModel with 0 initial rows
        DefaultTableModel model = new DefaultTableModel(null, columns);
        sTable = new JTable(model);

        // Wrap sTable inside JScrollPane
        JScrollPane scrollPane = new JScrollPane(sTable);
        scrollPane.setBounds(60, 201, 430, 136); // Positioning on panel
        panel.add(scrollPane);
        
        JButton btnNewButton_1_1 = new JButton("Update");
        btnNewButton_1_1.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
				
				String sName = studentName.getText();
				String sClass = studentClass.getText();
				String sRollNo = studentRollNumber.getText();
				int sRollNumber = Integer.parseInt(sRollNo);
				
				try {
					String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver"; 
    	            String url = "jdbc:mysql://localhost:3306/college"; 
    	            String user = "root";        
    	            String password = "***"; //enter your own password here

    	            Class.forName(mysqlJDBCDriver);
    	            
    	            //Create Connection
      	            Connection con = DriverManager.getConnection(url, user, password);
      	            
      	            String query = "UPDATE student SET StudentName = ?, Class = ? WHERE RollNumber = ?";
      	            PreparedStatement pstatement = con.prepareStatement(query);
      	            pstatement.setString(1, sName);
      	            pstatement.setString(2, sClass);
      	            pstatement.setInt(3, sRollNumber);
        			pstatement.executeUpdate();
        			
        			JOptionPane.showInternalMessageDialog(btnNewButton_1_1, "Data updated successfully!");
        			
        			
				}catch(Exception err) {
					JOptionPane.showMessageDialog(btnNewButton_1_1, err);
				}
			}
        });
        btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnNewButton_1_1.setBounds(357, 165, 133, 23);
        panel.add(btnNewButton_1_1);
        
        JButton btnNewButton_1_2 = new JButton("Load Data");
        btnNewButton_1_2.addActionListener(new ActionListener() {
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
        			JOptionPane.showMessageDialog(btnNewButton_1_1, error.getMessage());
        		}
        	}
        });
        btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnNewButton_1_2.setBounds(210, 165, 133, 23);
        panel.add(btnNewButton_1_2);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Student Name");
        lblNewLabel_1_1_1.setPreferredSize(new Dimension(200, 60));
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNewLabel_1_1_1.setBounds(60, 85, 143, 29);
        panel.add(lblNewLabel_1_1_1);
        
        studentName = new JTextField("");
        studentName.setPreferredSize(new Dimension(200, 60));
        studentName.setHorizontalAlignment(SwingConstants.LEFT);
        studentName.setForeground(Color.LIGHT_GRAY);
        studentName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        studentName.setBounds(200, 85, 290, 29);
        panel.add(studentName);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Class");
        lblNewLabel_1_1_1_1.setPreferredSize(new Dimension(200, 60));
        lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNewLabel_1_1_1_1.setBounds(60, 125, 143, 29);
        panel.add(lblNewLabel_1_1_1_1);
        
        studentClass = new JTextField("");
        studentClass.setPreferredSize(new Dimension(200, 60));
        studentClass.setHorizontalAlignment(SwingConstants.LEFT);
        studentClass.setForeground(Color.LIGHT_GRAY);
        studentClass.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        studentClass.setBounds(200, 125, 290, 29);
        panel.add(studentClass);
        
       


        // --- REST OF YOUR EXISTING CODE (e.g., setVisible) ---
		
		//Set the size or bounds of your frame
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Center the frame on the screen
        setLocationRelativeTo(null); 

        //Set visible
        setVisible(true);

	}
}
