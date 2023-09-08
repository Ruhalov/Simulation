package com.ruhalov.util;

import com.ruhalov.Coordinates;


public class Node implements Comparable<Node> {
    private final Coordinates position;
    private final Node previusNode;
    private int f;
    private final int distanceFromStartToNode;

    public Node(Coordinates position, int distanceFromStartToNode, Node previusNode) {
        this.position = position;
        this.distanceFromStartToNode = distanceFromStartToNode;
        this.previusNode = previusNode;
    }

    public Coordinates getPosition() {
        return position;
    }

    public Node getPreviusNode() {
        return previusNode;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getDistanceFromStartToNode() {
        return distanceFromStartToNode;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.f, o.f);
    }
}
