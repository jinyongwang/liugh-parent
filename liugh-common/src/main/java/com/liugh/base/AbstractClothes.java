package com.liugh.base;

/**
 * Created by Administrator on 2018/10/12.
 */
public class AbstractClothes  implements AbstractPerson{

    AbstractPerson abstractPerson ;

    public AbstractClothes( AbstractPerson abstractPerson ){
        this.abstractPerson = abstractPerson ;
    }

    @Override
    public void show() {
        abstractPerson.show();
    }
}
