package com.tundemicahel.seamfix;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael.Orokola
 */
@XmlRootElement
public class Subscriber implements Serializable {

    private String phone;
    private String firstname;
    private String lastName;
    private int age;

    public Subscriber() {
    }

    public Subscriber(String phone, String firstname, String lastName, int age) {
        this.phone = phone;
        this.firstname = firstname;
        this.lastName = lastName;
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    @XmlElement
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    @XmlElement
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    @XmlAttribute
    public void setAge(int age) {
        this.age = age;
    }

}
