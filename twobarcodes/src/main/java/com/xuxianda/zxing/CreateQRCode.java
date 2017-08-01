package com.xuxianda.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xianda Xu on 2017/7/30.\
 * about:如何生成二维码
 */
public class CreateQRCode {
    public static void main(String[] args) throws Exception{
        int width = 300;
        int height = 300;
        String format  = "png";
        String content = "脆虾";

        //定义二维码参数
        Map map = new HashMap();
        map.put(EncodeHintType.CHARACTER_SET,"utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        map.put(EncodeHintType.MARGIN,2);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, map);

        File file = new File("D:/image/encode.png");
        if(!file.exists()){
            file.mkdirs();
        }
        MatrixToImageWriter.writeToPath(bitMatrix,format,file.toPath());
    }
}
