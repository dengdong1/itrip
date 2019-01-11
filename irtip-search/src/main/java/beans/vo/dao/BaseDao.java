package beans.vo.dao;

import cn.itrip.common.Constants;
import cn.itrip.common.EmptyUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

public class BaseDao<T>{
    private HttpSolrClient httpSolrClient=null;

    private QueryResponse queryResponse=null;

    //SolrClient实现，通过HTTP直接与Solr服务器进行通信
    public BaseDao(String url){
        //初始化HttpSolrClient
        httpSolrClient=new HttpSolrClient(url);
        //设置响应解析器
        httpSolrClient.setParser(new XMLResponseParser());
        //建立连接最长时间
        httpSolrClient.setConnectionTimeout(500);
    }

    public List<T> queryList(SolrQuery query, Integer count, Class clazz){
        List<T> list=null;
        //设置一下刚开始的页面
        query.setStart(0);
        query.setRows(EmptyUtils.isEmpty(count) ? Constants.DEFAULT_PAGE_SIZE : count);
        try{
            queryResponse=httpSolrClient.query(query);
            list=queryResponse.getBeans(clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



}
