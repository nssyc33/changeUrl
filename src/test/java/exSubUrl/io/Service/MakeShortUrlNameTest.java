package exSubUrl.io.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import static org.junit.Assert.*;

public class MakeShortUrlNameTest {
	
	@Test
	public void makeShortUrl_test1() {
		ArrayList asList = new ArrayList();
		MakeShortUrlName msun = new MakeShortUrlName();
		String as1 = msun.makeShortUrl(asList, 60);
		assertEquals("11111111", as1);   
	}
	
	@Test
	public void makeShortUrl_test2() {
		ArrayList asList = new ArrayList();
		HashMap asMap1 = new HashMap();
		asMap1.put("oriUrl", "www.daum.net");
		asMap1.put("subUrl", "http://localhost:8080/55555555");
		asMap1.put("subKey", "11111111");
		asList.add(asMap1);
		MakeShortUrlName msun = new MakeShortUrlName();
		String as1 = msun.makeShortUrl(asList, 60);
		assertEquals("11111112", as1);   
	}

}
