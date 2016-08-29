package ir.abshareducaion.abshareducationsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 8/29/2016.
 */
public class DataProvider {
    private static List<Course> data = new ArrayList<>();

    public static List<Course> getData() {
        return data;
    }

    static {
        data.add(new Course(1001, "DataStructure1", "This course should be taken in term3", 3));
        data.add(new Course(1002, "Algorithm", "It is optional for hardware engineering students", 3));
        data.add(new Course(1003, "Artificial Intelligence", "This course is iaken after algorithm", 3));
        data.add(new Course(1004, "Graphics", "Optional", 3));
    }
}
