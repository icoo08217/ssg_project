package com.ll.exam;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class App {
    Scanner sc;

    public App() {
        sc = new Scanner(System.in);
    }

    public void run() throws JsonProcessingException {
        System.out.println("== 명언 SSG ==");

        WiseSayingController wiseSayingController = new WiseSayingController(sc);

        outer:
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);
            switch (rq.getPath()) {

                case "등록":
                    wiseSayingController.write(rq);
//                    wiseSayingController.restore();
                    break;

                case "목록":
                    wiseSayingController.list(rq);
                    break;

                case "삭제":
                    wiseSayingController.remove(rq);
                    break;

                case "수정":
                    wiseSayingController.modify(rq);
                    break;

                case "종료":
                    break outer;
            }
        }
        sc.close();
    }
}
