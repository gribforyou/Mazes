package backend.academy;

import java.util.HashSet;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Maze {
    private final int height;
    private final int width;
    private final Entity[][] matrix;
    private Set<Edge> edges;
    private List<Edge> solution;
    private Vertex start;
    private Vertex end;
    private int endY;

    public Maze(int width, int height, int startX, int startY, int endX, int endY) {
        if (height < 2 || width < 2 || startX < 0 || startY < 0 || endX < 0 || endY < 0) {
            throw new IllegalArgumentException("Not correct sizes or coordinates");
        }
        this(height, width);
        start = new Vertex(startX, startY);
        end = new Vertex(endX, endY);
        matrix[startX - 1][startY - 1] = Entity.Entrance;
        matrix[endX - 1][endY - 1] = Entity.Exit;
    }

    public Maze(int width, int height) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Not correct sizes or coordinates");
        }
        this.height = height;
        this.width = width;
        solution = null;
        edges = new HashSet<>();
        matrix = new Entity[height][width];
        generateEntities();
    }

    public void generateEdges(MazeGenerator generator) {
        edges = generator.generate(width, height);
    }

    public void solve(MazeSolver solver) {
        solution = solver.solve(this);
    }

    public void showMaze(MazeRenderer renderer) {
        renderer.render(this);
    }

    public void showSolution(MazeRenderer renderer) {
        renderer.renderSolution(this, solution);
    }

    private void generateEntities() {
        Random rand = new Random();
        int temp;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = Entity.Default;
            }
        }
    }
}
