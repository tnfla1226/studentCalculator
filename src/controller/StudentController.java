package controller;

import student.Student;

import java.util.Scanner;

public class StudentController {
    private Scanner scanner = new Scanner(System.in);
    private Student[] s = new Student[SIZE];
    public static final int SIZE = 8;
    double Score = 0;
    String avg;

    // 객체 입력
    public StudentController() {
        s[0] = new Student("4학년 1학기", "자바프로그래밍", 3, 4.0, "A");
        s[1] = new Student("4학년 1학기", "소프트웨어공학", 2, 3.5, "B+");
        s[2] = new Student("4학년 2학기", "데이터베이스", 2, 4.5, "A+");
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
    public void insert(String semester, String subject, int credit, double score, String changeScore) {
        int count = existNum();
        s[count] = new Student(semester, subject, credit, score, changeScore);
    }

    //과목 수정 메서드
    public void modifySubject(String targetSubject) {
        for (int i = 0; i < existNum(); i++) {
            if (targetSubject.equals(s[i].getSubject())) {
                System.out.println("");
                System.out.println(s[i].showScore());
                System.out.println("");
            }
        }
    }

    //학기와 성적 수정 메서드
    public void modifySemesterScore(String targetSemester) {
        System.out.println("\n=============== 수정하실 과목의 입력 정보 ===============");
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
                System.out.print("\n수정 할 과목을 입력해주세요>> ");
                String subject = scanner.next();
                modifySubject(subject);

                //성적 입력
                System.out.print("수정 할 성적을 입력해주세요>> ");
                Score = scanner.nextDouble();
                if (Score > 4.5) {
                    System.out.println("최대 4.5점을 초과할 수 없습니다.");
                    continue;
                } else {
                    System.out.println("");
                    // 입력받은 성적으로 수정
                    s[i].setScore(Score);
                    // 입력받은 성적은 changeScore 메서드로 변경
                    s[i].setChangeScore(changeScore(Score));
                    System.out.println("");
                    System.out.println("=============== 수정 완료 성적 ===============");
                    System.out.println("");
                    System.out.println("|   학기   |   과목명   |  이수학점  |   성적   |   학점   |");
                    System.out.println("");
                    System.out.println(s[i].showScore());
                    System.out.println("");
                    break;
                }
            }
            break;
        }
        return;
    }

    //성적 삭제 메서드
    public boolean delete(String subject) {
        int count = existNum();
        //삭제할 데이터 인덱스 구하기
        int delIdx = -1;
        for (int i = 0; i < count; i++) {
            if (subject.equals(s[i].getSubject())) {
                delIdx = i;
                break;
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
    public String changeScore(double score) {
        String changeScore = null;
        if (score == 4.5) {
            changeScore = "A+";
        } else if (score < 4.5 && score >= 4.0) {
            changeScore = "A";
        } else if (score < 4.0 && score >= 3.5) {
            changeScore = "B+";
        } else if (score < 3.5 && score >= 3.0) {
            changeScore = "B";
        } else if (score < 3.0 && score >= 2.5) {
            changeScore = "C+";
        } else if (score < 2.5 && score >= 2.0) {
            changeScore = "C";
        } else if (score < 2.0 && score >= 1.5) {
            changeScore = "D+";
        } else if (score < 1.5 && score >= 1.0) {
            changeScore = "D";
        } else {
            changeScore = "F";
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
            tScore += s[i].getScore() * s[i].getCredit();
        }
        return tScore;
    }


    // 총 평균 학점 구해서 출력하는 메서드
    public void calculateAvg(double totalScore, int totalCredit) {

        double tempAvg = getTscore() / getTotalCredit();
        avg = String.format("%.2f", tempAvg);

        System.out.printf("총 이수학점 : %d   /   평균 학점 : %S\n", getTotalCredit(), avg);
    }

    //학기별 성적 출력하는 메서드
    public void printSemester(String semester) {

        System.out.printf("\n=============== %s 성적 ===============\n", semester);
        System.out.println("|   학기   |   과목명   |  이수학점  |   성적   |   학점   |");
        for (int i = 0; i < existNum(); i++) {
            if (semester.equals(s[i].getSemester())) {
                System.out.println(s[i].showScore());
            }
        }
        System.out.println("");
    }

    //전체 출력 메서드
    public Student[] printAll() {
        return s;
    }
}



