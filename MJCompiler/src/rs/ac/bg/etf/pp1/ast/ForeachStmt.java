// generated with ast extension for cup
// version 0.8
// 16/7/2023 0:32:35


package rs.ac.bg.etf.pp1.ast;

public class ForeachStmt extends Statement {

    private ForeachDesignator ForeachDesignator;
    private String ident;
    private StatementList StatementList;

    public ForeachStmt (ForeachDesignator ForeachDesignator, String ident, StatementList StatementList) {
        this.ForeachDesignator=ForeachDesignator;
        if(ForeachDesignator!=null) ForeachDesignator.setParent(this);
        this.ident=ident;
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public ForeachDesignator getForeachDesignator() {
        return ForeachDesignator;
    }

    public void setForeachDesignator(ForeachDesignator ForeachDesignator) {
        this.ForeachDesignator=ForeachDesignator;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident=ident;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForeachDesignator!=null) ForeachDesignator.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForeachDesignator!=null) ForeachDesignator.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForeachDesignator!=null) ForeachDesignator.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
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

        buffer.append(" "+tab+ident);
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForeachStmt]");
        return buffer.toString();
    }
}
