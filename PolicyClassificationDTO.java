package co.com.itac.wsguardian.common.dto;

import co.com.itac.wsguardian.common.policy.entity.Policy;
import co.com.itac.wsguardian.common.service.entity.Operation;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO que agrupa las politicas segun su clasificacion
 * @author Jorge
 */
public class PolicyClassificationDTO implements Serializable {

    private static final long serialVersionUID = -1209473334285898429L;
    
    private Operation operation = null;
    
    // politicas de entrada en modo Cliente
    private ArrayList<Policy> entryClientModeList = null;
    
    // politicas de entrada en modo Servidor
    private ArrayList<Policy> entryServerModeList = null;
    
    // politicas de salida en modo Cliente
    private ArrayList<Policy> responseClientModeList = null;
    
    // politicas de salida en modo Servidor
    private ArrayList<Policy> responseServerModeList = null;

    private boolean selected = false;
  
    public PolicyClassificationDTO() {
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public boolean hasEntryPolicies(){
        return  entryServerModeList!=null?!entryServerModeList.isEmpty():false;
    }
    
    public boolean hasPolicies(){
        boolean clientModeEntry = entryClientModeList!=null? !entryClientModeList.isEmpty(): false;
        boolean serverModeEntry = entryServerModeList!=null? !entryServerModeList.isEmpty(): false;
        boolean clientModeResponse = responseClientModeList!=null? !responseClientModeList.isEmpty(): false;
        boolean serverModeResponse = responseServerModeList!=null? !responseServerModeList.isEmpty(): false;

        return clientModeEntry && serverModeEntry && clientModeResponse && serverModeResponse;
    }


    public ArrayList<Policy> getEntryClientModeList() {
        return entryClientModeList;
    }

    public void setEntryClientModeList(ArrayList<Policy> entryClientModeList) {
        this.entryClientModeList = entryClientModeList;
    }

    public ArrayList<Policy> getEntryServerModeList() {
        return entryServerModeList;
    }

    public void setEntryServerModeList(ArrayList<Policy> entryServerModeList) {
        this.entryServerModeList = entryServerModeList;
    }

    public ArrayList<Policy> getResponseClientModeList() {
        return responseClientModeList;
    }

    public void setResponseClientModeList(ArrayList<Policy> responseClientModeList) {
        this.responseClientModeList = responseClientModeList;
    }

    public ArrayList<Policy> getResponseServerModeList() {
        return responseServerModeList;
    }

    public void setResponseServerModeList(ArrayList<Policy> responseServerModeList) {
        this.responseServerModeList = responseServerModeList;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
