# Lotto
```jsx
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
