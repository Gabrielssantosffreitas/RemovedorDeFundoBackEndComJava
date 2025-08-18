package com.gabriel.SendPngNotBackground.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedImage img = ImageIO.read(new File("C:\\Users\\PICHAU\\Documents\\SendPngNotBackground\\SendPngNotBackground\\src\\main\\java\\com\\gabriel\\SendPngNotBackground\\test\\triste.png"));
        ByteArrayOutputStream byteImg = new ByteArrayOutputStream();
        ImageIO.write(img,"png",byteImg);
        byte[] bytesImg = byteImg.toByteArray();
        String base64Img = Base64.getEncoder().encodeToString(bytesImg);
        System.out.println(base64Img);
    }
}
