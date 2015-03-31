package visitable.clean;

public class Block extends Statement {

    public final Node first;
    public final Node second;

    public Block(Node first, Node second) {
        this.first = first;
        this.second = second;
    }

}
