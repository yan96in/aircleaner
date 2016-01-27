package cn.somputon.aircleaner.entity;

/**
 * Created on 2016/1/22.
 */
public class CityFromJson {

    /**
     * errNum : 0
     * retMsg : success
     * retData : {"citylist":"安顺"}
     */

    private int errNum;
    private String retMsg;
    /**
     * citylist : 安顺
     */

    private RetDataEntity retData;

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public static class RetDataEntity {
        private String citylist;

        public void setCitylist(String citylist) {
            this.citylist = citylist;
        }

        public String getCitylist() {
            return citylist;
        }
    }
}
