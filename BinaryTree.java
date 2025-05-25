//Ниже представлена реализация бинарного дерева с основными операциями: вставка, поиск, обходы
//нахождение минимального/максимального элемента и удаление узлов
public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    // Внутренний класс для узла дерева
    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinaryTree() {
        root = null;
    }

    // Вставка элемента
    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node<T> insertRec(Node<T> root, T data) {
        if (root == null) {
            return new Node<>(data);
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Поиск элемента
    public boolean contains(T data) {
        return containsRec(root, data);
    }

    private boolean containsRec(Node<T> root, T data) {
        if (root == null) {
            return false;
        }

        if (data.compareTo(root.data) == 0) {
            return true;
        }

        return data.compareTo(root.data) < 0 
            ? containsRec(root.left, data) 
            : containsRec(root.right, data);
    }

    // Удаление элемента
    public void delete(T data) {
        root = deleteRec(root, data);
    }

    private Node<T> deleteRec(Node<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = deleteRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = deleteRec(root.right, data);
        } else {
            // Узел с одним потомком или без потомков
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Узел с двумя потомками: получаем преемника (минимальный в правом поддереве)
            root.data = minValue(root.right);

            // Удаляем преемника
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private T minValue(Node<T> root) {
        T min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    // Нахождение минимального элемента
    public T findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMinRec(root);
    }

    private T findMinRec(Node<T> root) {
        return root.left == null ? root.data : findMinRec(root.left);
    }

    // Нахождение максимального элемента
    public T findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMaxRec(root);
    }

    private T findMaxRec(Node<T> root) {
        return root.right == null ? root.data : findMaxRec(root.right);
    }

    // Обходы дерева
    public List<T> inOrder() {
        List<T> result = new ArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(Node<T> root, List<T> result) {
        if (root != null) {
            inOrderRec(root.left, result);
            result.add(root.data);
            inOrderRec(root.right, result);
        }
    }

    public List<T> preOrder() {
        List<T> result = new ArrayList<>();
        preOrderRec(root, result);
        return result;
    }

    private void preOrderRec(Node<T> root, List<T> result) {
        if (root != null) {
            result.add(root.data);
            preOrderRec(root.left, result);
            preOrderRec(root.right, result);
        }
    }

    public List<T> postOrder() {
        List<T> result = new ArrayList<>();
        postOrderRec(root, result);
        return result;
    }

    private void postOrderRec(Node<T> root, List<T> result) {
        if (root != null) {
            postOrderRec(root.left, result);
            postOrderRec(root.right, result);
            result.add(root.data);
        }
    }

    // Высота дерева
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node<T> root) {
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(heightRec(root.left), heightRec(root.right));
    }

    // Проверка на пустоту
    public boolean isEmpty() {
        return root == null;
    }

    // Количество узлов
    public int size() {
        return sizeRec(root);
    }

    private int sizeRec(Node<T> root) {
        return root == null ? 0 : 1 + sizeRec(root.left) + sizeRec(root.right);
    }
}
