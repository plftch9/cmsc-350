import javax.swing.*;
import java.awt.event.*;

public class P1Gui extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel contentPane;
    private JTextField input;
    private JLabel inputLabel;
    private JButton prefix;
    private JButton postfix;
    private JLabel result;
    private Converter converter = new Converter();

    public void constructAndShowGui() {

        frame = new JFrame("Expression Converter");
        contentPane = new JPanel();
        inputLabel = new JLabel("Enter Expression ");
        input = new JTextField(20);
        input.setBounds(10, 20, 80, 25);
        inputLabel.setLabelFor(input);
        prefix = new JButton("Prefix to Postfix");
        prefix.setBounds(10, 100, 25, 15);
        postfix = new JButton("Postfix to Prefix");
        postfix.setBounds(10, 100, 25, 15);
        result = new JLabel("Result ");
        result.setBounds(10, 350, 40, 25);

        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Program has safely exited");
                // quit program
                System.exit(0);
            }
        });

        prefix.addActionListener(this);
        postfix.addActionListener(this);
        contentPane.add(inputLabel);
        contentPane.add(input);
        contentPane.add(prefix);
        contentPane.add(postfix);
        contentPane.add(result);
        frame.add(contentPane);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(prefix)) {
            prefixHandler(input.getText());
        }
        if (source.equals(postfix)) {
            postfixHandler(input.getText());
        }
    }

    public void prefixHandler(String input) {
        try {
            result.setText(converter.prefix(input));
        } catch (SyntaxError e) {
            JFrame error2 = new JFrame();
            JOptionPane.showMessageDialog(error2, "Error Converting, Check syntax");
        }

    }

    public void postfixHandler(String input) {
        try {
            result.setText(converter.postfix(input));
        } catch (SyntaxError e) {
            JFrame error2 = new JFrame();
            JOptionPane.showMessageDialog(error2, "Error Converting, Check syntax");
        }
    }

    public static void main(String[] args) throws Exception {
        P1Gui enabler = new P1Gui();
        enabler.constructAndShowGui();
    }
}
