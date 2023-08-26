package io.wizarddleex.shorturl.data.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrl {
    @Id
    private String id;
    private String shortUrl;
    private String longUrl;
    @CreatedDate
    private LocalDate dateCreated;
    private LocalTime timeCreated = LocalTime.now();

    @Override
    public String toString() {
        return "ShortUrl{" +
                "shortUrl='" + shortUrl + '\'' +
                ", longUrl='" + longUrl + '\'' +
                ", dateCreated=" + dateCreated +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
