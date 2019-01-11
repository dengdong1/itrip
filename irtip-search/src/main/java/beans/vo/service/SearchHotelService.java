package beans.vo.service;

import beans.vo.hotel.ItripHoteIVO;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.Date;
import java.util.List;


public interface SearchHotelService {

    //根据热门城市查询6个酒店
    public List<ItripHoteIVO> searchItripHotelListByHotCity(Integer cityId, Integer count);

    //搜索酒店（分页）
    public List<ItripHoteIVO> searchItripHotelPage(Integer featureIds, Date checkInDate,Date checkOutDate,String destination,Integer hotelLevel,String keywords);

}
