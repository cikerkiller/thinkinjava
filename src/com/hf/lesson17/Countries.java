package com.hf.lesson17;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 抽象map
 * @author ciker
 * @desc   
 *
 */
public class Countries {
	public static final String[][] DATA = {
			{"中国","北京"},{"韩国","首尔"},{"日本","东京"},{"泰国","曼谷"},{"马来西亚","吉隆坡"},{"越南","河内"},{"朝鲜","平壤"},{"印度","新德里"},{"文莱","斯里巴加湾市"},{"黎巴嫩","贝鲁特"},{"老挝","万象"},{"阿联酋","阿布扎比"},{"科威特","科威特城"},{"巴勒斯坦","耶路撒冷"},{"阿曼","马斯喀特"},{"吉尔吉斯斯坦","比什凯克"},{"柬埔寨","金边"},{"阿塞拜疆","巴库"},{"缅甸","内比都"},{"哈萨克斯坦","阿斯塔纳"},{"格鲁吉亚","第比利斯"},{"菲律宾","马尼拉"},{"不丹","廷布"},{"巴林","麦纳麦"},{"巴基斯坦","伊斯兰堡"},{"卡塔尔","多哈"},{"土库曼斯坦","阿什哈巴德"},{"约旦","安曼"},{"印度尼西亚","雅加达"},{"以色列","特拉维夫"},{"伊朗","德黑兰"},{"伊拉克","巴格达"},{"也门","萨那"},{"亚美尼亚","埃里温"},{"叙利亚","大马士革"},{"蒙古","乌兰巴托"},{"乌兹别克斯坦","塔什干"},{"马尔代夫","马累"},{"土耳其","安卡拉"},{"塔吉克斯坦","杜尚别"},{"斯里兰卡","科伦坡"},{"沙特阿拉伯","利雅得"},{"塞浦路斯","尼科西亚"},{"尼泊尔","加德满都"},{"孟加拉国","达卡"},{"阿富汗","喀布尔"},{"新加坡","新加坡"},{"英国","伦敦"},{"意大利","罗马"},{"法国","巴黎"},{"西班牙","马德里"},{"德国","柏林"},{"瑞士","伯尔尼"},{"俄罗斯","莫斯科"},{"瑞典","斯底哥尔摩"},{"比利时","布鲁塞尔"},{"丹麦","哥本哈根"},{"保加利亚","索非亚"},{"白俄罗斯","明斯克"},{"列支敦士登","瓦杜兹"},{"奥地利","维也纳"},{"捷克","布拉格"},{"波兰","华沙"},{"波黑","萨拉热窝"},{"安道尔","安道尔城"},{"爱沙尼亚","塔林"},{"爱尔兰","都柏林"},{"梵蒂冈","梵蒂冈城"},{"芬兰","赫尔辛基"},{"荷兰","阿姆斯特丹"},{"冰岛","雷克雅未克"},{"摩尔多瓦","基希讷乌"},{"匈牙利","布达佩斯"},{"希腊","雅典"},{"乌克兰","基辅"},{"斯洛文尼亚","卢布尔雅那"},{"斯洛伐克","布拉迪斯拉发"},{"圣马力诺","圣马力诺"},{"葡萄牙","里斯本"},{"拉脱维亚","里加"},{"摩纳哥","摩纳哥城"},{"克罗地亚","萨格勒布"},{"马其顿","斯科普里"},{"马耳他","瓦莱塔"},{"罗马尼亚","布加勒斯特"},{"卢森堡","卢森堡"},{"直布罗陀(英)",""},{"立陶宛","维尔纽斯"},{"阿尔巴尼亚","地拉那"},{"挪威","奥斯陆"},{"埃及","开罗"},{"南非","比勒陀利亚"},{"坦桑尼亚","达累斯萨拉姆"},{"尼日利亚","阿布贾"},{"肯尼亚","内罗毕"},{"加纳","阿克拉"},{"喀麦隆","雅温得"},{"津巴布韦","哈拉雷"},{"埃塞俄比亚","亚的斯亚贝巴"},{"毛里求斯","路易港"},{"卢旺达","基加利"},{"利比亚","的黎波里"},{"摩洛哥","拉巴特"},{"塞舌尔","维多利亚"},{"赞比亚","卢萨卡"},{"突尼斯","突尼斯"},{"赤道几内亚","马拉博"},{"西撒哈拉","阿尤恩"},{"几内亚比绍","比绍"},{"吉布提","吉布提市"},{"刚果（布）","金沙萨"},{"冈比亚","班珠尔"},{"佛得角","普拉亚"},{"法属波里尼西亚",""},{"利比里亚","蒙罗维亚"},{"多哥","洛美"},{"布隆迪","布琼布拉"},{"布基纳法索","瓦加杜古"},{"博茨瓦纳","哈博罗内"},{"贝宁","波多诺伏"},{"安哥拉","罗安达"},{"乍得","恩贾梅纳"},{"厄立特里亚","阿斯马拉"},{"马达加斯加","塔那那利佛"},{"尼日尔","尼亚美"},{"圣多美和普林西比","圣多美"},{"纳米比亚","温得和克"},{"莫桑比克","马普托"},{"斯威士兰","姆巴巴纳"},{"毛里塔尼亚","努瓦克肖特"},{"苏丹","喀土穆"},{"加蓬","利伯维尔"},{"马拉维","利隆圭"},{"乌干达","坎帕拉"},{"中非","班吉"},{"苏里南","帕拉马里博"},{"阿尔及利亚","阿尔及尔"},{"莱索托","马塞卢"},{"索马里","摩加迪沙"},{"科特迪瓦","亚穆苏克罗"},{"科摩罗","莫罗尼"},{"塞内加尔","达喀尔"},{"马里","巴马科"},{"美国","华盛顿哥伦比亚特区"},{"加拿大","渥太华"},{"墨西哥","墨西哥城"},{"古巴","哈瓦那"},{"巴巴多斯","布里奇顿"},{"巴哈马","拿骚"},{"巴拿马","巴拿马城"},{"伯利兹","贝尔莫潘"},{"多米尼加 ","圣多明各"},{"多米尼克","罗索"},{"哥斯达黎加","圣何塞"},{"格林纳达","圣乔治"},{"格陵兰岛","戈特霍布"},{"安提瓜和巴布达","圣约翰"},{"洪都拉斯","特古西加尔巴"},{"牙买加","金斯敦"},{"尼加拉瓜","马那瓜"},{"萨尔瓦多","圣萨尔瓦多"},{"圣克里斯托弗和尼维斯",""},{"圣卢西亚","卡斯特里"},{"特立尼达和多巴哥","西班牙港"},{"危地马拉","危地马拉城"},{"维尔京群岛和圣罗克伊","夏洛特·阿马里"},{"海地","太子港"},{"巴西","巴西利亚"},{"阿根廷","布宜诺斯艾利斯"},{"智利","圣地亚哥"},{"乌拉圭","蒙得维的亚"},{"委内瑞拉","加拉加斯"},{"秘鲁","利马"},{"圭亚那","乔治敦"},{"哥伦比亚","圣菲波哥大"},{"刚果(金)","斯坦利港"},{"法属圭亚那","卡宴"},{"厄瓜多尔","基多"},{"玻利维亚","苏克雷"},{"巴拉圭","亚松森"},{"新西兰","惠灵顿"},{"澳大利亚","堪培拉"},{"斐济","苏瓦"},{"萨摩亚","阿皮亚"},{"瓦努阿图","维拉港"},{"图瓦卢","富纳富提"},{"汤加","努库阿洛法"},{"所罗门群岛","霍尼亚拉"},{"帕劳","科罗尔"},{"瑙鲁","亚伦区"},{"马绍尔群岛","马朱罗"},{"几内亚","马拉博"},{"基里巴斯","塔拉瓦"},{"巴布亚新几内亚","莫尔斯比港"}
	};
	
