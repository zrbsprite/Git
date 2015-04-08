package com.cmcc.slience.test.j2se;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

	private static final String KEYCODE = "Jsprit2014";

	/**
	 * 加密目标字符串
	 * 
	 * @param src
	 * @return 加密后的字符串
	 */
	public static String encrypt(String src) {

		try {
			byte[] myKey = getLocalKey(KEYCODE);
			SecretKeySpec key = new SecretKeySpec(myKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = src.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent); // 加密
			return byte2HexStr(result);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密字符串
	 * 
	 * @param src
	 * @return 解密后的字符串
	 */
	public static String decrypt(String src) {
		try {
			byte[] myKey = getLocalKey(KEYCODE);
			byte[] btext = hexStr2byte(src);
			SecretKeySpec key = new SecretKeySpec(myKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(btext); // 解密
			return new String(result, "utf-8");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
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
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(keycode.getBytes());
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * @param bytes
	 * @return
	 */
	private static String byte2HexStr(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase(Locale.getDefault()));
		}
		return sb.toString();
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

	public static void main(String[] args) {
		/*String str = "3DFBA30CC4FFBBD0D9EF95803ECD3D10";
		System.out.println(decrypt(str));*/
		
		String name = "zhangribo";
		System.out.println(encrypt(name));
		
		System.out.println(encrypt("123456"));
		System.out.println(decrypt("0E5CDD47FF6A4B66A14249530ABD4D46"));
	}

}