package com.binarytree;

import com.LeetCode.entity.TreeNode;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 二叉树中任意结点的左右子树深度相差不超过1，那么它就是平衡二叉树
 */
public class BinaryTreeBalance {


    public static void main(String[] args) {
        //需求:将字符串按照长度排序
        TreeSet<String> ts = new TreeSet<>(new CompareByLen());        //Comparator c = new CompareByLen();
        ts.add("aaaaaaaa");
        System.out.println("-------");
        ts.add("z");
        System.out.println();
        ts.add("wc");
        System.out.println();
        ts.add("nba");
        System.out.println();
        ts.add("cba");
        System.out.println();

        System.out.println(ts);
    }
    //定义一个类，实现Comparator接口，并重写compare()方法，
    static class CompareByLen /*extends Object*/ implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {        //按照字符串的长度比较
            int num = s1.length() - s2.length(); //长度为主要条件
            System.out.println(s1);
            return num == 0 ? s1.compareTo(s2) : num;    //内容为次要条件
        }
    }

    /**
     * 获取一个节点的深度，也就是最深子节点的高度
     */
    public int getTreeDepth(TreeNode root){
        if(root == null) return 0;

        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right))+1;
    }

    /**
     * 遍历每个结点的时候，得到它的左右结点的深度。如果每个结点的左右二叉树的深度相差都不超过1，就是平衡二叉树
     * 这个方法每个结点被重复遍历，效率不高
     */
    public boolean isBalance(TreeNode root){
        if(root == null) return true;

        int left = getTreeDepth(root.left);
        int right = getTreeDepth(root.right);
        return Math.abs(left - right)<=1 && isBalance(root.left) && isBalance(root.right);
    }

    /**
     * 自底向上 另一个思路：
     * 如果我们用后序遍历的方式遍历二叉树的每一个结点，在遍历到一个结点之前我们就已经遍历了它的左右子树。
     * 只要在遍历每个结点的时候几下它的深度，就可以一次遍历判断每个结点是不是平衡二叉树
     */
    public boolean isBalance2(TreeNode root){
        int[] depth = new int[1];
        return isBalancedCore(root, depth);
    }
    //todo 没有理解透彻
    public boolean isBalancedCore(TreeNode node, int[] length) {
        if (node == null) {
            length[0] = 0;
            return true;
        }
        int[] left = new int[1];
        int[] right = new int[1];
        if (isBalancedCore(node.left, left) && isBalancedCore(node.right, right)) {
            if (Math.abs(left[0] - right[0]) <= 1) {
                length[0] = Math.max(left[0], right[0]) + 1;
                return true;
            }
        }
        return false;
    }

    public boolean isBalance3(TreeNode root) {
        return height(root) != -1;
    }
    public int height(TreeNode node) {
        //第一步：判断node == null
        if (node == null) {
            return 0;
        }
        // 第二步：求左子树高度
        int lH = height(node.left);
        //第五步:如果左子树不是平衡的，则子节点 也不是
        if (lH == -1) {
            return -1;
        }
        // 第三步：求右子树高度
        int rH = height(node.right);
        //第五步后；如果右子树不是平衡的，则子节点 也不是
        if (rH == -1) {
            return -1;
        }
        // 第四步： 判断node 左右子树高度相差，
        // 如果不是AVL，返回高度 -1
        if (Math.abs(lH - rH) > 1) {
            return -1;
        }
        //第四步后续：如果是AVL，返回此node结点的高度
        return Math.max(lH, rH) + 1;
    }
}
