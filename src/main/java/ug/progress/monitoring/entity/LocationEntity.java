package ug.progress.monitoring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZR on 01.06.2014.
 */
@Entity
@Table(name = "location")
public class LocationEntity implements Serializable {


    /**
     * id записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Id пользователя
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * Долгода
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * Широта
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * Дата и время получение координат
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    /**
     * Apple id устройства
     */
    @Column(name = "apple_id")
    private String appleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAppleId() {
        return appleId;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }
}
