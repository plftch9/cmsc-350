/*This class uses Jfile chooser to provide text files to the 
 * program, then uses methods in Graph and related classes
 * to print a class dependency list based on the contents of the 
 * text file
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P4Gui {

    static DirectedGraph graph = new DirectedGraph();

    public void readGraph() {

        JFileChooser choice = new JFileChooser(new File("."));
        int option = choice.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {

            try {

                Scanner input = new Scanner(choice.getSelectedFile());
                while (input.hasNextLine()) {

                    String edgeString = input.nextLine();
                    String[] edge = edgeString.split(" ");

                    if (graph.startingNode == null)
                        graph.startingNode = graph.getVertex(edge[0]);

                    for (int i = 1; i < edge.length; i++) {

                        graph.addEdge(edge[0], edge[i]);

                    }

                }

            } catch (FileNotFoundException e) {

                e.printStackTrace();

            }

        }

    }

    public static void main(String[] args) {

        new P4Gui().readGraph();
        graph.depthFirstSearch();
        System.out.println(graph.parenthesizedList.toString());
        System.out.println(graph.hierarchy.toString());
        graph.displayUnreachableClasses();

    }

}