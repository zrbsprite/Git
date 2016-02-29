package com.jsprite.core.utils;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class EncryptUtils {

	private static final String KEY_TYPE_HMAC = "HmacMD5";
	
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
		byte[] temp = Base64.decodeBase64(base64Str.getBytes());
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
		return toBase64(src.getBytes());
	}

	/**
	 * 获取序列号
	 * @param str
	 * @return
	 */
	public static String getSerializeCode(String str){
		str = getMD5(str);
		String[] arrays = string2ArraysByLen(str);
		str = StringUtils.join(arrays, "-");
		return str;
	}
	
	public static String getHMACString(String src){
		String key = initStaticHMACKey();
		SecretKey secretKey = new SecretKeySpec(fromBase64(key).getBytes(), KEY_TYPE_HMAC);  
	    Mac mac;
		try {
			mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			byte[] bt = mac.doFinal();
			return byte2Hex(bt);
		} catch (NoSuchAlgorithmException e) {
			LogUtils.warn("不支持的加密类型", EncryptUtils.class);
		} catch (InvalidKeyException e) {
			LogUtils.warn("key错误", EncryptUtils.class);
		}
		return "";
	}
	
	public static String getRandomHMACString(String src){
		String key = initStaticHMACKey();
		SecretKey secretKey = new SecretKeySpec(fromBase64(key).getBytes(), KEY_TYPE_HMAC);  
	    Mac mac;
		try {
			mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			byte[] bt = mac.doFinal();
			BigInteger big = new BigInteger(bt);
			return big.toString(32).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			LogUtils.warn("不支持的加密类型", EncryptUtils.class);
		} catch (InvalidKeyException e) {
			LogUtils.warn("key错误", EncryptUtils.class);
		}
		return "";
	}
	
	@SuppressWarnings("unused")
	private static String initHMACKey(){
		String keyStr = "";
		try {
			KeyGenerator generator = KeyGenerator.getInstance(KEY_TYPE_HMAC);
			SecretKey key = generator.generateKey();
			keyStr = toBase64(key.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			LogUtils.warn("不支持的加密类型", EncryptUtils.class);
		}
		return keyStr;
	}
	
	private static String initStaticHMACKey(){
		return KEYCODE;
	}
	
	private static String toBase64(byte[] by){
		return new String(Base64.encodeBase64(by));
	}
	
	private static String[] string2ArraysByLen(String str){
		int size = str.length();
		int len = size/4;
		String[] arrays = new String[len];
		for(int j=0; j<len; j++){
			arrays[j] = str.substring(0,4);
			str = str.substring(4);
		}
		return arrays;
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
	
	public static void main(String[] args) {
		String result = getHMACString("zhangribo");
		System.out.println(result);
	}
}
