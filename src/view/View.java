package view;

import controller.StudentController;
import student.Student;

import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);
    private StudentController sc = new StudentController();
    String menuNo = "-1";
    int sCount = 0;
    public String semester2;
    String Score = null;
    double tScore = 0;
    double total = 0;
    int creditCount = 0;


    public void mainMenu() {

        //메인화면
        while (menuNo != "6") {

            System.out.println("========== 학점 관리 프로그램 ==========");
            System.out.println("[1]성적 입력");
            System.out.println("[2]성적 수정");
            System.out.println("[3]성적 삭제");
            System.out.println("[4]학기별 성적 조회");
            System.out.println("[5]전체 성적 조회");
            System.out.println("[6]종료");
            System.out.println("====================================");
            System.out.print("이용하실 메뉴 번호를 입력하세요>> ");

            menuNo = scanner.next();

            switch (menuNo) {
                case "1":
                    insert();
                    break;
                case "2":
                    modifyMenu();
                    break;
                case "3":
                    deleteScore();
                    break;
                case "4":
                    printOneSemester();
                    break;
                case "5":
                    printAll();
                    break;
                case "6":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); //프로그램 종료
                    break;
                default:
                    System.out.println("메뉴를 잘못 입력하였습니다.");
            }


        }
    }

    //성적 입력 메서드
    public void insert() {

        String score = null;
        String changeScore;
        String semester;
        String subject;
        double resultScore = 0;


        // 학기를 선택할 수 있는 메서드
        System.out.println("====================================");
        seasonMenu();

        while (true) {
            System.out.println("============= 과목 목록 ==============");
            System.out.println("1. 자바프로그래밍 - 홍길동 교수님");
            System.out.println("2. 소프트웨어공학 - 뽀로로 교수님");
            System.out.println("3. 데이터베이스 - 김영희 교수님");
            System.out.println("====================================");

            //과목 입력
            System.out.print("과목 입력: ");
            subject = scanner.next();
            if (!subject.equals("자바프로그래밍") && !subject.equals("소프트웨어공학") && !subject.equals("데이터베이스")
                    && !subject.equals("1") && !subject.equals("2") && !subject.equals("3")) {
                System.out.println("잘못된 입력입니다.");
                continue;
            } else if (subject.equals("1")) subject = "자바프로그래밍";
            else if (subject.equals("2")) {
                subject = "소프트웨어공학";
            } else if (subject.equals("3")) {
                subject = "데이터베이스";
            }

            //점수 입력
            modifyScore: while (true) {

                System.out.print("점수 입력: ");
                score = scanner.next();
                if (!(score.equals("A") || score.equals("A+") || score.equals("B") || score.equals("B+")
                        || score.equals("C") || score.equals("C+") || score.equals("D") || score.equals("D+") || score.equals("F"))) {
                    System.out.println("\n[A, A+, B, B+, C, C+, D, D+, F 중의 점수를 입력하세요.]");
                    continue modifyScore;
                }
                resultScore = sc.changeScore(score);
                break;
            }

            //이수학점 임력
            System.out.print("이수학점 입력: ");
            int credit = scanner.nextInt();

            sc.insert(semester2, subject, credit, score, resultScore);

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

    //성적 수정 메서드
    public void modifyMenu() {
        String targetSemester = null;
        String subject = null;
        while (true) {
            printAll();
            System.out.println("수정할 학기를 입력하세요. ex) 4학년 2학기");
            System.out.print(">> ");
            scanner.nextLine();
            targetSemester = scanner.nextLine();
            System.out.println();
            sc.modifySemesterScore(targetSemester);
            break;

        }
        return;
    }

    //성적 삭제 메서드
    public void deleteScore() {
        while (true) {
            System.out.println("====================================");
            String seasoon = seasonMenu();
            sc.printSemester(seasoon);

            delete: while (true) {
                System.out.println("====================================");
                System.out.print("삭제할 과목 입력>> ");
                String subjectName = scanner.next();
                System.out.println();
                boolean check = sc.delete(seasoon, subjectName);

                if (check) {
                    System.out.println("삭제되었습니다!");
                    return;
                } else {
                    System.out.println("삭제할 과목명을 다시 입력하세요.");
                    continue delete;
                }
            }
        }
    }

    //학기별 조회 메서드
    public void printOneSemester() {

        System.out.println("\n=========== 학기별 성적 조회 ===========");
        sc.printSemester(seasonMenu());

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
        semester2 = semester;
        return semester;
    }


    //전체 조회 메서드
    public void printAll() {
        Student[] students = sc.printAll();
        int count = sc.existNum();

        if (count == 0) {
            System.out.println("\n저장된 성적이 없습니다.");
        } else {
            System.out.println("\n==================== 전체 성적 조회 ====================\n");

            printAllSemester();

            System.out.println("=======================================================");
            sc.calculateAvg(total, creditCount);
            System.out.println();

        }
    }

    //전체조회시 학기명 넘기는 메서드
    public void printAllSemester() {
        sc.printSemester("1학년 1학기");
        sc.printSemester("1학년 2학기");
        sc.printSemester("2학년 1학기");
        sc.printSemester("2학년 2학기");
        sc.printSemester("3학년 1학기");
        sc.printSemester("3학년 2학기");
        sc.printSemester("4학년 1학기");
        sc.printSemester("4학년 2학기");
    }

}