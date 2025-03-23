import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.print.*;
import javax.print.*;
import java.sql.*;
import java.util.Date;
import net.proteanit.sql.*;

class CropProject extends JFrame
{
	JPanel center=new JPanel();

	JPanel loginP=new JPanel();

	JPanel optionP=new JPanel();

	JPanel calbillP=new JPanel();

	JPanel displayP=new JPanel();

	JPanel northP=new JPanel();
	JLabel label1=new JLabel("WELLCOME",JLabel.CENTER);

	CardLayout c=new CardLayout(10,10);

	Connection conn;
	Statement stmt;
	PreparedStatement ps;

	JTable table=new JTable();

	{
		setSize(800,680);
		setTitle("Crop Shop");
		setResizable(false);
		setLocation(200,0);

		Image icon = Toolkit.getDefaultToolkit().getImage("logoagri.jpg");

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("Driver is registered");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/croprecord", "chetan","chetandhangar");
			ps=conn.prepareStatement("insert into croprecord values(?,?,?,?,?,?,?)");
			stmt=conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		setIconImage(icon);

		setBackground(Color.blue);
		label1.setFont(new Font("Arial",Font.BOLD,16));
		northP.setBackground(Color.green);
		northP.add(label1);
		add(northP,"North");

		center.setLayout(c);

		center.setBackground(Color.black);

		loginMe();
		optionMe();
		calbillMe();
		displayMe();

		center.add(loginP);
		center.add("option",optionP);
		center.add("calbill",calbillP);
		center.add("display",displayP);
		add(center);

	}
//------------------------------------LOGIN PAGE----------------------------------------------------
	void loginMe()
	{
		loginP.setLayout(null);

		JLabel loginLab=new JLabel("Login");					//Login Label
		loginLab.setFont(new Font("Arial",Font.BOLD,26));
		loginLab.setForeground(Color.red);
		loginLab.setBounds(330,20,200,60);

		JLabel userL=new JLabel("Username");					//Username Label
		userL.setFont(new Font("Arial",Font.BOLD,16));
		userL.setBounds(50,150,100,60);
		userL.setForeground(Color.white);

		JTextField usern=new JTextField(30);					//Username Field
		usern.setBounds(155,160,155,35);
		usern.setFont(new Font("Times New Romen",Font.PLAIN,14));

		JLabel passL=new JLabel("Password");					//Password Label
		passL.setFont(new Font("Arial",Font.BOLD,16));
		passL.setBounds(50,240,100,60);
		passL.setForeground(Color.white);

		JPasswordField pf=new JPasswordField(30);				//Password Field
		pf.setBounds(155,250,155,35);

		JButton loginB=new JButton("Login");					//Login Button
		loginB.setFont(new Font("Arial",Font.BOLD,16));
		loginB.setBounds(220,340,100,30);

		JLabel agriI=new JLabel(new ImageIcon("agriculture.jpg"));	//Background Image
		agriI.setBounds(10,80,740,600);

		loginB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("buttton click");
				String ustr=usern.getText();

				char ch[]=pf.getPassword();
				String pfstr = new String(ch);

				if(ustr.equals("admin") && pfstr.equals("password"))
				{
					JOptionPane.showMessageDialog(null, " Successfully Login ","Login",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("user");
					c.next(center);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please Enter valid username & password","Error",JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		loginP.add(loginLab);									//Add all components
		loginP.add(userL);
		loginP.add(usern);
		loginP.add(passL);
		loginP.add(pf);
		loginP.add(loginB);
		loginP.add(agriI);
	}
//-------------------------------------------OPTION PAGE----------------------------------------------------
	void optionMe()
	{
		optionP.setLayout(null);
		//optionP.setBackground(Color.cyan);

		JLabel optionL=new JLabel(new ImageIcon("goldagri.jpg"));	//optionPage Image
		optionL.setBounds(0,0,800,575);

		JButton billB=new JButton("Billing");			//Billing Button
		billB.setBounds(300,50,200,50);
		billB.setFont(new Font("Arial",Font.BOLD,16));

		JButton displayB=new JButton("Display Records");	//Display Record Button
		displayB.setBounds(300,150,200,50);
		displayB.setFont(new Font("Arial",Font.BOLD,16));

		JButton exitB=new JButton("Exit");				//Exit Button
		exitB.setBounds(300,250,200,50);
		exitB.setFont(new Font("Arial",Font.BOLD,16));

		billB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e2)
			{
				System.out.println("bill button");
				c.show(center,"calbill");
			}
		});

		displayB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e3)
			{
				System.out.println("display button");

				try {
						ResultSet rs = ps.executeQuery("select * from croprecord");
						table.setModel(DbUtils.resultSetToTableModel(rs));
						}
						catch(Exception e9)
						{}
				c.show(center,"display");
			}
		});

		exitB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				System.exit(0);
			}
		});

		optionP.add(billB);				//Add all components
		optionP.add(displayB);
		optionP.add(exitB);
		optionP.add(optionL);
	}
