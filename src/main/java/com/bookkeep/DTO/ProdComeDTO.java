package com.bookkeep.DTO;

import java.util.Date;

/**
 * @author scn
 * @create 2018-03-09 14:18
 * @desc 产品进厂DTO
 **/
public class ProdComeDTO {
    private Integer id;

    private Integer prodId;
    private String prodName;

    private String batch;

    private Long price;

    private Integer memberId;
    private String memberName;

    private String comeTime;

    private Integer comeCount;
    private Integer currentCount;//产品剩余数量

    private Date createTime;
    private Date updateTime;
    private Date startTime;
    private Date endTime;

    private Integer createUser;

    public ProdComeDTO() {
    }

    public ProdComeDTO(Integer id, Integer prodId, String batch, Long price, Integer memberId, String comeTime, Integer comeCount, Date createTime, Date updateTime, Integer createUser, String prodName, String memberName, Integer currentCount) {
        this.id = id;
        this.prodId = prodId;
        this.prodName = prodName;
        this.batch = batch;
        this.price = price;
        this.memberId = memberId;
        this.memberName = memberName;
        this.comeTime = comeTime;
        this.comeCount = comeCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.currentCount = currentCount;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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

    public Integer getComeCount() {
        return comeCount;
    }

    public void setComeCount(Integer comeCount) {
        this.comeCount = comeCount;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
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
}
