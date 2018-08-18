package com.yt.comment.entity;

/**
 * Description:
 *
 * @author:Tong
 */
public class Business extends Base{

    private Long id;
    private String imgFileName;
    private String title;//标题
    private String subtitle;//副标题
    private Double price;
    private Integer distance;
    private Integer number;
    private String desc;//描述
    private String city;
    private String category;
    private Long starTotalNum;
    private Long commentTotalNum;

    private Dic cityDic;
    private Dic categoryDic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getStarTotalNum() {
        return starTotalNum;
    }

    public void setStarTotalNum(Long starTotalNum) {
        this.starTotalNum = starTotalNum;
    }

    public Long getCommentTotalNum() {
        return commentTotalNum;
    }

    public void setCommentTotalNum(Long commentTotalNum) {
        this.commentTotalNum = commentTotalNum;
    }

    public Dic getCityDic() {
        return cityDic;
    }

    public void setCityDic(Dic cityDic) {
        this.cityDic = cityDic;
    }

    public Dic getCategoryDic() {
        return categoryDic;
    }

    public void setCategoryDic(Dic categoryDic) {
        this.categoryDic = categoryDic;
    }
}
