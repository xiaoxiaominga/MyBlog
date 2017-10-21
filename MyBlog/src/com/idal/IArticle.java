package com.idal;
import com.entity.*;
import java.util.List;
/**
 * @author 马腾飞
 *提供对文章类进行操作的接口。
 */

public interface IArticle {
	
     /**
     * @param article 要插入文章对象的数据
     * @return 是否插入成功
     */
    public boolean insert(Article article);
    
    /**
     * @param articleid 要获取的文章id
     * @return 返回文章对象
     */
    public Article getArticle(int articleid);
    
    /**
     * @return 返回文章所有集合
     */
    public List<Article> getArticles();
    /**获取推荐的文章
     * @return
     */
    public List<Article> getRecommendArticles();
    /**
     * @param type 文章的类别
     * @return 返回该类别的所有文章
     */
    public List<Article> getArticlesByType(String type);
    /**
     * @return true：删除成功     false:删除失败 
     */
    public boolean deleteArticle(int id);
    
}
