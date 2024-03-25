/* This class is used to throw exceptions when
 * there is invalid syntax in a polynomial expression
 */

public class InvalidPolynomialSyntax extends RuntimeException {
    public InvalidPolynomialSyntax(String e) {
        super(e);
    }
}
