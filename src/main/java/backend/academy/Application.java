package backend.academy;

import backend.academy.Generators.PrimMazeGenerator;
import backend.academy.MazeClasses.Maze;
import backend.academy.MazeClasses.Vertex;
import backend.academy.Renderers.ConsoleMazeRenderer;
import backend.academy.Solvers.BFSMazeSolver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Application {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(fis);

        final int height = Integer.parseInt(prop.getProperty("demonstrationHeight"));
        final int width = Integer.parseInt(prop.getProperty("demonstrationWidth"));

        Maze maze = new Maze(width, height);
        maze.generateEdges(new PrimMazeGenerator());
        maze.showMaze(new ConsoleMazeRenderer());
        maze.setEnd(new Vertex(0, 0));
        maze.setStart(new Vertex(width - 1, height - 1));
        maze.solve(new BFSMazeSolver());
        maze.showSolution(new ConsoleMazeRenderer());
    }
}
