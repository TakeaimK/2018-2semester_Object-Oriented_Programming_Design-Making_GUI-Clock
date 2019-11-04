package clockpkg;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class StopWatch extends JFrame {
	// ���� ��ü
	private static StopWatch frame = new StopWatch();

	private JPanel rootPanel; // ���� �г�
	private JLabel timeText; // �ð� ǥ�� ��
	private JLabel start, pause, stop; // ���� ��ư��

	private TimeThread timeTh; // �ð� ������
	private long time = 0l, preTime = 0l; // �ð� ����� ���� ������

	private Color commonColor1 = Color.green; // Ŭ���� �� ���ϴ� ����
	private Color commonColor2 = Color.orange; // Ŭ���� �� ���ϴ� ����
	private Color commonColor3 = Color.red; // Ŭ���� �� ���ϴ� ����

	public StopWatch() {
		// ������ ����
		setSize(400, 300);
		getContentPane().setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(false);
		consistComponent();
		setTitle("Choi's StopWatch");
		setLocationRelativeTo(null);
	}

	//�Լ��� ������ ����
	public void visibleFrame() {
		frame.setVisible(true);
	}

	private void consistComponent() {
		// ��Ʈ �г� ����, ��� ����
		rootPanel = new JPanel(new BorderLayout());
		rootPanel.setBackground(Color.black);

		// ���� ��ư���� �ִ� �г� ����
		Panel southPanel = createSouthPanel();
		rootPanel.add(southPanel, BorderLayout.SOUTH);

		// �ð��� ǥ���ϴ� ���̺��� �ִ� �г� ����
		Panel centerPanel = createCenterPanel();
		rootPanel.add(centerPanel, BorderLayout.CENTER);

		// ��Ʈ �г� ����
		setContentPane(rootPanel);
	}

	// ���� �� �ð� ǥ�� �г�
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
		title.setFont(new Font("a���ڽð�", Font.PLAIN, 30));
		centerPanel.add(title, BorderLayout.NORTH);

		timeText = new JLabel(toTime(time));
		timeText.setHorizontalAlignment(SwingConstants.CENTER);
		timeText.setForeground(Color.white);
		timeText.setFont(new Font("a���ڽð�", Font.BOLD, 50));
		centerPanel.add(timeText, BorderLayout.CENTER);
		constraints.gridx = 0;
		constraints.gridy = 0;

		return centerPanel;
	}

	// ��ư ���� �г� ����
	private Panel createSouthPanel() {
		// ���� ��ư�� ����
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

		start.setFont(new Font("a���ڽð�", Font.BOLD, 25));
		stop.setFont(new Font("a���ڽð�", Font.BOLD, 25));
		pause.setFont(new Font("a���ڽð�", Font.BOLD, 25));

		Panel btnPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		btnPanel.add(start);
		btnPanel.add(pause);
		btnPanel.add(stop);

		return btnPanel;
	}

	// �����ġ ���� ��ư ���
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

	// �����ġ �Ͻ����� ��ư ���
	private void pause() {
		if (timeTh == null)
			return;
		if (timeTh.isAlive())
			timeTh.interrupt();
	}

	// �����ġ �ʱ�ȭ ��ư ���
	private void stop() {
		if (timeTh.isAlive()) {
			timeTh.interrupt();
		}
		time = 0l;
		timeText.setText(toTime(time));
	}

	// ������ �۵��� Ŭ����
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

	// ���콺 Ŭ�� ���� ó����
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

	// ��,��,�� ����
	private String toTime(long time) {
		int m = (int) (time / 1000.0 / 60.0);
		int s = (int) (time % (1000.0 * 60) / 1000.0);
		int ms = (int) (time % 1000 / 10.0);

		return String.format("%02d : %02d : %02d", m, s, ms);
	}
}