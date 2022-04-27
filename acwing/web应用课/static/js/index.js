let input = document.querySelector(".input");
let run = document.querySelector(".run");
let output = document.querySelector(".output");
function main() {
    // run.addEventListener("click", function () {
    //     let s = input.value;
    //     output.innerHTML = s;
    //     let a = [2, 1, 4, 3, 0, "6"];
    //     a.sort(function (a, b) {
    //         return b - a;
    //     });

    //     let f1 = () => {
    //         return (1);
    //     }
    //     console.log(f1(10));
    // });
    // let p = new Point(1,2);
    // console.log(p.toString());
    
}

class Point {
    constructor(x,y){
        this.x = y;
        this.y = y;
    }

    init(){
        this.sum = this.x + this.y;
    }

    toString(){
        return "(" + this.x + "," + this.y + ")";
    }

}

export {
    main
}