package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarSystem;

/**
 * ��Ч��
 * @author ��up
 *
 */
public class Duang extends PlaneWarObject{
	
	/**
	 * ����
	 */
	private int step = 0;
	/**
	 * ͼƬ����
	 */
	public Image[] imgs;
	
	public Duang() {
	}
	
	public Duang(int x,int y,PlaneWarSystem pws,Image[] imgs) {
		this.x = x;
		this.y = y;
		this.pws = pws;
		this.imgs = imgs;
	}
	
	@Override
	public void draw(Graphics g) {
		if(!live) {
			return;
		}
		if(this.step>=imgs.length) {
			this.step = 0;
			this.live = false;
			return;
		}
		g.drawImage(imgs[step], x, y, null);
		setStep(getStep() + 1);
	}

	@Override
	public void move() {
	}

	public PlaneWarSystem getPws() {
		return pws;
	}

	public void setPws(PlaneWarSystem pws) {
		this.pws = pws;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
}
