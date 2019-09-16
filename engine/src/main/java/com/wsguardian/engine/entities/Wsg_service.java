package com.wsguardian.engine.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class Wsg_service implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private Integer TAXONOMY_ID;
	private Integer SERVICE_GLOBAL_POLICY_ID;
	private Integer WSDL_GLOBAL_POLICY_ID;
	private Integer STATE_ID;
	private Integer CONNECTION_TIMEOUT;
	private Integer READ_TIMEOUT;
	private Integer LOAD_BALANCER_TYPE;
	private Integer TOTAL_SIZE_REQUEST;
	
	private String NAME;
	private String DESCRIPTION;
	private String URL_WSDL;
	private String SERVICE_END_POINT;
	private String BUILDING_STANDARD;
	private String VERSION;
	private String LABELS;
	private String JUDDI_SERVICE_KEY;
	private String JUDDI_TMODEL_KEY;
	private String SOAP_VERSION;
	private String WRITE_SOAP_MESSAGE;

		
	private Date PRODUCTION_DATE;
	
	@Type(type = "numeric_boolean")
	private boolean IS_DEFAULT;  
	@Type(type = "numeric_boolean")
	private boolean IS_LOCAL_WSDL;
	@Type(type = "numeric_boolean")
	private boolean EXPOSE_SOAP_FAULTS;
	@Type(type = "numeric_boolean")
	private boolean ENABLE_WS_POLICY;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTAXONOMY_ID() {
		return TAXONOMY_ID;
	}
	public void setTAXONOMY_ID(Integer tAXONOMY_ID) {
		TAXONOMY_ID = tAXONOMY_ID;
	}
	public Integer getSERVICE_GLOBAL_POLICY_ID() {
		return SERVICE_GLOBAL_POLICY_ID;
	}
	public void setSERVICE_GLOBAL_POLICY_ID(Integer sERVICE_GLOBAL_POLICY_ID) {
		SERVICE_GLOBAL_POLICY_ID = sERVICE_GLOBAL_POLICY_ID;
	}
	public Integer getWSDL_GLOBAL_POLICY_ID() {
		return WSDL_GLOBAL_POLICY_ID;
	}
	public void setWSDL_GLOBAL_POLICY_ID(Integer wSDL_GLOBAL_POLICY_ID) {
		WSDL_GLOBAL_POLICY_ID = wSDL_GLOBAL_POLICY_ID;
	}
	public Integer getSTATE_ID() {
		return STATE_ID;
	}
	public void setSTATE_ID(Integer sTATE_ID) {
		STATE_ID = sTATE_ID;
	}
	public Integer getCONNECTION_TIMEOUT() {
		return CONNECTION_TIMEOUT;
	}
	public void setCONNECTION_TIMEOUT(Integer cONNECTION_TIMEOUT) {
		CONNECTION_TIMEOUT = cONNECTION_TIMEOUT;
	}
	public Integer getREAD_TIMEOUT() {
		return READ_TIMEOUT;
	}
	public void setREAD_TIMEOUT(Integer rEAD_TIMEOUT) {
		READ_TIMEOUT = rEAD_TIMEOUT;
	}
	public Integer getLOAD_BALANCER_TYPE() {
		return LOAD_BALANCER_TYPE;
	}
	public void setLOAD_BALANCER_TYPE(Integer lOAD_BALANCER_TYPE) {
		LOAD_BALANCER_TYPE = lOAD_BALANCER_TYPE;
	}
	public Integer getTOTAL_SIZE_REQUEST() {
		return TOTAL_SIZE_REQUEST;
	}
	public void setTOTAL_SIZE_REQUEST(Integer tOTAL_SIZE_REQUEST) {
		TOTAL_SIZE_REQUEST = tOTAL_SIZE_REQUEST;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getURL_WSDL() {
		return URL_WSDL;
	}
	public void setURL_WSDL(String uRL_WSDL) {
		URL_WSDL = uRL_WSDL;
	}
	public String getSERVICE_END_POINT() {
		return SERVICE_END_POINT;
	}
	public void setSERVICE_END_POINT(String sERVICE_END_POINT) {
		SERVICE_END_POINT = sERVICE_END_POINT;
	}
	public String getBUILDING_STANDARD() {
		return BUILDING_STANDARD;
	}
	public void setBUILDING_STANDARD(String bUILDING_STANDARD) {
		BUILDING_STANDARD = bUILDING_STANDARD;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public String getLABELS() {
		return LABELS;
	}
	public void setLABELS(String lABELS) {
		LABELS = lABELS;
	}
	public String getJUDDI_SERVICE_KEY() {
		return JUDDI_SERVICE_KEY;
	}
	public void setJUDDI_SERVICE_KEY(String jUDDI_SERVICE_KEY) {
		JUDDI_SERVICE_KEY = jUDDI_SERVICE_KEY;
	}
	public String getJUDDI_TMODEL_KEY() {
		return JUDDI_TMODEL_KEY;
	}
	public void setJUDDI_TMODEL_KEY(String jUDDI_TMODEL_KEY) {
		JUDDI_TMODEL_KEY = jUDDI_TMODEL_KEY;
	}
	public String getSOAP_VERSION() {
		return SOAP_VERSION;
	}
	public void setSOAP_VERSION(String sOAP_VERSION) {
		SOAP_VERSION = sOAP_VERSION;
	}
	public String getWRITE_SOAP_MESSAGE() {
		return WRITE_SOAP_MESSAGE;
	}
	public void setWRITE_SOAP_MESSAGE(String wRITE_SOAP_MESSAGE) {
		WRITE_SOAP_MESSAGE = wRITE_SOAP_MESSAGE;
	}
	public Date getPRODUCTION_DATE() {
		return PRODUCTION_DATE;
	}
	public void setPRODUCTION_DATE(Date pRODUCTION_DATE) {
		PRODUCTION_DATE = pRODUCTION_DATE;
	}
	public boolean isIS_DEFAULT() {
		return IS_DEFAULT;
	}
	public void setIS_DEFAULT(boolean iS_DEFAULT) {
		IS_DEFAULT = iS_DEFAULT;
	}
	public boolean isIS_LOCAL_WSDL() {
		return IS_LOCAL_WSDL;
	}
	public void setIS_LOCAL_WSDL(boolean iS_LOCAL_WSDL) {
		IS_LOCAL_WSDL = iS_LOCAL_WSDL;
	}
	public boolean isEXPOSE_SOAP_FAULTS() {
		return EXPOSE_SOAP_FAULTS;
	}
	public void setEXPOSE_SOAP_FAULTS(boolean eXPOSE_SOAP_FAULTS) {
		EXPOSE_SOAP_FAULTS = eXPOSE_SOAP_FAULTS;
	}
	public boolean isENABLE_WS_POLICY() {
		return ENABLE_WS_POLICY;
	}
	public void setENABLE_WS_POLICY(boolean eNABLE_WS_POLICY) {
		ENABLE_WS_POLICY = eNABLE_WS_POLICY;
	}
}
