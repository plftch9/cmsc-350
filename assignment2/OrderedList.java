/* This class is used to check the sorting
 * of lists containing polynomials
 */

import java.util.List;

public class OrderedList {

    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
        boolean isSorted = true;
        for (int i = list.size() - 1; i > 0; i--) {
            T listItem = list.get(i);
            if (checkSorted(list, listItem)) {
                isSorted = false;
            }
        }
        return isSorted;
    }

    private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T listItem) {
        T itemValue = list.get(list.indexOf(listItem));
        T nextItem = list.get(list.indexOf(listItem) - 1);

        if (nextItem != null) {
            return itemValue.compareTo(nextItem) >= 0;
        }
        return true;

    }
}
