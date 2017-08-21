package com.xuxianda.aes;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by Xianda Xu on 2017/08/21 11:19.
 */
public class AES {

    private static String src = "xuxianda";

    public static void main(String[] args) throws Exception{
        jdkAES();
    }

    public static void jdkAES() throws Exception{
        //生成key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        //key转换
        Key key = new SecretKeySpec(keyBytes,"AES");

        //加密
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println("jdk es encrypt："+ Base64.encodeBase64String(result));

        //解密
        cipher.init(Cipher.DECRYPT_MODE,key);
        result = cipher.doFinal(result);
        System.out.println("jdk es decrypt："+new String(result));
    }

}
