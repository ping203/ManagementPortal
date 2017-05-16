package org.radarcns.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Device.
 */
@Entity
@Table(name = "device")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "device_physical_id", nullable = false)
    private String devicePhysicalId;

    @NotNull
    @Column(name = "device_category", nullable = false)
    private String deviceCategory;

    @NotNull
    @Column(name = "activated", nullable = false)
    private Boolean activated;

    @ManyToOne
    private DeviceType deviceType;

    @ManyToMany(mappedBy = "devices")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Project> projects = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevicePhysicalId() {
        return devicePhysicalId;
    }

    public Device devicePhysicalId(String devicePhysicalId) {
        this.devicePhysicalId = devicePhysicalId;
        return this;
    }

    public void setDevicePhysicalId(String devicePhysicalId) {
        this.devicePhysicalId = devicePhysicalId;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public Device deviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
        return this;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public Boolean isActivated() {
        return activated;
    }

    public Device activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public Device deviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Device projects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Device addProject(Project project) {
        this.projects.add(project);
        project.getDevices().add(this);
        return this;
    }

    public Device removeProject(Project project) {
        this.projects.remove(project);
        project.getDevices().remove(this);
        return this;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        if (device.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, device.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Device{" +
            "id=" + id +
            ", devicePhysicalId='" + devicePhysicalId + "'" +
            ", deviceCategory='" + deviceCategory + "'" +
            ", activated='" + activated + "'" +
            '}';
    }
}
