package com.mxnet.tools.cppAntlrAPI;

import com.mxnet.tools.cppAntlrAPI.CPP14Parser;
import com.mxnet.tools.cppAntlrAPI.CPP14ParserBaseListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;



import java.util.*;

public class CppAllWalker extends CPP14ParserBaseListener {
    StringBuilder tokens;
    public CppAllWalker(StringBuilder tokens) {
        this.tokens = tokens;
    }

}

