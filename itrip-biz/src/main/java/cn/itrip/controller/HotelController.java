package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.*;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.hotel.ItripSearchDetailsHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchFacilitiesHotelVO;
import cn.itrip.beans.vo.hotel.ItripSearchPolicyHotelVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.areadic.ItripAreaDicService;
import cn.itrip.service.labeldic.ItripLabelDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 酒店信息Controller
 * <p/>
 * 包括API接口：
 * 1、根据酒店id查询酒店拓展属性
 * 2、根据酒店id查询酒店介绍，酒店政策，酒店设施
 * 3、根据酒店id查询酒店特色属性列表
 * 4、根据type 和target id 查询酒店图片
 * 5、查询热门城市
 * 6、查询热门商圈列表（搜索-酒店列表）
 * 7、查询数据字典特色父级节点列表（搜索-酒店列表）
 * 8、根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）
 * <p/>
 * 注：错误码（100201 ——100300）
 * <p/>
 * Created by hanlu on 2017/5/9.
 */

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotel")
public class HotelController {

    private Logger logger = Logger.getLogger(HotelController.class);

    @Resource
    private ItripAreaDicService itripAreaDicService;

    @Resource
    private ItripLabelDicService itripLabelDicService;

    /****
     * 查询热门城市的接口
     *
     * @param type
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询热门城市", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询国内、国外的热门城市(1:国内 2:国外)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotcity/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripAreaDicVO> queryHotCity(@PathVariable Integer type) {
        List<ItripAreaDic> itripAreaDics = null;
        List<ItripAreaDicVO> itripAreaDicVOs = null;
        try {
            if (EmptyUtils.isNotEmpty(type)) {
                Map param = new HashMap();
                param.put("isHot", 1);
                param.put("isChina", type);
                itripAreaDics = itripAreaDicService.getItripAreaDicListByMap(param);
                if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                    itripAreaDicVOs = new ArrayList();
                    for (ItripAreaDic dic : itripAreaDics) {
                        ItripAreaDicVO vo = new ItripAreaDicVO();
                        BeanUtils.copyProperties(dic, vo);
                        itripAreaDicVOs.add(vo);
                    }
                }
            } else {
                DtoUtil.returnFail("type不能为空", "10201");
            }
            } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10202");
            e.printStackTrace();
             }
            return DtoUtil.returnDataSuccess(itripAreaDicVOs);
        }

    /****
     * 查询城市商圈
     *
     * @param type
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询城市商圈", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询城市商圈" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/querytradearea/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody/**/
    public Dto<ItripAreaDicVO> querytradearea(@PathVariable Integer type) {
        List<ItripAreaDic> itripAreaDics = null;
        List<ItripAreaDicVO> itripAreaDicVOs = null;
        try {
            if (EmptyUtils.isNotEmpty(type)) {
                Map param = new HashMap();
                param.put("isTradingArea", 1);
                param.put("isChina", type);
                itripAreaDics = itripAreaDicService.querytradearea(param);
                if (EmptyUtils.isNotEmpty(itripAreaDics)) {
                    itripAreaDicVOs = new ArrayList();
                    for (ItripAreaDic dic : itripAreaDics) {
                        ItripAreaDicVO vo = new ItripAreaDicVO();
                        BeanUtils.copyProperties(dic, vo);
                        itripAreaDicVOs.add(vo);
                    }
                }

            } else {
                DtoUtil.returnFail("type不能为空", "10201");
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10202");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOs);
    }

    /***
     * 查询酒店特色列表
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店特色列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店特色(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelfeature", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> queryHotelFeature() {
        List<ItripLabelDic> itripLabelDics = null;
        List<ItripLabelDicVO> itripAreaDicVOs = null;
        try {
            Map param = new HashMap();
            param.put("parentId", 16);
            itripLabelDics = itripLabelDicService.getItripLabelDicListByMap(param);
            if (EmptyUtils.isNotEmpty(itripLabelDics)) {
                itripAreaDicVOs = new ArrayList();
                for (ItripLabelDic dic : itripLabelDics) {
                    ItripLabelDicVO vo = new ItripLabelDicVO();
                    BeanUtils.copyProperties(dic, vo);
                    itripAreaDicVOs.add(vo);
                }
            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnDataSuccess(itripAreaDicVOs);
    }

    /***
     * 查询酒店描述和特色
     *效果没有显示出来，应该有点bug
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店描述和特色", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店特色(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhoteldetails/{hotelId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchDetailsHotelVO> queryhotelDetails(@PathVariable Integer hotelId)throws Exception{
        ItripHotel  itripHotel=null;

        itripHotel=itripAreaDicService.getItripMapHotelId(hotelId);

        List<ItripSearchDetailsHotelVO> itripSearchDetailsHotelVO=itripLabelDicService.getItripMapHotel(hotelId);

        ItripSearchDetailsHotelVO itripAreaDicVOs = new ItripSearchDetailsHotelVO();

        itripAreaDicVOs.setName("酒店介绍");

        itripAreaDicVOs.setDescription(itripHotel.getDetails());

        itripSearchDetailsHotelVO.add(itripAreaDicVOs);

        return DtoUtil.returnDataSuccess(itripSearchDetailsHotelVO);
    }

    /***
     * 查询酒店设施
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店设施", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店设施(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelfacilities/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchFacilitiesHotelVO> queryhotelfacilities(@PathVariable Long type)throws Exception {
        ItripHotel itripLabe = null;
        ItripSearchFacilitiesHotelVO onetwo = new ItripSearchFacilitiesHotelVO();
        itripLabe= itripLabelDicService.getItripHotel(type);
        onetwo.setFacilities(itripLabe.getFacilities());
        return DtoUtil.returnSuccess("算成功吧", onetwo.getFacilities());
    }
    /***
     * 查询酒店政策
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店政策", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店政策(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/queryhotelpolicy/{type}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchPolicyHotelVO> queryhotelpolicy(@PathVariable Long type)throws Exception {
        ItripHotel itripLabe = null;
        ItripSearchPolicyHotelVO onetwo = new ItripSearchPolicyHotelVO();
        itripLabe = itripLabelDicService.getItripHotelone(type);
        onetwo.setHotelPolicy(itripLabe.getHotelPolicy());
        return DtoUtil.returnSuccess("算成功吧", onetwo.getHotelPolicy());
    }

    /***
     * 查询酒店图片
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店图片", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店政策(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getimg/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> hotelgetimg(@PathVariable Long id)throws Exception {
        List<ItripImageVO> fater = null;
    /*    ItripHotel itripHotel=null;
        itripHotel=itripLabelDicService.getItripHotel(id);
        System.out.println("asdddddddddddddddddddddddddd"+itripHotel);
        Map param = new HashMap();
        param.put("id", itripHotel);*/
        fater = itripLabelDicService.hotelgetimg(id);
        System.out.println("asdddddddddddddddddddddddddd"+fater);
        return DtoUtil.returnSuccess("算成功吧", fater);
    }
}
