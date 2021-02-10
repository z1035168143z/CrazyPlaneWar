package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * ���зɻ���
 * @author ��up
 *
 */
public class Plane extends PlaneWarObject{
	
	/**
	 * �����������ҷ���
	 */
	private boolean up,left,right,down;
	/**
	 * �ƶ��ٶ�
	 */
	private int speed;
	/**
	 * ����
	 */
	private Direction dir;
	/**
	 * ��ͣ��
	 */
	private PlaneWarSystem pws;
	/**
	 * ����
	 */
	private int score;
	/**
	 * �ɻ�����
	 */
	private int life;
	/**
	 * �ڲ��໭Ѫ������
	 */
	private Blood bloodImg = new Blood();
	private Energy energy = new Energy();
	/**
	 * �ӵ��ĵȼ�
	 */
	private int bullet_level;
	/**
	 * ������
	 */
	private int energyBar;
	public Plane() {
	}
	
	/**
	 * ���ڴ������󲢸�ֵ�Ĺ��췽��
	 * @param x ������
	 * @param y	������
	 * @param path	·��
	 */
	private Plane(int x,int y,String key) {
		this.x = x;
		this.y = y;
		this.img = ImageUtil.imgs.get(key);
		this.speed = Constant.PLANE_SPEED;
	}
	
	/**
	 * ���ع��췽��,���뷽��
	 * @param x 	������
	 * @param y		������
	 * @param key	·��
	 * @param dir	����
	 * @param pws	��ͣ��
	 * @param belong	����
	 */
	public Plane(int x,int y,String key,Direction dir,PlaneWarSystem pws,boolean belong) {
		this(x, y, key);
		this.dir = dir;
		this.pws = pws;
		this.belong = belong;
		this.life = Constant.PLANE_LIFE;
		this.power = 10;
		this.bullet_level = 1;
		this.energyBar = 0;
	}
	
	/**
	 * ��д�����draw()����,�����ڻ��廭���ɻ�,���ҵ������ƶ�����
	 */
	@Override
	public void draw(Graphics g) {
		bloodImg.draw(g);
		energy.draw(g);
		g.drawImage(img, x, y,null);
		move();
	}
	
	/**
	 * �ж��Ƿ�Ե�����
	 * @param food
	 * @return
	 */
	private boolean eat(Food food) {
		
		if(this.getRectangle().intersects(food.getRectangle()) && this.belong) {
			
			switch (food.getLevel()) {
			case 0:
				if(this.bullet_level<3) 
					this.bullet_level += 1;
				else
					this.bullet_level = 3;
				break;
				
			case 1:
				this.bullet_level = 3;
				break;
				
			case 2:
				for (int i = 0; i < pws.enemies.size(); i++) {
					Plane ememy = pws.enemies.get(i);
					Explode e = new Explode(ememy.x+ememy.img.getWidth(null)/2, ememy.y+ememy.img.getHeight(null)/2,pws);
					pws.explodes.add(e);
					if(new Random().nextInt(1000)>950) {
						Food f = new Food(ememy.getX(), ememy.getY(),ememy,pws);
						pws.foods.add(f);
					}
				}
				pws.enemies.removeAll(pws.enemies);
				break;
				
			case 3:
				if(this.power + 3 <= 10) {
					this.power += 3;
				}else {
					this.power = 10;
				}
				break;
				
			case 4:
				if(pws.protects.size() == 0) {
					Protect protect = new Protect(this.getX(), this.getY(),pws,this);
					pws.protects.add(protect);
				}else {
					pws.protects.get(0).setPower(5);
				}
				break;

			default:
				break;
			}
			pws.foods.remove(food);
		}
		
		return false;
	}
	
