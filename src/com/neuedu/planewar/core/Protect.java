package com.neuedu.planewar.core;

import java.awt.Graphics;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.util.ImageUtil;

/**
 * ±£ª§’÷¿‡
 * @author ª up
 *
 */
public class Protect extends PlaneWarObject{

	Plane p;
	
	public Protect(int x,int y,PlaneWarSystem pws,Plane p) {
		this.img = ImageUtil.imgs.get("protect");
		this.x = x + p.img.getWidth(null)/2 - img.getWidth(null)/2;
		this.y = y + p.img.getHeight(null)/2 - img.getHeight(null)/2;
		this.pws = pws;
		this.p = p;
		this.power = 5;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, p.getX() + p.img.getWidth(null)/2 - img.getWidth(null)/2,p.getY() + p.img.getHeight(null)/2 - img.getHeight(null)/2, null);
	}

	@Override
	public void move() {
		
	}

}
