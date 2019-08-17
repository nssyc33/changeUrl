package exSubUrl.io.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class MakeShortUrlName {
	
	private static final String mainString = "1234567890AaBbCcDdEeFfGghHiIjJkKLlmMNnOopPqQRrSsTtuUvVWwXxyY";

	/**
	 * 존재하는 URL인지 판단하는 함수
	 * @param asMap
	 * @param list
	 * @return
	 */
	public boolean duplicateExistsYn(HashMap asMap, ArrayList list){
		String url = (String) asMap.get("oriUrl");
		String asUrl = "";
		for(int i=0;i<list.size();i++){
			asUrl = (String)((HashMap)list.get(i)).get("oriUrl");
			if(asUrl.equals(url)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * key 생성하는 함수
	 * @param list
	 * @return
	 */
	public String makeShortUrl(ArrayList list , int nowSec){
		String standKey = makeShortUrl_step1(nowSec);
		String asKey = "";
		String result = "";
		int check = 0;
		
		while(check == 0){
			check = 1;
			for(int i=0;i<list.size();i++){
				asKey = (String)((HashMap)list.get(i)).get("subKey");
				if(asKey.equals(standKey)){
					check = 0;
					break;
				}
			}
			if(check == 0){
				System.out.println("여기?");
				standKey = makeshortUrlName_step2(standKey);
			}else{
				System.out.println("여기??????");
				result = standKey;
				break;
			}
		}
		return result;
	}
	
	/**
	 * key 생성시 기준 key 생성하는 함수
	 * @return
	 */
	private String makeShortUrl_step1(int nowSec){
		int start = 60%nowSec;
		
		String result = "";
		for(int i=0;i<8;i++){
			result += mainString.substring(start, start+1);
		}
		return result;
	}
	
	/**
	 * key 생성시 존재하는 key이면 다른 key 생성하는 함수
	 * @param asIsKey
	 * @return
	 */
	private String makeshortUrlName_step2(String asIsKey){
		String stnKey = asIsKey;
		System.out.println("stnKey : "+stnKey);
		StringBuilder changeVar = new StringBuilder(stnKey);
		if(stnKey.indexOf("Y")> -1){
			if(stnKey.substring(7,8).equals("Y")){
				if("YYYYYYYY".equals(stnKey)){
					changeVar = new StringBuilder("11111111");
				}else{
					System.out.println("7~8 라인을 들어왔습니다.");
					int po = 10;
					if(stnKey.substring(0,8).lastIndexOf("Y") > -1){
						int yIndex = stnKey.substring(0,8).lastIndexOf("Y");
						for(int i=7;i>-1;i--){
							if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
								po = i;
								break;
							}
						}
					}
					//po는 카운트를 넘겨야할 인덱스이다.
					String stdVar = stnKey.substring(0,7).substring(po, po+1);
					//stdVar 는 넘겨야할 인덱스의 다음 문자를 나타낸다.
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);//해당 인덱스를 다음 문자열로 교체하고
						}else{
							changeVar.setCharAt(j, '1');//그 이하단은 모두 1로 reset해준다.
						}
					}
					System.out.println("changeVar : "+changeVar);
				}
			}else if(stnKey.substring(6,7).equals("Y")){
				System.out.println("6~7 라인을 들어왔습니다.");
				String pointVar = stnKey.substring(6,7);
				int po = 10;
				if(stnKey.substring(0,6).lastIndexOf("Y") > -1){
					int yIndex = stnKey.substring(0,6).lastIndexOf("Y");
					for(int i=5;i>-1;i--){
						System.out.println("test : "+stnKey.substring(i,i+1));
						if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
							po = i;
							break;
						}
					}
					System.out.println("확인해봐라 po : "+po);		
					String stdVar = stnKey.substring(0,6).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}else{	
					po = 5;
					String stdVar = stnKey.substring(0,6).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}
				System.out.println("changeVar : "+changeVar);
			}else if(stnKey.substring(5,6).equals("Y")){
				System.out.println("5~6 라인을 들어왔습니다.");
				String pointVar = stnKey.substring(5,6);
				int po = 10;
				if(stnKey.substring(0,5).lastIndexOf("Y") > -1){
					int yIndex = stnKey.substring(0,5).lastIndexOf("Y");
					for(int i=4;i>-1;i--){
						System.out.println("test : "+stnKey.substring(i,i+1));
						if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
							po = i;
							break;
						}
					}
					System.out.println("확인해봐라 po : "+po);		
					String stdVar = stnKey.substring(0,5).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}else{	
					po = 4;
					String stdVar = stnKey.substring(0,5).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}
				System.out.println("changeVar : "+changeVar);
			}else if(stnKey.substring(4,5).equals("Y")){
				System.out.println("4~5 라인을 들어왔습니다.");
				String pointVar = stnKey.substring(4,5);
				int po = 10;
				if(stnKey.substring(0,4).lastIndexOf("Y") > -1){
					int yIndex = stnKey.substring(0,4).lastIndexOf("Y");
					for(int i=3;i>-1;i--){
						System.out.println("test : "+stnKey.substring(i,i+1));
						if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
							po = i;
							break;
						}
					}
					System.out.println("확인해봐라 po : "+po);		
					String stdVar = stnKey.substring(0,4).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}else{	
					po = 3;
					String stdVar = stnKey.substring(0,4).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}
				System.out.println("changeVar : "+changeVar);
			}else if(stnKey.substring(3,4).equals("Y")){
				System.out.println("3~4 라인을 들어왔습니다.");
				String pointVar = stnKey.substring(3,4);
				int po = 10;
				if(stnKey.substring(0,3).lastIndexOf("Y") > -1){
					int yIndex = stnKey.substring(0,3).lastIndexOf("Y");
					for(int i=2;i>-1;i--){
						System.out.println("test : "+stnKey.substring(i,i+1));
						if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
							po = i;
							break;
						}
					}
					System.out.println("확인해봐라 po : "+po);		
					String stdVar = stnKey.substring(0,3).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}else{	
					po = 2;
					String stdVar = stnKey.substring(0,3).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}
				System.out.println("changeVar : "+changeVar);
			}else if(stnKey.substring(2,3).equals("Y")){
				System.out.println("2~3 라인을 들어왔습니다.");
				String pointVar = stnKey.substring(2,3);
				int po = 10;
				if(stnKey.substring(0,2).lastIndexOf("Y") > -1){
					int yIndex = stnKey.substring(0,2).lastIndexOf("Y");
					for(int i=1;i>-1;i--){
						System.out.println("test : "+stnKey.substring(i,i+1));
						if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
							po = i;
							break;
						}
					}
					System.out.println("확인해봐라 po : "+po);		
					String stdVar = stnKey.substring(0,2).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}else{	
					po = 1;
					String stdVar = stnKey.substring(0,2).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}
				System.out.println("changeVar : "+changeVar);
			}else if(stnKey.substring(1,2).equals("Y")){
				System.out.println("1~2 라인을 들어왔습니다.");
				String pointVar = stnKey.substring(1,2);
				int po = 10;
				if(stnKey.substring(0,1).lastIndexOf("Y") > -1){
					int yIndex = stnKey.substring(0,1).lastIndexOf("Y");
					for(int i=1;i>-1;i--){
						System.out.println("test : "+stnKey.substring(i,i+1));
						if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
							po = i;
							break;
						}
					}
					System.out.println("확인해봐라 po : "+po);		
					String stdVar = stnKey.substring(0,1).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}else{	
					po = 0;
					String stdVar = stnKey.substring(0,1).substring(po, po+1);
					System.out.println("확인해봐라 : "+stdVar);		
					int position = mainString.indexOf(stdVar);
					char as = mainString.charAt(position+1);
					for(int j=po; j<8; j++){
						if(j == po){
							changeVar.setCharAt(j, as);
						}else{
							changeVar.setCharAt(j, '1');
						}
					}
				}
				System.out.println("changeVar : "+changeVar);
			}else if(stnKey.substring(0,1).equals("Y")){
				System.out.println("zzzzz");
				changeVar = new StringBuilder("11111111");
				System.out.println("맨앞이 Y인 경우이다.");
			}
		}else{
			System.out.println("Y가 없을 경우이다.");
			String stdVar = stnKey.substring(7,8);
			System.out.println("확인해봐라 : "+stdVar);		
			int position = mainString.indexOf(stdVar);
			char as = mainString.charAt(position+1);
			changeVar.setCharAt(7, as);
		}
		System.out.println("changeVar : "+changeVar);
		return changeVar.toString();
	}
	
	/**
	 * 대체 URL 호출 시 실제 URL 호출하는 함수
	 * @param searchKey
	 * @param list
	 * @return
	 */
	public String getOriUrl(String searchKey, ArrayList list){
		String subKey = "";
		for(int i=0;i<list.size();i++){
			subKey = (String)((HashMap)list.get(i)).get("subKey");
			if(searchKey.equals(subKey)){
				return (String)((HashMap)list.get(i)).get("oriUrl");
			}
		}
		return "";
	}
}
