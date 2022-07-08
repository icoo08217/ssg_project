package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void 태스트_실험() {
        int result = 10 + 20;
        assertEquals(30, result);
    }

    @Test
    public void 테스트_스캐너() {
        String input = """
                등록
                명언1
                작가1""".stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);

        String cmd = sc.nextLine().trim();
        String content = sc.nextLine().trim();
        String author = sc.nextLine().trim();

        assertEquals("등록" , cmd);
        assertEquals("명언1" , content);
        assertEquals("작가1" , author);


    }
}
