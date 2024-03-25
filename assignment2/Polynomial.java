/* This class handles the creation and storage of polynomial
    objects using a linked list. This class also contains methods
    used to itearate the linked list and compare polynomials
 */

import java.util.Iterator;
import java.util.Scanner;

public class Polynomial implements Comparable<Polynomial>, Iterable<Polynomial.Node> {
    public Node head = null;

    // class to be used to create nodes in linked list
    public static class Node {
        double coefficient;
        int exponent;
        Node head;
        Node next;

        public Node(double co, int ex) {
            coefficient = co;
            exponent = ex;
            next = null;
        }

        public int getExponent() {
            return this.exponent;
        }

        public double getCoefficient() {
            return this.coefficient;
        }

        public Node getHead() {
            return head;
        }

        public Node getNext() {
            return next;
        }

        public String toString() {
            String polyString = String.valueOf(Math.abs(coefficient));
            if (exponent == 0) {
                return polyString;
            } else if (exponent == 1) {
                return polyString + "x";
            } else {
                return polyString + "x^" + exponent;
            }
        }
    }

    // constructor
    public Polynomial(String polynomial) {
        Scanner scanner = new Scanner(polynomial);
        try {
            while (scanner.hasNext()) {
                addToPoly(scanner.nextDouble(), scanner.nextInt());
            }

        } catch (Exception e) {
            scanner.close();
            e.printStackTrace();
            throw new InvalidPolynomialSyntax("Error loading string.");
        }
        scanner.close();
    }

    // this method adds polynomials to the linked list as nodes
    public void addToPoly(double coefficient, int exponent) {

        if (exponent < 0)
            throw new InvalidPolynomialSyntax("Negative exponent detected.\nCheck syntax");

        Node currentNode = head;

        if (currentNode == null) {
            head = new Node(coefficient, exponent);
            head.next = null;
        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node(coefficient, exponent);
        }

    }

    // compareTo inherited from interface
    public int compareTo(Polynomial comparitor) {
        Node currentNode = this.head;
        Node compareNode = comparitor.head;

        while (currentNode != null && compareNode != null) {
            if (currentNode.getExponent() != compareNode.getExponent()) {
                return currentNode.getExponent() - compareNode.getExponent();
            } else if (currentNode.getCoefficient() != compareNode.getCoefficient()) {
                if (currentNode.getCoefficient() < compareNode.getCoefficient()) {
                    return -1;
                } else if (currentNode.getCoefficient() > compareNode.getCoefficient()) {
                    return 1;
                }
            }

            currentNode = currentNode.getNext();
            compareNode = compareNode.getNext();
        }

        if (currentNode == null && compareNode == null) {
            return 0;
        }

        if (currentNode == null) {
            return -1;
        } else {
            return 1;
        }

    }

    // iterator inherited from interface
    public Iterator<Node> iterator() {

        return new Iterator<Node>() {
            public Node currentNode = head.getHead();

            public boolean hasNext() {
                return (currentNode != null) && (currentNode.getNext() != null);
            }

            public Node next() {
                Node node = currentNode;
                currentNode = currentNode.next;
                return node;
            }
        };
    }

    // compares two polynomial exponents
    public int comparePolynomialExponent(Polynomial comparitor) {
        Node currentNode = this.head;
        Node compareNode = comparitor.head;

        while (currentNode != null && compareNode != null) {
            if (currentNode.getExponent() != compareNode.getExponent()) {
                return currentNode.getExponent() - compareNode.getExponent();
            } else {
                currentNode = currentNode.getNext();
                compareNode = compareNode.getNext();
            }
        }

        if ((currentNode == null && compareNode == null)) {
            return 0;
        }
        if (compareNode == null) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        // stringbuilder converts node objects to a string
        StringBuilder string = new StringBuilder();

        if (head.coefficient > 0) {
            string.append(head.toString());
        } else {
            string.append(" - ");
            string.append(head.toString());
        }

        for (Node placeholderNode = head.next; placeholderNode != null; placeholderNode = placeholderNode.next) {
            if (placeholderNode.coefficient < 0) {
                string.append(" - ");
                string.append(placeholderNode.toString());
            } else {
                string.append(" + ");
                string.append(placeholderNode.toString());
            }
        }
        return string.toString();
    }

}
