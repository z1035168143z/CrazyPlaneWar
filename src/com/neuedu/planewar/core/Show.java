package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.util.ImageUtil;

public class Show extends Duang{
	
	private PlaneWarSystem pws;
	
	private static Image[] imgs = new Image[32];
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageUtil.imgs.get("duang_"+(i+1));
		}
	}
	
	
	
	public Show(int x,int y,PlaneWarSystem pws) {
		this.x = x;
		this.y = y;
		this.pws = pws;
	}
	
	private int step = 0;
	@Override
	public void draw(Graphics g) {
		if(!live) {
			return;
		}
		if(this.step>=imgs.length) {
			this.step = 0;
			this.live = false;
			pws.shows.remove(this);
			if(pws.fairies.size() == 0 ) {
				Fairy fairy = new Fairy(this.x,this.y, pws.planes.get(0),pws);
				pws.fairies.add(fairy);
			}
			return;
		}
		Plane p = pws.planes.get(0);
		g.drawImage(imgs[step], p.getX() + p.img.getWidth(null), p.getY() - 10, null);
		step++;
	}
}
