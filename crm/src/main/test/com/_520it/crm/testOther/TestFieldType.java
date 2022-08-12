package com._520it.crm.testOther;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;

public class TestFieldType {

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
        //每次都重新创建
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        //索引写入对象
        IndexWriter writer = new IndexWriter(directory,config);

        FieldType type = new FieldType();
        type.setIndexed(true);//表示是否索引字段
        type.setStored(true);//表示是否存储
        type.setTokenized(true);//表示是否分割
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
}
