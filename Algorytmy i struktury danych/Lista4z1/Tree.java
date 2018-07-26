public class Tree {
    Node root;
    int numberOfComparisons;

    public void insert(int value) {
        if (this.root == null) {
            this.root = new Node(value);
        } else {
            Node node = this.root, prev = null;
            while (node != null) {
                node.size++;
                prev = node;
                if (value >= node.value) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
            node = new Node(value);
            if (value >= prev.value) {
                prev.right = node;
            } else {
                prev.left = node;
            }
            node.parent = prev;
        }
    }

    public void delete(int value) {
        Node node = this.root;
        while (node != null) {
            node.size--;
            if (node.value == value) {
                break;
            } else if (value > node.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if (node != null) {
            Node x, y;
            if (node.left == null || node.right == null) {
                y = node;                             // Element ma co najwyżej jednego syna
            } else {
                y = minNode(node.right, true);        // Następnik znajduje się w prawym poddrzewie
            }
            if (y.left != null) {
                x = y.left;                           // Element posiada tylko lewego syna
            } else {
                x = y.right;                          // Element posiada albo tylko prawego syna, albo obydwu, albo żadnego
            }
            if (x != null) {
                x.parent = y.parent;                  // Przepięcie wskaźnika
            }
            if (y.parent == null) {
                this.root = x;                        // Następnik (syn) staje się korzeniem drzewa
            } else if (y == y.parent.left) {
                y.parent.left = x;                    // Usuwany węzeł był lewym synem
            } else {
                y.parent.right = x;                   // Usuwany węzeł był prawym synem
            }
            if (y != node) {
                node.value = y.value;                 // W miejsce zwolnionego węzła należy wstawić odpowiednią wartość
            }
        }
    }

    public static int height(Node node) {
        if (node == null) {
            return -1;
        } else {
            int leftH = height(node.left), rightH = height(node.right);
            if (leftH >= rightH) {
                return leftH+1;
            } else {
                return rightH+1;
            }
        }
    }

    public Node successor(Node node) {
        if (node.right != null) {
            return minNode(node.right, false);
        } else {
            Node n = node.parent;
            while (n != null && n.left != node) {
                node = n;
                n = n.parent;
            }
            return n;
        }
    }

    public void inorder(Node node) {
        if (this.root == null) {
            System.out.println();
            return;
        }
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
        if (this.root == node) {
            System.out.println();
        }
    }

    public Node minNode(Node node, boolean decSize) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            if (decSize) {
                node.size--;
            }
            node = node.left;
        }
        return node;
    }

    public void min(Node node) {
        if (node == null) {
            System.out.println();
            return;
        }
        while (node.left != null) {
            node = node.left;
        }
        System.out.println(node.value);
    }

    public void max(Node node) {
        if (node == null) {
            System.out.println();
            return;
        }
        while (node.right != null) {
            node = node.right;
        }
        System.out.println(node.value);
    }

    public void find(int value, boolean prints) {
        Node node = this.root;
        while (node != null) {
            numberOfComparisons++;
            if (node.value == value) {
                if (prints) {
                    System.out.println(1);
                }
                return;
            } else if (value > node.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if (prints) {
            System.out.println(0);
        }
    }
}
