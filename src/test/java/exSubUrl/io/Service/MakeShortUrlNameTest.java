package exSubUrl.io.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class MakeShortUrlNameTest {
//
//	ArrayList asList = new ArrayList();
//	
//	@Before
//	public void ArrayListSetting(){
//		ArrayList asList = new ArrayList();
//		HashMap asMap = new HashMap();
//		asMap.put("oriUrl", "www.daum.net");
//		asMap.put("subUrl", "http://localhost:8080/55555555");
//		asMap.put("subKey", "55555555");
//		asList.add(asMap);
//	}
	
	@Test
	public void test() {
		ArrayList asList = new ArrayList();
		HashMap asMap1 = new HashMap();
		HashMap asMap2 = new HashMap();
		HashMap asMap3 = new HashMap();
		
		asMap1.put("oriUrl", "www.daum.net");
		asMap1.put("subUrl", "http://localhost:8080/55555555");
		asMap1.put("subKey", "55555555");
		asList.add(asMap1);
		asMap2.put("oriUrl", "www.naver.com");
		asMap2.put("subUrl", "http://localhost:8080/44444444");
		asMap2.put("subKey", "44444444");
		asList.add(asMap2);
		asMap3.put("oriUrl", "www.naver.com");
		asMap3.put("subUrl", "http://localhost:8080/77777777");
		asMap3.put("subKey", "77777777");
		asList.add(asMap3);
	
		MakeShortUrlName msun = new MakeShortUrlName();
		String as = msun.makeShortUrl(asList, 7);
		System.out.println("결론은 : "+as);
		
		String as1 = msun.makeShortUrl(asList, 6);
		System.out.println("결론은 : "+as1);
		
		String as2 = msun.makeShortUrl(asList, 59);
		System.out.println("결론은 : "+as2);
		
		String as3 = msun.makeShortUrl(asList, 58);
		System.out.println("결론은 : "+as3);
		
	}

}
