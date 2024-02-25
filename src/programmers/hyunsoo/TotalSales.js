/**
 * 음식점에서 하루 동안의 판매 수익을 계산하고자 합니다.
 * 아래 3개의 표는 각각 음식점에서 사용하는 재료, 판매하는 메뉴, 하루 동안의 판매 실적에 대한 정보를 나타내는 예시입니다.
 * 재료 가격
 * r 10
 * a 23
 * t 124
 * k 9
 *
 * 메뉴 이름   필요 재료  판매가  수익
 * PIZZA     arraak   145   47
 * HAMBURGER tkar     180
 * BREAD     kkk      30
 * ICECREAM  rar      50
 * SHAVEDICE rar      45
 * JUICE     rra      55
 * WATER     a        20     -3
 *
 *
 * 메뉴 이름    판매 수량
 * PIZZA         7
 * BREAD         5
 * ICECREAM    100
 * JUICE        10
 * WATER         1
 *
 *
 * 하루 동안 총 수익을 구하시오
 *
 * 제한사항
 * • 1≤ tngs 의 길이 s 26
 *  • tngs 의 각 원소는 " ING_NAME ING_PRICE" 형식입니다.
 *  • ING_MAME 은 재료의 이름을 나타내며, 알파벳 소문자 하나로 표시됩니다.
 *  • ING_PRICE 는 재료의 가격을 나타내는 정수입니다.
 *      • 1 <= ING_PRICE ≤ 1000
 *  • ING_NAME 과 ING_PRICE 는 1개의 공백으로 구분되어 있습니다.
 *  • 1ngs 에서 ING_NAME 은 중복되어 나타나지 않습니다.
 *
 * • 1 <= menu 의 길이 <= 100
 *  • menu 의 각 원소는 " MENU_NAME ING_LIST MENU_PRICE" 형식입니다.
 *  • MENU_NAME 은 식당에서 판매하고 있는 메뉴의 이름을 나타내며, 알파벳 대문자로 구성된 문자열입니다.
 *      • 3 <= MENU_NAME 의 길이 <= 10
 *  • ING_LIST 는 메뉴를 만드는데 필요한 재료들을 나타내는, 알파벳 소문자로 구성된 문자열입니다.
 *      • 1 <= ING_LIST 의 길이 <= 20
 *      • ㅑngs 에 담겨있는 재료들만 ING_LIST 에 나타납니다.
 *  • MENU_PRICE 는 메뉴의 가격을 나타내는 정수입니다.
 */

const solution  = (ings, menu, sell) => {
    const ingsMap = new Map();
    const menuMap = new Map()
    ings.forEach( v => {
        const split = v.split(' ');
        ingsMap.set(split[0], split[1])
    })
    menu.forEach(v => {
        const split = v.split(" ")
        menuMap.set(split[0], split[2] - split[1].split('').reduce((p, n) => {
            return Number(p) + Number(ingsMap.get(n) ?? 0)
        }, 0))
    })


    return sell.reduce((p, n ) => {
        const split = n.split(' ')
        return p + (Number(menuMap.get(split[0])) * Number(split[1]))
    }, 0 )
}

console.log("result:: ", solution([ "r 10", "a 23", "t 124", "k 9" ], [ "PIZZA arraak 145", "HAMBURGER tkar 100", "BREAD kkk 30", "ICECREAM rar 50", "SHAVEDICE rar 45", "JUICE rra 55", "WATER a 20" ], [ "BREAD 5", "ICECREAM 100", "PIZZA 7", "JUICE 10", "WATER 1" ]));
console.log("result:: ", solution([ "x 25", "y 20", "z 1000" ], [ "AAAA xyxy 15", "TTT yy 30", "BBBB xx 30" ], [ "BBBB 3", "TTT 2" ]));