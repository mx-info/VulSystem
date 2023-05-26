package com.mxnet.tools;

import com.mxnet.tools.cppAntlrAPI.CPP14Lexer;
import com.mxnet.tools.cppAntlrAPI.CPP14Parser;
import com.mxnet.tools.cppAntlrAPI.CppAllWalker;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExtractFun {
    public static String temp = "";

    public static void main(String[] args) throws Exception {
        ExtractFun e = new ExtractFun();
//        e.extractAllFun("E:\\testcases", "E:\\functions.txt");
//        File[] files = new File("E:\\sard\\testcases").listFiles();
//        for (File type : files) {
//            e.extractAllTypeFun(type.getAbsolutePath(),"E:\\sard\\type118\\" + type.getName());
//        }
        List<String> strings = e.extractFun("C:\\Users\\Administrator\\Desktop\\vulsystem\\VulSystem\\src\\main\\java\\com\\mxnet\\test.cpp");
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static List<String> extractFun(String sourcePath) throws Exception {
        InputStream is = new FileInputStream(sourcePath);
        ANTLRInputStream input = new ANTLRInputStream(is);
        CPP14Lexer lexer = new CPP14Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        CPP14Parser parser = new CPP14Parser(tokens);
        ParseTree tree = parser.translationUnit();
        /* Extract Function Tokens */
        ParseTreeWalker walker = new ParseTreeWalker();
        StringBuilder contractTokens = new StringBuilder();
        walker.walk( new CppAllWalker(contractTokens), tree);
        List<Token> tokens_funs = new ArrayList<>();
        List<String> funs = new ArrayList<>();
        extractFunctionByInorderTraversal(tokens_funs, tree, funs);
//        String functions = "";
//        for (String fun : funs) {
//            functions += fun + "\n";
//        }
//        System.out.println(functions);
        return funs;

    }
    public static void extractFunctionByInorderTraversal(List<Token> tokens, ParseTree tree, List<String> funs) {
        for (int i = 0; i < tree.getChildCount(); i++) {
            ParseTree child = tree.getChild(i);
            String funString = new String();
            if (child instanceof CPP14Parser.FunctionDefinitionContext) {
                inOrderTraversal(tokens, child, funString);
                funs.add(temp);
                temp = "";
            }
            extractFunctionByInorderTraversal(tokens, child, funs);
        }
    }

    public static void inOrderTraversal(List<Token> tokens, ParseTree parent, String funStr) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            ParseTree child = parent.getChild(i);
            String label = "";
            if (child instanceof TerminalNode) {
                TerminalNode node = (TerminalNode) child;
                tokens.add(node.getSymbol());
                label = node.getText();
                temp += label + " ";
                funStr += node.getText() + " ";
            } else {
                inOrderTraversal(tokens, child, funStr);
            }
        }
    }

}
