package com.neuedu.planewar.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.neuedu.planewar.constant.Constant;

/**
 * ������Ϸ�л�ȡͼ�������ȵĹ�����
 * @author ��up
 *
 */
public class GameUtil {
	
	/**
	 * ���������ļ�
	 */
	public static Properties prop = new Properties();
	
	static {
		try {
			prop.load(GameUtil.class.getClassLoader().getResourceAsStream("crazy_plane_war.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡͼƬ����
	 * @param imgPath	�ļ�·��
	 * @return Image����
	 */
	public static Image getImage(String imgPath) {
		URL u = GameUtil.class.getClassLoader().getResource(Constant.IMAGE_PATH+imgPath);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img; 
	}
}
