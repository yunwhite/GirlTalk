
import javax.swing.JFrame;

public class TitlePanel extends JFrame {
	public StartPanel StartPanel = null;
	public JPanel1 MainPanel = null;							//�ڵ��� ����ȭ�� 
	public GameIntroducePanel GameIntroducePanel = null;		//���� �Ұ� 
	public Developer Developer = null;							//�����ڼҰ�
	public Calender Calender = null;							//������� ������ ������ Ķ���� ȭ��
	public EndingAlbum EndingAlbum = null;						//���� ������
	public Memo Memo = null;
	
	public void change(String panelName) {
		if(panelName.contentEquals("MainPanel")) {
			getContentPane().removeAll();
			getContentPane().add(MainPanel);
			revalidate();
			repaint();
		}else if(panelName.contentEquals("GameIntroducePanel")) {
			getContentPane().removeAll();
			getContentPane().add(GameIntroducePanel);
			revalidate();
			repaint();
		}else if(panelName.contentEquals("Developer")) {
			getContentPane().removeAll();
			getContentPane().add(Developer);
			revalidate();
			repaint();
		}else if(panelName.contentEquals("Calender")) {
			getContentPane().removeAll();
			getContentPane().add(Calender);
			revalidate();
			repaint();
		}else if(panelName.contentEquals("EndingAlbum")) {
			getContentPane().removeAll();
			getContentPane().add(EndingAlbum);
			revalidate();
			repaint();
		}else if(panelName.contentEquals("Memo")) {
			getContentPane().removeAll();
			getContentPane().add(Memo);
			revalidate();
			repaint();
		}else if(panelName.contentEquals("StartPanel")) {
			getContentPane().removeAll();
			getContentPane().add(StartPanel);
			revalidate();
			repaint();
		}
	}
	public static void main(String[] args) {
		
		TitlePanel win = new TitlePanel();
	    win.setLocation(700, 200);
		
		win.setTitle("��������� ��Ƴ���");
		win.StartPanel = new StartPanel(win);
		win.MainPanel = new JPanel1(win);
		win.GameIntroducePanel = new GameIntroducePanel(win);
		win.Developer = new Developer(win);
		win.Calender = new Calender(win);
		win.EndingAlbum = new EndingAlbum(win);
		win.Memo = new Memo(win);
		
		win.add(win.StartPanel);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(450, 650);
		win.setVisible(true);
	}
}
