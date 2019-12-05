import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.sql.*;

public class EndingAlbum extends JPanel {
	private TitlePanel win;
	public int chknum = 0;
	static String ending;
	static EndingDialog edialog = null;
	
	static class EndingDialog extends JDialog {
		
		public EndingDialog() {
			setTitle("엔딩");
			setSize(450, 150);
			JLabel message = new JLabel(ending);
			message.setFont(new Font("나눔스퀘어", Font.PLAIN, 16));
			message.setHorizontalAlignment(JLabel.CENTER);
			add(message);
		}
	}

	public EndingAlbum(TitlePanel win) {
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
				edialog = null;
				win.change("MainPanel");
			}
		});
		
		ImageIcon Day1Icon = new ImageIcon("images/Lee.jpg");
		ImageIcon sDay1Icon = new ImageIcon(Day1Icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton Day1Button = new JButton(sDay1Icon);
		Day1Button.setSize(70,70);
		Day1Button.setLocation(25,85);
		add(Day1Button);
		Day1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chknum=1;
				System.out.println("DB연동 시도");
				DBConnect();
				edialog = new EndingDialog();
				edialog.setLocationRelativeTo(win);
				edialog.setVisible(true);
			}
		});
		
		ImageIcon Day2Icon = new ImageIcon("images/Park.jpg");
		ImageIcon sDay2Icon = new ImageIcon(Day2Icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton Day2Button = new JButton(sDay2Icon);
		Day2Button.setSize(70,70);
		Day2Button.setLocation(130,85);
		add(Day2Button);
		Day2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chknum=2;		
				System.out.println("DB연동 시도");
				DBConnect();
				edialog = new EndingDialog();
				edialog.setLocationRelativeTo(win);
				edialog.setVisible(true);
			}
		});
		
		ImageIcon Day3Icon = new ImageIcon("images/Oh.jpg");
		ImageIcon sDay3Icon = new ImageIcon(Day3Icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton Day3Button = new JButton(sDay3Icon);
		Day3Button.setSize(70,70);
		Day3Button.setLocation(240,85);
		add(Day3Button);
		Day3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chknum=3;		
				System.out.println("DB연동 시도");
				DBConnect();
				edialog = new EndingDialog();
				edialog.setLocationRelativeTo(win);
				edialog.setVisible(true);
			}
		});
		ImageIcon Day4Icon = new ImageIcon("images/Moon.jpg");
		ImageIcon sDay4Icon = new ImageIcon(Day4Icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton Day4Button = new JButton(sDay4Icon);
		Day4Button.setSize(70,70);
		Day4Button.setLocation(340,85);
		add(Day4Button);
		Day4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chknum=4;		
				System.out.println("DB연동 시도");
				DBConnect();
				edialog = new EndingDialog();
				edialog.setLocationRelativeTo(win);
				edialog.setVisible(true);
			}
		});
		
		ImageIcon Day5Icon = new ImageIcon("images/Hwang.jpg");
		ImageIcon sDay5Icon = new ImageIcon(Day5Icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton Day5Button = new JButton(sDay5Icon);
		Day5Button.setSize(70,70);
		Day5Button.setLocation(25,215);
		add(Day5Button);
		Day5Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chknum=5;		
				System.out.println("DB연동 시도");
				DBConnect();
				edialog = new EndingDialog();
				edialog.setLocationRelativeTo(win);
				edialog.setVisible(true);
			}
		});
		
		ImageIcon Day6Icon = new ImageIcon("images/Kim.jpg");
		ImageIcon sDay6Icon = new ImageIcon(Day6Icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton Day6Button = new JButton(sDay6Icon);
		Day6Button.setSize(70,70);
		Day6Button.setLocation(130,215);
		add(Day6Button);
		Day6Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chknum=6;		
				System.out.println("DB연동 시도");
				DBConnect();
				edialog = new EndingDialog();
				edialog.setLocationRelativeTo(win);
				edialog.setVisible(true);
			}
		});	
		
		
		
	}//생성자
	
	
	
	public void  DBConnect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
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
					ending = srs.getString("ment"); 
					System.out.println("ending : "+ending);
				}else {
					ending = "아직 확인하지 못한 엔딩입니다";
					System.out.println("ending 선택 : "+ending);
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
	}//DBConnect
			
	//image background
	ImageIcon icon = new ImageIcon("images/album3.jpg");
	Image img = icon.getImage(); // 이미지 객체
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
}