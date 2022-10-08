package org.suNing.utli;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SuNingUtil {
	
	
//	public static void main(String[] args) {
//		System.out.println(generateFileName("jpg"));
//	}
	/**
	 * 执行生成文件名称
	 * @param extension  文件扩展名
	 * @return
	 */
	public static String generateFileName(String extension){
		StringBuffer sb = new StringBuffer();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//格式化时间
		
		Random random = new Random();
		int r = random.nextInt(10000);  // 生成随机数
		
		DecimalFormat decfor = new DecimalFormat("0000");  // 如果随机数小于 4位的话 用该对象布上
		if(extension != null){ // 把 时间 随机数 扩展名拼接成字符串
			sb.append(sdf.format(new Date())).append(decfor.format(r)).append(".").append(extension);
			return sb.toString();
		}
		return null;
		
	}
	
	
	
	
	/**
	 * 这是用来产生订单号的公共方法
	 * @return
	 */
	public static String generateRandom(){
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Random rand = new Random();
		int random = (int)(rand.nextDouble()*9000+1000);
		sb.append(sdf.format(new Date())).append(random);
		return sb.toString();

	}
	
	
	
	
	/**
	 * 将数组格式化成字符串方法
	 * @param str  传过来的数组
	 * @param cr   分隔符
	 * @return  返回拼接后的值
	 */
	public static String arrayToString(String[] str,String cr){
		StringBuffer sb = new StringBuffer();   //创建 拼接字符串对象
		
		if(str != null && str.length>0){//判断数组是否有值
			for (int i = 0; i < str.length; i++) {
				sb.append(str[i]).append(cr);  // 将数组拼接
			}
			sb.deleteCharAt(sb.length()-1);  // 将最后的字符删除
			return sb.toString();
		}
		return null;
	}

}
