package com.neuedu.planewar.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neuedu.planewar.constant.Constant;

/**
 * �̳�Frame��,��Ϊ���������ĸ���
 * @author ��up
 */
public class PlaneUtil extends Frame {

	private static final long serialVersionUID = 2482264199980238767L;

	public PlaneUtil() {}
	
	/**
	 * �������,�ɻ�
	 */
	public void lauchFrame() {
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);// ��ʾ�жϽ���
			}
		});
		this.setResizable(false);
		new MyThread().start();
	}

	/**
	 * ����߳��ڲ���������ػ����߳�
	 * @author ��up
	 */
	class MyThread extends Thread {
		@Override
		public void run() {
			while (true) {
				repaint();// ��Frame���ظ�����paint()����
				// ����ÿ�����Ե�cpu��ͬ����������Ϊ�ô����ʾ��Ч����ͬ
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ���ͼƬ��˸�����⣬��˫���巽�������˸����
	Image backImg = null;

	// ��дupdate()�������ڴ��ڵ�������һ�������ͼƬ
	@Override
	public void update(Graphics g) {
		if (backImg == null) {
			// �������ͼƬ�����ڣ�����һ���ʹ���һ����С��ͼƬ
			backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		// ��ȡ������ͼƬ�Ļ���
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(c);
		// ��������ͼƬ��paint()������ÿ50msˢ��һ��
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}
}
