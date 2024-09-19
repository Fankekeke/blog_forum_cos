package cc.mrbird.febs.cos.controller;

import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MessageInfo;
import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.entity.ReplyInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.*;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    private final IUserInfoService userInfoService;

    private final IBulletinInfoService bulletinInfoService;

    private final IPostInfoService postInfoService;

    private final IReplyInfoService replyInfoService;

    private final IMessageInfoService messageInfoService;

    @PostMapping("/userAdd")
    public R userAdd(@RequestBody UserInfo user) throws Exception {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=wx76a6577665633a86";//自己的appid
        url += "&secret=78070ccedf3f17b272b84bdeeca28a2e";//自己的appSecret
        url += "&js_code=" + user.getOpenId();
        url += "&grant_type=authorization_code";
        url += "&connect_redirect=1";
        String res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(false).build();
        httpget.setConfig(requestConfig);
        response = httpClient.execute(httpget);
        HttpEntity responseEntity = response.getEntity();
        System.out.println("响应状态为:" + response.getStatusLine());
        if (responseEntity != null) {
            res = EntityUtils.toString(responseEntity);
            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
            System.out.println("响应内容为:" + res);
        }
        // 释放资源
        httpClient.close();
        response.close();
        String openid = JSON.parseObject(res).get("openid").toString();
        System.out.println("openid" + openid);
        int count = userInfoService.count(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getOpenId, openid));
        if (count > 0) {
            return R.ok(userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getOpenId, openid)));
        } else {
            user.setOpenId(openid);
            user.setCreateDate(DateUtil.formatDateTime(new Date()));
            user.setCode("UR-" + System.currentTimeMillis());
            userInfoService.save(user);
            return R.ok(user);
        }
    }

    @RequestMapping("/openid")
    public R getUserInfo(@RequestParam(name = "code") String code) throws Exception {
        System.out.println("code" + code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=wx9fffb151ded22005";//自己的appid
        url += "&secret=9666e94c91361e7de4d3a6d09a23402f";//自己的appSecret
        url += "&js_code=" + code;
        url += "&grant_type=authorization_code";
        url += "&connect_redirect=1";
        String res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);    //GET方式
        CloseableHttpResponse response = null;
        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(false).build();
        httpget.setConfig(requestConfig);
        response = httpClient.execute(httpget);
        HttpEntity responseEntity = response.getEntity();
        System.out.println("响应状态为:" + response.getStatusLine());
        if (responseEntity != null) {
            res = EntityUtils.toString(responseEntity);
            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
            System.out.println("响应内容为:" + res);
        }
        // 释放资源
        httpClient.close();
        response.close();
        String openid = JSON.parseObject(res).get("openid").toString();
        System.out.println("openid" + openid);
        return R.ok(openid);
    }

    @GetMapping("/subscription")
    public R subscription(@RequestParam("taskCode") String taskCode) throws Exception {
        LinkedHashMap<String, Object> tokenParam = new LinkedHashMap<String, Object>() {
            {
                put("grant_type", "client_credential");
                put("appid", "wx76a6577665633a86");
                put("secret", "78070ccedf3f17b272b84bdeeca28a2e");
            }
        };
        String tokenResult = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token", tokenParam);
        String token = JSON.parseObject(tokenResult).get("access_token").toString();
        LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>() {
            {
                put("thing1", new HashMap<String, Object>() {
                    {
                        put("value", "张三");
                    }
                });
                put("character_string3", new HashMap<String, Object>() {
                    {
                        put("value", taskCode);
                    }
                });
                put("time4", new HashMap<String, Object>() {
                    {
                        put("value", DateUtil.formatDateTime(new Date()));
                    }
                });
                put("thing5", new HashMap<String, Object>() {
                    {
                        put("value", "若查看详细内容，请登录小程序");
                    }
                });
            }
        };
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + token;
        LinkedHashMap<String, Object> subscription = new LinkedHashMap<String, Object>() {
            {
                put("touser", "oeDfR5zqxQD3EEA3uPT836qnauSc");
                put("template_id", "Z27pBK1n9WnKNtQ_fo7TC-nUJUlOQ9KVJk6LIgp0nH8");
                put("data", data);
            }
        };
        String result = HttpUtil.post(url, JSONUtil.toJsonStr(subscription));
        return R.ok(result);
    }

