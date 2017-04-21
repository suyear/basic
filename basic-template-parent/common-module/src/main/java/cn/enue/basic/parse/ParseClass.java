package cn.enue.basic.parse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xiaoleilu.hutool.db.DbUtil;
import com.xiaoleilu.hutool.db.SqlRunner;

import cn.enue.basic.common.util.UUIDGenerator;

public class ParseClass {
	public static List<HashMap<String,String>> parsePackage(Document doc){
		Elements classLis=doc.select("div.indexContainer li");
		for (int i = 0; i < classLis.size(); i++) {
			Element ele=classLis.get(i);
			Element aEle=ele.select("a").get(0);
			String href=aEle.attr("href");
			String title=aEle.attr("title");
			String text=aEle.text();
			String sql="insert into api_beta.api_classes(uuid,api_href,api_title,api_class_name) values('"+UUIDGenerator.getUUID()+"','"+href+"','"+title+"','"+text+"');";
			
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			Document doc=Jsoup.connect("http://docs.oracle.com/javase/8/docs/api/allclasses-frame.html").get();
			parsePackage(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
