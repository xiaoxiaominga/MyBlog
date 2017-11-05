package com.bll;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;
import com.idal.*;
import com.utility.JsonUtil;
public class ArticleGet {
     /**
     * @param articleid ���µ�id
     * @return ��ȡ�ض�����id������
     */
    public  Article getArticle(int articleid)
     {
    	 IArticle article=com.dal.Article.createIArticle();
    	 Article article2= article.getArticle(articleid);
         return article2;
     }
    /**
     * @param article�������´�������
     * @return
     */
    public boolean insertArticle(Article article)
    {
    	//����IArtitcle��ʵ��
    	IArticle iarticle=com.dal.Article.createIArticle();
        return iarticle.insert(article);
    }
    /**
     * @return �������е�����
     */
    public String getAllArticles()
    {
    	//�����ӿڵ�ʵ��
    	IArticle article=com.dal.Article.createIArticle();
    	List<Article> list=article.getArticles();
        List<Entity> entity=new ArrayList<Entity>();
    	for(Article article2:list)
        {
        	entity.add(article2);
        }
    	String sendString=JsonUtil.listToArray(entity);
    	return sendString;
    }
    /**
     * @param type Ҫ���ҵ�����type����
     * @return ���ظ�type���͵���������
     */
    public List<Article> getArticlesByType(String type)
    {
    	IArticle article=com.dal.Article.createIArticle();
    	return  article.getArticlesByType(type);
    }
    /**
     * @return �����Ƽ��������б������㷨��Ҫ����
     */
    public List<Article> getRecommandedArticles()
    {
    	IArticle article=com.dal.Article.createIArticle();
    	return  article.getRecommendArticles();
    }
    /**
     * @param id Ҫɾ�����µ�id
     * @return �����Ƿ�ɾ���ɹ�
     */
    public boolean deleteArticle(int id)
    {
    	IArticle article=com.dal.Article.createIArticle();
    	return  article.deleteArticle(id);
    }
}
