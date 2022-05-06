import java.util.Stack;

public class SpringArray {

    public static Spring equivalentSpring(String springExpr){
        int count=0;
        for(int i=0; i<springExpr.length()-2; i++){
            if(springExpr.substring(i,i+2).equals("[]"))
                count++;
        }
        Spring[] springs=new Spring[count];
        for(int i=0; i<count; i++){
            springs[i]=new Spring();
        }
        return equivalentSpring(springExpr, springs, 0, springExpr.length());
    }
    public static Spring equivalentSpring(String springExpr, Spring[] springs){
        return equivalentSpring(springExpr, springs, 0, springExpr.length());
    }

    //This is not working
    //(but it will)
    private static int springIndex=0;
    public static Spring equivalentSpring(String springExpr, Spring[] springs, int exprStartIndex, int exprEndIndex) {
        if(exprEndIndex-exprStartIndex==1){
            return springs[springIndex++];
        }
        int closingIndex=getClosingIndex(springExpr, exprStartIndex+1, springExpr.charAt(exprStartIndex+1));
        if(springExpr.charAt(exprStartIndex)=='{'){
            return equivalentSpring(springExpr, springs, exprStartIndex, closingIndex)
                    .inSeries(equivalentSpring(springExpr, springs, closingIndex+1, exprEndIndex-1));
        }
        return equivalentSpring(springExpr, springs, exprStartIndex, closingIndex)
                    .inSeries(equivalentSpring(springExpr, springs, closingIndex, exprEndIndex));

    }
    public static int getClosingIndex(String expr, int pos, char c){
        char oppositeChar=getOppositeChar(c);
        int closingIndex=expr.indexOf(oppositeChar,pos+1);
        int firstOpenIndex=expr.indexOf(c,pos+1);
        while(!(firstOpenIndex==-1 || closingIndex<firstOpenIndex)){
            firstOpenIndex=expr.indexOf(c,firstOpenIndex+1);
            closingIndex=expr.indexOf(oppositeChar,closingIndex+1);
        }
        return closingIndex;
    }
    private static char getOppositeChar(char c){
        if(c=='{')
            return '}';
        if(c=='[')
            return ']';
        if(c==']')
            return '[';
        return '{';
    }
    }
