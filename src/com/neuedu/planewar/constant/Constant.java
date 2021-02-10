package com.neuedu.planewar.constant;

import com.neuedu.planewar.util.GameUtil;

/**
 * 游戏中的常量类
 * @author 皇up
 *
 */
public class Constant {
	/**
	 * 从配置文件获取窗口宽度
	 */
	public static final int GAME_WIDTH = Integer.parseInt(GameUtil.prop.getProperty("GAME_WIDTH"));
	/**
	 * 从配置文件获取窗口高度
	 */
	public static final int GAME_HEIGHT = Integer.parseInt(GameUtil.prop.getProperty("GAME_HEIGHT"));
	/**
	 * 从配置文件获取飞机移动速度
	 */
	public static final int PLANE_SPEED = Integer.parseInt(GameUtil.prop.getProperty("PLANE_SPEED"));
	/**
	 * 从配置文件获取图片加载共同路径
	 */
	public static final String IMAGE_PATH = GameUtil.prop.getProperty("IMAGE_PATH");
	/**
	 * 从配置文件获取敌机速度
	 */
	public static final int ENMEY_PALNE_01_SPEED = Integer.parseInt(GameUtil.prop.getProperty("ENMEY_PALNE_01_SPEED"));
	/**
	 * 从配置文件获取子弹速度
	 */
	public static final int BOLLET_SPEED = Integer.parseInt(GameUtil.prop.getProperty("BOLLET_SPEED"));
	/**
	 * 从配置文件获取敌机子弹速度
	 */
	public static final int ENMEY_BOLLET_01_SPEED = Integer.parseInt(GameUtil.prop.getProperty("ENMEY_BOLLET_01_SPEED"));
	/**
	 * 从配置文件获取友军生命
	 */
	public static final int PLANE_LIFE = Integer.parseInt(GameUtil.prop.getProperty("PLANE_LIFE"));
	
}
