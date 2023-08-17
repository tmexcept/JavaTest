package com.binarytree;


import com.LeetCode.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeDepth {

    /**
     * 递归的思路：
     * 如果该树只有一个结点，它的深度为1.如果根节点只有左子树没有右子树，那么树的深度为左子树的深度加1；
     * 同样，如果只有右子树没有左子树，那么树的深度为右子树的深度加1。
     * 如果既有左子树也有右子树，那该树的深度就是左子树和右子树的最大值加1
     */
    public int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;
    }

    /**
     * 使用队列先进先出FIFO原理计算树的深度，层序遍历
     * 计算每一层节点的个数，也就是父节点一层级的左右子节点的个数和count，插入队列中；
     * 计算完成之后从队列之后取出，当从队列中取出的个数与count相等时候；
     * 也就是遍历一层结束一层的层序遍历
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0, depth = 0, nextCount = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count ++;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (count == nextCount) {
                nextCount = queue.size();
                count = 0;
                depth++;
            }
        }
        return depth;
    }

    public int TreeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            depth ++;
        }
        return depth;
    }
}
