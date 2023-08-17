// generated with ast extension for cup
// version 0.8
// 16/7/2023 19:44:44


package rs.ac.bg.etf.pp1.ast;

public class ForeachStmt extends Statement {

    private ForeachDesignator ForeachDesignator;
    private ForeachIdent ForeachIdent;
    private StatementList StatementList;
    private ForeachLoop ForeachLoop;
    private ForeachEnd ForeachEnd;

    public ForeachStmt (ForeachDesignator ForeachDesignator, ForeachIdent ForeachIdent, StatementList StatementList, ForeachLoop ForeachLoop, ForeachEnd ForeachEnd) {
        this.ForeachDesignator=ForeachDesignator;
        if(ForeachDesignator!=null) ForeachDesignator.setParent(this);
        this.ForeachIdent=ForeachIdent;
        if(ForeachIdent!=null) ForeachIdent.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
        this.ForeachLoop=ForeachLoop;
        if(ForeachLoop!=null) ForeachLoop.setParent(this);
        this.ForeachEnd=ForeachEnd;
        if(ForeachEnd!=null) ForeachEnd.setParent(this);
    }

    public ForeachDesignator getForeachDesignator() {
        return ForeachDesignator;
    }

    public void setForeachDesignator(ForeachDesignator ForeachDesignator) {
        this.ForeachDesignator=ForeachDesignator;
    }

    public ForeachIdent getForeachIdent() {
        return ForeachIdent;
    }

    public void setForeachIdent(ForeachIdent ForeachIdent) {
        this.ForeachIdent=ForeachIdent;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public ForeachLoop getForeachLoop() {
        return ForeachLoop;
    }

    public void setForeachLoop(ForeachLoop ForeachLoop) {
        this.ForeachLoop=ForeachLoop;
    }

    public ForeachEnd getForeachEnd() {
        return ForeachEnd;
    }

    public void setForeachEnd(ForeachEnd ForeachEnd) {
        this.ForeachEnd=ForeachEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForeachDesignator!=null) ForeachDesignator.accept(visitor);
        if(ForeachIdent!=null) ForeachIdent.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
        if(ForeachLoop!=null) ForeachLoop.accept(visitor);
        if(ForeachEnd!=null) ForeachEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForeachDesignator!=null) ForeachDesignator.traverseTopDown(visitor);
        if(ForeachIdent!=null) ForeachIdent.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
        if(ForeachLoop!=null) ForeachLoop.traverseTopDown(visitor);
        if(ForeachEnd!=null) ForeachEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForeachDesignator!=null) ForeachDesignator.traverseBottomUp(visitor);
        if(ForeachIdent!=null) ForeachIdent.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        if(ForeachLoop!=null) ForeachLoop.traverseBottomUp(visitor);
        if(ForeachEnd!=null) ForeachEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForeachStmt(\n");

        if(ForeachDesignator!=null)
            buffer.append(ForeachDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachIdent!=null)
            buffer.append(ForeachIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachLoop!=null)
            buffer.append(ForeachLoop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachEnd!=null)
            buffer.append(ForeachEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForeachStmt]");
        return buffer.toString();
    }
}
