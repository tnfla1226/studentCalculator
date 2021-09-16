package student;

public class Schedule {

    private String scheduleSemester;
    private String scheduleSubject;
    private int time1, time2;

    public Schedule(String scheduleSemester, String scheduleSubject, int time1, int time2) {
        this.scheduleSemester = scheduleSemester;
        this.scheduleSubject = scheduleSubject;
        this.time1 = time1;
        this.time2 = time2;
    }

    public String showSchedule() {
        return this.scheduleSemester + "  |  " + this.scheduleSubject + "  |  " + this.time1 + "시 - " + this.time2 + "시 ";
    }

    public String getScheduleSemester() {
        return scheduleSemester;
    }

    public void setScheduleSemester(String scheduleSemester) {
        this.scheduleSemester = scheduleSemester;
    }

    public String getScheduleSubject() {
        return scheduleSubject;
    }

    public void setScheduleSubject(String scheduleSubject) {
        this.scheduleSubject = scheduleSubject;
    }


    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }
}