//----------------------------Calbill--------------------------------------------
	void calbillMe()
	{
		calbillP.setLayout(null);

		//JLabel cropI=new JLabel(new ImageIcon("wheatImage.jpg"));	//Background Image
		//cropI.setBounds(0,0,700,475);

		JLabel nameL=new JLabel("Name");					//Name Label
		nameL.setBounds(50,50,50,30);
		nameL.setFont(new Font("Arial",Font.BOLD,16));

		JTextField nameTF=new JTextField(20);				//Name TextField
		nameTF.setBounds(150,50,150,30);
		nameTF.setFont(new Font("Arial",Font.PLAIN,14));

		JLabel mobL=new JLabel("Mob No.");					//Mob No Label
		mobL.setBounds(50,100,90,30);
		mobL.setFont(new Font("Arial",Font.BOLD,16));

		JTextField mobTF=new JTextField(10);				//Mob No TextField
		mobTF.setBounds(150,100,150,30);
		mobTF.setFont(new Font("Arial",Font.PLAIN,14));

		JLabel cropL=new JLabel("Crop");					//Crop Label
		cropL.setBounds(50,150,90,30);
		cropL.setFont(new Font("Arial",Font.BOLD,16));

		String nameCrop[]={"wheat","rice","maize","jowar","bajra"};

		JComboBox cb=new JComboBox(nameCrop);				//Crop Names ComboBox
		cb.setBounds(150,150,100,30);

		JLabel kqL=new JLabel("Quental");					//Quental Label
		kqL.setBounds(50,200,90,30);
		kqL.setFont(new Font("Arial",Font.BOLD,16));

		JTextField kqTF=new JTextField(10);				//Quental TextField
		kqTF.setBounds(150,200,150,30);
		kqTF.setFont(new Font("Arial",Font.PLAIN,14));

		JLabel priceL=new JLabel("Price rate:");		//Price Rate Label
		priceL.setBounds(50,250,100,30);
		priceL.setFont(new Font("Arial",Font.BOLD,16));

		JTextField prTF=new JTextField(10);				//Price Rate TextField
		prTF.setBounds(150,250,150,30);
		prTF.setFont(new Font("Arial",Font.PLAIN,14));

		JButton calculateB=new JButton("Calculate Bill");	//Calculate Button
		calculateB.setBounds(125,300,150,30);
		calculateB.setFont(new Font("Arial",Font.BOLD,16));

		JTextArea billDisTA=new JTextArea(45,85);			//Bill TextArea
		billDisTA.setBounds(425,40,300,300);
		billDisTA.setFont(new Font("Times New Romen",Font.PLAIN,14));

		Border border = BorderFactory.createLineBorder(Color.RED, 5);
		 billDisTA.setBorder(border);

		calculateB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e7)
			{
				try{														//	Insert the Data
				String nameC=nameTF.getText();
				String mobC=mobTF.getText();
				String selecteC=(String)cb.getSelectedItem();
				Double Quental=Double.parseDouble(kqTF.getText());
				Double Rate=Double.parseDouble(prTF.getText());
				Double Total=Quental*Rate;
				Date date=new Date();
				String date1=date.toString();


				ps.setString(1,nameC);
				ps.setString(2,mobC);
				ps.setString(3,selecteC);
				ps.setDouble(4,Quental);
				ps.setDouble(5,Rate);
				ps.setDouble(6,Total);
				ps.setString(7,date1);

				ps.executeUpdate();
				//JOptionPane.showMessageDialog(null,"Record is inserted");

				billDisTA.setText("********************************************************\nName:"+nameC+"\nMob:"+mobC+"\n********************************************************\nCrop        Que       Rate        Total\n********************************************************\n"+selecteC+"     "+Quental+"        "+Rate+"           "+ Total+"                   \n\n*******************************************************\n\n\tThank    You");

			}
			catch(Exception e8)
			{}

			}
		});

		JButton printB=new JButton("Print");		//Print Button
		printB.setBounds(500,350,100,30);
		printB.setFont(new Font("Arial",Font.BOLD,16));

		printB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e5)
			{
				try{
				billDisTA.print();
				}
				catch(Exception e6)
				{
					System.out.println(e6);
				}
			}
		});

		JButton previous=new JButton("Back");	//Previous Button
		previous.setBounds(100,500,150,30);
		previous.setFont(new Font("Arial",Font.BOLD,16));

		previous.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e4)
			{
				c.show(center,"option");
			}
		});

		calbillP.add(nameL);			//Add All Components
		calbillP.add(nameTF);
		calbillP.add(mobL);
		calbillP.add(mobTF);
		calbillP.add(cropL);
		calbillP.add(cb);
		calbillP.add(kqL);
		calbillP.add(kqTF);
		calbillP.add(priceL);
		calbillP.add(prTF);
		calbillP.add(calculateB);
		calbillP.add(billDisTA);
		calbillP.add(printB);
		calbillP.add(previous);
		//calbillP.add(cropI);

	}
	//------------------------------------------------
	void displayMe()
	{
		displayP.setLayout(null);

		table.getTableHeader().setBackground(Color.GREEN);			//For Styling the Table
		table.setGridColor(Color.red);
		table.setBackground(Color.yellow);

		JScrollPane jp=new JScrollPane(table);
		jp.setBounds(15,10,735,300);

		JButton backB=new JButton("Back");	//Previous Button
		backB.setBounds(350,400,90,30);
		backB.setFont(new Font("Arial",Font.BOLD,16));

				backB.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e11)
					{
						c.show(center,"option");
					}
				});

		displayP.add(jp);
		displayP.add(backB);
	}

	public static void main(String [] args)
	{
		CropProject cp=new CropProject();
		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try
			{
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			}
			catch(Exception e)
	{}

		cp.setVisible(true);
	}
}