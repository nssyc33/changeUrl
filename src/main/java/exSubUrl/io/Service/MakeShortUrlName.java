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
				standKey = makeshortUrlName_step2(standKey);
			}else{
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
		if(nowSec == 0){
			nowSec = 1;
		}
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
	private String makeshortUrlName_step2(String stnKey){
		StringBuilder changeVar = new StringBuilder(stnKey);
		if(stnKey.indexOf("Y")> -1){
			if(stnKey.substring(7,8).equals("Y")){
				if("YYYYYYYY".equals(stnKey)){
					changeVar = new StringBuilder("11111111");
				}else{
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
				}
			}else if(stnKey.substring(6,7).equals("Y")){
				changeVar = makeStringBuilder(6, changeVar, stnKey);
			}else if(stnKey.substring(5,6).equals("Y")){
				changeVar = makeStringBuilder(5, changeVar, stnKey);
			}else if(stnKey.substring(4,5).equals("Y")){
				changeVar = makeStringBuilder(4, changeVar, stnKey);
			}else if(stnKey.substring(3,4).equals("Y")){
				changeVar = makeStringBuilder(3, changeVar, stnKey);
			}else if(stnKey.substring(2,3).equals("Y")){
				changeVar = makeStringBuilder(2, changeVar, stnKey);
			}else if(stnKey.substring(1,2).equals("Y")){
				changeVar = makeStringBuilder(1, changeVar, stnKey);
			}else if(stnKey.substring(0,1).equals("Y")){
				changeVar = new StringBuilder("11111111");
			}
		}else{
			String stdVar = stnKey.substring(7,8);
			int position = mainString.indexOf(stdVar);
			char as = mainString.charAt(position+1);
			changeVar.setCharAt(7, as);
		}
		return changeVar.toString();
	}
	
	/**
	 * makeshortUrlName_step2 파생 기능 
	 * @param index
	 * @param changeVar
	 * @param stnKey
	 * @return
	 */
	private StringBuilder makeStringBuilder(int index, StringBuilder changeVar, String stnKey){
		String pointVar = stnKey.substring(index,index+1);
		int po = 10;
		if(stnKey.substring(0,index+1).lastIndexOf("Y") > -1){
			int yIndex = stnKey.substring(0,index).lastIndexOf("Y");
			for(int i=index-1;i>-1;i--){
				if(!"Y".equals(stnKey.substring(i,i+1)) && yIndex!= i){
					po = i;
					break;
				}
			}
			String stdVar = stnKey.substring(0,index).substring(po, po+1);
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
			po = index-1;
			String stdVar = stnKey.substring(0,index).substring(po, po+1);
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
		return changeVar;
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
