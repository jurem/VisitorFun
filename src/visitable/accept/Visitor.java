package visitable.accept;

public interface Visitor {

    void visit(Comment comment);

    void visit(Print print);

    void visit(PrintBold printBold);

    void visit(Block block);

}
