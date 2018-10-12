package com.liugh.base;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2018/10/12.
 */
public class Test {
    public static void main(String[] args) throws Exception{

//        //创建被装饰者
        Me me = new Me() ;
//        //裸体的人被装饰了帽子 ，具有了展示帽子的能力
        Hat hat = new Hat( me ) ;

        // 带了帽子的人被装饰了鞋子，具有了展示鞋子的本领
        Shoes shoes = new Shoes(  new Hat( new Me() ) ) ;

        shoes.show();

        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("C://offline_FtnInfo.txt")));
        byte b = in.readByte();


    }
}
