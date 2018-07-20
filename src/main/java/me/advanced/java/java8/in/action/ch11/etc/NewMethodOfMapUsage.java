package me.advanced.java.java8.in.action.ch11.etc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-20
 */
@Service
public class NewMethodOfMapUsage implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
//		new ComputeExample().run();
		new ComputeIfAbsentExample().run();
//		new MergeExample().run();
//		new PutIfAbsentExample().run();
//		new RemoveByKeyValyeExample().run();
//		new ReplaceAllExample().run();
	}
}


class ComputeExample {
	private Map<String, String> userDetails = new HashMap<>();
	
	public ComputeExample() {
		System.out.println("\n\nComputeExample");
		init();
	}
	
	public void init() {
		userDetails.clear();
		userDetails.put("userKey", "1");
		userDetails.put("userName", "Lee Tae Su");
		userDetails.put("address", "Mapo-gu");
		userDetails.put("email", "taesu@crscube.co.kr");
	}
	
	public void run() {
		//Origin logic - change user name
		if(userDetails.containsKey("userName")) {
			userDetails.put("userName", userDetails.get("userName") + ZonedDateTime.now());
		}
		System.out.println(userDetails);
		init();
		
		//Advanced logic
		userDetails.compute("userName", (k, v) -> v != null ? v + ZonedDateTime.now() : null);
		System.out.println(userDetails);
		init();
		
		//Origin logic - change not contains
		if(userDetails.containsKey("userName_2")) {
			userDetails.put("userName", userDetails.get("userName") + ZonedDateTime.now());
		}
		System.out.println(userDetails);
		init();
		
		//Advanced logic
		userDetails.compute("userName_2", (k, v) -> {
			System.out.println(k + "::" + v);        //userName+2::null
			return v != null ? v + ZonedDateTime.now() : null;
		});
		System.out.println(userDetails);
		init();
		
		/*
			compute -> BiFunction을 인자로 받음 
			(Key를 통해 어디선가 map에 put 할 데이터를 불러올 수 있음)
			
			기존에 존재하지 않는 경우가 있을 수 있으므로 기존 value가 null인지 조사 필요 (merge와 비슷)
			
			차이점은 merge는 originValue, newValue를 인자로 받는 BiFunction 이지만
			compute는 key, originValue를 인자로 받는 BiFunction임

		 */
	}
}

class ComputeIfAbsentExample {
	private Map<String, String> menu = new HashMap<>();
	
	public ComputeIfAbsentExample() {
		System.out.println("\n\nComputeIfAbsentExample");
		init();
	}
	
	public void init() {
		menu.clear();
		menu.put("coffee", "5000");
		menu.put("juice", "1500");
		menu.put("water", "1000");
		menu.put("milk", "3000");
	}
	
	public void run() {
		//Origin logic - if not contain then put
		if(!menu.containsKey("green-tea")) {
			menu.put("green-tea", "4000");
		}
		System.out.println(menu);
		init();
		
		
		/*
			computeIfAbsent -> Function을 인자로 받음 (Key를 통해 어디선가 map에 put 할 데이터를 불러올 수 있음)
		 */
		menu.computeIfAbsent("green-tea", this::getMenuPriceFromRemote);
		System.out.println(menu);
		
		//이미 존재하는 경우엔 무거운 로직을 돌리지 않음
		menu.computeIfAbsent("green-tea", this::getMenuPriceFromRemote);
		System.out.println(menu);
		
		
		/*
			computeIfPresent -> BiFunction을 인자로 받음 (Key, Value)
		 */
		//key가 존재하는 경우에만 실행 됨
		menu.computeIfPresent("lemon-tea", (k, v) -> {
			System.out.println(k + "::" + v);
			return k + v;
		});
		System.out.println(menu);
		
		//key가 존재하는 경우에만 실행 됨
		menu.computeIfPresent("green-tea", (k, v) -> {
			System.out.println(k + "::" + v);
			return k + v;
		});
		System.out.println(menu);
		
		
		System.out.println("\n\nList in Map");
		List<String> foods = Arrays.asList("orange", "banana", "coffee", "pizza",
				"orange", "hamburger", "pizza", "coffee", "banana");
		Map<String, List<String>> map = new HashMap<>();
		foods.forEach(food ->
				map.computeIfAbsent(food, key -> new ArrayList<>()).add(food));
		System.out.println(map);
		map.clear();
		
		for(String food : foods) {
			if(!map.containsKey(food)) {
				map.put(food, new ArrayList<>());
			}
			map.get(food).add(food);
		}
		System.out.println(map);
	}
	
