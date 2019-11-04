package clockpkg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MinuteTimer extends JFrame implements ItemListener {

    JLabel jltime;		//시간 표시부 글씨
    JLabel jl;		//시간 선택 부분 글씨 
    JComboBox<Integer> selectMinute;		//작동시간 선택을 담을 콤보박스
    JButton startButton;	//타이머 시작
    JButton resetButton;	//타이머 리셋
    NumberFormat format;	//시간 포멧

    //시간 계산 관련 변수 선언
    public Timer timer;		
    public long initial;
    public long ttime2;
    public String ttime;
    public long remaining;

    public MinuteTimer() {	//생성자

    	setResizable(false);
    	//패널 생성 및 색상 지정
        JPanel timePanel = new JPanel();
        timePanel.setBackground(Color.DARK_GRAY);
        timePanel.setForeground(Color.BLACK);
        
        //숫자 출력부
        jltime = new JLabel("00:00");
        jltime.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jltime.setForeground(Color.WHITE);
        jltime.setBackground(Color.DARK_GRAY);
        jltime.setOpaque(true);
        jltime.setFont(new Font("a전자시계", Font.BOLD, 96));
        timePanel.add(jltime);

        JPanel jp1 = new JPanel();
        jp1.setBackground(Color.DARK_GRAY);
        jp1.setLayout(new FlowLayout());

        //레이블 셋팅
        jl = new JLabel("TOTAL TIME (minutes):");
        jl.setForeground(Color.WHITE);
        jl.setFont(new Font("a전자시계", Font.PLAIN, 12));
        jl.setBackground(new Color(255, 250, 250));
        jp1.add(jl);

        //시간 설정 콤보박스 설정
        selectMinute = new JComboBox<Integer>();
        selectMinute.setForeground(new Color(0, 0, 128));
        selectMinute.setBackground(Color.LIGHT_GRAY);
        selectMinute.setFont(new Font("a전자시계", Font.PLAIN, 15));
        for (int i = 0; i <60; i++) {		//1분부터 60분까지 설정
        	if(i==0) {
        		selectMinute.addItem(Integer.valueOf(60));	//0분이 되면 바로 종료되므로 60분으로 설정
        		continue;
        	}
            selectMinute.addItem(Integer.valueOf(i));
        }
        selectMinute.setSelectedIndex(0);
        ttime = "60";
        jp1.add(selectMinute);

        //시작 버튼
        startButton = new JButton("START");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.GREEN);
        startButton.setFont(new Font("a전자시계", Font.PLAIN, 15));
        jp1.add(startButton);

        //리셋 버튼
        resetButton = new JButton("RESET");
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.ORANGE);
        resetButton.setFont(new Font("a전자시계", Font.PLAIN, 15));
        jp1.add(resetButton);

        getContentPane().add(jp1, BorderLayout.NORTH);
        getContentPane().add(timePanel, BorderLayout.CENTER);

        //버튼 액션 설정
        Event e = new Event();
        startButton.addActionListener(e);
        resetButton.addActionListener(e);
        selectMinute.addItemListener(this);
        setBackground(Color.BLACK);
        setTitle("Choi's Timer");
        pack();
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //시작버튼 누를 경우 동작
    void updateDisplay() {

        Timeclass tc = new Timeclass();
        timer = new Timer(1000, tc);
        initial = System.currentTimeMillis();
        timer.start();
    }

    //시작,리셋 누를 경우 동작 액션
    public class Event implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String bname = e.getActionCommand();
            if (bname.equals("START")) {
                updateDisplay();
            } else {
                jltime.setText("00:00");
                timer.stop();
                remaining = convertTime();
            }
        }
    }

    // swing timer를 활용한 시간 계산 클래스
    public class Timeclass implements ActionListener {

        public void actionPerformed(ActionEvent e) {	//start 버튼이 눌리면 동작

            remaining = convertTime();
            long current = System.currentTimeMillis();
            long elapsed = current - initial;
            remaining -= elapsed;
            // initial = current;

            format = NumberFormat.getNumberInstance();
            format.setMinimumIntegerDigits(2);

            if (remaining < 0)
                remaining = (long) 0;
            int minutes = (int) (remaining / 60000);
            int seconds = (int) ((remaining % 60000) / 1000);
            jltime.setText(format.format(minutes) + ":" + format.format(seconds));

            if (remaining == 0) {
                jltime.setText("Stop!");
                timer.stop();
            }
        }
    }

    
    //사용자가 시간 선택 시 그 시간으로 설정하는 함수
    public void itemStateChanged(ItemEvent ie) {

        ttime = (String) selectMinute.getSelectedItem().toString();
        convertTime();
    }

   
    //마이크로초 단위로 변환 
    public long convertTime() {

        ttime2 = Long.parseLong(ttime);
        long converted = (ttime2 * 60000) + 1000;
        return converted;
    }
}