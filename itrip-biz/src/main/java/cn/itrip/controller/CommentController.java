package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.comment.ItripHotelDescVO;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import cn.itrip.beans.vo.comment.ItripingCommentVO;
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

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/comment")
public class CommentController {

    private Logger logger = Logger.getLogger(CommentController.class);

    @Resource
    private ItripAreaDicService itripAreaDicService;

    @Resource
    private ItripLabelDicService itripLabelDicService;

    /**/

    /***
     * 查询酒店评分
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店评分", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店评分(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/gethotelscore/{hotelId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripScoreCommentVO> queryhotelfacilities(@PathVariable Long hotelId)throws Exception {
        ItripComment itripLabe = null;

        ItripScoreCommentVO onetwo = new ItripScoreCommentVO();
        itripLabe= itripLabelDicService.gethotelscoreId(hotelId);
        onetwo.setAvgPositionScore(itripLabe.getPositionScore());
        onetwo.setAvgHygieneScore(itripLabe.getHygieneScore());
        onetwo.setAvgFacilitiesScore(itripLabe.getFacilitiesScore());
        onetwo.setAvgServiceScore(itripLabe.getServiceScore());
        onetwo.setAvgScore(itripLabe.getScore());
        return DtoUtil.returnSuccess("算成功吧",onetwo);
    }

    /***
     * 查询所有床型
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询所有床型", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取所有床型(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/gettraveltype", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripLabelDicVO> gettraveltype() {
        List<ItripLabelDic> itripLabelDics = null;
        List<ItripLabelDicVO> itripAreaDicVOs = null;
        try {
            Map param = new HashMap();
            param.put("parentId", 107);
            itripLabelDics = itripLabelDicService.gettraveltype(param);
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
     * 查询酒店评论列表
     * @return
     * @throws Exception
     */
/*    @ApiOperation(value = "查询酒店评分", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取酒店评分(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/getcount/{hotelId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripSearchCommentVO> queryhotelfacilities(Long hotelId , Integer isHavingImg, Integer isOk, Integer pageSize, Integer pageNo)throws Exception {
        ItripComment itripLabe = null;
        ItripSearchCommentVO onetwo = new ItripSearchCommentVO();
        itripLabe= itripLabelDicService.gethotelscoreId(hotelId);
        onetwo.setIsHavingImg(isHavingImg);
        onetwo.setIsOk(isOk);
        onetwo.setPageSize(pageSize);
        onetwo.setPageNo(pageNo);
        return DtoUtil.returnSuccess("算成功吧",onetwo);
    }*/

    /***
     * 查询评论列表
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询评论列表", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取评论列表(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "getcount/{hotelId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripingCommentVO> getcountone(@PathVariable Long hotelId)throws Exception {
        ItripComment itripLabe = null;
        ItripingCommentVO onetwo =null;

         onetwo= itripLabelDicService.getcount(hotelId);

        return DtoUtil.returnSuccess("算成功吧",onetwo);
    }

    /**
     * 查询评论内容列表
     */
   /* @ApiOperation(value = "查询评论内容列表", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取评论内容列表(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "getcommentlist", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripSearchCommentVO> getcommentlist(Long hotelId,Integer isHavingImg,Integer isOk,Integer pageSize,Integer pageNo)throws Exception {
        ItripComment itripLabe = null;
        ItripSearchCommentVO onetwo = new ItripSearchCommentVO();
        onetwo.setIsHavingImg(isHavingImg);
        onetwo.setIsOk(isOk);
        onetwo.setPageSize(pageSize);
        onetwo.setPageNo(pageNo);
        List<ItripSearchCommentVO> ItripSearchComment= itripLabelDicService.getcommentlist(onetwo);
        return DtoUtil.returnSuccess("算成功吧",onetwo);
    }*/

    /**
     * 查询评论内容列表
     */
 @ApiOperation(value = "查询评论附带的图片", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "获取评论附带的图片(用于查询页列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "getimg/{commentId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripImageVO> getimg(@PathVariable Long commentId)throws Exception {
        ItripComment itripLabe = null;
        ItripImageVO onetwo = new ItripImageVO();


/*     select imgUrl,position from itrip_comment,itrip_image
     where itrip_comment.id in (itrip_image.id) and itrip_comment.id=#{id}
     and itrip_image.type=2*/

         Map param = new HashMap();
         param.put("id", commentId);
        List<ItripImageVO> onethree= itripLabelDicService.getCommentImg(param);
        return DtoUtil.returnSuccess("算成功吧",onethree);
    }


    /***
     * 查询酒店评分
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店详情（评论）", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店详情（评论）" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码: </p>" +
            "<p>10205: 系统异常,获取失败</p>")
    @RequestMapping(value = "/gethoteldesc/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripHotelDescVO> gethoteldesc(@PathVariable Long id)throws Exception {
        ItripComment itripComment=null;
        ItripHotelDescVO onetwo = null;
        onetwo= itripLabelDicService.gethoteldesc(id);
        return DtoUtil.returnSuccess("算成功吧",onetwo);
    }
}
