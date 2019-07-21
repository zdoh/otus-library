package ru.zdoher.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    private String id;

    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }
}

