package com.neuedu.planewar.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.neuedu.planewar.constant.Constant;

/**
 * 继承Frame类,作为所有作画的父类
 * @author 皇up
 */
public class PlaneUtil extends Frame {

	private static final long serialVersionUID = 2482264199980238767L;

	public PlaneUtil() {}
	
	/**
	 * 加载面板,飞机
	 */
	public void lauchFrame() {
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);// 表示中断进程
			}
		});
		this.setResizable(false);
		new MyThread().start();
	}

	/**
	 * 添加线程内部类来完成重画的线程
	 * @author 皇up
	 */
	class MyThread extends Thread {
		@Override
		public void run() {
			while (true) {
				repaint();// 在Frame中重复调用paint()方法
				// 由于每个电脑的cpu不同，所以这里为让大家显示的效果相同
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
	Image backImg = null;

	// 重写update()方法，在窗口的里层添加一个虚拟的图片
	@Override
	public void update(Graphics g) {
		if (backImg == null) {
			// 如果虚拟图片不存在，创建一个和窗口一样大小的图片
			backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		}
		// 获取到虚拟图片的画笔
		Graphics backg = backImg.getGraphics();
		Color c = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(c);
		// 调用虚拟图片的paint()方法，每50ms刷新一次
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}
}
