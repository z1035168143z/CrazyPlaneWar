package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.util.ImageUtil;

public class BossDeath extends Explode{
	
	private Boss boss;
	
	public static Image[] imgs = new Image[15];
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageUtil.imgs.get("duang_03_"+(i+1));
		}
	}
	
	public BossDeath(int x,int y,PlaneWarSystem pws,Boss boss) {
		this.x = x - imgs[0].getWidth(null)/2;
		this.y = y - imgs[0].getHeight(null)/2;
		this.pws = pws;
		this.boss = boss;
		this.live = true;
	}
	
	private int step = 0;
	
	@Override
	public void draw(Graphics g) {
		if(!live) {
			return;
		}
		if(this.step>=imgs.length*5) {
			this.live = false;
			pws.enemies.removeAll(pws.enemies);
			pws.bullets.removeAll(pws.bullets);
			pws.bosses.removeAll(pws.bosses);
			pws.planes.get(0).setScore(pws.planes.get(0).getScore()+10000 * pws.round);
			if(pws.round+1<6){
				pws.start = false;
			}
			pws.round += 1;
			return;
		}
		pws.planes.get(0).setPower(10);
		g.drawImage(imgs[step%15], x, y, null);
		step++;
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}
	
}
