package backend.academy;

import java.util.List;

public class ConsoleMazeRenderer implements MazeRenderer {

    private final String defaultSprite = "⬜";
    private final String wallSprite = "\uD83D\uDFEB";
    private final String coinSprite = "\uD83D\uDFE8";
    private final String forestSprite = "\uD83D\uDFE9";
    private final String seaSprite = "\uD83D\uDFE6";
    private final String entranceSprite = "1\uFE0F⃣";
    private final String exitSprite = "2\uFE0F⃣";
    private final String pathSprite = "❎";

    public ConsoleMazeRenderer() {}

    @Override
    public void render(Maze maze) {
        if (maze == null) {
            throw new NullPointerException("maze is null");
        }
        int height = maze.height();
        int width = maze.width();
        int x = height * 2 + 1;
        int y = width * 2 + 1;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (j == 0 || j == y - 1 || i == 0 || i == x - 1 || (i % 2) + (j % 2) == 0) {
                    System.out.print(wallSprite);
                }
                else if(j % 2 == 0) {
                    if (maze.edges().contains(new Edge(j / 2 - 1, (i - 1) / 2, j / 2, (i - 1) / 2))) {
                        System.out.print(defaultSprite);
                    }
                    else {
                        System.out.print(wallSprite);
                    }
                }
                else if(i % 2 == 0){
                    if(maze.edges().contains(new Edge((j - 1) / 2, i / 2 - 1, (j - 1) / 2, i / 2 ) )) {
                        System.out.print(defaultSprite);
                    }
                    else {
                        System.out.print(wallSprite);
                    }
                }
                else {
                    switch (maze.matrix()[(i - 1) / 2][(j - 1) / 2]) {
                        case Default -> System.out.print(defaultSprite);
                        case Coin -> System.out.print(coinSprite);
                        case Forest -> System.out.print(forestSprite);
                        case Sea -> System.out.print(seaSprite);
                        default -> System.out.print(defaultSprite);
                    }
                }
            }
            System.out.println();
        }
    }

    @Override
    public void renderSolution(Maze maze, List<Edge> solution) {

    }
}
