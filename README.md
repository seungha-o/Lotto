# Lotto
```jsx
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
		
		
		
JsonReader jr = new JsonReader();
		JSONObject jo = jr.connectionUrlToJson(turnTxt.getText()); // json으로 긁어온건 String
		String[] right = new String[MAX_CNT]; // 로또번호 확인하기위한 변수
		int nCnt = 0; // 맞는 숫자 개수
		int bCnt = 0; // 맞는 숫자 개수(보너스 번호. 2등)
		
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
		
		
		
try {
	//https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=112
	URL url = new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo="+turn);
	HttpsURLConnection conn = null;
	HostnameVerifier hnv = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true; //return false;
		}
	};
			
			
			
			
			
			
	public class Lotto extends JFrame implements MouseListener, KeyListener {
	final int WIDTH_BTN = 50;
	final int WIDTH_GAP = 5;
	private final int MAX_CNT = 6;
	private final int MAX_CNT_W_BN = 7;
	// 로또 당첨 번호 표시 - JButton --> 라벨을 쓸 수 없기 때문(크기 위치조정 안됨). 동그라미 그려서 색칠하는 형태
	// 7개
	private MyButton[] btn = new MyButton[MAX_CNT_W_BN];

	// 로또 당첨 확인할 번호 입력 - TextField
	private JTextField[] txt = new JTextField[MAX_CNT];

	// 회차 입력 - JTextField
	private JTextField turnTxt = new JTextField();

	// 회차 확인 = JButton
	private JButton checkBtn = new JButton("확인");

	// 정보 - JLabel
	private JLabel infoLbl = new JLabel();

	// 순위 문구
	JLabel rankLabel = new JLabel("");
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
 ```
