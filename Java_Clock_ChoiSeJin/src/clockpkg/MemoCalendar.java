package clockpkg;
// originally uploaded to http://blog.naver.com/azure0777

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

//�޷� ���� ���ϴ� class
class CalendarPart { 
	static final int CAL_WIDTH = 7;
	static final int CAL_HEIGHT = 6;
	int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	int calYear;
	int calMonth;
	int calDayOfMon;
	final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	int calLastDate;
	Calendar today = Calendar.getInstance();
	Calendar cal;

	public CalendarPart() {
		setToday();
	}

	public void setToday() {
		calYear = today.get(Calendar.YEAR);
		calMonth = today.get(Calendar.MONTH);
		calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
		makeCalData(today);
	}

	// 1���� ��ġ�� ������ ��¥�� ����
	private void makeCalData(Calendar cal) {
		
		int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK) + 7 - (cal.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (calMonth == 1)
			calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
		else
			calLastDate = calLastDateOfMonth[calMonth];
		// �޷� �迭 �ʱ�ȭ
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				calDates[i][j] = 0;
			}
		}
		// �޷� �迭�� �� ä���ֱ�
		for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
			if (i == 0)
				k = calStartingPos;
			else
				k = 0;
			for (int j = k; j < CAL_WIDTH; j++) {
				if (num <= calLastDate)
					calDates[i][j] = num++;
			}
		}
	}

	// �������� Ȯ���ϴ� �Լ�
	private int leapCheck(int year) { 
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return 1;
		else
			return 0;
	}

	// ����޷� ���� n�� ���ĸ� �޾� �޷� �迭�� ����� �Լ�
	public void moveMonth(int mon) { 
		calMonth += mon;
		if (calMonth > 11)	//�� ���� �̵�
			while (calMonth > 11) {
				calYear++;
				calMonth -= 12;
			}
		else if (calMonth < 0)	//�� ���� �̵�
			while (calMonth < 0) {
				calYear--;
				calMonth += 12;
			}
		cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);
		makeCalData(cal);
	}
}
//�޷�+�޸�+�ð� â ���� Ŭ����
public class MemoCalendar extends CalendarPart { 
	JFrame mainFrame;

	//�޷� �������
	JPanel calP;
	JButton goToday;
	JLabel todayOut;
	JButton previousYearB;
	JButton previousMonthB;
	JLabel yearMonthL;
	JButton nextMonthB;
	JButton nextYearB;
	ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();
	JPanel calPanel;
	JButton weekDaysName[];
	JButton dateButs[][] = new JButton[6][7];
	ListenForDateButs lForDateButs = new ListenForDateButs();
	JPanel infoPanel;

	//�޸� �������
	JPanel memoPanel;
	JLabel selectedDate;
	JTextArea memoArea;
	JScrollPane memoAreaSP;
	JPanel memoSubPanel;
	JButton saveB;
	JButton deleteB;
	JButton clearB;

	//�޼��� �г� �������
	JPanel frameBottomPanel;
	JLabel bottomInfo = new JLabel("Welcome!");
	// ���, �޼���
	final String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
	final String title = "Choi's Memo Calendar";
	final String saveBMsg1 = " Saved in \"SaveMemo\" folder.";
	final String saveBMsg2 = "Please write in memo first.";
	final String saveBMsg3 = "ERROR : File write fail";
	final String deleteBMsg1 = "Delete memo.";
	final String deleteBMsg2 = "A memo that has not been created or has already been deleted.";
	final String deleteBMsg3 = "ERROR : File remove fail";
	final String ClrButMsg1 = "Memo clear Success.";

