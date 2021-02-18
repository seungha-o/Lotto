package test.kh.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;

// https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=
//{
//"totSellamnt":59292005000,  // 당첨금
//"returnValue":"success",   // 회차 여부
//"drwNoDate":"2005-01-22",   // 추첨일
//"firstWinamnt":1567271167,
//"drwtNo6":42,// 1번쨰 당첨번호
//"drwtNo4":33,// 1번쨰 당첨번호
//"firstPrzwnerCo":9,      // 1등 당첨자 명수
//"drwtNo5":41,// 1번쨰 당첨번호
//"bnusNo":43,// 보너스번호
//"firstAccumamnt":0,     // 1등 당첨금
//"drwNo":112,    // 회차
//"drwtNo2":29,  // 2번쨰 당첨번호
//"drwtNo3":30,   // 3번쨰 당첨번호
//"drwtNo1":26  // 1번쨰 당첨번호
//}
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import javax.swing.JPanel;
import java.awt.Font;

public class Lotto extends JFrame implements KeyListener, MouseListener {
	final int WIDTH_BTN = 50;
	final int WIDTH_GAP = 5;
	private final int MAX_CNT = 6;
	private final int MAX_CNT_W_BN = 7;
	// 로또 당첨 번호 표시 - JButton : label이 불편하여 사용함. 동그라미 그려서 색칠하는 형태
	// 6+1
	MyButton[] btn = new MyButton[MAX_CNT_W_BN];

	// 로또 당첨 확인할 번호 입력 - JTextField
	JTextField[] txt = new JTextField[MAX_CNT];

	// 회차 입력 - JTextField
	 JTextField turnTxt = new JTextField();

	// 회차확인 - JButton
	JButton checkBtn = new JButton("확인");

	// 정보 - JLabel
	JLabel infoLbl = new JLabel();

	// 순위 문구
	JLabel rankLabel = new JLabel("");

	// 회차 정보, 경고 문구 표시할 라벨
	JLabel turnLabel = new JLabel("");
		
