package clockpkg;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SuppressWarnings("serial")
class AnalogClock extends Canvas implements Runnable {

	//바늘시계 절대위치 상수
	public static final int centerx = 170;
	public static final int centery = 170;

	//바늘시계 생성자 
	public AnalogClock() {
		setSize(300, 300);
		Thread th = new Thread(this);
		th.start();
	}

	//원과 바늘 그려주는 부분
	public void paint(Graphics g) {
		drawPlate(g);
		drawNeedle(g);

	}

	//바늘시계 원 생성
	public void drawPlate(Graphics g) {

		for (int i = 0; i < 60; i++) {

			double rad = ((6.0 * i) / 360.0) * 2 * Math.PI;

			Point tmp = rotate(new Point(0, -150), rad);

			tmp.x += centerx;
			tmp.y += centery;

			if (i % 5 == 0) {
				g.setColor(Color.YELLOW);
				g.fillRect(tmp.x - 3, tmp.y - 3, 6, 6);// hour
			} else {
				g.setColor(Color.white);
				g.fillOval(tmp.x - 2, tmp.y - 2, 4, 4);// minute
			}
		}

	}

	//바늘시계 바늘 생성
	public void drawNeedle(Graphics g) {

		Calendar cal = new GregorianCalendar();

		int hh, mm, ss;

		hh = cal.get(Calendar.HOUR);
		mm = cal.get(Calendar.MINUTE);
		ss = cal.get(Calendar.SECOND);

		//초 단위 바늘 그리기
		double rad = ((6.0 * ss) / 360.0) * (2 * Math.PI);
		double radmin = ((6.0 * mm) / 360.0) * (2 * Math.PI);
		double radhour = ((30.0 * hh) / 360.0) * (2 * Math.PI);

		Point tmpsec = rotate(new Point(0, -130), rad);

		tmpsec.x += centerx;
		tmpsec.y += centery;

		//분 단위 바늘 그리기
		Point tmpmin = rotate(new Point(0, -95), radmin);
		tmpmin.x += centerx;
		tmpmin.y += centery;

		Point tmpmin1 = rotate(new Point(-2, -95), radmin);

		tmpmin1.x += centerx;
		tmpmin1.y += centery;

		Point tmpmin2 = rotate(new Point(2, -95), radmin);

		tmpmin2.x += centerx;
		tmpmin2.y += centery;

		int[] xmin = { centerx, tmpmin1.x, tmpmin.x, tmpmin2.x };
		int[] ymin = { centery, tmpmin1.y, tmpmin.y, tmpmin2.y };

		g.fillPolygon(xmin, ymin, 4);
		
		//시간 단위 바늘 그리기
		Point tmphour = rotate(new Point(0, -70), radhour);

		tmphour.x += centerx;
		tmphour.y += centery;

		Point tmphour1 = rotate(new Point(-4, -45), radhour);

		tmphour1.x += centerx;
		tmphour1.y += centery;

		Point tmphour2 = rotate(new Point(4, -45), radhour);

		tmphour2.x += centerx;
		tmphour2.y += centery;

		int[] xhour = { centerx, tmphour1.x, tmphour.x, tmphour2.x };
		int[] yhour = { centery, tmphour1.y, tmphour.y, tmphour2.y };

		
		g.fillPolygon(xhour, yhour, 3);

		{
			g.setColor(Color.orange);
			g.drawLine(centerx, centery, tmpsec.x, tmpsec.y);
		}

		
		

	}

	public Point rotate(Point p, double angle) {

		Point res = new Point();

		res.x = (int) (p.x * Math.cos(angle) - p.y * Math.sin(angle));

		res.y = (int) (p.x * Math.sin(angle) + p.y * Math.cos(angle));

		return res;

	}

	public void update(Graphics g) {

		Image bimg = createImage(this.getWidth(), this.getHeight());
		Graphics bg = bimg.getGraphics();
		drawPlate(bg);
		drawNeedle(bg);
		g.drawImage(bimg, 0, 0, this);

	}

	public void run() {

		while (true)

			repaint();

	}

}
