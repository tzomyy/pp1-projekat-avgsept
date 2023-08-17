// generated with ast extension for cup
// version 0.8
// 16/7/2023 19:44:44


package rs.ac.bg.etf.pp1.ast;

public class FindAndReplaceStmt extends Statement {

    private DesignatorFAR DesignatorFAR;
    private Expr Expr;
    private FARIdent FARIdent;
    private Expr Expr1;
    private FARAssign FARAssign;
    private FAREnd FAREnd;

    public FindAndReplaceStmt (DesignatorFAR DesignatorFAR, Expr Expr, FARIdent FARIdent, Expr Expr1, FARAssign FARAssign, FAREnd FAREnd) {
        this.DesignatorFAR=DesignatorFAR;
        if(DesignatorFAR!=null) DesignatorFAR.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.FARIdent=FARIdent;
        if(FARIdent!=null) FARIdent.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
        this.FARAssign=FARAssign;
        if(FARAssign!=null) FARAssign.setParent(this);
        this.FAREnd=FAREnd;
        if(FAREnd!=null) FAREnd.setParent(this);
    }

    public DesignatorFAR getDesignatorFAR() {
        return DesignatorFAR;
    }

    public void setDesignatorFAR(DesignatorFAR DesignatorFAR) {
        this.DesignatorFAR=DesignatorFAR;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public FARIdent getFARIdent() {
        return FARIdent;
    }

    public void setFARIdent(FARIdent FARIdent) {
        this.FARIdent=FARIdent;
    }

    public Expr getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr Expr1) {
        this.Expr1=Expr1;
    }

    public FARAssign getFARAssign() {
        return FARAssign;
    }

    public void setFARAssign(FARAssign FARAssign) {
        this.FARAssign=FARAssign;
    }

    public FAREnd getFAREnd() {
        return FAREnd;
    }

    public void setFAREnd(FAREnd FAREnd) {
        this.FAREnd=FAREnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorFAR!=null) DesignatorFAR.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(FARIdent!=null) FARIdent.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
        if(FARAssign!=null) FARAssign.accept(visitor);
        if(FAREnd!=null) FAREnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorFAR!=null) DesignatorFAR.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(FARIdent!=null) FARIdent.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
        if(FARAssign!=null) FARAssign.traverseTopDown(visitor);
        if(FAREnd!=null) FAREnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorFAR!=null) DesignatorFAR.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(FARIdent!=null) FARIdent.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        if(FARAssign!=null) FARAssign.traverseBottomUp(visitor);
        if(FAREnd!=null) FAREnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FindAndReplaceStmt(\n");

        if(DesignatorFAR!=null)
            buffer.append(DesignatorFAR.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FARIdent!=null)
            buffer.append(FARIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FARAssign!=null)
            buffer.append(FARAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FAREnd!=null)
            buffer.append(FAREnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FindAndReplaceStmt]");
        return buffer.toString();
    }
}
