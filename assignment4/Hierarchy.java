/*This class is used to print contents of a graph in hierarchal
 * format
 */

import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy implements DFSActions<Vertex> {

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
        int sz = 0;

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

            if (c == "(")
                sz++;

            else if (c == ")")
                --sz;

            if (c == "(" || c == ")")
                continue;

            if (c != "*")
                ans += "\n";

            for (int i = 0; i < sz; i++) {
                ans += "\t";

            }

            ans += c + " ";

        }

        ans += "\n";
        return ans;

    }

    public boolean isAlpha(String s) {

        return s != "(" && s != ")";

    }

}
