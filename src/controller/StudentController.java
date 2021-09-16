package controller;

import student.Schedule;
import student.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentController {
    private final Scanner scanner = new Scanner(System.in);
    private final Student[] s = new Student[SIZE];
    private final Schedule[] c = new Schedule[SIZE];

    public static final int SIZE = 50;
    String Score = null;
    String avg;
    int targetNum = 0;

    // 시간표 수정을 위한 조건
    boolean modify = false;


    // 객체 입력
    public StudentController() {
        //성적 객체
        s[0] = new Student("1학년 1학기", "자바프로그래밍", 3, "A", 4.0);
        s[1] = new Student("1학년 1학기", "소프트웨어공학", 2, "B+", 3.5);
        s[2] = new Student("1학년 2학기", "데이터베이스", 3, "B+", 3.5);
//        s[3] = new Student("1학년 2학기", "통신개론", 3, 0.0, "F");
//        s[4] = new Student("2학년 1학기", "프로그래밍언어론", 3, 4.5, "A+");
//        s[5] = new Student("2학년 1학기", "컴퓨터활용", 3, 4.5, "A+");
//        s[6] = new Student("2학년 2학기", "컴퓨터구조", 3, 2.0, "C");
//        s[7] = new Student("2학년 2학기", "현대인과영양", 3, 3.5, "B+");
//        s[8] = new Student("3학년 1학기", "데이터구조", 3, 3.0, "B");
//        s[9] = new Student("3학년 1학기", "웹서버설계및구축", 3, 3.5, "B+");
//        s[10] = new Student("3학년 2학기", "운영체제", 3, 4.0, "A");
//        s[11] = new Student("3학년 2학기", "유비쿼터스컴퓨팅", 3, 4.5, "A+");
//        s[12] = new Student("4학년 1학기", "그리스신화속의사랑", 3, 2.5, "C+");
//        s[13] = new Student("4학년 1학기", "데이터통신", 3, 3.5, "B+");
//        s[14] = new Student("4학년 2학기", "디지털콘텐츠", 3, 4.0, "A");
//        s[15] = new Student("4학년 2학기", "중국어기초", 2, 2.5, "C+");


        //시간표 객체
        c[0] = new Schedule("1학년 1학기", "자바프로그래밍",10, 12);
        c[1] = new Schedule("1학년 1학기", "소프트웨어공학",13, 15);
        c[2] = new Schedule("1학년 1학기", "데이터베이스",17, 18);

    }


    //실제 저장된 과목의 숫자를 반환
    public int existNum() {
        int count = 0; // 숫자를 세는 변수
        for (int i = 0; i < s.length; i++) {
            if (s[i] == null) {
                break;
            }
            count++;
        }
        return count;
    }

    public int existNumShedule() {
        int count = 0; // 숫자를 세는 변수
        for (int i = 0; i < c.length; i++) {
            if (c[i] == null) {
                break;
            }
            count++;
        }
        return count;
    }


    //성적 입력 메서드
    public void insert(String semester, String subject, int credit, String score, double changeScore) {
        int count = existNum();
        s[count] = new Student(semester, subject, credit, score, changeScore);
    }

    //과목 수정 메서드
    public boolean modifySubject(String targetSubject) {
        for (int i = 0; i < existNum(); i++) {
            if (targetSubject.equals(s[i].getSubject())) {
                System.out.println();
                System.out.println(s[i].showScore());
                System.out.println();
                targetNum = i;
                return true;
            }
        }
        return false;
    }

    //학기와 성적 수정 메서드
    public void modifySemesterScore(String targetSemester) {
        System.out.println("\n=============== 수정하실 학기의 입력 정보 =================");
        System.out.println("\n|   학기   |   과목명   |  이수학점  |   성적   |   학점   |");
        for (int i = 0; i < existNum(); i++) {
            // 이 if문으로 인해 입력 한 학기에 일치하는 해당 배열만 수정 됨.
            if (targetSemester.equals(s[i].getSemester())) {
                System.out.println(s[i].showScore());
            }
        }
        for (int i = 0; i < existNum(); i++) {
            while (true) {
                // 과목 입력
                System.out.print("\n수정할 점수의 과목명을 입력하세요>> ");
                String subject = scanner.next();
                if (!modifySubject(subject)) {
                    System.out.println("과목명이 일치하지 않습니다.");
                    continue;
                } else {

                    //성적 입력
                    System.out.print("수정할 성적을 입력하세요>> ");
                    Score = scanner.next();
                    if (!(Score.equals("A") || Score.equals("A+") || Score.equals("B") || Score.equals("B+")
                            || Score.equals("C") || Score.equals("C+") || Score.equals("D") || Score.equals("D+") || Score.equals("F"))) {
                        System.out.println("## A, A+, B, B+, C, C+, D, D+, F 중의 점수를 입력하세요.");
                        continue;
                    } else {
                        System.out.println();
                        // 입력받은 성적으로 수정
                        s[targetNum].setScore(Score);
                        // 입력받은 성적은 changeScore 메서드로 변경
                        s[i].setChangeScore(changeScore(Score));
                        System.out.println("\n==================== 수정 완료 성적 ====================\n");
                        System.out.println("|   학기   |   과목명   |  이수학점  |   성적   |   학점   |");
                        System.out.println();
                        System.out.println(s[targetNum].showScore());
                        System.out.println();
                        break;
                    }
                }
            }
            break;
        }
        return;
    }

    //성적 삭제 메서드
    public boolean delete(String semaester, String subject) {
        int count = existNum();
        //삭제할 데이터 인덱스 구하기
        int delIdx = -1;
        for (int i = 0; i < count; i++) {
            if (semaester.equals(s[i].getSemester())) {
                if (subject.equals(s[i].getSubject())) {
                    delIdx = i;
                    break;
                }
            }
        }

        //삭제 알고리즘
        if (delIdx != -1) {
            for (int i = delIdx; i < count - 1; i++) {
                s[i] = s[i + 1];
            }
            s[count - 1] = null; //마지막 데이터 null로 변경
            return true;
        }
        return false;
    }


    //입력받은 점수를 학점으로 변환하는 메서드
    public double changeScore(String score) {

        double changeScore = 0;
        switch (score) {
            case "A+":
                changeScore = 4.5;
                break;
            case "A":
                changeScore = 4.0;
                break;
            case "B+":
                changeScore = 3.5;
                break;
            case "B":
                changeScore = 3.0;
                break;
            case "C+":
                changeScore = 2.5;
                break;
            case "C":
                changeScore = 2.0;
                break;
            case "D+":
                changeScore = 1.5;
                break;
            case "D":
                changeScore = 1.0;
                break;
            case "F":
                changeScore = 0.0;
                break;

        }
        return changeScore;
    }


    // 평균학점 구하는 식: (과목1 점수 * 과목1 이수학점) + (과목2 점수 * 과목2 이수학점)... / 총 이수학점
    //총 이수학점 구하는 메서드
    public int getTotalCredit() {

        int totalCredit = 0;
        for (int i = 0; i < existNum(); i++) {
            totalCredit += s[i].getCredit();
        }
        return totalCredit;
    }

    //점수*이수학점 구하는 메서드
    public double getTscore() {

        double tScore = 0;
        for (int i = 0; i < existNum(); i++) {
            tScore += s[i].getChangeScore() * s[i].getCredit();
        }
        return tScore;
    }

    //학기별 성적 출력하는 메서드
    public void printSemester(String semester) {

        System.out.printf("\n==================== %s 성적 ====================\n", semester);
        System.out.println("|   학기   |   과목명   |  이수학점  |   성적   |   학점   |");
        for (int i = 0; i < existNum(); i++) {
            if (semester.equals(s[i].getSemester())) {
                System.out.println(s[i].showScore());
            }
        }
        calculateOneAvg(semester);
        System.out.println();
    }

    //학기별 평균 학점 구해서 출력하는 메서드
    public void calculateOneAvg(String semester) {
        String semesterAvg;
        double sTotalScore = 0;
        int sTotalCredit = 0;
        for (int i = 0; i < existNum(); i++) {
            if (semester.equals(s[i].getSemester())) {
                sTotalScore += s[i].getChangeScore() * s[i].getCredit();
                sTotalCredit += s[i].getCredit();
            }
        }
        double tempAvg = 0.0;
        if (sTotalCredit <= 0) {
            semesterAvg = "0";
        } else {
            tempAvg = sTotalScore / sTotalCredit;
            semesterAvg = String.format("%.2f", tempAvg);
        }
        System.out.printf("총 이수학점 : %d   /   평균 학점 : %s\n", sTotalCredit, semesterAvg);
    }

    // 총 평균 학점 구해서 출력하는 메서드
    public void calculateAvg(double totalScore, int totalCredit) {

        double tempAvg = getTscore() / getTotalCredit();
        avg = String.format("%.2f", tempAvg);

        System.out.printf("총 이수학점 : %d   /   평균 학점 : %S\n", getTotalCredit(), avg);
    }

    //전체 출력 메서드
    public Student[] printAll() {
        return s;
    }



    // 시간표를 만들기 위한 학기와 과목 출력 ( target 입력받아야함 )
    public Student[] scheduleMenu() {
        System.out.println("====================================");
        System.out.println("\n## 시간표 관련 메뉴를 선택하세요.");
        System.out.println("[1]시간표 입력");
        System.out.println("[2]시간표 조회");
        System.out.println("[3]시간표 수정");
        System.out.println("[4]시간표 삭제");
        System.out.println("====================================");
        System.out.print("메뉴 입력 >> ");
        int Num = scanner.nextInt();
        switch (Num) {
            case 1:
                scheduleInsert();
                break;
            case 2:
                scheduleView();
                break;
            case 3:
                scheduleModify();
                break;
            case 4:
                scheduledelete();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }
        return null;
    }

    //시간표 입력 메서드
    public void scheduleInsert(String semester, String subject, int time1, int time2) {
        int count = existNumShedule();
        c[count] = new Schedule(semester, subject, time1, time2);
    }

    public void scheduleInsert() {

        String semester;
        String subject;
        int time1;
        int time2;
        String menuNo;

        System.out.println("====================================");
        semester = seasonMenu();

        while (true) {
            System.out.println("============= 과목 목록 ==============");
            System.out.println("1. 자바프로그래밍 - 홍길동 교수님");
            System.out.println("2. 소프트웨어공학 - 뽀로로 교수님");
            System.out.println("3. 데이터베이스 - 김영희 교수님");
            System.out.println("4. 통신개론 - 신짱구 교수님");
            System.out.println("5. 컴퓨터활용 - 신형만 교수님");
            System.out.println("6. 데이터구조 - 봉미선 교수님");
            System.out.println("7. 웹서버설계및구축 - 한유리 교수님");
            System.out.println("8. 현대인과영양 - 신짱아 교수님");
            System.out.println("9. 실용영어 - 이훈이 교수님");
            System.out.println("10. 인성심리학 - 이맹구 교수님");
            System.out.println("====================================");

            //과목 입력
            System.out.print("과목 입력: ");
            subject = scanner.next();
            if (!subject.equals("자바프로그래밍") && !subject.equals("소프트웨어공학") && !subject.equals("데이터베이스")
                    && !subject.equals("통신개론") && !subject.equals("컴퓨터활용") && !subject.equals("데이터구조")
                    && !subject.equals("웹서버설계및구축") && !subject.equals("현대인과영양") && !subject.equals("실용영어") && !subject.equals("인성심리학")
                    && !subject.equals("1") && !subject.equals("2") && !subject.equals("3")
                    && !subject.equals("4") && !subject.equals("5") && !subject.equals("6")
                    && !subject.equals("7") && !subject.equals("8") && !subject.equals("9")
                    && !subject.equals("10")){
                System.out.println("과목명을 다시 입력해주세요.");
                continue;
            } else if (subject.equals("1"))
                subject = "자바프로그래밍";
            else if (subject.equals("2")) {
                subject = "소프트웨어공학";
            } else if (subject.equals("3")) {
                subject = "데이터베이스";
            } else if (subject.equals("4")) {
                subject = "통신개론";
            } else if (subject.equals("5")) {
                subject = "컴퓨터활용";
            } else if (subject.equals("6")) {
                subject = "데이터구조";
            } else if (subject.equals("7")) {
                subject = "웹서버설계및구출";
            } else if (subject.equals("8")) {
                subject = "현대인과영양";
            } else if (subject.equals("9")) {
                subject = "실용영어";
            } else if (subject.equals("10")) {
                subject = "인성심리학";
            }

            while (true) {
                try {
                    System.out.println("");
                    System.out.println("## 강의 시간은 24시 단위로 입력합니다.");
                    System.out.println("## EX) 오후 1시 = 13시");
                    System.out.print("강의 시작 시간: ");
                    time1 = scanner.nextInt();

                    System.out.print("강의 종료 시간: ");
                    time2 = scanner.nextInt();

                    if (time1 > time2) {
                        System.out.println("");
                        System.out.println("시작시간이 종료시간보다 느릴 수 없습니다.");
                        continue;
                    }
                    else{
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("강의 시간은 숫자만 입력 할 수 있습니다.");
                    continue;
                }
            }

            // 시간표 배열 추가 메서드
            scheduleInsert(semester, subject, time1, time2);

            System.out.println("\n[추가 입력하기 : 1 / 메인메뉴로 돌아가기: 0]");
            System.out.print(">> ");
            menuNo = scanner.next();
            if (menuNo.equals("1")) {
                continue;
            } else {
                return;
            }

        }

    }

    public Student[] scheduleView() {
        System.out.println("");
        System.out.println("## 시간표를 조회를 시작합니다.");
        System.out.print("학기를 입력하세요 >> ");
        scanner.nextLine();
        String target = scanner.nextLine();
        System.out.printf("================ %s 시간표 ================ \n", target);
        System.out.println("");
        for (int i = 0; i < existNumShedule(); i++) {
            // 입력한 학기의 학기와 과목명만 출력
            if (target.equals(c[i].getScheduleSemester())) {
                System.out.println(c[i].getScheduleSemester() + " | " + c[i].getScheduleSubject() + " | " + c[i].getTime1() + "시 - " + c[i].getTime2() + "시");
            }
        }
        System.out.println("");
        return null;
    }

    public Student[] scheduleModify() {
        System.out.println("");
        System.out.println("## 시간표 수정을 시작합니다.");
        String target = null;
        int n1 = 0;
        System.out.print("수정하실 학기를 입력하세요 >> ");
        scanner.nextLine();
        target = scanner.nextLine();
        System.out.println(" ");
        System.out.println("");
        for (int i = 0; i < existNumShedule(); i++) {
            // 입력한 학기의 학기와 과목명만 출력
            if (target.equals(c[i].getScheduleSemester())) {
                System.out.println(c[i].showSchedule());
            }
        }

        System.out.println("");
        while (true) {
            System.out.print("수정하실 과목을 입력하세요 >> ");
            String target2 = scanner.nextLine();

            for (int i = 0; i < existNumShedule(); i++) {
                if (target2.equals(c[i].getScheduleSubject())) {
                    modify = true;
                    n1 = i;
                }
            }
            if (modify == true) {
                int time1 = 0;
                int time2 = 0;
                try {
                    System.out.println("");
                    System.out.println("## 강의 시간은 24시 단위로 입력합니다.");
                    System.out.println("## EX) 오후 1시 = 13시");
                    System.out.print("강의 시작 시간: ");
                    time1 = scanner.nextInt();
                    c[n1].setTime1(time1);

                    System.out.print("강의 종료 시간: ");
                    time2 = scanner.nextInt();
                    c[n1].setTime2(time2);

                    if(time1 > time2) {
                        System.out.println("");
                        System.out.println("시작시간이 종료시간보다 느릴 수 없습니다.");
                        continue;
                    }
                    System.out.println("");
                    System.out.printf(" ================ %s 수정 시간표 ================ \n", target);
                    System.out.println("");
                    System.out.println(c[n1].getScheduleSemester() + " | " + c[n1].getScheduleSubject() + " | " + c[n1].getTime1() + "시 - " + c[n1].getTime2() + "시");
                    System.out.println("");
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("강의 시간은 숫자만 입력 할 수 있습니다.");
                    continue;
                }
            }
            return null;
        }
        return null;
    }


    public void scheduledelete() {

        boolean check = false;

        System.out.println("");
        System.out.println("## 시간표 삭제를 시작합니다.");
        System.out.print("수정하실 학기를 입력하세요 >> ");
        scanner.nextLine();
        String target = scanner.nextLine();
        System.out.println(" ");
        int n1 = 0;
        System.out.printf("================ %s 시간표 ================ \n", target);
        System.out.println("");
        for (int i = 0; i < existNumShedule(); i++) {
            // 입력한 학기의 학기와 과목명만 출력
            if (target.equals(c[i].getScheduleSemester())) {
                System.out.println(c[i].getScheduleSemester() + " | " + c[i].getScheduleSubject() + " | " + c[i].getTime1() + "시 - " + c[i].getTime2() + "시");
            }
        }

        System.out.println("");

        while (true) {
            System.out.print("삭제하실 과목을 입력하세요 >> ");
            String target2 = scanner.nextLine();


            int count = existNumShedule();
            //삭제할 데이터 인덱스 구하기
            int delIdx = -1;
            for (int i = 0; i < count; i++) {
                if (target.equals(c[i].getScheduleSemester())) {
                    if (target2.equals(c[i].getScheduleSubject())) {
                        delIdx = i;
                        break;
                    }
                }
            }

            //삭제 알고리즘
            if (delIdx != -1) {
                for (int i = delIdx; i < count - 1; i++) {
                    c[i] = c[i + 1];
                }
                c[count - 1] = null; //마지막 데이터 null로 변경
                check = true;
            } else {
                check = false;
            }

            if (check) {
                System.out.println("삭제되었습니다!");
                return;
            } else {
                System.out.println("삭제할 과목명을 다시 입력하세요.");
                continue;
            }
        }
    }

    //학기 선택 메서드
    public String seasonMenu() {

        String semester = null;

        System.out.println("[1]1학년 1학기");
        System.out.println("[2]1학년 2학기");
        System.out.println("[3]2학년 1학기");
        System.out.println("[4]2학년 2학기");
        System.out.println("[5]3학년 1학기");
        System.out.println("[6]3학년 2학기");
        System.out.println("[7]4학년 1학기");
        System.out.println("[8]4학년 2학기");
        System.out.println("====================================");

        while (true) {
            System.out.print("학기를 선택하세요>> ");
            String menuNo = scanner.next();

            switch (menuNo) {
                case "1":
                    semester = "1학년 1학기";
                    break;
                case "2":
                    semester = "1학년 2학기";
                    break;
                case "3":
                    semester = "2학년 1학기";
                    break;
                case "4":
                    semester = "2학년 2학기";
                    break;
                case "5":
                    semester = "3학년 1학기";
                    break;
                case "6":
                    semester = "3학년 2학기";
                    break;
                case "7":
                    semester = "4학년 1학기";
                    break;
                case "8":
                    semester = "4학년 2학기";
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    System.out.println();
                    continue;
            }
            break;
        }
        return semester;
    }
}