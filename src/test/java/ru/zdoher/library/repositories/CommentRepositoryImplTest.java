package ru.zdoher.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Класс CommentRepository")
@DataJpaTest
@Import({CommentRepositoryImpl.class, BookRepositoryImpl.class})
class CommentRepositoryImplTest {
    private static final Long FIRST_ID = 1L;
    private static final String NEW_COMMENT = "newComment";
    private static final Long NEW_COMMENT_ID = 7L;
    private static final Long WRONG_DEL_COMMENT_ID = 5L;


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName(" добавление коммантария корректно")
    @Test
    void addCommentToBook() {
        commentRepository.insert(new Comment(NEW_COMMENT, bookRepository.getById(FIRST_ID)));

        Comment comment = commentRepository.getById(NEW_COMMENT_ID);

        assertThat(comment)
                .matches( c -> c.getComment().equals(NEW_COMMENT))
                .matches( c -> c.getId().equals(NEW_COMMENT_ID))
                .matches( c -> c.getBook().getId().equals(FIRST_ID));
    }

    @DisplayName(" проверка удаление комментария пренадлежащей книге")
    @Test
    void checkCorrectCommentDelete() {
        boolean bool = commentRepository.commentInBookExist(FIRST_ID, FIRST_ID);

        assertThat(bool).isEqualTo(true);

    }

    @DisplayName(" проверка удаление комментария не пренадлежащей книге")
    @Test
    void checkWrongCommentDelete() {
        boolean bool = commentRepository.commentInBookExist(FIRST_ID, WRONG_DEL_COMMENT_ID);

        assertThat(bool).isEqualTo(false);

    }
}