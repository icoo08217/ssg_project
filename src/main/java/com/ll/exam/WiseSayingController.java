package com.ll.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class WiseSayingController {

    public Scanner sc;
    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingRepository = new WiseSayingRepository();
    }

    public void modify(Rq rq) { // 수정부분 구현 -- 수정?id=1
        // URL에 입력된 id 얻기
        int paramId = rq.getIntParam("id", 0);

        // URL에 입력된 id에 해당하는 명언 객체 찾기
        WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

        // URL에 입력된 id가 없다면 작업 중지
        if (paramId <= 0 ) {
            System.out.println("올바른 id를 입력해주세요.");
            return;
        }


        // 찾지 못했다면 중지
        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n" , paramId);
            return;
        }

        System.out.println("기존 명언 : " + foundWiseSaying.content);
        System.out.print("새 명언 : ");
        foundWiseSaying.content = sc.nextLine().trim();

        System.out.println("기존 작가 : " + foundWiseSaying.author);
        System.out.print("새 작가 : ");
        foundWiseSaying.author = sc.nextLine().trim();

        System.out.printf("%d번 명언이 수정되었습니다.\n" , paramId) ;
    }

    public void remove(Rq rq) {
        int paramId = rq.getIntParam("id", 0);

        if (paramId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n" , paramId);
            return;
        }

        // 입력된 id에 해당하는 명언객체를 리스트에서 삭제
        wiseSayingRepository.wiseSayings.remove(foundWiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSayingRepository.wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying_ = wiseSayingRepository.wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n" , wiseSaying_.id , wiseSaying_.author , wiseSaying_.content);
        }
    }

    public void write(Rq rq) {
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();

        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();

        int id = ++wiseSayingRepository.wiseSayingLastId; // 명언 글 번호 증가

        WiseSaying wiseSaying = new WiseSaying(id, content , author);
        wiseSayingRepository.wiseSayings.add(wiseSaying);

        System.out.printf( "%d번 명언이 등록되었습니다.\n" , id);
    }

    public void restore() throws JsonProcessingException { // 모든 기능들이 끝나면 json파일을 새로 저장해야한다 ?
        ObjectMapper objectMapper = new ObjectMapper();
        String JsonStr = objectMapper.writeValueAsString(wiseSayingRepository.wiseSayings);
        System.out.println(JsonStr);
    }
}
