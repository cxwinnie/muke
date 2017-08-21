package com.xuxianda.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by Xianda Xu on 2017/8/21.
 */
public class Base64 {

    private static String src = "xuxianda";

    public static void main(String[] args) throws Exception {
        jdkBase64();
    }

    public static void jdkBase64() throws Exception {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(src.getBytes());
        System.out.println(encode);

        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(encode);
        System.out.println(new String(bytes));
    }

}
