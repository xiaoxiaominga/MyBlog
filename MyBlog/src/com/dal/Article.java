package com.dal;
import com.idal.*;
import com.oracledal.ArticleOperator;
import com.utility.Log;
/**
 * @author ���ڷ�
 * �����½��в�����dal��
 */
public class Article {
    /**
     * @return  ����һ��articleoperator��ʵ����ͨ��IArticle�ӿ�
     * ����
     */
    public static IArticle createIArticle()
    {
    	try {
			Class<?> class1= Class.forName("com.oracledal.ArticleOperator");
		    try {
				IArticle articleOperator=(ArticleOperator)class1.newInstance();
			    return articleOperator;
		    } catch (InstantiationException e) {
				// TODO Auto-generated catch block
		    	Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����dal����Article���е�createIArticle()����");
				return null;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����dal����Article���е�createIArticle()����");
			    return null;
			}
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����dal����Article���е�createIArticle()����");
			return null;
		}
    }
}