	private static class FlyweightMap extends AbstractMap<String, String> {
		
		// 定制Map.Entry 享元部分：每个Map.Entry只存储data的索引index，使用索引来返回恰当的DATA元素
		private static class Entry implements Map.Entry<String, String> {
			int index;
			public Entry(int index) {
				this.index=index;
			}
			
			@Override
			public int hashCode() {
				return DATA[index][0].hashCode();
			}

			@Override
			public boolean equals(Object obj) {
				return DATA[index][0].equals(obj);
			}

			@Override
			public String getKey() {
				return DATA[index][0];
			}

			@Override
			public String getValue() {
				return DATA[index][1];
			}

			@Override
			public String setValue(String value) {
				throw new UnsupportedOperationException();
			}
			
		}
		
		static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
			private int size;
			EntrySet(int size){
				// EntrySet保证它的size不会大于DATA的长度
				if(size<0) {
					this.size=0;
				}else if(size>DATA.length){
					this.size=DATA.length;
				}else {
					this.size=size;
				}
			}
			
			private class Iter implements Iterator<Map.Entry<String, String>> {
				// 初始化entry.index=-1;
				private Entry entry = new Entry(-1);
				@Override
				public boolean hasNext() {
					return entry.index < size-1;
				}

				@Override
				public Map.Entry<String, String> next() {
					// 从index=0开始
					entry.index++;
					return entry;
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
				
			}
			
