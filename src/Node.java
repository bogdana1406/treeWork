import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Node<T> {
    private T data;
    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node<T>> children) {
        children.forEach(each->each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData (T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Optional<Node<T>> findDataInTree(Node node, T searchQuery) {
        if(node.getData().equals(searchQuery)) {
            return Optional.of(node);
        }

        List<Node<T>> children = node.getChildren();
        for(Node each : children) {
            System.out.println(each.getData());
            Optional findDataInTree = findDataInTree(each, searchQuery);
            if(findDataInTree.isPresent()) {
                return findDataInTree;
            }
        }
        return Optional.empty();
    }
    public List<List<T>> levelOrder(Node root)
    {
        if(root == null)
        {
            return new ArrayList<>();
        }

        List<List<T>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        // add root to queue
        queue.add(root);

        while(!queue.isEmpty())
        {
            List<T> levelNodes = new ArrayList<>();

            int queueSize = queue.size();

            for(int i = 0 ; i < queueSize; i++)
            {
                Node levelRoot = queue.remove();
                levelNodes.add((T) levelRoot.data);

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

