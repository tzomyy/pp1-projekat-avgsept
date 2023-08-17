package rs.ac.bg.etf.pp1;

import java.util.*;

import org.apache.log4j.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor{
	
	Logger log = Logger.getLogger(getClass());
	boolean errorDetected = false;
	boolean arrayType = false;
	boolean hasReturn = false;
	
	int nvars = 0;
	int methFormParams = 0;
	int methActParams = 0;
	int depthWhile = 0;

	Obj currMethod = null;
	Struct currType = null;
	Struct boolType = new Struct(Struct.Bool);
	
	HashMap<String, List<Obj>> methFuncParam = new HashMap<>();
	List<Struct> actParams = new ArrayList<>();
	List<Obj> formParams = new ArrayList<>();
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed() {
		return !errorDetected;
	}
	
	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
		
		formParams = new ArrayList<>();
		formParams.add(new Obj(Obj.Var, "", Tab.charType));
		methFuncParam.put("ord", formParams);
		
		formParams = new ArrayList<>();
		formParams.add(new Obj(Obj.Var, "", Tab.intType));
		methFuncParam.put("chr", formParams);
		
		Struct arrayType = new Struct(Struct.Array);
		arrayType.setElementType(Tab.noType);
		formParams = new ArrayList<>();
		formParams.add(new Obj(Obj.Var, "", Tab.intType));
		methFuncParam.put("len", formParams);
		
		formParams = new ArrayList<>();
	}
	
	// otvaranje scope za program

	public void visit(ProgName progName) {

		// ubaci se objektni cvor u tabelu simbola i otvori se opseg
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) {

		if (Tab.find("main") == Tab.noObj) {
			report_error("Nije definisana main funkcija!", null);
		} else {
			report_info("Definisana je main funkcija!", null);
		}

		// uvezuju se simboli sa ospegom iznad i zatvara se opseg
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());

		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola!", null);
			type.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
		currType = type.struct;
	}
	
	public void visit(BoolConstDecl boolConstDecl) {

		if (Tab.find(boolConstDecl.getConstName()) != Tab.noObj) {
			report_error("Greska: Ime " + boolConstDecl.getConstName() + " vec postoji u tabeli simbola!",
					boolConstDecl);
			return;
		} else {
			if (!boolType.equals(currType)) {
				report_error("Greska: Tip konstante " + boolConstDecl.getConstName()
						+ " nije kompatibilan sa vrednoscu konstante!", boolConstDecl);
				return;
			} else {
				report_info("Definisana bool konstanta " + boolConstDecl.getConstName(), boolConstDecl);
				int boolValue;
				if (boolConstDecl.getBooleanConst() == true) {
					boolValue = 1;
				} else {
					boolValue = 0;
				}
				Obj boolNode = Tab.insert(Obj.Con, boolConstDecl.getConstName(), boolType);
				boolNode.setAdr(boolValue);
			}
		}
		boolConstDecl.struct = boolType;
	}
	
	public void visit(IntegerConstDecl integerConstDecl) {

		if (Tab.find(integerConstDecl.getConstName()) != Tab.noObj) {
			report_error("Greska: Ime " + integerConstDecl.getConstName() + " vec postoji u tabeli simbola!",
					integerConstDecl);
			return;
		} else {
			if (!Tab.intType.equals(currType)) {
				report_error("Greska: Tip konstante " + integerConstDecl.getConstName()
						+ " nije kompatibilan sa vrednoscu konstante!", integerConstDecl);
				return;
			} else {
				report_info("Definisana int kontanta " + integerConstDecl.getConstName(), integerConstDecl);

				Obj intNode = Tab.insert(Obj.Con, integerConstDecl.getConstName(), Tab.intType);
				intNode.setAdr(integerConstDecl.getNumberConst());
			}
		}
		integerConstDecl.struct = Tab.intType;
	}
	
	public void visit(CharConstDecl charConstDecl) {

		if (Tab.find(charConstDecl.getConstName()) != Tab.noObj) {
			report_error("Greska: Ime " + charConstDecl.getConstName() + " vec postoji u tabeli simbola!",
					charConstDecl);
			return;
		} else {
			if (!Tab.charType.equals(currType)) {
				report_error("Greska: Tip konstante " + charConstDecl.getConstName()
						+ " nije kompatibilan sa vrednoscu konstante!", charConstDecl);
				return;
			} else {
				report_info("Definisana char konstanta " + charConstDecl.getConstName() , charConstDecl);

				Obj intNode = Tab.insert(Obj.Con, charConstDecl.getConstName(), Tab.charType);
				intNode.setAdr(charConstDecl.getCharConst());
			}
		}
		charConstDecl.struct = Tab.charType;
	}
	
	public void visit(ArrayBrackets brackets) {
		this.arrayType = true;
	}
	
	public void visit(EndOfVarDeclar varDecl) {
		// treba proveriti da li se takav simbol nalazi u tabeli simbola, tj. da li se
		// nalazi u tom opsegu vazenja
		// ako ne postoji potrebno je dodati ga

		if ((Tab.find(varDecl.getVarName())) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(varDecl.getVarName()) != null) {
				report_error("Greska: Ime " + varDecl.getVarName() + " vec postoji u tabeli simbola i u tom opsegu!",
						varDecl);
			} else {
				if (this.arrayType) {
					Struct arrayType = new Struct(Struct.Array, currType);
					Tab.insert(Obj.Var, varDecl.getVarName(), arrayType);

				} else {
					Tab.insert(Obj.Var, varDecl.getVarName(), currType);

				}
			}
		} else {
			if (this.arrayType) {
				Struct arrayType = new Struct(Struct.Array, currType);
				Tab.insert(Obj.Var, varDecl.getVarName(), arrayType);
			} else {
				Tab.insert(Obj.Var, varDecl.getVarName(), currType);
			}
		}


		if (this.arrayType == true) {
			this.arrayType = false;
		}
		
		if (currMethod == null || currMethod.getName() == "main") this.nvars++;
		report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
		
	}
	
	public void visit(MoreVarDecls varDecl) {
		// treba proveriti da li se takav simbol nalazi u tabeli simbola, tj. da li se
		// nalazi u tom opsegu vazenja
		// ako ne postoji potrebno je dodati ga

		if ((Tab.find(varDecl.getVarName())) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(varDecl.getVarName()) != null) {
				report_error("Greska: Ime " + varDecl.getVarName() + " vec postoji u tabeli simbola i u tom opsegu!",
						varDecl);
			} else {
				if (this.arrayType) {
					Struct arrayType = new Struct(Struct.Array, currType);
					Tab.insert(Obj.Var, varDecl.getVarName(), arrayType);

				} else {
					Tab.insert(Obj.Var, varDecl.getVarName(), currType);

				}
			}
		} else {
			if (this.arrayType) {
				Struct arrayType = new Struct(Struct.Array, currType);
				Tab.insert(Obj.Var, varDecl.getVarName(), arrayType);
			} else {
				Tab.insert(Obj.Var, varDecl.getVarName(), currType);
			}
		}


		if (this.arrayType == true) {
			this.arrayType = false;
		}
		
		if (currMethod == null || currMethod.getName() == "main") this.nvars++;
		report_info("Deklarisana promenljiva " + varDecl.getVarName(), varDecl);
		
	}
	
	public void visit(TypeMethod methDecl) {
		// proveriti da li se nalazi u tabeli simbola i da li se nalazi u tom opsegu
		// ako ne dodati ga
		// nakon dodavanja otvoriti opseg
		
		if (methDecl.getMethodName().equals("main")) {
			report_error("Metoda main treba da bude deklarisana kao void!", null);
		}

		if (Tab.find(methDecl.getMethodName()) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(methDecl.getMethodName()) != null) {
				report_error("Greska: Simbol " + methDecl.getMethodName() + " je vec definisan u tabeli simbola",
						methDecl);
				return;
			} else {
				currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
			}
		} else {
			currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
		}
		
		methDecl.obj = currMethod;
		methDecl.getType().struct = currType;
		report_info("Definisana funkcija " + methDecl.getMethodName(), methDecl);
		Tab.openScope();
	}

	public void visit(VoidMethod methDecl) {
		currType = Tab.noType;
		if (Tab.find(methDecl.getMethodName()) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(methDecl.getMethodName()) != null) {
				report_error("Greska: Simbol " + methDecl.getMethodName() + " je vec definisan u tabeli simbola",
						methDecl);
				return;
			} else {
				currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
			}
		} else {
			currMethod = Tab.insert(Obj.Meth, methDecl.getMethodName(), currType);
		}

		methDecl.obj = currMethod;
		report_info("Definisana funkcija " + methDecl.getMethodName(), methDecl);
		Tab.openScope();
	}
	
	public void visit(ReturnNoExpr retExpr) {
		if (this.currMethod.getType() != Tab.noType) {
			report_error("Funkcija ima povratni tip!" , retExpr);
		}
	}

	public void visit(ReturnExpr retExpr) {
		if (this.currMethod == null) {
			report_error("Return naredba se nalazi van funkcije!", retExpr);
		}
		
		if (this.currMethod.getType() != retExpr.getExpr().struct) {
			report_error("Povratni izraz se ne poklapa sa tipom funkcije!" , retExpr);
		}

		this.hasReturn = true;
	}

	public void visit(MethDeclarations methDecl) {		
		
//		if (!this.hasReturn && (currMethod.getType() != Tab.noType)) {
//			report_error("Metoda " + this.currMethod.getName() + " nema povratnu vrednost u telu svoje funkcije",
//					methDecl);
//		}
		
		if (currMethod.getName().equals("main") && formParams.size() > 0) {
			report_error("Main metoda ne sme da ima parametre!",
					methDecl);
		}
		
		methDecl.getMethodDecl().obj = new Obj(Obj.Meth, currMethod.getName(), currMethod.getType());
		
		currMethod.setLevel(methFormParams);
		methFuncParam.put(currMethod.getName(), formParams);
		Tab.chainLocalSymbols(currMethod);
		
		Tab.closeScope();
		this.formParams = new ArrayList<>();
		this.methFormParams = 0;
		this.currMethod = null;
		this.hasReturn = false;
	}
	
	public void visit(SingleFormParam params) {
		
		if (Tab.find(params.getFormName()) != Tab.noObj) {
			if (Tab.currentScope.findSymbol(params.getFormName()) != null) {
				report_error("Parametar s imenom "+ params.getFormName() + " je vec naveden!", params);
				return;
			}			
		}
		
		if (this.arrayType) {
			Struct arrayType = new Struct(Struct.Array, currType);
			Tab.insert(Obj.Var, params.getFormName() , arrayType);			
		} else {
			Tab.insert(Obj.Var, params.getFormName(), this.currType);
		}
		
		this.methFormParams++;
		this.formParams.add(Tab.find(params.getFormName()));
	}
	
	
	public void visit(DesignFunction function) {
		
//		report_info(function.getDesignator().obj.getKind() + "", function);
		
		if (function.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Greska na liniji " + function.getLine() +
					": Promenljiva " + function.getDesignator().obj.getName() + " nije funkcija", function);
			return;
		}
		
		Collection<Obj> funcParam = methFuncParam.get(function.getDesignator().obj.getName());
				
		
		if (funcParam.size() != this.actParams.size()) {
			report_error("Greska na liniji " + function.getLine() 
			+ ": Broj argumenata funkcije " + "bi trebalo da bude " + funcParam.size(), function);
			return;
		}
		int i = 0;
		for (Obj obj: funcParam) {
			if (!obj.getType().compatibleWith(this.actParams.get(i))) {
				this.actParams = new ArrayList<>();
				report_error("Greska na liniji " + function.getLine() + 
						": Tipovi na " + (i+1) + ". mestu u pozivu funkcije " 
				+ function.getDesignator().obj.getName() +" nisu kompatablni", function);
				return;
			}
			i++;
		}
		this.actParams = new ArrayList<>();
	}
	
	//actparams	
	
	public void visit(SingleActParam params) {		
		actParams.add(params.getExpr().struct);
	}
	
	public void visit(ActParams params) {		
		actParams.add(params.getExpr().struct);
	}
	
	//condition
	
	boolean flagRelop = false;
	Expr secondRelopExpr = null;

	public void visit(MoreConds condition) {
		flagRelop = true;
		secondRelopExpr = condition.getExpr();

	}

	public void visit(CondFact condition) {

		// ako ima samo jedan uslov bez operacija
		if (!flagRelop) {
			if (condition.getExpr().struct != boolType) {
				report_error("Greska na liniji " + condition.getLine() + ": Uslov nije tipa boolean!", null);
				return;
			} else {
				condition.struct = condition.getExpr().struct;
				return;
			}
		}

		if (!secondRelopExpr.struct.compatibleWith(condition.getExpr().struct)) {
			report_error("Greska na liniji " + condition.getLine() + ": Tipovi u uslovu nisu kompatabilni!", null);
			return;
		}

		if (condition.getExpr().struct.getKind() == Struct.Array) {
			if (!(currOperator == Operator.IS_EQUAL || currOperator == Operator.NOT_EQUAL)) {
				report_error("Greska na liniji " + condition.getLine() + ": Nizovi mogu korisititi != ili  == od relacionih operatora!", null);
			}
		}

		flagRelop = false;
	}
	
	
	// statement
	
	
	public void visit(WhileStart whileStart) {
		this.depthWhile++;
	}

	public void visit(WhileEnd whileEnd) {
		this.depthWhile--;
	}
		

	public void visit(BreakStmt breakStmt) {
		if (this.depthWhile == 0) {
			report_error("Break se nalazi van while petlje! ", breakStmt);
		}
	}
	
	public void visit(ContinueStmt breakStmt) {
		if (this.depthWhile == 0) {
			report_error("Continue se nalazi van while petlje! ", breakStmt);
		}
	}
	
	public void visit(ReadStmt readStmt) {
		if (readStmt.getDesignator().obj.getKind() != Obj.Elem &&
				readStmt.getDesignator().obj.getKind() != Obj.Var) {
			report_error("Greska na liniji " + readStmt.getLine() + 
					": U read statement-u designator mora biti promenljiva ili element niza!", null);
			return;
		}
		if (readStmt.getDesignator().obj.getType().getKind() != Tab.intType.getKind() 
				&& readStmt.getDesignator().obj.getType().getKind() != Tab.charType.getKind()
				&& readStmt.getDesignator().obj.getType().getKind() != boolType.getKind()) {
			report_error("Greska na liniji " + readStmt.getLine() + 
					": U read statement-u designator mora biti tipa int, char ili bool!", readStmt);
		}
	}
	
	public void visit(FindAnyStmt findAnyStmt) {
		
		if (findAnyStmt.getDesignator1().obj.getType().getKind() != Struct.Array) {
			report_error("Greska na liniji " + findAnyStmt.getLine() +
					": Naredba findAnyStmt mora biti pozvana za promenljivu koja je tipa niz!", null);
			return;
		}
		
		if (findAnyStmt.getDesignator().obj.getKind() != Obj.Var) {
			report_error("Greska na liniji " + findAnyStmt.getLine() +
					": Identifikator mora biti promenljiva!" , null);
			return;
		}
		
		if (findAnyStmt.getDesignator().obj.getType().getKind() != Struct.Bool ) {
			report_error("Greska na liniji " + findAnyStmt.getLine() +
					": Promenljiva kojoj se dodeljuje vrednost naredbe findAnyStmt mora biti tipa bool!" +
					findAnyStmt.getDesignator().obj.getKind(), null);
			return;
		}
	}
	
	public void visit(FindAndReplaceStmt stmt) {
		
		if (stmt.getDesignatorFAR().getDesignator().obj.getType().getKind() != Struct.Array) {
			report_error("Greska na liniji " + stmt.getLine() +
					": Promenljiva kojoj se dodeljuje naredba findAndReplace mora biti tipa niz!", null);
			return;
		}
		
		if (stmt.getDesignatorFAR().getDesignator1().obj.getType().getKind() != Struct.Array) {
			report_error("Greska na liniji " + stmt.getLine() +
					": Naredba findAndReplace mora biti pozvana za promenljivu koja je tipa niz!", null);
			return;
		}
		
		Obj ident = Tab.find(stmt.getFARIdent().getIdent());
		if (ident == Tab.noObj || ident.getKind() != Obj.Var) {
			report_error("Greska na liniji " + stmt.getLine() +
					": Promenljiva nije definisana u tabeli simbola ili nije promenljiva!", null);
			return;
		}
		
		if (ident.getKind() != stmt.getDesignatorFAR().getDesignator().obj.getType().getElemType().getKind()) {
			report_error("Greska na liniji " + stmt.getLine() +
					": Elementi niza nisu istog tipa kao promenljiva za iteriranje!", null);
			return;
		}
		
	}
	
	public void visit(FARIdent ident) {
		ident.obj = Tab.find(ident.getIdent());
	}
	
	
	
	public void visit(ForeachStmt foreachStmt) {
		this.depthWhile--;
		
		if ( foreachStmt.getForeachDesignator().getDesignator().obj.getType().getKind() != Struct.Array) {
			report_error("Greska na liniji " + foreachStmt.getLine() +
					": Naredba foreach mora biti pozvana za promenljivu koja je tipa niz!", foreachStmt);
			return;
		}
		
		Obj ident = Tab.find(foreachStmt.getForeachIdent().getIdent());
		if (ident == Tab.noObj || ident.getKind() != Obj.Var) {
			report_error("Greska na liniji " + foreachStmt.getLine() +
					": Promenljiva nije definisana u tabeli simbola ili nije promenljiva!", foreachStmt);
			return;
		}
		
		if (ident.getKind() != foreachStmt.getForeachDesignator().getDesignator().obj.getType().getElemType().getKind()) {
			report_error("Greska na liniji " + foreachStmt.getLine() +
					": Elementi niza nisu istog tipa kao promenljiva za iteriranje!", foreachStmt);
			return;
		}
	}
	
	public void visit (ForeachIdent ident) {
		ident.obj = Tab.find(ident.getIdent());
	}
	
	public void visit(ForeachDesignator designator) {
		this.depthWhile++;
	}
	
	
	
	public void visit(DesignError designator) {
		report_error("Greska na liniji " + designator.getLine() +
				": Ovaj izraz se ne poklapa sa napisanom gramatikom!", null);
		return;
	}
	
	
	public void visit(DesignInc designator) {
		
		if (designator.getDesignator().obj.getKind() != Obj.Elem &&
				designator.getDesignator().obj.getKind() != Obj.Var) {
			report_error("Greska na liniji " + designator.getLine() + 
					": U inkrement statement-u designator mora biti promenljiva ili element niza!", null);
			return;
		}
				
		if (designator.getDesignator().obj.getType().getKind() != Tab.intType.getKind()) {
			report_error("Greska na liniji " + designator.getLine() + 
					": U inkrement statement-u designator mora biti tipa int, char ili bool!", null);
		}
		
		
	}
	
	public void visit(DesignDec designator) {
		
		if (designator.getDesignator().obj.getKind() != Obj.Elem &&
				designator.getDesignator().obj.getKind() != Obj.Var) {
			report_error("Greska na liniji " + designator.getLine() + 
					": U dekrement statement-u designator mora biti promenljiva ili element niza!", null);
			return;
		}
		
		if (designator.getDesignator().obj.getType().getKind() != Tab.intType.getKind()) {
			report_error("Greska na liniji " + designator.getLine() + 
					": U dekrement statement-u designator mora biti tipa int, char ili bool!", null);
		}
	}
	
	public void visit(DesignAssign designator) {
		if (designator.getDesignator().obj.getKind() != Obj.Elem &&
				designator.getDesignator().obj.getKind() != Obj.Var) {
			report_error("Greska na liniji " + designator.getLine() + 
					"U assign statement-u designator mora biti promenljiva ili element niza!", null);
		}
		if (!designator.getDesignator().obj.getType().compatibleWith(designator.getExpr().struct)) {
			report_error("Greska na liniji " + designator.getLine() +
					": Tip neterminala Expr" 
					+" mora biti kompatibilan sa tipom neterminala "
					+ designator.getDesignator().obj.getName(), null);
		}
	}
	
	// expr
	
	public void visit(PrintExpr printExpr) {
		
		if (currType != Tab.intType && currType != Tab.charType && currType != boolType) {
			report_error("Greska na liniji " + printExpr.getLine() + 
					": Povratna vrednost izraza u print naredbi nije odgovarajuceg tipa! " , null);
		}
	}
	
	public void visit(SingleExpr expr) {
		currType = expr.struct = expr.getTerm().struct;		
	}

	public void visit(NegativeExpr expr) {
		if (expr.getTerm().struct != Tab.intType) {
			currType = expr.struct = Tab.noType;
			report_error("Greska na liniji " + expr.getLine() + 
					": Negativni izraz treba da bude tipa int", null);
		} else {
			currType = expr.struct = expr.getTerm().struct;
		}

	}

	public void visit(MultipleExpr expr) {

		if (expr.getExpr().struct != Tab.intType || expr.getTerm().struct != Tab.intType) {
			report_error("Greska na liniji " + expr.getLine() + 
					": Izrazi operanada treba da budu tipa int!", null);
		}
		if (expr.getExpr().struct.compatibleWith(expr.getTerm().struct)) {
			expr.struct = expr.getExpr().struct;
		} else {
			expr.struct = Tab.noType;
			report_error("Greska na liniji " + expr.getLine() + 
					": Tipovi operanada treba da budu kompatabilni!", null);
		}

	}
	
	// term
	
	public void visit(SingleTerm term) {
		term.struct = term.getFactor().struct;
	}

	public void visit(MultipleTerms term) {

		if (term.getTerm().struct != Tab.intType || term.getFactor().struct != Tab.intType) {
			report_error("Greska na liniji " + term.getLine() + 
					": Tip operanada treba da bude int! ", null);
		}
		term.struct = term.getTerm().struct;
	}

	// faktor
	
	public void visit(FactNum factor) {
		factor.struct = Tab.intType;
	}
	

	public void visit(FactChar factor) {
		factor.struct = Tab.charType;
	}

	public void visit(FactBoolean factor) {
		factor.struct = boolType;
	}

	public void visit(FactVar factor) {
		factor.struct = factor.getDesignator().obj.getType();
	}
	
	public void visit(FactFunc factor) {
		
		if (factor.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Greska na liniji " + factor.getLine() + 
					": Promenljiva " + factor.getDesignator().obj.getName() + " nije funkcija", factor);
			this.actParams = new ArrayList<>();
			return;
		} 
		
		factor.struct = factor.getDesignator().obj.getType();
		
		List<Obj> funcParam = this.methFuncParam.get(factor.getDesignator().obj.getName());
		if (funcParam.size() != this.actParams.size()) {
			report_error("Broj argumenata funkcije bi trebalo da bude " + funcParam.size() + this.actParams.size(), factor);
			return;
		}
		int i = 0;
		for (Obj obj: funcParam) {
			if (!obj.getType().compatibleWith(this.actParams.get(i))) {
				this.actParams = new ArrayList<>();
				report_error("Tipovi na " + (i+1) + ". mestu u pozivu funkcije " 
				+ factor.getDesignator().obj.getName() +" nisu kompatablni", null);
				return;
			}
			i++;
		}
		this.actParams = new ArrayList<>();
	}
	
	public void visit(FactConstrArray factor) {
		if (factor.getExpr().struct != Tab.intType ) {
			report_error("Greska na liniji " + factor.getLine() +": Index niza mora biti int!", null);
		}
		Struct arrayType = new Struct(Struct.Array, factor.getType().struct);
		factor.struct = arrayType;
	}
	
	public void visit(FactExpr factor) {
		factor.struct = factor.getExpr().struct;
	}

	// designator
	
	public void visit(VarDesign designator) {
		Obj designNode = Tab.find(designator.getVar());

		if (designNode == Tab.noObj) {
			report_error("Varijabla " + designator.getVar() + " nije deklarisana! ", null);
		}
		designator.obj = designNode;
	}
	
	public void visit(DesignatorName designator) {
		Obj designNode = Tab.find(designator.getName());

		if (designNode == Tab.noObj) {
			report_error("Varijabla " + designator.getName() + " nije deklarisana! ", null);
		}
		designator.obj = designNode;
	}

	public void visit(ArrayDesign designator) {

		Obj designNode = designator.getDesignatorName().obj;

		if (designNode.getType().getKind() != Struct.Array) {
			report_error("Promenljiva " + designator.getDesignatorName().obj.getName() + " nije niz!", designator);
		}
		if (designator.getExpr().struct != Tab.intType) {
			report_error("Greska: Izraz bi trebalo da bude tipa int! ", designator);
		}
		
		designator.obj = new Obj(Obj.Elem, designNode.getName(), designNode.getType().getElemType() );
	}
	
	// operatori

	enum Operator {
		ADD, SUB, MUL, DIV, PROC, IS_EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL, EQUAL
	};

	Operator currOperator = null;

	public void visit(Assignop op) {
		currOperator = Operator.EQUAL;
	}

	public void visit(IsEqual op) {
		currOperator = Operator.IS_EQUAL;
	}

	public void visit(NotEqual op) {
		currOperator = Operator.NOT_EQUAL;
	}

	public void visit(Greater op) {
		currOperator = Operator.GREATER;
	}

	public void visit(GreaterEqual op) {
		currOperator = Operator.GREATER_EQUAL;
	}

	public void visit(Less op) {
		currOperator = Operator.LESS;
	}

	public void visit(LessEqual op) {
		currOperator = Operator.LESS_EQUAL;
	}

	public void visit(Plus op) {
		currOperator = Operator.ADD;
	}

	public void visit(Minus op) {
		currOperator = Operator.SUB;
	}

	public void visit(Asterisk op) {
		currOperator = Operator.MUL;
	}

	public void visit(Slash op) {
		currOperator = Operator.DIV;
	}

	public void visit(Percent op) {
		currOperator = Operator.PROC;
	}

	
}
