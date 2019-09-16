package com.wsguardian.orchestrator.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class SoapRequestEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Policy> politicas;
	Map<String, String> headers;
	Map<String,String[]> parameters;
	String body;
	boolean isWsdl;
	String fullUri;
	String pathInfo;
	

	public SoapRequestEntity() {

	}


	

	public List<Policy> getPoliticas() {
		return politicas;
	}




	public void setPoliticas(List<Policy> politicas) {
		this.politicas = politicas;
	}




	public Map<String, String> getHeaders() {
		return headers;
	}




	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}




	public Map<String, String[]> getParameters() {
		return parameters;
	}




	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}




	public String getBody() {
		return body;
	}




	public void setBody(String body) {
		this.body = body;
	}




	public boolean isWsdl() {
		return isWsdl;
	}




	public void setWsdl(boolean isWsdl) {
		this.isWsdl = isWsdl;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public String getPathInfo() {
		return pathInfo;
	}




	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}

	public String getFullUri() {
		return fullUri;
	}




	public void setFullUri(String fullUri) {
		this.fullUri = fullUri;
	}




	@Override
	public String toString() {
		return "SoapRequestEntity [politicas=" + politicas + ", headers=" + headers + ", parameters=" + parameters
				+ ", body=" + body + ", isWsdl=" + isWsdl + ", fullUri=" + fullUri + ", pathInfo=" + pathInfo + "]";
	}


}