			@Override
			public Iterator<java.util.Map.Entry<String, String>> iterator() {
				return new Iter();
			}

			@Override
			public int size() {
				return size;
			}
		}
		
		private static Set<Map.Entry<String, String>> entries = new EntrySet(DATA.length);
		// 实现abstractMap的方法,此处需要定制的set与Map.Entry
		@Override
		public Set<Map.Entry<String, String>> entrySet() {
			return entries;
		}
	}
	
	// 产生一个包含指定尺寸的EntrySet的FlyweightMap
	static Map<String,String> select(final int size) {
		return new FlyweightMap() {
			@Override
			public Set<Map.Entry<String, String>> entrySet() {
				return new EntrySet(size);// 此处产生数据
			}
		};
	}
	
	static Map<String, String> map = new FlyweightMap();
	public static Map<String,String> capitals(){
		return map;
	}
	
	public static Map<String,String> capitals(int size){
		return select(size);
	}
	
	static List<String> names = new ArrayList<>(map.keySet());// 使用国家首都键值对的键（即国家名）来填充list
	
	public static List<String> names() {
		return names;
	}
	
	// 使用指定大小来生成国家名list
	public static List<String> names(int size){
		return new ArrayList<>(select(size).keySet());
	}
	
	public static void main(String[] args) {
		System.out.println(capitals(10));
		System.out.println(names(10));
		System.out.println(new HashMap<>(capitals(3)));
		System.out.println(new TreeMap<>(capitals(3)));
		System.out.println(new Hashtable<>(capitals(3)));
		System.out.println("======================");
		System.out.println(new HashSet<>(names(6)));
		System.out.println(new LinkedHashSet<>(names(6)));
		System.out.println(new TreeSet<>(names(6)));
		System.out.println("======================");
		System.out.println(new ArrayList<>(names(6)));
		System.out.println(new LinkedList<>(names(6)));
		System.out.println("======================");
		System.out.println(capitals().get("中国"));
		ArrayList<String> list1 = new ArrayList<>(names(6));
		System.out.println(list1);
		Collections.shuffle(list1);// 打乱顺序
		System.out.println(list1);
		LinkedList<String> list2 = new LinkedList<>(names(6));
		System.out.println(list2);
		Collections.shuffle(list2);// 打乱顺序
		System.out.println(list2);
		
		Set<String> set = new HashSet<>(names(6));
		System.out.println(set);
		System.out.println("======================");
		set = new HashSet<>(new HashSet<>(names(6)));
		set.addAll(set);
		System.out.println(set);
		ArrayList arrayList = new ArrayList<>();
		arrayList.addAll(new ArrayList<>(names(6)));
		arrayList.addAll(new ArrayList<>(names(6)));
		System.out.println(arrayList);
		
	}
	
}
