package pl.skrys.app;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "building")
public class SpBuilding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;




    private String nazwa;

    private String city;
    private String street;
    private String buildingNumber;
    private String postalCode;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "building")
    private List<SpFlat> flat;


    public void removeFlat(SpFlat flat) {
        this.flat.remove(flat);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<SpFlat> getFlat() {
        return flat;
    }

    public void setFlat(List<SpFlat> flat) {
        this.flat = flat;
        this.flat.forEach(spflat -> spflat.setBuilding(this));
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
