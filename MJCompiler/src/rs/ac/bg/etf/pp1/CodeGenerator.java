package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor{
	
	private int mainPc;
	
	public int getMainPc() {
		return this.mainPc;
	}
	
	public void visit(PrintStmt printStmt) {
		if (printStmt.getPrintExpr().getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}else if (printStmt.getPrintExpr().getExpr().struct == Tab.charType)  {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(FactNum number) {
		Obj con = Tab.insert(Obj.Con, "$", number.struct);
		con.setLevel(0); // globalni opseg
		con.setAdr(number.getN1()); // postavlja se vrednost konstante
		Code.load(con);
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
	
	public void visit(MethodVarDecls methodVarDecl) {
		Code.put(Code.enter);
		Code.put(formParam);
		Code.put(varParam + formParam);
		formParam = 0;
		varParam = 0;
	}
	
	public void visit(EndOfMethodVarDecl methodVarDecl) {
//		Code.put(Code.enter);
//		Code.put(formParam);
//		Code.put(varParam + formParam);
//		formParam = 0;
//		varParam = 0;
	}
	
	public void visit(MethDeclarations methodDecl) {
				
		Code.put(Code.exit);
		Code.put(Code.return_);
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
