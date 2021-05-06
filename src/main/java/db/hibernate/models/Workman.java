package db.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workman")
public final class Workman {
    @Id
    private int id;
    @Column(name = "\"name\"")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "position")
    private  Integer pos;

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

    public Integer getPos() {
        return pos;
    }

    public void setPos(final Integer pos) {
        this.pos = pos;
    }
}
