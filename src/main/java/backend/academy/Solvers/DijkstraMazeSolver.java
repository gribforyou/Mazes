package backend.academy.Solvers;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Maze;
import backend.academy.MazeClasses.Vertex;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DijkstraMazeSolver extends MazeSolver {
    private final int defaultPrice = 1;
    private final int coinPrice = 0;
    private final int forestPrice = 2;
    private final int seaPrice = 3;

    @Override
    public Set<Edge> solve(Maze maze) {
        validateMaze(maze);

        Queue<DijkstraVertex> toVisit = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        Set<Vertex> visited = new HashSet<>(maze.width() * maze.height());
        toVisit.add(new DijkstraVertex(maze.start(), null, 0));
        DijkstraVertex current = null;

        while (!toVisit.isEmpty()) {
            current = toVisit.poll();
            if (!visited.add(current.v)) {
                continue;
            }
            if (current.v.equals(maze.end())) {
                return buildPath(current);
            }

            for (Vertex neighbour : getNeighbours(current.v)) {
                if (maze.edges().contains(new Edge(neighbour, current.v)) && !visited.contains(neighbour)) {
                    int step;
                    switch (maze.matrix()[neighbour.y()][neighbour.x()]) {
                        case Coin -> step = coinPrice;
                        case Forest -> step = forestPrice;
                        case Sea -> step = seaPrice;
                        default -> step = defaultPrice;
                    }
                    DijkstraVertex temp = new DijkstraVertex(neighbour, current, current.weight + step);
                    toVisit.remove(temp);
                    toVisit.add(temp);
                }
            }
        }
        return Collections.emptySet();
    }

    private Set<Edge> buildPath(DijkstraVertex end) {
        Set<Edge> path = new HashSet<>();
        DijkstraVertex current = end;
        while (current.prev != null) {
            path.add(new Edge(current.v, current.prev.v));
            current = current.prev;
        }
        return path;
    }

    private Set<Vertex> getNeighbours(Vertex v) {
        Set<Vertex> neighbours = new HashSet<>();
        neighbours.add(new Vertex(v.x(), v.y() - 1));
        neighbours.add(new Vertex(v.x(), v.y() + 1));
        neighbours.add(new Vertex(v.x() - 1, v.y()));
        neighbours.add(new Vertex(v.x() + 1, v.y()));
        return neighbours;
    }

    private class DijkstraVertex {
        final Vertex v;
        DijkstraVertex prev;
        int weight;

        DijkstraVertex(Vertex v, DijkstraVertex prev, int weight) {
            this.v = v;
            this.prev = prev;
            this.weight = weight;
        }

        @Override
        public int hashCode() {
            return v.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DijkstraVertex) {
                return v.equals(((DijkstraVertex) obj).v);
            }
            return false;
        }
    }
}
