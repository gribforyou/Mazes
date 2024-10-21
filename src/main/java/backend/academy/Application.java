package backend.academy;

import backend.academy.Generators.EntityGenerators.BasicEntityGenerator;
import backend.academy.Generators.MazeGenerators.KrascalMazeGenerator;
import backend.academy.Generators.MazeGenerators.KrascalWithExtraEdges;
import backend.academy.MazeClasses.Maze;
import backend.academy.MazeClasses.Vertex;
import backend.academy.Renderers.ConsoleMazeRenderer;
import backend.academy.Solvers.AStarMazeSolver;
import backend.academy.Solvers.DijkstraMazeSolver;
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
        maze.generateEntities(new BasicEntityGenerator());
        maze.generateEdges(new KrascalMazeGenerator());
        maze.generateEdges(new KrascalWithExtraEdges());
        maze.showMaze(new ConsoleMazeRenderer());
        maze.setStart(new Vertex(0, 0));
        maze.setEnd(new Vertex(width - 1, height - 1));
        maze.solve(new DijkstraMazeSolver());
        maze.solve(new AStarMazeSolver());
        maze.showSolution(new ConsoleMazeRenderer());
    }
}
