package com.neuedu.planewar.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.core.Background;
import com.neuedu.planewar.core.Boss;
import com.neuedu.planewar.core.Bullet;
import com.neuedu.planewar.core.Direction;
import com.neuedu.planewar.core.EnemyPlane;
import com.neuedu.planewar.core.Explode;
import com.neuedu.planewar.core.Fairy;
import com.neuedu.planewar.core.Food;
import com.neuedu.planewar.core.Plane;
import com.neuedu.planewar.core.Protect;
import com.neuedu.planewar.core.Score;
import com.neuedu.planewar.core.Show;
import com.neuedu.planewar.core.Ultimate;
import com.neuedu.planewar.util.ImageUtil;
import com.neuedu.planewar.util.PlaneUtil;

/**
 * ִ����Ϸ������
 * @author ��up
 *
 */
public class PlaneWarSystem extends PlaneUtil {
	
	/**
	 * Ψһ��ʶ
	 */
	private static final long serialVersionUID = 1295506572827730531L;
	/**
	 * �Ѿ��ɻ�����
	 */
	public List<Plane> planes = new ArrayList<>();
	/**
	 * �о��ɻ�����
	 */
	public List<Plane> enemies = new ArrayList<>();
	/**
	 * �ӵ�
	 */
	public List<Bullet> bullets = new ArrayList<>();
	/**
	 * ��ը������
	 */
	public List<Explode> explodes = new ArrayList<>();
	/**
	 * ���߼���
	 */
	public List<Food> foods = new ArrayList<>();
	/**
	 * boss����
	 */
	public List<Boss> bosses = new ArrayList<>();
	/**
	 * ����ͼƬ����
	 */
	public List<Background> bgs = new ArrayList<>();
	/**
	 * ��������
	 */
	public List<Fairy> fairies = new ArrayList<>();
	/**
	 * ������Ч
	 */
	public List<Show> shows = new ArrayList<Show>();
	/**
	 * ��������
	 */
	public List<Protect> protects = new ArrayList<Protect>();
	/**
	 * ������Ч
	 */
	public List<Ultimate> ultimates = new ArrayList<Ultimate>();
	/**
	 * ����ֵ
	 */
	private int life = 0;
	/**
	 * ͼƬ��ʽ��ʾ����
	 */
	private Score s = new Score(this);
	/**
	 * �������boss
	 */
	private Random r = new Random();
	/**
	 * �ؿ�
	 */
	public int round = 0;
	/**
	 * ����,�л�����
	 */
	private int count = 0;
	public boolean start;
	/**
	 * ����,�ɸ����е�repaint()������������
	 */
	@Override
	public void paint(Graphics g) {
		
		//��h��ʼ
		if(!start) {
			Color c = g.getColor();
			g.setColor(Color.WHITE);
			if(round == 0) {
				g.drawImage(ImageUtil.imgs.get("bg_menu"), 0, 0, null);
				g.drawImage(ImageUtil.imgs.get("start"), 175, 300, null);
				g.drawImage(ImageUtil.imgs.get("achievement"), 180, 340, null);
				g.drawImage(ImageUtil.imgs.get("shezhi"), 180, 395, null);
				g.drawImage(ImageUtil.imgs.get("team"), 180, 445, null);
				g.drawImage(ImageUtil.imgs.get("help"), 180, 495, null);
			}else {
				g.drawImage(ImageUtil.imgs.get("bg_start"), 0, 0, null);
				g.drawImage(ImageUtil.imgs.get("stageName"+(round-1)), 140, 150, null);
				g.drawImage(ImageUtil.imgs.get("continue"), 180, 280, null);
				g.drawImage(ImageUtil.imgs.get("achievement"), 180, 340, null);
				g.drawImage(ImageUtil.imgs.get("shezhi"), 180, 395, null);
				g.drawImage(ImageUtil.imgs.get("team"), 180, 445, null);
				g.drawImage(ImageUtil.imgs.get("help"), 180, 495, null);
			}
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_H) {
						start = true;
						if(round == 0) {
							round = 1;
						}
					}
				}
					
			});
			g.setColor(c);
			return;
		}
		
		//��h��ͣ
		if(start) {
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_H) {
						start = false;
					}
				}
			});
		}
		//��������
		Font f = g.getFont();
		g.setFont(new Font("΢���ź�", Font.BOLD, 30));
		
		if(bgs.size() == 0) {
			for (int i = 1; i <= 5; i++) {
				bgs.add(new Background(i,this));
			}
		}
		//����ͼƬ
		if(round<6) {
			bgs.get(round-1).draw(g);
		}else {
			bgs.get(round-2).draw(g);
			enemies.removeAll(enemies);
			g.drawString("��ϲ��!ͨ��!", Constant.GAME_WIDTH/2-80, Constant.GAME_HEIGHT/2);
		}
		
		
		//��¼����
		if(planes.size()>0) {
			life = planes.get(0).getLife();
		}
		g.drawImage(ImageUtil.imgs.get("my_01"), 30, Constant.GAME_HEIGHT-80,50,50, null);
		g.drawString("X"+life, 80, Constant.GAME_HEIGHT-44);
		
		//������о�
		if(r.nextInt(1000)>950 && round <6) {
			if(round == 1) {
				enemies.add(new EnemyPlane(100*r.nextInt(5)+5, 30, "enemy_0"+(r.nextInt(6)+1),this,false,round));
			}else if(round == 2) {
				enemies.add(new EnemyPlane(r.nextInt(2) == 1 ? 5 : Constant.GAME_WIDTH-50, 30, "enemy_0"+(r.nextInt(6)+1),this,false,round));
			}else if(round == 3) {
				enemies.add(new EnemyPlane(r.nextInt(2) == 1 ? 5 : Constant.GAME_WIDTH-50, 30, "enemy_0"+(r.nextInt(6)+1),this,false,round));
			}else if(round == 4) {
				enemies.add(new EnemyPlane(Constant.GAME_WIDTH / 2 - 30, 100, "enemy_0"+(r.nextInt(6)+1),this,false,round));
			}else if(round == 5) {
				enemies.add(new EnemyPlane(Constant.GAME_WIDTH / 2 - 30, 100, "enemy_0"+(r.nextInt(6)+1),this,false,round));
			}
			count++;
		}
		
		//���ӵ�
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.draw(g);					//�����ӵ�
			b.hitPlanes(enemies);		//�ж��ӵ�����з�
			b.hitBollets(bullets);		//�ж��ӵ������ӵ�
			b.hitPlanes(planes);		//�ж��ӵ������ҷ�
			if(bosses.size()>0)
				b.hitBosses(bosses);
		}
		
		//������
		for (int i = 0; i < protects.size(); i++) {
			Protect protect = protects.get(i);
			protect.draw(g);
		}

		//���Ѿ�
		for (int i = 0; i < planes.size(); i++) {
			Plane p = planes.get(i);
			p.draw(g);
			p.eatFoods(foods);
		}
		
		//���о�
		for (int i = 0; i < enemies.size(); i++) {
			Plane enemy = enemies.get(i);
			enemy.draw(g);
		}
		
		//������
		for (int i = 0; i < foods.size(); i++) {
			Food food = foods.get(i);
			food.draw(g);
		}
		
		//��������Ч
		for (int i = 0; i < shows.size(); i++) {
			Show show = shows.get(i);
			show.draw(g);
		}
		
		//������
		for (int i = 0; i < fairies.size(); i++) {
			Fairy fairy = fairies.get(i);
			fairy.draw(g);
		}
		
		//������������boss
		if(count > 30 * round * round && bosses.size() == 0 && round <6) {
			g.drawImage(ImageUtil.imgs.get("warning"), 180, 300, null);
			Boss boss = new Boss(round,this);
			bosses.add(boss);
		}
		
		//������ʾ
		g.drawString("����:", Constant.GAME_WIDTH-180, 55);
		s.draw(g);
		
		//��boss
		for (int i = 0; i < bosses.size(); i++) {
			Boss boss = bosses.get(i);
			boss.draw(g);
		}
		
		//����ըЧ��
		for (int i = 0; i < explodes.size(); i++) {
			Explode explode = explodes.get(i);
			explode.draw(g);
		}
		
		//��������Ч
		for (int i = 0; i < ultimates.size(); i++) {
			Ultimate ultimate = ultimates.get(i);
			ultimate.draw(g);
			if(bosses.size()>0) {
				ultimate.destory(bosses.get(0));
			}
			ultimate.destorys(enemies);
			ultimate.destoryBullets(bullets);
		}
		
		//�ѷ�ȫ��,GAME OVER
		if(planes.size() <= 0) {
			g.drawString("GAME OVER", Constant.GAME_WIDTH/2-60, Constant.GAME_HEIGHT/2);
		}
		
		//���廹ԭ
		g.setFont(f);
	}
	
	/**
	 * �޲ι���,���ڴ�������
	 */
	public PlaneWarSystem() {}
	
	@Override
	public void lauchFrame() {
		super.lauchFrame();
		this.setTitle("�ɻ���ս");
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				for (int i = 0; i < planes.size(); i++) {
					planes.get(i).keyPressed(e);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				for (int i = 0; i < planes.size(); i++) {
					planes.get(i).keyReleased(e);
				}
			}
		});
		planes.add(new Plane(350, 600, "my_01",Direction.STOP,this,true));
		/*for (int i = 0; i < 5; i++) {
			enemies.add(new EnemyPlane(100+100*i, 30, "enemy_01", Direction.DOWN,this,false));
		}
		count++;*/
	}

	/**
	 * ������,�����Լ��Ķ���,�����÷���
	 * @param args	û˵��
	 */
	public static void main(String[] args) {
		new PlaneWarSystem().lauchFrame();
	}

}
