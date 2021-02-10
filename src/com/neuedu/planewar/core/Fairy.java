package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.util.ImageUtil;

/**
 * ¾«Áé
 * @author »Êup
 *
 */
public class Fairy extends Duang{
	
	private PlaneWarSystem pws;
	private Plane p;
	/**
	 * Í¼Æ¬Êý×é
	 */
	public static Image[] imgs = new Image[22];
	/**
	 * ¾²Ì¬´úÂë¿é¼ÓÔØÍ¼Æ¬
	 */
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageUtil.imgs.get("fairy_"+(i+1));
		}
	}
	
	public Fairy(int x,int y,Plane p,PlaneWarSystem pws) {
		this.p = p;
		this.x = x + p.img.getWidth(null);
		this.y = y + imgs[0].getHeight(null)/2 -20;
		this.pws = pws;
	}
	
	private void protect() {
		if(p.getPower() == 1) {
			p.setPower(10);
			pws.fairies.remove(this);
		}
	}
	
	int step = 0;
	int space;
	@Override
	public void draw(Graphics g) {
		if(step >= 22) {
			step = 0;
		}
		g.drawImage(imgs[step], p.getX() + p.img.getWidth(null), p.getY() + imgs[0].getHeight(null)/2 -20, null);
		step++;
		protect();
	}

	@Override
	public void move() {
		
	}

	public Plane getP() {
		return p;
	}

	public void setP(Plane p) {
		this.p = p;
	}
	
}
