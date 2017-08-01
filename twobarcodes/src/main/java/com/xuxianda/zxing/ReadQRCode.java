package com.xuxianda.zxing;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xianda Xu on 2017/8/1.
 */
public class ReadQRCode {
    public static void main(String[] args) throws Exception {
        MultiFormatReader multiFormatReader = new MultiFormatReader();

        File file = new File("D:/image/encode.png");

        BufferedImage read = ImageIO.read(file);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(read)));

        Map map = new HashMap();
        map.put(EncodeHintType.CHARACTER_SET,"utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        Result result = multiFormatReader.decode(binaryBitmap,map);

        System.out.println(result.toString());
    }
}
