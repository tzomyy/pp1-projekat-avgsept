// generated with ast extension for cup
// version 0.8
// 16/7/2023 19:44:44


package rs.ac.bg.etf.pp1.ast;

public class FARAssign implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public FARAssign () {
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FARAssign(\n");

        buffer.append(tab);
        buffer.append(") [FARAssign]");
        return buffer.toString();
    }
}