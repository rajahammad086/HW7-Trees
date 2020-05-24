//Raja Hammad Mehmood
// Author: Chris Fietkiewicz. Demonstrated ExpNode class on page 466 of
//Introduction to Programming Using Java by David Eck.
// We print the infix form for the tree.

enum NodeType {
	NUMBER, OPERATOR
} // Possible kinds of node.

public class Question1 {
	public static void main(String[] args) {
		ExpNode c1 = new ExpNode(1); // Create leaf node
		ExpNode c2 = new ExpNode(2);
		ExpNode c3 = new ExpNode(3);
		ExpNode c4 = new ExpNode(4);
		ExpNode o1 = new ExpNode('+', c1, c2);
		ExpNode o2 = new ExpNode('-', c3, c4);
		ExpNode root = new ExpNode('*', o1, o2);
		System.out.println("Post Order:");
		postorderPrint(root);
		System.out.print(" = ");
		System.out.println(getValue(root)); // Print the value
		System.out.println("Infix form:");
		infixForm(root);
		System.out.print(" = ");
		System.out.println(getValue(root)); // Print the value
	}

	/**
	 * Print all the items in the tree to which root points. The items in the left
	 * subtree are printed first, followed by the items in the right subtree and
	 * then the item in the root node.
	 */
	static void postorderPrint(ExpNode root) {
		if (root.kind == NodeType.NUMBER) { // (Otherwise, there's nothing to print.)
			System.out.print(root.number + " ");
		} else {
			postorderPrint(root.left); // Print items in left subtree.
			postorderPrint(root.right); // Print items in right subtree.
			System.out.print(root.op + " "); // Print the root item.
		}
	} // end postorderPrint()

	/**
	 * Print all the items in the tree in infix form.
	 */
	static void infixForm(ExpNode root) {
		if (root.kind == NodeType.NUMBER) { // (Otherwise, there's nothing to print.)
			System.out.print(root.number + " ");
		} else {
			System.out.print("(");
			infixForm(root.left); // Print items in left subtree.
			System.out.print(root.op + " "); // Print the root item.
			infixForm(root.right); // Print items in right subtree.
			System.out.print(")");

		}
	}

	// Method from page 467. Uses a single class "ExpNode" given on page 466.
	static double getValue(ExpNode node) {
		// Return the value of the expression represented by
		// the tree to which node refers. Node must be non-null.
		if (node.kind == NodeType.NUMBER) {
			// The value of a NUMBER node is the number it holds.
			return node.number;
		} else { // The kind must be OPERATOR.
					// Get the values of the operands and combine them
					// using the operator.
			double leftVal = getValue(node.left);
			double rightVal = getValue(node.right);
			switch (node.op) {
			case '+':
				return leftVal + rightVal;
			case '-':
				return leftVal - rightVal;
			case '*':
				return leftVal * rightVal;
			case '/':
				return leftVal / rightVal;
			default:
				return Double.NaN; // Bad operator.
			}
		}
	} // end getValue()
}

class ExpNode { // A node in an expression tree.
	NodeType kind; // Which type of node is this?
	double number; // The value in a node of type NUMBER.
	char op; // The operator in a node of type OPERATOR.
	ExpNode left; // Pointers to subtrees,
	ExpNode right; // in a node of type OPERATOR.

	ExpNode(double val) {
		// Constructor for making a node of type NUMBER.
		kind = NodeType.NUMBER;
		number = val;
	}

	ExpNode(char op, ExpNode left, ExpNode right) {
		// Constructor for making a node of type OPERATOR.
		kind = NodeType.OPERATOR;
		this.op = op;
		this.left = left;
		this.right = right;
	}
} // end class ExpNode
