package maze;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Play {
    int lines;
    int columns;
    String [][] maze;
    int [][] graphMatrix;

    final
    public void go() {
        getGridSize();
        fillGraphMatrix();
        displayGraphMatrix();
        /*fillMaze();
        displayMaze();*/
    }

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void getGridSize() {
        System.out.println("Please, enter the size of a maze");
        String[] gridSize = getInput().trim().split("\\s+");
        try {
            lines = Integer.parseInt(gridSize[0]);
            columns = Integer.parseInt(gridSize[1]);
        } catch (Exception e) {
            System.out.println("Please, enter two numbers");
            getGridSize();
        }
        maze = new String[lines][columns];
        graphMatrix = new int[lines][columns];
    }

    private void fillGraphMatrix() {
        for (int i = 0; i < graphMatrix.length; i++) {
            for (int j = 0; j < graphMatrix[0].length; j++) {
                graphMatrix[i][j] = getWeight();
            }
        }
    }

    private void displayGraphMatrix() {
        System.out.println(Arrays.deepToString(graphMatrix));
    }


    /*private void fillMaze() {
        //fill blocks and empty cells
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == 0 || i == 9 || j % 2 == 0 || j == 9) {
                    maze[i][j] = "\u2588".repeat(2);
                } else {
                    maze[i][j] = " ".repeat(2);
                }
            }
        }
        enterEntrance();
        enterExit();

        for (int k = 2; k < 9; k +=2 ) {
            int amount = getAmountOfCell();
            int line = getNumberOfLine();
            while (!"  ".equals(maze[line][k])) {
                if (!"  ".equals(maze[line - 1][k - 2]) && !"  ".equals(maze[line + 1][k - 2])) {
                    maze[line][k] = " ".repeat(2);
                }
            }
        }
    }

    private void enterEntrance() {
        maze[getNumberOfLine()][0] = "  ";
    }

    private void enterExit() {
        int line = getNumberOfLine();
        maze[line][9] = "  ";
        maze[line][8] = "  ";
    }*/
    
    private void displayMaze() {
        for (String[] line : maze) {
            for (String cell : line) {
                System.out.print(cell);
            }
            System.out.println("");
        }
    }

    private int getWeight() {
        Random random = new Random();
        return random.nextInt(20);
    }

    private int getAmountOfCell() {
        Random random = new Random();
        return random.nextInt(2) + 1;
    }


}
