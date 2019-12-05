import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Memo extends JPanel {
	private TitlePanel win;
	String string; 				//입력받은 문자열 저장
	String dateInfo;			//현재 시각 정보 저장`
	String Output;				//출력 문자열
	JTextArea OutputTextArea;	//출력 문자열 담은 공간
	Font font1 = new Font("나눔스퀘어",Font.BOLD,15);
	Font font2 = new Font("나눔스퀘어",Font.PLAIN,17);
	
	public Memo(TitlePanel win) {
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
				win.change("MainPanel");
			}
		});
				
		SimpleDateFormat date = new SimpleDateFormat ("yyyy년 MM월 dd일  HH : mm");
		String date_time = date.format (System.currentTimeMillis());	
		dateInfo = date_time;
		
		JLabel dateLabel = new JLabel(dateInfo);
		JTextArea InputTextArea = new JTextArea("메모를 입력해주세요 ",3,30);
		InputTextArea.setFont(font2);
		InputTextArea.setOpaque(true); 		//투명하게 
		
		OutputTextArea = new JTextArea(28,35);
		OutputTextArea.setEditable(false);
		OutputTextArea.setOpaque(false); 		//투명하게 

		ImageIcon writeIcon = new ImageIcon("images/writeIcon.png");
		ImageIcon swriteIcon = new ImageIcon(writeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		ImageIcon rwriteIcon = new ImageIcon("images/writeIcon2.png");
		ImageIcon srwriteIcon = new ImageIcon(rwriteIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		JButton writeButton=new JButton(swriteIcon);
		writeButton.setRolloverIcon(srwriteIcon);
		writeButton.setBorderPainted(false); writeButton.setFocusPainted(false); writeButton.setContentAreaFilled(false); 
		writeButton.setPreferredSize(new Dimension(50, 50));
		writeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				string = InputTextArea.getText();
				fileinput(string);
				InputTextArea.setText("");
				OutputTextArea.setText(OutputTextArea.getText() + string + "\n");
			}
		});
		
		System.out.println("<<파일 불러오기>>");
		fileoutput();
		System.out.println(Output);
		OutputTextArea.setFont(font2);
		OutputTextArea.append(Output);
		
		dateLabel.setBounds(110,50,300,20);
		OutputTextArea.setBounds(15,80,400,450);
		InputTextArea.setBounds(10,540,350,50);
		writeButton.setBounds(370,540,50,50);
		
		dateLabel.setFont(font1);
		dateLabel.setForeground(Color.GRAY);
		add(dateLabel);
		add(OutputTextArea);
		add(InputTextArea);
		add(writeButton);
		
	}//memo
	
	public void fileinput(String string) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("D:/Fileio/memo.txt",true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(bw,true);
		pw.write(string+"\n");
		pw.flush();
		pw.close();
	}//fileinput
	
	public void fileoutput() {
		try{
	        File file = new File("D:/Fileio/memo.txt");
	        FileReader fr = new FileReader(file);
	        int cur = 0;
	        while((cur = fr.read()) != -1){
	        	Output += (char)cur;
	            System.out.print((char)cur);
	            
	        }
	        fr.close();
	        }catch (FileNotFoundException e) {
	            e.getStackTrace();
	        }catch(IOException e){
	            e.getStackTrace();
	        }
	}//fileoutput
	
	//image background
	ImageIcon icon = new ImageIcon("images/base_memo.jpg");
	Image img = icon.getImage(); // 이미지 객체
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
		
}
