import { Player } from "./base.js";
import { GIF } from "../utils/gif.js";

export class Kyo extends Player {
    constructor(root, info) {
        super(root, info);
        this.initAnimations();
    }

    initAnimations() {
        let outer = this;
        let offsets = [0, -22, -22, -140, 0, 0, 0];
        for (let i = 0; i < 7; i++) {
            let gif = GIF();
            gif.load(`/kof/static/images/player/kyo/${i}.gif`);
            this.animations.set(i, {
                gif: gif,
                frameCnt: 0,
                frameRate: 5,
                offsetY: offsets[i],  //竖直方向偏移量
                loaded: false,
                scale: 2,
            });

            gif.onload = function () {
                let obj = outer.animations.get(i);
                obj.frameCnt = gif.frames.length;
                obj.loaded = true;
                if(i === 3){
                    obj.frameRate = 4;
                }
            }
        }
    }
}