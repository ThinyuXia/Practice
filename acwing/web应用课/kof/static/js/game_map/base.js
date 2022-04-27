import { GameObject } from "../game_object/base.js";
import { Controller } from "../controller/base.js";


export class GameMap extends GameObject {
    constructor(root) {
        super();
        this.root = root;
        this.$canvas = $('<canvas width="1420px" height="800px" tabindex=0></canvas>'); //tabindex使canvas聚焦接收键盘输入
        this.ctx = this.$canvas[0].getContext('2d');
        this.root.$kof.append(this.$canvas);
        this.$canvas.focus();
        this.controller = new Controller(this.$canvas);

        this.root.$kof.append($(`
        <div class="kof-head">
            <div class="kof-head-hp-0">
                <div><div></div></div>
            </div>
            <div class="kof-head-timer">60</div>
            <div class="kof-head-hp-1">
                <div><div></div></div>
            </div>
        </div>`));

        this.timeleft = 60000;  //单位 ms
        this.$timer = this.root.$kof.find(".kof-head-timer")
    }

    start() {

    }

    update() {
        this.timeleft -= this.timeDelta;
        if (this.timeleft < 0) {
            this.timeleft = 0;
            let [a, b] = this.root.players;
            if (a.status !== 6 && b.status !== 6) {
                a.status = 6;
                b.status = 6;
                a.frameCurrentCnt = 0;
                b.frameCurrentCnt = 0;
                a.vx = b.vx = 0;
            }
        }
        this.$timer.text(parseInt(this.timeleft / 1000));

        this.render();
    }

    render() {
        this.ctx.clearRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
        // this.ctx.fillStyle = "black";
        // this.ctx.fillRect(0, 0, this.$canvas.width(), this.$canvas.height());
    }
}