package com.neuedu.planewar.core;

import java.awt.Image;
/**
 * 所有飞机和障碍物的父类,实现了能动和能画出来接口
 * @author 皇up
 *
 */
import java.awt.Rectangle;

import com.neuedu.planewar.client.PlaneWarSystem;
public abstract class PlaneWarObject implements Drawable,Moveable{
	
	/**
	 * 初始位置x坐标
	 */
	public int x;
	/**
	 * 初始位置y左边
	 */
	public int y;
	/**
	 * 加载图片
	 */
	public Image img;
	/**
	 * 生命
	 */
	public boolean live = true;
	/**
	 * 确定飞机和子弹所属
	 */
	public boolean belong;
	/**
	 * 增加重要度..(生命值,子弹威力)
	 */
	public int power;
	/**
	 * 调停者
	 */
	public PlaneWarSystem pws;
	
	/**
	 * 获得该对象所在的图片所在的矩形
	 * 碰撞检测
	 * @return 对象所在的图片所在的矩形
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public Image getImg() {
		return img;
	}



	public void setImg(Image img) {
		this.img = img;
	}



	public boolean isLive() {
		return live;
	}



	public void setLive(boolean live) {
		this.live = live;
	}



	public boolean isBelong() {
		return belong;
	}



	public void setBelong(boolean belong) {
		this.belong = belong;
	}



	public int getPower() {
		return power;
	}



	public void setPower(int power) {
		this.power = power;
	}
	
	
}
