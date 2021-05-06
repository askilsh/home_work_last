package db.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
public final class Animal {
    @Id
    private int id;
    @Column(name = "\"name\"")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "\"type\"")
    private  Integer type;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "place")
    private  Integer place;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Integer getType() {
        return type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(final Integer sex) {
        this.sex = sex;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(final Integer place) {
        this.place = place;
    }
}
