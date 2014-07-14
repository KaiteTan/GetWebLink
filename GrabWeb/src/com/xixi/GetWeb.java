package com.xixi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.JspTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class GetWeb {

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
	
	public final static String testFilePath="e:"+File.separator+"baidu.txt";  // File.separator
    //public final static String urlName="http://www.taobao.com/";
   
    public static void parser(String content) throws ParserException{
        Parser parser=new Parser(content);
        parser.setEncoding( parser.getEncoding());  //parser.getEncoding()

        NodeList nodeList=null;
         
        //  ע���ض���ǩ����  ����˵ĳ���ض��Ľڵ�
        NodeFilter titleFilter=new NodeClassFilter(TitleTag.class);
        NodeFilter metaFilter=new NodeClassFilter(MetaTag.class);
        NodeFilter textFilter=new NodeClassFilter(TextNode.class);
        NodeFilter tableFilter=new NodeClassFilter(TableTag.class);
        NodeFilter linkFilter=new NodeClassFilter(LinkTag.class);
        NodeFilter jspFilter=new NodeClassFilter(JspTag.class);
         
         
        //OrFilter����Եõ�����������õ��κνڵ����ͣ��ڵ�֮���ǻ�Ĺ�ϵ  ����ʵ�ַ���ΪsetPredicates()
        OrFilter lastFilter=new OrFilter();
        lastFilter.setPredicates(new NodeFilter[]{titleFilter,metaFilter,textFilter,tableFilter,linkFilter,jspFilter});
         
        nodeList=parser.parse(lastFilter);
         
        Node [] nodes=nodeList.toNodeArray();
        String line="";
        for(int i=0;i<nodes.length;i++){
            Node node=nodes[i];
            if(node instanceof TitleTag){   //�õ���ҳ�ı���
                TitleTag titlenode=(TitleTag)node;
                line=titlenode.getTitle();
                //System.out.println(line);
            }
            else if(node instanceof MetaTag) { //�õ�meta�е�content���ݣ�������ҳ�Ĺؼ��֡����롢������Ϣ�ȡ�
                MetaTag metaTag=(MetaTag)node;
                line=metaTag.getAttribute("content");  
                //System.out.println(line);
            }
             
            else if(node instanceof TextNode){  //�õ��ı�����
                TextNode textNode=(TextNode)node;
                line=textNode.getText();
                //System.out.println(line);
            }
            else if(node instanceof TableTag){   
                TableTag tableTag=(TableTag)node;
                line=tableTag.toPlainTextString();   //�õ���������
                //System.out.println(line);
                String width=tableTag.getAttribute("width");
                String height=tableTag.getAttribute("height");
                //System.out.println(width);
            }
            else if(node instanceof LinkTag){    //�õ���ҳ�е����Ӻ������ı�
                LinkTag linknode=(LinkTag)node;
                String linkText=linknode.getLinkText();
                String link=linknode.getLink();
                 
                System.out.println(linkText);
                //System.out.println(link);
            }
            else if(node instanceof JspTag){   //������ҳ�е�Javas��ASP�ȶ�̬�Ĵ���
                JspTag jspTag=(JspTag)node;
                line=jspTag.toString();
                System.out.println(line);
            }
             
        }
             
    }
    public static void main(String[] args) throws Exception {
        String content=getWeb();
        parser(content);
    }
 
}