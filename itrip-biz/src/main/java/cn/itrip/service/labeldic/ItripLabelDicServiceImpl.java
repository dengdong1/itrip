package cn.itrip.service.labeldic;
import cn.itrip.beans.pojo.*;
import cn.itrip.beans.vo.ItripAreaDicVO;
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
import cn.itrip.dao.labeldic.ItripLabelDicMapper;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.itrip.common.Constants;
@Service
public class ItripLabelDicServiceImpl implements ItripLabelDicService {

      @Resource
    private ItripLabelDicMapper itripLabelDicMapper;

    //查询酒店视频信息
    @Override
    public ItripLabelDic getvideodesc(Long id) throws Exception {
        return null;
    }

    @Override
    public List<ItripImageVO> getIHotelgetimg(Integer id) throws Exception {
        return itripLabelDicMapper.getIHotelgetimg(id);
    }

    @Override
    public ItripHotel dicListByqueryhotelpolicy(Long id) throws Exception {
        return itripLabelDicMapper.dicListByqueryhotelpolicy(id);
    }

    public ItripLabelDic getItripLabelDicById(Long id)throws Exception{
        return itripLabelDicMapper.getItripLabelDicById(id);
    }

    public List<ItripSearchFacilitiesHotelVO> getItripLabelDicByIdfacilities(Long id)throws Exception{
        return itripLabelDicMapper.dicListByMapqueryHotelfacilities(id);
    }

    @Override
    public ItripHotel getItripHotel(Long id) {
        return itripLabelDicMapper.getItripHotel(id);
    }
    @Override
    public ItripHotel getItripHotelone(Long id) {
        return itripLabelDicMapper.getItripHotelone(id);
    }

    @Override
    public ItripHotel getItripHoteltwo(Long id) {
        return itripLabelDicMapper.getItripHoteltwo(id);
    }

    @Override
    public List<PreAddOrderVO> getloadPreAddOrderVO(Long hotelId, Long roomId, Date checkInDate,Date checkOutDate,Integer count,String hotelName,Integer store,BigDecimal price) {
        return itripLabelDicMapper.getloadPreAddOrderVO(hotelId,roomId,checkInDate,checkOutDate,count,hotelName,store,price);
    }

    @Override
    public List<ItripPersonalHotelOrderVO> getpersonalorderinfo(@Param(value = "id")Long id) {
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        System.out.println("asdasdasdwqfzxcasd"+id);
        return itripLabelDicMapper.getpersonalorderinfo(id);
    }




    @Override
    public Page<ItripHotelOrder> getpersonalOrderDingdan(Map<String, Object> param, Integer pageNo,Integer pageSize) {
        System.out.println("进来service层吧，啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
        List<ItripListHotelOrderVO> itripListHotelOrder=null;

        Integer total=itripLabelDicMapper.getpersonnalorderlistCount(param);
        System.out.println("toteltoteltoteltoteltoteltotel:"+total);

        pageNo=EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO :pageNo;
        System.out.println("pageNopageNopageNopageNopageNo:"+pageNo);

        pageSize=EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_NO :pageSize;
        System.out.println("pageSizepageSizepageSizepageSize:"+pageSize);

        param.put("beginPos",pageNo);

        param.put("pageSize",pageSize);

        Page page=new Page(pageNo,pageSize,total);

        List<ItripHotelOrder> itrip = itripLabelDicMapper.getpersonalorderlistsone(param);
        System.out.println("输出的结果输出的结果输出的结果输出的结果："+itrip);
        if (EmptyUtils.isNotEmpty(itrip)) {
            itripListHotelOrder = new ArrayList();
            for (ItripHotelOrder dic : itrip) {
                ItripListHotelOrderVO vo = new ItripListHotelOrderVO();
                BeanUtils.copyProperties(dic, vo);
                itripListHotelOrder.add(vo);
            }
        }
        page.setRows(itripListHotelOrder);
        return page;
    }

    @Override
    public ItripComment gethotelscoreId(@Param(value = "hotelId")Long hotelId) throws Exception {
        return itripLabelDicMapper.gethotelscoreId(hotelId);
    }

    public List<ItripSearchPolicyHotelVO> getItripLabelDicByIdfacilities1(Long id)throws Exception{
        return itripLabelDicMapper.dicListByMapqueryHotelfacilities1(id);
    }


    @Override
    public List<ItripSearchDetailsHotelVO> getItripMapHotel(@Param(value = "hotelId") Integer hotelId) throws Exception {
        return itripLabelDicMapper.getItripMapHotel(hotelId);
    }

    @Override
    public List<ItripSearchCommentVO> getcommentlist(Long hotelId, Integer isHavingImg, Integer isOk, Integer pageSize, Integer pageNo) {
        return itripLabelDicMapper.getcommentlist(hotelId,isHavingImg,isOk,pageSize,pageNo);
    }

    @Override
    public List<ItripImageVO> getCommentImg(Map<String, Object> param) {
        return itripLabelDicMapper.getCommentImg(param);
    }

    public List<ItripLabelDic>	getItripLabelDicListByMap(Map<String,Object> param)throws Exception{
        return itripLabelDicMapper.getItripLabelDicListByMap(param);
    }

    @Override
    public List<ItripLabelDic> queryhotelroombed(Map<String, Object> param) throws Exception {
        return itripLabelDicMapper.queryhotelroombed(param);
    }

    //评论适量
    public Integer getItripLabelDicCountByMap(Map<String,Object> param)throws Exception{
        return itripLabelDicMapper.getItripLabelDicCountByMap(param);
    }

    public Integer itriptxAddItripLabelDic(ItripLabelDic itripLabelDic)throws Exception{
            itripLabelDic.setCreationDate(new Date());
            return itripLabelDicMapper.insertItripLabelDic(itripLabelDic);
    }

    public Integer itriptxModifyItripLabelDic(ItripLabelDic itripLabelDic)throws Exception{
        itripLabelDic.setModifyDate(new Date());
        return itripLabelDicMapper.updateItripLabelDic(itripLabelDic);
    }

    public Integer itriptxDeleteItripLabelDicById(Long id)throws Exception{
        return itripLabelDicMapper.deleteItripLabelDicById(id);
    }

    public Page<ItripLabelDic> queryItripLabelDicPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripLabelDicMapper.getItripLabelDicCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripLabelDic> itripLabelDicList = itripLabelDicMapper.getItripLabelDicListByMap(param);
        page.setRows(itripLabelDicList);
        return page;
    }

    @Override
    public ItripingCommentVO getcount(@Param(value = "hotelId") Long hotelId) {
        System.out.println("hotelIdhotelIdhotelIdhotelIdhotelIdhotelIdhotelIdhotelIdhotelId"+hotelId);
        return itripLabelDicMapper.getcount(hotelId);
    }

    @Override
    public ItripHotelDescVO gethoteldesc(Long hotelId) throws Exception {
        return itripLabelDicMapper.gethoteldesc(hotelId);
    }

    @Override
    public List<ItripLabelDic> gettraveltype(Map<String, Object> param) {
        return itripLabelDicMapper.gettraveltype(param);
    }

    @Override
    public List<ItripImageVO> hotelgetimg(Long id) {
        return itripLabelDicMapper.hotelgetimg(id);
    }


    /**getItripMapHotel
     * 根据parentId查询数据字典
     * @param parentId
     * @return
     * @throws Exception
     * add by hanlu 2017-5-11
     */
    public List<ItripLabelDicVO> getItripLabelDicByParentId(Long parentId)throws Exception{
        return itripLabelDicMapper.getItripLabelDicByParentId(parentId);
    }


}
