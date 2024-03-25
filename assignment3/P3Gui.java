/* This class constructs a GUI and also calls functions
 * necessary to facilitate creating and manipulating data 
 * in binary trees
 */

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class P3Gui extends JFrame implements ActionListener {

    // 2 labels
    // 2 text fields
    // 7 buttons
    JTextField input = new JTextField(20), output = new JTextField(30);
    JButton makeTree = new JButton("Make Tree"), isBalanced = new JButton("Is Balanced?"),
            isFull = new JButton("is Full?"), isProper = new JButton("Is Proper?"), height = new JButton("Height"),
            nodes = new JButton("Nodes"), inOrder = new JButton("In Order");
    private static BinaryTree inputExpression;

    public P3Gui() {

        super("Binary Tree Categorizer");
        setSize(750, 150);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Program has safely exited");
                System.exit(0);
            }
        });

        setLayout(new GridLayout(3, 1));
        JComponent[] inputs = { new JLabel("Enter Expression"), input };
        JComponent[] outputs = { new JLabel("Output:"), output };
        JButton[] buttons = { makeTree, isBalanced, isFull, isProper, height, nodes, inOrder };

        makeFlowPanel(inputs);
        makeFlowPanel(buttons);
        makeFlowPanel(outputs);
        addActionListeners(buttons);

        output.setEditable(false);
    }

    private void makeFlowPanel(JComponent[] jArray) {
        JPanel contentPane = new JPanel(new FlowLayout());
        for (JComponent jComponent : jArray) {
            contentPane.add(jComponent);
        }
        add(contentPane);
    }

    private void addActionListeners(JButton[] jButtonArray) {
        for (JButton jButton : jButtonArray) {
            jButton.addActionListener(this);
        }
    }

    public void makeTreeHandler(String expression) {
        try {
            inputExpression = new BinaryTree(expression);
            output.setText(inputExpression.toString());
        } catch (InvalidTreeSyntax e) {
            JOptionPane.showMessageDialog(getParent(), e.getMessage());
        } catch (IndexOutOfBoundsException indexExcept) {
            JOptionPane.showMessageDialog(getParent(), "No input");
        }
    }

    public void isBalancedHandler() {

        output.setText(String.valueOf(inputExpression.isBalanced()));

    }

    public void isFullHandler() {

        output.setText(String.valueOf(inputExpression.isFull()));

    }

    public void isProperHandler() {

        output.setText(String.valueOf(inputExpression.isProper()));

    }

    public void heightHandler() {

        output.setText(String.valueOf(inputExpression.height()));

    }

    public void nodesHandler() {
        output.setText(String.valueOf(inputExpression.nodes()));

    }

    public void inOrderHandler() {

        output.setText(String.valueOf(inputExpression.inOrder()));

    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        try {
            if (source.equals(makeTree)) {
                makeTreeHandler(input.getText());
            } else if (source.equals(isBalanced)) {
                isBalancedHandler();
            } else if (source.equals(isFull)) {
                isFullHandler();
            } else if (source.equals(isProper)) {
                isProperHandler();
            } else if (source.equals(height)) {
                heightHandler();
            } else if (source.equals(nodes)) {
                nodesHandler();
            } else if (source.equals(inOrder)) {
                inOrderHandler();
            }
        } catch (IndexOutOfBoundsException err) {
            JOptionPane.showMessageDialog(getParent(), err.getMessage());
        } catch (NullPointerException err) {
            JOptionPane.showMessageDialog(getParent(), "Please provide an input");
        }
    }

    public static void main(String[] args) {
        P3Gui enabler = new P3Gui();
        enabler.setVisible(true);

    }
}
