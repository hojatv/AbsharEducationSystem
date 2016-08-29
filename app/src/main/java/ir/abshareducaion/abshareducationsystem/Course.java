package ir.abshareducaion.abshareducationsystem;

/**
 * Created by Dell on 8/28/2016.
 */
public class Course {
    private int courseNumber = 10101;
    private String title = "Designing a basic digital character";
    /*private String description = "r.abshareducaion.abshareducationsystem W/ViewRootImpl:" +
            " Cancelling event due to no window focus: MotionEvent r.abshareducaion.abshareducationsystem " +
            "W/ViewRootImpl: Cancelling event due to no window focus: MotionEvent";*/
    private String description;
    private double credits;

    public Course(int pCourseNumber, String pTitle, String pDescription, double pCredits) {
        courseNumber = pCourseNumber;
        title = pTitle;
        description = pDescription;
        credits = pCredits;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getCredits() {
        return credits;
    }
}
