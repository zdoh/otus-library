package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.zdoher.library.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс CommentRepository")
@DataMongoTest
class CommentRepositoryTest {
    private static final String NEW_COMMENT = "newComment";

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName(" создание и получение - корректно")
    @Test
    void commentCreateAndGet() {
        final Comment newComment = commentRepository.insert(new Comment(NEW_COMMENT));

        Comment comment = commentRepository.findById(newComment.getId()).orElse(null);

        assertThat(comment).isNotNull()
                .matches( c -> c.getComment().equals(NEW_COMMENT))
                .matches( c -> c.getId().equals(newComment.getId()));
    }

}
