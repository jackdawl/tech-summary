package demo;

import java.util.*;

public class FindCityByIP {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] cityArr = sc.nextLine().split(";");
        String[] ipArr = sc.nextLine().split(",");

        List<CityInfo> cityInfos = new ArrayList<>();

        for (String city : cityArr) {
            String[] info = city.split("[=,]");
            cityInfos.add(new CityInfo(info[0], info[1], info[2]));
        }


        StringJoiner res = new StringJoiner(",");

        for (String ip : ipArr) {
            long ipNum = ip2Long(ip);
            long minLen = Long.MAX_VALUE;
            String cityName = "";

            for (CityInfo cityInfo : cityInfos) {

                if (ipNum >= cityInfo.startIp && ipNum <= cityInfo.endIp && minLen > cityInfo.ipLen ) {
                    cityName = cityInfo.name;
                    minLen = cityInfo.ipLen;
                }


            }

            res.add(cityName);
        }

        System.out.println(res);

    }


    static class CityInfo {
        String name;
        long startIp;
        long endIp;
        long ipLen;

        public CityInfo(String name, String startIp, String endIp) {
            this.name = name;
            this.startIp = ip2Long(startIp);
            this.endIp = ip2Long(endIp);
            this.ipLen = this.endIp - this.startIp + 1;


        }
    }


    public static long ip2Long(String ip) {
        ip = ip.replaceAll("\\.", "");

        return Long.parseLong(ip);

    }


}
