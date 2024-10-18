package backend.academy.Solvers;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Maze;
import backend.academy.MazeClasses.Vertex;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class BFSMazeSolver implements MazeSolver {

    @Override
    public Set<Edge> solve(Maze maze) {
        validateMaze(maze);

        Queue<VertexWithPrevious> toVisit = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>(maze.width() * maze.height());
        Set<Edge> edges = new HashSet<>(maze.width() * maze.height() * 2);

        toVisit.add(new VertexWithPrevious(maze.start(), null));
        visited.add(maze.start());

        while (!toVisit.isEmpty()) {
            VertexWithPrevious current = toVisit.poll();
            if (current.v.equals(maze.end())) {
                return buildSolutionPath(edges, current);
            }

            for (Vertex neighbour : getNeighbours(current.v)) {
                Edge edge = new Edge(neighbour, current.v);
                if (maze.edges().contains(edge) && !visited.contains(neighbour)) {
                    toVisit.add(new VertexWithPrevious(neighbour, current));
                    visited.add(neighbour);
                }
            }
        }
        return null;
    }

    private void validateMaze(Maze maze) {
        if (maze == null || maze.edges().isEmpty() || maze.end() == null || maze.start() == null) {
            throw new IllegalArgumentException("Maze params are not correct!");
        }
    }

    private Set<Vertex> getNeighbours(Vertex v) {
        Set<Vertex> neighbours = new HashSet<>();
        neighbours.add(new Vertex(v.x(), v.y() - 1));
        neighbours.add(new Vertex(v.x(), v.y() + 1));
        neighbours.add(new Vertex(v.x() - 1, v.y()));
        neighbours.add(new Vertex(v.x() + 1, v.y()));
        return neighbours;
    }

    private Set<Edge> buildSolutionPath(Set<Edge> edges, VertexWithPrevious current) {
        VertexWithPrevious currentCopy = current;
        while (currentCopy.previous != null) {
            edges.add(new Edge(currentCopy.previous.v, currentCopy.v));
            currentCopy = currentCopy.previous;
        }
        return edges;
    }

    private class VertexWithPrevious {
        private final Vertex v;
        private final VertexWithPrevious previous;

        VertexWithPrevious(Vertex current, VertexWithPrevious previous) {
            this.v = current;
            this.previous = previous;
        }
    }
}
