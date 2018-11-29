package pers.zyc.learn;

/**
 * @author YanchaoZhang
 * @date 2018/11/14 13:34
 */
public class BRTree {

    BRNode root;

    class BRNode {
        int value;
        BRNode parent;
        BRNode left;
        BRNode right;
        boolean red;

        public BRNode(int value) {
            this.value = value;
        }

//        public BRNode root() {
//            BRNode node = this;
//            for (; node.parent != null; node = node.parent) ;
//            return node;
//        }

        private void rotateRight() {
            if (left != null) {
                BRNode temp = left;
                left.parent = parent;
                if (parent == null) root = left;
                left = left.right;
                temp.right = this;
                parent = temp;
            }
        }

        private void rotateLeft() {
            if (right != null) {
                BRNode temp = right;
                right.parent = parent;
                if (parent == null) root = right;
                right = right.left;
                temp.left = this;
                parent = temp;
            }
        }
    }

}
