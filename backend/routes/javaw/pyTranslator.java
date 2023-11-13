/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author server
 */
public class pyTranslator {
    private String sourceCode;

    public pyTranslator(String sourceCode){
        this.sourceCode = sourceCode;
    }

    public void pythonTranslate(){
        sourceCode = reemplaceImports(sourceCode);
        sourceCode = reemplaceVarsEquals(sourceCode);
        sourceCode = reemplaceVarTypeAssign(sourceCode);
        sourceCode = reemplaceFuncVar(sourceCode);
        sourceCode = reemplaceIncremento(sourceCode);
        sourceCode = reemplaceClassMaker(sourceCode);
        sourceCode = reemplaceConstructor(sourceCode);
        sourceCode = reemplaceVar2Var(sourceCode);
        sourceCode = reemplacePrivateVar(sourceCode);
        sourceCode = reemplaceAccessFunc(sourceCode);
        sourceCode = sourceCode.replace(";", "");
        sourceCode = sourceCode.replace("}", "");
        sourceCode = sourceCode.replace("{", ":");
        sourceCode = sourceCode.replace("main():", "");
        sourceCode = sourceCode.replace("[", "");
        sourceCode = sourceCode.replace("]", "");
        sourceCode = sourceCode.replace("public", "");
    }

    private String reemplaceAccessFunc(String temporal){
        try{
            String pattern = "(protected|private)\\s+def\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(sourceCode.substring(start, end));
                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerAccessFunc(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceAccessFunc");
        }
        return temporal;
    }

    private String reemplacePrivateVar(String temporal){
        try{
            String pattern = "private\\s+([a-zA-Z_$][a-zA-Z_$0-9]*);";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(sourceCode.substring(start, end));
                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerPrivateVar(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplacePrivateFunc");
        }
        return temporal;
    }

    private String reemplaceVar2Var(String temporal){
        try{
            String pattern = "super\\.([a-zA-Z_$][a-zA-Z_$0-9]*)\\(([a-zA-Z_$][a-zA-Z_$0-9]*)\\);";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(sourceCode.substring(start, end));
                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerVar2Var(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceVar2Var");
        }
        return temporal;
    }

    private String reemplaceConstructor(String temporal){
        try{
            String pattern = "\\(\\)constructor\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();
            
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(sourceCode.substring(start, end));

                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerConstructor(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceConstructor");
        }
        return temporal;
    }

    private String reemplaceClassMaker(String temporal){
        try{
            String pattern = "class\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)\\s*(?:extends\\s+([a-zA-Z_$][a-zA-Z_$0-9]*))?\\s*";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();
            
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(sourceCode.substring(start, end));

                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerClassMaker(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceClassMaker");
        }
        return temporal;
    }
    
    private String reemplaceVarTypeAssign(String temporal){
        try{
            String pattern = "\\((int|float|double|boolean|string)\\)\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)\\;";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();
            
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(sourceCode.substring(start, end));

                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerVarTypeAssign(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceVarTypeAssign");
        }
        return temporal;
    }

    private String reemplaceVarsEquals(String temporal){
        try{
            String pattern = "\\s*(string|float|int|double|boolean)\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)\\s*=";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(sourceCode.substring(start, end));
                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerVarsEquals(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceVarsEquals");
        }
        return temporal;
    }

    private String reemplaceIncremento(String temporal){
        try{
            String pattern = "([a-zA-Z_$][a-zA-Z_$0-9]*)(\\+\\+|--)\\s*;";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(sourceCode.substring(start, end));
                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerIncremento(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceIncremento");
        }
        return temporal;
    }

    private String reemplaceFuncVar(String temporal){
        try{
            String pattern = "\\s*(int|float|double|boolean|string)\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerReemplaceFuncVar(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceFuncVar");
        }
        return temporal;
    }

    private String reemplaceImports(String temporal){
        try{
            String pattern = "import\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)\\s+([a-zA-Z_$][a-zA-Z_$0-9]*)\\s*;";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(temporal);
            List<int[]> matchedIndices = new ArrayList<>();

            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                matchedIndices.add(new int[]{start, end});
            }

            for (int[] index : matchedIndices) {
                temporal = temporal.replace(sourceCode.substring(index[0], index[1]), descomponerImport(sourceCode.substring(index[0], index[1])));
            }
        }catch(Exception e){
            System.out.println("Error en reemplaceImports");
        }
        return temporal;
    }

    private String descomponerIncremento(String codeLine){
        if (codeLine.contains("++")) {
            return codeLine.substring(0, codeLine.indexOf("++")) + " += 1";
        } else {
            return codeLine.substring(0, codeLine.indexOf("--")) + " -= 1";
        }
    }

    private String descomponerConstructor(String codeLine){
        return "def __init__(self):";
    }

    private String descomponerVar2Var(String codeLine){
        return "self." + codeLine.substring(codeLine.indexOf("super.") + 6, codeLine.indexOf("(")) + " = " + codeLine.substring(codeLine.indexOf("(") + 1, codeLine.indexOf(")"));
    }

    private String descomponerClassMaker(String codeLine){
        if (codeLine.contains("extends")) {
            return "class " + codeLine.substring(codeLine.indexOf(" ") + 1, codeLine.indexOf("extends")) + "(" + codeLine.substring(codeLine.indexOf("extends") + 8) + ")";
        } else {
            return "class " + codeLine.substring(codeLine.indexOf(" ") + 1) + ":";
        }
    }

    private String descomponerAccessFunc(String codeLine){
        if (codeLine.contains("protected")) {
            return "def _" + codeLine.substring(codeLine.indexOf("def") + 4);
        } else {
            return "def _" + codeLine.substring(codeLine.indexOf("def") + 4);
        }
    }

    private String descomponerPrivateVar(String codeLine){
        if(codeLine.contains("=")){
            return "__" + codeLine.substring(codeLine.indexOf("private") +8);
        }else{
            return "__" + codeLine.substring(codeLine.indexOf("private") +8) + " = None";
        }
    }

    private String descomponerReemplaceFuncVar(String codeLine){
        return codeLine.substring(codeLine.indexOf(" ") + 1);
    }
    
    private String descomponerVarTypeAssign(String codeLine){
        return (codeLine.substring(codeLine.indexOf(" ") + 1)) + " = " + codeLine.substring(codeLine.indexOf("(")+1, codeLine.indexOf(")")) + "()";
    }
    
    private String descomponerVarsEquals(String codeLine){
        return "\n"+(codeLine.substring(codeLine.indexOf(" ")+1));
    }

    private String descomponerImport(String codeLine){
        return ("import " + codeLine.substring(codeLine.lastIndexOf(" ") + 1));
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}
