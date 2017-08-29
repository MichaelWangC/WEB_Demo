package com.api.util;

public class ConstantUtil {
    private static final String sessionPrefix = ConstantUtil.class.getPackage()+".";
    public static String SESSION_USER_ID = sessionPrefix + "SESSION_USER_ID";
    public static String SESSION_USER_NAME = sessionPrefix + "SESSION_USER_NAME";

    public static String SESSION_CUSTOMER_ID = sessionPrefix + "SESSION_CUSTOMER_ID";
    public static String SESSION_CUSTOMER_NAME = sessionPrefix + "SESSION_CUSTOMER_NAME";

    public static String SESSION_SUPPLIER_ID = sessionPrefix + "SESSION_SUPPLIER_ID";
    public static String SESSION_SUPPLIER_NAME = sessionPrefix + "SESSION_SUPPLIER_NAME";

    // 错误码
    public static String ERROR_CODE_OK = "1";
    public static String ERROR_CODE_FAIL = "-1";
    public static String ERROR_CODE_LOGIN = "0";

    // 初始密码
    public static String INIT_PASSWORD = "888888";

    // 角色编码
    public  static String ROLE_ID_ADMINS = "1"; // 领导
    public  static String ROLE_ID_MEMBERS = "2"; // 员工

}
