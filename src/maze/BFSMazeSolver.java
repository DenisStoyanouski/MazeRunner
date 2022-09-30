package maze;

import java.util.*;

import static maze.Play.*;

public class BFSMazeSolver {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Coordinate> solve(String[][] maze) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        Coordinate start = new Coordinate(enter[0], enter[1]);
        nextToVisit.add(start);
        Arrays.fill(visited, false);

        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            if (cur.getRow() > 0 || cur.getRow() <= maze.length || cur.getColumn() > 0 || cur.getColumn() <= maze[0].length) {
                continue;
            }
            if (!visited[cur.getRow()][cur.getColumn()]) {
                continue;
            }

            if (!"/u2588".repeat(2).equals(maze[cur.getRow()][cur.getColumn()])) {
                visited[cur.getRow()][cur.getColumn()] = true;
                continue;
            }

            if (cur.getRow() == entrance[0] && cur.getColumn() == entrance[1]) {
                return backtrackPath(cur);
            }


            for (int[] direction : DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getRow() + direction[0], cur.getColumn() + direction[1], cur);
                nextToVisit.add(coordinate);
                visited[cur.getRow()][cur.getColumn()] = true;
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
}
