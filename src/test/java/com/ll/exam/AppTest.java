package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void 파일에_내용쓰기(){
        Util.mkdir("test_data");
        Util.saveToFile("test_data/1.json" , "내용");
    }

    @Test
    public void Rq__getPath(){
        Rq rq = new Rq("삭제?id=1");

        String path = rq.getPath();

        assertEquals("삭제" , path);
    }

    @Test
    public void Rq__getIntParam() { //
        Rq rq = new Rq("삭제?id=1");

        int id = rq.getIntParam("id", 0);

        assertEquals(1 ,id);
    }

    @Test
    public void Rq__getIntParam2() { //
        Rq rq = new Rq("검색?id=10&no=1");

        int id = rq.getIntParam("id", 0);
        int no = rq.getIntParam("no", 0);

        assertEquals(10 ,id);
        assertEquals(1, no);
    }
}
