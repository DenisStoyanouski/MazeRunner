package maze;

import java.util.*;

public class Play {
    int lines;
    int columns;
    String [][] maze;

    ArrayList<ArrayList<List<Integer>>> edges = new ArrayList<>();

    public void go() {
        getGridSize();
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
        fillEdgesMatrix();
    }

    private void fillEdgesMatrix() {

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                ArrayList<List<Integer>> edgesOfGraph = new ArrayList<>();
                if (j != columns - 1) {
                    edgesOfGraph.add(List.of(j + i * columns + 1, getWeight()));
                }
                if (i != lines - 1) {
                    edgesOfGraph.add(List.of(j + (i + 1) * columns, getWeight()));
                }
                edges.add(edgesOfGraph);
            }
        }
        createSpanningTree(edges);
    }

    private void createSpanningTree(ArrayList<ArrayList<List<Integer>>> edges) {
        System.out.println("This is a spanning tree:");
        //sort edges by weights
        for (var edge : edges) {
            edge.sort((edge1, edge2) -> edge1.get(1).compareTo(edge2.get(1)));
        }
        //leave only edge with minimal weight
        for (var edge : edges) {
            if (edge.size() > 1) {
                edge.remove(1);
            }
        }
        //add edges to another graphs
        for (var edge : edges) {
            edges.get(edge.get(0).get(0)).add(edge.get(0));
        }
        fillMaze();
    }

    private void fillMaze() {
        //fill blocks and empty cells
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (edges.get(i * columns + j).size() < 2) {
                    maze[i][j] = "\u2588".repeat(2);
                } else {
                    maze[i][j] = " ".repeat(2);
                }
            }
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == 0 || i == maze.length - 1 || j == 0 || j == maze[0].length - 1) {
                    maze[i][j] = "\u2588".repeat(2);
                }
            }
        }
        getEnter();
        getEntrance();
        displayMaze();

    }

    private void getEnter() {
        int line = getRandomLine();
        if ("  ".equals(maze[line][1])) {
            maze[line][0] = " ".repeat(2);
        } else {
            getEnter();
        }

    }

    private void getEntrance() {
        int line = getRandomLine();
        if ("  ".equals(maze[line][columns - 2])) {
            maze[line][columns - 1] = " ".repeat(2);
        } else {
            getEntrance();
        }

    }


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
        return random.nextInt(100);
    }

    private int getRandomLine() {
        Random random = new Random();
        return random.nextInt(lines - 1) + 1;
    }


}
