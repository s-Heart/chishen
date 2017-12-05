package demo.com.cssdk;

/**
 * Author: shishaojie
 * Date: 2017/11/30 0030 12:01
 * Description:
 */
public class Juzi {

    /**
     * coreType : en.sent.score
     * refText :  I want to know the past and present of Hong Kong.
     * rank : 100
     * userId : xxx
     * attachAudioUrl : 1
     * result : {"details":{"raw":1,"sym":1}}
     */

    private String coreType;
    private String refText;
    private int rank;
    private String userId;
    private int attachAudioUrl;
    private ResultBean result;

    public String getCoreType() {
        return coreType;
    }

    public void setCoreType(String coreType) {
        this.coreType = coreType;
    }

    public String getRefText() {
        return refText;
    }

    public void setRefText(String refText) {
        this.refText = refText;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAttachAudioUrl() {
        return attachAudioUrl;
    }

    public void setAttachAudioUrl(int attachAudioUrl) {
        this.attachAudioUrl = attachAudioUrl;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * details : {"raw":1,"sym":1}
         */

        private DetailsBean details;

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public static class DetailsBean {
            /**
             * raw : 1
             * sym : 1
             */

            private int raw;
            private int sym;

            public int getRaw() {
                return raw;
            }

            public void setRaw(int raw) {
                this.raw = raw;
            }

            public int getSym() {
                return sym;
            }

            public void setSym(int sym) {
                this.sym = sym;
            }
        }
    }



}
