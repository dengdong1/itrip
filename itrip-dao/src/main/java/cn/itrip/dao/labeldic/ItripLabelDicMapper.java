package cn.itrip.dao.labeldic;
import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.*;
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
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ItripLabelDicMapper {

	public ItripLabelDic getItripLabelDicById(@Param(value = "id") Long id)throws Exception;

	public List<ItripLabelDic>	getItripLabelDicListByMap(Map<String, Object> param)throws Exception;

	public ItripHotel getItripHotel(Long id);

	public ItripHotel getItripHotelone(Long id);

	public ItripHotel getItripHoteltwo(Long id);

	public List<ItripSearchFacilitiesHotelVO> dicListByMapqueryHotelfacilities(@Param(value = "id") Long id)throws Exception;

	public List<ItripSearchPolicyHotelVO> dicListByMapqueryHotelfacilities1(@Param(value = "id") Long id)throws Exception;

	public ItripHotel dicListByqueryhotelpolicy(@Param(value = "id") Long id)throws Exception;

	public ItripComment gethotelscoreId(@Param(value = "hotelId") Long hotelId)throws Exception;

	//查询所有床xing
	public List<ItripLabelDic> queryhotelroombed(Map<String, Object> param)throws Exception;

	//查询酒店房型的图片
	public List<ItripImageVO> getIHotelgetimg(@Param(value = "roomBedTypeId") Integer id)throws Exception;

	//查询酒店描述和特色
	public List<ItripSearchDetailsHotelVO> getItripMapHotel(@Param(value = "hotelId")Integer type)throws Exception;
	//预生成订单
	public List<PreAddOrderVO> getloadPreAddOrderVO(Long hotelId, Long roomId, Date checkInDate, Date checkOutDate, Integer count,String hotelName,Integer store,BigDecimal price);
    //添加订单


	//获取个人订单信息
	public List<ItripPersonalHotelOrderVO> getpersonalorderinfo(@Param(value = "id")Long id);

	//查看酒店评论
	public ItripHotelDescVO gethoteldesc(@Param(value = "id") Long id)throws Exception;

	public List<ItripImageVO> getCommentImg(Map<String, Object> param);

	public Integer getItripLabelDicCountByMap(Map<String, Object> param)throws Exception;

	public List<ItripSearchCommentVO> getcommentlist(Long hotelId,Integer isHavingImg,Integer isOk,Integer pageSize,Integer pageNo);

    public ItripingCommentVO getcount(@Param(value = "hotelId") Long hotelId);

    public Integer insertItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

	public Integer updateItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

	public Integer deleteItripLabelDicById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelOrder> getpersonalorderlistsone(Map<String, Object> param);

	public Integer getpersonnalorderlistCount(Map<String, Object> param);

	//查询旅游类型列表
	public List<ItripLabelDic> gettraveltype(Map<String, Object> param);

	//查询酒店图片
	public List<ItripImageVO> hotelgetimg(@Param(value = "id") Long id);

	/**
	 * 根据parentId查询数据字典
	 * @param parentId
	 * @return
	 * @throws Exception
	 * add by hanlu 2017-5-11
	 */
	public List<ItripLabelDicVO> getItripLabelDicByParentId(@Param(value = "parentId") Long parentId)throws Exception;

}