	public Lotto() {
		super("로또확인 프로그램");
		getContentPane().setFont(new Font("휴먼엑스포", Font.PLAIN, 30));
		// 처음 화면을 구성함.
		init();
		Container c = getContentPane();
		c.setLayout(null);
		int w = 40;
		for (int i = 0; i < txt.length; i++) {
			txt[i].setBounds(w, 130, WIDTH_BTN, WIDTH_BTN);
			c.add(txt[i]);
			w += WIDTH_BTN + WIDTH_GAP;
		}
		// 이벤트 등록
		event();
		// 화면에 보여주기
		setSize(579, 403);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void init() {
		Container c = getContentPane();
		c.setLayout(null);
		int w = 40; // 초기 시작하는 x 위치
		for (int i = 0; i < btn.length; i++) {
			if (i == 6) {
				btn[i] = new MyButton("[B]");
			}else {
				btn[i] = new MyButton("0" + (i + 1));
			
			}btn[i].setBounds(w, 65, WIDTH_BTN, WIDTH_BTN);
			w += WIDTH_BTN + WIDTH_GAP;
			c.add(btn[i]);
		}
		for (int i = 0; i < txt.length; i++) {
		txt[i] = new JTextField(i + 1);
		txt[i].setBounds(30 + (WIDTH_BTN * i) + (WIDTH_GAP * i), 120, WIDTH_BTN, WIDTH_BTN);
		txt[i].setHorizontalAlignment(JTextField.CENTER); // 커서 위치
		c.add(txt[i]);
	}
		//add전에 
		turnTxt.setBounds(306, 265, 100, 40);
		c.add(turnTxt);
		checkBtn.setFont(new Font("휴먼엑스포", Font.PLAIN, 30));
		checkBtn.setBounds(416, 265, 100, 40);
		c.add(checkBtn);
		infoLbl.setFont(new Font("휴먼엑스포", Font.PLAIN, 23));
		infoLbl.setText("\uC22B\uC790 \uC5EC\uC12F\uAC1C\uC640 \uD68C\uCC28\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		infoLbl.setBounds(40, 14, 423, 40);
		infoLbl.setForeground(new Color(0, 153, 255));
		c.add(infoLbl);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		getContentPane().add(panel);
		rankLabel.setFont(new Font("한컴 고딕", Font.PLAIN, 23));
		rankLabel.setBounds(40, 215, 300, 30);
		c.add(rankLabel);
		turnLabel.setFont(new Font("한컴 고딕", Font.PLAIN, 20));
		turnLabel.setOpaque(false);
		turnLabel.setBounds(25, 265, 300, 120);
		turnLabel.setForeground(Color.black);
		c.add(turnLabel);
	}

	private void event() {
		checkBtn.addMouseListener(this); // this: 현재 동작하고 있는 이 객체
		turnTxt.addKeyListener(this); // 다음 포커스로 이동할때  tab 
	}

	private void showResult() {
		super.setTitle("결과화면보기");
		try { // 회차 입력 
			Integer.parseInt(turnTxt.getText()); // 문자로 입력받은 turnNum을 int형으로 반환 
		} catch (Exception e) {
			// e.printStackTrace(); 숫자가 아니면 들어감
			turnLabel.setText("찾는 회차의 숫자를 입력해주세요");
			turnTxt.setText("");
			return;
		}
		turnLabel.setText("");
		
		JsonReader jr = new JsonReader();
		JSONObject jo = jr.connectionUrlToJson(turnTxt.getText()); // json으로 긁어온건 String
		String[] right = new String[MAX_CNT]; // 로또번호 확인하기위한 변수
		int nCnt = 0; // 맞는 숫자 개수
		int bCnt = 0; // 맞는 숫자 개수(보너스 번호. 2등)
						
		if (jo == null) {
			infoLbl.setText("찾는 회차의 정보가 없습니다.");
			turnTxt.setText("");
			return;
		}
		if (jo.get("returnValue").equals("fail")) {
			infoLbl.setText(turnTxt.getText() + "찾는 회차의 정보가 없습니다.");
			turnTxt.setText("");
			return;
		}
		infoLbl.setText(turnTxt.getText() + "회차의 정보입니다.");
		turnTxt.setText("");
	
		for (int i = 0; i < MAX_CNT; i++) {
			// btn[i].setText((String)jo.get("drwtNo"+(i+1)));
			String strNo = String.valueOf(jo.get("drwtNo" + (i + 1)));
			// 공 색깔
			btn[i].setText(strNo);
			int a = Integer.parseInt(strNo);
			if (a > 40) {
				btn[i].setBgColor(Color.magenta);
			} else if (a > 30) {
				btn[i].setBgColor(Color.cyan);
			} else if (a > 20) {
				btn[i].setBgColor(Color.lightGray);
			} else if (a > 10) {
				btn[i].setBgColor(Color.pink);
			} else {
				btn[i].setBgColor(Color.orange);
			}
			btn[6].setBgColor(Color.green);
		}
		btn[MAX_CNT].setText(String.valueOf(jo.get("bnusNo")));
		

		for (int i = 0; i < MAX_CNT; i++) {
			if (txt[i].getText().equals("")) { // 공백이 입력되었을때
				try {
					right[i] = txt[i].getText();
					Integer.parseInt(right[i]);
				} catch (Exception e) {
					turnLabel.setText("");
					return; // return쓰면 아예 메소드를 나가버린다.
				}
			}
			if (!txt[i].getText().equals("")) { // 로또 번호에 숫자 말고 문자 입력했을 때
				try {
					right[i] = txt[i].getText();
					Integer.parseInt(right[i]);
				} catch (Exception e) {
					turnLabel.setText("숫자만 입력해주세요");
					txt[0].setText("");
					txt[1].setText("");
					txt[2].setText("");
					txt[3].setText("");
					txt[4].setText("");
					txt[5].setText("");
					return;
				}
			}
		}
		//로또 번호 범위
				if(Integer.parseInt(txt[0].getText())<1||Integer.parseInt(txt[0].getText())>45
						||Integer.parseInt(txt[1].getText())<1||Integer.parseInt(txt[1].getText())>45
						||Integer.parseInt(txt[2].getText())<1||Integer.parseInt(txt[2].getText())>45
						||Integer.parseInt(txt[3].getText())<1||Integer.parseInt(txt[3].getText())>45
						||Integer.parseInt(txt[4].getText())<1||Integer.parseInt(txt[4].getText())>45
						||Integer.parseInt(txt[5].getText())<1||Integer.parseInt(txt[5].getText())>45)
				{
					turnLabel.setText("로또 번호의 범위는 1~45 입니다.");
					return;
				}
				
				//같은 숫자를 입력했을때
				ArrayList<Integer> checkList = new ArrayList<Integer>();
				for(int i = 0; i<txt.length; i++) {
					checkList.add(Integer.parseInt(txt[i].getText()));
				}
				
				HashSet<Integer> hash = new HashSet<Integer>(checkList);
				ArrayList<Integer> checkedList = new ArrayList<Integer>(hash);
				if(checkedList.size()<6) {
					turnLabel.setText("중복 입력된 로또 번호가 있습니다.");
					return;
				}
				
				for (int i = 0; i < MAX_CNT; i++) {
					for (int j = 0; j < right.length; j++) {
						if (right[j].equals(btn[i].getText())) { // 텍스트랑 버튼이랑 비교하는거니까 텍스트의 j와 버튼의 i를 비교해야한다
							btn[i].setBgColor(Color.yellow);
							btn[i].setTxtColor(Color.blue);
							nCnt++;
						}
					}
				}
				for (int i = 0; i < MAX_CNT; i++) {
					if (right[i].equals(btn[6].getText())) {
						btn[6].setBgColor(Color.yellow);
						btn[6].setTxtColor(Color.blue);
						bCnt++;
					}
				}
				if (nCnt == 6) {
					rankLabel.setText("축하합니다 1등입니다!");
				} else if (nCnt == 5 && bCnt == 1) {
					rankLabel.setText("축하합니다 2등입니다!");
				} else if (nCnt == 5) {
					rankLabel.setText("축하합니다 3등입니다!");
				} else if (nCnt == 4) {
					rankLabel.setText("축하합니다 4등입니다!");
				} else if (nCnt == 3) {
					rankLabel.setText("축하합니다 5등입니다!");
				} else {
					rankLabel.setText("다음 기회를 노려보세요.");
				}
	}
		
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// 회차 정보 결과확인
			showResult();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		showResult();
	}
}
