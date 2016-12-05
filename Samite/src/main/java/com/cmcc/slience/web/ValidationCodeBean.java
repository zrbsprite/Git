package com.cmcc.slience.web;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang.RandomStringUtils;

public class ValidationCodeBean {

	private static Random random = new Random();

	private static Color getRandColor() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		return new Color(r, g, b);
	}

	public static void generate() {
		int width = 200;
		int height = 80;

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("consolas", Font.BOLD, height - 4);

		// 设置背景色
		graphics2D.setColor(getRandColor());
		// 主要在这里
		graphics2D.fillRect(0, 0, width, height);

		graphics2D.setFont(font);

		BasicStroke gasicStroke = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL); // 定制线条样式
		graphics2D.setStroke(gasicStroke);

		graphics2D.setColor(getRandColor());
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics2D.drawLine(x, y, x + xl, y + yl);
		}
		// 输出由英文，数字，和中文随机组成的验证文字，具体的组合方式根据生成随机数确定。
		String source = "1234567890ABCDEFGHJKLMNPQRSTUVWXYZ";
		int len = 4;
		int h = height / 3;
		AffineTransform trans = new AffineTransform();
		for (int i = 0; i < len; i++) {
			String str = RandomStringUtils.random(1, source);
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), random.nextInt(110));
			graphics2D.setColor(color);
			trans.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1), (width / len) * i
					+ (height - 4) / 2, height / 2);
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1f)
				scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			graphics2D.setTransform(trans);
			graphics2D.drawString(str, ((width - 10) / len) * i + 5, height / 2 + (height - 4) / 2 - 10);
		}
		try (FileOutputStream fos = new FileOutputStream("C:\\Users\\ruibo\\Desktop\\abc.jpg")) {
			ImageIO.write(bufferedImage, "jpg", fos);
			graphics2D.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		generate();
	}
}
