package backend.academy.Solvers;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Entity;
import backend.academy.MazeClasses.Maze;
import backend.academy.MazeClasses.Vertex;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DijkstraMazeSolver extends MazeSolver {
    protected Vertex endVertex;

    //CHECKSTYLE:OFF
    protected int defaultPrice = 1;
    protected int coinPrice = 0;
    protected int forestPrice = 3;
    protected int seaPrice = 4;
    //CHECKSTYLE:ON

    public DijkstraMazeSolver() {
    }

    public DijkstraMazeSolver(int defaultPrice, int coinPrice, int forestPrice, int seaPrice) {
        this.defaultPrice = defaultPrice;
        this.coinPrice = coinPrice;
        this.forestPrice = forestPrice;
        this.seaPrice = seaPrice;
    }

    @Override
    public Set<Edge> solve(Maze maze) {
        validateMaze(maze);
        endVertex = maze.end();
        Queue<VertexWithPrevAndWeight> toVisit = new PriorityQueue<>(Comparator.comparingLong(o -> o.priority));
        Set<Vertex> visited = new HashSet<>(maze.width() * maze.height());
        Set<Vertex> neighbours;
        toVisit.add(new VertexWithPrevAndWeight(maze.start(), null, maze.height() + maze.height()));
        VertexWithPrevAndWeight current = null;

        while (!toVisit.isEmpty()) {
            current = toVisit.poll();
            if (!visited.add(current.v)) {
                continue;
            }
            if (current.v.equals(endVertex)) {
                return buildPath(current);
            }
            neighbours = current.v.getNeighbors();
            for (Vertex neighbour : neighbours) {
                if (maze.edges().contains(new Edge(neighbour, current.v)) && !visited.contains(neighbour)) {
                    long priority = priority(current, maze.matrix());
                    VertexWithPrevAndWeight temp = new VertexWithPrevAndWeight(neighbour, current, priority);
                    toVisit.add(temp);
                }
            }
        }
        return Collections.emptySet();
    }

    protected Set<Edge> buildPath(VertexWithPrevAndWeight end) {
        Set<Edge> path = new HashSet<>();
        VertexWithPrevAndWeight current = end;
        while (current.prev != null) {
            path.add(new Edge(current.v, current.prev.v));
            current = current.prev;
        }
        return path;
    }

    protected long priority(VertexWithPrevAndWeight v1, Entity[][] matrix) {
        long step;
        switch (matrix[v1.v.y()][v1.v.x()]) {
            case Coin -> step = coinPrice;
            case Forest -> step = forestPrice;
            case Sea -> step = seaPrice;
            default -> step = defaultPrice;
        }
        return v1.priority + step;
    }

    protected class VertexWithPrevAndWeight {
        final Vertex v;
        VertexWithPrevAndWeight prev;
        long priority;

        VertexWithPrevAndWeight(Vertex v, VertexWithPrevAndWeight prev, long priority) {
            this.v = v;
            this.prev = prev;
            this.priority = priority;
        }

        @Override
        public int hashCode() {
            return v.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof VertexWithPrevAndWeight) {
                return v.equals(((VertexWithPrevAndWeight) obj).v);
            }
            return false;
        }
    }
}
