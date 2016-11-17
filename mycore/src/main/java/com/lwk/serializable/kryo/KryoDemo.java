package com.lwk.serializable.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.lwk.serializable.CityState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liwenke on 2016/11/13.
 */
public class KryoDemo {

    private static Logger logger = LoggerFactory.getLogger(KryoDemo.class);

    static Kryo kryo = new Kryo();

    public static byte[] serialize(Object obj) {
        byte[] buffer = new byte[2048];
        Output output = new Output(buffer);

        kryo.writeClassAndObject(output, obj);
        byte[] bs = output.toBytes();
        output.close();
        return bs;
    }

    public static Object deserialize(byte[] src) {
        Input input = new Input(src);
        Object obj = kryo.readClassAndObject(input);
        input.close();
        return obj;
    }

    public static void main(String[] args) {
        CityState cityState = new CityState("北京", "状态");
        byte[] serialize = serialize(cityState);
        CityState cityState1 = (CityState) deserialize(serialize);
        logger.info(cityState1.getCityName());


    }


}
