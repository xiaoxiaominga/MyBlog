package com.oracledal;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Article;
import com.entity.Entity;
import com.idal.IArticle;
import com.oracledal.SQLHelper;
import com.utility.Log;
public class ArticleOperator implements IArticle {
    //����article���±���
	private final String INSERT_ARTICLE="INSERT INTO ARTICLE(TITLE,CONTENT,DATA,TYPE,COUNT,IMAGEURL,DEL) VALUES(?,?,TO_DATE(?,'yyyy-MM-dd hh24:mi:ss'),?,?,?,?)";
	private final String SELECT_ALL_ARTICLE="SELECT * FROM ARTICLE";
	private final String SELECT_ONE_ARTICLE="SELECT * FROM ARTICLE WHERE ARTISTID=?";
	private final String SELECT_RECOMMAND_ARTICLE="SELECT * FROM ARTICLE WHERE ROWNUM<=10";
    private final String SELECT_ARTICLES_BY_TYPE="SELECT * FROM ARTICLE WHERE TYPE=?";
	private final String DELETE_ARTICLE="UPDATE ARTICLE SET DEL=1 WHERE ARTISTID=?";
    @Override
	public boolean insert(Article article) {
		// TODO Auto-generated method stub
	    Object[] parms={article.getTitle(),article.getContent(),article.getDate().toString(),
	    		article.getType(),article.getCount(),article.getImageurl(),article.getDel()};
		int count=SQLHelper.ExecuteNonQuery(INSERT_ARTICLE, parms);
		if(count>0)
	    return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.idal.IArticle#getArticle(int)
	 * @Params articleid 
	 * ʹ�ò���id����ȡarticle����
	 */
	@Override
	public Article getArticle(int articleid) {
		// TODO Auto-generated method stub
		try{
		Object[] parms={articleid};
		Article article=null;
	    ResultSet set=SQLHelper.ExecuteResultSet(SELECT_ONE_ARTICLE, parms);
	    if(set.next())
	    article=new Article(set.getInt("ARTISTID"),set.getString("TITLE").trim(),set.getString("CONTENT").trim(),set.getString("DATA"),
	    		set.getString("TYPE").trim(),
	    		set.getInt("COUNT"),set.getString("IMAGEURL").trim(),set.getInt("del"));
		return article;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����oracledal����ArticleOperator���е�getArticle(INT)����");
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.idal.IArticle#getArticles()
	 * ��ȡ���е������б�
	 */
	@Override
	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		List<Article> list=new ArrayList<Article>();
		try{
			ResultSet set=SQLHelper.ExecuteResultSet(SELECT_ALL_ARTICLE, null);
			while(set.next())
			{
				Article article=new Article(set.getInt("ARTISTID"),set.getString("TITLE"),getClobString(set.getClob("CONTENT")).toString(),set.getString("DATA"),
	    		set.getString("TYPE"),
	    		set.getInt("COUNT"),set.getString("IMAGEURL"),set.getInt("del"));
				list.add(article);
			}
			return list;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����oracledal����ArticleOperator���е�getArticles()����");
		return list;
		}
		
	}
    private String getClobString(Clob clob)
    {
    	String relString="";
    	try{
    		Reader is=clob.getCharacterStream();//��ȡ�ַ���
    		BufferedReader br=new BufferedReader(is);
    		StringBuffer sbBuffer=new StringBuffer();
    		String string=br.readLine();
    		while(string!=null)
    		{
    			sbBuffer.append(string);
    			string=br.readLine();
    		}
    		relString=sbBuffer.toString();
    	}catch (Exception e) {
			// TODO: handle exception
    		relString=e.getMessage();
		}
    	return relString;
    }
	/* (non-Javadoc)
	 * @see com.idal.IArticle#getRecommendArticles()
	 * ��ȡ���µ������б�
	 */
	@Override
	public List<Article> getRecommendArticles() {
		// TODO Auto-generated method stub
		List<Article> list=new ArrayList<Article>();
		try{
			ResultSet set=SQLHelper.ExecuteResultSet(SELECT_RECOMMAND_ARTICLE, null);
			while(set.next())
			{
				Article article=new Article(set.getInt("ARTISTED"),set.getString("TITLE"),set.getString("CONTENT"),set.getString("DATA"),
	    		set.getString("TYPE"),
	    		set.getInt("COUNT"),set.getString("IMAGEURL"),set.getInt("del"));
				list.add(article);
			}
			return list;
		}catch (Exception e) {
			// TODO: handle exception
			Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����oracledal����ArticleOperator���е�getRecommandArticles()����");
		return list;
		}
		
	}
    @Override
	public List<Article> getArticlesByType(String type)
    {
    	List<Article> list=new ArrayList<Article>();
    	Object[] parms={type};
    	try{
    		ResultSet set=SQLHelper.ExecuteResultSet(SELECT_ARTICLES_BY_TYPE, parms);
    		while(set.next())
    		{
    			Article article=new Article(set.getInt("ARTISTID"),set.getString("TITLE").trim(),set.getString("CONTENT").trim(),set.getString("DATA"),
    		    		set.getString("TYPE").trim(),
    		    		set.getInt("COUNT"),set.getString("IMAGEURL").trim(),set.getInt("del"));
    			list.add(article);
    		}
    		return list;
    	}catch(Exception e)
    	{
    		
    		Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����oracledal����ArticleOperator���е�getArticle(INT)����");
    		return list;
    	}
	}

	@Override
	public boolean deleteArticle(int id) {
		// TODO Auto-generated method stub
		Object[] parms={id};
		try{
			int count=SQLHelper.ExecuteNonQuery(DELETE_ARTICLE, parms);
			if(count>0)
			{
				return true;
			}
			return false;
		}catch(Exception e){
			Log.writeToError("����ԭ��"+e.getMessage().toString()+"\r\n���󳡾�����oracledal����ArticleOperator���е�getArticle(INT)����");
			return false;
		}
		
	}
    
}
