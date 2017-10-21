package com.bll;
import java.util.List;

import com.entity.*;
import com.idal.*;
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
    public List<Article> getAllArticles()
    {
    	//�����ӿڵ�ʵ��
    	IArticle article=com.dal.Article.createIArticle();
        return article.getArticles();
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
