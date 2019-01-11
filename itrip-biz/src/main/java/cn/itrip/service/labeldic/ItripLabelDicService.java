package cn.itrip.service.labeldic;
import cn.itrip.beans.pojo.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import cn.itrip.beans.vo.comment.ItripingCommentVO;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import cn.itrip.beans.vo.order.ItripListHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalHotelOrderVO;
import cn.itrip.beans.vo.order.PreAddOrderVO;
import cn.itrip.common.Page;
import org.apache.ibatis.annotations.Param;

/**
* Created by shang-pc on 2015/11/7.
*/
public interface ItripLabelDicService {

    //查询酒店视频信息
    public ItripLabelDic getvideodesc(Long id)throws Exception;

    /*    public               queryhoteldetails()throws Exception;*/

    //查询酒店房型的图片
    public List<ItripImageVO> getIHotelgetimg(@Param(value = "hotelId") Integer id)throws Exception;

    public ItripHotel dicListByqueryhotelpolicy(Long id)throws Exception;

    public List<ItripSearchFacilitiesHotelVO> getItripLabelDicByIdfacilities(Long id)throws Exception;

    ItripHotel getItripHotel(Long id);

    ItripHotel getItripHotelone(Long id);

    ItripHotel getItripHoteltwo(Long id);

    public List<PreAddOrderVO> getloadPreAddOrderVO(Long hotelId, Long roomId, Date checkInDate,Date checkOutDate,Integer count,String hotelName,Integer store,BigDecimal price);

    //获取个人订单信息
    public List<ItripPersonalHotelOrderVO> getpersonalorderinfo(@Param(value = "id")Long id);



    //评分
    public ItripComment gethotelscoreId(@Param(value = "hotelId") Long hotelId)throws Exception;

    public List<ItripSearchPolicyHotelVO> getItripLabelDicByIdfacilities1(Long id)throws Exception;

    public ItripLabelDic getItripLabelDicById(Long id)throws Exception;

    public List<ItripSearchDetailsHotelVO> getItripMapHotel(@Param(value = "hotelId") Integer hotelId)throws Exception;

    public List<ItripSearchCommentVO> getcommentlist(Long hotelId, Integer isHavingImg, Integer isOk, Integer pageSize, Integer pageNo);

    public List<ItripImageVO> getCommentImg(Map<String, Object> param);

    public List<ItripLabelDic>	getItripLabelDicListByMap(Map<String, Object> param)throws Exception;

    public List<ItripLabelDic>	queryhotelroombed(Map<String, Object> param)throws Exception;

    public Integer getItripLabelDicCountByMap(Map<String, Object> param)throws Exception;

    public Integer itriptxAddItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

    public Integer itriptxModifyItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

    public Integer itriptxDeleteItripLabelDicById(Long id)throws Exception;

    public Page<ItripLabelDic> queryItripLabelDicPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize)throws Exception;

    public ItripingCommentVO getcount(@Param(value = "hotelId") Long hotelId);

    public ItripHotelDescVO gethoteldesc(@Param(value = "id") Long id)throws Exception;

    //查询旅游类型列表
    public List<ItripLabelDic> gettraveltype(Map<String, Object> param);

    //查询酒店图片
    public List<ItripImageVO> hotelgetimg(Long id);

    /**
     * 个人酒店分页订单
     */
    public Page<ItripHotelOrder> getpersonalOrderDingdan(Map<String, Object> param,Integer pageNo,Integer pageSize);

    /**
     * 根据parentId查询数据字典
     * @param parentId
     * @return
     * @throws Exception
     * add by hanlu 2017-5-11
     */
    public List<ItripLabelDicVO> getItripLabelDicByParentId(Long parentId)throws Exception;


}
