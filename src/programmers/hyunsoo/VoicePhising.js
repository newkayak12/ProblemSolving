/**
 * 어떤 은행에서는 고객들이 송금할 때, 다음의 두 가지 조건 중 한 가지 이상 해당하면
 * 보이스피싱 주의 문자를 발송합니다.
 * [문자 발송 조건]
 * 1. 같은 사람에게 K 번 이상 연속으로 송금할 때
 *      • 사람 이름을 비교할 때, 대소문자는 구분하지 않습니다.
 *
 * 2. 송금액이 m 원 이상일 때
 * 아래의 표는 k = 3 = 50000 인 경우에 고객들의 송금 내역에 따른 문자 발송 결과입니다.
 *
 *
 * |받는 사람 이름|금액|결과
 * |:---:|:---:|:---:|
 * |mSLEE|950||
 * |jsKim|52524|송금액이 50000원 이상이므로, 문자 발송|
 * |ISKIM|1400||
 * |IskiM|6055|3번 연속으로 같은 사람에게 송금하므로, 문자 발송|
 * |jskim|10000|4번 연속으로 같은 사람에게 송금하므로, 문자 발송|
 * |John|4512||
 * |john|512||
 * |John|52000|두 가지 조건 모두 해당하므로, 문자 발송|
 * |MSLEE|9000||
 * |MSLEE|49000||
 * |ISKIM|1400||
 * |roy|50000|송금액이 50000원 이상이므로, 문자 발송|
 * • 위의 예시에서는 총 5회 문자가 발송됩니다.
 *
 * 고객들에게 문자를 발송하는 기준이 되는 정수 K 와 n,
 * 송금을 받는 사람의 이름을 담은 문자열 배열 names,
 * 송금액을 담은 정수 배열 amounts 가 매개변수로 주어집니다.
 *
 * 이때, 문자가 발송되는 횟수를 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * • 23 k s10
 * • 1≤ m <= 1,000,000
 * • 1 <= names 의 길이 <= 1,000
 *  • 3 <= names 의 원소의 길이 <= 10
 *  • names 의 원소는 알파벳 소문자와 알파벳 대문자로만 이루어진 문자열입니다.
 * • amounts 의 길이 = names 의 길이
 *  • 1 <= anounts 의 원소 <= 1,000,000
 * names[i] 와 amounts[i] 는 i+1 번째 송금 내역에서 받는 사람 이름과 송금액을 의미합니다.
 */

const solution = (k, m, names, amounts) => {
    let arr = []
   let count = 0;
   for (let i = 0; i < amounts.length; i ++ ) {
       let send = false
       const name = names[i]
       const amount = amounts[i]
       const lower = name.toLowerCase()

       if( !arr.includes(lower) ) arr = [lower]
       else {
           arr.push(lower)
           if( arr.length >= k) send |= true;
       }

       if ( amount >= m) send |= true

       if( send ) count +=1
   }

   return count

}

console.log(5, solution(3, 50000,
    [
        "mSLEE",
        "jsKim",
        "jsKIM",
        "jskiM",
        "jskim",
        "John",
        "john",
        "John",
        "msLEE",
        "msLEE",
        "jsKIM",
        "roy"
    ],
    [
        950,
        52524,
        1400,
        6055,
        10000,
        4512,
        512,
        52000,
        9000,
        49000,
        1400,
        50000
    ]))
console.log(3, solution(2, 3451, [
    'abcd',
    'aBCd',
    'jsKIM',
    'rrr',
    'rrr'],
    [950,
        1000,
        1400,
        4000,
        10000]))




