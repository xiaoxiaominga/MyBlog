package com.bll;
import java.util.List;

import com.entity.*;
import com.idal.*;
public class ArticleGet {
     /**
     * @param articleid 文章的id
     * @return 获取特定文章id的文章
     */
    public  Article getArticle(int articleid)
     {
    	 IArticle article=com.dal.Article.createIArticle();
    	 Article article2= article.getArticle(articleid);
         return article2;
     }
    /**
     * @param article，插入新创建文章
     * @return
     */
    public boolean insertArticle(Article article)
    {
    	//创建IArtitcle的实例
    	IArticle iarticle=com.dal.Article.createIArticle();
       return iarticle.insert(article);
    }
    /**
     * @return 返回所有的文章
     */
    public List<Article> getAllArticles()
    {
    	//创建接口的实例
    	IArticle article=com.dal.Article.createIArticle();
        return article.getArticles();
    }
    /**
     * @param type 要查找的文章type类型
     * @return 返回该type类型的所有文章
     */
    public List<Article> getArticlesByType(String type)
    {
    	IArticle article=com.dal.Article.createIArticle();
    	return  article.getArticlesByType(type);
    }
    /**
     * @return 返回推荐的文章列表，，，算法需要更新
     */
    public List<Article> getRecommandedArticles()
    {
    	IArticle article=com.dal.Article.createIArticle();
    	return  article.getRecommendArticles();
    }
    /**
     * @param id 要删除文章的id
     * @return 返回是否删除成功
     */
    public boolean deleteArticle(int id)
    {
    	IArticle article=com.dal.Article.createIArticle();
    	return  article.deleteArticle(id);
    }
}
