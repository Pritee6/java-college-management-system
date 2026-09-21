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

public class Search extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentRollNumber;
	private JTable sTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
					frame.setVisible(true);
					frame.getAccessibleContext().setAccessibleName("Search");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Search() {
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
				
				Welcome welcome = new Welcome();
				welcome.show();
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
		lblNewLabel_1_1.setBounds(60, 79, 143, 41);
		panel.add(lblNewLabel_1_1);
		
		studentRollNumber = new JTextField("");
		studentRollNumber.setPreferredSize(new Dimension(200, 60));
		studentRollNumber.setHorizontalAlignment(SwingConstants.LEFT);
		studentRollNumber.setForeground(Color.LIGHT_GRAY);
		studentRollNumber.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		studentRollNumber.setBounds(200, 79, 290, 41);
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
	        btnNewButton_1.setBounds(200, 131, 133, 51);
	        panel.add(btnNewButton_1);
	        
	        //ADD JTABLE & JSCROLLPANE HERE (GUI SETUP)
	     
	        String[] columns = {"Roll Number", "Student Name", "Class"};
	        
	        // Initialize DefaultTableModel with 0 initial rows
	        DefaultTableModel model = new DefaultTableModel(null, columns);
	        sTable = new JTable(model);

	        // Wrap sTable inside JScrollPane
	        JScrollPane scrollPane = new JScrollPane(sTable);
	        scrollPane.setBounds(60, 201, 430, 136); // Positioning on panel
	        panel.add(scrollPane);
	        
	        JButton btnNewButton_1_1 = new JButton("Load Data");
	        btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        btnNewButton_1_1.setBounds(357, 131, 133, 51);
	        btnNewButton_1_1.addActionListener(new ActionListener() {
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
	        
	        panel.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Search Student Form");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(128, 0, 64));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblNewLabel_2.setBounds(149, 11, 290, 39);
		panel.add(lblNewLabel_2);
				       	
		/*// --- ADDING JTABLE STARTS HERE ---

        // 1. Table Columns and Data
        String[] columns = {"Roll Number", "Name", "Class"};
        Object[][] data = {
            //{101, "Pritee M", "10th", "Regular"},
            //{102, "Milan M", "12th", "Distance"}
        };

        // 2. Create Table Model and JTable
        DefaultTableModel model = new DefaultTableModel(data, columns);
        sTable = new JTable(model);
        
        // 3. Wrap JTable in JScrollPane (Required for headers)
        JScrollPane scrollPane = new JScrollPane(sTable);
        scrollPane.setMaximumSize(new Dimension(300, 200));
        panel.add(scrollPane);
        
        // 4. Set position & size on your existing screen (x, y, width, height)
        scrollPane.setBounds(60, 201, 430, 136);*/
        
        // --- REST OF EXISTING CODE (e.g., setVisible) ---
		
		//Set the size or bounds of your frame
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Center the frame on the screen
        setLocationRelativeTo(null); 

        //Set visible
        setVisible(true);

	}

}
