package parser;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWebString {

	//��ȡhtmlԴ���ļ������ַ�����ʽ���أ�ͬʱ�������ڱ���txt�ļ���
		public static String getWeb() throws Exception{
			String strUrl = "http://www.cnblogs.com/sl-shilong/articles/2860412.html";
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("GET");
			String str = null;
			
			if(conn.getResponseCode() == 200){  //���������ȡ����		
				InputStreamReader inRead = new InputStreamReader(conn.getInputStream(),"UTF-8");
			 	BufferedReader bufRead = new BufferedReader(inRead);
			 	
			 	StringBuffer strBuf = new StringBuffer();
			 	String line = "";
			 	while((line = bufRead.readLine()) != null){
			 		strBuf.append(line);
			 	}
			 	inRead.close();
			 	bufRead.close();
			 	
			 	str = strBuf.toString();  //StringBuffereת��ΪString����
			}

	//��htmlԴ��д�뱾��ϵͳ��txt�ļ�
			FileOutputStream fos = new FileOutputStream("e:/baidu.txt"); 
			byte[] str1 = str.getBytes();
			fos.write(str1);
		 	fos.close();
		 	
			return str;
		}
		
}
