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
 * 所有飞机类
 * @author 皇up
 *
 */
public class Plane extends PlaneWarObject{
	
	/**
	 * 控制上下左右方向
	 */
	private boolean up,left,right,down;
	/**
	 * 移动速度
	 */
	private int speed;
	/**
	 * 方向
	 */
	private Direction dir;
	/**
	 * 调停者
	 */
	private PlaneWarSystem pws;
	/**
	 * 分数
	 */
	private int score;
	/**
	 * 飞机生命
	 */
	private int life;
	/**
	 * 内部类画血条对象
	 */
	private Blood bloodImg = new Blood();
	private Energy energy = new Energy();
	/**
	 * 子弹的等级
	 */
	private int bullet_level;
	/**
	 * 能量条
	 */
	private int energyBar;
	public Plane() {
	}
	
	/**
	 * 用于创建对象并赋值的构造方法
	 * @param x 横坐标
	 * @param y	纵坐标
	 * @param path	路径
	 */
	private Plane(int x,int y,String key) {
		this.x = x;
		this.y = y;
		this.img = ImageUtil.imgs.get(key);
		this.speed = Constant.PLANE_SPEED;
	}
	
	/**
	 * 重载构造方法,加入方向
	 * @param x 	横坐标
	 * @param y		纵坐标
	 * @param key	路径
	 * @param dir	方向
	 * @param pws	调停者
	 * @param belong	所属
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
	 * 重写父类的draw()方法,用来在画板画出飞机,并且调用了移动方法
	 */
	@Override
	public void draw(Graphics g) {
		bloodImg.draw(g);
		energy.draw(g);
		g.drawImage(img, x, y,null);
		move();
	}
	
	/**
	 * 判断是否吃到道具
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
	 * 遍历穿入的道具集合,并调用eat()方法
	 * @param food 道具集合
	 * @return 是否吃到
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
	 * 发射子弹
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
	 * 重写父类移动方法,八个方向
	 * 并且让飞机不出边界
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
	 * 根据键盘按键方向对dir方向进行赋值
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
	 * 键盘按下监听器
	 * @param e	KeyEvent对象
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
	 * 键盘抬起监听器
	 * @param e	KeyEvent对象
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
	 * 生成血条的内部类
	 * @author 皇up
	 *
	 */
	class Blood{
		public void draw(Graphics g) {
			/**
			 * 如果是友军
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
