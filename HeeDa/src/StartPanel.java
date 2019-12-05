import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	private TitlePanel win;

	public StartPanel(TitlePanel win) {
		setLayout(null);
		this.win=win;
		
		ImageIcon startIcon = new ImageIcon("images/appleloge.png");
		ImageIcon sstartIcon = new ImageIcon(startIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
		JButton startButton=new JButton(sstartIcon);
		startButton.setBorderPainted(false); startButton.setFocusPainted(false); startButton.setContentAreaFilled(false); 
		startButton.setPreferredSize(new Dimension(70, 70));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				win.change("MainPanel");
			}
		});
		startButton.setSize(70,70);
		startButton.setLocation(185,260);
		add(startButton);
		
		
	}//StartPanel
	//image background
		ImageIcon icon = new ImageIcon("images/base2.jpg");
		Image img = icon.getImage(); // ¿ÃπÃ¡ˆ ∞¥√º
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
}
