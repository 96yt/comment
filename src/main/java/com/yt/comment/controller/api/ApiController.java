package com.yt.comment.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.yt.comment.constant.ApiCodeEnum;
import com.yt.comment.dto.*;
import com.yt.comment.entity.Page;
import com.yt.comment.service.*;
import com.yt.comment.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AdService adService;

    @Resource
    private BusinessService businessService;

    @Resource
    private MemberService memberService;

    @Resource
    private OrdersService ordersService;
    @Resource
    private CommentService commentService;

    @Value("${ad.number}")
    private int adNumber;

    //首页商户推荐每页条数
    @Value("${businessHome.number}")
    private int businessHomeNumber;

    //商户搜索列表每页条数
    @Value("${businessSearch.number}")
    private int businessSearchNumber;

    /**
     * 首页 —— 广告（超值特惠）
     */
    @RequestMapping(value = "/homead",method = RequestMethod.GET)
    public List<AdDto> homead() {
        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        return adService.searchByPage(adDto);
    }



    /**
     * 首页 —— 推荐列表（猜你喜欢）(一个参数)
     *
     */
    @RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
    public BusinessListDto homelist(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessHomeNumber);
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 搜索结果页 - 搜索结果 - 三个参数
     *
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}", method = RequestMethod.GET)
    public BusinessListDto searchByKeyword(BusinessDto businessDto) {
      businessDto.getPage().setPageNumber(businessSearchNumber);
      return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 搜索结果页 - 搜索结果 - 两个参数
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}", method = RequestMethod.GET)
    public BusinessListDto search(BusinessDto businessDto) {
        businessDto.getPage().setPageNumber(businessSearchNumber);
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 详情页 - 商户信息
     *
     */
    @RequestMapping(value = "/detail/info/{id}", method = RequestMethod.GET)
    public BusinessDto detail(@PathVariable("id") Long id) {
        return businessService.getById(id);
    }

    /**
     * 详情页 - 用户评论
     */
    @RequestMapping(value = "/detail/comment/{page}/{id}", method = RequestMethod.GET)
    public CommentListDto detail(@PathVariable("businessId")Long businessId, Page page) {
        return commentService.getListByBusinessId(businessId,page);
    }

    /**
     * 订单列表
     */
    @RequestMapping(value = "/orderlist/{username}", method = RequestMethod.GET)
    public List<OrdersDto> orderlist(@PathVariable("username")Long username) {
        Long memberId = memberService.getIdByPhone(username);
        return ordersService.getListByMemberId(memberId);
    }

    /**
     * 提交评论
     */
    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    public ApiCodeDto submitComment(CommentForSubmitDto dto) {
        ApiCodeDto result;
        //TODO 需要完成的步骤
        //1.校验登录信息：token、手机号
        Long phone = memberService.getPhone(dto.getToken());
        if (phone != null && phone.equals(dto.getUsername())) {
            //2、根据手机号取出会员ID
            Long memberId = memberService.getIdByPhone(phone);
            //3、根据提交上来的订单Id获取对应的会员ID，校验与当前登录的会员是否一致
            OrdersDto ordersDto = ordersService.getById(dto.getId());
            if (ordersDto.getMemberId().equals(memberId)) {
                //4、保存评论
                commentService.add(dto);
                result = new ApiCodeDto(ApiCodeEnum.SUCCESS);
            } else {
                result = new ApiCodeDto(ApiCodeEnum.NO_AUTH);
            }
        } else {
            result = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
        }
        return result;
    }

    /**
     * 根据手机号下发短信验证码
     * @param username
     * @return
     */
    @RequestMapping(value = "/sms",method = RequestMethod.POST)
    public ApiCodeDto sms(@RequestParam("username")Long username) {
        ApiCodeDto apiCodeDto;
        //1.验证用户手机号是否存在（是否注册过）
        if (memberService.exists(username)) {
            //2.生成6位随机数
            String code = String.valueOf(CommonUtil.random(6));
            //3、保存手机号与对应的md5（6位随机数）(一般保存一分钟)
            if (memberService.saveCode(username,code)) {
                if (memberService.sendCode(username,code)) {
                    apiCodeDto = new ApiCodeDto(ApiCodeEnum.SUCCESS,code);
                } else {
                    //调用信息通道发送验证码失败
                    apiCodeDto = new ApiCodeDto(ApiCodeEnum.SEND_FAIL);
                }
                //4.调用短信通道，将明文6位随机数发送到对应的手机上
            } else {
                //保存失败，说明有对应的用户名的验证码存在，不要在时间内重复请求
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
            }
        } else {
            //会员没有注册
            apiCodeDto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
        }
        return apiCodeDto;
    }

    /**
     * 会员登录
     * @param username
     * @param code
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ApiCodeDto login(@RequestParam("username")Long username,@RequestParam("code")String code) {
        ApiCodeDto apiCodeDto = null;
        //1.用手机号取出保存的MD5（6位随机数），能取到，并且与提交上来的code值相同为校验通过
        String saveCode = memberService.getCode(username);
        if (saveCode != null) {
            //2 判断验证码是否正确
            //因为前端传过来的也是MD5算法加密的字符串
            if (saveCode.equals(code)) {
                //3 如果校验通过，生成一个32位的token
                String token = CommonUtil.getUUID();
                //4.保存手机号与对应的token(一般这个手机中途没有与服务端交互的情况下，保持10分钟)
                memberService.saveToken(token,username);
                //5 token返回给前端
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
                apiCodeDto.setToken(token);
            } else {
                //验证出错
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
            }
        } else {
            //验证码失效
            apiCodeDto = new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
        }
        return apiCodeDto;
    }

    /**
     * 买单
     * @param orderForBuyDto
     * @return
     */
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public ApiCodeDto order(OrderForBuyDto orderForBuyDto) {
        ApiCodeDto apiCodeDto;
        //1.校验token是否有效
        Long phone = memberService.getPhone(orderForBuyDto.getToken());
        if (phone != null && phone.equals(orderForBuyDto.getUsername())) {
            //2.根据手机号获取会员主键
            Long memberId = memberService.getIdByPhone(phone);
            //3.保存订单
            OrdersDto ordersDto = new OrdersDto();
            ordersDto.setNum(orderForBuyDto.getNum());
            ordersDto.setPrice(orderForBuyDto.getPrice());
            ordersDto.setBusinessId(orderForBuyDto.getId());
            ordersDto.setMemberId(memberId);
            ordersService.add(ordersDto);
            apiCodeDto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
        } else {
            apiCodeDto = new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
        }
        return apiCodeDto;
    }
}
