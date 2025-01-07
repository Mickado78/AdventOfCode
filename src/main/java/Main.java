package main.java;

import main.java.y2024.Y2024D3Service;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws Exception {

        callYearXDayYService(2015, 1);
        //callYearXDayYService(2015, 2);

        callYearXDayYService(2016, 1);

        callYearXDayYService(2017, 1);

        callYearXDayYService(2018, 1);

        callYearXDayYService(2019, 1);

        callYearXDayYService(2020, 1);

        callYearXDayYService(2021, 1);

        callYearXDayYService(2022, 1);

        callYearXDayYService(2023, 1);
        /*
        callYearXDayYService(2024, 1);
        callYearXDayYService(2024, 2);
        callYearXDayYService(2024, 3);
        //stream way from Gille
        callYear2024Day3();
        callYearXDayYService(2024, 4);
        callYearXDayYService(2024, 5);
        callYearXDayYService(2024, 6);*/
    }

    private static void callYearXDayYService(int year, int day) throws Exception {

        Class<?> yearDayServiceClass = Class.forName("main.java.y" + year + ".Y" + year + "D" + day + "Service");
        Object instance = yearDayServiceClass.getConstructor().newInstance();
        long start1 = Instant.now().toEpochMilli();
        System.out.println("Year " + year + ", Day " + day + ", Part 1, result : "
                + (int) yearDayServiceClass.getDeclaredMethod("getFirstResult").invoke(instance)
                + " - Duration : " + (Instant.now().toEpochMilli() - start1) + " ms");
        long start2 = Instant.now().toEpochMilli();
        System.out.println("Year " + year + ", Day " + day + ", Part 2, result : "
                + (int) yearDayServiceClass.getDeclaredMethod("getSecondResult").invoke(instance)
                + " - Duration : " + (Instant.now().toEpochMilli() - start2) + " ms");
    }

    private static void callYear2024Day3() throws Exception {
        long start = Instant.now().toEpochMilli();
        System.out.println("Year 2024, Day 3, Part 1, stream : " + new Y2024D3Service().getResultUsingStream()
                + " - Duration : " + (Instant.now().toEpochMilli() - start) + " ms");
    }
}