	public MemoCalendar() { 

		mainFrame = new JFrame(title);
		mainFrame.setSize(700, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);

		//Ķ���� �»�� �г� ����
		calP = new JPanel();
		calP.setBackground(Color.DARK_GRAY);
		goToday = new JButton("Today");
		goToday.setForeground(new Color(255, 182, 193));
		goToday.setBackground(Color.DARK_GRAY);
		goToday.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		goToday.setToolTipText("Today");
		goToday.addActionListener(lForCalOpButtons);
		todayOut = new JLabel(" " + today.get(Calendar.YEAR) + " / " + (today.get(Calendar.MONTH) + 1) + " / "+ today.get(Calendar.DAY_OF_MONTH) + " ");
		todayOut.setBackground(Color.DARK_GRAY);
		todayOut.setForeground(Color.WHITE);
		todayOut.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		previousYearB = new JButton("<<");
		previousYearB.setBackground(Color.DARK_GRAY);
		previousYearB.setForeground(Color.WHITE);
		previousYearB.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		previousYearB.setToolTipText("Previous Year");
		previousYearB.addActionListener(lForCalOpButtons);
		previousMonthB = new JButton("<");
		previousMonthB.setForeground(Color.WHITE);
		previousMonthB.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		previousMonthB.setBackground(Color.DARK_GRAY);
		previousMonthB.setToolTipText("Previous Month");
		previousMonthB.addActionListener(lForCalOpButtons);
		yearMonthL = new JLabel(" " + calYear + " / " + ((calMonth + 1) < 10 ? "&nbsp;" : "") + (calMonth + 1) + " ");
		yearMonthL.setForeground(new Color(238, 232, 170));
		yearMonthL.setBackground(Color.DARK_GRAY);
		yearMonthL.setFont(new Font("a���ڽð�", Font.PLAIN, 10));
		nextMonthB = new JButton(">");
		nextMonthB.setBackground(Color.DARK_GRAY);
		nextMonthB.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		nextMonthB.setForeground(Color.WHITE);
		nextMonthB.setToolTipText("Next Month");
		nextMonthB.addActionListener(lForCalOpButtons);
		nextYearB = new JButton(">>");
		nextYearB.setBackground(Color.DARK_GRAY);
		nextYearB.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		nextYearB.setForeground(Color.WHITE);
		nextYearB.setToolTipText("Next Year");
		nextYearB.addActionListener(lForCalOpButtons);
		calP.setLayout(new GridBagLayout());
		GridBagConstraints calOpGC = new GridBagConstraints();
		calOpGC.gridx = 1;
		calOpGC.gridy = 1;
		calOpGC.gridwidth = 2;
		calOpGC.gridheight = 1;
		calOpGC.weightx = 1;
		calOpGC.weighty = 1;
		calOpGC.insets = new Insets(5, 5, 0, 0);
		calOpGC.anchor = GridBagConstraints.WEST;
		calOpGC.fill = GridBagConstraints.NONE;
		calP.add(goToday);
		calOpGC.gridwidth = 3;
		calOpGC.gridx = 2;
		calOpGC.gridy = 1;
		calP.add(todayOut);
		calOpGC.anchor = GridBagConstraints.CENTER;
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 1;
		calOpGC.gridy = 2;
		GridBagConstraints gbc_previousYearB = new GridBagConstraints();
		gbc_previousYearB.gridx = 2;
		calP.add(previousYearB, gbc_previousYearB);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 2;
		calOpGC.gridy = 2;
		calP.add(previousMonthB);
		calOpGC.gridwidth = 2;
		calOpGC.gridx = 3;
		calOpGC.gridy = 2;
		calP.add(yearMonthL);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 5;
		calOpGC.gridy = 2;
		calP.add(nextMonthB);
		calOpGC.gridwidth = 1;
		calOpGC.gridx = 6;
		calOpGC.gridy = 2;
		calP.add(nextYearB);

		//Ķ���� ���� �г� ����
		calPanel = new JPanel();
		calPanel.setBackground(Color.DARK_GRAY);
		weekDaysName = new JButton[7];
		for (int i = 0; i < CAL_WIDTH; i++) {	//��� ���� �κ�
			weekDaysName[i] = new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setBorderPainted(false);
			weekDaysName[i].setContentAreaFilled(false);
			weekDaysName[i].setForeground(Color.WHITE);
			if (i == 0)
				weekDaysName[i].setBackground(Color.RED);
			else if (i == 6)
				weekDaysName[i].setBackground(Color.BLUE);
			else
				weekDaysName[i].setBackground(Color.LIGHT_GRAY);
			weekDaysName[i].setOpaque(true);
			weekDaysName[i].setFocusPainted(false);
			weekDaysName[i].setFont(new Font("a���ڽð�", Font.PLAIN, 9));
			calPanel.add(weekDaysName[i]);
		}
		for (int i = 0; i < CAL_HEIGHT; i++) {	//Ķ���� ��¥ �κ�
			for (int j = 0; j < CAL_WIDTH; j++) {
				dateButs[i][j] = new JButton();
				dateButs[i][j].setBorderPainted(false);
				dateButs[i][j].setContentAreaFilled(false);
				dateButs[i][j].setBackground(Color.WHITE);
				dateButs[i][j].setOpaque(true);
				dateButs[i][j].addActionListener(lForDateButs);
				dateButs[i][j].setFont(new Font("a���ڽð�", Font.PLAIN, 12));
				calPanel.add(dateButs[i][j]);
			}
		}
		calPanel.setLayout(new GridLayout(0, 7, 2, 2));
		calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		showCal(); // �޷��� ǥ��

		//memo �۾� �г�
		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setBackground(Color.DARK_GRAY);
		selectedDate = new JLabel("MemoCalendar", SwingConstants.LEFT);
		selectedDate.setForeground(Color.WHITE);
		selectedDate.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		selectedDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		//�޸� ���� ����
		memoPanel = new JPanel();
		memoPanel.setBackground(Color.DARK_GRAY);
		memoPanel.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		memoArea = new JTextArea();
		memoArea.setBackground(new Color(255, 250, 205));
		memoArea.setLineWrap(true);
		memoArea.setWrapStyleWord(true);
		memoAreaSP = new JScrollPane(memoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		readMemo();

		//�޸� ��ư ���� ����
		memoSubPanel = new JPanel();
		memoSubPanel.setBackground(Color.DARK_GRAY);
		saveB = new JButton("Save");		//�޸� ����
		saveB.setBackground(Color.DARK_GRAY);
		saveB.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		saveB.setForeground(new Color(255, 228, 181));
		saveB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File f = new File("SaveMemo");	//SaveMemo ������ ������ ����
					if (!f.isDirectory())
						f.mkdir();

					//�޸� ���� �̸��� �� ���ڷ� ����
					String memo = memoArea.getText();
					if (memo.length() > 0) {
						BufferedWriter out = new BufferedWriter(
								new FileWriter("SaveMemo/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1) + (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt"));
						String str = memoArea.getText();
						out.write(str);
						out.close();
						bottomInfo.setText(calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1) + (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt" + saveBMsg1);
					} else
						bottomInfo.setText(saveBMsg2);
				} catch (IOException e) {
					bottomInfo.setText(saveBMsg3);
				}
				showCal();
			}
		});
		deleteB = new JButton("Delete");		//�޸� ����
		deleteB.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		deleteB.setForeground(new Color(250, 128, 114));
		deleteB.setBackground(Color.DARK_GRAY);
		deleteB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memoArea.setText("");
				File f = new File("SaveMemo/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1) + (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt");
				if (f.exists()) {
					f.delete();
					showCal();
					bottomInfo.setText(deleteBMsg1);
				} else
					bottomInfo.setText(deleteBMsg2);
			}
		});
		clearB = new JButton("Clear");		//�޸� ���� Ŭ����
		clearB.setBackground(Color.DARK_GRAY);
		clearB.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		clearB.setForeground(new Color(175, 238, 238));
		clearB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				memoArea.setText(null);
				bottomInfo.setText(ClrButMsg1);
			}
		});
		memoSubPanel.add(saveB);
		memoSubPanel.add(deleteB);
		memoSubPanel.add(clearB);
		memoPanel.setLayout(new BorderLayout());
		memoPanel.add(selectedDate, BorderLayout.NORTH);
		memoPanel.add(memoAreaSP, BorderLayout.CENTER);
		memoPanel.add(memoSubPanel, BorderLayout.SOUTH);

		// calP, calPanel�� frameSubPanelWest�� ��ġ
		JPanel frameSubPanelWest = new JPanel();
		frameSubPanelWest.setBackground(Color.DARK_GRAY);
		Dimension calPSize = calP.getPreferredSize();
		calPSize.height = 90;
		calP.setPreferredSize(calPSize);
		frameSubPanelWest.setLayout(new BorderLayout());
		frameSubPanelWest.add(calP, BorderLayout.NORTH);
		frameSubPanelWest.add(calPanel, BorderLayout.CENTER);

		// infoPanel, memoPanel�� frameSubPanelEast�� ��ġ
		JPanel frameSubPanelEast = new JPanel();
		Dimension infoPanelSize = infoPanel.getPreferredSize();
		infoPanelSize.height = 65;
		infoPanel.setPreferredSize(infoPanelSize);
		frameSubPanelEast.setLayout(new BorderLayout());
		frameSubPanelEast.add(infoPanel, BorderLayout.NORTH);
		frameSubPanelEast.add(memoPanel, BorderLayout.CENTER);

		Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
		frameSubPanelWestSize.width = 410;
		frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);

		//�޼��� �г� ����
		frameBottomPanel = new JPanel();
		frameBottomPanel.setBackground(Color.DARK_GRAY);
		bottomInfo.setForeground(Color.YELLOW);
		bottomInfo.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
		frameBottomPanel.add(bottomInfo);

		//������ ����
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.getContentPane().add(frameSubPanelWest, BorderLayout.WEST);
		mainFrame.getContentPane().add(frameSubPanelEast, BorderLayout.CENTER);
		mainFrame.getContentPane().add(frameBottomPanel, BorderLayout.SOUTH);
		mainFrame.setVisible(true);

		focusToday();
	}

	// ���� ��¥ �޷� ����
	private void focusToday() {
		if (today.get(Calendar.DAY_OF_WEEK) == 1)
			dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		else
			dateButs[today.get(Calendar.WEEK_OF_MONTH) - 1][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
	}

	//�޸� �о���� �Լ�
	private void readMemo() {
		try {
			File f = new File("SaveMemo/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1) + (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt");
			if (f.exists()) {
				BufferedReader in = new BufferedReader(
						new FileReader("SaveMemo/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1) + (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt"));
				String memoAreaText = new String();
				while (true) {
					String tempStr = in.readLine();
					if (tempStr == null)
						break;
					memoAreaText = memoAreaText + tempStr + System.getProperty("line.separator");
				}
				memoArea.setText(memoAreaText);
				in.close();
			} else
				memoArea.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Ķ������ ����ϴ� �Լ�
	private void showCal() {
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				String fontColor = "black";
				if (j == 0)
					fontColor = "red";
				else if (j == 6)
					fontColor = "blue";

				File f = new File("SaveMemo/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
						+ (calDates[i][j] < 10 ? "0" : "") + calDates[i][j] + ".txt");

				if (f.exists()) {
					dateButs[i][j]
							.setText("<html><b><font color=" + fontColor + ">" + calDates[i][j] + "</font></b></html>");
				} else
					dateButs[i][j].setText("<html><font color=" + fontColor + ">" + calDates[i][j] + "</font></html>");

				JLabel todayMark = new JLabel();
				dateButs[i][j].removeAll();
				if (calMonth == today.get(Calendar.MONTH) && calYear == today.get(Calendar.YEAR)
						&& calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)) {
					dateButs[i][j].add(todayMark);
					dateButs[i][j].setToolTipText("Today");
				}

				if (calDates[i][j] == 0)
					dateButs[i][j].setVisible(false);
				else
					dateButs[i][j].setVisible(true);
			}
		}
	}

	//��ư ������ Ŭ����
	private class ListenForCalOpButtons implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == goToday) {
				setToday();
				lForDateButs.actionPerformed(e);
				focusToday();
			} else if (e.getSource() == previousYearB)
				moveMonth(-12);
			else if (e.getSource() == previousMonthB)
				moveMonth(-1);
			else if (e.getSource() == nextMonthB)
				moveMonth(1);
			else if (e.getSource() == nextYearB)
				moveMonth(12);

			yearMonthL.setText(" " + calYear + " / " + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1) + " ");
			showCal();
		}
	}

	//�޷� ��¥ ��ư ������ Ŭ����
	private class ListenForDateButs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k = 0, l = 0;
			for (int i = 0; i < CAL_HEIGHT; i++) {
				for (int j = 0; j < CAL_WIDTH; j++) {
					if (e.getSource() == dateButs[i][j]) {
						k = i;
						l = j;
					}
				}
			}

			//today ��ư ����
			if (!(k == 0 && l == 0))
				calDayOfMon = calDates[k][l];

			cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);

			String dDayString = new String();
			int dDay = ((int) ((cal.getTimeInMillis() - today.getTimeInMillis()) / 1000 / 60 / 60 / 24));
			if (dDay == 0 && (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR))
					&& (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH))
					&& (cal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)))
				dDayString = "Today";
			else if (dDay >= 0)
				dDayString = "D-" + (dDay + 1);
			else if (dDay < 0)
				dDayString = "D+" + (dDay) * (-1);

			selectedDate.setText("<Html><font size=3>" + calYear + " / " + (calMonth + 1) + " / " + calDayOfMon + "&nbsp;(" + dDayString + ")</html>");

			readMemo();
		}
	}
}