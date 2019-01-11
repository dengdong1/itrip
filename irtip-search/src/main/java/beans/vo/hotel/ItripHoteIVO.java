package beans.vo.hotel;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * 返给给客户端的酒店数据
 * Created by XX on 17-5-10.
 */
public class ItripHoteIVO {

    @Field("id")
    private Long id;

    @Field("hotelName")
    private String hotelName;

    @Field("address")
    private String address;

    @Field("hotelLevel")
    private Integer hotelLevel;

    @Field("redundantCityName")
    private String redundantCityName;

    @Field("redundantProvinceName")
    private String redundantProvinceName;

    @Field("redundantCountryName")
    private String redundantCountryName;

    @Field("maxPrice")
    private Double maxPrice;

    @Field("minPrice")
    private Double minPrice;

    @Field("extendPropertyNames")
    private String extendPropertyNames;

    @Field("extendPropertyPics")
    private String extendPropertyPics;

    @Field("tradingAreaNames")
    private String tradingAreaNames;

    @Field("featureNames")
    private String featureNames;

    @Field("isOkCount")
    private Integer isOkCount;

    @Field("commentCount")
    private Integer commentCount;

    @Field("avgScore")
    private Double avgScore;

    @Field("imgUrl")
    private String imgUrl;


    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getIsOkCount() {
        return isOkCount;
    }

    public void setIsOkCount(Integer isOkCount) {
        this.isOkCount = isOkCount;
    }

    public String getExtendPropertyNames() {
        return extendPropertyNames;
    }

    public void setExtendPropertyNames(String extendPropertyNames) {
        this.extendPropertyNames = extendPropertyNames;
    }

    public String getTradingAreaNames() {
        return tradingAreaNames;
    }

    public void setTradingAreaNames(String tradingAreaNames) {
        this.tradingAreaNames = tradingAreaNames;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getFeatureNames() {
        return featureNames;
    }

    public void setFeatureNames(String featureNames) {
        this.featureNames = featureNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(Integer hotelLevel) {
        this.hotelLevel = hotelLevel;
    }

    public String getRedundantCityName() {
        return redundantCityName;
    }

    public void setRedundantCityName(String redundantCityName) {
        this.redundantCityName = redundantCityName;
    }

    public String getRedundantProvinceName() {
        return redundantProvinceName;
    }

    public void setRedundantProvinceName(String redundantProvinceName) {
        this.redundantProvinceName = redundantProvinceName;
    }

    public String getRedundantCountryName() {
        return redundantCountryName;
    }

    public void setRedundantCountryName(String redundantCountryName) {
        this.redundantCountryName = redundantCountryName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getExtendPropertyPics() {
        return extendPropertyPics;
    }

    public void setExtendPropertyPics(String extendPropertyPics) {
        this.extendPropertyPics = extendPropertyPics;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

}