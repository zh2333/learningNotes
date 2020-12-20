package com.interview.enums;

public  enum CountyEnum {
    ONE(1, "齐国"), TWO(2, "楚国"),THREE(3, "燕国"),FOUR(4 ,"赵国"),FIVE(5, "韩国");

    private Integer retcode;
    private String retMsg;

    CountyEnum(Integer retcode, String retMsg) {
        this.retcode = retcode;
        this.retMsg = retMsg;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public static CountyEnum forEach_CountryEnum(int index) {
        CountyEnum[] myArray = CountyEnum.values();
        for (CountyEnum element: myArray) {
            if (index == element.getRetcode()) {
                return element;
            }
        }
        return null;
    }
}
