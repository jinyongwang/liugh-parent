package com.liugh.base;

/**
 * Created by Administrator on 2018/10/12.
 */
public class Hat extends AbstractClothes {

    public Hat(AbstractPerson abstractPerson) {
        super(abstractPerson);
    }

    @Override
    public void show() {
        super.show();
        say();
    }

    public void say(){
        System.out.println( "我展示一个帽子");
    }

}
