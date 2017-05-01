/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ironchain.common.kits;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.Validate;

/**
 * 支持SHA-1/MD5消息摘要的工具类.
 * 
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 * 
 * @author zheng xin
 */
public class DigestKit {

	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";

	private static final String AES = "AES";
	private static final String AES_CBC = "AES/CBC/PKCS5Padding";
	private static final String DES = "DES";
	private static final String DES_CBC = "DES/CBC/PKCS5Padding";

	private static final String HMACSHA1 = "HmacSHA1";

	private static final int DEFAULT_HMACSHA1_KEYSIZE = 160; // RFC2401
	private static final int DEFAULT_AES_KEYSIZE = 128;
	private static final int DEFAULT_IVSIZE = 16;

	private static SecureRandom random = new SecureRandom();

	/**
	 * 对输入字符串进行sha1散列.
	 */
	public static byte[] sha1(byte[] input) {
		return digest(input, SHA1, null, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt) {
		return digest(input, SHA1, salt, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
		return digest(input, SHA1, salt, iterations);
	}

	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 */
	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw ExceptionKit.unchecked(e);
		}
	}

	/**
	 * 生成随机的Byte[]作为salt.
	 * 
	 * @param numBytes
	 *            byte数组的大小
	 */
	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	/**
	 * 对文件进行md5散列.
	 */
	public static byte[] md5(InputStream input) throws IOException {
		return digest(input, MD5);
	}

	/**
	 * 对文件进行sha1散列.
	 */
	public static byte[] sha1(InputStream input) throws IOException {
		return digest(input, SHA1);
	}

