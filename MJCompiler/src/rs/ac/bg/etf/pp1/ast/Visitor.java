// generated with ast extension for cup
// version 0.8
// 14/7/2023 12:2:43


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(MoreVarDecl MoreVarDecl);
    public void visit(Mulop Mulop);
    public void visit(MethodDecl MethodDecl);
    public void visit(Relop Relop);
    public void visit(StatementList StatementList);
    public void visit(ClassVarDecl ClassVarDecl);
    public void visit(Addop Addop);
    public void visit(ClassConstr ClassConstr);
    public void visit(StmtConst StmtConst);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(FactParam FactParam);
    public void visit(MethodFormPars MethodFormPars);
    public void visit(EndOfVarDecl EndOfVarDecl);
    public void visit(MultipleFormPar MultipleFormPar);
    public void visit(ElseStatement ElseStatement);
    public void visit(ClassConstrMethod ClassConstrMethod);
    public void visit(Brackets Brackets);
    public void visit(MethodVarDecl MethodVarDecl);
    public void visit(ClassExtends ClassExtends);
    public void visit(Expr Expr);
    public void visit(ClassMethod ClassMethod);
    public void visit(ActPars ActPars);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(MultipleConstDecl MultipleConstDecl);
    public void visit(MoreCondFacts MoreCondFacts);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(IfStatement IfStatement);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(Declaration Declaration);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(SingleConstDecl SingleConstDecl);
    public void visit(SingleFormPar SingleFormPar);
    public void visit(Type Type);
    public void visit(Percent Percent);
    public void visit(Slash Slash);
    public void visit(Asterisk Asterisk);
    public void visit(Minus Minus);
    public void visit(Plus Plus);
    public void visit(LessEqual LessEqual);
    public void visit(Less Less);
    public void visit(GreaterEqual GreaterEqual);
    public void visit(Greater Greater);
    public void visit(NotEqual NotEqual);
    public void visit(IsEqual IsEqual);
    public void visit(Label Label);
    public void visit(Assignop Assignop);
    public void visit(DesignatorName DesignatorName);
    public void visit(VarDesign VarDesign);
    public void visit(ArrayDesign ArrayDesign);
    public void visit(ClassDesign ClassDesign);
    public void visit(FactExpr FactExpr);
    public void visit(FactConstrObject FactConstrObject);
    public void visit(FactConstrArray FactConstrArray);
    public void visit(FactBoolean FactBoolean);
    public void visit(FactChar FactChar);
    public void visit(FactNum FactNum);
    public void visit(FactFunc FactFunc);
    public void visit(FactVar FactVar);
    public void visit(MultipleTerms MultipleTerms);
    public void visit(SingleTerm SingleTerm);
    public void visit(SingleExpr SingleExpr);
    public void visit(MultipleExpr MultipleExpr);
    public void visit(NegativeExpr NegativeExpr);
    public void visit(EndOfCond EndOfCond);
    public void visit(MoreConds MoreConds);
    public void visit(CondFact CondFact);
    public void visit(SingleCondTerm SingleCondTerm);
    public void visit(CondTerms CondTerms);
    public void visit(OrDummy OrDummy);
    public void visit(SingleCondition SingleCondition);
    public void visit(Conditions Conditions);
    public void visit(SingleActParam SingleActParam);
    public void visit(ActParams ActParams);
    public void visit(NoFactFuncParams NoFactFuncParams);
    public void visit(FactFuncParams FactFuncParams);
    public void visit(AssignDummy AssignDummy);
    public void visit(DesignError DesignError);
    public void visit(DesignDec DesignDec);
    public void visit(DesignInc DesignInc);
    public void visit(DesignFunction DesignFunction);
    public void visit(DesignAssign DesignAssign);
    public void visit(DummyAfterElse DummyAfterElse);
    public void visit(NoElseStmt NoElseStmt);
    public void visit(ElseStmt ElseStmt);
    public void visit(AfterIFConditionDummy AfterIFConditionDummy);
    public void visit(BeforeIFConditionDummy BeforeIFConditionDummy);
    public void visit(ErrorStmt ErrorStmt);
    public void visit(IfState IfState);
    public void visit(DummyBeforeIf DummyBeforeIf);
    public void visit(WhileEnd WhileEnd);
    public void visit(WhileStart WhileStart);
    public void visit(PrintExpr PrintExpr);
    public void visit(NoStmtCnst NoStmtCnst);
    public void visit(StmtCnst StmtCnst);
    public void visit(ListStmts ListStmts);
    public void visit(ForeachStmt ForeachStmt);
    public void visit(FindAndReplaceStmt FindAndReplaceStmt);
    public void visit(FindAnyStmt FindAnyStmt);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(WhileStmt WhileStmt);
    public void visit(IfStmt IfStmt);
    public void visit(DesignStmt DesignStmt);
    public void visit(NoStatement NoStatement);
    public void visit(Statements Statements);
    public void visit(ErrorFormComma ErrorFormComma);
    public void visit(EndOfFormPar EndOfFormPar);
    public void visit(MultipleFormPars MultipleFormPars);
    public void visit(SingleFormParam SingleFormParam);
    public void visit(FormPars FormPars);
    public void visit(EndOfMethodVarDecl EndOfMethodVarDecl);
    public void visit(MethodVarDecls MethodVarDecls);
    public void visit(ErrorMethodFormParams ErrorMethodFormParams);
    public void visit(NoMethodFormParams NoMethodFormParams);
    public void visit(MethodFormParams MethodFormParams);
    public void visit(VoidMethod VoidMethod);
    public void visit(TypeMethod TypeMethod);
    public void visit(VoidMethodDecl VoidMethodDecl);
    public void visit(TypeMethodDecl TypeMethodDecl);
    public void visit(NoMethDeclarations NoMethDeclarations);
    public void visit(MethDeclarations MethDeclarations);
    public void visit(ErrorClassExtend ErrorClassExtend);
    public void visit(NoClassExtend NoClassExtend);
    public void visit(ClassExtend ClassExtend);
    public void visit(EndOfClassVarDecl EndOfClassVarDecl);
    public void visit(MultipleClassVarDecl MultipleClassVarDecl);
    public void visit(EndOfClassMethod EndOfClassMethod);
    public void visit(MultipleClassMethod MultipleClassMethod);
    public void visit(NoClassConstrMethodCurly NoClassConstrMethodCurly);
    public void visit(ClassConstrMethodCurly ClassConstrMethodCurly);
    public void visit(ClassMethodCurly ClassMethodCurly);
    public void visit(ClassOnlyCurly ClassOnlyCurly);
    public void visit(ClassName ClassName);
    public void visit(ClassDecl ClassDecl);
    public void visit(NoBrackets NoBrackets);
    public void visit(ArrayBrackets ArrayBrackets);
    public void visit(ErrorEndOfVarDecl ErrorEndOfVarDecl);
    public void visit(EndOfVarDeclar EndOfVarDeclar);
    public void visit(ErrorMoreVarDecl ErrorMoreVarDecl);
    public void visit(MoreVarDecls MoreVarDecls);
    public void visit(EndOfVarDeclarations EndOfVarDeclarations);
    public void visit(MultipleVarDecl MultipleVarDecl);
    public void visit(VarDecls VarDecls);
    public void visit(EndOfDeclaration EndOfDeclaration);
    public void visit(MultipleConstDecls MultipleConstDecls);
    public void visit(CharConstDecl CharConstDecl);
    public void visit(IntegerConstDecl IntegerConstDecl);
    public void visit(BoolConstDecl BoolConstDecl);
    public void visit(ConstDecl ConstDecl);
    public void visit(NoDeclaration NoDeclaration);
    public void visit(ClassDeclaration ClassDeclaration);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}