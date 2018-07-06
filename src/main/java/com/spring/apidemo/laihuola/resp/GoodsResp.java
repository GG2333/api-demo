package com.spring.apidemo.laihuola.resp;

public class GoodsResp {

    /*货源*/

    public String id;//订单编号

    public String orderNumber;//订单短编号

    public String creationTime;

    public String arrivalTime;//到货时间

    public String deliveryTime;//发货时间

    public String releaseTime;//发布时间

    public String startingPlaceCode;//起始地编码

    public String startingPlaceName;//起始地名称

    public String destinationPlaceCode;//目的地编码

    public String destinationName;//目的地名称

    public String destinationPlaceDetailName;

    public double amount;//货量

    public String amountUnit;//货量单位

    public String category;//货类（货物名称）

    public String carLength;//车长

    public String contacts;//客服

    public String contactsChatId;//客服融云编号

    public String contactPhoneNumber;//客服手机号码

    public String dispatcherHead;

    public boolean isFocus;//是否为关注路线订单

    public int orderSuppliedState;//货源状态: 1未抢 && 2已抢

    public String phoneNumber;//手机号 ???

    public String remarks;//备注

    public int type;//订单类型-整单零担

    public int state;//订单状态

    public double orderToDriver_Length;

    /*已抢*/

    public int orderGrabbedState;//抢单状态

    public int vehicleType;//车型

    public int grabNum;//报价次数

    public double price;//有效报价

    public int unit;//有效价格单位

    public String grabComment;//有效报价说明

    public double priceInvalid;//无效报价

    public int unitInvalid;//无效价格单位

    public String grabCommentInvalid;//无效报价说明

    public double quotationRate;//报价成功率

    public int quotationPeopleNumber;//抢单人数

    /*运单*/

    public String carrierOrderId;//承运单

    public int drawableCostCount;//可提现费用数量

}
