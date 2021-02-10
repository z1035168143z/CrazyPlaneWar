package com.neuedu.planewar.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * 一次性加载所有图片的工具类
 * @author 皇up
 *
 */
public class ImageUtil {
	
	/**
	 * 用于存储所有图片的Map集合
	 */
	public static Map<String, Image> imgs = new HashMap<>();
	
	static {
		
		//背景图片们
		for (int i = 1; i <= 5; i++) {
			imgs.put("bg_"+i, GameUtil.getImage("bg_level_"+i+".jpg"));
		}
		
		//我方一号飞机
		for (int i = 1; i <= 1; i++) {
			imgs.put("my_0"+i, GameUtil.getImage("my_plane_0"+i+".png"));
		}
		
		//敌方飞机
		for (int i = 1; i <= 6; i++) {
			imgs.put("enemy_0"+i, GameUtil.getImage("enemy_0"+i+".png"));
		}
		
		//我方子弹
		for (int i = 1; i <= 3; i++) {
			imgs.put("my_bullet_0"+i, GameUtil.getImage("my_bullet_0"+i+".png"));
		}
		
		//敌方子弹
		for (int i = 1; i <= 5; i++) {
			imgs.put("enemy_bullet_0"+i, GameUtil.getImage("enemy_bullet_0"+i+".png"));
		}
		
		//爆炸图片组
		for (int i = 1; i <= 15; i++) {
			imgs.put("e"+i, GameUtil.getImage("e"+i+".png"));
		}
		
		//数字图片组
		for (int i = 0; i <= 9; i++) {
			imgs.put("num_0"+i, GameUtil.getImage("num_0"+i+".png"));
		}
		
		//血量
		imgs.put("blood_div", GameUtil.getImage("blood_div.png"));
		imgs.put("blood_ox", GameUtil.getImage("blood_ox.png"));
		imgs.put("energy", GameUtil.getImage("energy.png"));
		
		//boss血量
		imgs.put("bosshp_ox", GameUtil.getImage("bosshp_ox.png"));
		for (int i = 1; i <= 4; i++) {
			imgs.put("bosshp"+i, GameUtil.getImage("bosshp"+i+".png"));
		}
		
		//boss子弹
		for (int i = 1; i <= 5; i++) {
			imgs.put("boss_bullet_0"+i, GameUtil.getImage("boss_bullet_0"+i+".png"));
		}
		
		//导弹
		imgs.put("missile", GameUtil.getImage("missile.png"));
		
		//道具图片组
		for (int i = 1; i <= 5; i++) {
			imgs.put("food_0"+i, GameUtil.getImage("food_0"+i+".png"));
		}
		
		//护盾道具
		imgs.put("protect", GameUtil.getImage("protect.png"));
		
		//精灵图片组
		for (int i = 1; i <= 22; i++) {
			imgs.put("fairy_"+i, GameUtil.getImage("Fairy_"+i+".png"));
		}
		
		//出场特效图片组
		for (int i = 1; i <= 32; i++) {
			imgs.put("duang_"+i, GameUtil.getImage("duang/duang_"+i+".png"));
		}
		
		//大招特效图片组
		for (int i = 1; i <= 6; i++) {
			imgs.put("duang_02_"+i, GameUtil.getImage("duang/duang_02_"+i+".png"));
		}
		
		//大招特效图片组
		for (int i = 1; i <= 9; i++) {
			imgs.put("duang_03_"+i, GameUtil.getImage("duang/duang_03_"+i+".png"));
		}
		
		//boss图片组
		for (int i = 1; i <= 9; i++) {
			imgs.put("boss01_"+i, GameUtil.getImage("boss/boss01_"+i+".png"));
			imgs.put("boss02_"+i, GameUtil.getImage("boss/boss02_"+i+".png"));
		}
		
		//加载开始界面
		imgs.put("bg_menu", GameUtil.getImage("bg_menu.jpg"));			//开始背景
		imgs.put("start", GameUtil.getImage("start.png"));				//开始游戏
		imgs.put("bg_start", GameUtil.getImage("bg_start.jpg"));		//暂停背景
		imgs.put("continue", GameUtil.getImage("continue.png"));		//继续游戏
		imgs.put("warning", GameUtil.getImage("warning.png"));			//警告
		imgs.put("help", GameUtil.getImage("bangzhu.png"));				//帮助
		imgs.put("achievement", GameUtil.getImage("achievement.png"));	//成就
		imgs.put("choujiang", GameUtil.getImage("choujiang.png"));		//抽奖
		imgs.put("breakStage", GameUtil.getImage("breakStage.png"));	//通关结算
		imgs.put("team", GameUtil.getImage("team.png"));				//制作团队
		imgs.put("shezhi", GameUtil.getImage("shezhi.png"));			//设置
		
		//加载关卡名图片
		for (int i = 0; i <= 4; i++) {
			imgs.put("stageName"+i, GameUtil.getImage("stageName"+i+".png"));
		}
		
		//加载boss名图片
		for (int i = 0; i <= 4; i++) {
			imgs.put("bossName"+i, GameUtil.getImage("bossName"+i+".png"));
		}
	}
}
