package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import cn.itrip.beans.vo.order.ItripListHotelOrderVO;
import cn.itrip.beans.vo.order.ItripPersonalHotelOrderVO;
import cn.itrip.beans.vo.order.PreAddOrderVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelorder")
public class HotelorderController {

    private Logger logger = Logger.getLogger(HotelController.class);

    @Resource
    private ItripAreaDicService itripAreaDicService;

    @Resource
    private ItripLabelDicService itripLabelDicService;

    /***
     * 查询预生成订单信息
     * @return
     * @throws Exception
     */
   @ApiOperation(value = "预生成订单信息", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取预生成订单信息(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getpreorderinfo", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<PreAddOrderVO> getpreorderinfo(Long hotelId, Long roomId, Date checkInDate, Date checkOutDate, Integer count,String hotelName,Integer store,BigDecimal price)throws Exception {
       /*    PreAddOrderVO onetwo = new PreAddOrderVO();
       onetwo.setHotelId(hotelId);
       onetwo.setRoomId(roomId);
       onetwo.setCheckInDate(checkInDate);
       onetwo.setCheckOutDate(checkOutDate);
       onetwo.setCount(count);*/
       List<PreAddOrderVO> list=itripLabelDicService.getloadPreAddOrderVO(hotelId,roomId,checkInDate,checkOutDate,count,hotelName,store,price);

       return DtoUtil.returnSuccess("算成功吧", list);
    }


    /***
     *
     * 查询预生成订单信息
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "预生成订单信息", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取预生成订单信息(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getpersonalorderinfo/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody

    public Dto<ItripPersonalHotelOrderVO> getpreorderinfo(@PathVariable Long id)throws Exception {
        ItripPersonalHotelOrderVO itripPersonalHotelOrderVO = null;

        List<ItripPersonalHotelOrderVO> itripHotelOrder=itripLabelDicService.getpersonalorderinfo(id);
        for (ItripPersonalHotelOrderVO  fa: itripHotelOrder) {
            fa.setId(fa.getId());
            fa.setCreationDate(fa.getCreationDate());
            fa.setBookType(fa.getBookType());
            fa.setNoticePhone(fa.getNoticePhone());
            fa.setOrderNo(fa.getOrderNo());
            fa.setPayType(fa.getPayType());
            fa.setRoomPayType(fa.getRoomPayType());
            fa.setPayAmount(fa.getPayAmount());

        if (fa.getOrderStatus()==0){
            fa.setProcessNode("2");
            fa.setOrderProcess("1.已提交,2.待支付");
        }else if(fa.getOrderStatus()==1){
            fa.setProcessNode("3");
            fa.setOrderProcess("1.已提交,2.待支付,3.已取消");
        }else if(fa.getOrderStatus()==2){
            fa.setProcessNode("4");
            fa.setOrderProcess("1.已提交,2.待支付,3.支付成功");
        }else if(fa.getOrderStatus()==3){
            fa.setProcessNode("5");
            fa.setOrderProcess("1.已提交,2.待支付,3.支付成功,4.已入住,5.已点评");
        }else{
            fa.setProcessNode("6");
            fa.setOrderProcess("1.已提交,2.待支付,3.支付成功,4.已入住,5.已点评,6.已完成");
            }
        }
        return DtoUtil.returnSuccess("算成功吧", itripHotelOrder);
    }


    /***
     *
     * 查询个人酒店订单列表
     * @return
     * @throws Exception
     */
   @ApiOperation(value = "查询个人酒店订单列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取预生成订单信息(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getpersonalorderlist", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripListHotelOrderVO>> getpersonalorderlists(@RequestBody ItripListHotelOrderVO voorder)throws Exception {
            Page<ItripListHotelOrderVO> page=null;
            System.out.println(",page.getCurPage(),page.getPageSize()"+voorder.getPageNo()+"ssssssssssss"+voorder.getPageSize());
            ItripListHotelOrderVO itripListHotelOrderVO = null;
            Map param = new HashMap();
            param.put("orderStatus", voorder.getOrderStatus());
            param.put("orderType", voorder.getOrderType());
            page=itripLabelDicService.getpersonalOrderDingdan(param,voorder.getPageNo(),voorder.getPageSize());
            logger.debug("asddddddddddddddddddddddddddddddd"+page);
            return DtoUtil.returnSuccess("算成功吧", page);
    }
}