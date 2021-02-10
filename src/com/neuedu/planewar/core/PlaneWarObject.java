package com.neuedu.planewar.core;

import java.awt.Image;
/**
 * ���зɻ����ϰ���ĸ���,ʵ�����ܶ����ܻ������ӿ�
 * @author ��up
 *
 */
import java.awt.Rectangle;

import com.neuedu.planewar.client.PlaneWarSystem;
public abstract class PlaneWarObject implements Drawable,Moveable{
	
	/**
	 * ��ʼλ��x����
	 */
	public int x;
	/**
	 * ��ʼλ��y���
	 */
	public int y;
	/**
	 * ����ͼƬ
	 */
	public Image img;
	/**
	 * ����
	 */
	public boolean live = true;
	/**
	 * ȷ���ɻ����ӵ�����
	 */
	public boolean belong;
	/**
	 * ������Ҫ��..(����ֵ,�ӵ�����)
	 */
	public int power;
	/**
	 * ��ͣ��
	 */
	public PlaneWarSystem pws;
	
	/**
	 * ��øö������ڵ�ͼƬ���ڵľ���
	 * ��ײ���
	 * @return �������ڵ�ͼƬ���ڵľ���
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
