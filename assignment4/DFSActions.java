/*This interface is used to direct depth first 
 * search actions
 */

public interface DFSActions<V> {

    public void processVertex(V vertex);

    public void descendVertex(V vertex);

    public void ascendVertex(V vertex);

    public void cycleDetected();

}
