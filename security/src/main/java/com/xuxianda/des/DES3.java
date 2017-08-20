package com.xuxianda.des;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 3重DES
 * Created by Xianda Xu on 2017/8/20.
 */
public class DES3 {

    private static String src = "xuxianda";

    public static void main(String[] args) throws Exception{
        jdk3DES();
    }

    /**
     * jdk3重DES
     */
    private static void jdk3DES() throws Exception{
        //生成key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        //keyGenerator.init(112);
        keyGenerator.init(new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();

        //key转换
        DESedeKeySpec desKeySpec = new DESedeKeySpec(encoded);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
        Key convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

        //加密
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
        byte[] results = cipher.doFinal(src.getBytes());
        System.out.println("jdk 3des encrpyt："+ Hex.encodeHexString(results));

        //解密操作
        cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
        byte[] bytes = cipher.doFinal(results);
        System.out.println("jdk 3des decrpyt："+new String(bytes));
    }

}
