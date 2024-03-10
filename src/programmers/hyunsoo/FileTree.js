/**
 * slvd
 * hard - 2
 * N개의 디렉토리가 다음과 같이 트리 형태의 간단한 구조를 이루고 있다.
 * - 모든 디렉토리에는 1 ~ N 번호 있음
 * - 모든 디렉토리에는 이름이 있음
 * - 최상위 디렉토리는 1개, 최상위 디렉토리 번호는 1
 * - 모든 디렉토리는 0개 이상의 하위 디렉토리를 가질 수 있다.
 * - 최상위 디렉토리를 제외한 나머지는 정확히 1개의 상위 디렉토리를 가진다.
 * - 모든 디렉토리는 같은 이름의 하위 디렉토리를 2개 이상 가질 수 없다.
 *
 *  1 root
 *      ᄂ  2 abcd
 *      │       ᄂ 5 etc
 *      │       ᄂ 6 hello
 *      ᄂ 3 cs
 *      │     ᄂ 7 solution
 *      ᄂ4 hello
 *
 * 각 디렉토리 위치는 절대 경로를 사용해서 표현한다.
 * 1) root
 * 2) root/abcd
 * 3) root/css
 * 4) root/hello
 * 5) root/abcd/etc
 * 6) root/abcd/hello
 * 7) root/cs/solution
 *
 * 가장 긴 경로( 가장 많은 문자를 사용한) 경로는 7) root/cs/solution으로 16개 문자를 사용했다.
 * 전체 디렉토리의 개수 N, 관계 배열 relation, 디렉토리 이름이 순서대로 있는 배열 dirname이 있을 때
 * 가장 긴 경로를 완성하시오
 *
 * 지인사항
 * • 디렉토리의 개수 N은 2 이상 100,000 이하의 자연수입니다.
 * • relation은 디렉토리의 관계를 담고 있는 내영이여 깊이는 N - 1입니다
 * • relation의 각 요소는 디렉토리의 경계를 나타내는 길어가 2인 배열에이, 손시대로 (상위 디역토리 번호, 해쉬 다역모리 번호)를 나타냅니다.
 * • 예를 붙어 다이도리 권계가 12,5도 주어진다면 2번 디렉토리의 하위 디렉토리에 5번 디렉토리의 있다는 의미합니다.
 * •디역모리의 관계는 한 번안 들어있습니다.
 * • 항상 원너은 디디토리 경계인 업력으로 주어니다.
 * amanx은 1번 디디토리부터 N번 디역도리까지 각 디티토리의 이용을 순시대로 담고 있는 비입니다.
 * • 한 디도리가 같은 이용의 허위 디토리를 주거 이상 가지는 경우는 주어지지 않습니다.
 * • 모든 다도리 이름은 살바껏 소유자한 어부어져 있으며, 김는 1이상 255어입니다
 */
const solution = (n, relation, dirname) => {
    const dir = []

    for (let i = 0; i < n; i ++) {
        dir[i] = {elem: dirname[i], children:[], parent:[]}
    }


    for (let rel of relation) {
        dir[ rel[0] - 1].children.push(rel[1] - 1)
        dir[ rel[1] - 1].parent.push(rel[0] - 1)
    }


    return search(dir, 0).length
}

const search = (dir,idx) => {
    const node = dir[idx];
    let elem = ''

    /***
     *
     *  1root
     *    ᄂ  2 abcd
     *    │       ᄂ 5 etc
     *    │       ᄂ 6 hello
     *    ᄂ 3 cs
     *    │     ᄂ 7 solution
     *    ᄂ4 hello
     *
     */

    if( node.children.length === 0) return node.elem

    for( let child of node.children ) {
        const value = node.elem+'/'+search(dir, child)
        if( elem.length < value.length ) {
            elem = value;
        }

        console.log(value)
    }


    return elem
}

console.log(16, solution(7, [ [ 1, 2 ], [ 1, 3 ], [ 1, 4 ], [ 2, 5 ], [ 2, 6 ], [ 3, 7 ] ], [ "root", "abcd", "cs", "hello", "etc", "hello", "solution" ]))
console.log(16, solution(8, [ [ 1, 2 ], [ 1, 3 ], [ 1, 4 ], [ 2, 5 ], [ 2, 6 ], [ 3, 7 ], [4, 8] ], [ "root", "abcd", "cs", "hello", "etc", "hello", "solution", 'worlds' ]))