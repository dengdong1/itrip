package beans.vo.controller;

import beans.vo.hotel.ItripHoteIVO;
import beans.vo.service.SearchHotelService;
import beans.vo.service.SearchHotelServiceImp;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.hotel.SearchHotCityVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotellist")
public class HotelListController {

    /***
     * 根据热门城市查询6个酒店
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据热门城市查询6个酒店", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "根据热门城市查询6个酒店" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/searchItripHotelListByHotCity", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripHoteIVO>> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO){
        System.out.println("进来进来进来进来进来进来进来进来");
        System.out.println("+++++++++++++++++++++++++++++++"+searchHotCityVO.getCityId());
        System.out.println("+++++++++++++++++++++++++++++++"+searchHotCityVO.getCount());
        List<ItripHoteIVO> list=null;
        SearchHotelService search=new SearchHotelServiceImp();
        try {
            Map<String,Object> param = new HashMap();
            param.put("cityId", searchHotCityVO.getCityId());
            list= search.searchItripHotelListByHotCity(searchHotCityVO.getCityId(),searchHotCityVO.getCount());
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("结果",list);
    }
}
