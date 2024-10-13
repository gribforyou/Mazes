package backend.academy;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Maze {
    private final int height;
    private final int width;
    private final Entity[][] matrix;
    private List<Edge> edges;
    private List<Edge> solution;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Maze(int height, int width, int startX, int startY, int endX, int endY) {
        this(height, width);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        matrix[startX - 1][startY - 1] = Entity.Entrance;
        matrix[endX - 1][endY - 1] = Entity.Exit;
    }

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        solution = null;
        edges = new ArrayList<>();
        matrix = new Entity[height][width];
        generateEntities();
    }

    public void generateEdges(MazeGenerator generator) {
        edges = generator.generate(height, width);
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
        Random rand  = new Random();
        int temp;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                temp = rand.nextInt(100);
                if (temp < 10) {
                    matrix[i][j] = Entity.Coin;
                } else if(temp < 20) {
                    matrix[i][j] = Entity.Sea;
                } else if(temp < 30){
                    matrix[i][j] = Entity.Forest;
                } else {
                    matrix[i][j] = Entity.Default;
                }
            }
        }
    }
}
