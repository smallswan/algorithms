package org.gxu.algorithm.test;
import java.util.HashSet;
import java.util.Set;

import com.tfc.analysis.KWSeeker;
import com.tfc.analysis.entity.Keyword;
import com.tfc.analysis.fragment.HTMLFragment;
import com.tfc.analysis.process.WordFinder;
public class SearchWordDemo {
	public static void main(String[] args) {
		KWSeeker kw1 = KWSeeker.getInstance(new Keyword("test1"), new Keyword("tes2"));
		// 添加一个词
		kw1.addWord(new Keyword("test3"));
		// 使用默认的高亮方式将文本中含有上面指定的所有词显示出来！
		kw1.highlight("这是tes1,要注意哦！");
		// 使用HTML页面加粗的高亮方式将文本中含有上面指定的所有词显示出来！
		String highlight=kw1.highlight("这是tes1,要注意哦！", new HTMLFragment("<b>", "</b>"));
		System.out.println(highlight);
		// 找出文本中所有含有上面词库中的词！
		Set<String> keyword=kw1.findWords("这是tes1,要注意哦！");
		System.out.println(keyword);
		// 使用指定的processor（如：WordFinder找出文本中所有含有上面词库中的词）对文本进行处理！
		HashSet result=(HashSet )kw1.process(new WordFinder(), "这是tes1,要注意哦！", null);
		System.out.println(result);
	}

}