//    /**
//     * 进入小程序主页信息
//     *
//     * @return 结果
//     */
//    @GetMapping("/home")
//    public R home() {
//        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
//        result.put("commodityHot", commodityInfoService.getCommodityHot());
//        result.put("shopInfo", shopInfoService.shopInfoHot());
//        result.put("postInfo", postInfoService.getPostListHot());
//        return R.ok(result);
//    }

    /**
     * 获取贴子信息
     *
     * @return 结果
     */
    @GetMapping("/getPostList")
    public R getPostList() {
        return R.ok(postInfoService.getPostByTag(null));
    }

    /**
     * 根据贴子编号获取详细信息
     *
     * @param postId
     * @return 结果
     */
    @GetMapping("/getPostInfoById")
    public R getPostInfoById(@RequestParam Integer postId) {
        return R.ok(postInfoService.postDetail(postId));
    }

    /**
     * 贴子回复
     *
     * @return 结果
     */
    @PostMapping("/replyPost")
    public R replyPost(@RequestBody ReplyInfo replyInfo) {
        replyInfo.setSendCreate(DateUtil.formatDateTime(new Date()));
        return R.ok(replyInfoService.save(replyInfo));
    }

    /**
     * 添加贴子
     *
     * @param postInfo
     * @return 结果
     */
    @PostMapping("/postAdd")
    public R postAdd(@RequestBody PostInfo postInfo) {
        postInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(postInfoService.save(postInfo));
    }

    /**
     * 获取公告信息
     *
     * @return 结果
     */
    @GetMapping("/getBulletinList")
    public R getBulletinList() {
        return R.ok(bulletinInfoService.list());
    }

//    /**
//     * 查询消息信息
//     *
//     * @param userId
//     * @return 结果
//     */
//    @GetMapping("/messageListById")
//    public R messageListById(@RequestParam Integer userId) {
//        return R.ok(messageInfoService.messageListById(userId));
//    }
//
//    /**
//     * 查找聊天记录
//     *
//     * @param takeUser
//     * @param sendUser
//     * @return 结果
//     */
//    @GetMapping("/getMessageDetail")
//    public R getMessageDetail(@RequestParam(value = "takeUser") Integer takeUser, @RequestParam(value = "sendUser") Integer sendUser, @RequestParam(value = "userId") Integer userId) {
//        if (takeUser.equals(userId)) {
//            messageInfoService.update(Wrappers.<MessageInfo>lambdaUpdate().set(MessageInfo::getTaskStatus, 1)
//                    .eq(MessageInfo::getTakeUser, takeUser).eq(MessageInfo::getSendUser, sendUser));
//        } else {
//            messageInfoService.update(Wrappers.<MessageInfo>lambdaUpdate().set(MessageInfo::getTaskStatus, 1)
//                    .eq(MessageInfo::getTakeUser, sendUser).eq(MessageInfo::getSendUser, takeUser));
//        }
//        return R.ok(messageInfoService.getMessageDetail(takeUser, sendUser));
//    }
//
//    /**
//     * 消息回复
//     *
//     * @param messageInfo
//     * @return 结果
//     */
//    @PostMapping("/messageReply")
//    public R messageReply(@RequestBody MessageInfo messageInfo) {
//        messageInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
//        messageInfo.setTaskStatus(0);
//        return R.ok(messageInfoService.save(messageInfo));
//    }
//
//    /**
//     * 查询店铺及商品信息
//     *
//     * @return 结果
//     */
//    @GetMapping("/selShopDetailList")
//    public R selShopDetailList() {
//        return R.ok(commodityInfoService.selShopDetailList());
//    }
//
//    /**
//     * 获取商铺及商品详细信息
//     *
//     * @param shopId
//     * @return 结果
//     */
//    @GetMapping("/getShopDetail")
//    public R getShopDetail(@RequestParam Integer shopId) {
//        return R.ok(commodityInfoService.getShopDetail(shopId));
//    }
//
//    /**
//     * 店铺商品排序方式
//     *
//     * @param shopId
//     * @return 结果
//     */
//    @GetMapping("/shopCommoditSort")
//    public R shopCommoditySort(@RequestParam(value = "shopId") Integer shopId, @RequestParam(value = "type") Integer type) {
//        return R.ok(commodityInfoService.shopCommoditySort(shopId, type));
//    }
//
//    /**
//     * 模糊查询店内商品
//     *
//     * @param shopId
//     * @param key
//     * @return 结果
//     */
//    @GetMapping("/commodityLikeByShop")
//    public R commodityLikeByShop(@RequestParam(value = "shopId") Integer shopId, @RequestParam(value = "key") String key) {
//        return R.ok(commodityInfoService.commodityLikeByShop(shopId, key));
//    }
//
//    /**
//     * 查找商品或店铺
//     *
//     * @param key
//     * @return 结果
//     */
//    @GetMapping("/getGoodsFuzzy")
//    public R getGoodsFuzzy(String key) {
//        return R.ok(commodityInfoService.getGoodsFuzzy(key));
//    }
//
//    /**
//     * 获取用户所有订单
//     *
//     * @param userId
//     * @return 结果
//     */
//    @GetMapping("/getOrderListByUserId")
//    public R getOrderListByUserId(Integer userId) {
//        return R.ok(orderInfoService.getOrderListByUserId(userId));
//    }
}