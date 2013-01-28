
 package com.autoUpdate;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.encoder.ByteMatrix;
//import javax.servlet.http.HttpServletResponse;
 public class Config 
 {
	 private static final int BLACK = 0xff000000;
     private static final int WHITE = 0xFFFFFFFF;
	 public static void main(String []args)throws Exception{   
		 //生成二维码
		 showTowCode("不@#$%^&*(),.~!-=+_\\||';.332胆小鬼dfdfe");
		 //解析二维码
		 decode();
	 }
	 /**
	  * 生成二维码
	  * @param content
	  * @return
	  */
	 public static String showTowCode(String content){
		//HttpServletResponse response;
		try {
			System.out.println("生成的内容为:"+content);
			int width=200;
			int height=200;
			String format = "png";
			Hashtable htab=new Hashtable();
			htab.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitmartix=new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, width, height,htab);
			File outputFile=new File("new.png");
			MatrixToImageWriter.writeToFile(bitmartix, format, outputFile);
			//ImageIO.write(image, "png", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}   
		return null;
	}
	 /**
	  * 解析二维码
	  */
	public static void decode(){
		try{
			Reader reader = new MultiFormatReader(); 
			String imgPath = "new.png"; 
			File file = new File(imgPath); 
			BufferedImage image; 
			image = ImageIO.read(file); 
			if (image == null) { 
				System.out.println("没有你要解析的二维码"); 
			} 
			LuminanceSource source = new BufferedImageLuminanceSource(image); 
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source)); 
			Result result; 
			Hashtable hints= new Hashtable(); 
			hints.put(DecodeHintType.CHARACTER_SET, "utf-8"); 
			//解码设置编码方式为：utf-8，
			result = new MultiFormatReader().decode(bitmap,hints); 
			String resultStr = result.getText(); 
			System.out.println("解析后内容为:"+resultStr); 
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
 
}