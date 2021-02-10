package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * �ӵ���
 * @author ��up
 *
 */
public class Bullet extends PlaneWarObject{
	/**
	 * �ӵ�����
	 */
	private Direction dir;
	/**
	 * �ӵ����ٶ�
	 */
	private int speed;
	/**
	 * ��ͣ��,��ȡ�ɻ�������ȷ���ӵ���ʼλ��
	 */
	private Plane p;
	
	public Bullet() {
	}
	
	/**
	 * ���ӵ�
	 * @param x			��ʼx����
	 * @param y			��ʼy����
	 * @param key		�ӵ���ͼƬ
	 * @param dir		�ӵ��ķ���
	 * @param pws		��ͣ��
	 * @param p			�ɻ�����
	 * @param belong	�ӵ�����
	 */
	public Bullet(int x,int y,String key,Direction dir,PlaneWarSystem pws,Plane p,boolean belong) {
		this.img = ImageUtil.imgs.get(key);
		this.x = x - img.getWidth(null)/2 + p.img.getWidth(null)/2;
		this.y = y + img.getWidth(null);
		this.dir = dir;
		this.pws = pws;
		this.live = true;
		this.belong = belong;
		if(this.belong) {
			this.speed = Constant.BOLLET_SPEED;
		}else {
			this.speed = Constant.ENMEY_BOLLET_01_SPEED;
		}
		this.power = 1;
	}
	
	/**
	 * ����
	 * @param key	����ͼƬ
	 * @param pws	��ͣ��
	 */
	public Bullet(String key,PlaneWarSystem pws) {
		this.x = new Random().nextInt(512);
		this.y = 30;
		this.dir = Direction.DOWN;
		this.power = 5;
		this.img = ImageUtil.imgs.get(key);
		this.belong = false;
		this.speed = 15;
		this.pws = pws;
	}
	
	/**
	 * �����ӵ�,���ҵ���move()����
	 */
	@Override
	public void draw(Graphics g) {
		if(!live) return;
		g.drawImage(img, x, y, null);
		outOfBounds();
		move();
	}
	
	/**
	 * �ӵ����������
	 */
	private void outOfBounds() {
		if(x<0 || x>Constant.GAME_WIDTH || y<0 || y>Constant.GAME_HEIGHT) {
			pws.bullets.remove(this);
		}
	}
	
	/**
	 * �ӵ��Ƿ���зɻ�,������ը��Ч����
	 * @param p 
	 */
	private boolean hitPlane(Plane p) {
		if(this.getRectangle().intersects(p.getRectangle()) && this.belong != p.belong) {
			//��ը��Ч
			Explode e = new Explode(p.x+p.img.getWidth(null)/2, p.y+p.img.getHeight(null)/2,pws);
			pws.explodes.add(e);
			//�ӵ�״̬��,�������Ƴ�
			this.live = false;
			pws.bullets.remove(this);
			if(!p.belong) {
				p.power -= this.power;
				if(p.power <= 0) {
					p.live = false;
					pws.enemies.remove(p);
					if(!p.belong && new Random().nextInt(1000)>(950/pws.round)) {
						Food food = new Food(p.getX(), p.getY(),p,pws);
						pws.foods.add(food);
					}
					//�����ѷ���δȫ����û,�ݻٵо��ӷ�.
					if(!p.belong && pws.planes.size()>0) {
						pws.planes.get(0).setScore(pws.planes.get(0).getScore()+10);
						if(pws.planes.get(0).getEnergyBar()<150)
							pws.planes.get(0).setEnergyBar(pws.planes.get(0).getEnergyBar()+1);
					}
				}
			}
			
			
			
			//���p���Ѿ�
			if(p.belong) {
				if(pws.protects.size() != 0) {
					pws.protects.get(0).power -= this.power;
					if(pws.protects.get(0).power <= 0) {
						pws.protects.removeAll(pws.protects);
					}
				}else {
					p.power -= this.power;
					if(p.power<=0) {
						if(pws.planes.get(0).getLife() == 0) { 
							pws.planes.remove(p);
						}
						p.setLife(p.getLife()-1);
						p.setBullet_level(1);
						p.power = 10;
						p.setX(Constant.GAME_WIDTH/2);
						p.setY(Constant.GAME_HEIGHT);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * �жϷɻ��Ƿ񱻻���
	 * @param plane ������жϷɻ�����
	 * @return	�Ƿ����
	 */
	public boolean hitPlanes(List<Plane> plane) {
		for (int i = 0; i < plane.size(); i++) {
			Plane p = plane.get(i);
			if(hitPlane(p)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �ӵ��໥����
	 * @param b
	 * @return
	 */
	private boolean hitBollet(Bullet b) {
		if(this.getRectangle().intersects(b.getRectangle()) && this.belong != b.belong && this.speed != 15 && b.speed != 15) {
			
			pws.planes.get(0).setScore(pws.planes.get(0).getScore()+10);
			this.live = false;
			pws.bullets.remove(this);
			b.live = false;
			pws.bullets.remove(b);
			
			return true;
		}
		return false;
	}
	
	/**
	 * �Ƚ��ӵ��Ƿ���ײ
	 * @param bollets ���Ƚ��ӵ�����
	 * @return	�Ƿ���ӵ�
	 */
	public boolean hitBollets(List<Bullet> bollets) {
		for (int i = 0; i < bollets.size(); i++) {
			Bullet b = bollets.get(i);
			if(hitBollet(b)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �ӵ��Ƿ����boss,������ը��Ч����
	 * @param boss	 	boss����
	 * @return	�Ƿ���ӵ�
	 */
	public boolean hitBoss(Boss boss) {
		if(this.getRectangle().intersects(boss.getRectangle()) && this.belong != boss.belong) {
			//�ӵ�״̬��,�������Ƴ�
			this.live = false;
			pws.bullets.remove(this);
			if(pws.planes.get(0).getEnergyBar()<150) 
				pws.planes.get(0).setEnergyBar(pws.planes.get(0).getEnergyBar()+1);
			if(boss.power>0) {
				boss.power -= this.power;
				if(boss.power <= 0) {
					pws.planes.get(0).setPower(10);
					BossDeath bd = new BossDeath(boss.x+boss.img.getWidth(null)/2, boss.y+boss.img.getHeight(null)/2,pws,boss);
					pws.explodes.add(bd);
					if(pws.fairies.size() == 0) {
						Show show = new Show(pws.planes.get(0).getX()+pws.planes.get(0).img.getWidth(null), pws.planes.get(0).getY()+20, pws);
						pws.shows.add(show);
					}
				}
			}
			return true;
		}
		return false;
	}
	public boolean hitBosses(List<Boss> bosses) {
		for (int i = 0; i < bosses.size(); i++) {
			Boss boss = bosses.get(i);
			if(hitBoss(boss)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �ӵ����ƶ�����
	 */
	@Override
	public void move() {
		switch (dir) {
		case LEFT:
			x -= speed;
			break;
		case LEFT_UP:
			x -= speed;
			y -= speed;
			break;
		case UP:
			y -= speed;
			break;
		case RIGHT_UP:
			x += speed;
			y -= speed;
			break;
		case RIGHT:
			x += speed;
			break;
		case RIGHT_DOWN:
			x += speed;
			y += speed;
			break;
		case DOWN:
			y += speed;
			break;
		case LEFT_DOWN:
			x -= speed;
			y += speed;
			break;
		default:
			break;
		}
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Plane getP() {
		return p;
	}

	public void setP(Plane p) {
		this.p = p;
	}
	
}
