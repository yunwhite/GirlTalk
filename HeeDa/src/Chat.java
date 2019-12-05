import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Chat extends JPanel {
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	static JPanel chatpanel = new JPanel();
	static JPanel nextpanel = new JPanel();
	static MyDialog dialog = null;
	static int i = 0;
	static int j = 0;
	static int cnt = 0;
	static int day = 0;
	static String chat[][] = null;
	
	static class MyDialog extends JDialog {
		public MyDialog(JFrame frame, String title, String text) {
			super(frame,title);
			setSize(300, 200);
			setLayout(new GridLayout(3, 1, 0, 1));
			String choice[] = text.split("&");
			
			for (int k = 0; k < 3; k++) {
				JButton bt = new RoundedButton2(choice[k]);
				bt.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
				add(bt);
				if (k == 0)
					bt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							j = 1;
							i = 0;
							setVisible(false);
							NextMessage(j, i);
						}
					});
				else if (k == 1)
					bt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							j = 2;
							i = 0;
							setVisible(false);
							NextMessage(j, i);
						}
					});
				else
					bt.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							j = 3;
							i = 0;
							setVisible(false);
							NextMessage(j, i);
						}
					});
			}
		}
	}
	
	static void ChatSet(int n) {
		switch (n) {
			case 1:
				chat = new String[][]{
					{"1'저기.. 다들 내일 조별과제 모임 있는 거 아시죠?",
					"3'네",
					"2'아.. 저 근데 할머니께서 아프셔서요..",
					"2'병원에 가봐야할 것 같은데ㅠㅠ",
					"1'.. 어쩔 수 없죠..",
					"1'힘내세요..!",
					"1'(가족 핑계로 빠지는 사람들 많다고 들었는데..)",
					"1'(근데 설마 할머니까지 이용할까..?)",
					"0'병문안을 간다&과제를 혼자 다 한다&이름을 뺀다"},
					{"1'앗 그럼 병원 주소좀 알려주실 수 있을까요?",
					"1'제가 문안인사 드리고 싶은데..",
					"2'앗 정말요?",
					"2'너무 감사합니다ㅠㅠ",
					"1'네? 아니에요 별말씀을",
					"2'이렇게 까지 신경써주시는데",
					"2'열심히 조별과제 참여하겠습니다!!!",
					"9'[성공] 감동받은 조원은 열심히 참여하여 A+라는 결과를 얻었다!",
					"10"},
					{"1'(뭐.. 어쩔 수 없지..)",
					"1'(그냥 내가 다 해야겠다..)",
					"9'[실패] 몇 날 며칠간 밤을 샜더니 과로사로 죽었다..",
					"11"},
					{"1'(허 무슨 할머니를 핑계 삼아?)",
					"1'(괘씸해서 못봐주겠네 그냥 이름 빼야겠다)",
					"1'그럼 선배님 이름도 뺄게요~ㅋ",
					"9'[실패] 결국 조원의 할머니께서는 돌아가셨고 나는 과에서 손절당했다..",
					"11"}		
				};
				break;
			case 2:
				chat = new String[][]{
					{"1'휴.. 힘들다ㅠㅠ",
					"1'레포트는 무사히 마쳤고 얼른 집가서 쉬어야지",
					"1'(버스에 자리가 있으려나..?)",
					"1'(앗! 저기 자리 있네!)",
					"1'(아싸 오늘은 편하게 갈 수 있겠다)",
					"2'에고공.. 허리야..",
					"2'자리가 없네..",
					"2'아이고 무거워라..",
					"0'자리를 비켜드린다&못 들은 척 하며 휴대폰을 한다&할머니께 대든다"},
					{"1'저 할머니..!",
					"1'여기 앉으세요ㅎㅎ",
					"2'아유 젊은이 고마워요 홍홍^^",
					"1'(하.. 레포트 때문에 며칠 간 잠도 제대로 못 잤는데)",
					"1'(계속 서서 가려니까 힘드네..)",
					"1'(아.. 아아..)",
					"9'[실패] 수면 부족으로 쓰러졌다..",
					"11"},
					{"1'(평소라면 할머니께 양보해드렸을텐데)",
					"1'(오늘은 내가 너무 힘드니까)",
					"1'(그냥 못 들은 척 해야겠다...)",
					"2'아유 저기 자리가 났네!",
					"2'홍홍 저기 앉으면 되겠구먼",
					"9'[성공] 뒤에 자리가 나서 거기에 앉으셨다!",
					"10"},
					{"1'저기요 할머니",
					"1'지금 저 들으라고 일부러 그러시는거죠?",
					"2'으응..?",
					"2'아녀~ 그냥 이 늙은이가 허리가 아파서 그래..",
					"1'무슨 소리에요!",
					"1'지금 저보고 비키라고",
					"1'일부러 제 앞에서 그러시는 거 잖아요!",
					"3'저기 아가씨! 무슨 말을 그렇게 해요?",
					"4'그래 학생~ 어르신께 무슨 말 버릇이야?",
					"9'[실패] 나는 버스 사람들에게 된통 혼났다..",
					"11"}
				};
				break;
			case 3:
				chat = new String[][]{
					{"1'(오늘은 월급날~!)",
					"1'(그런데 왜 입금이 안 되어있지?)",
					"1'(사장님께 연락해봐야겠다)",
					"1'사장님!",
					"1'오늘 월급날인데 알바비가 안 들어와서요",
					"2'아 그런데 말이야",
					"2'내가 이번 달에 예산이 조금 모자라서 ㅎㅎ",
					"2'다음에 주면 안 될까?",
					"1'(뭐라고? 다음에?)",
					"0'노동청에 신고한다&월급을 안 받고 그냥 퇴사한다&줄 때까지 기다린다"},
					{"1'(내가 얼마나 뼈빠지게 일했는데 알바비를 미뤄?)",
					"1'네? 뭐라고요?",
					"2'다음에 준다니까~",
					"2'좀만 기다려~",
					"1'허 정말 요즘 세상을 모르시네",
					"1'사장님 노동청에 신고할 거예요!!",
					"2'뭐라고?!",
					"2'그래 어디 한 번 해보자!",
					"9'[실패] 알고보니 사장님 친형이 검사였고 나는 재판에서 지고 말았다..",
					"11"},
					{"1'정말 치사해서 일 못 해먹겠네요",
					"1'저 오늘부터 알바 안 나갑니다",
					"2'뭐??",
					"2'다시 한번 생각해봐..",
					"1'아니요",
					"1'사장님 그러시는 게 한두 번이어야 말이죠",
					"1'안녕히 계세요",
					"9'(채팅방을 나갔습니다)",
					"9'[실패] 월급을 안 받고 퇴사한 탓에 돈이 부족해서 궁핍하게 살았다..",
					"11"},
					{"1'아 그래요?",
					"1'그럼 다음에 주세요!",
					"9'(며칠 후)",
					"2'기다려 줘서 고마워~",
					"2'내가 고마워서 돈은 더블로 넣었어~",
					"1'네?? 더블이요??",
					"1'감사합니다!!",
					"9'[성공] 사장님은 고맙다며 월급을 두 배로 주셨다!",
					"10"}
				};
				break;
			case 4:
				chat = new String[][]{
					{"1'(신환회는 언제나 시끌벅적하다)",
					"1'(피곤한데 옆에서 자꾸 어떤 선배가 술을 따라준다)",
					"2'마셔~! ",
					"3'어우 선배 그만 좀 하세요",
					"2'자~ 받아~",
					"2'마시고 더블로 가~!",
					"1'네?",
					"1'(이미 많이 마셨는데..)",
					"1'(속도 좀 안 좋고..)",
					"1'(이 잔을 받아 받지 마?)",
					"0'주는대로 다 마신다&은근슬쩍 테이블을 옮긴다&그냥 집에 간다"},
					{"1'아 네 감사합니다!",
					"1'(꼴깍 꼴깍)",
					"2'(꼴깍 꼴깍)",
					"9'(몇 분 뒤)",
					"3'선배!! 정신 좀 차려보세요!",
					"2'으..ㅇ..ㅓ...",
					"2'나 잠쉬뫄...ㄴ...",
					"1'(뭐 별거 아니네 ㅋ)",
					"9'[성공] 알고보니 선배의 주량은 맥주 한 캔이었다!",
					"10"},
					{"1'(에이씨.. 잘못 걸렸네 그냥 테이블 옮겨야겠다)",
					"1'하하 저 화장실 좀..",
					"9'(다른 테이블)",
					"3'처음 보는 얼굴?",
					"3'신입생인가 봐???",
					"1'네..!",
					"1'19학번입니다!",
					"4'어우 그래?",
					"4'그럼 그냥은 못 보내주지 마셔~!",
					"9'[실패] 옮긴 테이블에 더 꼰대가 있었다..",
					"11"},
					{"1'(에이씨.. 잘못 걸렸네 그냥 집이나 가야지)",
					"1'앗 저 집에 볼일이 생겨서..!",
					"3'잘 가~",
					"2'그래? 아쉽네 다음에 보자",
					"9'[실패] 신환회에서 어울리지 못한 나는 아싸가 되었다..",
					"11"}
				};
				break;
			case 5:
				chat = new String[][]{
					{"1'(하아.. 불금에 알바라니..)",
					"1'(집 가서 롤 하고 싶다..)",
					"2'저기 알바 주문 안 받아?",
					"1'아 넵",
					"1'주문 도와드리겠습니다",
					"1'(또 진상 손님인가..? 싫다..)",
					"2'여기 따뜻한 아이스 아메리카노 한 잔 줘",
					"1'네..?",
					"1'(따뜻한 아이스 아메리카노?)",
					"0'주문을 다시 한 번 확인한다&아이스 아메리카노를 만든다&따뜻한 아메리카노를 만든다"},
					{"1'(다시 한번 여쭤봐야겠다)",
					"1'손님 따뜻한 아이스 아메리카노는 판매하지 않습니다~",
					"2'뭐여? 내가 언제 그거 달라고 했어?",
					"2'따뜻한 아메리카노 달라고!!",
					"1'방금 전에는 손님이..",
					"2'어디서 건방지게 말대꾸야??!",
					"9'[실패] 손님이 급발진하여 멘탈이 부서졌다..",
					"11"},
					{"1'(음.. 그냥 아이스로 해야겠다..!)",
					"9'(잠시 후)",
					"1'손님 여기 아이스 아메리카노 나왔습니다~",
					"2'내가 언제 아이스로 달라고 했어?",
					"2'누가 이 겨울에 차가운 걸 마셔?",
					"2'환불해줘!",
					"9'[실패] 내 알바비에서 커피값을 물어냈다..",
					"11"},
					{"1'(음.. 그냥 따뜻하게 만들어야겠다..!)",
					"9'(잠시 후)",
					"1'손님 여기 따뜻한 아메리카노 나왔습니다~",
					"2'음 스멜~",
					"2'고마워요^^",
					"2'겨울엔 역시 따뜻한 게 최고~",
					"9'[성공] 그냥 따뜻한 음료를 좋아하시는 손님이었다!",
					"10"}
				};
				break;
			case  6:
				chat = new String[][]{
					{"9'(남자친구와 음식점 데이트 중)",
					"1'(냠냠 쩝쩝)",
					"2'너 왜 그렇게 쩝쩝대고 먹어?",
					"1'뭐?",
					"1'먹으면서 소리 낼 수도 있지 왜 그래?",
					"1'너 왜 그렇게 예민해",
					"2'그냥 네가 추잡스럽게 먹는 거 같아서 그런다 왜",
					"1'뭐 추잡???",
					"2'더러워 죽겠어 진짜",
					"0'화낸다&사과한다&조신하게 먹는다"},
					{"1'너는 안 더러운 줄 알아?",
					"1'너야말로 맨날 화장실 갔다가",
					"1'손 안 씻고 그냥 나오잖아",
					"2'그 얘기가 지금 여기서 왜 나와",
					"1'너가 나보고 더럽다며!",
					"1'추잡하다며!!",
					"2'그래 너 더럽고 추잡해!!!",
					"3'저 손님 여기서 이러시면 안되세요;;",
					"9'[실패] 결국 우리는 식당에서 쫓겨났다..",
					"11"},
					{"1'(내가 참아야지..)",
					"1'미안.. 앞으로는 조신하게 먹을게 ",
					"2'맨날 미안하다는 소리 ㅋ",
					"2'이젠 질린다",
					"2'우리 헤어져",
					"3'왜? 잘 먹어서 보기 좋은데",
					"3'그럼 나랑 만나",
					"9'[성공] 옆 테이블에 있던 잘생긴 남자가 도와줬다!",
					"10"},
					{"1'(그래.. 내가 참는다..)",
					"1'그래 알았어",
					"1'(조신하게 음식을 먹는다)",
					"2'넌 밥을 왜 그렇게 깨작깨작 먹어?",
					"2'하 진짜 입맛 떨어지게",
					"2'너랑은 도저히 밥 같이 못 먹겠다",
					"2'우리 헤어지자",
					"1'아니 잠깐만",
					"1'뭐라고??",
					"9'[실패] 남자친구에게 차였다..",
					"11"}			
				};
				break;
		}
	}
	
	static void reset() {
		i = 0;
		j = 0;
		cnt = 0;
		panel.removeAll();
		chatpanel.removeAll();
		nextpanel.removeAll();
	}
	
	static void NextMessage(int j, int i) {
		if (j != 0 && i == chat[j].length - 1) {
			if (chat[j][i].equals("10")) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					Class.forName("org.gjt.mm.mysql.Driver").newInstance();
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heeda", "root", "mirim2");
					System.out.println("DB 연결 완료");
					String sql = "update ending set tfchk = 10 where number = (?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, day);
					pstmt.executeUpdate();
					System.out.println("업데이트 완료");
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
			}
			reset();
			frame.dispose();
			return;
		}
		if (cnt > 0 && cnt % 12 == 0)
			chatpanel.removeAll();
		
		String st[] = chat[j][i].split("'");
		int n = Integer.parseInt(st[0]);
		JButton bt = null;
		
		switch(n) {
		case 0:
			dialog = new MyDialog(frame, "당신의 선택은?", st[1]);
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			break;
		case 1:
			bt = new RoundedButton1(st[1]);
			break;
		case 2:
			bt = new RoundedButton2(st[1]);
			break;
		case 3:
			bt = new RoundedButton3(st[1]);
			break;
		case 4:
			bt = new RoundedButton4(st[1]);
			break;
		case 5:
			bt = new RoundedButton5(st[1]);
			break;
		default:
			bt = new RoundedButton6(st[1]);
		}
		
		if (n == 1)
			chatpanel.add(bt, new LinearConstraints().setLinearSpace(LinearSpace.WRAP_END_CONTENT));
		else if (n == 9)
			chatpanel.add(bt, new LinearConstraints().setLinearSpace(LinearSpace.MATCH_PARENT));
		else if (n > 1)
			chatpanel.add(bt, new LinearConstraints().setLinearSpace(LinearSpace.WRAP_CONTENT));
		
		cnt++;
		panel.add(chatpanel, BorderLayout.CENTER);
		frame.setContentPane(panel);
	}
	
	public Chat(int n) {		
		day = n;
		
		frame.setSize(450,650);
		frame.setLocation(700, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("여고생톡");
		
		panel.setLayout(new BorderLayout(10, 10));
		chatpanel.setLayout(new LinearLayout(Orientation.VERTICAL, 15));
		nextpanel.setLayout(new BorderLayout());
		
		
		panel.setBackground(new Color(175, 196, 213));
		chatpanel.setBackground(new Color(175, 196, 213));
		nextpanel.setBackground(new Color(175, 196, 213));
		
		JLabel day = new JLabel("Day " + n);
		JButton next = new RoundedButton2("다음");
		day.setHorizontalAlignment(JLabel.CENTER);
		day.setFont(new Font("나눔스퀘어", Font.BOLD, 25));
		
		ChatSet(n);
		NextMessage(j, i);
		next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NextMessage(j, ++i);
				}
		});
		
		nextpanel.add(next, BorderLayout.EAST);
		panel.add(day, BorderLayout.NORTH);
		panel.add(nextpanel, BorderLayout.SOUTH);
		frame.setContentPane(panel);
		frame.setVisible(true);
		
	}
}
