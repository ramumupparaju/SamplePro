package com.android.samplepro.filter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubLocationResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subname")
    @Expose
    private String subname;
    @SerializedName("zoneid")
    @Expose
    private Integer zoneid;
    @SerializedName("location")
    @Expose
    private Location location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public Integer getZoneid() {
        return zoneid;
    }

    public void setZoneid(Integer zoneid) {
        this.zoneid = zoneid;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
