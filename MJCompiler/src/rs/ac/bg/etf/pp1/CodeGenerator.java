package rs.ac.bg.etf.pp1;

import java.util.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor{
	
	private int mainPc;
	private boolean flagReturn = false;
	
	Struct boolType = new Struct(Struct.Bool);
	
	public int getMainPc() {
		return this.mainPc;
	}
	

	
	public void visit(PrintStmt printStmt) {
		if (printStmt.getPrintExpr().getExpr().struct == Tab.intType) {
			Code.put(Code.print);
		}else if (printStmt.getPrintExpr().getExpr().struct == Tab.charType)  {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
	}	
	
	public void visit(StmtCnst stmtConst) {
		Code.loadConst(stmtConst.getNum());
	}
	
	public void visit(NoStmtCnst stmtConst) {
		if(((PrintStmt) stmtConst.getParent()).getPrintExpr().getExpr().struct == Tab.charType ){			
			Code.loadConst(1);
		}else{
			Code.loadConst(5);
		}
	}
	
	public void visit(FactNum number) {
		Obj con = Tab.insert(Obj.Con, "$", number.struct);
		con.setLevel(0); // globalni opseg
		con.setAdr(number.getN1()); // postavlja se vrednost konstante
		Code.load(con);
	}
	
//	public void visit (FactFunc factor) {
//		int offset = factor.getDesignator().obj.getAdr() - Code.pc;
//		Code.put(Code.call);
//		Code.put2(offset);
//	}
	
	
	public void visit(DesignFunction designFunc) {
		int offset = designFunc.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		
		if (designFunc.getDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
	}
	
	public void visit(TypeMethod typeMethod) {
		
		typeMethod.obj.setAdr(Code.pc);
		if (typeMethod.getMethodName().equals("main")) {
			this.mainPc = Code.pc;
		}
		formParam = 0;
		varParam = 0;
	}
	
	public void visit(VoidMethod voidMethod) {
		voidMethod.obj.setAdr(Code.pc);
		if (voidMethod.getMethodName().equals("main")) {
			this.mainPc = Code.pc;
		}	
		formParam = 0;
		varParam = 0;
	}
	
	public void visit(MethodDummy methodVarDecl) {
		
		// jedino mesto u koje svaka metoda ulazi na posle liste paramatera a pre tela metode
//		if (methodVarDecl.getParent() instanceof ListStmts) {
//			return;
//		}
		Code.put(Code.enter);
		Code.put(formParam);
		Code.put(varParam + formParam);
		formParam = 0;
		varParam = 0;
	}
	
	public void visit(MethDeclarations methodDecl) {
		
		if ( methodDecl.getMethodDecl().obj.getType() != Tab.noType && !flagReturn) {
			Code.put(Code.trap);
			Code.put(1);
			return;
		} 
		
		if (flagReturn) {
			flagReturn = false;
			return;
		}	
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignAssign assign) {
		// stavlja promenljivu na exprStack
		Code.store(assign.getDesignator().obj);
	}
	
	public void visit(DesignInc designator) {
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designator.getDesignator().obj);
	}
	
	public void visit(DesignDec designator) {
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designator.getDesignator().obj);
	}	
	
	public void visit(VarDesign designator){
		SyntaxNode parent = designator.getParent();
		
		if(!(parent instanceof DesignAssign) && !(parent instanceof FactFunc)){
			Code.load(designator.obj);
		}
	}
	
	public void visit (FactFunc factor) {
		
		if (factor.getDesignator().obj.getName().equals("chr") ||
				factor.getDesignator().obj.getName().equals("ord")) {
			return;
		}
		
		int offset = factor.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	public void visit(ReturnExpr returnExpr) {
		flagReturn = true;
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnNoExpr returnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	public void visit(MultipleExpr AddExpt) {
		if (AddExpt.getAddop() instanceof Plus) {
			Code.put(Code.add);
		} else if (AddExpt.getAddop() instanceof Minus) {
			Code.put(Code.sub);
		}
		
	}
	
	public void visit(ReadStmt readStmt) {		
		if (readStmt.getDesignator().obj.getType() == Tab.intType 
				|| readStmt.getDesignator().obj.getType() == boolType ) {
			Code.put(Code.read);
		} else {
			Code.put(Code.bread);
		}
		
		Code.store(readStmt.getDesignator().obj);
	}
	
	public void visit(FactChar factChar) {
		Code.loadConst(((int) factChar.getC1()));
	}
	
	public void visit(FactBoolean factBoolean) {
		if (factBoolean.getB1() == true) {
			Code.loadConst(1);
		} else {
			Code.loadConst(0);
		}
	}
	
	public void visit(FactConstrArray factArray) {
		Code.put(Code.newarray);
		if (factArray.struct.getElemType() == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
	
	public void visit(DesignatorName designator) {
		Code.load(designator.obj);
	}
	
	public void visit(ArrayDesign arr) {
		if (arr.getParent() instanceof DesignAssign || arr.getParent() instanceof ReadStmt) {
			return;
		}
		if (arr.getDesignatorName().obj.getType().getElemType() == Tab.charType) {
			Code.put(Code.baload);
		} else {
			Code.put(Code.aload);
		}
	}
	
	public void visit(NegativeExpr expr) {
		Code.put(Code.neg);
	}
	
	public void visit(MultipleTerms term) {
		if (term.getMulop() instanceof Asterisk) {
			Code.put(Code.mul);
		} else if (term.getMulop() instanceof Slash) {
			Code.put(Code.div);
		} else if (term.getMulop() instanceof Percent) {
			Code.put(Code.rem);
		}
	}
	
	// ifelse
	
	Stack<List<Integer>> stackOfAnds = new Stack<>();
	Stack<List<Integer>> stackOfOrs = new Stack<>();
 	
	public void visit(OrDummy afterOr) {
		stackOfOrs.peek().add(Code.pc + 1);
    	Code.putJump(0);
    	
    	List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	stackOfAnds.push(new ArrayList<Integer>());
	}
	
	public void visit(AfterIFConditionDummy afterIf) {
		List<Integer> orList = stackOfOrs.pop();
		for (int i = 0; i < orList.size(); i++) {
    		int onecond = orList.get(i);
    		Code.fixup(onecond);
    	}
		stackOfOrs.push(new ArrayList<Integer>());
	}
	
	public void visit(MoreConds conds) {
		stackOfAnds.peek().add(Code.pc + 1);		
		
		Relop rel = conds.getRelop();
		int code = 0;
		if (rel instanceof IsEqual) code = Code.eq;
		if (rel instanceof NotEqual) code = Code.ne;
		if (rel instanceof Greater) code = Code.gt;
		if (rel instanceof GreaterEqual) code = Code.ge;
		if (rel instanceof Less) code = Code.lt;
		if (rel instanceof LessEqual) code = Code.le;
		Code.putFalseJump(code, 0);
	}
	
	public void visit(EndOfCond singleTermCond) {
		Code.loadConst(1);
		stackOfAnds.peek().add(Code.pc + 1);
    	Code.putFalseJump(Code.eq, 0);
	}
	
	Stack<List<Integer>> stackOfElses = new Stack<>();
	
	public void visit(DummyAfterElse elsedummy) {
		stackOfElses.peek().add(Code.pc + 1);
    	Code.putJump(0);
    	
    	List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	stackOfAnds.push(new ArrayList<Integer>());
	}
	
	public void visit(DummyBeforeIf ifdummy) {
		List<Integer> andlist = new ArrayList<>();
		stackOfAnds.add(andlist);
		List<Integer> orlist = new ArrayList<>();
		stackOfOrs.add(orlist);
		List<Integer> elselist = new ArrayList<>();
		stackOfElses.add(elselist);
	}
	
	public void visit(ElseStmt ifstatement) {
		List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	List<Integer> elseList = stackOfElses.pop();
    	for (int i = 0; i < elseList.size(); i++) {
    		int onecond = elseList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	stackOfOrs.pop();
	}
	
	public void visit(NoElseStmt ifstatement) {
		List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	List<Integer> elseList = stackOfElses.pop();
    	for (int i = 0; i < elseList.size(); i++) {
    		int onecond = elseList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	stackOfOrs.pop();
	}
	
	Stack<List<Integer>> stackOfBreaks = new Stack<>();
	Stack<Integer> stackOfLoops = new Stack<>();
	
	public void visit(WhileStart whilestart) {
		List<Integer> andlist = new ArrayList<>();
		stackOfAnds.add(andlist);
		List<Integer> orlist = new ArrayList<>();
		stackOfOrs.add(orlist);
		List<Integer> breaklist = new ArrayList<>();
		stackOfBreaks.add(breaklist);
		stackOfLoops.push(Code.pc);
	}
	
	public void visit(WhileStmt whilestatement) {
		int adr = stackOfLoops.pop();
		Code.putJump(adr);
		
		List<Integer> andList = stackOfAnds.pop();
    	for (int i = 0; i < andList.size(); i++) {
    		int onecond = andList.get(i);
    		Code.fixup(onecond);
    	}
    	
    	List<Integer> breakList = stackOfBreaks.pop();
    	for (int i = 0; i < breakList.size(); i++) {
    		int onecond = breakList.get(i);
    		Code.fixup(onecond);
    	}
		
    	stackOfOrs.pop();
	}
	
	public void visit(ContinueStmt cont) {
		int adr = stackOfLoops.peek();
    	Code.putJump(adr);
    }
	

    public void visit(BreakStmt breakstatement) {
    	stackOfBreaks.peek().add(Code.pc + 1);
    	Code.putJump(0);
    }
    
    
    
    // ovo ne znam da l radi
    public void visit(ForeachDesignator designator) {
    	List<Integer> breaklist = new ArrayList<>();
		stackOfBreaks.add(breaklist);
    }
    
    // ovo ne znam da l radi
    public void visit(ForeachStmt stmt) {
    	List<Integer> breakList = stackOfBreaks.pop();
    	for (int i = 0; i < breakList.size(); i++) {
    		int onecond = breakList.get(i);
    		Code.fixup(onecond);
    	}
    }
    
    int addrEqFA, addrNonFA;
    Stack<Integer> stackOfFindAnyNON = new Stack<>();
    Stack<Integer> stackOfFindAnyEQ = new Stack<>();
    
    public void visit(DummyFindAny dummy) {
    	Code.loadConst(-1);
    	int loop = Code.pc;
    	Code.loadConst(1); 
    	Code.put(Code.add); // adr1, adr2, expr, ind
    	Code.put(Code.dup); // adr1, adr2, expr, ind, ind
    	Code.put(Code.dup_x2); // adr1, adr2, ind, expr, ind , ind 
    	Obj arrayDesignator = ((FindAnyStmt)(dummy.getParent())).getDesignator1().obj;
    	Code.load(arrayDesignator); // adr1, adr2, ind, expr, ind, ind,  adr2
    	Code.put(Code.arraylength); // adr1, adr2, ind,  expr, ind, ind, n2
    	stackOfFindAnyNON.add(Code.pc + 1);
    	Code.putFalseJump(Code.lt, 0); // adr1, adr2, ind, expr, ind,
    	Code.load(arrayDesignator); // adr1, adr2, ind, expr, ind, adr2
    	Code.put(Code.dup_x1); // adr1, adr2, ind, expr, adr2, ind, adr2
    	Code.put(Code.pop); // adr1, adr2, ind, expr, adr2, ind
    	if (arrayDesignator.getType() == Tab.charType) {
    		Code.put(Code.baload);
    	} else {
    		Code.put(Code.aload);
    	} 
    	// adr1, adr2, ind, expr, val
    	Code.put(Code.dup_x1); // adr1, adr2, ind, val, expr, val
    	Code.put(Code.pop); // adr1, adr2, ind, val, expr
    	Code.put(Code.dup_x1); // adr1, adr2, ind, expr, val, expr
    	
    	stackOfFindAnyEQ.add(Code.pc + 1);
    	Code.putFalseJump(Code.ne, 0); // adr1, adr2, ind, expr
    	Code.put(Code.dup_x1); // adr1, adr2, expr, ind, expr
    	Code.put(Code.pop);
    	Code.putJump(loop);
    	
    }
    
    
    Stack<Integer> stackOfFindAnyEnd = new Stack<>();
    public void visit (DummyEq dummy) {
    	//  adr1, adr2, expr
    	
    	for (int x: stackOfFindAnyEQ) {
    		Code.fixup(x);
    	}
    	
    	Code.put(Code.pop);
    	Code.put(Code.pop);
    	Code.put(Code.pop);
    	
    	Obj booleanDesignator = ((FindAnyStmt)(dummy.getParent())).getDesignator().obj;
    	
    	
    	Code.loadConst(1);
    	Code.store(booleanDesignator);
     	
    	
    	
    	stackOfFindAnyEnd.add(Code.pc + 1);
    	Code.putJump(0);    	
    }
    
    public void visit (DummyNon dummy) {
    	//  adr1, adr2, expr
    	
    	for (int x: stackOfFindAnyNON) {
    		Code.fixup(x);
    	}
    	
    	Code.put(Code.pop);
    	Code.put(Code.pop);
    	Code.put(Code.pop);
    	Code.put(Code.pop);
    	
    	Obj booleanDesignator = ((FindAnyStmt)(dummy.getParent())).getDesignator().obj;
    	
    	
    	Code.loadConst(0);
    	Code.store(booleanDesignator);
    	
    	
    	
    	stackOfFindAnyEnd.add(Code.pc + 1);
    	Code.putJump(0);    	
    }
    
    public void visit(FindAnyEnd dummy) {
    	for (int x: stackOfFindAnyEnd) {
    		Code.fixup(x);
    	}
    	
    }
    
	
	int formParam = 0;
	int varParam = 0;
	
	public void visit(SingleFormParam singleFormPar) {
		formParam++;
	}
	
	public void visit(MoreVarDecls varDecl) {
		varParam++;
	}
	
	public void visit(EndOfVarDeclar varDecl) {
		varParam++;
	}

}
