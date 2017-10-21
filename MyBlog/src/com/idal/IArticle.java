package com.idal;
import com.entity.*;
import java.util.List;
/**
 * @author ���ڷ�
 *�ṩ����������в����Ľӿڡ�
 */

public interface IArticle {
	
     /**
     * @param article Ҫ�������¶��������
     * @return �Ƿ����ɹ�
     */
    public boolean insert(Article article);
    
    /**
     * @param articleid Ҫ��ȡ������id
     * @return �������¶���
     */
    public Article getArticle(int articleid);
    
    /**
     * @return �����������м���
     */
    public List<Article> getArticles();
    /**��ȡ�Ƽ�������
     * @return
     */
    public List<Article> getRecommendArticles();
    /**
     * @param type ���µ����
     * @return ���ظ�������������
     */
    public List<Article> getArticlesByType(String type);
    /**
     * @return true��ɾ���ɹ�     false:ɾ��ʧ�� 
     */
    public boolean deleteArticle(int id);
    
}
