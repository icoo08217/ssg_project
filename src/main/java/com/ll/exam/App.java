package com.ll.exam;

import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("== 명언 SSG ==");

        Scanner sc = new Scanner(System.in);

        outer:
        while(true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();
            int index = 1;

            switch (cmd) {
                case "종료":
                    break outer;

                case "등록":
                    System.out.printf("명언 : ");
                    String content = sc.nextLine().trim();

                    System.out.printf("작가 : ");
                    String author = sc.nextLine().trim();

                    System.out.println( index + "번 명언이 등록되었습니다.\n");
                    break;
                case "목록":
                    break;
                case "수정":
                    break;
                case "삭제":
                    break;
            }
        }
        sc.close();
    }
}
