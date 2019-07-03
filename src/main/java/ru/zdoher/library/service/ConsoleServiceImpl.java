package ru.zdoher.library.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Override
    public String getString()  {
        return new Scanner(System.in).nextLine();
    }

    @Override
    public void printString(String string) {
        System.out.println(string);
    }
}
