
import javax.swing.JFrame;

public class TitlePanel extends JFrame {
	public StartPanel StartPanel = null;
	public JPanel1 MainPanel = null;							//핸드폰 바탕화면 
	public GameIntroducePanel GameIntroducePanel = null;		//게임 소개 
	public Developer Developer = null;							//개발자소개
	public Calender Calender = null;							//여대생톡 누르면 나오는 캘린더 화면
	public EndingAlbum EndingAlbum = null;						//엔딩 모음집
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
		
		win.setTitle("여대생으로 살아남기");
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
