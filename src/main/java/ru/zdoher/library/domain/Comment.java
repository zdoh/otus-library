package ru.zdoher.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Comment {

    private UUID id;

    private String comment;

    public Comment(String comment) {
        this.comment = comment;
        id = UUID.randomUUID();
    }
}

