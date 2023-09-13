package com.ruhalov.util;

import com.ruhalov.Coordinates;

import java.util.Objects;


public class Node implements Comparable<Node> {
    private final Coordinates position;
    private Node previousNode;
    private int distanceFromStartToNode;
    private int f;

    public Node(Coordinates position, int distanceFromStartToNode, Node previousNode) {
        this.position = position;
        this.distanceFromStartToNode = distanceFromStartToNode;
        this.previousNode = previousNode;
    }

    public Coordinates getPosition() {
        return position;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previusNode) {
        this.previousNode = previusNode;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getDistanceFromStartToNode() {
        return distanceFromStartToNode;
    }

    public void setDistanceFromStartToNode(int distanceFromStartToNode) {
        this.distanceFromStartToNode = distanceFromStartToNode;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.f, o.f);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(position, node.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
