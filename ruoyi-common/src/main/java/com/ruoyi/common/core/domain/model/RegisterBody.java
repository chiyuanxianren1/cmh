package com.ruoyi.common.core.domain.model;

/**
 * 用户注册对象
 * 
 * @author ruoyi
 */
public class RegisterBody extends LoginBody
{
    //企业名称
    private String enterpriseName;

    /**
     * 企业联系方式
     */
    private String link;



    public String getLink() {
        return link;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
