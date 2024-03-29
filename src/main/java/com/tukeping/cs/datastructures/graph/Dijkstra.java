package com.tukeping.cs.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author tukeping
 * @date 2020/6/9
 **/
public class Dijkstra {

    public static void main(String[] arg) {

        Dijkstra obj = new Dijkstra();

        // Create a new graph.
        Graph g = new Graph(9);

        // Add the required edges.
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 1, 8);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 2, 7);
        g.addEdge(3, 5, 14);
        g.addEdge(3, 4, 9);
        g.addEdge(4, 3, 9);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 4, 10);
        g.addEdge(5, 3, 9);
        g.addEdge(5, 2, 4);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(6, 5, 2);
        g.addEdge(7, 0, 8);
        g.addEdge(7, 8, 7);
        g.addEdge(7, 1, 11);
        g.addEdge(7, 6, 1);
        g.addEdge(8, 2, 2);
        g.addEdge(8, 7, 7);
        g.addEdge(8, 6, 6);

        // Calculate Dijkstra.
        obj.calculate(g.getVertex(0));

        // Print the minimum Distance.
        for (Vertex v : g.getVertices()) {
            System.out.print("Vertex - " + v + " , Dist - " + v.minDistance + " , Path - ");
            for (Vertex pathvert : v.path) {
                System.out.print(pathvert + " ");
            }
            System.out.println("" + v);
        }

    }

    public void calculate(Vertex source) {
        // Algo:
        // 1. Take the unvisited node with minimum weight.
        // 2. Visit all its neighbours.
        // 3. Update the distances for all the neighbours (In the Priority Queue).
        // Repeat the process till all the connected nodes are visited.

        source.minDistance = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        queue.add(source);

        while (!queue.isEmpty()) {

            Vertex u = queue.poll();

            for (Edge neighbour : u.neighbours) {
                Double newDist = u.minDistance + neighbour.weight;

                if (neighbour.target.minDistance > newDist) {
                    // Remove the node from the queue to update the distance value.
                    queue.remove(neighbour.target);
                    neighbour.target.minDistance = newDist;

                    // Take the path visited till now and add the new node.s
                    neighbour.target.path = new LinkedList<Vertex>(u.path);
                    neighbour.target.path.add(u);

                    //Reenter the node with new distance.
                    queue.add(neighbour.target);
                }
            }
        }
    }

    static class Graph {
        private ArrayList<Vertex> vertices;

        public Graph(int numberVertices) {
            vertices = new ArrayList<>(numberVertices);
            for (int i = 0; i < numberVertices; i++) {
                vertices.add(new Vertex("v" + i));
            }
        }

        public void addEdge(int src, int dest, int weight) {
            Vertex s = vertices.get(src);
            Edge new_edge = new Edge(vertices.get(dest), weight);
            s.neighbours.add(new_edge);
        }

        public ArrayList<Vertex> getVertices() {
            return vertices;
        }

        public Vertex getVertex(int vert) {
            return vertices.get(vert);
        }
    }

    static class Edge {
        public final Vertex target;
        public final double weight;

        public Edge(Vertex target, double weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static class Vertex implements Comparable<Vertex> {
        public final String name;
        public ArrayList<Edge> neighbours;
        public LinkedList<Vertex> path;
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;

        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }

        public Vertex(String name) {
            this.name = name;
            neighbours = new ArrayList<>();
            path = new LinkedList<>();
        }

        public String toString() {
            return name;
        }
    }
}
