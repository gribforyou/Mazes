package backend.academy;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        MazeRenderer mazeRenderer = new ConsoleMazeRenderer();
        Maze maze = new Maze(38, 13);
        maze.generateEdges(new PrimMazeGenerator());
        maze.showMaze(mazeRenderer);
    }
}
