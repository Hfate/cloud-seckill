package com.hcq.seckill.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author admin
 */
public class AesUtil {
    /**
     * 加密key
     */

    private static final byte[] KEY = {(byte) 0xD1, (byte) 0xC0, (byte) 0xDE,
            (byte) 0x01, (byte) 0xDD, (byte) 0xC6, (byte) 0xF6, (byte) 0xC8,
            (byte) 0x16, (byte) 0x0E, (byte) 0x7D, (byte) 0xD4, (byte) 0xC0,
            (byte) 0xDE, (byte) 0x02, (byte) 0xDD};
    private static final byte[] IV = {(byte) 0xC6, (byte) 0xF6, (byte) 0xC9,
            (byte) 0x56, (byte) 0x0E, (byte) 0xD3, (byte) 0xC0, (byte) 0xDE,
            (byte) 0x01, (byte) 0xDD, (byte) 0x57, (byte) 0xC6, (byte) 0xF6,
            (byte) 0xC9, (byte) 0x56, (byte) 0x0E};
    /**
     * 默认转换模式
     */
    private static final String DEFAULT_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    /**
     * 默认使用AES算法
     */
    private static final String DEFAULT_ALGORITHM = "AES";
    /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "utf-8";

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param key     加密密匙
     * @return byte[]
     */
    public static byte[] encrypt(String content, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),
                    DEFAULT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(IV);
            byte[] byteContent = content.getBytes(DEFAULT_CHARSET);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            return cipher.doFinal(byteContent);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @param key     解密密钥
     * @return byte[]
     */
    public static byte[] decrypt(byte[] content, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),
                    DEFAULT_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content 带解密字节数组
     * @param key     解密的密匙
     * @return byte[]s
     */
    public static byte[] decrypt(byte[] content, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, DEFAULT_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param content 待加密的内容
     * @param key     加密key
     * @return byte[]
     */
    public static byte[] encrypt(String content, byte[] key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, DEFAULT_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV);
            Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
            byte[] byteContent = content.getBytes();
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            return cipher.doFinal(byteContent);
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将byte数组转换成16进制
     *
     * @param buff byte数组
     * @return String
     */
    public static String parseByte2HexStr(byte[] buff) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buff) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转换为byte数组
     *
     * @param hexStr 16进制字符串
     * @return byte[]
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() >> 1];
        for (int i = 0; i < hexStr.length() >> 1; i++) {
            int high = Integer.parseInt(hexStr.substring(i << 1, (i << 1) + 1), 16);
            int low = Integer.parseInt(hexStr.substring((i << 1) + 1, (i + 1) << 1),
                    16);
            result[i] = (byte) ((high << 4) + low);
        }
        return result;
    }

    /**
     * 解密字符串
     *
     * @param str 字符串
     * @return String
     */
    public static String decode(String str) {

        byte[] decryptFrom = parseHexStr2Byte(str);
        byte[] decryptResult = decrypt(decryptFrom, KEY);
        return new String(Objects.requireNonNull(decryptResult));
    }

    /**
     * 加密字符串
     *
     * @param str 待加密字符串
     * @return String
     */
    public static String encode(String str) {
        byte[] encryptResult = encrypt(str, KEY);
        return parseByte2HexStr(Objects.requireNonNull(encryptResult));
    }
}
