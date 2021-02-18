# Lotto

### 개요
로또 번호 확인 프로그램

### 기술스택
- FrontEnd - Java Swing
- BackEnd - Java(JDK 1.8)
- Library&API - json-simple-1.1.1.jar
- IDE - Eclipse(EE, 2020-06)
- Server - Java Application
- Cl - Github, git

### 설계의 주안점 
- Swing으로만 구성
- Window Builder를 이용해서 UI 배치
- Json을 이용해 로또번호를 읽어옴
- 입력한 숫자와 Json으로 받아온 숫자 검증
- 유효성 검사
- 숫자의 범위에 따른 배경색 변경

### 팀원
1명 

# 주요기능
1. 초기화면 
![화면 캡처 2021-02-18 125641](https://user-images.githubusercontent.com/69295153/108355693-8166eb80-722e-11eb-8926-685e1d8b7541.png)
숫자 여섯개를 각각 입력 할수 있도록 JButton과 JTextField 사용했습니다. 
JButton은 보너스 번호까지 포함하여 배열을 7개 생성하였으며, 크기와 모양을 조정하기위해 JLable이아닌 Jbutton을 사용했습니다.

Lotto.java
```jsx
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
	}	c.add(turnLabel);

 ```
2. 실행화면
![화면 캡처 2021-02-18 124359](https://user-images.githubusercontent.com/69295153/108355691-80ce5500-722e-11eb-92a2-5ef352eb21a3.png)
JsonReader.java
```jsx
URL url = new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+turn);
```
turn에 입력한 회차의 로또번호를 위 url에서 JsonObject로 받아옵니다. 
```jsx
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
```
해당회차의 번호가 Jbutton에 set되며, 숫자의 범위에 따라 배경색을 지정합니다. 
```jsx
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
``` nCnt와 bCnt로 맞은숫자와 보너스 숫자와 일치하는지 확인합니다. 
3. 그 외
![화면 캡처 2021-02-18 180622](https://user-images.githubusercontent.com/69295153/108355685-8035be80-722e-11eb-8edc-6ae7a4e16d51.png)
입력한 숫자중에 중복된 숫자가 있는지 검사합니다. 
```jsx
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
```
![화면 캡처 2021-02-18 133740](https://user-images.githubusercontent.com/69295153/108355696-81ff8200-722e-11eb-9c0d-bb73072527d6.png)
입력한 숫자가 1-45사이의 숫자인지 검사합니다. 
```jsx
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
```			
![화면 캡처 2021-02-18 134824](https://user-images.githubusercontent.com/69295153/108355698-81ff8200-722e-11eb-85de-bea8b1ef702c.png)
검색할 회차가 공백이거나 문자인 경우 출력하는 메세지입니다. 
```jsx
try { // 회차 입력 
	Integer.parseInt(turnTxt.getText()); // 문자로 입력받은 turnNum을 int형으로 반환 
	} catch (Exception e) {
		// e.printStackTrace(); 숫자가 아니면 들어감
		turnLabel.setText("찾는 회차의 숫자를 입력해주세요");
		turnTxt.setText("");
		return;
	}
	turnLabel.setText("");
```
### 보완할 점
- 해당회차의 다양한 정보 표시 (당첨금, 추첨일 등)
- UI 개선
- 랜덤 로또번호 
