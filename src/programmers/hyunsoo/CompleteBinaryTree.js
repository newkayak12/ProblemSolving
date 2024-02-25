/**
 * 완전 이진트리(complete binary tree)를 사용하여 메시지를 암호화하려고 합니다.
 * 메시지를 암호화하는 방법은 아래와 같습니다.
 *
 *  1. 완전 이진트리에 메시지를 한 글자씩 순서대로 추가 합니다.
 *  2. 한 글자를 추가한 상태 또한 완전 이진트리가 되어야 합니다.
 *  3. 완전 이진트리를 루트 노드부터 후위 순회(post-order traversal)하여 한 글자씩 이어 붙입니다.
 *
 * 암호화할 메시지 message가 주어질 때, message를 암 호화한 결과를 return 하는 solution 함수를 완성해주세요.
 *
 *
 *  제한사항
 *  • 메시지는 1글자 이상 50,000글자 이하입니다.
 *  • 메시지는 영어 대문자로만 이루어져 있습니다.
 *
 * ex)
 *  |message | return |
 *  |:----:|:-----:|
 *  | "ABCDEF" | "DEBFCA"|
 *
 *          A
 *         ╱  ╲
 *        ╱    ╲
 *      B       C
 *    ╱   ╲    ╱
 *   D     E  F
 *
 *
 *   왼족 서브 - 오른쪽 서브 - 노드 순서로 방문하면
 *   'DEBFCA'가 나온다.
 */


const solution = message => {
    const messageArr = message.split('')
    const complete = tree(messageArr)
}
const tree = (messageArr = []) => {
    const complete = []
    for ( let i = 0; i < messageArr.length; i ++ ) {
        if( i < 1 ) complete.push({root:null, left: i*2 + 1, right: i * 2 + 2, elem : messageArr[i]})
        else {
            let left = messageArr.length  > i * 2 + 1 ? i * 2 + 1 : null
            let right = messageArr.length  > i * 2 + 2 ? i * 2 + 2 : null
            complete.push({root: Math.floor((i - 1) / 2), left, right, elem: messageArr[i]})
        }
    }
    return complete
}
const journey = (complete, idx = 0) => {
    let returnValue = ''
    if( complete[idx].left ) returnValue += journey(complete, complete[idx].left)
    if( complete[idx].right ) returnValue += journey(complete, complete[idx].right)
    returnValue += complete[idx].elem

    return returnValue;
}

console.log(solution("ABCDEF")) //DEBFCA