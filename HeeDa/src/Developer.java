import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Developer extends JPanel implements ActionListener {
	private TitlePanel win;
	private BufferedImage pic;
    private JButton daButton, heeButton;
    private JPanel imgPanel;
    
	public Developer (TitlePanel win) {        
		setLayout(null);
		this.win=win;
		
		ImageIcon backIcon = new ImageIcon("images/back2.png");
		ImageIcon sbackIcon = new ImageIcon(backIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		JButton backButton=new JButton(sbackIcon);
		backButton.setBorderPainted(false); backButton.setFocusPainted(false); backButton.setContentAreaFilled(false); 
		backButton.setPreferredSize(new Dimension(50, 50));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				win.change("MainPanel");
			}
		});
		backButton.setSize(50,50);
		backButton.setLocation(380,0);
		add(backButton);
		
		imgPanel = new ChangeImagePanel();
        try {
            pic = ImageIO.read(new File("C:\\Java\\mcw_1.png"));
        } catch (IOException e) {
            System.out.println("이미지 없음!");
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        
        ImageIcon Icon1 = new ImageIcon("images/DeveloperIcon1.jpg");
		ImageIcon sIcon1 = new ImageIcon(Icon1.getImage().getScaledInstance(225, 50, Image.SCALE_DEFAULT));
		panel.add(daButton = new JButton(sIcon1));
		daButton.setBorderPainted(false); daButton.setFocusPainted(false); daButton.setContentAreaFilled(false); 
		daButton.setPreferredSize(new Dimension(225, 50));
		System.out.println("버튼1");
		daButton.addActionListener(this);
		daButton.setSize(190,50);
		daButton.setLocation(0,0);
		add(daButton);
           
        ImageIcon Icon2 = new ImageIcon("images/DeveloperIcon2.jpg");
		ImageIcon sIcon2 = new ImageIcon(Icon2.getImage().getScaledInstance(225, 50, Image.SCALE_DEFAULT));
//		JButton btn2=new JButton(sIcon2);
		panel.add(heeButton = new JButton(sIcon2));
		heeButton.setBorderPainted(false); heeButton.setFocusPainted(false); heeButton.setContentAreaFilled(false); 
		heeButton.setPreferredSize(new Dimension(225, 50));
		heeButton.addActionListener(this);
        System.out.println("버튼2");
        heeButton.setSize(190,50);
        heeButton.setLocation(190,0);
		add(heeButton);
        
		//아무 버튼 누르지 않았을 때
		try {
			pic = ImageIO.read(new File("images/Developer1.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        imgPanel.setSize(450,550);
        imgPanel.setLocation(0,50);
		add(imgPanel);
        
        setSize(450, 650);
        setVisible(true);
        
	}//생성자
	
	class ChangeImagePanel extends JPanel {
        public ChangeImagePanel() {
        }
       
        @Override
        public void paint(Graphics g) {
            g.drawImage(pic, 0, 0, null);
        }
       
        @Override
        public Dimension getPreferredSize() {
            if (pic == null) {
                return new Dimension(200, 300);
            } else {
                return new Dimension(pic.getWidth(), pic.getHeight());
            }
        }
    }//ChangeImagePanel
   
    public void actionPerformed(ActionEvent e) {
        String imgFile = "";
        System.out.println("actioonPerformed 실행");
        if (e.getSource() == daButton) {
        	System.out.println("다연이 누름");
            imgFile = "Developer1.jpg";
        } else {
            imgFile = "Developer2_2.jpg";
            System.out.println("희나 누름");
        }
       System.out.println("try");
        try {
            pic = ImageIO.read(new File("images/"+ imgFile));
            System.out.println("이미지 출력 성공");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            System.out.println("이미지 없음!");
        }
       
        imgPanel.repaint();
    }//actionPerformed
}
