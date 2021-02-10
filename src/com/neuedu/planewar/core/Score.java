package com.neuedu.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.planewar.client.PlaneWarSystem;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.util.ImageUtil;

/**
 * 分数转换为图片
 * @author 皇up
 *
 */
public class Score extends PlaneWarObject{

	/**
	 * 调停者
	 */
	private PlaneWarSystem pws;
	/**
	 * 记录得分
	 */
	private int score;
	
	public Score() {
	}
	
	/**
	 * 创建对象,赋予值
	 * @param pws	调停者
	 */
	public Score(PlaneWarSystem pws) {
		this.x = Constant.GAME_WIDTH-110;
		this.y = 30;
		this.pws = pws;
	}



	@Override
	public void draw(Graphics g) {
		if(pws.planes.size()>0) {
			score = pws.planes.get(0).getScore();
		}
		List<Image> scoreArray = getScore(score);
		for (int i = 0; i < scoreArray.size(); i++) {
			g.drawImage(scoreArray.get(i), x+i*17, y, 17, 30,null);
		}
		
	}

	@Override
	public void move() {
	}
	
	/**
	 * 将传入的分数转换成List
	 * @param score 分数
	 * @return List
	 */
	public List<Image> getScore(int score) {
		String scoreStr = score + "";
		if(scoreStr.length()<6) {
			//scoreStr = String.format("%6d", score).replace(" ", "0");
			scoreStr = String.format("%06d", score);
		}
		List<Image> scoreArray = new ArrayList<>();
		for (int i = 0; i < scoreStr.length(); i++) {
			int j = scoreStr.charAt(i)-'0';
			scoreArray.add(ImageUtil.imgs.get("num_0"+j));
		}
		return scoreArray;
	}

	
	
	
	public PlaneWarSystem getPws() {
		return pws;
	}

	public void setPws(PlaneWarSystem pws) {
		this.pws = pws;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
