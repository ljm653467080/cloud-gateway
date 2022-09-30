package cn.com.imaginary.ms.apigateway.core;

import cn.com.imaginary.ms.apigateway.model.ReturnData;
import cn.com.imaginary.ms.apigateway.util.DateUtil;
import cn.com.imaginary.ms.apigateway.util.ReturnDataUtil;
import com.alibaba.fastjson.JSON;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2018/11/2 17:08
 */
public class JwtHelper {
    private static final String TIMESTAMP = "timestamp";
    private static final String EXPIRE = "expire";
    private static final int DEFAULT_TIMEOUT = 5;

    private static final byte[] secret = "d8ec89ccd7394606972c1263d308cfb3!".getBytes();

    public static String creatToken(com.alibaba.fastjson.JSONObject playload) {
        return creatToken(playload, null);
    }

    public static String creatToken(com.alibaba.fastjson.JSONObject playload, Integer timeout) {
        try {
            if (playload == null) {
                playload = new com.alibaba.fastjson.JSONObject();
            }
            long currentTimeMillis = System.currentTimeMillis();
            playload.put(TIMESTAMP, currentTimeMillis);
            playload.put(EXPIRE, DateUtil.getDateByHours(timeout == null ? DEFAULT_TIMEOUT : timeout).getTime());
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
            Payload payload = new Payload(new JSONObject(playload));
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);
            JWSSigner jwsSigner = new MACSigner(secret);
            jwsObject.sign(jwsSigner);
            return jwsObject.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ReturnData valid(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return ReturnDataUtil.unAuth();
            }

            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier jwsVerifier = new MACVerifier(secret);
            ReturnData returnData;
            if (jwsObject.verify(jwsVerifier)) {
                returnData = new ReturnData();
                JSONObject jsonObject = payload.toJSONObject();
                returnData.setData(jsonObject);
                if (jsonObject.containsKey(EXPIRE)) {
                    Long expTime = Long.valueOf(jsonObject.get(EXPIRE).toString());
                    Long nowTime = System.currentTimeMillis();
                    if (nowTime > expTime) {
                        returnData = ReturnDataUtil.unAuth();
                    }
                }
            } else {
                returnData = ReturnDataUtil.unAuth();
            }
            return returnData;
        } catch (Exception e) {
            return ReturnDataUtil.systemError();
        }
    }

    /**
     * 数组中两个元素之和=target值
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        if (Objects.isNull(nums) || nums.length == 0) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }

    public static int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            intersection[index++] = num;
        }
        System.out.println(JSON.toJSON(intersection));
        return intersection;
    }


    public static void main(String[] args) {

//        int[] nums = {2, 7, 11, 15, 6};
        int[] nums = {6, 3, 8, 2, 1, 5};
        System.out.println(JSON.toJSON(JwtHelper.twoSum(nums, 8)));

        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        intersection(nums1, nums2);
    }

}
