/*This class is used to print contents of a graph in a 
 * parenthesized list format
 */

import java.util.LinkedList;
import java.util.Queue;

public class ParenthesizedList implements DFSActions<Vertex> {

    Queue<String> res = new LinkedList<>();

    public void processVertex(Vertex vertex) {

        res.add(vertex.toString());
    }

    public void descendVertex(Vertex vertex) {

        res.add("(");
    }

    public void ascendVertex(Vertex vertex) {

        res.add(")");
    }

    public void cycleDetected() {

        res.add("*");
    }

    public String toString() {

        String ans = "";
        ans += "( ";

        while (res.size() > 0) {

            String c = res.peek();
            res.remove();

            if (c == "(") {

                if (res.peek() == ")") {

                    res.remove();
                    continue;

                } else if (res.peek() == "*") {

                    ans += res.peek() + " ";
                    res.remove();
                    res.remove();
                    continue;

                }

            }

            ans += c + " ";

        }

        ans += ")\n";
        return ans;

    }

}
