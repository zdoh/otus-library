package ru.zdoher.library.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.zdoher.library.domain.Author;
import ru.zdoher.library.domain.Book;
import ru.zdoher.library.domain.Comment;
import ru.zdoher.library.domain.Genre;

@ChangeLog(order = "000")
public class InitMongoDBDataChangeLog {

    private Author author1;
    private Author author2;

    private Genre genre1;
    private Genre genre2;

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;
    private Comment comment4;
    private Comment comment5;
    private Comment comment6;

    @ChangeSet(order = "000", id = "dropDB", author = "zdoh", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthor", author = "zdoh", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        author1 = template.save(new Author("Анджей Сапковский"));
        author2 = template.save(new Author("Говард Лавкрафт"));
    }

    @ChangeSet(order = "002", id = "initGenre", author = "zdoh", runAlways = true)
    public void initGenres(MongoTemplate template) {
        genre1 = template.save(new Genre("фэнтези"));
        genre2 = template.save(new Genre("ужасы"));
    }

/*    @ChangeSet(order = "003", id = "initCommenst", author = "zdoh", runAlways = true)
    public void initComments(MongoTemplate template) {
        comment1 = template.save(new Comment("очень классная книга"));
        comment2 = template.save(new Comment("читал оторваться не мог"));
        comment3 = template.save(new Comment("не читал, но одобряю"));
        comment4 = template.save(new Comment("название внушает уважение"));
        comment5 = template.save(new Comment("из-за названия купил, но пока не читал"));
        comment6 = template.save(new Comment("не понравилось, игра лучше"));
    }*/

/*    @ChangeSet(order = "004", id = "initBook", author = "zdoh", runAlways = true)
    public void initBooks(MongoTemplate template) {
        template.save(new Book("Последнее желание", author1, genre1, comment1));
        template.save(new Book("Меч Предназначения", author1, genre1, comment2, comment6));
        template.save(new Book("Кровь эльфов", author1, genre1, comment5));
        template.save(new Book("Зов Ктулху", author2, genre2, comment3));
        template.save(new Book("Данвический ужас", author2, genre2, comment4));
    }*/

    @ChangeSet(order = "003", id = "initBook", author = "zdoh", runAlways = true)
    public void initBooks(MongoTemplate template) {
        template.save(new Book("Последнее желание", author1, genre1, new Comment("очень классная книга")));
        template.save(new Book("Меч Предназначения", author1, genre1, new Comment("читал оторваться не мог"), new Comment("не понравилось, игра лучше")));
        template.save(new Book("Кровь эльфов", author1, genre1, new Comment("из-за названия купил, но пока не читал")));
        template.save(new Book("Зов Ктулху", author2, genre2, new Comment("не читал, но одобряю")));
        template.save(new Book("Данвический ужас", author2, genre2, new Comment("название внушает уважение")));
    }

}

