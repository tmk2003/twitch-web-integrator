package com.impurity.twitchwebintegrator.test.utils;

import com.impurity.twitchwebintegrator.domain.osrs.OsrsPlayer;

import static com.impurity.twitchwebintegrator.builder.osrs.OsrsPlayerBuilder.buildPlayer;

public class OsrsFactory {
    public static String getValidOsrsPlayerClientResponse() {
        return "590663,1328,39039916\n" +
                "195711,91,6103476\n" +
                "205897,89,5298921\n" +
                "379871,90,5360274\n" +
                "289325,93,7515747\n" +
                "384239,87,4244478\n" +
                "501686,64,425495\n" +
                "449344,83,2679530\n" +
                "930420,58,236075\n" +
                "1339240,51,117752\n" +
                "1288040,30,13705\n" +
                "745048,62,335272\n" +
                "687565,59,264653\n" +
                "666942,59,258067\n" +
                "582299,56,187666\n" +
                "162560,76,1356244\n" +
                "401936,58,237626\n" +
                "667564,58,224811\n" +
                "1231633,28,11508\n" +
                "168429,87,4147823\n" +
                "1706522,1,0\n" +
                "1017801,15,2642\n" +
                "1288615,1,10\n" +
                "763340,32,18141\n" +
                "-1,-1\n" +
                "-1,-1\n" +
                "93027,500\n" +
                "-1,-1\n" +
                "234068,1\n" +
                "-1,-1\n" +
                "-1,-1\n" +
                "-1,-1\n" +
                "-1,-1\n" +
                "-1,-1";
    }

    public static OsrsPlayer getValidOsrsPlayer(String name, String type) {
        return buildPlayer(name, type, getValidOsrsPlayerClientResponse());
    }
}
