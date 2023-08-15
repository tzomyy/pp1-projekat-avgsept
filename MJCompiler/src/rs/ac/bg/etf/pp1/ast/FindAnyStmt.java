// generated with ast extension for cup
// version 0.8
// 16/7/2023 0:32:35


package rs.ac.bg.etf.pp1.ast;

public class FindAnyStmt extends Statement {

    private Designator Designator;
    private Assignop Assignop;
    private Designator Designator1;
    private Expr Expr;
    private DummyFindAny DummyFindAny;
    private DummyEq DummyEq;
    private DummyNon DummyNon;
    private FindAnyEnd FindAnyEnd;

    public FindAnyStmt (Designator Designator, Assignop Assignop, Designator Designator1, Expr Expr, DummyFindAny DummyFindAny, DummyEq DummyEq, DummyNon DummyNon, FindAnyEnd FindAnyEnd) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.Designator1=Designator1;
        if(Designator1!=null) Designator1.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.DummyFindAny=DummyFindAny;
        if(DummyFindAny!=null) DummyFindAny.setParent(this);
        this.DummyEq=DummyEq;
        if(DummyEq!=null) DummyEq.setParent(this);
        this.DummyNon=DummyNon;
        if(DummyNon!=null) DummyNon.setParent(this);
        this.FindAnyEnd=FindAnyEnd;
        if(FindAnyEnd!=null) FindAnyEnd.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public Designator getDesignator1() {
        return Designator1;
    }

    public void setDesignator1(Designator Designator1) {
        this.Designator1=Designator1;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public DummyFindAny getDummyFindAny() {
        return DummyFindAny;
    }

    public void setDummyFindAny(DummyFindAny DummyFindAny) {
        this.DummyFindAny=DummyFindAny;
    }

    public DummyEq getDummyEq() {
        return DummyEq;
    }

    public void setDummyEq(DummyEq DummyEq) {
        this.DummyEq=DummyEq;
    }

    public DummyNon getDummyNon() {
        return DummyNon;
    }

    public void setDummyNon(DummyNon DummyNon) {
        this.DummyNon=DummyNon;
    }

    public FindAnyEnd getFindAnyEnd() {
        return FindAnyEnd;
    }

    public void setFindAnyEnd(FindAnyEnd FindAnyEnd) {
        this.FindAnyEnd=FindAnyEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(Designator1!=null) Designator1.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(DummyFindAny!=null) DummyFindAny.accept(visitor);
        if(DummyEq!=null) DummyEq.accept(visitor);
        if(DummyNon!=null) DummyNon.accept(visitor);
        if(FindAnyEnd!=null) FindAnyEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(Designator1!=null) Designator1.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(DummyFindAny!=null) DummyFindAny.traverseTopDown(visitor);
        if(DummyEq!=null) DummyEq.traverseTopDown(visitor);
        if(DummyNon!=null) DummyNon.traverseTopDown(visitor);
        if(FindAnyEnd!=null) FindAnyEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(Designator1!=null) Designator1.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(DummyFindAny!=null) DummyFindAny.traverseBottomUp(visitor);
        if(DummyEq!=null) DummyEq.traverseBottomUp(visitor);
        if(DummyNon!=null) DummyNon.traverseBottomUp(visitor);
        if(FindAnyEnd!=null) FindAnyEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FindAnyStmt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator1!=null)
            buffer.append(Designator1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyFindAny!=null)
            buffer.append(DummyFindAny.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyEq!=null)
            buffer.append(DummyEq.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DummyNon!=null)
            buffer.append(DummyNon.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FindAnyEnd!=null)
            buffer.append(FindAnyEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FindAnyStmt]");
        return buffer.toString();
    }
}
