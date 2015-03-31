package visitable.accept;

public class Comment extends Node {

    public final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
