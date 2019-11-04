/*
	201511033 �ּ��� ��ü�������α׷��ּ��� �ð� ����� ������Ʈ Source
	IDE : Eclipse Oxygen.3a Release (4.7.3a)
	Warning : ��Ʈ��v2.0���� "a���ڽð�" ��Ʈ�� �� ��ġ �� �������� ��!
*/


package clockpkg;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class _Main_Shutdown {

	public static void main(String[] args) {	//Main �� Shutdown ����

		ClockLabel dateLable = new ClockLabel("date");	//date ��ü ����
		ClockLabel timeLable24 = new ClockLabel("time24");	//24�ð� ��ü ����
		ClockLabel timeLable12 = new ClockLabel("time12");	//12�ð� ��ü ����

		//������ ���� �� �⺻ ����
		JFrame.setDefaultLookAndFeelDecorated(true);	
		JFrame f = new JFrame("Choi's Digital Clock");
		f.setSize(650, 500);
		f.setResizable(false);		//ũ�� ���� ����
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�ٸ� ��� ������ ���� �����ϵ��� ��Ȱ��ȭ
		f.getContentPane().setLayout(null);			

		//�г� �߰�
		f.getContentPane().add(dateLable);
		f.getContentPane().add(timeLable12);
		f.getContentPane().add(timeLable24);
		
		//��ġ �� ��� ����
		dateLable.setBounds(79, 179, 160, 30);
		timeLable12.setBounds(0, 10, 600, 100);
		timeLable24.setBounds(191, 104, 246, 50);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.DARK_GRAY);

		//��� ���� ��ư ����.
		JButton nowShutdown = new JButton("Shutdown now");
		nowShutdown.setBackground(Color.DARK_GRAY);
		nowShutdown.setForeground(Color.RED);
		nowShutdown.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		nowShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();	//actionPerformed ���� shutDown�� ���������μ�
								//�� ��ư���� �ٸ� ����ð��� ������ ����
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					//�������� cmd Ŀ�ǵ� â�� ���� Process ��ü ����
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					//outPut��ų ��ɾ ������ ��ü ����
					OutputStream os = process.getOutputStream();
					// ��Ȯ���� ��ư�� ���� �� 5�� �� ����(�Ǽ��� ������ �� ����� �ð� �ο�)
					os.write("shutdown -s -f -t 5 \n\r".getBytes());
					os.close();
					process.waitFor();
					//���� ���Ǵ� �������� ������ os.write �κи� �ٸ���

				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});
		nowShutdown.setBounds(22, 297, 145, 22);
		f.getContentPane().add(nowShutdown);
		
		//5�� �� ���� ��ư ����.
		JButton min_5 = new JButton("5min");
		min_5.setBackground(Color.DARK_GRAY);
		min_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						shutDown();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}

			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					OutputStream os = process.getOutputStream();
					os.write("shutdown -s -f -t 300 \n\r".getBytes());
					os.close();
					process.waitFor();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		});
		min_5.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		min_5.setForeground(new Color(255, 153, 0));
		min_5.setBounds(22, 331, 67, 23);
		f.getContentPane().add(min_5);

		JLabel lblNewLabel = new JLabel("Shutdown");
		lblNewLabel.setFont(new Font("a���ڽð�", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 99, 71));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 273, 140, 23);
		f.getContentPane().add(lblNewLabel);

		JLabel lblReservation = new JLabel("Reservation");
		lblReservation.setFont(new Font("a���ڽð�", Font.PLAIN, 16));
		lblReservation.setForeground(new Color(255, 99, 71));
		lblReservation.setBounds(43, 257, 120, 15);
		f.getContentPane().add(lblReservation);
		
		//10�� �� ���� ��ư ����.
		JButton min_10 = new JButton("10min");
		min_10.setBackground(Color.DARK_GRAY);
		min_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					OutputStream os = process.getOutputStream();
					os.write("shutdown -s -f -t 600 \n\r".getBytes());
					os.close();
					process.waitFor();

				} catch (IOException e) {
					e.printStackTrace();

				}

			}
		});
		min_10.setForeground(new Color(255, 153, 102));
		min_10.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		min_10.setBounds(101, 331, 67, 23);
		f.getContentPane().add(min_10);
		
		//30�� �� ���� ��ư ����.
		JButton min_30 = new JButton("30min");
		min_30.setBackground(Color.DARK_GRAY);
		min_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					OutputStream os = process.getOutputStream();
					os.write("shutdown -s -f -t 1800 \n\r".getBytes());
					os.close();
					process.waitFor();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});
		min_30.setForeground(new Color(255, 204, 0));
		min_30.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		min_30.setBounds(22, 364, 67, 23);
		f.getContentPane().add(min_30);
		
		//1�ð� �� ���� ��ư ����.
		JButton hour_1 = new JButton("1 H");
		hour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					OutputStream os = process.getOutputStream();
					os.write("shutdown -s -f -t 3600 \n\r".getBytes());
					os.close();
					process.waitFor();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});
		hour_1.setBackground(Color.DARK_GRAY);
		hour_1.setForeground(new Color(255, 204, 153));
		hour_1.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		hour_1.setBounds(103, 364, 64, 23);
		f.getContentPane().add(hour_1);
		
		//2�ð� �� ���� ��ư ����.
		JButton hour_2 = new JButton("2 H");
		hour_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					OutputStream os = process.getOutputStream();
					os.write("shutdown -s -f -t 7200 \n\r".getBytes());
					os.close();
					process.waitFor();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});
		hour_2.setBackground(Color.DARK_GRAY);
		hour_2.setForeground(new Color(255, 204, 204));
		hour_2.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		hour_2.setBounds(22, 395, 67, 23);
		f.getContentPane().add(hour_2);

		//3�ð� �� ���� ��ư ����.
		JButton hour_3 = new JButton("3 H");
		hour_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					OutputStream os = process.getOutputStream();
					os.write("shutdown -s -f -t 10800 \n\r".getBytes());
					os.close();
					process.waitFor();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});
		hour_3.setForeground(new Color(255, 153, 204));
		hour_3.setBackground(Color.DARK_GRAY);
		hour_3.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		hour_3.setBounds(101, 395, 67, 23);
		f.getContentPane().add(hour_3);
		
		JButton cancelShutdown = new JButton("Cancel Shutdown");
		cancelShutdown.setBackground(Color.DARK_GRAY);
		cancelShutdown.setForeground(new Color(240, 230, 140));
		cancelShutdown.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		cancelShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//���� ��� ��ư ����.
			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					OutputStream os = process.getOutputStream();
					os.write("shutdown -a \n\r".getBytes());
					os.close();
					process.waitFor();

				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});

		//�Ƴ��α� �ð� ����
		clockpkg.AnalogClock clock = new clockpkg.AnalogClock();
		clock.setEnabled(true);
		clock.setBackground(Color.DARK_GRAY);
		clock.setBounds(299, 135, 384, 361);
		f.getContentPane().add(clock);
		cancelShutdown.setBounds(22, 428, 145, 23);
		f.getContentPane().add(cancelShutdown);
		
		
		//stopwatch ��ư
		JButton btnStopwatch = new JButton("StopWatch");
		btnStopwatch.setBackground(Color.DARK_GRAY);
		btnStopwatch.setForeground(new Color(153, 204, 0));
		btnStopwatch.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		btnStopwatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StopWatch st = new StopWatch();	//��ư�� ������ StopWatch ��ü ����
				st.visibleFrame();		//��ü ���� �� ȭ�� ����� ������ ����
			}
		});
		btnStopwatch.setBounds(179, 361, 120, 23);
		f.getContentPane().add(btnStopwatch);

		//timer ��ư
		JButton btnTimer = new JButton("Timer");
		btnTimer.setBackground(Color.DARK_GRAY);
		btnTimer.setForeground(new Color(51, 204, 153));
		btnTimer.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		btnTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MinuteTimer();		//��ư�� ������  MinuteTimer Ŭ���� ����
			}
		});
		btnTimer.setBounds(180, 394, 119, 23);
		f.getContentPane().add(btnTimer);
		
		//memocalender ��ư
		JButton btnCalender = new JButton("MemoCalender");
		btnCalender.setBackground(Color.DARK_GRAY);
		btnCalender.setForeground(new Color(176, 196, 222));
		btnCalender.setFont(new Font("a���ڽð�", Font.PLAIN, 11));
		btnCalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MemoCalendar();		//��ư�� ������ StopWatch Ŭ���� ����
			}
		});
		btnCalender.setBounds(179, 425, 120, 27);
		f.getContentPane().add(btnCalender);

		JLabel lblWatch = new JLabel("Watch");
		lblWatch.setFont(new Font("a���ڽð�", Font.PLAIN, 15));
		lblWatch.setForeground(new Color(224, 255, 255));
		lblWatch.setBounds(201, 314, 67, 15);
		f.getContentPane().add(lblWatch);

		JLabel lblNewLabel_1 = new JLabel("Function");
		lblNewLabel_1.setFont(new Font("a���ڽð�", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(224, 255, 255));
		lblNewLabel_1.setBounds(201, 333, 81, 15);
		f.getContentPane().add(lblNewLabel_1);

		f.setVisible(true);
	}
}

