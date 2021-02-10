package com.neuedu.planewar.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.neuedu.planewar.constant.Constant;

/**
 * 关于游戏中获取图像、声音等的工具类
 * @author 皇up
 *
 */
public class GameUtil {
	
	/**
	 * 加载配置文件
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
	 * 获取图片对象
	 * @param imgPath	文件路径
	 * @return Image对象
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
