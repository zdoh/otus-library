package ru.zdoher.library.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private MessageService messageService;

    public ConsoleServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public String getString()  {
        return new Scanner(System.in).nextLine();
    }

    @Override
    public void printString(String string) {
        System.out.println(string);
    }

    @Override
    public void printServiceMessage(String string) {
        printString(messageService.getMessage(string));
    }
}
