package dev.vengateshm.java_practice.asynchronous;

import java.util.List;

public class EmployeeDataList {
    public static List<EmployeeData> getEmployeeDataList() {
        return List.of(
                new EmployeeData("100", "Jack", 3, "M", "jack@in.com"),
                new EmployeeData("101", "Rose", 5, "F", "rose@in.com"),
                new EmployeeData("102", "John", 5, "M", "john@in.com")
        );
    }
}
