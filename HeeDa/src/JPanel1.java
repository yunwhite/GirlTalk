import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JPanel1 extends JPanel{
	private JButton GameStartButton;
	private TitlePanel win;
	private Clip clip;
	
	static MyDialog mydialog = null;
	
	class MyDialog extends JDialog {
		public MyDialog(String title, String text) {
			setTitle(title);
			setSize(300, 150);
			JLabel message = new JLabel(text);
			message.setFont(new Font("나눔스퀘어", Font.PLAIN, 16));
			message.setHorizontalAlignment(JLabel.CENTER);
			add(message);
		}
	}

	public JPanel1 (TitlePanel win) {
		this.win=win;
		setLayout(null);

		//icon
		ImageIcon startIcon = new ImageIcon("images/logoImage.png");
		ImageIcon sstartIcon = new ImageIcon(startIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton startbutton=new JButton(sstartIcon);
		startbutton.setBorderPainted(false); startbutton.setFocusPainted(false); startbutton.setContentAreaFilled(false); 
		startbutton.setPreferredSize(new Dimension(90, 90));
		startbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				win.change("Calender");

			}
		});
		startbutton.setSize(100,100);
		startbutton.setLocation(5,500);
		add(startbutton);
		
		ImageIcon howIcon = new ImageIcon("images/howToPlay.png");
		ImageIcon showIcon = new ImageIcon(howIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton inforbutton=new JButton(showIcon);
		inforbutton.setBorderPainted(false); inforbutton.setFocusPainted(false); inforbutton.setContentAreaFilled(false);
		inforbutton.setPreferredSize(new Dimension(100, 100));
		inforbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				win.change("GameIntroducePanel");

			}
		});
		inforbutton.setSize(100,100);
		inforbutton.setLocation(111,500);
		add(inforbutton);
		
		ImageIcon intreIcon = new ImageIcon("images/developer.png");
		ImageIcon sintreIcon = new ImageIcon(intreIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton debutton=new JButton(sintreIcon);
		debutton.setBorderPainted(false); debutton.setFocusPainted(false); debutton.setContentAreaFilled(false); 
		debutton.setPreferredSize(new Dimension(100, 100));
		debutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				win.change("Developer");

			}
		});
		debutton.setSize(100,100);
		debutton.setLocation(221,500);
		add(debutton);
		
		ImageIcon quitIcon = new ImageIcon("images/powerOFF.png");
		ImageIcon squitIcon = new ImageIcon(quitIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton exitbutton=new JButton(squitIcon);
		exitbutton.setBorderPainted(false); exitbutton.setFocusPainted(false); exitbutton.setContentAreaFilled(false); 
		exitbutton.setPreferredSize(new Dimension(100, 100 ));
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					Class.forName("org.gjt.mm.mysql.Driver").newInstance();
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heeda", "root", "mirim2");
					System.out.println("DB 연결 완료");
					for (int i = 1; i <= 6; i++) {
						String sql = "update ending set tfchk = 11 where number = (?)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, i);
						pstmt.executeUpdate();
					}
				} catch (SQLException ex) {
					System.out.println("SQLException : " + ex);
				} catch (Exception ex) {
					System.out.println("Exception : " + ex);
				} finally {
					if (conn != null)
						try {
							conn.close();
						} catch (SQLException sqle) {}
					if (pstmt != null)
						try {
							pstmt.close();
						} catch (SQLException sqle) {}
				}
				System.exit(0);
			}
		});
		exitbutton.setSize(100,100);
		exitbutton.setLocation(331,500);
		add(exitbutton);		

		ImageIcon albumIcon = new ImageIcon("images/endingAlbum.png");
		ImageIcon salbumIcon = new ImageIcon(albumIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton albumButton=new JButton(salbumIcon);
		albumButton.setBorderPainted(false); albumButton.setFocusPainted(false); albumButton.setContentAreaFilled(false); 
		albumButton.setPreferredSize(new Dimension(100, 100 ));
		albumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				win.change("EndingAlbum");

			}
		});
		albumButton.setSize(100,100);
		albumButton.setLocation(5,5);
		add(albumButton);
		
		ImageIcon musicIcon = new ImageIcon("images/music.png");
		ImageIcon smusicIcon = new ImageIcon(musicIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton musicButton=new JButton(smusicIcon);
		musicButton.setBorderPainted(false); musicButton.setFocusPainted(false); musicButton.setContentAreaFilled(false); 
		musicButton.setPreferredSize(new Dimension(100, 100 ));
		musicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clip.start();
				mydialog = new MyDialog("알림", "음악이 재생됩니다");
				mydialog.setLocationRelativeTo(win);
				mydialog.setVisible(true);
			}
		});
		musicButton.setSize(100,100);
		musicButton.setLocation(111,5);
		add(musicButton);
		
		ImageIcon memoIcon = new ImageIcon("images/memo2.png");
		ImageIcon smemoIcon = new ImageIcon(memoIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton memoButton=new JButton(smemoIcon);
		memoButton.setBorderPainted(false); memoButton.setFocusPainted(false); memoButton.setContentAreaFilled(false); 
		memoButton.setPreferredSize(new Dimension(100, 100 ));
		memoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				win.change("Memo");

			}
		});
		memoButton.setSize(100,100);
		memoButton.setLocation(221,5);
		add(memoButton);
		
		ImageIcon appstoreIcon = new ImageIcon("images/appstore.png");
		ImageIcon sappstoreIcon = new ImageIcon(appstoreIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton appstoreButton=new JButton(sappstoreIcon);
		appstoreButton.setBorderPainted(false); appstoreButton.setFocusPainted(false); appstoreButton.setContentAreaFilled(false); 
		appstoreButton.setPreferredSize(new Dimension(100, 100 ));
		appstoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mydialog = new MyDialog("메롱", "아무것도 없지롱");
				mydialog.setLocationRelativeTo(win);
				mydialog.setVisible(true);
			}
		});
		appstoreButton.setSize(100,100);
		appstoreButton.setLocation(331,5);
		add(appstoreButton);
		
		ImageIcon movieIcon = new ImageIcon("images/movie.png");
		ImageIcon smovieIcon = new ImageIcon(movieIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton movieButton=new JButton(smovieIcon);
		movieButton.setBorderPainted(false); movieButton.setFocusPainted(false); movieButton.setContentAreaFilled(false); 
		movieButton.setPreferredSize(new Dimension(100, 100 ));
		movieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mydialog = new MyDialog("올라프 죽음", "겨울 왕국2 재밌다");
				mydialog.setLocationRelativeTo(win);
				mydialog.setVisible(true);
			}
		});
		movieButton.setSize(100,100);
		movieButton.setLocation(5,110);
		add(movieButton);
		
		ImageIcon messageIcon = new ImageIcon("images/message.png");
		ImageIcon smessageIcon = new ImageIcon(messageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		JButton messageButton=new JButton(smessageIcon);
		messageButton.setBorderPainted(false); messageButton.setFocusPainted(false); messageButton.setContentAreaFilled(false); 
		messageButton.setPreferredSize(new Dimension(100, 100 ));
		messageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mydialog = new MyDialog("야", "누르지마");
				mydialog.setLocationRelativeTo(win);
				mydialog.setVisible(true);
			}
		});
		messageButton.setSize(100,100);
		messageButton.setLocation(111,110);
		add(messageButton);
		
//		GameStartButton = new JButton("여대생톡");
//		GameStartButton.setSize(70,70);
//		GameStartButton.setLocation(100,10);
//		add(GameStartButton);
//		
//		GameStartButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				JButton b = (JButton)e.getSource();
//				win.change("panel2");
//				
//			}
//		});
		
		loadAudio("audio/bgm.wav");
	
	} //JPanel1
	
	private void loadAudio(String pathName) {
		try {
			clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			File audioFile = new File(pathName); // 오디오 파일의 경로명
			AudioInputStream audioStream =
				AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioStream); // 재생할 오디오 스트림 열기
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}//loadAudio
	
		//image background
		ImageIcon icon = new ImageIcon("images/base1.jpg");
		Image img = icon.getImage(); // 이미지 객체
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}

}
