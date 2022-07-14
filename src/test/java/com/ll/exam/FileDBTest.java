package com.ll.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDBTest {

    @BeforeEach // 각 테스트가 실행 되기전에 항상 실행되는
    void beforeEach() {
        Util.deleteDir("test_data");
        Util.mkdir("test_data");
    }

    @Test
    void 특정_폴더에_존재하는_모든_파일의_이름들을_가져온다(){
        Util.saveNumberToFile("test_data/1.txt" , 1);
        Util.saveNumberToFile("test_data/2.txt" , 1);
        Util.saveNumberToFile("test_data/3.txt" , 1);

        List<String> fileNames = Util.getFileNamesFromDir("test_data");

        assertEquals("1.txt" ,fileNames.get(2));
        assertEquals("2.txt" ,fileNames.get(1));
        assertEquals("3.txt" ,fileNames.get(0));

    }

    @Test
    void 파일에_숫자_저장(){
        Util.saveNumberToFile("test_data/last_id.txt", 100);

        int rs = Util.readNumberFromFile("test_data/last_id.txt", 0);

        assertEquals(100,rs);
    }

    @Test
    public void 특정_폴더에_들어있는_JSON_파일들만_(){
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json" , wiseSaying.toJson());

        String rs = Util.readFromFile("test_data/1.json");
        Map<String, Object> map = Util.jsonToMap(rs);
        WiseSaying loadedWiseSaying = new WiseSaying(map);

        assertEquals(1 , map.get("id"));
        assertEquals("내 사전에 불가능은 없다." , map.get("content"));
        assertEquals(wiseSaying , loadedWiseSaying);
    }

    @Test
    public void 파일에_있는_JSON을_객체로_변환(){
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json" , wiseSaying.toJson());

        String rs = Util.readFromFile("test_data/1.json");
        Map<String, Object> map = Util.jsonToMap(rs);
        WiseSaying loadedWiseSaying = new WiseSaying(map);

        assertEquals(1 , map.get("id"));
        assertEquals("내 사전에 불가능은 없다." , map.get("content"));
        assertEquals(wiseSaying , loadedWiseSaying);
    }

    @Test
    public void 파일에_있는_JSON을_맵으로_변환(){
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json" , wiseSaying.toJson());

        String rs = Util.readFromFile("test_data/1.json");
        Map<String, Object> map = Util.jsonToMap(rs);

        System.out.println(map);

        assertEquals(1 , map.get("id"));
        assertEquals("내 사전에 불가능은 없다." , map.get("content"));
        assertEquals("나폴레옹" , map.get("author"));
    }

    @Test
    public void 파일에_객체를_저장(){
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        // json 형식 { }
        Util.saveToFile("test_data/1.json" , wiseSaying.toJson());

        String rs = Util.readFromFile("test_data/1.json");

        assertEquals(wiseSaying.toJson() , rs);
    }

    @Test
    public void 파일에_내용쓰기(){ // 파일 저장
        Util.mkdir("test_data");
        Util.saveToFile("test_data/1.json" , "내용\n내용");

        String rs = Util.readFromFile("test_data/1.json");

        assertEquals("내용\n내용" , rs);
    }
}
