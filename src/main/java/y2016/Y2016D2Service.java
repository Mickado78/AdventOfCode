package main.java.y2016;

import main.java.common.AbstractService;
import main.java.common.CommonService;
import main.java.common.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Y2016D2Service extends AbstractService {
    @Override
    public String getFirstResult() throws Exception {

        List<String> dataList = getDataList();
        Position previous = new Position(1, 1);
        int[][] keypad = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                keypad[i][j] = j + 1 + 3 * i;
            }
        }
        List<Integer> numbers = new ArrayList<>();

        for(String data:dataList) {
            for (char c:data.toCharArray()) {
                switch (c){
                    case 'U':
                        if(previous.x() > 0 ) {
                            previous = new Position(previous.x() - 1, previous.y());
                        }
                        break;
                    case 'D':
                        if(previous.x() < 2 ) {
                            previous = new Position(previous.x() + 1, previous.y());
                        }
                        break;
                    case 'L':
                        if(previous.y() > 0 ) {
                            previous = new Position(previous.x(), previous.y() - 1);
                        }
                        break;
                    case 'R':
                        if(previous.y() < 2 ) {
                            previous = new Position(previous.x(), previous.y() + 1);
                        }
                        break;
                }
            }
            numbers.add(keypad[previous.x()][previous.y()]);
        }
        return numbers.stream().map(String::valueOf).collect(Collectors.joining());
    }

    @Override
    public String getSecondResult() throws Exception {

        List<String> dataList = getDataList();
        Position previous = new Position(2, 0);
        char[][] keypad = {
                {' ', ' ', '1', ' ', ' '},
                {' ', '2', '3', '4', ' '},
                {'5', '6', '7', '8', '9'},
                {' ', 'A', 'B', 'C', ' '},
                {' ', ' ', 'D', ' ', ' '}
        };
        List<Character> numbers = new ArrayList<>();

        for(String data:dataList) {
            for (char c:data.toCharArray()) {
                switch (c){
                    case 'U':
                        if(!(previous.x() == 2 && previous.y() == 0
                          || previous.x() == 1 && previous.y() == 1
                          || previous.x() == 0 && previous.y() == 2
                          || previous.x() == 1 && previous.y() == 3
                          || previous.x() == 2 && previous.y() == 4 )) {
                            previous = new Position(previous.x() - 1, previous.y());
                        }
                        break;
                    case 'D':
                        if(!(previous.x() == 2 && previous.y() == 0
                          || previous.x() == 3 && previous.y() == 1
                          || previous.x() == 4 && previous.y() == 2
                          || previous.x() == 3 && previous.y() == 3
                          || previous.x() == 2 && previous.y() == 4 )) {
                            previous = new Position(previous.x() + 1, previous.y());
                        }
                        break;
                    case 'L':
                        if(!(previous.x() == 0 && previous.y() == 2
                          || previous.x() == 1 && previous.y() == 1
                          || previous.x() == 2 && previous.y() == 0
                          || previous.x() == 3 && previous.y() == 1
                          || previous.x() == 4 && previous.y() == 2 )) {
                            previous = new Position(previous.x(), previous.y() - 1);
                        }
                        break;
                    case 'R':
                        if(!(previous.x() == 0 && previous.y() == 2
                          || previous.x() == 1 && previous.y() == 3
                          || previous.x() == 2 && previous.y() == 4
                          || previous.x() == 3 && previous.y() == 3
                          || previous.x() == 4 && previous.y() == 2 )) {
                            previous = new Position(previous.x(), previous.y() + 1);
                        }
                        break;
                }
            }
            numbers.add(keypad[previous.x()][previous.y()]);
        }

        return numbers.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2016, 2);
    }
}
