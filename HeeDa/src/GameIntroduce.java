import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


	
	class GameIntroducePanel extends JPanel {
		private TitlePanel win;
		public GameIntroducePanel (TitlePanel win) {
			setLayout(null);
			this.win=win;
			
			ImageIcon backIcon = new ImageIcon("images/back.png");
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
			backButton.setLocation(360,10);
			add(backButton);

		}
		private ImageIcon icon = new ImageIcon("images/Gameinfo.jpg");
		private Image img = icon.getImage(); // ¿ÃπÃ¡ˆ ∞¥√º
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}
