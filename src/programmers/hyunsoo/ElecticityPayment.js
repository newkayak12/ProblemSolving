/**
 * slvd
 * 전기 요금표와 사용한 전력량이 주어졌을 때, 납부해야할 요금을 구하려고 한다.
 *  예시)
 *
 *  |전력량 구간 | 기본 요금 | 전력량 요금|
 *  |:---:|:----:|:----:|
 *  |200 이하| 910| 93|
 *  |200 초과 400 이하| 1600| 188|
 *  |400초과 655이하| 7300| 281|
 *  |655 초과| 15372| 435|
 *
 *
 *  사용한 전력량이 320면
 *  기본 요금                      = 1800
 *  200 이하 (200 * 93)           = 18600
 *  200 초과 400 이하 (120 * 188)  = 22560
 *  --------------------------------------
 *  총                             42760
 *
 *  사용한 전력량이 320면
 *  기본 요금                      = 7300
 *  200 이하 (200 * 93)           = 18600
 *  200 초과 400 이하 (200 * 188)  = 37600
 *  400 초과 (200 * 188)          = 14050
 *  --------------------------------------
 *  총                             77550
 */
const solution = ( fees, usage ) => {
    const baseTable = [{usage: 0, base:0}]
    const feeTable = [{usage: 0, base:0}]
    for ( const fee of fees ) {
        baseTable.push({usage:fee[0] == 0? Number.MAX_VALUE : fee[0], base: fee[1]})
        feeTable.push({usage:fee[0] == 0? Number.MAX_VALUE : fee[0], base: fee[2]})
    }

    let base = 0;
    let fee = 0;
    let usageCopy = usage

    for (let i = 1; i < baseTable.length && usageCopy > 0; i ++ ) {
        let usageNow = 0;
        if( base == 0 && baseTable[i - 1].usage < usage && usage <= baseTable[i].usage) base = baseTable[i].base
        if ( feeTable[i].usage <= usage ) usageNow = (feeTable[i].usage - feeTable[i-1].usage);
        if ( feeTable[i].usage > usage ) usageNow = usageCopy
        usageCopy -= (feeTable[i].usage - feeTable[i - 1].usage)
        fee += usageNow * feeTable[i].base
    }
    return fee + base
}



console.log("result:: ", solution([ [ 200, 910, 93 ], [ 400, 1600, 188 ], [ 655, 7300, 281 ], [ 0, 15372, 435 ] ], 320)); // 42760
console.log("result:: ", solution([ [ 200, 910, 93 ], [ 400, 1600, 188 ], [ 655, 7300, 281 ], [ 0, 15372, 435 ] ], 450)); //77550
console.log("result:: ", solution([ [ 1851, 1000, 100 ], [ 0, 2000, 155 ] ], 1205)); //121500
console.log("result:: ", solution([ [ 100, 415, 90 ], [ 250, 1600, 389 ], [ 0, 7000, 480 ] ], 530)); //208750