package cn.itrip.beans.vo.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class ItripingCommentVO {

    private Integer improve;//是否满意（0：有待改善 1：值得推荐）

    private Integer havingimg;//是否有评论图片（0 无图片 1 有图片）

    private Integer isok;//是否满意（0：有待改善 1：值得推荐）

    private Integer allcomment;

    public Integer getImprove() {
        return improve;
    }

    public void setImprove(Integer improve) {
        this.improve = improve;
    }

    public Integer getHavingimg() {
        return havingimg;
    }

    public void setHavingimg(Integer havingimg) {
        this.havingimg = havingimg;
    }

    public Integer getIsok() {
        return isok;
    }

    public void setIsok(Integer isok) {
        this.isok = isok;
    }

    public Integer getAllcomment() {
        return allcomment;
    }

    public void setAllcomment(Integer allcomment) {
        this.allcomment = allcomment;
    }
}
