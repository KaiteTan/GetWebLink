package com.search.spider;

public class UrlDataHanding implements Runnable  
{  
    /**  
     * ���ض�Ӧҳ�沢������ҳ���Ӧ��URL����δ���ʶ����С�  
     * @param url  
     */ 
    public void dataHanding(String url)  
    {  
    	//��������ҳ���url����ȡҳ������
            HrefOfPage.getHrefOfContent(DownloadPage.getContentFormUrl(url));  
    }  
          
    public void run()  
    {  
    	//urlQueue���в�Ϊ�գ�����ú������Ӹö����е�ȡurl����
        while(!UrlQueue.isEmpty())  
        {  
           dataHanding(UrlQueue.outElem());  
        }  
    }  
} 