package controller;

import student.Student;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentController {
    private final Scanner scanner = new Scanner(System.in);
    private final Student[] s = new Student[SIZE];
    public static final int SIZE = 50;
    String Score = null;
    String avg;
    int targetNum = 0;

    // 시간표 수정을 위한 조건
    boolean modify = false;


    // 객체 입력
    public StudentController() {
        s[0] = new Student("1학년 1학기", "자바프로그래밍", 3, "A", 4.0, 07, 10);
        s[1] = new Student("1학년 1학기", "소프트웨어공학", 2, "B+", 3.5, 13, 15);
        s[2] = new Student("1학년 2학기", "데이터베이스", 3, "B+", 3.5, 16, 20);
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


    //입력 메서드
    public void insert(String semester, String subject, int credit, String score, double changeScore, int time1, int time2) {
        int count = existNum();
        s[count] = new Student(semester, subject, credit, score, changeScore, time1, time2);
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
                    System.out.println("과목이 일치하지 않습니다.");
                    continue;
                } else {

                    //성적 입력
                    System.out.print("수정할 성적을 입력하세요>> ");
                    Score = scanner.next();
                    if (!(Score.equals("A") || Score.equals("A+") || Score.equals("B") || Score.equals("B+")
                            || Score.equals("C") || Score.equals("C+") || Score.equals("D") || Score.equals("D+") || Score.equals("F"))) {
                        System.out.println("[A, A+, B, B+, C, C+, D, D+, F 중의 점수를 입력하세요.]");
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
        System.out.println("\n## 시간표 관련 메뉴를 선택하세요.");
        System.out.println("[1]시간표 조회");
        System.out.println("[2]시간표 수정");
        System.out.println("[3]시간표 삭제");
        System.out.print("메뉴 입력 >> ");
        int Num = scanner.nextInt();
        switch (Num) {
            case 1:
                scheduleView();
                break;
            case 2:
                scheduleModify();
                break;
            case 3:
                scheduledelete();
                break;
            default:
                System.out.println("메뉴를 잘못 입력하셨습니다.");
        }
        return null;
    }

    public Student[] scheduleView() {
        System.out.println("");
        System.out.println("## 시간표를 조회를 시작합니다.");
        System.out.print("학기를 입력하세요 >> ");
        scanner.nextLine();
        String target = scanner.nextLine();
        System.out.printf(" ================ %s 시간표 ================ \n", target);
        System.out.println("");
        for (int i = 0; i < existNum(); i++) {
            // 입력한 학기의 학기와 과목명만 출력
            if (target.equals(s[i].getSemester())) {
                System.out.println(s[i].getSemester() + " | " + s[i].getSubject() + " | " + s[i].getTime1() + "시 - " + s[i].getTime2() + "시");
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
        n1 = 0;
        System.out.println("");
        for (int i = 0; i < existNum(); i++) {
            // 입력한 학기의 학기와 과목명만 출력
            if (target.equals(s[i].getSemester())) {
                System.out.println(s[i].getSemester() + " | " + s[i].getSubject() + " | " + s[i].getTime1() + "시 - " + s[i].getTime2() + "시");
                break;
            }
        }


        System.out.println("");
        while (true) {
            System.out.print("수정하실 과목을 입력하세요 >> ");
            String target2 = scanner.nextLine();

            for (int i = 0; i < existNum(); i++) {
                if (target2.equals(s[i].getSubject())) {
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
                    s[n1].setTime1(time1);

                    System.out.print("강의 종료 시간: ");
                    time2 = scanner.nextInt();
                    s[n1].setTime2(time2);

                    if(time1 > time2) {
                        System.out.println("");
                        System.out.println("## 시작시간이 종료시간보다 느릴 수 없습니다.");
                        break;
                    }
                    System.out.println("");
                    System.out.printf(" ================ %s 수정 시간표 ================ \n", target);
                    System.out.println("");
                    System.out.println(s[n1].getSemester() + " | " + s[n1].getSubject() + " | " + s[n1].getTime1() + "시 - " + s[n1].getTime2() + "시");
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


    public Student[] scheduledelete() {
        System.out.println("");
        System.out.println("## 시간표 삭제를 시작합니다.");
        System.out.print("수정하실 학기를 입력하세요 >> ");
        scanner.nextLine();
        String target = scanner.nextLine();
        System.out.println(" ");
        int n1 = 0;
        System.out.printf(" ================ %s 시간표 ================ \n", target);
        System.out.println("");
        for (int i = 0; i < existNum(); i++) {
            // 입력한 학기의 학기와 과목명만 출력
            if (target.equals(s[i].getSemester())) {
                System.out.println(s[i].getSemester() + " | " + s[i].getSubject() + " | " + s[i].getTime1() + "시 - " + s[i].getTime2() + "시");
            }
        }

        boolean modify = false;
        System.out.println("");
        System.out.print("수정하실 과목을 입력하세요 >> ");
        String target2 = scanner.nextLine();

        for (int i = 0; i < existNum(); i++) {
            if (target2.equals(s[i].getSubject())) {
                modify = true;
                n1 = i;
            }
        }
        if (modify == true) {
            s[n1].setTime1(0);
            s[n1].setTime2(0);
            System.out.printf("\n%s 시간표가 모두 0시로 초기화 되었습니다.\n", target2);
            System.out.println("수정 메뉴로 이동해 새로운 시간표를 입력해주세요.\n");
        }
        return null;
    }
}