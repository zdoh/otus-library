package ru.zdoher.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Comment {

    private UUID id = UUID.randomUUID();

    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }
}

