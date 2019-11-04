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

    JLabel jltime;		//�ð� ǥ�ú� �۾�
    JLabel jl;		//�ð� ���� �κ� �۾� 
    JComboBox<Integer> selectMinute;		//�۵��ð� ������ ���� �޺��ڽ�
    JButton startButton;	//Ÿ�̸� ����
    JButton resetButton;	//Ÿ�̸� ����
    NumberFormat format;	//�ð� ����

    //�ð� ��� ���� ���� ����
    public Timer timer;		
    public long initial;
    public long ttime2;
    public String ttime;
    public long remaining;

    public MinuteTimer() {	//������

    	setResizable(false);
    	//�г� ���� �� ���� ����
        JPanel timePanel = new JPanel();
        timePanel.setBackground(Color.DARK_GRAY);
        timePanel.setForeground(Color.BLACK);
        
        //���� ��º�
        jltime = new JLabel("00:00");
        jltime.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jltime.setForeground(Color.WHITE);
        jltime.setBackground(Color.DARK_GRAY);
        jltime.setOpaque(true);
        jltime.setFont(new Font("a���ڽð�", Font.BOLD, 96));
        timePanel.add(jltime);

        JPanel jp1 = new JPanel();
        jp1.setBackground(Color.DARK_GRAY);
        jp1.setLayout(new FlowLayout());

        //���̺� ����
        jl = new JLabel("TOTAL TIME (minutes):");
        jl.setForeground(Color.WHITE);
        jl.setFont(new Font("a���ڽð�", Font.PLAIN, 12));
        jl.setBackground(new Color(255, 250, 250));
        jp1.add(jl);

        //�ð� ���� �޺��ڽ� ����
        selectMinute = new JComboBox<Integer>();
        selectMinute.setForeground(new Color(0, 0, 128));
        selectMinute.setBackground(Color.LIGHT_GRAY);
        selectMinute.setFont(new Font("a���ڽð�", Font.PLAIN, 15));
        for (int i = 0; i <60; i++) {		//1�к��� 60�б��� ����
        	if(i==0) {
        		selectMinute.addItem(Integer.valueOf(60));	//0���� �Ǹ� �ٷ� ����ǹǷ� 60������ ����
        		continue;
        	}
            selectMinute.addItem(Integer.valueOf(i));
        }
        selectMinute.setSelectedIndex(0);
        ttime = "60";
        jp1.add(selectMinute);

        //���� ��ư
        startButton = new JButton("START");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.GREEN);
        startButton.setFont(new Font("a���ڽð�", Font.PLAIN, 15));
        jp1.add(startButton);

        //���� ��ư
        resetButton = new JButton("RESET");
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.ORANGE);
        resetButton.setFont(new Font("a���ڽð�", Font.PLAIN, 15));
        jp1.add(resetButton);

        getContentPane().add(jp1, BorderLayout.NORTH);
        getContentPane().add(timePanel, BorderLayout.CENTER);

        //��ư �׼� ����
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
    //���۹�ư ���� ��� ����
    void updateDisplay() {

        Timeclass tc = new Timeclass();
        timer = new Timer(1000, tc);
        initial = System.currentTimeMillis();
        timer.start();
    }

    //����,���� ���� ��� ���� �׼�
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

    // swing timer�� Ȱ���� �ð� ��� Ŭ����
    public class Timeclass implements ActionListener {

        public void actionPerformed(ActionEvent e) {	//start ��ư�� ������ ����

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

    
    //����ڰ� �ð� ���� �� �� �ð����� �����ϴ� �Լ�
    public void itemStateChanged(ItemEvent ie) {

        ttime = (String) selectMinute.getSelectedItem().toString();
        convertTime();
    }

   
    //����ũ���� ������ ��ȯ 
    public long convertTime() {

        ttime2 = Long.parseLong(ttime);
        long converted = (ttime2 * 60000) + 1000;
        return converted;
    }
}