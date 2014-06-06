package ug.progress.monitoring.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by Ruslan Zekokh.
 */
@Entity
@Table(name = "location")
public class LocationEntity implements Serializable {


    /**
     * id записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_key_gen")
    @SequenceGenerator(sequenceName = "location_id_seq", allocationSize = 1, name = "location_id_key_gen")
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
    private String longitude;

    /**
     * Широта
     */
    @Column(name = "latitude")
    private String latitude;

    /**
     * Дата и время получение координат
     */
    @Temporal(TemporalType.TIMESTAMP)
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
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
