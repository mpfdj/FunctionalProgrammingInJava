package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Record implements Comparable<Record>{
    private String name;
    private String artist;
    private int releaseDate;
    private String genre;
    private BigDecimal euro;

    @Override
    public int compareTo(Record r) {
        return releaseDate - r.releaseDate;
    }
}
