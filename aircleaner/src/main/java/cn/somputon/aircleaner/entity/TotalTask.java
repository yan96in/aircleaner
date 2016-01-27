package cn.somputon.aircleaner.entity;

/**

 */
public class TotalTask {

    /**
     * Comment : {"t1000times":0,"t100times":0,"t10times":0,"t2000times":0,"t200times":0,"t5000times":0,"t500times":0,"t50times":0,"userId":132}
     * Zan : {"t1000times":0,"t100times":0,"t10times":0,"t2000times":0,"t200times":0,"t5000times":0,"t500times":0,"t50times":0,"userId":132}
     * Sign : {"t100day":0,"t30day":0,"t365day":0,"t7day":0,"userId":132}
     * Share : {"t1000times":0,"t100times":0,"t10times":0,"t2000times":0,"t200times":0,"t5000times":0,"t500times":0,"t50times":0,"userId":132}
     * ShareFriend : {"t1000times":0,"t100times":0,"t10times":0,"t2000times":0,"t200times":0,"t5000times":0,"t500times":0,"t50times":0,"userId":132}
     */

    private BaseModleEntity BaseModle;
    /**
     * BaseModle : {"Comment":{"t1000times":0,"t100times":0,"t10times":0,"t2000times":0,"t200times":0,"t5000times":0,"t500times":0,"t50times":0,"userId":132},"Zan":{"t1000times":0,"t100times":0,"t10times":0,"t2000times":0,"t200times":0,"t5000times":0,"t500times":0,"t50times":0,"userId":132},"Sign":{"t100day":0,"t30day":0,"t365day":0,"t7day":0,"userId":132},"Share":{"t1000times":0,"t100times":0,"t10times":0,"t2000times":0,"t200times":0,"t5000times":0,"t500times":0,"t50times":0,"userId":132},"ShareFriend":{"t1000times":0,"t100times":0,"t10times":0,"t2000times":0,"t200times":0,"t5000times":0,"t500times":0,"t50times":0,"userId":132}}
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
        /**
         * t1000times : 0
         * t100times : 0
         * t10times : 0
         * t2000times : 0
         * t200times : 0
         * t5000times : 0
         * t500times : 0
         * t50times : 0
         * userId : 132
         */

        private CommentEntity Comment;
        /**
         * t1000times : 0
         * t100times : 0
         * t10times : 0
         * t2000times : 0
         * t200times : 0
         * t5000times : 0
         * t500times : 0
         * t50times : 0
         * userId : 132
         */

        private ZanEntity Zan;
        /**
         * t100day : 0
         * t30day : 0
         * t365day : 0
         * t7day : 0
         * userId : 132
         */

        private SignEntity Sign;
        /**
         * t1000times : 0
         * t100times : 0
         * t10times : 0
         * t2000times : 0
         * t200times : 0
         * t5000times : 0
         * t500times : 0
         * t50times : 0
         * userId : 132
         */

        private ShareEntity Share;
        /**
         * t1000times : 0
         * t100times : 0
         * t10times : 0
         * t2000times : 0
         * t200times : 0
         * t5000times : 0
         * t500times : 0
         * t50times : 0
         * userId : 132
         */

        private ShareFriendEntity ShareFriend;

        public void setComment(CommentEntity Comment) {
            this.Comment = Comment;
        }

        public void setZan(ZanEntity Zan) {
            this.Zan = Zan;
        }

        public void setSign(SignEntity Sign) {
            this.Sign = Sign;
        }

        public void setShare(ShareEntity Share) {
            this.Share = Share;
        }

        public void setShareFriend(ShareFriendEntity ShareFriend) {
            this.ShareFriend = ShareFriend;
        }

        public CommentEntity getComment() {
            return Comment;
        }

        public ZanEntity getZan() {
            return Zan;
        }

        public SignEntity getSign() {
            return Sign;
        }

        public ShareEntity getShare() {
            return Share;
        }

        public ShareFriendEntity getShareFriend() {
            return ShareFriend;
        }

        public static class CommentEntity {
            private int t1000times;
            private int t100times;
            private int t10times;
            private int t2000times;
            private int t200times;
            private int t5000times;
            private int t500times;
            private int t50times;
            private int userId;

            public void setT1000times(int t1000times) {
                this.t1000times = t1000times;
            }

            public void setT100times(int t100times) {
                this.t100times = t100times;
            }

            public void setT10times(int t10times) {
                this.t10times = t10times;
            }

            public void setT2000times(int t2000times) {
                this.t2000times = t2000times;
            }

            public void setT200times(int t200times) {
                this.t200times = t200times;
            }

            public void setT5000times(int t5000times) {
                this.t5000times = t5000times;
            }

            public void setT500times(int t500times) {
                this.t500times = t500times;
            }

