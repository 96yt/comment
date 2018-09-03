package com.yt.comment.service;


public interface MemberService {

    /**
     * 判断手机号是否存在
     * @param phone 手机号
     * @return
     */
    boolean exists(Long phone);

    /**
     * 保存手机号与对应的验证码的MD5码到缓存中
     * @param phone 手机号
     * @param code 验证码
     * @return
     */
    boolean saveCode(Long phone,String code);

    /**
     * 下发短信验证码
     * @param phone 手机号
     * @param content 验证码
     * @return
     */
    boolean sendCode(Long phone,String content);

    /**
     * 根据手机号获取验证码的MD5值
     * @param phone 手机号
     * @return
     */
    String getCode(Long phone);

    /**
     * 保存token到对应的手机号
     * @param token
     * @param phone 手机号
     */
    void saveToken(String token,Long phone);

    /**
     * 根据token获取用户信息（手机号）
     * @param token
     * @return 手机号
     */
    Long getPhone(String token);

    /**
     * 根据手机号获取会员表主键
     * @param phone 手机号
     * @return 会员表主键
     */
    Long getIdByPhone(Long phone);
}
