package db.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "places")
public final class Places {
    @Id
    private int id;
    @Column(name = "\"row\"")
    private Integer row;
    @Column(name = "place_num")
    private Integer placeNum;
    @Column(name = "\"name\"")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(final Integer row) {
        this.row = row;
    }

    public Integer getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(final Integer placeNum) {
        this.placeNum = placeNum;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
