package com.xw.project.dto;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xw.common.entity.BasePlusEntity;
import com.xw.project.util.PageHelper;

/**
* @author Jy
* @since 2020-06-28
*/
public class TargetPositionDto extends PageHelper {

                        private String refId;
                        private String refType;
                        private String orgId;
                        private String areaId;
                        private String address;
                        private BigDecimal positionLon;
                        private BigDecimal positionLat;
                        private String state;

                    
    public String getRefId() {
    return refId;
    }

                public void setRefId(String refId) {
            this.refId = refId;
            }
                    
    public String getRefType() {
    return refType;
    }

                public void setRefType(String refType) {
            this.refType = refType;
            }
                    
    public String getOrgId() {
    return orgId;
    }

                public void setOrgId(String orgId) {
            this.orgId = orgId;
            }
                    
    public String getAreaId() {
    return areaId;
    }

                public void setAreaId(String areaId) {
            this.areaId = areaId;
            }
                    
    public String getAddress() {
    return address;
    }

                public void setAddress(String address) {
            this.address = address;
            }
                    
    public BigDecimal getPositionLon() {
    return positionLon;
    }

                public void setPositionLon(BigDecimal positionLon) {
            this.positionLon = positionLon;
            }
                    
    public BigDecimal getPositionLat() {
    return positionLat;
    }

                public void setPositionLat(BigDecimal positionLat) {
            this.positionLat = positionLat;
            }
                    
    public String getState() {
    return state;
    }

                public void setState(String state) {
            this.state = state;
            }
    

}