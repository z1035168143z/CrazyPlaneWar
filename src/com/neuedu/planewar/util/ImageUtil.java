package com.neuedu.planewar.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * һ���Լ�������ͼƬ�Ĺ�����
 * @author ��up
 *
 */
public class ImageUtil {
	
	/**
	 * ���ڴ洢����ͼƬ��Map����
	 */
	public static Map<String, Image> imgs = new HashMap<>();
	
	static {
		
		//����ͼƬ��
		for (int i = 1; i <= 5; i++) {
			imgs.put("bg_"+i, GameUtil.getImage("bg_level_"+i+".jpg"));
		}
		
		//�ҷ�һ�ŷɻ�
		for (int i = 1; i <= 1; i++) {
			imgs.put("my_0"+i, GameUtil.getImage("my_plane_0"+i+".png"));
		}
		
		//�з��ɻ�
		for (int i = 1; i <= 6; i++) {
			imgs.put("enemy_0"+i, GameUtil.getImage("enemy_0"+i+".png"));
		}
		
		//�ҷ��ӵ�
		for (int i = 1; i <= 3; i++) {
			imgs.put("my_bullet_0"+i, GameUtil.getImage("my_bullet_0"+i+".png"));
		}
		
		//�з��ӵ�
		for (int i = 1; i <= 5; i++) {
			imgs.put("enemy_bullet_0"+i, GameUtil.getImage("enemy_bullet_0"+i+".png"));
		}
		
		//��ըͼƬ��
		for (int i = 1; i <= 15; i++) {
			imgs.put("e"+i, GameUtil.getImage("e"+i+".png"));
		}
		
		//����ͼƬ��
		for (int i = 0; i <= 9; i++) {
			imgs.put("num_0"+i, GameUtil.getImage("num_0"+i+".png"));
		}
		
		//Ѫ��
		imgs.put("blood_div", GameUtil.getImage("blood_div.png"));
		imgs.put("blood_ox", GameUtil.getImage("blood_ox.png"));
		imgs.put("energy", GameUtil.getImage("energy.png"));
		
		//bossѪ��
		imgs.put("bosshp_ox", GameUtil.getImage("bosshp_ox.png"));
		for (int i = 1; i <= 4; i++) {
			imgs.put("bosshp"+i, GameUtil.getImage("bosshp"+i+".png"));
		}
		
		//boss�ӵ�
		for (int i = 1; i <= 5; i++) {
			imgs.put("boss_bullet_0"+i, GameUtil.getImage("boss_bullet_0"+i+".png"));
		}
		
		//����
		imgs.put("missile", GameUtil.getImage("missile.png"));
		
		//����ͼƬ��
		for (int i = 1; i <= 5; i++) {
			imgs.put("food_0"+i, GameUtil.getImage("food_0"+i+".png"));
		}
		
		//���ܵ���
		imgs.put("protect", GameUtil.getImage("protect.png"));
		
		//����ͼƬ��
		for (int i = 1; i <= 22; i++) {
			imgs.put("fairy_"+i, GameUtil.getImage("Fairy_"+i+".png"));
		}
		
		//������ЧͼƬ��
		for (int i = 1; i <= 32; i++) {
			imgs.put("duang_"+i, GameUtil.getImage("duang/duang_"+i+".png"));
		}
		
		//������ЧͼƬ��
		for (int i = 1; i <= 6; i++) {
			imgs.put("duang_02_"+i, GameUtil.getImage("duang/duang_02_"+i+".png"));
		}
		
		//������ЧͼƬ��
		for (int i = 1; i <= 9; i++) {
			imgs.put("duang_03_"+i, GameUtil.getImage("duang/duang_03_"+i+".png"));
		}
		
		//bossͼƬ��
		for (int i = 1; i <= 9; i++) {
			imgs.put("boss01_"+i, GameUtil.getImage("boss/boss01_"+i+".png"));
			imgs.put("boss02_"+i, GameUtil.getImage("boss/boss02_"+i+".png"));
		}
		
		//���ؿ�ʼ����
		imgs.put("bg_menu", GameUtil.getImage("bg_menu.jpg"));			//��ʼ����
		imgs.put("start", GameUtil.getImage("start.png"));				//��ʼ��Ϸ
		imgs.put("bg_start", GameUtil.getImage("bg_start.jpg"));		//��ͣ����
		imgs.put("continue", GameUtil.getImage("continue.png"));		//������Ϸ
		imgs.put("warning", GameUtil.getImage("warning.png"));			//����
		imgs.put("help", GameUtil.getImage("bangzhu.png"));				//����
		imgs.put("achievement", GameUtil.getImage("achievement.png"));	//�ɾ�
		imgs.put("choujiang", GameUtil.getImage("choujiang.png"));		//�齱
		imgs.put("breakStage", GameUtil.getImage("breakStage.png"));	//ͨ�ؽ���
		imgs.put("team", GameUtil.getImage("team.png"));				//�����Ŷ�
		imgs.put("shezhi", GameUtil.getImage("shezhi.png"));			//����
		
		//���عؿ���ͼƬ
		for (int i = 0; i <= 4; i++) {
			imgs.put("stageName"+i, GameUtil.getImage("stageName"+i+".png"));
		}
		
		//����boss��ͼƬ
		for (int i = 0; i <= 4; i++) {
			imgs.put("bossName"+i, GameUtil.getImage("bossName"+i+".png"));
		}
	}
}
