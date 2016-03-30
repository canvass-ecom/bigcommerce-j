/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnv.bigcom;

/**
 *
 * @author Naga Srinivas @Canvass
 */
public class WebhookEvents {

    public static enum Order {

        CREATED("store/order/created"),
        UPDATED("store/order/updated");

        private Order(String path) {
            this.path = path;
        }
        private String path;
    }

    public static enum App {

        UNINSTALLED("store/app/uninstalled");

        private App(String path) {
            this.path = path;
        }
        private String path;
    }

    public static void main(String[] args) {
        System.out.println(WebhookEvents.App.UNINSTALLED.path);
    }
}
