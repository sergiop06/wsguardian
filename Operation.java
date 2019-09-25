package co.com.itac.wsguardian.common.service.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "wsg_operation")
@NamedQueries({
@NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o"),
@NamedQuery(name = "Operation.findAllByServiceId", query = "SELECT o FROM Operation o WHERE o.serviceId = :service"),
@NamedQuery(name = "Operation.findAllByServiceId2", query = "SELECT o FROM Operation o WHERE o.serviceId.id = :id"),
@NamedQuery(name = "Operation.findCountByServiceId", query = "SELECT COUNT(o) FROM Operation o WHERE o.serviceId = :service"),
@NamedQuery(name = "Operation.findByID", query = "SELECT o FROM Operation o WHERE o.id = :id")})

public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "REQUEST_PART_ELEMENT")
    private String requestPartElement;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SOAP_ACTIONS")
    private String soapActions;

    @OrderBy(value="isServerMode ASC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operationId", fetch = FetchType.LAZY)
    private List<OperationPolicy> operationPolicyCollection;

    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Service serviceId;

    @Transient
    private Map<Integer,PolicyAssociationRule> policyAssociationRuleCollection;

    public Operation() {
        policyAssociationRuleCollection = new HashMap<Integer, PolicyAssociationRule>();
    }

    public Operation(Integer id) {
        this.id = id;
    }

    public Operation(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Operation(Integer id, String name, String requestPartElement) {
        this.id = id;
        this.name = name;
        this.requestPartElement = requestPartElement;
    }

    public Map<Integer, PolicyAssociationRule> getPolicyAssociationRuleCollection() {
        return policyAssociationRuleCollection;
    }

    public void setPolicyAssociationRuleCollection(Map<Integer, PolicyAssociationRule> policyAssociationRuleCollection) {
        this.policyAssociationRuleCollection = policyAssociationRuleCollection;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OperationPolicy> getOperationPolicyCollection() {
        return operationPolicyCollection;
    }

    public void setOperationPolicyCollection(List<OperationPolicy> operationPolicyCollection) {
        this.operationPolicyCollection = operationPolicyCollection;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }
    
    public String getRequestPartElement() {
        return requestPartElement;
    }

    public void setRequestPartElement(String requestPartElement) {
        this.requestPartElement = requestPartElement;
    }

    public String getSoapActions() {
        return soapActions;
    }

    public void setSoapActions(String soapActions) {
        this.soapActions = soapActions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.itac.wsguardian.common.service.entity.Operation[id=" + id + "]";
    }

}
