public class Nicknames {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int numOfNames = io.getInt();
        AVLTree avlt = new AVLTree();
        for (int i = 0; i < numOfNames; i++) {
            String str = io.getWord();
            avlt.insert(str);
            /*
             * io.println(avlt);
             */
        }
        int numOfNicknames = io.getInt();
        for (int j = 0; j < numOfNicknames; j++) {
            String nickname = io.getWord();
            int rank1 = avlt.getRank(nickname);
            char lastC = nickname.charAt(nickname.length() - 1);
            char newLastC = (char) (lastC + 1);
            String incrementNickname = nickname.substring(0, nickname.length() - 1) + newLastC;
            int rank2 = avlt.getRank(incrementNickname);
            int result = rank2 - rank1;
            io.println(result);
        }
        io.close();
    }

}

class AVLVertex {
    String key;
    AVLVertex parent, left, right;
    int height;
    int size;

    AVLVertex(String key) {
        this.key = key;
        this.parent = this.left = this.right = null;
        this.height = 0;
        this.size = 1;
    }
}

class AVLTree {
    AVLVertex root;

    AVLTree() {
        root = null;
    }

    public void insert(String v) {
        root = insert(root, v);
    }

    // helper recursive method to perform insertion of new vertex into AVL
    public AVLVertex insert(AVLVertex T, String v) {
        if (T == null) // if the root is null
            return new AVLVertex(v); // insertion point is found
        else if (T.key.compareTo(v) < 0) {
            // if root is not null, compare root with v
            // insert v to the right if root is smaller to v
            T.right = insert(T.right, v);
            T.right.parent = T;
        } else if (T.key.compareTo(v) > 0) {
            T.left = insert(T.left, v);
            T.left.parent = T;
        } else {
            return T;
        }
        T.size++; // the size of root will increase by one after an insertion
        T.height = 1 + Math.max(getH(T.left), getH(T.right)); // height of T will change
        return balance(T); // do balancing
    }

    public int getH(AVLVertex T) { // get height
        if (T != null) {
            return T.height;
        } else {
            return -1;
        }
    }

    public int getS(AVLVertex T) { // get size
        if (T != null) {
            return T.size;
        } else {
            return 0;
        }
    }

    public int getBF(AVLVertex T) {
        return getH(T.left) - getH(T.right);
    }

    public AVLVertex balance(AVLVertex T) {
        if (getBF(T) == 2) {
            if (getBF(T.left) >= 0) {
                return rightRotate(T); // right
            } else {
                T.left = leftRotate(T.left); // left
                return rightRotate(T); // then right
            }
        }
        if (getBF(T) == -2) {
            if (getBF(T.right) <= 0) {
                return leftRotate(T); // left
            } else {
                T.right = rightRotate(T.right); // right
                return leftRotate(T); // left
            }
        }
        return T;
    }

    public AVLVertex leftRotate(AVLVertex T) {
        AVLVertex w = T.right;
        w.parent = T.parent;
        T.parent = w;
        T.right = w.left;
        if (w.left != null) {
            w.left.parent = T;
        }
        w.left = T;
        // update heights
        T.height = 1 + Math.max(getH(T.left), getH(T.right));
        w.height = 1 + Math.max(getH(w.left), getH(w.right));

        // update sizes
        T.size = 1 + getS(T.left) + getS(T.right);
        w.size = 1 + getS(w.left) + getS(w.right);

        return w;
    }

    public AVLVertex rightRotate(AVLVertex T) {
        AVLVertex w = T.left;
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;
        if (w.right != null) {
            w.right.parent = T;
        }
        w.right = T;
        // update heights
        T.height = 1 + Math.max(getH(T.left), getH(T.right));
        w.height = 1 + Math.max(getH(w.left), getH(w.right));

        // update sizes
        T.size = 1 + getS(T.left) + getS(T.right);
        w.size = 1 + getS(w.left) + getS(w.right);

        return w;
    }

    public int getRank(String v) { // get rank
        return getRank(v, root);
    }

    public int getRank(String v, AVLVertex T) {
        if (T == null) {
            return 0;
        }
        if (T.key.compareTo(v) > 0) { // v is smaller than root
            return getRank(v, T.left);
        } else if (T.key.compareTo(v) < 0) {
            return 1 + getS(T.left) + getRank(v, T.right);
        } else {
            return getS(T.left);
        }

    }

    @Override
    public String toString() {
        return inOrderTraversal(root);
    }

    // Helper method to perform in-order traversal
    private String inOrderTraversal(AVLVertex T) {
        if (T == null) {
            return ""; // base case
        }
        StringBuilder sb = new StringBuilder();
        // Traverse left subtree
        sb.append(inOrderTraversal(T.left));
        // Visit node
        sb.append("Key: ").append(T.key)
                .append(", Height: ").append(T.height)
                .append(", Size: ").append(T.size)
                .append("\n");
        // Traverse right subtree
        sb.append(inOrderTraversal(T.right));
        return sb.toString();
    }
}