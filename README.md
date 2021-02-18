# Lotto
```jsx
	
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
