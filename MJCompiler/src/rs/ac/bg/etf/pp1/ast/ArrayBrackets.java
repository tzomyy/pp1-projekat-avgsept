// generated with ast extension for cup
// version 0.8
// 16/7/2023 0:32:35


package rs.ac.bg.etf.pp1.ast;

public class ArrayBrackets extends Brackets {

    public ArrayBrackets () {
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
        buffer.append("ArrayBrackets(\n");

        buffer.append(tab);
        buffer.append(") [ArrayBrackets]");
        return buffer.toString();
    }
}
