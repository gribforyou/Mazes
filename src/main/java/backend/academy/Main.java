package backend.academy;

import lombok.experimental.UtilityClass;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        MazeRenderer mr = new ConsoleMazeRenderer();
        Maze maze = new Maze(5, 5);
        mr.render(maze);
    }
}
