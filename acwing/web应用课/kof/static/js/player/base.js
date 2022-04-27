import { GameObject } from "../game_object/base.js";

export class Player extends GameObject {
    constructor(root, info) {
        super();
        this.root = root;
        this.id = info.id;
        this.x = info.x;
        this.y = info.y;
        this.width = info.width;
        this.height = info.height;
        this.color = info.color;

        this.direction = 1;

        this.vx = 0;
        this.vy = 0;

        this.speedx = 400; //水平速度
        this.speedy = -1000; //跳跃初始速度

        this.gravity = 50; //重力加速度

        this.status = 3; //0: idle,1: 向前,2: 向后,3: 跳跃,4: 攻击,5: 被打,6: 死亡 
        this.animations = new Map();

        this.ctx = this.root.gameMap.ctx;

        this.pressedKeys = this.root.gameMap.controller.pressedKeys;
        this.frameCurrentCnt = 0;

        this.hp = 100;
        this.$hp = this.root.$kof.find(`.kof-head-hp-${this.id}>div`);
        this.$hp_div = this.$hp.find('div');

    }


    start() {

    }

    updateControl() {
        let w, a, d, space;
        if (this.id === 0) {
            w = this.pressedKeys.has('w');
            a = this.pressedKeys.has('a');
            d = this.pressedKeys.has('d');
            space = this.pressedKeys.has(' ');
        } else {
            w = this.pressedKeys.has('ArrowUp');
            a = this.pressedKeys.has('ArrowLeft');
            d = this.pressedKeys.has('ArrowRight');
            space = this.pressedKeys.has('Enter');
        }

        if (this.status === 0 || this.status === 1) {
            if (space) {
                this.status = 4;
                this.vx = 0;
                this.frameCurrentCnt = 0;
            } else if (w) {
                if (d) {
                    this.vx = this.speedx;
                } else if (a) {
                    this.vx = -this.speedx;
                } else {
                    this.vx = 0;
                }
                this.vy = this.speedy;
                this.status = 3;
                this.frameCurrentCnt = 0;
            } else if (d) {
                this.vx = this.speedx;
                this.status = 1;
            } else if (a) {
                this.vx = -this.speedx;
                this.status = 1;
            } else {
                this.vx = 0;
                this.status = 0;
            }
        }
    }

    updateMove() {
        this.vy += this.gravity;

        this.x += this.vx * this.timeDelta / 1000;
        this.y += this.vy * this.timeDelta / 1000;

        if (this.y > 500) {
            this.y = 500;
            this.vy = 0;
            if (this.status === 3) this.status = 0;
        }

        if (this.x < 0) {
            this.x = 0;
        } else if (this.x + this.width > this.root.gameMap.$canvas.width()) {
            this.x = this.root.gameMap.$canvas.width() - this.width;
        }

    }

    updateDirection() {
        if (this.status === 6) return;

        let players = this.root.players;
        // console.log(players);
        if (players[0] && players[1]) {
            let me = this, you = players[1 - this.id];
            if (me.x < you.x) me.direction = 1;
            else me.direction = -1;

        }
    }

    isCollition(r1, r2) {
        if (Math.max(r1.x1, r2.x1) > Math.min(r1.x2, r2.x2)) {
            return false;
        }
        if (Math.max(r1.y1, r2.y1) > Math.min(r1.y2, r2.y2)) {
            return false;
        }
        return true;
    }

    updateAttack() {

        if (this.status === 4 && this.frameCurrentCnt === 18) {
            let me = this, you = this.root.players[1 - this.id];
            let r1;
            if (this.direction > 0)
                r1 = {
                    x1: me.x + 120,
                    y1: me.y + 40,
                    x2: me.x + 120 + 110,
                    y2: me.y + 40 + 30,
                };
            else {
                r1 = {
                    x1: me.x + me.width - 120 - 110,
                    y1: me.y + 40,
                    x2: me.x + me.width - 120 - 110 + 110,
                    y2: me.y + 40 + 30,
                }
            }
            let r2 = {
                x1: you.x,
                y1: you.y,
                x2: you.x + you.width,
                y2: you.y + you.height,
            };
            if (this.isCollition(r1, r2)) you.isAttack();
            // console.log(this.isCollition(r1, r2));
        }
    }

    isAttack() {

        if (this.status === 6) return;
        this.status = 5;
        this.frameCurrentCnt = 0;
        this.hp = Math.max(0, this.hp - 20);

        if (this.hp <= 0) {
            this.status = 6;
            this.frameCurrentCnt = 0;
            this.vx = 0;
        }


        this.$hp_div.animate({
            width: this.$hp.parent().width() * this.hp / 100,
        }, '100');

        this.$hp.animate({
            width: this.$hp.parent().width() * this.hp / 100,
        }, 'slow');


    }

    update() {
        this.updateControl();
        this.updateDirection();
        this.updateMove();
        this.updateAttack();

        this.updateRender();
    }

    updateRender() {


        // this.ctx.fillStyle = this.color;
        // this.ctx.fillRect(this.x, this.y, this.width, this.height);

        // d


        let status = this.status;
        // console.log(this.animations);

        if (this.status === 1 && this.vx * this.direction < 0) status = 2;

        let obj = this.animations.get(status);
        if (obj && obj.loaded) {
            if (this.direction > 0) {
                let k = parseInt(this.frameCurrentCnt / obj.frameRate) % obj.frameCnt;
                let image = obj.gif.frames[k].image;
                this.ctx.drawImage(image, this.x, this.y + obj.offsetY, image.width * obj.scale, image.height * obj.scale);
            } else {
                this.ctx.save();
                this.ctx.scale(-1, 1);
                this.ctx.translate(-this.root.gameMap.$canvas.width(), 0);
                let k = parseInt(this.frameCurrentCnt / obj.frameRate) % obj.frameCnt;
                let image = obj.gif.frames[k].image;
                this.ctx.drawImage(image, this.root.gameMap.$canvas.width() - this.x - this.width, this.y + obj.offsetY, image.width * obj.scale, image.height * obj.scale);

                this.ctx.restore();
            }

        }

        if (status === 4 || status === 5 || status === 6) {
            if (this.frameCurrentCnt === obj.frameRate * (obj.frameCnt - 1)) {
                if (status === 6) this.frameCurrentCnt--;
                else {
                    this.status = 0;
                    this.frameCurrentCnt = 0;
                }
            }
        }

        this.frameCurrentCnt++;
    }

}