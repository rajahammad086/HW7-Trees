
// Raja Hammad Mehmood
// In this program, we check if the tree is valid or not.

import java.util.Scanner;

/**
 * This program demonstrates a few routines for processing binary sort trees. It
 * uses a binary sort tree of strings. The user types in strings. The user's
 * string is converted to lower case, and -- if it is not already in the tree --
 * it is inserted into the tree. Then the number of nodes in the tree and a list
 * of items in the tree are output. The program ends when the user enters an
 * empty string.
 */
public class Question4 {
	/**
	 * An object of type TreeNode represents one node in a binary tree of strings.
	 */
	private static class TreeNode {
		String item; // The data in this node.
		TreeNode left; // Pointer to left subtree.
		TreeNode right; // Pointer to right subtree.

		TreeNode(String str) {
			// Constructor. Make a node containing the specified string.
			// Note that left and right pointers are initially null.
			item = str;
		}
	} // end nested class TreeNode

	private static TreeNode root; // Pointer to the root node in a binary tree.
									// This tree is used in this program as a
									// binary sort tree. When the tree is empty,
									// root is null (as it is initially).

	public static void main(String[] args) {
		TreeNode c1 = new TreeNode("3"); // Create leaf node
		c1.right = new TreeNode("4");
		c1.right.right = new TreeNode("6");
		c1.left = new TreeNode("2");
		System.out.println("tree in-order:");
		treeList(c1);
		if (isValid(c1) == true) {
			System.out.println("the tree is valid");
		} else {
			System.out.println("the tree is not valid");
		}

	} // end main()

	private static boolean isValid(TreeNode node) {
		boolean x = true;
		boolean y = true;
		while (x == false || y == false || (node.left == null && node.right == null)) {
			if (node.left.item.compareTo(node.item) < 0) {
				x = false;
			} else {
				isValid(node.left);
			}
			if (node.right.item.compareTo(node.item) > 0) {
				y = false;
			} else {
				isValid(node.right);
			}
		}
		return x && y;
	}

	/**
	 * Add the item to the binary sort tree to which the global variable "root"
	 * refers. (Note that root can't be passed as a parameter to this routine
	 * because the value of root might change, and a change in the value of a formal
	 * parameter does not change the actual parameter.)
	 */
	private static void treeInsert(String newItem) {
		if (root == null) {
			// The tree is empty. Set root to point to a new node containing
			// the new item. This becomes the only node in the tree.
			root = new TreeNode(newItem);
			return;
		}
		TreeNode runner; // Runs down the tree to find a place for newItem.
		runner = root; // Start at the root.
		while (true) {
			if (newItem.compareTo(runner.item) < 0) {
				// Since the new item is less than the item in runner,
				// it belongs in the left subtree of runner. If there
				// is an open space at runner.left, add a new node there.
				// Otherwise, advance runner down one level to the left.
				if (runner.left == null) {
					runner.left = new TreeNode(newItem);
					return; // New item has been added to the tree.
				} else
					runner = runner.left;
			} else {
				// Since the new item is greater than or equal to the item in
				// runner it belongs in the right subtree of runner. If there
				// is an open space at runner.right, add a new node there.
				// Otherwise, advance runner down one level to the right.
				if (runner.right == null) {
					runner.right = new TreeNode(newItem);
					return; // New item has been added to the tree.
				} else
					runner = runner.right;
			}
		} // end while
	} // end treeInsert()

	/**
	 * Return true if item is one of the items in the binary sort tree to which root
	 * points. Return false if not.
	 */
	static boolean treeContains(TreeNode root, String item) {
		if (root == null) {
			// Tree is empty, so it certainly doesn't contain item.
			return false;
		} else if (item.equals(root.item)) {
			// Yes, the item has been found in the root node.
			return true;
		} else if (item.compareTo(root.item) < 0) {
			// If the item occurs, it must be in the left subtree.
			return treeContains(root.left, item);
		} else {
			// If the item occurs, it must be in the right subtree.
			return treeContains(root.right, item);
		}
	} // end treeContains()

	/**
	 * Print the items in the tree in inorder, one item to a line. Since the tree is
	 * a sort tree, the output will be in increasing order.
	 */
	private static void treeList(TreeNode node) {
		if (node != null) {
			treeList(node.left); // Print items in left subtree.
			System.out.println("  " + node.item); // Print item in the node.
			treeList(node.right); // Print items in the right subtree.
		}
	} // end treeList()

	/**
	 * Count the nodes in the binary tree.
	 * 
	 * @param node A pointer to the root of the tree. A null value indicates an
	 *             empty tree.
	 * @return the number of nodes in the tree to which node points. For an empty
	 *         tree, the value is zero.
	 */
	private static int countNodes(TreeNode node) {
		if (node == null) {
			// Tree is empty, so it contains no nodes.
			return 0;
		} else {
			// Add up the root node and the nodes in its two subtrees.
			int leftCount = countNodes(node.left);
			int rightCount = countNodes(node.right);
			return 1 + leftCount + rightCount;
		}
	} // end countNodes()
} // end class SortTreeDemo