package com.lizhuopeng.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

public class MiniPicOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 是否已支付
     */
    private boolean isPaid;

    /**
     * 应支付金额
     */
    private Integer amountOfMoney;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date updateTime;

    /**
     * 该订单是否已失效
     */
    private boolean isDiscarded;

    /**
     * 压缩文件名
     */
    private String imgName;

    public MiniPicOrder() {
    }

    public MiniPicOrder(Integer id, String username, boolean isPaid, Integer amountOfMoney, Date createTime, Date updateTime, boolean isDiscarded, String imgName) {
        this.id = id;
        this.username = username;
        this.isPaid = isPaid;
        this.amountOfMoney = amountOfMoney;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDiscarded = isDiscarded;
        this.imgName = imgName;
    }

    public MiniPicOrder(String username, boolean isPaid, Integer amountOfMoney, Date createTime, Date updateTime, String imgName) {
        this.username = username;
        this.isPaid = isPaid;
        this.amountOfMoney = amountOfMoney;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.imgName = imgName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Integer getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Integer amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDiscarded() {
        return isDiscarded;
    }

    public void setDiscarded(boolean discarded) {
        isDiscarded = discarded;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
