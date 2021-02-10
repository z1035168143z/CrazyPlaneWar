package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.util.ImageUtil;

/**
 * ��ը��Ч
 * @author ��up
 *
 */
public class Explode extends Duang{
	
	/**
	 * ͼƬ����
	 */
	public static Image[] imgs = new Image[15];
	/**
	 * ��̬��������ͼƬ
	 */
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageUtil.imgs.get("e"+(i+1));
		}
	}

	public Explode() {
	}
	
	/**
	 * ���췽��,��������,�����ͣ��
	 * @param x		x����
	 * @param y		y����
	 * @param pws	��ͣ��
	 */
	public Explode(int x,int y,PlaneWarSystem pws) {
		super(x - imgs[0].getWidth(null)/2, y - imgs[0].getHeight(null)/2, pws, imgs);
		this.pws = pws;
	}
	
	@Override
	public void draw(Graphics g) {
		if(super.getStep()>=imgs.length) {
			pws.explodes.remove(this);
		}
		super.draw(g);
	}

	@Override
	public void move() {
		
	}
	
}