//������ �ð� Ŭ����
@SuppressWarnings("serial")
class ClockLabel extends JLabel implements ActionListener {

	String typeof;
	SimpleDateFormat simple;

	public ClockLabel(String typeof) {
		this.typeof = typeof;
		setForeground(Color.green);
		setOpaque(false);

		switch (typeof) {
		case "date":
			simple = new SimpleDateFormat(" YYYY / MM / dd / EEEE");	//��,��,��,���� �� ���
			setFont(new Font("a���ڽð�", Font.PLAIN, 14));
			break;
		case "time12":
			simple = new SimpleDateFormat("a hh:mm:ss");	//�������� �ִ� 12�ð��� �ð�
			setFont(new Font("a���ڽð�", Font.PLAIN, 60));
			setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case "time24":
			simple = new SimpleDateFormat("HH:mm:ss");		//24�ð��� �ð�
			setFont(new Font("a���ڽð�", Font.PLAIN, 40));
			setHorizontalAlignment(SwingConstants.CENTER);
			break;
		
		default:
			simple = new SimpleDateFormat();
			break;
		}

		Timer t = new Timer(1000, this);	//1�� ���� Ÿ�̸�
		t.start();
	}

	//���ʸ��� ���Ž��� ��
	public void actionPerformed(ActionEvent ae) {
		Date d = new Date();
		setText(simple.format(d));
		
	}
}