package maze;

import java.util.Random;

public class Play {

    final String [][] maze = new String[10][10];
    public void go() {
        fillMaze();
        displayMaze();
    }

    private void fillMaze() {
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
    }
    
    private void displayMaze() {
        for (String[] line : maze) {
            for (String cell : line) {
                System.out.print(cell);
            }
            System.out.println("");
        }
    }

    private int getNumberOfLine() {
        Random random = new Random();
        return random.nextInt(8) + 1;
    }

    private int getAmountOfCell() {
        Random random = new Random();
        return random.nextInt(2) + 1;
    }


}
