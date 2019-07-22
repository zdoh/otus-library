package ru.zdoher.library.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.repositories.BookRepository;
import ru.zdoher.library.repositories.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoCommentCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        bookRepository.findById(id).ifPresent(book -> book.getComments().forEach(commentRepository::delete));
    }
}
