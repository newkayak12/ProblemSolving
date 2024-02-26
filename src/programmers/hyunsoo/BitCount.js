/**
 * slvd
 * 자연수 n을 이진법으로 변환했을 때 나오는 1의 개수를 k라고 했을 떄,
 * n보다 작은 자연수 중에서 이진법으로 변환하여
 * 1의 개수가 k인 수가 몇 개 있는지를 return 하도록
 * solution을 완성해라
 *
 * 제한사항
 *  - n 은 1<= n <= 2^30인 자연수
 *
 */

const solution = n => {
    const count = (toBitLength(n))
    let answer = 0;
    for( let i = 1; i < n; i ++ ) {
        if((toBitLength(i)) === count ) answer += 1
        // console.log(i)
    }

    return answer
}
const toBitLength = (s = 0) => {
    let count = 0;
    while (s) {
        count += s & 1;
        s >>= 1;
    }
    return count;
}
const solutionComplete = (n) => {
    let lim = 0;
    const countBits = (s, flag) => {
        let count = 0;
        while (s) {
            count += s & 1;
            s >>= 1;
            if (!flag && count > lim) return false;
        }
        if (flag) lim = count;
        return count;
    };

    const tempCount = countBits(n, true); //2
    let answer = 0;
    for (let i = 0; i < n; i++) {
        const res = countBits(i, false);
        if (!res) continue;
        if (res !== tempCount) continue;
        answer++;
    }

    return answer;
};

let st = new Date().getTime()
console.log("result:: ", solution(Math.pow(2, 20)));
console.log(new Date().getTime() - st)
st = new Date().getTime()
console.log("result:: ", solutionComplete(Math.pow(2, 20)));
console.log(new Date().getTime() - st)