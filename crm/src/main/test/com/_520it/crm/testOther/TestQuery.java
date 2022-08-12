package com._520it.crm.testOther;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;

public class TestQuery {

    String content1 = "hello world!";
    String content2 = "hello java world.";
    String content3 = "hello java lucene world!";


    //索引文件的目录
    String path = "D:/Materials/IDEACode/crm/lucene/query";
    //版本
    Version matchVersion = Version.LUCENE_4_10_4;

    @Test
    public void testIndex() throws Exception {
        //1.创建目录文件，存储索引文件
        Directory directory = FSDirectory.open(new File(path));
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //配置对象
        IndexWriterConfig config = new IndexWriterConfig(matchVersion,analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        //索引写入对象
        IndexWriter writer = new IndexWriter(directory,config);

        FieldType type = new FieldType();
        type.setIndexed(true);
        type.setStored(true);
        //创建文档
        Document doc1 = new Document();
        //在文档中添加字段信息
        doc1.add(new Field("title","doc1",type));
        doc1.add(new Field("content",content1,type));
        doc1.add(new Field("inputtime","20160918",type));

        //创建文档
        Document doc2 = new Document();
        //在文档中添加字段信息
        doc2.add(new Field("title","doc2",type));
        doc2.add(new Field("content",content2,type));
        doc2.add(new Field("inputtime","20160919",type));

        //创建文档
        Document doc3 = new Document();
        //在文档中添加字段信息
        doc3.add(new Field("title","doc3",type));
        doc3.add(new Field("content",content3,type));
        doc3.add(new Field("inputtime","20160920",type));

        //写入文档
        writer.addDocument(doc1);
        writer.addDocument(doc2);
        writer.addDocument(doc3);

        //写入内容提交
        writer.commit();
        //关闭资源
        writer.close();

    }

    public void testSearch(Query query) throws Exception {
        //1.创建目录文件，存储索引文件
        Directory directory = FSDirectory.open(new File(path));
        //需要读取的索引库中的内容
        IndexReader r = DirectoryReader.open(directory);
        //创建索引读取对象
        IndexSearcher searcher = new IndexSearcher(r);

        //符合条件的前1000个
        TopDocs tds = searcher.search(query, 1000);
        System.out.println(tds.totalHits);
        ScoreDoc[] sds = tds.scoreDocs;
        for (int i = 0; i < sds.length; i++) {
            ScoreDoc scoreDoc = sds[i];
            System.out.println(scoreDoc.score+"  "+scoreDoc.doc);
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println(doc.get("title")+"  "+doc.get("content")+"  "+doc.get("inputtime"));
        }
    }

    @Test
    public void testAll() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //查询所有
/*        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("*:*");*/
        Query query = new MatchAllDocsQuery();
        System.out.println(query.getClass());
        testSearch(query);
    }

    @Test
    public void testWord() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //选择在哪里查询
/*        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("title:doc1");*/
        TermQuery query = new TermQuery(new Term("title","doc1"));

        System.out.println(query.getClass());
        testSearch(query);
    }

    @Test
    public void testPhrash() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //内容禁止解析,段落查询
/*        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("\"hello world\"");*/
        PhraseQuery query = new PhraseQuery();
        query.add(new Term("content","hello"));
        query.add(new Term("content","world"));
        System.out.println(query.getClass());
        testSearch(query);
    }

    @Test
    public void testWildCard() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //通配符
        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("l*en?");
        System.out.println(query.getClass());
        testSearch(query);
    }

    @Test
    public void testLike() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //模糊查询
/*        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("lucDene~1");*/
        FuzzyQuery query = new FuzzyQuery(new Term("content","lucDne"),1);
        System.out.println(query.getClass());
        testSearch(query);
    }

    @Test
    public void testPhrash2() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //段落的临近查询
/*        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("\"hello world\"~1");*/
        PhraseQuery query = new PhraseQuery();
        query.add(new Term("content","hello"));
        query.add(new Term("content","world"));
        query.setSlop(1);//N任意值
        System.out.println(query.getClass());
        testSearch(query);
    }

    @Test
    public void testRange() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //范围查询{}[],开闭区间  true是否为闭区间
/*        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("inputtime:{20160918 TO 20160920}");*/
        TermRangeQuery query = new TermRangeQuery("inputtime",new BytesRef("20160918"),new BytesRef("20160920"),true,true);
        System.out.println(query.getClass());
        testSearch(query);
    }

    @Test
    public void testMix() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //组合查询
        QueryParser parser = new QueryParser("content",analyzer);
/*        Query query = parser.parse("java AND world");*/
/*        Query query = parser.parse("java && world");*/
/*        Query query = parser.parse("java || world");*/
/*        Query query = parser.parse("hello NOT content:java");*/
/*        Query query = parser.parse("hello !content:java");*/
/*        Query query = parser.parse("+content:java -content:lucene");*/
        BooleanQuery query = new BooleanQuery();
        TermRangeQuery q1 = new TermRangeQuery("inputtime",new BytesRef("20160918"),new BytesRef("20160920"),true,true);
        TermQuery q2 = new TermQuery(new Term("content","java"));
        //BooleanClause.Occur.SHOULD
        query.add(q1, BooleanClause.Occur.MUST);
        query.add(q2, BooleanClause.Occur.MUST_NOT);

        System.out.println(query.getClass());
        testSearch(query);
    }

    @Test
    public void testWeight() throws Exception {
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();
        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //增加权重
/*        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("hello world^10");*/
        BooleanQuery query = new BooleanQuery();
        TermQuery q1 = new TermQuery(new Term("content","hello"));

        TermQuery q2 = new TermQuery(new Term("content","lucene"));
        q2.setBoost(10f);
        query.add(q1, BooleanClause.Occur.SHOULD);
        query.add(q2, BooleanClause.Occur.SHOULD);



        System.out.println(query.getClass());
        testSearch(query);
    }



}
