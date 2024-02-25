/**
 * 주어진 메일 주소 중, 규격에 맞는 주소는 몇 개인지 구하려 합니다. 메일 규격은 다음과 같습니다.
 *  • 규격에 맞는 메일은 이름 `도메인이름.탑 레벨 도메인` 형식입니다.
 *  • 이름은 영문 소문자와 . 로만 구성되며 길이가 1 이상이어야 합니다.1
 *  • 도메인이름은 영문 소문자로만 구성되며 길이가 1 이상이어야 합니다.
 *  • 탑레벨도메인은 "com', "net"', "org" 중 하나입니다.
 * 메일 주소를 담은 배열 emails가 매개변수로 주어질 때
 * 이 중 규격에 맞는 주소는 몇 개인지 return
 * 하도록 Solution 함수를 완성해주세요.
 *
 * 제한사항
 *  • emalls의 길이는 1 이상 100,000 이하입니다.
 *  • emalls의 원소는 길이가 1이상 100이하인 문자열입니다.
 *  • emals의 원소는 영문 소문자, 0로 이루어진 문자열입니다
 */

const solution = (emails) => {
    return emails
        .filter(elem => {
            return /^([a-z.]{1,})\@[a-z]{1,}.(com|org|net)$/gis.test(elem)
        })
        .length
}

console.log(3, solution(["d@co@m.com", "a@abc.com", "b@def.com", "c@ghi.net"]))
console.log(1, solution(["abc.def@x-com", "abc", "abc@defx", "abc@defx.xyz"]))
