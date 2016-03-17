package com.cmcc.slience.senior.base.encryption;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * ������Password-based encryption������������ܣ��� ���ص����ڿ������û��Լ��ƹܣ��������κ�����ý�壻
 * ������������������ǽ����Σ��Ӵն��ؼ��ܵȷ�����֤���ݵİ�ȫ�ԡ���һ�ּ��ļ��ܷ�ʽ�� <br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��4��9������11:21:25 <br>
 * <br/>
 */
public class PBE {

	public static final String ALGORITHM = "PBEWITHMD5andDES";
	
	public static byte[] initSale() {
		byte[] salt = new byte[8];
		Random random = new Random();
		random.nextBytes(salt);
		return salt;
	}

	/**
	 * ת����Կ<br>
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(String password) throws Exception {
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);

		return secretKey;
	}

	/**
	 * ����
	 * 
	 * @param data ����
	 * @param password ����
	 * @param salt  ��
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String password, byte[] salt)
			throws Exception {

		Key key = toKey(password);

		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

		return cipher.doFinal(data);

	}

	/**
	 * ����
	 * @param data   ����
	 * @param password  ����
	 * @param salt  ��
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String password, byte[] salt) throws Exception {
		Key key = toKey(password);
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		return cipher.doFinal(data);

	}
}
