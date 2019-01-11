package cn.itrip.service.user;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.service.labeldic.ItripLabelDicService;
import cn.itrip.service.labeldic.ItripLabelDicServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestItripUserService {
    @Test
    public void testAddUser() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripUserService itripUserService = ctx.getBean(ItripUserService.class);
        try {
            ItripUser itripUser = itripUserService.getItripUserById((long)12);
            System.out.println(itripUser.getId() + " " + itripUser.getUserCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUser1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        ItripLabelDicService itripUserService = ctx.getBean(ItripLabelDicService.class);
       // ItripLabelDicService itripLabelDicService = ctx.getBean(ItripLabelDicService.class);
        try {
         /*   ItripHotel  itripHotel=itripLabelDicService.getItripHotel(1L);
            System.out.println("asdddddddddddddddddddddddddd"+itripHotel);
            Map param = new HashMap();
            param.put("id", itripHotel);*/
            List<ItripImageVO>  itripUser = itripUserService.hotelgetimg(1L);
            System.out.println(">>>>>>>"+itripUser.size());
            for (ItripImageVO itripImage : itripUser) {
                System.out.println("namr>>namrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamrnamr>>>"+itripImage.getImgUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
