public class PingPong {

    private boolean sent = false;

    private void ping() {
        sent = true;
        System.out.println("ping");
    }

    private void pong() throws InterruptedException {
        while (sent == false) {
            Thread.sleep(100);
        }
        System.out.println("pong");
    }

    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    new AssertionError(e);
                }
                pingPong.ping();
            }

        }).start();
        pingPong.pong();
    }

}
