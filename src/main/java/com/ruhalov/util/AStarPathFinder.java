package com.ruhalov.util;

import com.ruhalov.Coordinates;
import com.ruhalov.World;
import com.ruhalov.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

import static java.lang.Math.abs;


public class AStarPathFinder {
    private final ArrayList<Coordinates> pathToTarget = new ArrayList<>(); //TODO: не используется ретерн не возвращается, если путь найден он строиться методом calculatePathFromNode()
    private Coordinates target;

    public List<Coordinates> findPathToTarget(Coordinates start, Coordinates target, World world) {
        this.target = target;
        if (start.equals(target)) {
            return pathToTarget;
        }
        Node startNode = new Node(start, 0, null);
        startNode.setF(manhattanDistance(startNode.getPosition(), target, world));
        List<Node> closedNodes = new ArrayList<>();
        List<Node> openNodes = new ArrayList<>(getAvailableNeighboringNodes(startNode, world));
        closedNodes.add(startNode);
        while (!openNodes.isEmpty()) {
            openNodes.forEach(x -> x.setF(x.getDistanceFromStartToNode() + manhattanDistance(x.getPosition(), target, world)));
            Node nodeToCheck = openNodes.stream().min(Node::compareTo).get();
            if (nodeToCheck.getPosition().equals(target)) {
                return calculatePathFromNode(nodeToCheck);
            }
            closedNodes.add(nodeToCheck);
            openNodes.remove(nodeToCheck);
            for (Node neighbour : getAvailableNeighboringNodes(nodeToCheck, world)) {
                if (closedNodes.contains(neighbour)) {
                    continue;
                }
                neighbour.setF(neighbour.getDistanceFromStartToNode() + manhattanDistance(neighbour.getPosition(), target, world));
                if (!openNodes.contains(neighbour) || nodeToCheck.getDistanceFromStartToNode() + 1 < neighbour.getDistanceFromStartToNode()) {
                    neighbour.setDistanceFromStartToNode(nodeToCheck.getDistanceFromStartToNode() + 1);
                    neighbour.setF(neighbour.getDistanceFromStartToNode() + manhattanDistance(neighbour.getPosition(), target, world));
                    neighbour.setPreviousNode(nodeToCheck);
                    if (!openNodes.contains(neighbour)) {
                        openNodes.add(neighbour);
                    }
                }
            }
        }
        return pathToTarget;
    }

    private List<Coordinates> calculatePathFromNode(Node node) {
        List<Coordinates> path = new ArrayList<>();
        Node currNode = new Node(node.getPreviousNode().getPosition(), node.getPreviousNode().getDistanceFromStartToNode(), node.getPreviousNode().getPreviousNode()); //todo fix
        while (currNode != null) {
            path.add(currNode.getPosition());
            currNode = currNode.getPreviousNode();
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

    public int manhattanDistance(Coordinates start, Coordinates target, World world) {
        int distanceWithoutAxisTransitions = abs(start.getX() - target.getX()) + abs(start.getY() - target.getY());
        int distanceWithXTransition = 0;
        int distanceWithYTransition = 0;
        int distanceWithXYTransitions = 0;

        int distXWithRightTransition = abs(world.getWidth() - start.getX() + target.getX());
        int distXWithLeftTransition = abs(world.getWidth() - target.getX() + start.getX());
        int distYWithUpTransition = abs(world.getHeight() - target.getY() + start.getY());
        int distYWithDownTransition = abs(world.getHeight() - start.getY() + target.getY());
        int distX = abs(start.getX() - target.getX());
        int distY = abs(start.getY() - target.getY());

        if (start.getX() >= target.getX() && start.getY() >= target.getY()) {
            distanceWithXTransition = distXWithRightTransition + distY;
            distanceWithYTransition = distYWithDownTransition + distX;
            distanceWithXYTransitions = distXWithRightTransition + distYWithDownTransition;
        } else if (start.getX() <= target.getX() && start.getY() >= target.getY()) {
            distanceWithXTransition = distXWithLeftTransition + distY;
            distanceWithYTransition = distYWithDownTransition + distX;
            distanceWithXYTransitions = distXWithLeftTransition + distYWithDownTransition;
        } else if (start.getX() >= target.getX() && start.getY() < target.getY()) {
            distanceWithXTransition = distXWithRightTransition + distY;
            distanceWithYTransition = distYWithDownTransition + distX;
            distanceWithXYTransitions = distXWithRightTransition + distYWithDownTransition;
        } else if (start.getX() <= target.getX() && start.getY() < target.getY()) {
            distanceWithXTransition = distXWithLeftTransition + distY;
            distanceWithYTransition = distYWithUpTransition + distX;
            distanceWithXYTransitions = distXWithLeftTransition + distYWithUpTransition;
        }
        return Stream.of(distanceWithoutAxisTransitions, distanceWithXTransition, distanceWithYTransition, distanceWithXYTransitions)
                .min(Integer::compareTo)
                .get();
    }
    public <T extends Entity> T selectNearestTarget(Coordinates start, Class<T> classOfTarget, World world) {
        List<T> targets = world.getEntitiesByClass(classOfTarget);
        if (targets.isEmpty()) return null;
        TreeMap<Integer, T> distancesToTarget = new TreeMap<>();
        for (T entity : targets) {
            int distance = manhattanDistance(start, entity.getCoordinates(), world);
            distancesToTarget.put(distance, entity);
        }
        return distancesToTarget.get(distancesToTarget.firstKey());
    }
}
