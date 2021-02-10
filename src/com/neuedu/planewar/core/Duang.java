package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarSystem;

/**
 * 特效类
 * @author 皇up
 *
 */
public class Duang extends PlaneWarObject{
	
	/**
	 * 计数
	 */
	private int step = 0;
	/**
	 * 图片数组
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
