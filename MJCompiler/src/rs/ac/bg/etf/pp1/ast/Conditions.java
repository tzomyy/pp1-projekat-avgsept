// generated with ast extension for cup
// version 0.8
// 16/7/2023 0:32:35


package rs.ac.bg.etf.pp1.ast;

public class Conditions extends Condition {

    private CondTerm CondTerm;
    private OrDummy OrDummy;
    private CondTerm CondTerm1;

    public Conditions (CondTerm CondTerm, OrDummy OrDummy, CondTerm CondTerm1) {
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.OrDummy=OrDummy;
        if(OrDummy!=null) OrDummy.setParent(this);
        this.CondTerm1=CondTerm1;
        if(CondTerm1!=null) CondTerm1.setParent(this);
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public OrDummy getOrDummy() {
        return OrDummy;
    }

    public void setOrDummy(OrDummy OrDummy) {
        this.OrDummy=OrDummy;
    }

    public CondTerm getCondTerm1() {
        return CondTerm1;
    }

    public void setCondTerm1(CondTerm CondTerm1) {
        this.CondTerm1=CondTerm1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(OrDummy!=null) OrDummy.accept(visitor);
        if(CondTerm1!=null) CondTerm1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(OrDummy!=null) OrDummy.traverseTopDown(visitor);
        if(CondTerm1!=null) CondTerm1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(OrDummy!=null) OrDummy.traverseBottomUp(visitor);
        if(CondTerm1!=null) CondTerm1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Conditions(\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OrDummy!=null)
            buffer.append(OrDummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm1!=null)
            buffer.append(CondTerm1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Conditions]");
        return buffer.toString();
    }
}
