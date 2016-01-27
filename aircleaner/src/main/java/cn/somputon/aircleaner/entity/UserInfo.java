package cn.somputon.aircleaner.entity;

/**
 */
public class UserInfo {

    /**
     * UserId : 132
     * NickName : null
     * Mobile : 15532118101
     * Avatar : /storage/emulated/0/Download/anion.png
     */

    private BaseModleEntity BaseModle;
    /**
     * BaseModle : {"UserId":132,"NickName":null,"Mobile":"15532118101","Avatar":"/storage/emulated/0/Download/anion.png"}
     * Statu : 1
     * StatusReson : 调用成功
     */

    private String Statu;
    private String StatusReson;

    public void setBaseModle(BaseModleEntity BaseModle) {
        this.BaseModle = BaseModle;
    }

    public void setStatu(String Statu) {
        this.Statu = Statu;
    }

    public void setStatusReson(String StatusReson) {
        this.StatusReson = StatusReson;
    }

    public BaseModleEntity getBaseModle() {
        return BaseModle;
    }

    public String getStatu() {
        return Statu;
    }

    public String getStatusReson() {
        return StatusReson;
    }

    public static class BaseModleEntity {
        private int UserId;
        private String NickName;
        private String Mobile;
        private String Avatar;

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public void setAvatar(String Avatar) {
            this.Avatar = Avatar;
        }

        public int getUserId() {
            return UserId;
        }

        public String getNickName() {
            return NickName;
        }

        public String getMobile() {
            return Mobile;
        }

        public String getAvatar() {
            return Avatar;
        }
    }
}
