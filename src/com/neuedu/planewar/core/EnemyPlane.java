package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * 地方势力类,继承Plane
 * 
 * @author 皇up
 *
 */
public class EnemyPlane extends Plane {

	/**
	 * 敌机的飞行速度
	 */
	private int speed;
	/**
	 * 调停者
	 */
	private PlaneWarSystem pws;
	/**
	 * 用随机数控制发射子弹频率
	 */
	private static Random r = new Random();
	/**
	 * 关卡
	 */
	private int round;
	private boolean right = true;

	public EnemyPlane() {
	}

	/**
	 * 创建敌机的构造方法
	 * @param x			初始位置x
	 * @param y			初始位置y
	 * @param key		敌机图片
	 * @param pws		调停者
	 * @param belong	所属
	 * @param round		关卡
	 */
	public EnemyPlane(int x, int y, String key, PlaneWarSystem pws, boolean belong, int round) {
		this.x = x;
		this.y = y;
		this.img = ImageUtil.imgs.get(key);
		this.speed = Constant.ENMEY_PALNE_01_SPEED;
		this.pws = pws;
		this.belong = belong;
		this.power = round;
		this.round = round;
	}

	/**
	 * 画出敌机的方法
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawImage(img, x, y, null);
		outOfBounds();
		move();
		if (r.nextInt(1000) > 980) fire();
	}

	/**
	 * 敌军出界后销毁
	 */
	private void outOfBounds() {
		if (x < -Constant.GAME_WIDTH  || x > Constant.GAME_WIDTH + 100 || y < -Constant.GAME_HEIGHT || y > Constant.GAME_HEIGHT * 2) {
			pws.enemies.remove(this);
		}
	}

	/**
	 * 敌机开火的方法
	 */
	private void fire() {
		Bullet b = new Bullet(x, y, "enemy_bullet_0"+pws.round, Direction.DOWN, this.pws, this, belong);
		pws.bullets.add(b);
	}
	/**
	 * 渐开线,变化越快,旋转越快
	 */
	double theta = 0;

	/**
	 * 敌机的移动方法
	 */
	@Override
	public void move() {
		switch (round) {
		case 1:
			y += speed;
			break;
		case 2:
			if (this.x < 0) right = true;
			if (this.x > Constant.GAME_WIDTH - 50) right = false;
			if (right) {
				x += 1;
				y += 1;
			} else {
				x -= 1;
				y += 1;
			}
			break;
		case 3:
			if (this.x < 0) right = true;
			if (this.x > Constant.GAME_WIDTH - 50) right = false;
			if (right) {
				x += 1;
				y += 1;
			} else {
				x -= 1;
				y += 1;
			}
			
			break;
			
		case 4:
			double alfa = 15;
			double r = 5;
			int CenterX = Constant.GAME_WIDTH / 2 - 30;
			int CenterY = 100;
			x = CenterX + (int) (r * Math.cos((theta + alfa) * Math.PI / 180)
					+ (theta + alfa) * Math.PI / 180 * r * Math.sin((theta + alfa) * Math.PI / 180));
			y = CenterY - (int) (r * Math.sin((theta + alfa) * Math.PI / 180)
					- (theta + alfa) * Math.PI / 180 * r * Math.cos((theta + alfa) * Math.PI / 180));
			theta += 5;
			break;
		case 5:
			double alfa_2 = 15;
			double r_2 = 5;
			int Center_X = Constant.GAME_WIDTH / 2 - 30;
			int Center_Y = 100;
			x = Center_X + (int) (r_2 * Math.cos((theta + alfa_2) * Math.PI / 180)
					+ (theta + alfa_2) * Math.PI / 180 * r_2 * Math.sin((theta + alfa_2) * Math.PI / 180));
			y = Center_Y - (int) (r_2 * Math.sin((theta + alfa_2) * Math.PI / 180)
					- (theta + alfa_2) * Math.PI / 180 * r_2 * Math.cos((theta + alfa_2) * Math.PI / 180));
			theta += 5;
			break;

		default:
			break;
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public PlaneWarSystem getPws() {
		return pws;
	}

	public void setPws(PlaneWarSystem pws) {
		this.pws = pws;
	}

	public static Random getR() {
		return r;
	}

	public static void setR(Random r) {
		EnemyPlane.r = r;
	}

}
