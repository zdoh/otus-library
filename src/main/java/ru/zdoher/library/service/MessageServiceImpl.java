package ru.zdoher.library.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class MessageServiceImpl implements MessageService {

    private Locale locale;

    private MessageSource ms;

    public MessageServiceImpl(MessageSource ms) {
        locale = new Locale("ru");
        this.ms = ms;
    }

    public String getMessage(String field) {
        return ms.getMessage(field, null, locale);
    }


}
