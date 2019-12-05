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
				bt.setFont(new Font("����������", Font.PLAIN, 14));
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
					{"1'����.. �ٵ� ���� �������� ���� �ִ� �� �ƽ���?",
					"3'��",
					"2'��.. �� �ٵ� �ҸӴϲ��� �����ż���..",
					"2'������ �������� �� �������Ф�",
					"1'.. ��¿ �� ����..",
					"1'��������..!",
					"1'(���� �ΰ�� ������ ����� ���ٰ� ����µ�..)",
					"1'(�ٵ� ���� �ҸӴϱ��� �̿��ұ�..?)",
					"0'�������� ����&������ ȥ�� �� �Ѵ�&�̸��� ����"},
					{"1'�� �׷� ���� �ּ��� �˷��ֽ� �� �������?",
					"1'���� �����λ� �帮�� ������..",
					"2'�� ������?",
					"2'�ʹ� �����մϴ٤Ф�",
					"1'��? �ƴϿ��� ��������",
					"2'�̷��� ���� �Ű���ֽôµ�",
					"2'������ �������� �����ϰڽ��ϴ�!!!",
					"9'[����] �������� ������ ������ �����Ͽ� A+��� ����� �����!",
					"10"},
					{"1'(��.. ��¿ �� ����..)",
					"1'(�׳� ���� �� �ؾ߰ڴ�..)",
					"9'[����] �� �� ��ĥ�� ���� ������ ���λ�� �׾���..",
					"11"},
					{"1'(�� ���� �ҸӴϸ� �ΰ� ���?)",
					"1'(�����ؼ� �����ְڳ� �׳� �̸� ���߰ڴ�)",
					"1'�׷� ����� �̸��� ���Կ�~��",
					"9'[����] �ᱹ ������ �ҸӴϲ����� ���ư��̰� ���� ������ �������ߴ�..",
					"11"}		
				};
				break;
			case 2:
				chat = new String[][]{
					{"1'��.. ����٤Ф�",
					"1'����Ʈ�� ������ ���ư� �� ������ �������",
					"1'(������ �ڸ��� ��������..?)",
					"1'(��! ���� �ڸ� �ֳ�!)",
					"1'(�ƽ� ������ ���ϰ� �� �� �ְڴ�)",
					"2'�����.. �㸮��..",
					"2'�ڸ��� ����..",
					"2'���̰� ���ſ���..",
					"0'�ڸ��� ���ѵ帰��&�� ���� ô �ϸ� �޴����� �Ѵ�&�ҸӴϲ� ����"},
					{"1'�� �ҸӴ�..!",
					"1'���� �������䤾��",
					"2'���� ������ ������ ȫȫ^^",
					"1'(��.. ����Ʈ ������ ��ĥ �� �ᵵ ����� �� ��µ�)",
					"1'(��� ���� �����ϱ� �����..)",
					"1'(��.. �ƾ�..)",
					"9'[����] ���� �������� ��������..",
					"11"},
					{"1'(��Ҷ�� �ҸӴϲ� �纸�ص�����ٵ�)",
					"1'(������ ���� �ʹ� ����ϱ�)",
					"1'(�׳� �� ���� ô �ؾ߰ڴ�...)",
					"2'���� ���� �ڸ��� ����!",
					"2'ȫȫ ���� ������ �ǰڱ���",
					"9'[����] �ڿ� �ڸ��� ���� �ű⿡ �����̴�!",
					"10"},
					{"1'����� �ҸӴ�",
					"1'���� �� ������� �Ϻη� �׷��ô°���?",
					"2'����..?",
					"2'�Ƴ�~ �׳� �� �����̰� �㸮�� ���ļ� �׷�..",
					"1'���� �Ҹ�����!",
					"1'���� ������ ��Ű���",
					"1'�Ϻη� �� �տ��� �׷��ô� �� �ݾƿ�!",
					"3'���� �ư���! ���� ���� �׷��� �ؿ�?",
					"4'�׷� �л�~ ��Ų� ���� �� �����̾�?",
					"9'[����] ���� ���� ����鿡�� ���� ȥ����..",
					"11"}
				};
				break;
			case 3:
				chat = new String[][]{
					{"1'(������ ���޳�~!)",
					"1'(�׷��� �� �Ա��� �� �Ǿ�����?)",
					"1'(����Բ� �����غ��߰ڴ�)",
					"1'�����!",
					"1'���� ���޳��ε� �˹ٺ� �� ���ͼ���",
					"2'�� �׷��� ���̾�",
					"2'���� �̹� �޿� ������ ���� ���ڶ� ����",
					"2'������ �ָ� �� �ɱ�?",
					"1'(�����? ������?)",
					"0'�뵿û�� �Ű��Ѵ�&������ �� �ް� �׳� ����Ѵ�&�� ������ ��ٸ���"},
					{"1'(���� �󸶳� �������� ���ߴµ� �˹ٺ� �̷�?)",
					"1'��? ������?",
					"2'������ �شٴϱ�~",
					"2'���� ��ٷ�~",
					"1'�� ���� ���� ������ �𸣽ó�",
					"1'����� �뵿û�� �Ű��� �ſ���!!",
					"2'�����?!",
					"2'�׷� ��� �� �� �غ���!",
					"9'[����] �˰��� ����� ģ���� �˻翴�� ���� ���ǿ��� ���� ���Ҵ�..",
					"11"},
					{"1'���� ġ���ؼ� �� �� �ظ԰ڳ׿�",
					"1'�� ���ú��� �˹� �� �����ϴ�",
					"2'��??",
					"2'�ٽ� �ѹ� �����غ�..",
					"1'�ƴϿ�",
					"1'����� �׷��ô� �� �ѵ� ���̾�� ������",
					"1'�ȳ��� �輼��",
					"9'(ä�ù��� �������ϴ�)",
					"9'[����] ������ �� �ް� ����� ſ�� ���� �����ؼ� �����ϰ� ��Ҵ�..",
					"11"},
					{"1'�� �׷���?",
					"1'�׷� ������ �ּ���!",
					"9'(��ĥ ��)",
					"2'��ٷ� �༭ ����~",
					"2'���� ������ ���� ����� �־���~",
					"1'��?? �����̿�??",
					"1'�����մϴ�!!",
					"9'[����] ������� ���ٸ� ������ �� ��� �̴ּ�!",
					"10"}
				};
				break;
			case 4:
				chat = new String[][]{
					{"1'(��ȯȸ�� ������ �ò������ϴ�)",
					"1'(�ǰ��ѵ� ������ �ڲ� � ���谡 ���� �����ش�)",
					"2'����~! ",
					"3'��� ���� �׸� �� �ϼ���",
					"2'��~ �޾�~",
					"2'���ð� ����� ��~!",
					"1'��?",
					"1'(�̹� ���� ���̴µ�..)",
					"1'(�ӵ� �� �� ����..)",
					"1'(�� ���� �޾� ���� ��?)",
					"0'�ִ´�� �� ���Ŵ�&���ٽ�½ ���̺��� �ű��&�׳� ���� ����"},
					{"1'�� �� �����մϴ�!",
					"1'(�ñ� �ñ�)",
					"2'(�ñ� �ñ�)",
					"9'(�� �� ��)",
					"3'����!! ���� �� ����������!",
					"2'��..��..��...",
					"2'�� �Ὤ��...��...",
					"1'(�� ���� �ƴϳ� ��)",
					"9'[����] �˰��� ������ �ַ��� ���� �� ĵ�̾���!",
					"10"},
					{"1'(���̾�.. �߸� �ɷȳ� �׳� ���̺� �Űܾ߰ڴ�)",
					"1'���� �� ȭ��� ��..",
					"9'(�ٸ� ���̺�)",
					"3'ó�� ���� ��?",
					"3'���Ի��ΰ� ��???",
					"1'��..!",
					"1'19�й��Դϴ�!",
					"4'��� �׷�?",
					"4'�׷� �׳��� �� �������� ����~!",
					"9'[����] �ű� ���̺� �� ���밡 �־���..",
					"11"},
					{"1'(���̾�.. �߸� �ɷȳ� �׳� ���̳� ������)",
					"1'�� �� ���� ������ ���ܼ�..!",
					"3'�� ��~",
					"2'�׷�? �ƽ��� ������ ����",
					"9'[����] ��ȯȸ���� ��︮�� ���� ���� �ƽΰ� �Ǿ���..",
					"11"}
				};
				break;
			case 5:
				chat = new String[][]{
					{"1'(�Ͼ�.. �ұݿ� �˹ٶ��..)",
					"1'(�� ���� �� �ϰ� �ʹ�..)",
					"2'���� �˹� �ֹ� �� �޾�?",
					"1'�� ��",
					"1'�ֹ� ���͵帮�ڽ��ϴ�",
					"1'(�� ���� �մ��ΰ�..? �ȴ�..)",
					"2'���� ������ ���̽� �Ƹ޸�ī�� �� �� ��",
					"1'��..?",
					"1'(������ ���̽� �Ƹ޸�ī��?)",
					"0'�ֹ��� �ٽ� �� �� Ȯ���Ѵ�&���̽� �Ƹ޸�ī�븦 �����&������ �Ƹ޸�ī�븦 �����"},
					{"1'(�ٽ� �ѹ� ������߰ڴ�)",
					"1'�մ� ������ ���̽� �Ƹ޸�ī��� �Ǹ����� �ʽ��ϴ�~",
					"2'����? ���� ���� �װ� �޶�� �߾�?",
					"2'������ �Ƹ޸�ī�� �޶��!!",
					"1'��� ������ �մ���..",
					"2'��� �ǹ����� ����پ�??!",
					"9'[����] �մ��� �޹����Ͽ� ��Ż�� �μ�����..",
					"11"},
					{"1'(��.. �׳� ���̽��� �ؾ߰ڴ�..!)",
					"9'(��� ��)",
					"1'�մ� ���� ���̽� �Ƹ޸�ī�� ���Խ��ϴ�~",
					"2'���� ���� ���̽��� �޶�� �߾�?",
					"2'���� �� �ܿ￡ ������ �� ����?",
					"2'ȯ������!",
					"9'[����] �� �˹ٺ񿡼� Ŀ�ǰ��� ����´�..",
					"11"},
					{"1'(��.. �׳� �����ϰ� �����߰ڴ�..!)",
					"9'(��� ��)",
					"1'�մ� ���� ������ �Ƹ޸�ī�� ���Խ��ϴ�~",
					"2'�� ����~",
					"2'������^^",
					"2'�ܿ￣ ���� ������ �� �ְ�~",
					"9'[����] �׳� ������ ���Ḧ �����Ͻô� �մ��̾���!",
					"10"}
				};
				break;
			case  6:
				chat = new String[][]{
					{"9'(����ģ���� ������ ����Ʈ ��)",
					"1'(�ȳ� ����)",
					"2'�� �� �׷��� ������� �Ծ�?",
					"1'��?",
					"1'�����鼭 �Ҹ� �� ���� ���� �� �׷�?",
					"1'�� �� �׷��� ������",
					"2'�׳� �װ� ���⽺���� �Դ� �� ���Ƽ� �׷��� ��",
					"1'�� ����???",
					"2'������ �װھ� ��¥",
					"0'ȭ����&����Ѵ�&�����ϰ� �Դ´�"},
					{"1'�ʴ� �� ������ �� �˾�?",
					"1'�ʾ߸��� �ǳ� ȭ��� ���ٰ�",
					"1'�� �� �İ� �׳� �����ݾ�",
					"2'�� ��Ⱑ ���� ���⼭ �� ����",
					"1'�ʰ� ������ �����ٸ�!",
					"1'�����ϴٸ�!!",
					"2'�׷� �� ������ ������!!!",
					"3'�� �մ� ���⼭ �̷��ø� �ȵǼ���;;",
					"9'[����] �ᱹ �츮�� �Ĵ翡�� �Ѱܳ���..",
					"11"},
					{"1'(���� ���ƾ���..)",
					"1'�̾�.. �����δ� �����ϰ� ������ ",
					"2'�ǳ� �̾��ϴٴ� �Ҹ� ��",
					"2'���� ������",
					"2'�츮 �����",
					"3'��? �� �Ծ ���� ������",
					"3'�׷� ���� ����",
					"9'[����] �� ���̺� �ִ� �߻��� ���ڰ� �������!",
					"10"},
					{"1'(�׷�.. ���� ���´�..)",
					"1'�׷� �˾Ҿ�",
					"1'(�����ϰ� ������ �Դ´�)",
					"2'�� ���� �� �׷��� ���۱��� �Ծ�?",
					"2'�� ��¥ �Ը� ��������",
					"2'�ʶ��� ������ �� ���� �� �԰ڴ�",
					"2'�츮 �������",
					"1'�ƴ� ���",
					"1'�����??",
					"9'[����] ����ģ������ ������..",
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
					System.out.println("DB ���� �Ϸ�");
					String sql = "update ending set tfchk = 10 where number = (?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, day);
					pstmt.executeUpdate();
					System.out.println("������Ʈ �Ϸ�");
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
			dialog = new MyDialog(frame, "����� ������?", st[1]);
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
		frame.setTitle("�������");
		
		panel.setLayout(new BorderLayout(10, 10));
		chatpanel.setLayout(new LinearLayout(Orientation.VERTICAL, 15));
		nextpanel.setLayout(new BorderLayout());
		
		
		panel.setBackground(new Color(175, 196, 213));
		chatpanel.setBackground(new Color(175, 196, 213));
		nextpanel.setBackground(new Color(175, 196, 213));
		
		JLabel day = new JLabel("Day " + n);
		JButton next = new RoundedButton2("����");
		day.setHorizontalAlignment(JLabel.CENTER);
		day.setFont(new Font("����������", Font.BOLD, 25));
		
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
