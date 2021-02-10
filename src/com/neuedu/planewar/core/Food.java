package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * 道具类
 * @author 皇up
 *
 */
public class Food extends PlaneWarObject{
	
	/**
	 * 区分不同道具
	 */
	private int level;
	/**
	 * 随机生成一种道具
	 */
	private Random r = new Random();
	private int i = r.nextInt(5);
	/**
	 * 道具移动速度
	 */
	private int speed = 3;
	/**
	 * 道具移动方向
	 */
	private Direction dir;
	/**
	 * 图片数组
	 */
	public static Image[] imgs = new Image[5];
	/**
	 * 静态代码块加载图片
	 */
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageUtil.imgs.get("food_0"+(i+1));
		}
	}
	/**
	 * 穿入被击毁敌军
	 */
	private Plane ep;
	/**
	 * 调停者,用于移除本类对象
	 */
	private PlaneWarSystem pws;
	
	public Food() { 
	}
	public Food(int x,int y,Plane ep,PlaneWarSystem pws) {
		this.ep = ep;
		this.x = x + this.ep.img.getWidth(null)/2 - imgs[0].getWidth(null)/2;
		this.y = y + this.ep.img.getHeight(null)/2 - imgs[0].getHeight(null)/2;
		this.img = imgs[i];
		this.level = i;
		this.pws = pws;
		this.dir = r.nextInt(2) % 2 == 1 ? Direction.LEFT_DOWN : Direction.RIGHT_DOWN;
	}

	
	@Override
	public void draw(Graphics g) {
		g.drawImage(this.img, x, y, null);
		outOfBounds();
		move();
	}
	
	/**
	 * 道具出界后销毁
	 */
	private void outOfBounds() {
		if(x<0) this.dir = Direction.RIGHT_DOWN;
		if(x>Constant.GAME_WIDTH-img.getWidth(null))	this.dir = Direction.LEFT_DOWN;
		if(y>Constant.GAME_HEIGHT) {
			pws.foods.remove(this);
		}
	}
	
	@Override
	public void move() {
		switch (dir) {
		case RIGHT_DOWN:
			x += speed;
			y += speed;
			break;
		case LEFT_DOWN:
			x -= speed;
			y += speed;
			break;
		default:
			break;
		}
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}


}
