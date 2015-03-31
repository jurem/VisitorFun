package visitable.methods;

public class Comment extends Node {

    public final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public void print(int indent) {
        printIndent(indent, "// " + comment);
    }

    @Override
    public void exec() {
    }

    @Override
    public int size() {
        return 0;
    }

}
