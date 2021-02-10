package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * 关卡背景图
 * @author 皇up
 *
 */
public class Background extends PlaneWarObject{
	
	/**
	 * 存储关卡的数组
	 */
	private static Image[] imgs = new Image[5];
	
	static {
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageUtil.imgs.get("bg_"+(i+1));
		}
	}
	
	/**
	 * 关卡
	 */
	private int round;
	
	public Background(int round,PlaneWarSystem pws) {
		this.round = round - 1;
		this.img = imgs[this.round];
		this.x = 0;
		this.pws = pws;
		this.y = Constant.GAME_HEIGHT - img.getHeight(null);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.img, x, y, null);
		g.drawImage(this.img, x, y - img.getHeight(null), null);
		move();
		if(new Random().nextInt(10000) > 9950) fireMissile();
	}

	@Override
	public void move() {
		this.y += 5;
		if(y >= Constant.GAME_HEIGHT) {
			this.y = Constant.GAME_HEIGHT - img.getHeight(null);
		}
	}
	
	/**
	 * 发射导弹
	 */
	private void fireMissile() {
		Bullet missile = new Bullet("missile",pws);
		pws.bullets.add(missile);
	}
}
