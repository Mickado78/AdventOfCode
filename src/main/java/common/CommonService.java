package main.java.common;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class CommonService {

    public static List<String> getDataList(int year, int day) throws Exception {
         return Files.lines(Path.of(CommonService.class.getClassLoader()
                .getResource("main/resources/y" + year + "/puzzle" + day + ".txt").toURI()))
                 .collect(Collectors.toList());

    }

    public static String getDataString(int year, int day) throws Exception {
        return Files.lines(Path.of(CommonService.class.getClassLoader()
                .getResource("main/resources/y" + year + "/puzzle" + day + ".txt").toURI()))
                .collect(Collectors.joining());
    }

    public static Character[][] getDataMatrix(int year, int day) throws Exception {
        List<String> dataList = getDataList(year, day);
        Character[][] returnMatrix = new Character[dataList.size()][dataList.getFirst().length()];
        for(int i = 0; i < dataList.size(); i++) {
            for(int j = 0; j < dataList.getFirst().length(); j++) {
                returnMatrix[i][j] = dataList.get(i).charAt(j);
            }
        }
        return returnMatrix;
    }
}
