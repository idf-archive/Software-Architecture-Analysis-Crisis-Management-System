/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 6:24 PM
 */
package com.springapp.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class IncidentRecord implements Comparable{
    private String description;
    private String address;
    private int levelReported;

    /* de-normalized */
    private String typeName;
    private int defaultEmergencyLevel;
    private Calendar timestamp;

    public IncidentRecord() {

    }

    public IncidentRecord(String description, String address) {

        this.description = description;
        this.address = address;
        this.timestamp = GregorianCalendar.getInstance();
        this.typeName = "Haze";
        this.defaultEmergencyLevel = 1;

    }

    public int getLevel() {
        if(this.levelReported==0 || this.levelReported==this.defaultEmergencyLevel)
            return this.defaultEmergencyLevel;
        else
            return this.levelReported;
    }

    @Override
    public int hashCode(){
//        System.out.println("Hashcode debugging: "+this.address+" "+this.address.hashCode());
        return this.address.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof IncidentRecord))return false;
        IncidentRecord obj1 = (IncidentRecord) obj;
        return obj1.typeName.equals(this.typeName) && obj1.address.equals(this.address) && obj1.getTimestamp().equals(this.getTimestamp());

    }

    @Override
    public String toString() {
        return "IncidentRecord{" +
                "description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", levelReported=" + levelReported +
                ", typeName='" + typeName + '\'' +
                ", defaultEmergencyLevel=" + defaultEmergencyLevel +
                ", timestamp=" + timestamp +
                '}';
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public Calendar getTimestamp() {
        return timestamp; // Sat Oct 19 14:05:58 SGT 2013
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public int getLevelReported() {
        return levelReported;
    }

    public void setLevelReported(int levelReported) {
        this.levelReported = levelReported;
    }

    public int getDefaultEmergencyLevel() {
        return defaultEmergencyLevel;
    }

    public void setDefaultEmergencyLevel(int defaultEmergencyLevel) {
        this.defaultEmergencyLevel = defaultEmergencyLevel;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj == null) return 0;
        if (obj == this) return 0;
        if (!(obj instanceof IncidentRecord))return 0;
        IncidentRecord obj1 = (IncidentRecord) obj;

        return this.timestamp.getTimeInMillis() - obj1.timestamp.getTimeInMillis()<0?-1:1;

    }
}
