package com.example.springboottest.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 */
public class AesEncryptTool {
	// AES-128-CBC加密模式，key需要为16位
	private static String key = "1q2w3e4R5T6Y7U8*";
	// 使用CBC模式，定义向量iv，增加加密算法的强度
	private static String initVector = "U8*1q2w3e4R5T6Y7";

	/**
	 * 对原文加密
	 *
	 * @param key
	 * @param initVector
	 * @param strSource
	 * @return
	 */
	private static String encrypt(String key, String initVector, String strSource) {
		String strResult = null;
		try {
			// 使用CBC模式，密码分组链接
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(strSource.getBytes("UTF-8"));
			strResult = Base64.encodeBase64String(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			strResult = null;
		}

		return strResult;
	}


	/**
	 * 对密文解密
	 *
	 * @param key
	 * @param initVector
	 * @param encrypted
	 * @return
	 */
	private static String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original, "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * 对原文加密
	 *
	 * @param key
	 * @param strSource
	 * @return
	 */
	private static String encrypt(String key, String strSource) {
		String strResult = null;
		try {
			// 使用CBC模式，密码分组链接
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

			byte[] encrypted = cipher.doFinal(strSource.getBytes("UTF-8"));
			strResult = Base64.encodeBase64String(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			strResult = null;
		}

		return strResult;
	}

	/**
	 * 对密文解密
	 *
	 * @param key
	 * @param encrypted
	 * @return
	 */
	private static String decrypt(String key, String encrypted) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original, "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * AES 加密<br>
	 * 传入需要加密的原文
	 *
	 * @return
	 */
	public static String encrypt(String strText) {
		return StringUtils.isEmpty(strText) ? strText : encrypt(key, initVector, strText);
	}

	/**
	 * AES 解密<br>
	 * 输入密文
	 *
	 * @param strCiphertext
	 * @return
	 */
	public static String decrypt(String strCiphertext) {
		return StringUtils.isEmpty(strCiphertext) ? strCiphertext : decrypt(key, initVector, strCiphertext);
	}

	/**
	 * 解密手机号
	 *
	 * @param strCiphertext
	 * @param isSuperAdmin
	 *            是否超管
	 * @return
	 */
	public static String decryptMobile(String strCiphertext, boolean isSuperAdmin) {
		if (StringUtils.isEmpty(strCiphertext) || strCiphertext.length() == 11 || !(strCiphertext.indexOf("=") > 0)) {
			return strCiphertext;
		}

		try {
			String mobileNo = decrypt(strCiphertext);

			return isSuperAdmin ? mobileNo : dealMobile(mobileNo);
		} catch (Exception e) {
			return strCiphertext;
		}
	}

	private static String dealMobile(String mobileNo) {
		if (StringUtils.isEmpty(mobileNo)) {
			return mobileNo;
		} else if (mobileNo.length() > 7) {
			return mobileNo.substring(0, 3) + mobileNo.substring(3, 7).replaceAll(".", "*")
					+ mobileNo.substring(7, mobileNo.length());
		} else if (mobileNo.length() > 4 && mobileNo.length() <= 7) {
			return mobileNo.substring(0, 4) + mobileNo.substring(4, mobileNo.length()).replaceAll(".", "*");
		} else {
			return mobileNo;
		}
	}

	/**
	 * 解密邮箱
	 *
	 * @param strCiphertext
	 * @param isSuperAdmin
	 *            是否超管
	 * @return
	 */
	public static String decryptEmail(String strCiphertext, boolean isSuperAdmin) {
		if (StringUtils.isEmpty(strCiphertext) || strCiphertext.indexOf("@") > 0) {
			return strCiphertext;
		}

		try {
			String email = decrypt(strCiphertext);

			return isSuperAdmin ? email
					: email.substring(0, 1) + email.substring(1, email.indexOf("@") - 1).replaceAll(".", "*")
							+ email.substring(email.indexOf("@") - 1, email.length());
		} catch (Exception e) {
			return strCiphertext;
		}

	}

	/**
	 * 解密地址
	 *
	 * @param strCiphertext
	 * @param isSuperAdmin
	 *            是否超管
	 * @return
	 */
	public static String decryptAddr(String strCiphertext, boolean isSuperAdmin) {
		try {
			String addr = decrypt(strCiphertext);

			if (StringUtils.isEmpty(addr) && !StringUtils.isEmpty(strCiphertext)) {
				return strCiphertext;
			}

			return isSuperAdmin ? addr
					: addr.substring(0, 1) + addr.substring(1, addr.length() - 1).replaceAll(".", "*")
							+ addr.substring(addr.length() - 1, addr.length());
		} catch (Exception e) {
			return strCiphertext;
		}
	}

	/**
	 * 隐藏地址
	 *
	 * @param addr
	 *            地址
	 * @param isSuperAdmin
	 *            是否超管
	 * @return
	 */
	public static String hideAddr(String addr, boolean isSuperAdmin) {
		return (StringUtils.isEmpty(addr) || isSuperAdmin) ? addr
				: addr.substring(0, 1) + addr.substring(1, addr.length() - 1).replaceAll(".", "*")
						+ addr.substring(addr.length() - 1, addr.length());
	}

	public static boolean isBase64(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		} else {
			if (str.length() % 4 != 0) {
				return false;
			}

			char[] strChars = str.toCharArray();
			for (char c:strChars) {
				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')
						|| c == '+' || c == '/' || c == '=') {
					continue;
				} else {
					return false;
				}
			}
			return true;
		}
	}

	public static void main(String[] args) {
		String str = "17521271445";

		System.out.println(str);

		// String encStr = encrypt(str);
		// System.out.println(encStr);
		System.out.println(decrypt(""));
		String encrypt = encrypt(str);
		System.out.println(encrypt);
		System.out.println(decrypt(encrypt));
		String encrypt1 = encrypt(key, str);
		System.out.println(encrypt1);
		System.out.println(decrypt(key,encrypt1));


	}
}
