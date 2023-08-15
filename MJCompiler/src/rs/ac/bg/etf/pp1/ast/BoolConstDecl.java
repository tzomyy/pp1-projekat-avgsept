// generated with ast extension for cup
// version 0.8
// 16/7/2023 0:32:35


package rs.ac.bg.etf.pp1.ast;

public class BoolConstDecl extends SingleConstDecl {

    private String constName;
    private Boolean booleanConst;

    public BoolConstDecl (String constName, Boolean booleanConst) {
        this.constName=constName;
        this.booleanConst=booleanConst;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Boolean getBooleanConst() {
        return booleanConst;
    }

    public void setBooleanConst(Boolean booleanConst) {
        this.booleanConst=booleanConst;
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
        buffer.append("BoolConstDecl(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        buffer.append(" "+tab+booleanConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConstDecl]");
        return buffer.toString();
    }
}
