package com.xuxianda.pbe;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Created by Xianda Xu on 2017/08/21 14:37.
 */
public class PBE {

    private static String password = "xuxianda";

    public static void main(String[] args) throws Exception{
        jdkPBE();
    }

    public static void jdkPBE() throws Exception{
        //初始化盐
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(8);

        //口令与秘钥
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
        Key key = factory.generateSecret(pbeKeySpec);

        //加密
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,100);
        Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
        cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
        byte[] result = cipher.doFinal(password.getBytes());
        System.out.println("jdk pbe encrypt："+ Base64.encodeBase64String(result));

        //解密操作
        cipher.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
        result = cipher.doFinal(result);
        System.out.println("jdk pbe decrypt："+ new String(result));
    }

}
