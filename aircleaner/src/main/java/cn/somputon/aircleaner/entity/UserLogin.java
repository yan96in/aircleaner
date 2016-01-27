package cn.somputon.aircleaner.entity;
/**
 */
public class UserLogin {

    /**
     * SaiyiPwd : null
     * Token : 4194befa-7ddd-41d1-8ea2-517bc3cae54c
     * SaiyiUser : null
     */

    private BaseModleEntity BaseModle;
    /**
     * BaseModle : {"SaiyiPwd":null,"Token":"4194befa-7ddd-41d1-8ea2-517bc3cae54c","SaiyiUser":null}
     * Statu : 1
     * StatusReson : 登陆成功
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
        private Object SaiyiPwd;
        private String Token;
        private Object SaiyiUser;

        public void setSaiyiPwd(Object SaiyiPwd) {
            this.SaiyiPwd = SaiyiPwd;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public void setSaiyiUser(Object SaiyiUser) {
            this.SaiyiUser = SaiyiUser;
        }

        public Object getSaiyiPwd() {
            return SaiyiPwd;
        }

        public String getToken() {
            return Token;
        }

        public Object getSaiyiUser() {
            return SaiyiUser;
        }
    }
}
