package main.java.y2024;

import main.java.common.AbstractService;
import main.java.common.ObstructionEncounter;
import main.java.common.Position;

import java.util.HashSet;
import java.util.Set;

import static main.java.common.CommonService.getDataMatrix;

public class Y2024D6Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {

        Character[][] matrix = getDataMatrix(2024, 6);
        int[] initialPosition = getInitialPosition(matrix);
        Set<Position> positionsSet = new HashSet<>();
        positionsSet.add(new Position(initialPosition[0], initialPosition[1]));
        getUpPositions(initialPosition, matrix, positionsSet, new HashSet<>(), new HashSet<>(),
                new Position(0,0));

        return String.valueOf(positionsSet.size());
    }

    //1704
    @Override
    public String getSecondResult() throws Exception {

        Character[][] matrix = getDataMatrix(2024, 6);
        int[] initialPosition = getInitialPosition(matrix);
        Set<Position> guardPositionsSet = new HashSet<>();
        Set<ObstructionEncounter> obstructionEncounters = new HashSet<>();
        Set<Position> obstructionPositionsSet = new HashSet<>();
        getUpPositions(initialPosition, matrix, guardPositionsSet, obstructionEncounters, new HashSet<>(),
                new Position(0, 0));

        for(Position pos:guardPositionsSet) {
            Character[][] updatedMatrix = new Character[matrix.length][];
            for(int i = 0; i < matrix.length; i++) {
                updatedMatrix[i] = matrix[i].clone();
            }
            updatedMatrix[pos.x()][pos.y()] = '#';
            getUpPositions(initialPosition, updatedMatrix, new HashSet<>(), new HashSet<>(), obstructionPositionsSet,
                    pos);
        }
        obstructionPositionsSet.remove(new Position(initialPosition[0], initialPosition[1]));
        return String.valueOf(obstructionPositionsSet.size());
    }

    private int[] getInitialPosition(Character[][] matrix) {

        int[] initialPosition = new int[2];
        boolean found = false;
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++ ) {
                if (matrix[i][j] == '^') {
                    initialPosition[0] = i;
                    initialPosition[1] = j;
                    found = true;
                    break;
                }
            }
            if(found) {
                break;
            }
        }
        return initialPosition;
    }

    private void getUpPositions (int[] startPosition, Character[][] matrix, Set<Position> positionsSet,
                                 Set<ObstructionEncounter> obstructionEncounters, Set<Position> obstructionPositionsSet,
                                 Position pos) {

        boolean isObstacle = false;
        int[] nextPosition = new int[2];
        for(int i = startPosition[0] - 1; i > -1; i--) {
            if(matrix[i][startPosition[1]] == '#') {
                nextPosition[0] = i + 1;
                nextPosition[1] = startPosition[1];
                isObstacle = obstructionEncounters.add(new ObstructionEncounter(new Position(i, startPosition[1]),
                        "UP"));
                if(!isObstacle){
                    obstructionPositionsSet.add(pos);
                }
                break;
            } else {
                positionsSet.add(new Position(i, startPosition[1]));
            }
        }
        if(isObstacle) {
            getRightPositions (nextPosition, matrix, positionsSet, obstructionEncounters, obstructionPositionsSet,
                    pos);
        }
    }

    private void getRightPositions (int[] startPosition, Character[][] matrix, Set<Position> positionsSet,
                                    Set<ObstructionEncounter> obstructionEncounters,
                                    Set<Position> obstructionPositionsSet, Position pos) {

        boolean isObstacle = false;
        int[] nextPosition = new int[2];
        for(int j = startPosition[1] + 1; j < matrix[0].length; j++) {
            if(matrix[startPosition[0]][j] == '#') {
                nextPosition[0] = startPosition[0];
                nextPosition[1] = j - 1;
                isObstacle = obstructionEncounters.add(new ObstructionEncounter(new Position(startPosition[0], j),
                        "RIGHT"));
                if(!isObstacle) {
                    obstructionPositionsSet.add(pos);
                }
                break;
            } else {
                positionsSet.add(new Position(startPosition[0], j));
            }
        }
        if(isObstacle) {
            getDownPositions (nextPosition, matrix, positionsSet, obstructionEncounters, obstructionPositionsSet,
                    pos);
        }
    }

    private void getDownPositions (int[] startPosition, Character[][] matrix, Set<Position> positionsSet,
                                   Set<ObstructionEncounter> obstructionEncounters,
                                   Set<Position> obstructionPositionsSet, Position pos) {

        boolean isObstacle = false;
        int[] nextPosition = new int[2];
        for(int i = startPosition[0] + 1; i < matrix.length; i++) {
            if(matrix[i][startPosition[1]] == '#') {
                nextPosition[0] = i - 1;
                nextPosition[1] = startPosition[1];
                isObstacle = obstructionEncounters.add(new ObstructionEncounter(new Position(i, startPosition[1]),
                        "DOWN"));
                if(!isObstacle) {
                    obstructionPositionsSet.add(pos);
                }
                break;
            } else {
                positionsSet.add(new Position(i, startPosition[1]));
            }
        }
        if(isObstacle) {
            getLeftPositions (nextPosition, matrix, positionsSet, obstructionEncounters, obstructionPositionsSet,
                    pos);
        }
    }

    private void getLeftPositions (int[] startPosition, Character[][] matrix, Set<Position> positionsSet,
                                   Set<ObstructionEncounter> obstructionEncounters,
                                   Set<Position> obstructionPositionsSet, Position pos) {

        boolean isObstacle = false;
        int[] nextPosition = new int[2];
        for(int j = startPosition[1] - 1; j > -1; j--) {
            if(matrix[startPosition[0]][j] == '#') {
                nextPosition[0] = startPosition[0];
                nextPosition[1] = j + 1;
                isObstacle = obstructionEncounters.add(new ObstructionEncounter(new Position(startPosition[0], j),
                        "LEFT"));
                if(!isObstacle) {
                    obstructionPositionsSet.add(pos);
                }
                break;
            } else {
                positionsSet.add(new Position(startPosition[0], j));
            }
        }
        if(isObstacle) {
            getUpPositions (nextPosition, matrix, positionsSet, obstructionEncounters, obstructionPositionsSet,
                    pos);
        }
    }
}
