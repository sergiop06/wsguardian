package co.com.itac.wsguardian.common.policy.entity;

import co.com.itac.library.common.entity.BaseEntity;
import co.com.itac.wsguardian.common.policytype.entity.PolicyType;
import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "wsg_policy")
@NamedQueries({
@NamedQuery(name = "Policy.findAll", query = "SELECT p FROM Policy p"),
@NamedQuery(name = "Policy.findByPolicyType", query = "SELECT p FROM Policy p WHERE p.policyTypeId.id = :policyTypeId"),
@NamedQuery(name = "Policy.findByName", query = "SELECT p FROM Policy p WHERE p.name = :name"),
@NamedQuery(name = "Policy.findByID", query = "SELECT p FROM Policy p WHERE p.id.id = :id"),
@NamedQuery(name = "Policy.findPolicyByType", query = "SELECT p FROM Policy p WHERE p.policyTypeId = :policyType"),
@NamedQuery(name = "Policy.findPolicyByTypeId", query = "SELECT p FROM Policy p WHERE p.policyTypeId.id = :policyType"),
@NamedQuery(name = "Policy.findBySubPolicy", query = "SELECT policyCollection FROM Policy p WHERE p.id = :id")})
public class Policy extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7540516029647312850L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;

    @JoinTable(name = "wsg_sub_policy", joinColumns = {@JoinColumn(name = "CHILD_POLICY_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "POLICY_PARENT_ID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Policy> parentPolicyCollection;

    @JoinTable(name = "wsg_sub_policy", joinColumns = {@JoinColumn(name = "POLICY_PARENT_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "CHILD_POLICY_ID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Policy> policyCollection;

    @JoinColumn(name = "POLICY_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PolicyType policyTypeId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "policyId",fetch = FetchType.EAGER)
    @MapKey(name="alias")
    private Map<String,PolicyParameter> policyParameterCollection;

    public Policy() {
    }

    public Policy(Integer id) {
        this.id = id;
    }

    public Policy(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public List<Policy> getPolicyCollection() {
        return policyCollection;
    }

    public void setPolicyCollection(List<Policy> policyCollection) {
        this.policyCollection = policyCollection;
    }

    public PolicyType getPolicyTypeId() {
        return policyTypeId;
    }

    public void setPolicyTypeId(PolicyType policyTypeId) {
        this.policyTypeId = policyTypeId;
    }

    public Map<String, PolicyParameter> getPolicyParameterCollection() {
        return policyParameterCollection;
    }

    public void setPolicyParameterCollection(Map<String, PolicyParameter> policyParameterCollection) {
        this.policyParameterCollection = policyParameterCollection;
    }

    public List<Policy> getParentPolicyCollection() {
        return parentPolicyCollection;
    }

    public void setParentPolicyCollection(List<Policy> parentPolicyCollection) {
        this.parentPolicyCollection = parentPolicyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Policy)) {
            return false;
        }
        Policy other = (Policy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.itac.wsguardian.common.policy.entity.Policy[id=" + id + "]";
    }

}