	private String getMenuPriceFromRemote(String menuId) {
		System.out.println("get menu [" + menuId + "] price");
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			
		}
		return Integer.toString(menuId.charAt(0) * 100);
	}
}

class MergeExample {
	private List<String> fruits = Arrays.asList("Orange", "Orange", "Orange", "Banana",
			"Apple", "Orange", "Melon", "Orange", "Orange", "Banana", "Apple", "Watermelon"
	);
	private Map<String, Integer> countByGroup = new HashMap<>();
	
	public MergeExample() {
		System.out.println("\n\nMergeExample");
	}
	
	public void run() {
		//기존에 있는 Value(originValue)와 새로운 Value(newValue)를 
		//인자로 받는 BiFunction을 제공
		fruits.forEach(fruit ->
				countByGroup.merge(fruit, 1, (originValue, newValue) ->
						originValue != null ? originValue + newValue : newValue));
		
		System.out.println(countByGroup);
		countByGroup.clear();
		
		for(String fruit : fruits) {
			if(countByGroup.containsKey(fruit)) {
				countByGroup.put(fruit, countByGroup.get(fruit) + 1);
			}
			else {
				countByGroup.put(fruit, 1);
			}
		}
		System.out.println(countByGroup);
	}
}

class PutIfAbsentExample {
	private Map<String, String> addressMap = new HashMap<>();
	
	public PutIfAbsentExample() {
		System.out.println("\n\nPutIfAbsentExample");
	}
	
	public void run() {
		if(!addressMap.containsKey("friend1")) {
			addressMap.put("friend1", "010");
		}
		System.out.println(addressMap);
		
		addressMap.putIfAbsent("friend2", "9995");
		System.out.println(addressMap);
		
		addressMap.putIfAbsent("friend1", "010");
		System.out.println(addressMap);
	}
}

class RemoveByKeyValyeExample {
	private Map<String, String> addressMap = new HashMap<>();
	
	public RemoveByKeyValyeExample() {
		System.out.println("\n\nRemoveByKeyValyeExample");
	}
	
	public void run() {
		addressMap.putIfAbsent("friend1", "010");
		addressMap.putIfAbsent("friend2", "9995");
		
		if(addressMap.containsKey("friend1")) {
			if(ObjectUtils.nullSafeEquals(addressMap.get("friend1"), "010")) {
				addressMap.remove("friend1");
			}
		}
		System.out.println(addressMap);
		
		addressMap.remove("friend2", "9995");
		System.out.println(addressMap);
	}
}

class ReplaceAllExample {
	private Map<String, Integer> menu = new HashMap<>();
	
	public ReplaceAllExample() {
		System.out.println("\n\nRemoveByKeyValyeExample");
		init();
	}
	
	public void init() {
		menu.clear();
		menu.put("coffee", 5000);
		menu.put("juice", 1500);
		menu.put("water", 1000);
		menu.put("milk", 3000);
	}
	
	public void run() {
		//모든 메뉴 300원 인상
		for(String key : menu.keySet()) {
			menu.put(key, menu.get(key) + 300);
		}
		System.out.println(menu);
		init();
		
		//Using keyset foreach
		menu.keySet().forEach(key -> menu.put(key, menu.get(key) + 300));
		System.out.println(menu);
		init();
		
		//Using map foreach
		menu.forEach((key, value) -> menu.put(key, value + 300));
		System.out.println(menu);
		init();
		
		//Using replace all (반환 값이 value로 들어감)
		menu.replaceAll((key, value) -> value + 300);
		System.out.println(menu);
		init();
		
	}
}