export class Controller {
    constructor($convas) {
        this.$convas = $convas;
        this.pressedKeys = new Set();

        this.start();
    }

    start() {
        let outer = this; //为什么设置this？
        this.$convas.keydown(function (e) {
            outer.pressedKeys.add(e.key);
        });

        this.$convas.keyup(function (e) {
            outer.pressedKeys.delete(e.key);
        });
    }

}