package backend.academy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(fis);

        final int height = Integer.parseInt(prop.getProperty("demonstrationHeight"));
        final int width = Integer.parseInt(prop.getProperty("demonstrationWidth"));

        Maze maze = new Maze(width, height);
        maze.generateEdges(new PrimMazeGenerator());
        maze.showMaze(new ConsoleMazeRenderer());
    }
}
