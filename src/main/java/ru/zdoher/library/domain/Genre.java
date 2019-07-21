package ru.zdoher.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document
public class Genre  {

    @Id
    private String id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
