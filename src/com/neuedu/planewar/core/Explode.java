package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.util.ImageUtil;

/**
 * 爆炸特效
 * @author 皇up
 *
 */
public class Explode extends Duang{
	
	/**
	 * 图片数组
	 */
	public static Image[] imgs = new Image[15];
	/**
	 * 静态代码块加载图片
	 */
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageUtil.imgs.get("e"+(i+1));
		}
	}

	public Explode() {
	}
	
	/**
	 * 构造方法,创建对象,传入调停者
	 * @param x		x坐标
	 * @param y		y坐标
	 * @param pws	调停者
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
