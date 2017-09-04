package com.api.util;

public class TempletStatusManage {

    public static final String TEMPLET_STATUS_ERROR = "TEMPLET_STATUS_ERROR";
    /**
     * 根据当前样板状态 返回可执行步骤
     * @param status 当前状态
     * @param isSupplier 是否为供应商
     * @return
     */
    public static String getTempletNextActionsByStatus(String status, boolean isSupplier) {

        if (status.equals("已接单")){
            if (isSupplier) {
                return "分色";
            } else {
                return "";
            }
        }

        if (status.equals("分色中")){
            if (isSupplier) {
                return "制版";
            } else {
                return "";
            }
        }

        if (status.equals("制版中")){
            if (isSupplier) {
                return "调浆";
            } else {
                return "";
            }
        }

        if (status.equals("调浆中")){
            if (isSupplier) {
                return "打样";
            } else {
                return "";
            }
        }

        if (status.equals("打样中")){
            if (isSupplier) {
                return "品质检查";
            } else {
                return "";
            }
        }

        if (status.equals("打样中")){
            if (isSupplier) {
                return "品质检查";
            } else {
                return "";
            }
        }

        if (status.equals("品质检查")){
            if (isSupplier) {
                return "发货";
            } else {
                return "";
            }
        }

        if (status.equals("代签收")){
            if (isSupplier) {
                return "";
            } else {
                return "确认收货";
            }
        }

        if (status.equals("已签收")){
            if (isSupplier) {
                return "";
            } else {
                return "产品未达标,产品已达标";
            }
        }

        if (status.equals("产品未达标")){
            if (isSupplier) {
                return "";
//                return "重新录入";
            } else {
                return "";
            }
        }

        if (status.equals("产品已达标")){
            if (isSupplier) {
                return "";
//                return "订单录入";
            } else {
                return "";
            }
        }

        return TEMPLET_STATUS_ERROR;
    }

    public static String getTempletStatusByAction(String action){
        if ("分色".equals(action)) {
            return "分色中";
        }
        if ("制版".equals(action)) {
            return "制版中";
        }
        if ("调浆".equals(action)) {
            return "调浆中";
        }
        if ("打样".equals(action)) {
            return "打样中";
        }
        if ("品质检查".equals(action)) {
            return "品质检查";
        }
        if ("发货".equals(action)) {
            return "代签收";
        }
        if ("确认收货".equals(action)) {
            return "代签收";
        }
        if ("产品未达标".equals(action)) {
            return "产品未达标";
        }
        if ("产品已达标".equals(action)) {
            return "产品已达标";
        }

        return TEMPLET_STATUS_ERROR;
    }
}
