package com.xuxianda.des;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * @description 对称加密：加密秘钥和解密秘钥相同
 * Created by Xianda Xu on 2017/8/20.
 */
public class DES {

    private static String src = "xuxianda";

    public static void main(String[] args) throws Exception{
        jdkDES();
        bcDES();
    }

    public static void jdkDES() throws Exception{
        //生成key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();

        //key转换
        DESKeySpec desKeySpec = new DESKeySpec(encoded);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        Key convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

        //加密
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
        byte[] results = cipher.doFinal(src.getBytes());
        System.out.println("jdk des encrpyt："+ Hex.encodeHexString(results));

        //解密操作
        cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
        byte[] bytes = cipher.doFinal(results);
        System.out.println("jdk des decrpyt："+new String(bytes));
    }

    public static void bcDES() throws Exception{
        Security.addProvider(new BouncyCastleProvider());

        //生成key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();

        //key转换
        DESKeySpec desKeySpec = new DESKeySpec(encoded);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        Key convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);

        //加密
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
        byte[] results = cipher.doFinal(src.getBytes());
        System.out.println("jdk des encrpyt："+ Hex.encodeHexString(results));

        //解密操作
        cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
        byte[] bytes = cipher.doFinal(results);
        System.out.println("jdk des decrpyt："+new String(bytes));
    }

}
