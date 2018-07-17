package com.bookkeep.DTO;

import java.util.Date;

/**
 * @author scn
 * @create 2018-03-09 14:18
 * @desc 产品进厂DTO
 **/
public class WorkRordDTO {
    private Integer id;
    private Integer memberId;
    private Integer comeId;
    private Integer count;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Integer createUser;
    private Date updateTime;
    private String prodName;
    private String memberName;
    private Double price;
    private Integer prodId;
    private String batch;
    private String comeTime;
    private Integer comeCount;

    public WorkRordDTO() {
    }

    public WorkRordDTO(Integer id, Integer count, Date startTime, Date endTime, String prodName, String memberName, Double price, String batch, Integer comeCount) {
        this.id = id;
        this.count = count;
        this.startTime = startTime;
        this.endTime = endTime;
        this.prodName = prodName;
        this.memberName = memberName;
        this.price = price;
        this.batch = batch;
        this.comeCount = comeCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getComeTime() {
        return comeTime;
    }

    public void setComeTime(String comeTime) {
        this.comeTime = comeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getComeId() {
        return comeId;
    }

    public void setComeId(Integer comeId) {
        this.comeId = comeId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getComeCount() {
        return comeCount;
    }

    public void setComeCount(Integer comeCount) {
        this.comeCount = comeCount;
    }
}