	/**
	 * ��������ĵ��߼���,������eat()����
	 * @param food ���߼���
	 * @return �Ƿ�Ե�
	 */
	public boolean eatFoods(List<Food> food) {

		for (int i = 0; i < food.size(); i++) {
			Food f = food.get(i);
			if(eat(f)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �����ӵ�
	 */
	private void fire() {
		Bullet b1 = new Bullet(x, y, "my_bullet_0"+this.bullet_level, Direction.UP,this.pws,this,belong);
		pws.bullets.add(b1);
		/*Bullet b2 = new Bullet(x+23, y, "my_bullet_0"+this.bullet_level, Direction.UP,this.pws,this,belong);
		Bullet b3 = new Bullet(x-23, y, "my_bullet_0"+this.bullet_level, Direction.UP,this.pws,this,belong);
		pws.bullets.add(b2);
		pws.bullets.add(b3);*/
		Bullet b4 = new Bullet(x+50, y, "my_bullet_02", Direction.UP,this.pws,this,belong);
		Bullet b5 = new Bullet(x-50, y, "my_bullet_02", Direction.UP,this.pws,this,belong);
		pws.bullets.add(b4);
		pws.bullets.add(b5);
		if(this.bullet_level>1) {
			Bullet b6 = new Bullet(x+100, y, "my_bullet_01", Direction.UP,this.pws,this,belong);
			Bullet b7 = new Bullet(x-100, y, "my_bullet_01", Direction.UP,this.pws,this,belong);
			pws.bullets.add(b6);
			pws.bullets.add(b7);
		}
		if(this.bullet_level>2) {
			Bullet b8 = new Bullet(x+125, y, "my_bullet_01", Direction.LEFT_UP,this.pws,this,belong);
			Bullet b9 = new Bullet(x-125, y, "my_bullet_01", Direction.RIGHT_UP,this.pws,this,belong);
			pws.bullets.add(b8);
			pws.bullets.add(b9);
		}
	}
	
	/**
	 * ��д�����ƶ�����,�˸�����
	 * �����÷ɻ������߽�
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
		
		if(x<0) x = 0;
		if(x>Constant.GAME_WIDTH-this.img.getWidth(null)) {
			x = Constant.GAME_WIDTH-this.img.getWidth(null);
		}
		if(y<30) y = 30;
		if(y>Constant.GAME_HEIGHT - this.img.getHeight(null)) {
			y = Constant.GAME_HEIGHT - this.img.getHeight(null);
		}
	}
	
	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x + img.getWidth(null) / 4, y + img.getHeight(null) / 4, img.getWidth(null) / 2, img.getHeight(null) / 2);
	}
	
	
	/**
	 * ���ݼ��̰��������dir������и�ֵ
	 */
	private void locatedDirection() {
		if(left && !up && !right && !down) {
			dir = Direction.LEFT;
		}else if(left && up && !right && !down) {
			dir = Direction.LEFT_UP;
		}else if(!left && up && !right && !down) {
			dir = Direction.UP;
		}else if(!left && up && right && !down) {
			dir = Direction.RIGHT_UP;
		}else if(!left && !up && right && !down) {
			dir = Direction.RIGHT;
		}else if(!left && !up && right && down) {
			dir = Direction.RIGHT_DOWN;
		}else if(!left && !up && !right && down) {
			dir = Direction.DOWN;
		}else if(left && !up && !right && down) {
			dir = Direction.LEFT_DOWN;
		}else {
			dir = Direction.STOP;
		}
	}
	
	/**
	 * ���̰��¼�����
	 * @param e	KeyEvent����
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;

		case KeyEvent.VK_W:
			up = true;
			break;
			
		case KeyEvent.VK_D:
			right = true;
			break;
			
		case KeyEvent.VK_S:
			down = true;
			break;
			
		case KeyEvent.VK_J:
			fire();
			break;
			
		case KeyEvent.VK_U:
			if(energyBar >= 50) {
				Ultimate u = new Ultimate(this.x, this.y, this, pws);
				pws.ultimates.add(u);
				energyBar -= 50;
			}
			break;
		}
		locatedDirection();
	}
	
	/**
	 * ����̧�������
	 * @param e	KeyEvent����
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;

		case KeyEvent.VK_W:
			up = false;
			break;
			
		case KeyEvent.VK_D:
			right = false;
			break;
			
		case KeyEvent.VK_S:
			down = false;
			break;
		}
		locatedDirection();
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	public int getBullet_level() {
		return bullet_level;
	}

	public void setBullet_level(int bullet_level) {
		this.bullet_level = bullet_level;
	}
	
	public int getEnergyBar() {
		return energyBar;
	}

	public void setEnergyBar(int energyBar) {
		this.energyBar = energyBar;
	}



	/**
	 * ����Ѫ�����ڲ���
	 * @author ��up
	 *
	 */
	class Blood{
		public void draw(Graphics g) {
			/**
			 * ������Ѿ�
			 */
			if(belong) {
				g.drawImage(ImageUtil.imgs.get("blood_div"), x-2, y-9, 92,5, null);
				
				g.drawImage(ImageUtil.imgs.get("blood_ox"), x, y-9,86 * power/10,5, null);
			}
		}
	}
	class Energy{
		public void draw(Graphics g) {
			if(belong) {
				g.drawImage(ImageUtil.imgs.get("energy"), x, y-6, 86 * energyBar / 150,2, null);
			}
		}
	}
}
