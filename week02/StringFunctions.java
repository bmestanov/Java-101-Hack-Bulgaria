package week02;

import java.util.HashMap;

public class StringFunctions {
	private HashMap<String,String> funcs;
	
	public StringFunctions(){
		funcs = new HashMap<>();
	}
	
	public void add(String func){
		String[] newFunc = func.split(" = ");
		funcs.put(newFunc[0], newFunc[1]);
	}
	
	public int evaluate(String fname, int arg) throws Exception{
		if(!funcs.containsKey(fname)){
			throw new Exception("Function not defined");
		}
		
		String[] parts = funcs.get(fname).split(" ");
		for(String part : parts){
			if(isFunction(part)){
				part = getNumberRepresentation(getFuncArg(part), funcs.get(getFuncName(part)));
			}
		}
		
		int result = 0;
		for(String part : parts){
			result += Integer.parseInt(part);
		}
		
		return result;
	}
	
	private String getNumberRepresentation(String arg, String fname) throws Exception {
		if(!funcs.containsKey(fname)){
			throw new Exception("Function not defined");
		}
		
		String[] parts = funcs.get(fname).split(" ");
		for(String part : parts){
			if(isFunction(part)){
				part = getNumberRepresentation(getFuncArg(part), funcs.get(getFuncName(part)));
			}
			
			else if(part.equals(fname.split(" ")[1])){
				part = arg;
			}
		}
		
		String result = "";
		for (String part : parts){
			result += part;
		}
		return result;
	}

	private String getFuncArg(String part) {
		int indexOfOpenBracket = part.indexOf("(");
		return part.substring(indexOfOpenBracket, indexOfOpenBracket+1);
	}

	private boolean isFunction(String element){
		return element.contains("(") && element.contains(")");
	}
	
	private String getFuncName(String func){
		return func.substring(0, func.indexOf("("));
	}
	
	private boolean isOperator(String op){
		return op.equals("+") || op.equals("-");
	}
	
	private Operation getOperation(String op){
		if(op.equals("+"))
			return Operation.PLUS;
		return Operation.MINUS;
	}
	
	enum Operation{
		PLUS, MINUS
	}
}
