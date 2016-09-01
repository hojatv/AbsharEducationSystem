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
        data.add(new Course(1001, "ثبت نام", "فرایند تبت نام. در حال ارتباط با سرور", 3));
        data.add(new Course(1002, "برنامه درسی", "در حال دریافت اطلاعات ...  " , 3));
        data.add(new Course(1003, "ارتباط با مدرسین", "در حال دریافت اطلاعات ...", 3));
        data.add(new Course(1004, "کارنامه تحصیلی", "در حال دریافت اطلاعات", 3));
    }
}
