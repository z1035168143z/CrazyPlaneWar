package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * 大招类
 * @author 皇up
 *
 */
public class Ultimate extends PlaneWarObject{
	
	
	private static Image[] imgs = new Image[6];
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageUtil.imgs.get("duang_02_"+(i+1));
		}
	}
	
	
	private Plane p;
	
	public Ultimate(int x,int y,Plane p,PlaneWarSystem pws) {
		this.img = imgs[0];
		this.x = x + p.img.getWidth(null)/2 - img.getWidth(null)/2;
		this.y = y - img.getHeight(null);
		this.pws = pws;
		this.p = p;
		this.belong = true;
	}
	
	private int step = 0;
	@Override
	public void draw(Graphics g) {
		if(step >= 6) {
			step = 0;
		}
		outOfBounds();
		g.drawImage(imgs[step], x, y, null);
		step++;
		move();
	}

	/**
	 * 子弹出界后销毁
	 */
	private void outOfBounds() {
		if(x<0 || x>Constant.GAME_WIDTH || y<0 || y>Constant.GAME_HEIGHT) {
			pws.ultimates.remove(this);
		}
	}
	
	@Override
	public void move() {
		y -= 5;
	}

	public void destory(Plane p) {
		if(this.getRectangle().intersects(p.getRectangle()) && p.belong != this.belong) {
			if(p instanceof Boss) {
				p.power = (int) Math.round(p.power * 0.7);
				pws.ultimates.remove(this);
			}else {
				pws.enemies.remove(p);
			}
		}
	}
	
	public void destorys(List<Plane> planes) {
		for (int i = 0; i < planes.size(); i++) {
			Plane p = planes.get(i);
			destory(p);
		}
	}
	
	public void destoryBullets(List<Bullet> bullets) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			destoryBullet(bullet);
		}
	}
	
	private void destoryBullet(Bullet bullet) {
		if(this.getRectangle().intersects(bullet.getRectangle()) && this.belong != bullet.isBelong()) {
			pws.bullets.remove(bullet);
		}
		
	}

	public Plane getP() {
		return p;
	}

	public void setP(Plane p) {
		this.p = p;
	}
	
	
}
