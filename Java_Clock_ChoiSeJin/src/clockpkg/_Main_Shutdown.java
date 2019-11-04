/*
	201511033 최세진 객체지향프로그래밍설계 시계 만들기 프로젝트 Source
	IDE : Eclipse Oxygen.3a Release (4.7.3a)
	Warning : 폰트통v2.0에서 "a전자시계" 폰트를 꼭 설치 후 컴파일할 것!
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

	public static void main(String[] args) {	//Main 및 Shutdown 구현

		ClockLabel dateLable = new ClockLabel("date");	//date 객체 생성
		ClockLabel timeLable24 = new ClockLabel("time24");	//24시간 객체 생성
		ClockLabel timeLable12 = new ClockLabel("time12");	//12시간 객체 생성

		//프레임 생성 및 기본 세팅
		JFrame.setDefaultLookAndFeelDecorated(true);	
		JFrame f = new JFrame("Choi's Digital Clock");
		f.setSize(650, 500);
		f.setResizable(false);		//크기 변경 방지
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//다른 기능 실행후 종료 가능하도록 비활성화
		f.getContentPane().setLayout(null);			

		//패널 추가
		f.getContentPane().add(dateLable);
		f.getContentPane().add(timeLable12);
		f.getContentPane().add(timeLable24);
		
		//위치 및 배경 설정
		dateLable.setBounds(79, 179, 160, 30);
		timeLable12.setBounds(0, 10, 600, 100);
		timeLable24.setBounds(191, 104, 246, 50);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.DARK_GRAY);

		//즉시 종료 버튼 설정.
		JButton nowShutdown = new JButton("Shutdown now");
		nowShutdown.setBackground(Color.DARK_GRAY);
		nowShutdown.setForeground(Color.RED);
		nowShutdown.setFont(new Font("a전자시계", Font.PLAIN, 12));
		nowShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();	//actionPerformed 내에 shutDown을 생성함으로서
								//각 버튼마다 다른 종료시간을 가지게 구현
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			private void shutDown() throws InterruptedException {
				Runtime runtime = Runtime.getRuntime();
				try {
					//윈도우의 cmd 커맨드 창을 가진 Process 객체 생성
					Process process = runtime.exec("C:\\WINDOWS\\system32\\cmd.exe");
					//outPut시킬 명령어를 저장할 객체 생성
					OutputStream os = process.getOutputStream();
					// 정확히는 버튼을 누른 뒤 5초 후 종료(실수로 눌렀을 때 취소할 시간 부여)
					os.write("shutdown -s -f -t 5 \n\r".getBytes());
					os.close();
					process.waitFor();
					//이하 사용되는 예약종료 구문은 os.write 부분만 다르다

				} catch (IOException e) {

					e.printStackTrace();

				}

			}
		});
		nowShutdown.setBounds(22, 297, 145, 22);
		f.getContentPane().add(nowShutdown);
		
		//5분 뒤 종료 버튼 설정.
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
		min_5.setFont(new Font("a전자시계", Font.PLAIN, 11));
		min_5.setForeground(new Color(255, 153, 0));
		min_5.setBounds(22, 331, 67, 23);
		f.getContentPane().add(min_5);

		JLabel lblNewLabel = new JLabel("Shutdown");
		lblNewLabel.setFont(new Font("a전자시계", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 99, 71));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 273, 140, 23);
		f.getContentPane().add(lblNewLabel);

		JLabel lblReservation = new JLabel("Reservation");
		lblReservation.setFont(new Font("a전자시계", Font.PLAIN, 16));
		lblReservation.setForeground(new Color(255, 99, 71));
		lblReservation.setBounds(43, 257, 120, 15);
		f.getContentPane().add(lblReservation);
		
		//10분 뒤 종료 버튼 설정.
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
		min_10.setFont(new Font("a전자시계", Font.PLAIN, 11));
		min_10.setBounds(101, 331, 67, 23);
		f.getContentPane().add(min_10);
		
		//30분 뒤 종료 버튼 설정.
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
		min_30.setFont(new Font("a전자시계", Font.PLAIN, 11));
		min_30.setBounds(22, 364, 67, 23);
		f.getContentPane().add(min_30);
		
		//1시간 뒤 종료 버튼 설정.
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
		hour_1.setFont(new Font("a전자시계", Font.PLAIN, 11));
		hour_1.setBounds(103, 364, 64, 23);
		f.getContentPane().add(hour_1);
		
		//2시간 뒤 종료 버튼 설정.
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
		hour_2.setFont(new Font("a전자시계", Font.PLAIN, 11));
		hour_2.setBounds(22, 395, 67, 23);
		f.getContentPane().add(hour_2);

		//3시간 뒤 종료 버튼 설정.
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
		hour_3.setFont(new Font("a전자시계", Font.PLAIN, 11));
		hour_3.setBounds(101, 395, 67, 23);
		f.getContentPane().add(hour_3);
		
		JButton cancelShutdown = new JButton("Cancel Shutdown");
		cancelShutdown.setBackground(Color.DARK_GRAY);
		cancelShutdown.setForeground(new Color(240, 230, 140));
		cancelShutdown.setFont(new Font("a전자시계", Font.PLAIN, 11));
		cancelShutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shutDown();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//종료 취소 버튼 설정.
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

		//아날로그 시계 구현
		clockpkg.AnalogClock clock = new clockpkg.AnalogClock();
		clock.setEnabled(true);
		clock.setBackground(Color.DARK_GRAY);
		clock.setBounds(299, 135, 384, 361);
		f.getContentPane().add(clock);
		cancelShutdown.setBounds(22, 428, 145, 23);
		f.getContentPane().add(cancelShutdown);
		
		
		//stopwatch 버튼
		JButton btnStopwatch = new JButton("StopWatch");
		btnStopwatch.setBackground(Color.DARK_GRAY);
		btnStopwatch.setForeground(new Color(153, 204, 0));
		btnStopwatch.setFont(new Font("a전자시계", Font.PLAIN, 11));
		btnStopwatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StopWatch st = new StopWatch();	//버튼을 누르면 StopWatch 객체 생성
				st.visibleFrame();		//객체 생성 후 화면 출력을 별도로 설정
			}
		});
		btnStopwatch.setBounds(179, 361, 120, 23);
		f.getContentPane().add(btnStopwatch);

		//timer 버튼
		JButton btnTimer = new JButton("Timer");
		btnTimer.setBackground(Color.DARK_GRAY);
		btnTimer.setForeground(new Color(51, 204, 153));
		btnTimer.setFont(new Font("a전자시계", Font.PLAIN, 12));
		btnTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MinuteTimer();		//버튼을 누르면  MinuteTimer 클래스 실행
			}
		});
		btnTimer.setBounds(180, 394, 119, 23);
		f.getContentPane().add(btnTimer);
		
		//memocalender 버튼
		JButton btnCalender = new JButton("MemoCalender");
		btnCalender.setBackground(Color.DARK_GRAY);
		btnCalender.setForeground(new Color(176, 196, 222));
		btnCalender.setFont(new Font("a전자시계", Font.PLAIN, 11));
		btnCalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MemoCalendar();		//버튼을 누르면 StopWatch 클래스 실행
			}
		});
		btnCalender.setBounds(179, 425, 120, 27);
		f.getContentPane().add(btnCalender);

		JLabel lblWatch = new JLabel("Watch");
		lblWatch.setFont(new Font("a전자시계", Font.PLAIN, 15));
		lblWatch.setForeground(new Color(224, 255, 255));
		lblWatch.setBounds(201, 314, 67, 15);
		f.getContentPane().add(lblWatch);

		JLabel lblNewLabel_1 = new JLabel("Function");
		lblNewLabel_1.setFont(new Font("a전자시계", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(224, 255, 255));
		lblNewLabel_1.setBounds(201, 333, 81, 15);
		f.getContentPane().add(lblNewLabel_1);

		f.setVisible(true);
	}
}

//디지털 시계 클래스
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
			simple = new SimpleDateFormat(" YYYY / MM / dd / EEEE");	//년,월,일,요일 순 출력
			setFont(new Font("a전자시계", Font.PLAIN, 14));
			break;
		case "time12":
			simple = new SimpleDateFormat("a hh:mm:ss");	//오전오후 있는 12시간제 시계
			setFont(new Font("a전자시계", Font.PLAIN, 60));
			setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case "time24":
			simple = new SimpleDateFormat("HH:mm:ss");		//24시간제 시계
			setFont(new Font("a전자시계", Font.PLAIN, 40));
			setHorizontalAlignment(SwingConstants.CENTER);
			break;
		
		default:
			simple = new SimpleDateFormat();
			break;
		}

		Timer t = new Timer(1000, this);	//1초 단위 타이머
		t.start();
	}

	//매초마다 갱신시켜 줌
	public void actionPerformed(ActionEvent ae) {
		Date d = new Date();
		setText(simple.format(d));
		
	}
}