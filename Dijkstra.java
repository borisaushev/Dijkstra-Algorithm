import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static Tools.Print.PrintTool.print;
import static Tools.Print.PrintTool.println;

public class Dijkstra {

    static HashSet<Node> done;
    static HashMap<Node, Integer> prices;
    static HashMap<Node, Node> parents;

    public static void main(String[] args) {

        done = new HashSet<>();
        prices = new HashMap<>();
        parents = new HashMap<>();

        Node start = new Node("start");
        Node A = new Node("A");
        Node B = new Node("B");
        Node end = new Node("end");

        start.addNode(A, 6);
        start.addNode(B, 2);
        B.addNode(A, 10);
        B.addNode(end, 10);
        A.addNode(end, 1);

        prices.put(start, 0);
        prices.put(A, 6);
        prices.put(B, 2);

        parents.put(A, start);
        parents.put(B, start);


        Node current;
        while((current = findMin()) != null) {
            for(Map.Entry<Node, Integer> neighbor : current.getNodes().entrySet()) {
                int price = neighbor.getValue() + prices.get(current);
                Node neighborNode = neighbor.getKey();

                if(prices.get(neighborNode) == null || prices.get(neighborNode) > price) {
                    prices.put(neighborNode, price);
                    parents.put(neighborNode, current);
                }
            }
            done.add(current);
        }


        current = end;
        print(end + " -> ");
        while(parents.get(current) != null) {
            print(parents.get(current) + " -> ");
            current = parents.get(current);
        }
        println("sum: " + prices.get(end));

    }

    public static Node findMin() {
        Node min = null;
        Integer minPrice = null;

        for(Map.Entry<Node, Integer> current : prices.entrySet()) {
            Node currentNode = current.getKey();
            int price = current.getValue();

            if(!done.contains(currentNode) && (min == null || minPrice > price)) {
                min = currentNode;
                minPrice = price;
            }

        }

        return min;

    }

}

class Node {

    private final Map<Node, Integer> nodes = new HashMap<>();
    private final String name;

    public Node(String name) {
        this.name = name;
    }

    public Map<Node, Integer> getNodes() {
        return nodes;
    }

    public void addNode(Node node, int price) {
        this.nodes.put(node, price);
    }

    public String toString() {
        return name;
    }

}