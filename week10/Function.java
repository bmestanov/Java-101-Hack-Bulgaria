package week10;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mestanov on 20.12.16.
 */
public class Function {
    private String fName;
    private Type arg, resultType;

    public Function(String fn){
        String[] p = fn.split("::|->");
        if(p.length != 3){
            throw new IllegalArgumentException("Function described in wrong format!");
        }
        fName = p[0].trim();
        arg = Type.make(p[1].trim());
        resultType = Type.make(p[2].trim());
    }

    public static boolean compose(List<Function> functionList){
        if(functionList.isEmpty()){
            throw new IllegalArgumentException();
        }

        Function g = functionList.get(0);
        for(int i=1; i<functionList.size();i++){
            Function f = functionList.get(i);
            if(f.arg != g.resultType){
                return false;
            }

            g = f;
        }

        return true;
    }

    public static void orderByComposition(List<Function> functionList, String composition) {
        String[] fNames = composition.split("\\.");
        List<Function> toOrder = new ArrayList<>(functionList);
        functionList.clear();

        for(int i = fNames.length-1; i >= 0; i-- ){
            String toFindName = fNames[i].trim();
            Function toFind = null;

            for(Function function : toOrder){
                if(function.fName.equals(toFindName)){
                    toFind = function;
                    break;
                }
            }

            if(toFind == null){
                throw new IllegalArgumentException();
            }

            functionList.add(toFind);
        }
    }

    @Override
    public String toString() {
        return String.format("%s :: %s -> %s", fName, arg,resultType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Function function = (Function) o;

        return function.getfName().equals(getfName()) &&
                function.getArg().equals(getArg()) &&
                function.getResultType().equals(getResultType());

    }

    @Override
    public int hashCode() {
        return getfName().hashCode() +
                getArg().toString().hashCode() +
                getResultType().toString().hashCode();
    }

    public String getfName() {
        return fName;
    }

    public Type getArg() {
        return arg;
    }

    public Type getResultType() {
        return resultType;
    }
}