	private static byte[] digest(InputStream input, String algorithm) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 8 * 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return messageDigest.digest();
		} catch (GeneralSecurityException e) {
			throw ExceptionKit.unchecked(e);
		}
	}

	// -- HMAC-SHA1 funciton --//
	/**
	 * 使用HMAC-SHA1进行消息签名, 返回字节数组,长度为20字节.
	 * 
	 * @param input
	 *            原始输入字符数组
	 * @param key
	 *            HMAC-SHA1密钥
	 */
	public static byte[] hmacSha1(byte[] input, byte[] key) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, HMACSHA1);
			Mac mac = Mac.getInstance(HMACSHA1);
			mac.init(secretKey);
			return mac.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 校验HMAC-SHA1签名是否正确.
	 * 
	 * @param expected
	 *            已存在的签名
	 * @param input
	 *            原始输入字符串
	 * @param key
	 *            密钥
	 */
	public static boolean isMacValid(byte[] expected, byte[] input, byte[] key) {
		byte[] actual = hmacSha1(input, key);
		return Arrays.equals(expected, actual);
	}

	/**
	 * 生成HMAC-SHA1密钥,返回字节数组,长度为160位(20字节). HMAC-SHA1算法对密钥无特殊要求,
	 * RFC2401建议最少长度为160位(20字节).
	 */
	public static byte[] generateHmacSha1Key() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(HMACSHA1);
			keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	// -- AES funciton --//
	/**
	 * 使用AES加密原始字符串.
	 * 
	 * @param input
	 *            原始输入字符数组
	 * @param key
	 *            符合AES要求的密钥
	 */
	public static byte[] aesEncrypt(byte[] input, byte[] key) {
		return cryptos(input, key, null, Cipher.ENCRYPT_MODE, AES, AES);
	}

	/**
	 * 使用AES加密原始字符串.
	 * 
	 * @param input
	 *            原始输入字符
	 * @param key
	 *            符合AES要求的密钥
	 */
	public static String aesEncrypt(String input, String key) {
		Charset charset = Charset.forName("UTF-8");
		return Base64.getEncoder().encodeToString(
				aesEncrypt(input.getBytes(charset), key.getBytes(charset)));
	}

	/**
	 * 使用AES加密原始字符串.
	 * 
	 * @param input
	 *            原始输入字符数组
	 * @param key
	 *            符合AES要求的密钥
	 * @param iv
	 *            初始向量
	 */
	public static byte[] aesEncrypt(byte[] input, byte[] key, byte[] iv) {
		return cryptos(input, key, iv, Cipher.ENCRYPT_MODE, AES, AES_CBC);
	}

	/**
	 * 使用AES解密字符串, 返回原始字符串.
	 * 
	 * @param input
	 *            Hex编码的加密字符串
	 * @param key
	 *            符合AES要求的密钥
	 */
	public static byte[] aesDecrypt(byte[] input, byte[] key) {
		return cryptos(input, key, null, Cipher.DECRYPT_MODE, AES, AES);
	}
	
	/**
	 * 使用AES解密字符串, 返回原始字符串.
	 * 
	 * @param input
	 *            Hex编码的加密字符串
	 * @param key
	 *            符合AES要求的密钥
	 */
	public static String aesDecrypt(String input, String key) {
		byte[] decryptResult = aesDecrypt(Base64.getDecoder().decode(input), key.getBytes(Charset.forName("UTF-8")));
		return new String(decryptResult, Charset.forName("UTF-8"));
	}

	/**
	 * 使用AES解密字符串, 返回原始字符串.
	 * 
	 * @param input
	 *            Hex编码的加密字符串
	 * @param key
	 *            符合AES要求的密钥
	 * @param iv
	 *            初始向量
	 */
	public static byte[] aesDecrypt(byte[] input, byte[] key, byte[] iv) {
		return cryptos(input, key, iv, Cipher.DECRYPT_MODE, AES, AES_CBC);
	}

	/**
	 * 使用AES/DES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
	 * 
	 * @param input
	 *            原始字节数组
	 * @param key
	 *            符合AES要求的密钥
	 * @param iv
	 *            初始向量
	 * @param mode
	 *            Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
	 * @param keyMode
	 *            加密key的方式
	 * @param encryptMode
	 *            加密方式
	 */
	private static byte[] cryptos(byte[] input, byte[] key, byte[] iv, int mode, String keyMode, String encryptMode) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, keyMode);
			Cipher cipher = Cipher.getInstance(encryptMode);
			if (iv != null) {
				IvParameterSpec ivSpec = new IvParameterSpec(iv);
				cipher.init(mode, secretKey, ivSpec);
			} else {
				cipher.init(mode, secretKey);
			}
			return cipher.doFinal(input);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成AES密钥,返回字节数组, 默认长度为128位(16字节).
	 */
	public static byte[] generateAesKey() {
		return generateAesKey(DEFAULT_AES_KEYSIZE);
	}

	/**
	 * 生成AES密钥,可选长度为128,192,256位.
	 */
	public static byte[] generateAesKey(int keysize) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
			keyGenerator.init(keysize);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
	 */
	public static byte[] generateIV() {
		byte[] bytes = new byte[DEFAULT_IVSIZE];
		random.nextBytes(bytes);
		return bytes;
	}

	// -- DES funciton --//
	/**
	 * 使用DES加密原始字符串.
	 * 
	 * @param input
	 *            原始输入字符数组
	 * @param key
	 *            符合AES要求的密钥
	 */
	public static byte[] desEncrypt(byte[] input, byte[] key) {
		return cryptos(input, key, null, Cipher.ENCRYPT_MODE, DES, DES);
	}

	/**
	 * 使用DES解密字符串, 返回原始字符串.
	 * 
	 * @param input
	 *            Hex编码的加密字符串
	 * @param key
	 *            符合AES要求的密钥
	 */
	public static String desDecrypt(byte[] input, byte[] key) {
		byte[] decryptResult = cryptos(input, key, null, Cipher.DECRYPT_MODE, DES, DES);
		return new String(decryptResult, Charset.forName("UTF-8"));
	}

	/**
	 * 使用DES加密原始字符串.
	 * 
	 * @param input
	 *            原始输入字符数组
	 * @param key
	 *            符合AES要求的密钥
	 * @param iv
	 *            初始向量
	 */
	public static byte[] desEncrypt(byte[] input, byte[] key, byte[] iv) {
		return cryptos(input, key, iv, Cipher.ENCRYPT_MODE, DES, DES_CBC);
	}

	/**
	 * 使用DES解密字符串, 返回原始字符串.
	 * 
	 * @param input
	 *            Hex编码的加密字符串
	 * @param key
	 *            符合AES要求的密钥
	 * @param iv
	 *            初始向量
	 */
	public static String desDecrypt(byte[] input, byte[] key, byte[] iv) {
		byte[] decryptResult = cryptos(input, iv, null, Cipher.DECRYPT_MODE, DES, DES_CBC);
		return new String(decryptResult, Charset.forName("UTF-8"));
	}

	/**
	 * 使用DES加密原始字符串.
	 * 
	 * @param input
	 *            原始输入字符
	 * @param key
	 *            符合AES要求的密钥
	 */
	public static String desEncrypt(String input, String key) {
		Charset charset = Charset.forName("UTF-8");
		return Base64.getEncoder().encodeToString(
				desEncrypt(input.getBytes(charset), key.getBytes(charset)));
	}

	/**
	 * 使用AES解密字符串, 返回原始字符串.
	 * 
	 * @param input
	 *            Hex编码的加密字符串
	 * @param key
	 *            符合AES要求的密钥
	 */
	public static String desDecrypt(String input, String key) {
		return desDecrypt(Base64.getDecoder().decode(input), key.getBytes(Charset.forName("UTF-8")));
	}
	
}
