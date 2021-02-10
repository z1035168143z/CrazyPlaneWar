package com.neuedu.planewar.constant;

import com.neuedu.planewar.util.GameUtil;

/**
 * ��Ϸ�еĳ�����
 * @author ��up
 *
 */
public class Constant {
	/**
	 * �������ļ���ȡ���ڿ��
	 */
	public static final int GAME_WIDTH = Integer.parseInt(GameUtil.prop.getProperty("GAME_WIDTH"));
	/**
	 * �������ļ���ȡ���ڸ߶�
	 */
	public static final int GAME_HEIGHT = Integer.parseInt(GameUtil.prop.getProperty("GAME_HEIGHT"));
	/**
	 * �������ļ���ȡ�ɻ��ƶ��ٶ�
	 */
	public static final int PLANE_SPEED = Integer.parseInt(GameUtil.prop.getProperty("PLANE_SPEED"));
	/**
	 * �������ļ���ȡͼƬ���ع�ͬ·��
	 */
	public static final String IMAGE_PATH = GameUtil.prop.getProperty("IMAGE_PATH");
	/**
	 * �������ļ���ȡ�л��ٶ�
	 */
	public static final int ENMEY_PALNE_01_SPEED = Integer.parseInt(GameUtil.prop.getProperty("ENMEY_PALNE_01_SPEED"));
	/**
	 * �������ļ���ȡ�ӵ��ٶ�
	 */
	public static final int BOLLET_SPEED = Integer.parseInt(GameUtil.prop.getProperty("BOLLET_SPEED"));
	/**
	 * �������ļ���ȡ�л��ӵ��ٶ�
	 */
	public static final int ENMEY_BOLLET_01_SPEED = Integer.parseInt(GameUtil.prop.getProperty("ENMEY_BOLLET_01_SPEED"));
	/**
	 * �������ļ���ȡ�Ѿ�����
	 */
	public static final int PLANE_LIFE = Integer.parseInt(GameUtil.prop.getProperty("PLANE_LIFE"));
	
}
