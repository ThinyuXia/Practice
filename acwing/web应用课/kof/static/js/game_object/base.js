let GAME_OBJECTS = [];
class GameObject {
    constructor() {
        GAME_OBJECTS.push(this);

        this.timeDelta = 0; //存储当前帧与上一帧的时间间隔
        this.hasCallStart = false;
    }

    start() { //初始执行一次

    }

    update() { //每一帧执行一次(除第一帧以外)

    }

    detroy() { //删除当前对象
        for (let i in GAME_OBJECTS) {
            if (GAME_OBJECTS[i] === this) {
                GAME_OBJECTS.splice(i, 1);
                break;
            }
        }
    }
}

let lastTimeStamp = 0;
let GAME_OBJECTS_FRAME = (timestamp) => {
    for (let obj of GAME_OBJECTS) {
        if (!obj.hasCallStart) {
            obj.start();
            obj.hasCallStart = true;
        } else {
            obj.timeDelta = timestamp - lastTimeStamp;
            obj.update();
        }
    }
    lastTimeStamp = timestamp;
    requestAnimationFrame(GAME_OBJECTS_FRAME);
}

requestAnimationFrame(GAME_OBJECTS_FRAME);

export {
    GameObject
}