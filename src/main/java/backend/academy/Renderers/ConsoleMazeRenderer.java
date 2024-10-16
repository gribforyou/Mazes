package backend.academy.Renderers;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Maze;
import backend.academy.MazeClasses.Vertex;
import java.io.PrintStream;

public class ConsoleMazeRenderer implements MazeRenderer {

    private final PrintStream out = System.out;
    private final String defaultSprite = "⬜";
    private final String wallSprite = "\uD83D\uDFEB";
    private final String coinSprite = "\uD83D\uDFE8";
    private final String forestSprite = "\uD83D\uDFE9";
    private final String seaSprite = "\uD83D\uDFE6";
    private final String entranceSprite = "1\uFE0F⃣";
    private final String exitSprite = "2\uFE0F⃣";
    private final String pathSprite = "❎";

    public ConsoleMazeRenderer() {
    }

    @Override
    public void render(Maze maze, boolean showSolution) {
        validateMaze(maze, showSolution);

        int height = maze.height();
        int width = maze.width();
        int y = height * 2 + 1;
        int x = width * 2 + 1;

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                printCell(maze, showSolution, i, j, height, width);
            }
            out.println();
        }
    }

    private void validateMaze(Maze maze, boolean showSolution) {
        if (maze == null) {
            throw new NullPointerException("Maze is null!");
        }
        if (showSolution) {
            if (maze.solution() == null || maze.solution().size() == 0) {
                throw new IllegalArgumentException("Maze solution not found!");
            }
            if (maze.start().equals(maze.end())) {
                throw new IllegalArgumentException("Entrance and exit are equal!");
            }
        }
    }

    private void printCell(Maze maze, boolean showSolution, int y, int x, int height, int width) {
        if (isWall(y, x, height, width)) {
            out.print(wallSprite);
        } else {
            Edge tempEd;
            if (x % 2 == 0) {
                tempEd = new Edge(new Vertex(x / 2 - 1, (y - 1) / 2), new Vertex(x / 2, (y - 1) / 2));
                printEdgeOrWall(maze, showSolution, tempEd);
            } else if (y % 2 == 0) {
                tempEd = new Edge(new Vertex((x - 1) / 2, y / 2 - 1), new Vertex((x - 1) / 2, y / 2));
                printEdgeOrWall(maze, showSolution, tempEd);
            } else {
                printCellContent(maze, showSolution, y, x);
            }
        }
    }

    private boolean isWall(int y, int x, int height, int width) {
        int maxY = height * 2 + 1;
        int maxX = width * 2 + 1;
        return x == 0 || x == maxX - 1  || y == 0 || y == maxY - 1 || (x % 2) + (y % 2) == 0;
    }

    private void printEdgeOrWall(Maze maze, boolean showSolution, Edge tempEd) {
        if (showSolution && maze.solution().contains(tempEd)) {
            out.print(pathSprite);
        } else if (maze.edges().contains(tempEd)) {
            out.print(defaultSprite);
        } else {
            out.print(wallSprite);
        }
    }

    private void printCellContent(Maze maze, boolean showSolution, int y, int x) {
        if (showSolution) {
            switch (maze.matrix()[(y - 1) / 2][(x - 1) / 2]) {
                case Exit -> out.print(exitSprite);
                case Entrance -> out.print(entranceSprite);
                case Coin -> out.print(coinSprite);
                case Forest -> out.print(forestSprite);
                case Sea -> out.print(seaSprite);
                default -> out.print(defaultSprite);
            }
        } else {
            switch (maze.matrix()[(y - 1) / 2][(x - 1) / 2]) {
                case Coin -> out.print(coinSprite);
                case Forest -> out.print(forestSprite);
                case Sea -> out.print(seaSprite);
                default -> out.print(defaultSprite);
            }
        }
    }

}
