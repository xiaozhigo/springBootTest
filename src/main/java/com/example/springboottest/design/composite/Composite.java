package com.example.springboottest.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝节点
 */
public class Composite extends Component{
    // 构件容器
    private List<Component> componentArrayList = new ArrayList<>();

    // 增加一个叶子节点或者树枝节点
    public void add(Component component){
        this.componentArrayList.add(component);
    }

    // 删除一个叶子节点或者树枝节点
    public void remove(Component component){
        this.componentArrayList.remove(component);
    }

    // 获取分支下所有叶子节点和树枝节点
    public List<Component> getChildren(){
        return this.componentArrayList;
    }
}