            public void setT50times(int t50times) {
                this.t50times = t50times;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getT1000times() {
                return t1000times;
            }

            public int getT100times() {
                return t100times;
            }

            public int getT10times() {
                return t10times;
            }

            public int getT2000times() {
                return t2000times;
            }

            public int getT200times() {
                return t200times;
            }

            public int getT5000times() {
                return t5000times;
            }

            public int getT500times() {
                return t500times;
            }

            public int getT50times() {
                return t50times;
            }

            public int getUserId() {
                return userId;
            }
        }

        public static class ZanEntity {
            private int t1000times;
            private int t100times;
            private int t10times;
            private int t2000times;
            private int t200times;
            private int t5000times;
            private int t500times;
            private int t50times;
            private int userId;

            public void setT1000times(int t1000times) {
                this.t1000times = t1000times;
            }

            public void setT100times(int t100times) {
                this.t100times = t100times;
            }

            public void setT10times(int t10times) {
                this.t10times = t10times;
            }

            public void setT2000times(int t2000times) {
                this.t2000times = t2000times;
            }

            public void setT200times(int t200times) {
                this.t200times = t200times;
            }

            public void setT5000times(int t5000times) {
                this.t5000times = t5000times;
            }

            public void setT500times(int t500times) {
                this.t500times = t500times;
            }

            public void setT50times(int t50times) {
                this.t50times = t50times;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getT1000times() {
                return t1000times;
            }

            public int getT100times() {
                return t100times;
            }

            public int getT10times() {
                return t10times;
            }

            public int getT2000times() {
                return t2000times;
            }

            public int getT200times() {
                return t200times;
            }

            public int getT5000times() {
                return t5000times;
            }

            public int getT500times() {
                return t500times;
            }

            public int getT50times() {
                return t50times;
            }

            public int getUserId() {
                return userId;
            }
        }

        public static class SignEntity {
            private int t100day;
            private int t30day;
            private int t365day;
            private int t7day;
            private int userId;

            public void setT100day(int t100day) {
                this.t100day = t100day;
            }

            public void setT30day(int t30day) {
                this.t30day = t30day;
            }

            public void setT365day(int t365day) {
                this.t365day = t365day;
            }

            public void setT7day(int t7day) {
                this.t7day = t7day;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getT100day() {
                return t100day;
            }

            public int getT30day() {
                return t30day;
            }

            public int getT365day() {
                return t365day;
            }

            public int getT7day() {
                return t7day;
            }

            public int getUserId() {
                return userId;
            }
        }

        public static class ShareEntity {
            private int t1000times;
            private int t100times;
            private int t10times;
            private int t2000times;
            private int t200times;
            private int t5000times;
            private int t500times;
            private int t50times;
            private int userId;

            public void setT1000times(int t1000times) {
                this.t1000times = t1000times;
            }

            public void setT100times(int t100times) {
                this.t100times = t100times;
            }

            public void setT10times(int t10times) {
                this.t10times = t10times;
            }

            public void setT2000times(int t2000times) {
                this.t2000times = t2000times;
            }

            public void setT200times(int t200times) {
                this.t200times = t200times;
            }

            public void setT5000times(int t5000times) {
                this.t5000times = t5000times;
            }

            public void setT500times(int t500times) {
                this.t500times = t500times;
            }

            public void setT50times(int t50times) {
                this.t50times = t50times;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getT1000times() {
                return t1000times;
            }

            public int getT100times() {
                return t100times;
            }

            public int getT10times() {
                return t10times;
            }

            public int getT2000times() {
                return t2000times;
            }

            public int getT200times() {
                return t200times;
            }

            public int getT5000times() {
                return t5000times;
            }

            public int getT500times() {
                return t500times;
            }

            public int getT50times() {
                return t50times;
            }

            public int getUserId() {
                return userId;
            }
        }

        public static class ShareFriendEntity {
            private int t1000times;
            private int t100times;
            private int t10times;
            private int t2000times;
            private int t200times;
            private int t5000times;
            private int t500times;
            private int t50times;
            private int userId;

            public void setT1000times(int t1000times) {
                this.t1000times = t1000times;
            }

            public void setT100times(int t100times) {
                this.t100times = t100times;
            }

            public void setT10times(int t10times) {
                this.t10times = t10times;
            }

            public void setT2000times(int t2000times) {
                this.t2000times = t2000times;
            }

            public void setT200times(int t200times) {
                this.t200times = t200times;
            }

            public void setT5000times(int t5000times) {
                this.t5000times = t5000times;
            }

            public void setT500times(int t500times) {
                this.t500times = t500times;
            }

            public void setT50times(int t50times) {
                this.t50times = t50times;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getT1000times() {
                return t1000times;
            }

            public int getT100times() {
                return t100times;
            }

            public int getT10times() {
                return t10times;
            }

            public int getT2000times() {
                return t2000times;
            }

            public int getT200times() {
                return t200times;
            }

            public int getT5000times() {
                return t5000times;
            }

            public int getT500times() {
                return t500times;
            }

            public int getT50times() {
                return t50times;
            }

            public int getUserId() {
                return userId;
            }
        }
    }
}
