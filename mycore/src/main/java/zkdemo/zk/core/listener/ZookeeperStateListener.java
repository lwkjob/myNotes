//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zkdemo.zk.core.listener;

public interface ZookeeperStateListener {
    void stateChanged(ZookeeperStateListener.State var1);

    public static enum State {
        DISCONNECTED(0),
        CONNECTED(1),
        RECONNECTED(2);

        int code;

        private State(int code) {
            this.code = code;
        }

        public int code() {
            return this.code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
