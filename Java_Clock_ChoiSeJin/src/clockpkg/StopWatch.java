package clockpkg;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class StopWatch extends JFrame {
	// 정적 객체
	private static StopWatch frame = new StopWatch();

	private JPanel rootPanel; // 메인 패널
	private JLabel timeText; // 시간 표시 라벨
	private JLabel start, pause, stop; // 동작 버튼들

	private TimeThread timeTh; // 시간 쓰레드
	private long time = 0l, preTime = 0l; // 시간 계산을 위한 변수들

	private Color commonColor1 = Color.green; // 클릭할 때 변하는 색상
	private Color commonColor2 = Color.orange; // 클릭할 때 변하는 색상
	private Color commonColor3 = Color.red; // 클릭할 때 변하는 색상

	public StopWatch() {
		// 프레임 설정
		setSize(400, 300);
		getContentPane().setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		consistComponent();
		setTitle("Choi's StopWatch");
		setLocationRelativeTo(null);
	}

	//함수로 프레임 실행
	public void visibleFrame() {
		frame.setVisible(true);
	}

	private void consistComponent() {
		// 루트 패널 생성, 배경 설정
		rootPanel = new JPanel(new BorderLayout());
		rootPanel.setBackground(Color.black);

		// 동작 버튼들이 있는 패널 생성
		Panel southPanel = createSouthPanel();
		rootPanel.add(southPanel, BorderLayout.SOUTH);

		// 시간을 표시하는 레이블이 있는 패널 생성
		Panel centerPanel = createCenterPanel();
		rootPanel.add(centerPanel, BorderLayout.CENTER);

		// 루트 패널 부착
		setContentPane(rootPanel);
	}

	// 간판 및 시간 표시 패널
	private Panel createCenterPanel() {
		Panel centerPanel = new Panel();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		centerPanel.setLayout(new BorderLayout(0, 0));
		JLabel title = new JLabel("StopWatch");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.white);
		title.setFont(new Font("a전자시계", Font.PLAIN, 30));
		centerPanel.add(title, BorderLayout.NORTH);

		timeText = new JLabel(toTime(time));
		timeText.setHorizontalAlignment(SwingConstants.CENTER);
		timeText.setForeground(Color.white);
		timeText.setFont(new Font("a전자시계", Font.BOLD, 50));
		centerPanel.add(timeText, BorderLayout.CENTER);
		constraints.gridx = 0;
		constraints.gridy = 0;

		return centerPanel;
	}

	// 버튼 삽입 패널 설정
	private Panel createSouthPanel() {
		// 동작 버튼들 설정
		start = new JLabel("START");
		pause = new JLabel("PAUSE");
		stop = new JLabel("STOP");

		start.setOpaque(true);
		stop.setOpaque(true);
		pause.setOpaque(true);

		start.setBackground(Color.black);
		pause.setBackground(Color.black);
		stop.setBackground(Color.black);

		start.setForeground(Color.white);
		stop.setForeground(Color.white);
		pause.setForeground(Color.white);

		start.addMouseListener(myMouse);
		pause.addMouseListener(myMouse);
		stop.addMouseListener(myMouse);

		start.setFont(new Font("a전자시계", Font.BOLD, 25));
		stop.setFont(new Font("a전자시계", Font.BOLD, 25));
		pause.setFont(new Font("a전자시계", Font.BOLD, 25));

		Panel btnPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		btnPanel.add(start);
		btnPanel.add(pause);
		btnPanel.add(stop);

		return btnPanel;
	}

	// 스톱워치 시작 버튼 기능
	private void start() {
		if (timeTh == null || !timeTh.isAlive()) {
			if (time != 0)
				preTime = System.currentTimeMillis() - time;
			else
				preTime = System.currentTimeMillis();
			timeTh = new TimeThread();
			timeTh.start();
		}
	}

	// 스톱워치 일시정지 버튼 기능
	private void pause() {
		if (timeTh == null)
			return;
		if (timeTh.isAlive())
			timeTh.interrupt();
	}

	// 스톱워치 초기화 버튼 기능
	private void stop() {
		if (timeTh.isAlive()) {
			timeTh.interrupt();
		}
		time = 0l;
		timeText.setText(toTime(time));
	}

	// 스레드 작동부 클래스
	private class TimeThread extends Thread {
		public void run() {
			try {
				while (!Thread.currentThread().isInterrupted()) {
					sleep(10);
					time = System.currentTimeMillis() - preTime;
					timeText.setText(toTime(time));
				}
			} catch (Exception e) {
			}
		}
	}

	// 마우스 클릭 관련 처리부
	private MyMouse myMouse = new MyMouse();

	private class MyMouse implements MouseListener {
		public void mouseEntered(MouseEvent e) {
			Object o = e.getSource();
			if (o.equals(start)) {
				start.setForeground(commonColor1);
			} else if (o.equals(pause)) {
				pause.setForeground(commonColor2);
			} else if (o.equals(stop)) {
				stop.setForeground(commonColor3);
			}
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
			Object o = e.getSource();
			if (o.equals(start)) {
				start.setForeground(Color.white);
			} else if (o.equals(pause)) {
				pause.setForeground(Color.white);
			} else if (o.equals(stop)) {
				stop.setForeground(Color.white);
			}
		}

		public void mousePressed(MouseEvent e) {
			Object o = e.getSource();
			if (o.equals(start)) {
				start();
			} else if (o.equals(pause)) {
				pause();
			} else if (o.equals(stop)) {
				stop();
			} else {
				e.getX();
				e.getY();
			}
		}

		public void mouseReleased(MouseEvent e) {

		}
	}

	// 시,분,초 계산부
	private String toTime(long time) {
		int m = (int) (time / 1000.0 / 60.0);
		int s = (int) (time % (1000.0 * 60) / 1000.0);
		int ms = (int) (time % 1000 / 10.0);

		return String.format("%02d : %02d : %02d", m, s, ms);
	}
}