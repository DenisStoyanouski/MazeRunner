package maze;
import java.io.*;
import java.util.*;

public class Play {
    int lines;
    int columns;
    String [][] maze;

    ArrayList<ArrayList<List<Integer>>> edges = new ArrayList<>();
    File file;

    public void menu() {

            System.out.println("=== Menu ===");
            System.out.println("1. Generate a new maze");
            System.out.println("2. Load a maze");
            System.out.println("0. Exit");
            String item = getInput().trim();

            switch (item) {
                case "1" : generateMaze();
                    break;
                case "2" : loadMaze();
                    break;
                case "0" : exit();
                default:
                    System.out.println("Incorrect option. Please try again");
                    menu();
                    break;
            }
    }

    private void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private void loadMaze() {
        String fileName = getInput();
        file = new File(String.format(".\\%s", fileName));
        try (Scanner scFile = new Scanner(file)) {
            while (scFile.hasNext()) {
                System.out.println(scFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("The file %s does not exist", fileName);
        }


    }

    private void generateMaze() {
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
            if (gridSize.length == 2) {
                lines = Integer.parseInt(gridSize[0]);
                columns = Integer.parseInt(gridSize[1]);
            } else {
                lines = Integer.parseInt(gridSize[0]);
                columns = lines;
            }
        } catch (Exception e) {
            System.out.println("Please, enter numbers");
            getGridSize();
        }
        maze = new String[lines][columns];
        fillEdgesMatrix();
    }

    private void fillEdgesMatrix() {
        int nodeLines = lines - 2;
        int nodeColumns = columns - 2;
        for (int i = 0; i < nodeLines; i++) {
            for (int j = 0; j < nodeColumns; j++) {
                ArrayList<List<Integer>> edgesOfGraph = new ArrayList<>();
                if (j != nodeColumns - 1) {
                    edgesOfGraph.add(List.of(j + i * nodeColumns + 1, getWeight()));
                }
                if (i != nodeLines - 1) {
                    edgesOfGraph.add(List.of(j + (i + 1) * nodeColumns, getWeight()));
                }
                edges.add(edgesOfGraph);
            }
        }
        createSpanningTree(edges);
    }

    private void createSpanningTree(ArrayList<ArrayList<List<Integer>>> edges) {
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
        //fill all cells with blocks
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                    maze[i][j] = "\u2588".repeat(2);
            }
        }
        //fill cells with maze according to spanning tree
        for (int i = 1; i < maze.length - 1; i++) {
            for (int j = 1; j < maze[0].length - 1; j++) {
                if (edges.get((i - 1) * (columns - 2) + (j - 1)).size() > 1) {
                    maze[i][j] = " ".repeat(2);
                }
            }
        }

        getEnter();
        getEntrance();
        displayMaze();

    }

    private void getEnter() {
        int line;
        line = getRandomLine();
        if ("  ".equals(maze[line][1])) {
            maze[line][0] = " ".repeat(2);
        } else getEnter();
    }

    private void getEntrance() {
        int line;
        line = getRandomLine();
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
