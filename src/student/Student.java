package student;

public class Student {

    // 필드 선언
    // 학생
    private String name;
    //이수학점
    private int credit;
    //학기
    private String semester;
    // 과목
    private String subject;
    // 과목당 점수를 숫자로 환산 ( 4.5 이런식이기 때문에 double 사용 )
    private String score;
    // 과목당 점수(A~F)
    private double changeScore;
    // 숫자로 환산받은 점수를 모두 더함 ( 나중에 나눠서 (A~F)로 변환 예정 )
    private double total;
    private double avg;


    public Student(String semester, String subject, int credit, String score, double changeScore) {

        this.semester = semester;
        this.subject = subject;
        this.score = score;
        this.changeScore = changeScore;
        this.credit = credit;

    }

    public String showScore() {
        return this.semester + "  |  " + this.subject + "  |  " + this.credit + "  |  " + this.score + "  |  " + this.changeScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int credit) {
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getChangeScore() {
        return changeScore;
    }

    public void setChangeScore(double changeScore) {
        this.changeScore = changeScore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

}
