package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * boss类
 * @author 皇up
 */
public class Boss extends EnemyPlane{
	
	/**
	 * 调停者
	 */
	private PlaneWarSystem pws;
	/**
	 * 计数
	 */
	private int step = 0;
	/**
	 * 移动方向
	 */
	private boolean right = true;
	/**
	 * 移动速度
	 */
	private int speed =3;
	/**
	 * 随机数控制fire()
	 */
	private Random r = new Random();
	/**
	 * 内部类画血条对象
	 */
	private Blood bloodImg = new Blood();
	private int round;
	/**
	 * 图片数组
	 */
	public static Image[][] imgs = new Image[5][6];
	static {
		for (int i = 0; i < 6; i++) {
			imgs[0][i] = ImageUtil.imgs.get("enemy_0"+(i+1));
			imgs[1][i] = ImageUtil.imgs.get("enemy_0"+(i+1));
			imgs[2][i] = ImageUtil.imgs.get("boss02_"+(i+1));
			imgs[3][i] = ImageUtil.imgs.get("boss01_"+(i+1));
			imgs[4][i] = ImageUtil.imgs.get("enemy_0"+(i+1));
		}
	}
	
	/**
	 * boss血条图片数组
	 */
	public static Image[] bloodImgs = new Image[4];
	static {
		for (int i = 0; i < 4; i++) {
			bloodImgs[i] = ImageUtil.imgs.get("bosshp"+(i+1));
		}
	}
	
	public Boss(int round,PlaneWarSystem pws) {
		this.round = round - 1;
		this.x = Constant.GAME_WIDTH / 2 - imgs[this.round][0].getWidth(null) / 2;
		this.y = -380;
		this.belong = false;
		this.pws = pws;
		this.img = imgs[this.round][0];
		this.power = 100*round;
	}
	
	@Override
	public void draw(Graphics g) {
		if(this.y <= -150 ) {
			g.drawImage(ImageUtil.imgs.get("warning"), Constant.GAME_WIDTH/2-ImageUtil.imgs.get("warning").getWidth(null)/2,  Constant.GAME_HEIGHT/2-ImageUtil.imgs.get("warning").getHeight(null)/2, null);
			this.power = pws.round * 100;
		}else if(this.y < 100 && this.y > -150){
			g.drawImage(ImageUtil.imgs.get("bossName"+(this.round)), Constant.GAME_WIDTH/2-70,  Constant.GAME_HEIGHT/2-100, null);
			this.power = pws.round * 100;
		}else {
			g.drawImage(imgs[round][0], 7, 35, 30,30,null);
			bloodImg.draw(g);
		}
		if(this.step>=imgs.length) {
			this.step = 0;
		}
		if(r.nextInt(10)<1 && this.power > 0) fire();
		g.drawImage(imgs[round][step], x, y, null);
		step += 1;
		move();
	}

	@Override
	public void move() {
		if(this.power <= 0) {
			this.speed = 0;
		}
		if(this.y < 100) {
			this.y += speed;
		}else {
			if(right) this.x += speed;
			if(!right) this.x -= speed;
		}
		
		if(this.x < 0) right = true;
		if(this.x > Constant.GAME_WIDTH - imgs[round][0].getWidth(null)) right = false;
	}
	
	
	private void fire() {
		Bullet bullet1 = new Bullet(this.x, this.y, "boss_bullet_0"+(round+1), Direction.DOWN, pws, this, this.belong);
		Bullet bullet2 = new Bullet(this.x - 20, this.y, "boss_bullet_0"+(round+1), Direction.DOWN, pws, this, this.belong);
		Bullet bullet3 = new Bullet(this.x + 20, this.y, "boss_bullet_0"+(round+1), Direction.DOWN, pws, this, this.belong);
		bullet1.setPower(round + 1);
		bullet2.setPower(round + 1);
		bullet3.setPower(round + 1);
		pws.bullets.add(bullet1);
		pws.bullets.add(bullet2);
		pws.bullets.add(bullet3);
	}
	
	
	/**
	 * 生成血条的内部类
	 * @author 皇up
	 *
	 */
	class Blood{
		
		public void draw(Graphics g) {
			g.drawImage(ImageUtil.imgs.get("bosshp_ox"), 40, 40, 350,15, null);
			if(power>75*(round+1)) {
				g.drawImage(bloodImgs[0], 43, 40,350 * power/(100*(round+1)),15, null);
			}else if(power>50*(round+1)) {
				g.drawImage(bloodImgs[1], 43, 40,350 * power/(100*(round+1)),15, null);
			}else if(power>25*(round+1)) {
				g.drawImage(bloodImgs[2], 43, 40,350 * power/(100*(round+1)),15, null);
			}else {
				g.drawImage(bloodImgs[3], 43, 40,350 * power/(100*(round+1)),15, null);
			}
		}
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	
}
