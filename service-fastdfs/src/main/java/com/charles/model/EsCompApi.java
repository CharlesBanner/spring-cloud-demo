package com.charles.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 接口响应缓存
 *
 * @author: GanZiB
 * Date: 2019-04-29
 * Time: 17:35
 */
@Document(indexName = "ra6.comp_api", shards = 3, type = "comp_api", replicas = 1)
public class EsCompApi implements Serializable {
    private static final long serialVersionUID = 7235782703056790688L;
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    /**
     * 间隔测试时间,单位:秒
     */
    @Field(type = FieldType.Integer)
    private Integer timerPoll;

    /**
     * 固定参数
     */
    @Field(type = FieldType.Text)
    private String fixParam;
    /**
     * 测试参数
     */
    @Field(type = FieldType.Text)
    private String testParam;
    /**
     * 外部参数
     */
    @Field(type = FieldType.Text)
    private String externalParam;
    /**
     * 参数组合接口
     */
    @Field(type = FieldType.Keyword)
    private String combineParamInterface;
    /**
     * 参数校验接口
     */
    @Field(type = FieldType.Keyword)
    private String checkParamInterface;
    /**
     * 远程接口地址
     */
    @Field(type = FieldType.Keyword)
    private String apiUrl;
    /**
     * 接口调用服务
     */
    @Field(type = FieldType.Text)
    private String invokingInterface;
    /**
     * 数据校验服务
     */
    @Field(type = FieldType.Text)
    private String dataCheckInterface;
    /**
     *数据处理服务
     */
    @Field(type = FieldType.Text)
    private String dataProcessingInterface;

    /**
     * 异常处理服务
     */
    @Field(type = FieldType.Keyword)
    private String exceptionInterface;

    /**
     * 异常处理服务
     */
    @Field(type = FieldType.Keyword)
    private String cacheInterface;

    /**
     * api描述
     */
    @Field(type = FieldType.Text)
    private String apiDescription;
    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Field(type = FieldType.Date)
    private Date gmtUpdate;

    /**
     * 文档地址
     */
    @Field(type = FieldType.Keyword)
    private String documentAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTimerPoll() {
        return timerPoll;
    }

    public void setTimerPoll(Integer timerPoll) {
        this.timerPoll = timerPoll;
    }

    public String getFixParam() {
        return fixParam;
    }

    public void setFixParam(String fixParam) {
        this.fixParam = fixParam;
    }

    public String getTestParam() {
        return testParam;
    }

    public void setTestParam(String testParam) {
        this.testParam = testParam;
    }

    public String getExternalParam() {
        return externalParam;
    }

    public void setExternalParam(String externalParam) {
        this.externalParam = externalParam;
    }

    public String getCombineParamInterface() {
        return combineParamInterface;
    }

    public void setCombineParamInterface(String combineParamInterface) {
        this.combineParamInterface = combineParamInterface;
    }

    public String getCheckParamInterface() {
        return checkParamInterface;
    }

    public void setCheckParamInterface(String checkParamInterface) {
        this.checkParamInterface = checkParamInterface;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getInvokingInterface() {
        return invokingInterface;
    }

    public void setInvokingInterface(String invokingInterface) {
        this.invokingInterface = invokingInterface;
    }

    public String getDataCheckInterface() {
        return dataCheckInterface;
    }

    public void setDataCheckInterface(String dataCheckInterface) {
        this.dataCheckInterface = dataCheckInterface;
    }

    public String getDataProcessingInterface() {
        return dataProcessingInterface;
    }

    public void setDataProcessingInterface(String dataProcessingInterface) {
        this.dataProcessingInterface = dataProcessingInterface;
    }

    public String getExceptionInterface() {
        return exceptionInterface;
    }

    public void setExceptionInterface(String exceptionInterface) {
        this.exceptionInterface = exceptionInterface;
    }

    public String getCacheInterface() {
        return cacheInterface;
    }

    public void setCacheInterface(String cacheInterface) {
        this.cacheInterface = cacheInterface;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getDocumentAddress() {
        return documentAddress;
    }

    public void setDocumentAddress(String documentAddress) {
        this.documentAddress = documentAddress;
    }
}
