package com.example.springboottest.test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.*;

public class GuavaTest {


    @Test
    public void listTest() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        ImmutableList<String> list = ImmutableList.copyOf(arrayList);
        arrayList.add("4");
        System.out.println(arrayList);
        System.out.println(list);
    }

    @Test
    public void listTest1() {
        ImmutableList<String> list = ImmutableList.of("2","1",  "3");
        list.forEach(e -> {
            System.out.println(e);
        });
        System.out.println(list);
    }

    @Test
    public void multisetTest(){
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("1");
        multiset.add("2");
        multiset.add("3");
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("5");
        list.add("6");
        multiset.addAll(list);

        System.out.println(multiset.count("1"));
        System.out.println(multiset);

        multiset.remove("1");
        System.out.println(multiset);

    }

    @Test
    public void linkedListMultimapTest(){
        LinkedListMultimap<Object, Object> multimap = LinkedListMultimap.create();
        multimap.put("s1","s1");
        multimap.put("s2","s2");

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        multimap.putAll("list",list);

        System.out.println(multimap);
    }

    @Test
    public void newArrayListTest(){
        List<String> list = Lists.newArrayList("4", "5", "6");
        //按指定大小分隔list
        List<List<String>> lists = Lists.partition(list, 2);
        System.out.println(lists);
    }

    @Test
    public void cartesianProcustTest(){
        List<String> list1 = Lists.newArrayList("a","b","c");
        List<String> list2 = Lists.newArrayList("d","e","f");
        List<String> list3 = Lists.newArrayList("1","2","3");
        //笛卡尔集
        List<List<String>> lists = Lists.cartesianProduct(list3, list1, list2);
        System.out.println(lists);
    }

    @Test
    public void cartesianProduct(){
        Set<String> set1 = Sets.newHashSet("a", "b", "c");
        Set<String> set2 = Sets.newHashSet("1","2","3");
        Set<String> set3 = Sets.newHashSet("@","#","&");
        Set<List<String>> lists = Sets.cartesianProduct(set1, set2, set3);
        System.out.println(lists);

    }

    @Test
    public void differenceTest(){
        Set<String> set1 = Sets.newHashSet("a", "b", "d");
        Set<String> set2 = Sets.newHashSet("d", "e", "f");
        //difference返回：从set1中剔除两个set公共的元素
        System.out.println(Sets.difference(set1, set2));
        //symmetricDifference返回：剔除两个set公共的元素，再取两个集合的并集

    }


    @Test
    public void joinerTest(){
        String join = Joiner.on("-").join(Arrays.asList("1", "2", "3"));
        System.out.println(join);
    }


    @Test
    public void splitterTest(){
        List<String> list = Splitter.on(",").splitToList("a,b,c,d");
        System.out.println(list);
    }

    @Test
    public void rateLimit(){
        RateLimiter rateLimiter = RateLimiter.create(10);
        while (true){
            System.out.println("time:"+rateLimiter.acquire()+"s");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
