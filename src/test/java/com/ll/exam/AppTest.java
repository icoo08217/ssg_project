package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void 파일에_객체를_저장(){
        Util.mkdir("test_data");
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        // json 형식 { }
        Util.saveToFile("test_data/1.json" , wiseSaying.toJson());

        String rs = Util.readFromFile("test_data/1.json");

        assertEquals(wiseSaying.toJson() , rs);
    }

    @Test
    public void 파일에_내용쓰기(){
        Util.mkdir("test_data");
        Util.saveToFile("test_data/1.json" , "내용\n내용");

        String rs = Util.readFromFile("test_data/1.json");

        assertEquals("내용\n내용" , rs);
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
