package beans.vo.service;

import beans.vo.dao.BaseDao;
import beans.vo.hotel.ItripHoteIVO;
import cn.itrip.common.EmptyUtils;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.Date;
import java.util.List;

public class SearchHotelServiceImp implements SearchHotelService{

    public static String url="http://localhost:8003/solr/hotel/";

    private BaseDao<ItripHoteIVO> hotelBase=new BaseDao<ItripHoteIVO>(url);


    /**
     * @param cityId
     * @param count
     * @return
     */
    @Override
    public List<ItripHoteIVO> searchItripHotelListByHotCity(Integer cityId, Integer count) {
        SolrQuery query=new SolrQuery("*:*");
        if(EmptyUtils.isNotEmpty(cityId)){
            query.addFilterQuery("cityId:"+cityId);
        }else{
            return null;
        }
        List<ItripHoteIVO> ItripHotes=hotelBase.queryList(query,count,ItripHoteIVO.class);
        return ItripHotes;
    }

    @Override
    public List<ItripHoteIVO> searchItripHotelPage(Integer featureIds, Date checkInDate, Date checkOutDate, String destination, Integer hotelLevel, String keywords) {
        return null;
    }


}
