package com._520it.crm.testOther;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.util.HashMap;
import java.util.Map;

public class TestAnalyzer {

    String en = "oh my god";
    String cn = "迅雷不及掩耳盗铃";

    public void testAnalyzer(Analyzer analyzer, String content) throws Exception{
        TokenStream stream = analyzer.tokenStream("content", content);
        stream.reset();
        while (stream.incrementToken()){
            System.out.println(stream);
        }
    }
    @Test
    public void test1() throws Exception{
        testAnalyzer(new StandardAnalyzer(),cn);
    }

    @Test
    public void test2() throws Exception{
        Map<String,Analyzer> fieldAnalyzers = new HashMap<>();
        fieldAnalyzers.put("title",new StandardAnalyzer());
        fieldAnalyzers.put("content",new SimpleAnalyzer());
        PerFieldAnalyzerWrapper wrapper = new PerFieldAnalyzerWrapper(new SimpleAnalyzer(),fieldAnalyzers);

        //TokenStream stream = wrapper.tokenStream("title", cn);
        //TokenStream stream = wrapper.tokenStream("content", cn);
        TokenStream stream = wrapper.tokenStream("phone", cn);//默认分词器SimpleAnalyzer()
        stream.reset();
        while (stream.incrementToken()){
            System.out.println(stream);
        }
    }
    //两个词分词
    @Test
    public void test3() throws Exception{
        testAnalyzer(new CJKAnalyzer(),cn);
    }

    //根据字典分词
    @Test
    public void test4() throws Exception{
        testAnalyzer(new SmartChineseAnalyzer(),cn);
    }

    @Test
    public void test5() throws Exception{
        Map<String,Analyzer> fieldAnalyzers = new HashMap<>();
        fieldAnalyzers.put("content",new IKAnalyzer());
        PerFieldAnalyzerWrapper wrapper = new PerFieldAnalyzerWrapper(new SimpleAnalyzer(),fieldAnalyzers);

        TokenStream stream = wrapper.tokenStream("content", cn);
        stream.reset();
        while (stream.incrementToken()){
            System.out.println(stream);
        }
    }

}
