import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Calender extends JPanel{
	private static TitlePanel win;
	
	static WarningDialog dialog = null;
	int chknum = 0;

	static class WarningDialog extends JDialog {
		public WarningDialog() {
			setTitle("주의");
			setSize(300, 150);
			JLabel message = new JLabel("이전 단계를 먼저 클리어해주세요");
			message.setFont(new Font("나눔스퀘어", Font.PLAIN, 16));
			message.setHorizontalAlignment(JLabel.CENTER);
			add(message);
		}
	}
	
	public Calender(TitlePanel win) {
		setLayout(null);
		this.win=win;
		
		//backIcon
		JButton backButton = new JButton();
		backButton.setOpaque(false);  backButton.setBorderPainted(false); 
		backButton.setFocusPainted(false); backButton.setContentAreaFilled(false); 
		backButton.setSize(50,50);
		backButton.setLocation(0,0);
		add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				dialog = null;
				win.change("MainPanel");
			}
		});
		
		//calenderIcon
		ImageIcon icon14 = new ImageIcon("calender/OcalenderIcon_14.jpg");
		ImageIcon sicon14 = new ImageIcon(icon14.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon ricon14 = new ImageIcon("calender/calenderIcon_14.jpg");
		ImageIcon sricon14 = new ImageIcon(ricon14.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JButton iconButton14 = new JButton(sicon14);
		iconButton14.setRolloverIcon(sricon14);
		iconButton14.setBorderPainted(false); iconButton14.setFocusPainted(false); iconButton14.setContentAreaFilled(false); 
		iconButton14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				new Chat(1);
			}
		});
		iconButton14.setSize(40,40);
		iconButton14.setLocation(60,291);
		add(iconButton14);
		
		ImageIcon icon15 = new ImageIcon("calender/OcalenderIcon_15.jpg");
		ImageIcon sicon15 = new ImageIcon(icon15.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon ricon15 = new ImageIcon("calender/calenderIcon_15.jpg");
		ImageIcon sricon15 = new ImageIcon(ricon15.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JButton iconButton15 = new JButton(sicon15);
		iconButton15.setRolloverIcon(sricon15);
		iconButton15.setBorderPainted(false); iconButton15.setFocusPainted(false); iconButton15.setContentAreaFilled(false); 
		iconButton15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				chknum = 1;
				if(DBConnect()==10) {
					new Chat(2);
				}else if(DBConnect()==11) {
					dialog = new WarningDialog();
					dialog.setLocationRelativeTo(win);
					dialog.setVisible(true);
				}
			}
		});
		iconButton15.setSize(40,40);
		iconButton15.setLocation(120,291);
		add(iconButton15);
		
		ImageIcon icon16 = new ImageIcon("calender/OcalenderIcon_16.jpg");
		ImageIcon sicon16 = new ImageIcon(icon16.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon ricon16 = new ImageIcon("calender/calenderIcon_16.jpg");
		ImageIcon sricon16 = new ImageIcon(ricon16.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JButton iconButton16 = new JButton(sicon16);
		iconButton16.setRolloverIcon(sricon16);
		iconButton16.setBorderPainted(false); iconButton16.setFocusPainted(false); iconButton16.setContentAreaFilled(false); 
		iconButton16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				chknum = 2;
				if(DBConnect()==10) {
					new Chat(3);
				}else if(DBConnect()==11) {
					dialog = new WarningDialog();
					dialog.setLocationRelativeTo(win);
					dialog.setVisible(true);
				}
			}
		});
		iconButton16.setSize(40,40);
		iconButton16.setLocation(178,291);
		add(iconButton16);
		
		ImageIcon icon17 = new ImageIcon("calender/OcalenderIcon_17.jpg");
		ImageIcon sicon17 = new ImageIcon(icon17.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon ricon17 = new ImageIcon("calender/calenderIcon_17.jpg");
		ImageIcon sricon17 = new ImageIcon(ricon17.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JButton iconButton17 = new JButton(sicon17);
		iconButton17.setRolloverIcon(sricon17);
		iconButton17.setBorderPainted(false); iconButton17.setFocusPainted(false); iconButton17.setContentAreaFilled(false); 
		iconButton17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				chknum = 3;
				if(DBConnect()==10) {
					new Chat(4);
				}else if(DBConnect()==11) {
					dialog = new WarningDialog();
					dialog.setLocationRelativeTo(win);
					dialog.setVisible(true);
				}
			}
		});
		iconButton17.setSize(40,40);
		iconButton17.setLocation(245,291);
		add(iconButton17);
		
		ImageIcon icon18 = new ImageIcon("calender/OcalenderIcon_18.jpg");
		ImageIcon sicon18 = new ImageIcon(icon18.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon ricon18 = new ImageIcon("calender/calenderIcon_18.jpg");
		ImageIcon sricon18 = new ImageIcon(ricon18.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JButton iconButton18 = new JButton(sicon18);
		iconButton18.setRolloverIcon(sricon18);
		iconButton18.setBorderPainted(false); iconButton18.setFocusPainted(false); iconButton18.setContentAreaFilled(false); 
		iconButton18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				chknum = 4;
				if(DBConnect()==10) {
					new Chat(5);
				}else if(DBConnect()==11) {
					dialog = new WarningDialog();
					dialog.setLocationRelativeTo(win);
					dialog.setVisible(true);
				}
			}
		});
		iconButton18.setSize(40,40);
		iconButton18.setLocation(307,291);
		add(iconButton18);

		ImageIcon icon19 = new ImageIcon("calender/OcalenderIcon_19.jpg");
		ImageIcon sicon19 = new ImageIcon(icon19.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon ricon19 = new ImageIcon("calender/calenderIcon_19.jpg");
		ImageIcon sricon19 = new ImageIcon(ricon19.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		JButton iconButton19 = new JButton(sicon19);
		iconButton19.setRolloverIcon(sricon19);
		iconButton19.setBorderPainted(false); iconButton19.setFocusPainted(false); iconButton19.setContentAreaFilled(false); 
		iconButton19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				chknum = 5;
				if(DBConnect()==10) {
					new Chat(6);
				}else if(DBConnect()==11) {
					dialog = new WarningDialog();
					dialog.setLocationRelativeTo(win);
					dialog.setVisible(true);
				}
			}
		});
		iconButton19.setSize(40,40);
		iconButton19.setLocation(365,291);
		add(iconButton19);
		
	}
	
	public int  DBConnect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int returnnum = 0;
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance(); //메모리를 올리는 작업, jdbc할 떄 무조건 있어야 함
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/heeda","root","mirim2"); //서버 주소 
			System.out.println("DB연결 완료");
			String sql = "Select * from ending where number=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,chknum);
			ResultSet srs = pstmt.executeQuery();
			while(srs.next()) {
				System.out.print(srs.getInt("number")+"   ");
				System.out.print(srs.getString("ment")+"   ");
				System.out.print(srs.getInt("tfchk"));
				System.out.println();
				if(srs.getInt("number")==chknum && srs.getInt("tfchk")==10) {
					System.out.println("엔딩이 존재합니다 10return ");
					returnnum = 10;
				}else {
					System.out.println("이전 단계를 클리어 해주세요 11return ");
					returnnum = 11;
				}
			}
		}catch(SQLException ex) {
			System.out.println("SQLException : "+ex);
		}catch(Exception ex) {
			System.out.println("Exception : "+ex);
		}finally {
			if(conn != null)
				try { conn.close();
				}catch(SQLException sqle) {}
		}	
		return returnnum;
	}//DBConnect
	
	//image background
	ImageIcon icon = new ImageIcon("images/Calender.jpg");
	Image img = icon.getImage(); // 이미지 객체
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
}