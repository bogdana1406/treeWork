import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Node<String> {
    private String data;
    private List<Node<String>> children = new ArrayList<>();
    private Node<String> parent;

    public Node(String data) {
        this.data = data;
    }

    public Node<String> addChild(Node<String> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node<String>> children) {
        children.forEach(each->each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node<String>> getChildren() {
        return children;
    }

    public String getData() {
        return data;
    }

    public void setData (String data) {
        this.data = data;
    }

    public Node<String> getParent() {
        return parent;
    }

    public void setParent(Node<String> parent) {
        this.parent = parent;
    }

    public Optional<Node<String>> findDataInTree(Node node, String searchQuery) {
        if(node.getData().equals(searchQuery)) {
            return Optional.of(node);
        }

        List<Node<String>> children = node.getChildren();
        for(Node each : children) {
            System.out.println(each.getData());
            Optional findDataInTree = findDataInTree(each, searchQuery);
            if(findDataInTree.isPresent()) {
                return findDataInTree;
            }
        }
        return Optional.empty();
    }
    public List<List<String>> levelOrder(Node root)
    {
        if(root == null)
        {
            return new ArrayList<>();
        }

        List<List<String>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        // add root to queue
        queue.add(root);

        while(!queue.isEmpty())
        {
            List<String> levelNodes = new ArrayList<>();

            int queueSize = queue.size();

            for(int i = 0 ; i < queueSize; i++)
            {
                Node levelRoot = queue.remove();
                levelNodes.add((String) levelRoot.data);

                List<Node> children = levelRoot.children;
                for(Node tree : children)
                {
                    queue.add(tree);
                }
            }
            result.add(levelNodes);
        }
        return result;
    }
}

