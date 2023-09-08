package com.ruhalov.util;

import com.ruhalov.Coordinates;
import com.ruhalov.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.abs;


public class AStarPathFinder {
    private ArrayList<Coordinates> pathToTarget; //TODO: не используется ретерн не возвращается, если путь найден он строиться методом calculatePathFromNode()
    private final List<Node> openNodes = new ArrayList<>();
    private final List<Node> closedNodes = new ArrayList<>();
    private Coordinates target;

    public List<Coordinates> findPathToTarget(Coordinates start,
                                               Coordinates target,
                                               World world) {
        this.target = target;
        if (start.equals(target)) {
            return pathToTarget;
        }
        Node startNode = new Node(start, 0, null);
        closedNodes.add(startNode);
        openNodes.addAll(getAvailableNeighboringNodes(startNode, world));
        while (!openNodes.isEmpty()) {
            openNodes.forEach(x -> x.setF(x.getDistanceFromStartToNode() + manhattanDistance(x.getPosition(), target, world)));
            Node nodeToCheck = openNodes.stream().min(Node::compareTo).get();
            if (nodeToCheck.getPosition().equals(target)) {
                return calculatePathFromNode(nodeToCheck);
            }
            openNodes.remove(nodeToCheck);
            closedNodes.add(nodeToCheck);

            for (Node neighbour : getAvailableNeighboringNodes(nodeToCheck, world)) {
                if (closedNodes.contains(neighbour)) {
                    continue;
                }
                neighbour.setF(neighbour.getDistanceFromStartToNode() + manhattanDistance(neighbour.getPosition(), target, world));
                if (openNodes.contains(neighbour) && openNodes.stream().anyMatch(x -> x.getDistanceFromStartToNode() < neighbour.getDistanceFromStartToNode())) {
                    continue;
                }
                openNodes.add(neighbour);
            }
        }
        return pathToTarget;
    }

    private List<Coordinates> calculatePathFromNode(Node node) {
        List<Coordinates> path = new ArrayList<>();
        Node currNode = new Node(node.getPosition(), node.getDistanceFromStartToNode(), node.getPreviusNode());
        while (currNode != null) {
            path.add(currNode.getPosition());
            currNode = currNode.getPreviusNode();
        }
        Collections.reverse(path);
        return path;
    }

    private ArrayList<Node> getAvailableNeighboringNodes(Node node, World world) {
        ArrayList<Coordinates> neighbors = world.getNeighboringCoordinates(node.getPosition());
        ArrayList<Node> availableNeighboringNodes = new ArrayList<>();
        for (Coordinates coordinates : neighbors) {
            if (world.isEmptyCell(coordinates) || coordinates.equals(target)) {
                availableNeighboringNodes.add(new Node(coordinates, node.getDistanceFromStartToNode() + 1, node));
            }
        }
        return availableNeighboringNodes;
    }

    public int manhattanDistance(Coordinates start,
                                 Coordinates target,
                                 World world) {
        int DistanceWithoutAxisTransitions = abs(start.getX() - target.getX()) +
                abs(start.getY() - target.getY());
        int DistanceWithXTransition = 0;
        int DistanceWithYTransition = 0;
        int DistanceWithXYTransitions = 0;

        int distXWithRightTransition = abs(world.getWidth() - start.getX() + target.getX());
        int distXWithLeftTransition = abs(world.getWidth() - target.getX() + start.getX());
        int distYWithUpTransition = abs(world.getHeight() - target.getY() + start.getY());
        int distYWithDownTransition = abs(world.getHeight() - start.getY() + target.getY());
        int distX = abs(start.getX() - target.getX());
        int distY = abs(start.getY() - target.getY());

        if (start.getX() > target.getX() &&
                start.getY() > target.getY()) {
            DistanceWithXTransition = distXWithRightTransition + distY;
            DistanceWithYTransition = distYWithDownTransition + distX;
            DistanceWithXYTransitions = distXWithRightTransition + distYWithDownTransition;
        } else if (start.getX() < target.getX() &&
                start.getY() > target.getY()) {
            DistanceWithXTransition = distXWithLeftTransition + distY;
            DistanceWithYTransition = distYWithDownTransition + distX;
            DistanceWithXYTransitions = distXWithLeftTransition + distYWithDownTransition;
        } else if (start.getX() > target.getX() &&
                start.getY() < target.getY()) {
            DistanceWithXTransition = distXWithRightTransition + distY;
            DistanceWithYTransition = distYWithDownTransition + distX;
            DistanceWithXYTransitions = distXWithRightTransition + distYWithDownTransition;
        } else if (start.getX() < target.getX() &&
                start.getY() < target.getY()) {
            DistanceWithXTransition = distXWithLeftTransition + distY;
            DistanceWithYTransition = distYWithUpTransition + distX;
            DistanceWithXYTransitions = distXWithLeftTransition + distYWithUpTransition;
        }
        return Stream.of(DistanceWithoutAxisTransitions,
                DistanceWithXTransition,
                DistanceWithYTransition,
                DistanceWithXYTransitions).min(Integer::compareTo).get();
    }
}
