package top.mowang;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.Scanner;

/**
 * Redis6-Study
 *
 * @author : Xuan Li
 * @website : https://mowangblog.top
 * @date : 2021/10/21 19:12
 **/
@SuppressWarnings("all")
public class PhoneCode {
    public static void main(String[] args) {
//        String code = getCode();
//        System.out.println(code);
        Scanner scanner = new Scanner(System.in);
        System.out.println("===发送验证码===");
        System.out.println("===请输入你的手机号===");
        String phone = scanner.next();
        verifyCode(phone,getCode());
        System.out.println("===请输入你收到的验证码===");
        if (checkCode(phone,scanner.next())) {
            System.out.println("验证码正确，校验成功");
        }else {
            System.out.println("验证码错误，校验失败");
        }
    }

    public static boolean checkCode(String phone,String code){
        Jedis jedis = new Jedis("192.168.64.128",6379);
        String codeKey = phone+":code";
        if (jedis.get(codeKey).equals(code)) {
            return true;
        }
        return false;
    }
    public static void verifyCode(String phone,String code){
        Jedis jedis = new Jedis("192.168.64.128",6379);
        String contKey = phone+":count";
        String codeKey = phone+":code";
        String s = jedis.get(contKey);
        if (s == null) {
            jedis.setex(contKey,24*60*60,"1");
        }else if(Integer.parseInt(s)<=2){
            jedis.incr(contKey);
        }else {
            System.out.println("发送次数超过三次");
            jedis.close();
            return;
        }
        System.out.println(code);
        jedis.setex(codeKey,120,code);
        jedis.close();
    }

    public static String getCode(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
