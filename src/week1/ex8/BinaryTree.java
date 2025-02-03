package week1.ex8;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.right = null;
        this.left = null;
    }
}

public class BinaryTree {
    /*
    Serialise: Tree to String
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sHelper(root, sb);


        return sb.toString();
    }

    public void sHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("N").append(",");
            return;
        }

        sb.append(node.val).append(",");
        sHelper(node.left, sb);
        sHelper(node.right, sb);
    }

    /*
    Deserialize: String to binary tree
     */
    public TreeNode deserialize(String s) {
        String[] arr = s.split(",");
        int[] indx = {0};
        return dHelper(arr, indx);
    }

    public TreeNode dHelper(String[] arr, int[] indx) {
        if (indx[0] >= arr.length || arr[indx[0]].equals("N")) {
            indx[0]++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(arr[indx[0]]));
        indx[0]++;

        node.left = dHelper(arr, indx);
        node.right = dHelper(arr, indx);

        return node;
    }

    // Helper method to print the tree
    public void printTree(TreeNode root) {
        if (root == null) return;

        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        BinaryTree codec = new BinaryTree();

        // Create a test tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        //  serialization
        String serialized = codec.serialize(root);
        System.out.println("Serialized string: " + serialized);

        //  deserialization
        TreeNode deserializedRoot = codec.deserialize(serialized);

        // Print original and deserialized trees
        System.out.print("Original tree: ");
        codec.printTree(root);
        System.out.println();

        System.out.print("Deserialized tree: ");
        codec.printTree(deserializedRoot);
        System.out.println();
    }
}