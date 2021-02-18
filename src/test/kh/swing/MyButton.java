package test.kh.swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class MyButton extends JButton{
	private Color txtColor = Color.black;
	private Color bgColor = Color.white;
	public MyButton(String txt) {
		super(txt);
		setBorderPainted(false);  // �׵θ�������
		setOpaque(false);   // �޹����� ��Ÿ��. ����100%
	}
	
	public void setTxtColor(Color txtColor) {
		this.txtColor = txtColor;
	}
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	@Override
	public void paint(Graphics grps){
		grps.setColor(bgColor);   // setColor ������ �̾ ������ �κп� ������ ����.
		grps.fillRoundRect(0, 0, getWidth(), getHeight(), 100, 100);   // 100 ��� �ٸ� ����
		
		grps.setColor(txtColor);   // ���⼭ setColor �ٽ� �����ϸ�.. �̴��� ���� ������ �κп� ������ ����.
		grps.drawString(getText(), (int)getSize().getWidth()/2-7, (int)getSize().getWidth()/2+5);
	}
}

























