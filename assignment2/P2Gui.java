/* This class creates a GUI and contains funcionality
 * to read files containing polynomials. The contents of the files
 * are used to make polnomial objects. The objects are printed to the console.
 */

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class P2Gui extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel contentPane;
    private JButton loadButton;
    public static List<Polynomial> list = new ArrayList<>();

    public void constructAndShowGui() {

        frame = new JFrame("Polynomial checker");
        contentPane = new JPanel();
        loadButton = new JButton("Load Polynomial File");

        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Program has safely exited");
                // quit program
                System.exit(0);
            }
        });

        loadButton.addActionListener(this);
        contentPane.add(loadButton);
        frame.add(contentPane);
        frame.setSize(100, 100);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(loadButton)) {
            loadHandler();
        }
    }

    public void loadHandler() {
        doTheThing();
    }

    public ArrayList<String> load() {

        ArrayList<String> polynomialList = new ArrayList<>();
        JFileChooser file = new JFileChooser(".");
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        file.setSelectedFile(null);
        File selectedDirectory = null;

        int returnVal = file.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedDirectory = file.getSelectedFile();
            try {
                Scanner fileScan = new Scanner(selectedDirectory);
                if (selectedDirectory.isFile()) {
                    while (fileScan.hasNextLine()) {
                        String polynomial = fileScan.nextLine();
                        polynomialList.add(polynomial);
                    }
                }
                fileScan.close();
            }

            catch (FileNotFoundException e) {

                JOptionPane.showMessageDialog(this, "No Files in Directory");

            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog(this, "File Empty");
            }
        }
        return polynomialList;
    }

    public boolean isOrdered(List<Polynomial> list) {
        boolean isOrdered = true;
        Polynomial previous = list.get(list.size() - 1);
        for (int i = list.size() - 2; i > 0; i--) {
            if (previous.comparePolynomialExponent(list.get(i)) < 0) {
                isOrdered = false;
            }
        }
        return isOrdered;
    }

    public void doTheThing() {

        try {
            ArrayList<String> arrayString = load();

            for (String item : arrayString) {
                Polynomial poly = new Polynomial(item);
                System.out.println("Polynomial = " + poly.toString());
                list.add(poly);
            }
        } catch (InvalidPolynomialSyntax e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), e.getMessage());
        }
        if (list.isEmpty() == false) {
            System.out.println("Strong ordered: " + String.valueOf(OrderedList.checkSorted(list)));
            System.out.println("Weak ordered: " + String.valueOf(isOrdered(list)));
            System.out.println();
            list.clear();
        }
    }

    public static void main(String[] args) {
        P2Gui enabler = new P2Gui();
        enabler.constructAndShowGui();
    }
}
