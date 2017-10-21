package com.dal;
import com.idal.*;
import com.oracledal.ArticleOperator;
import com.utility.Log;
/**
 * @author 马腾飞
 * 对文章进行操作的dal类
 */
public class Article {
    /**
     * @return  返回一个articleoperator的实例，通过IArticle接口
     * 调用
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
		    	Log.writeToError("错误原因："+e.getMessage().toString()+"\r\n错误场景：在dal包中Article类中的createIArticle()方法");
				return null;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				Log.writeToError("错误原因："+e.getMessage().toString()+"\r\n错误场景：在dal包中Article类中的createIArticle()方法");
			    return null;
			}
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Log.writeToError("错误原因："+e.getMessage().toString()+"\r\n错误场景：在dal包中Article类中的createIArticle()方法");
			return null;
		}
    }
}
