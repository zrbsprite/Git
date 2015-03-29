package com.jsprite.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

public class EncryptUtils {

	private static final String KEYCODE = "AAAAB3NzaC1yc2EAAAADAQABAAAAgQC1ODcwBmuzTZdBwj9Nj4OECPXdIy0OZt7fy"
			+ "Q1U5wr1f6tnFPZzGTBKjAqDN9chhCgNcez1G8BrD3vQt686c/8GkxdrhzZO0xGEY4rlKr7eVI53ts9O7xg03WDEZlORHotA1Gfk1Z6yxl1koV/MuUkuHuSd9UctB/No7IIkeMJonQ== RSA-1024";

	/**
	 * 解密字符串
	 * 
	 * @param src
	 * @return 解密后的字符串
	 */
	public static String decryptASE(String src) {
		try {
			byte[] myKey = getLocalKey(KEYCODE);
			byte[] btext = hexStr2byte(src);
			SecretKeySpec key = new SecretKeySpec(myKey, "AES");
			// 创建密码器
			Cipher cipher = Cipher.getInstance("AES");
			// 初始化
			cipher.init(Cipher.DECRYPT_MODE, key);
			// 解密
			byte[] result = cipher.doFinal(btext);
			return new String(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 方法名称: encryptASE<br>
	 * 描述：产生ASE加密后的字符串
	 * 作者: ruibo
	 * 修改日期：2015年3月29日上午10:00:34
	 * @param source
	 * @return
	 */
	public static String encryptASE(String source) {
		String result = null;
		try {
			byte[] myKey = getLocalKey(KEYCODE);
			SecretKeySpec key = new SecretKeySpec(myKey, "AES");
			// 创建密码器
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = source.getBytes("UTF-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byteArray = cipher.doFinal(byteContent);
			return byte2Hex(byteArray);
		} catch (Exception e) {
			LogUtils.error("加密戳错", EncryptUtils.class);
		}
		return result;
	}

	/**
	 * 方法名称: fromBase64<br>
	 * 描述：把base64字符串还原
	 * 作者: ruibo
	 * 修改日期：2015年3月29日上午10:01:03
	 * @param base64Str
	 * @return
	 */
	public static String fromBase64(String base64Str) {
		byte[] temp = Base64Utils.decodeFromString(base64Str);
		return new String(temp);
	}

	/**
	 * 方法名称: getMD5<br>
	 * 描述：生成MD5加密字符串
	 * 作者: ruibo
	 * 修改日期：2015年3月29日上午10:01:47
	 * @param src
	 * @return
	 */
	public static String getMD5(String src) {
		byte[] byteArray = digestMd5(src);
		return byte2Hex(byteArray);
	}

	/**
	 * 方法名称: getSHA<br>
	 * 描述：生成SHA加密字符串
	 * 作者: ruibo
	 * 修改日期：2015年3月29日上午10:02:11
	 * @param src
	 * @return
	 */
	public static String getSHA(String src) {
		byte[] byteArray = digestSHA(src);
		return byte2Hex(byteArray);
	}

	/**
	 * 方法名称: toBase64<br>
	 * 描述：转化成base64字符串
	 * 作者: ruibo
	 * 修改日期：2015年3月29日上午10:02:30
	 * @param src
	 * @return
	 */
	public static String toBase64(String src) {
		if (StringUtils.isEmpty(src)) {
			return "";
		}
		return Base64Utils.encodeToString(src.getBytes());
	}

	private static String byte2Hex(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			byte b = byteArray[i];
			int src = b & 0xFF;
			String str = Integer.toHexString(src);
			if (str.length() < 2) {
				str = "0" + str;
			}
			sb.append(str);
		}
		return sb.toString().toUpperCase();
	}

	private static byte[] digestMd5(String src) {
		byte[] result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(src.getBytes());
			result = digest.digest();
		} catch (NoSuchAlgorithmException e) {
			LogUtils.warn("不支持的加密类型", EncryptUtils.class);
		}
		return result;
	}

	private static byte[] digestSHA(String src) {
		byte[] result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			digest.update(src.getBytes());
			result = digest.digest();
		} catch (NoSuchAlgorithmException e) {
			LogUtils.warn("不支持的加密类型", EncryptUtils.class);
		}
		return result;
	}

	/**
	 * 传入KEY
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] getLocalKey(String keycode)
			throws NoSuchAlgorithmException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = null;
		// SHA1PRNG 强随机种子算法, 要区别android 4.2以上版本的调用方法
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
		} catch (NoSuchProviderException e) {
			LogUtils.warn("不支持的加密类型", EncryptUtils.class);
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
		}
		secureRandom.setSeed(keycode.getBytes());
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		return secretKey.getEncoded();
	}

	private static byte[] hexStr2byte(String hexStr) {
		if (hexStr == null || hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
