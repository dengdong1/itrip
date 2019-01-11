import beans.vo.hotel.Hotel;
import beans.vo.hotel.ItripHoteIVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

public class Test {
    public static String url = "http://localhost:8003/solr/hotel/";
    public static void main(String[] args) {
        //初始化HttpSolrClient
        HttpSolrClient httpSolrClient = new HttpSolrClient(url);
        // 设置响应解析器
        httpSolrClient.setParser(new XMLResponseParser());
        // 建立连接的最长时间
        httpSolrClient.setConnectionTimeout(500);


        //初始化SolrQuery 模糊查找(SolrQuery query = new SolrQuery("address:天安门");)
        SolrQuery query = new SolrQuery("*:*");
        //设置fq  精确查找
        //query.addFilterQuery();
        query.setSort("id", SolrQuery.ORDER.desc);
        //从第一条开始显示
        query.setStart(0);
        //一页显示多少条
        query.setRows(10);

        QueryResponse queryResponse = null;
        try {
            queryResponse = httpSolrClient.query(query);
            List<Hotel> list = queryResponse.getBeans(Hotel.class);
            for (Hotel hotel : list) {
                System.out.println(hotel.getId() + " " + hotel.getAddress());
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
