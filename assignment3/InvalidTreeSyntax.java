/*This class is used to create invalid syntax errors when working
 * with binary trees
 */

public class InvalidTreeSyntax extends Exception {

    public InvalidTreeSyntax() {
        super();
    }

    public InvalidTreeSyntax(String message) {
        super(message);
    }
}
