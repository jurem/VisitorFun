package casting;


public class Visitor {

    protected void printIndent(int indent, String msg) {
        for (int i = 0; i < indent; i++)
            System.out.print("   ");
        System.out.println(msg);
    }

    public void print(int indent, Node node) {
        if (node instanceof Comment) {
            printIndent(indent, "// " + ((Comment) node).comment);
        } else if (node instanceof Print) {
            printIndent(indent, "print '" + ((Print) node).message + "'");
        } else if (node instanceof PrintFancy) {
            printIndent(indent, "printFancy '" + ((PrintFancy) node).message + "'");
        } else if (node instanceof Block) {
            printIndent(indent, "begin");
            print(indent + 1, ((Block) node).first);
            print(indent + 1, ((Block) node).second);
            printIndent(indent, "end");
        } else {
            System.err.println("ERR: no match for node.");
            System.exit(42);
        }
    }

    public void dump(Node node) {
        if (node instanceof Comment) {
            System.out.print(node);
        } else if (node instanceof Print) {
            System.out.print(node);
        } else if (node instanceof PrintFancy) {
            System.out.print(node);
        } else if (node instanceof Block) {
            System.out.print((Block) node +  ":");
            dump(((Block) node).first);
            System.out.print(',');
            dump(((Block) node).second);
        } else {
            System.err.println("ERR: no match for node.");
            System.exit(42);
        }
    }

    public void exec(Node node) {
        if (node instanceof Comment) {
        } else if (node instanceof Print) {
            System.out.println(((Print) node).message);
        } else if (node instanceof PrintFancy) {
            System.out.println(((PrintFancy) node).message);
        } else if (node instanceof Block) {
            exec(((Block) node).first);
            exec(((Block) node).second);
        } else {
            System.err.println("ERR: no match for node.");
            System.exit(42);
        }
    }

    public int size(Node node) {
        if (node instanceof Comment) {
            return 0;
        } else if (node instanceof Print) {
            return ((Print) node).message.length();
        } else if (node instanceof PrintFancy) {
            return ((PrintFancy) node).message.length();
        } else if (node instanceof Block) {
            return size(((Block) node).first) + size(((Block) node).second);
        } else {
            System.err.println("ERR: no match for node.");
            System.exit(42);
            return 0;   // warning
        }
    }

    public Node compile(Node node) {
        if (node instanceof Comment) {
            return node;
        } else if (node instanceof Print && !(node instanceof PrintFancy)) {
            return node;
        } else if (node instanceof PrintFancy) {
            return new Block(new Print("**"), new Block(new Print(((PrintFancy) node).message), new Print("**")));
        } else if (node instanceof Block) {
            Block block = (Block) node;
            boolean fc = block.first instanceof Comment;
            boolean sc = block.second instanceof Comment;
            if (fc && sc) return new Comment("Cannot compile this.");
            if (fc) return compile(block.second);
            if (sc) return compile(block.first);
            return new Block(compile(block.first), compile(block.second));
        } else {
            System.err.println("ERR: no match for node.");
            System.exit(42);
            return null;   // warning
        }
    }

}
