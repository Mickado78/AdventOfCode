package main.java.y2016;

import main.java.common.AbstractService;
import main.java.common.CommonService;
import main.java.common.Position;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Y2016D1Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {

        final List<String> dataList = Arrays.stream(getData().split(",")).toList();
        Position position = new Position(0, 0);
        String lastDirection = "N";
        for (String instruction : dataList) {
            final Integer number = getNumber(instruction);
            if (lastDirection.equals("N") && instruction.contains("R")
                    || lastDirection.equals("S") && instruction.contains("L")) {
                position = new Position(position.x() + number, position.y());
                lastDirection = "E";
            } else if (lastDirection.equals("N") && instruction.contains("L")
                    || lastDirection.equals("S") && instruction.contains("R")) {
                position = new Position(position.x() - number, position.y());
                lastDirection = "O";
            } else if (lastDirection.equals("O") && instruction.contains("R")
                    || lastDirection.equals("E") && instruction.contains("L")) {
                position = new Position(position.x(), position.y() + number);
                lastDirection = "N";
            } else {
                position = new Position(position.x(), position.y() - number);
                lastDirection = "S";
            }
        }
        return String.valueOf(Math.abs(position.x()) + Math.abs(position.y()));
    }

    @Override
    public String getSecondResult() throws Exception {

        final List<String> dataList = Arrays.stream(getData().split(",")).toList();
        Set<Position> positionSet = new HashSet<>();
        Position position = new Position(0, 0);
        String lastDirection = "N";
        Position twice = new Position(0, 0);
        boolean stop = false;

        for (String instruction : dataList) {
            final Integer number = getNumber(instruction);
            if (lastDirection.equals("N") && instruction.contains("R")
                    || lastDirection.equals("S") && instruction.contains("L")) {
                for(int i = 1; i <= number; i++) {
                    final Position tmpPos = new Position(position.x() + i, position.y());
                    stop = !positionSet.add(tmpPos);
                    if(stop)  {
                        twice = tmpPos;
                        break;
                    }
                }
                if(stop) {
                    break;
                }
                position = new Position(position.x() + number, position.y());
                lastDirection = "E";
            } else if (lastDirection.equals("N") && instruction.contains("L")
                    || lastDirection.equals("S") && instruction.contains("R")) {
                for(int i = 1; i <= number; i++) {
                    final Position tmpPos = new Position(position.x() - i, position.y());
                    stop = !positionSet.add(tmpPos);
                    if(stop)  {
                        twice = tmpPos;
                        break;
                    }
                }
                if(stop) {
                    break;
                }
                position = new Position(position.x() - number, position.y());
                lastDirection = "O";
            } else if (lastDirection.equals("O") && instruction.contains("R")
                    || lastDirection.equals("E") && instruction.contains("L")) {
                for(int i = 1; i <= number; i++) {
                    final Position tmpPos = new Position(position.x(), position.y() + i);
                    stop = !positionSet.add(tmpPos);
                    if(stop)  {
                        twice = tmpPos;
                        break;
                    }
                }
                if(stop) {
                    break;
                }
                position = new Position(position.x(), position.y() + number);
                lastDirection = "N";
            } else {
                for(int i = 1; i <= number; i++) {
                    final Position tmpPos = new Position(position.x(), position.y() - i);
                    stop = !positionSet.add(tmpPos);
                    if(stop)  {
                        twice = tmpPos;
                        break;
                    }
                }
                if(stop) {
                    break;
                }
                position = new Position(position.x(), position.y() - number);
                lastDirection = "S";
            }
        }

        return String.valueOf(Math.abs(twice.x()) + Math.abs(twice.y()));
    }

    private String getData() throws Exception {
        return CommonService.getDataString(2016, 1);
    }

    private Integer getNumber(final String instruction) {
        return Integer.parseInt(instruction.replace("L","").replace("R", "")
                .replace(" ", ""));
    }
}
