package com._520it.crm.testOther;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;

public class TestCreate {

    String content1 = "hello world!";
    String content2 = "hello java world.";
    String content3 = "hello lucene world!";


    //索引文件的目录
    String path = "D:/Materials/IDEACode/crm/lucene/create";
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

        //创建文档
        Document doc2 = new Document();
        //在文档中添加字段信息
        doc2.add(new Field("title","doc2",type));
        doc2.add(new Field("content",content2,type));

        //创建文档
        Document doc3 = new Document();
        //在文档中添加字段信息
        doc3.add(new Field("title","doc3",type));
        doc3.add(new Field("content",content3,type));

        //写入文档
        writer.addDocument(doc1);
        writer.addDocument(doc2);
        writer.addDocument(doc3);

        //写入内容提交
        writer.commit();
        //关闭资源
        writer.close();

    }

    @Test
    public void testSearch() throws Exception {
        //1.创建目录文件，存储索引文件
        Directory directory = FSDirectory.open(new File(path));
        //需要读取的索引库中的内容
        IndexReader r = DirectoryReader.open(directory);
        //创建索引读取对象
        IndexSearcher searcher = new IndexSearcher(r);
        //语言分析接口
        Analyzer analyzer = new StandardAnalyzer();

        //查询对象,如果需要根据文本获取查询对象，需要导入queryParser类
        //需要查询的字段
        QueryParser parser = new QueryParser("content",analyzer);
        Query query = parser.parse("hello");
        //符合条件的前1000个
        TopDocs tds = searcher.search(query, 1000);
        System.out.println(tds.totalHits);
        ScoreDoc[] sds = tds.scoreDocs;

        Formatter formatter = new SimpleHTMLFormatter("<font color=\"red\">","</font>");
        Scorer scorer = new QueryScorer(query);
        Highlighter h1 = new Highlighter(formatter,scorer);
        h1.setMaxDocCharsToAnalyze(20);//限定前N个字符被高亮显示

        for (int i = 0; i < sds.length; i++) {
            ScoreDoc scoreDoc = sds[i];
            System.out.println(scoreDoc.score+"  "+scoreDoc.doc);
            Document doc = searcher.doc(scoreDoc.doc);
            String str = h1.getBestFragment(new StandardAnalyzer(),"cotent",doc.get("content"));
            System.out.println(str);
            System.out.println(doc.get("title")+"  "+doc.get("content"));
        }
    }

}
