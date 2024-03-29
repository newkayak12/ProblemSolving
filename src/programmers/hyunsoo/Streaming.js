/**
 *
 * hard-4
 * <pre>
 * 문제 설명
 * 인터넷 음악 방송 채널에서 무료 스트리밍 서비스를 하고 있습니다.
 * 이 채널은 음악을 플레이 리스트에 등록된 순서에 따라 연속해서 재생합니다.
 * 플레이 리스트 에 등록된 음악은 모두 다르며,
 * 마지막 곡을 재생한 다음에는 다시 첫 번째 곡부터 반복해서 재생합니다.
 * 또, 모든 음악은 각각의 재생 시간만큼 재생됩니다.
 *
 * 이 스트리밍 채널에 접속해서 listen_time분 동안 음악을 들으려 합니다.
 * 이때, 들을 수 있는 `서로 다른` 꼭 개수의 최댓값을 구하려 합니다.
 *
 * 단, 음악이 정확히 `분` 단위로 흘렸을 때 접속한다고 가정하며,
 * 곡의 일부분만 들어도 들은 개수에 포함시킵니다.
 *
 * 각 음악의 재생 시간이 재생 목록에 들어있는 순서대로 담긴 배열 play_list와
 * 음악을 듣는 시간 listen_time이 매개변수로 주어질 때,
 * 들을 수 있는 서로 다른 꼭 개수의 최댓값을 raturn 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 *  • playList의 길이는 1 이상 100,000 이하입니다.
 *  • playList의 원소는 1 이상 10,000 이하인 자연수입니다.
 *  • 음악의 재생시간은 모두 분단위 입니다.
 *  • listen_time은 1이상 109 이하인 자연수입니다
 *
 *  1)
 *              |←  1 분 |  1 분 |  1 분  →|  => 3분
 *  |  2 분  |   3 분    |  1 분 |   4 분  |
 *
 *===========================================================================
 *
 *  2)
 *  |  1 분 |    2분    | 1분 →|          |←  1 분 |
 *  |  1 분 |    2분    |    3분   |      4분      |
 * </pre>
 */

const solution = (playList, listen_time) => {
    const shortest = Math.min(...playList)
    const startPoint = playList.map((v, i) => shortest === v ? i : null)
                               .filter(v => v !== null)
    let time = listen_time
    let count = 0;
    startPoint.forEach((v, i) => {
        count += 1
        time -= v;

        const leftIdx = i - 1 < 0 ? playList.length - 1 : i - 1
        const rightIdx = i + 1 > playList.length - 1 ? 0 : i + 1

        if( playList[leftIdx] > playList[rightIdx] ) {
            count += right(time, playList, i + 1)
            count += left(time, playList, i - 1)
        } else {
            count += left(time, playList, i - 1)
            count += right(time, playList, i + 1)
        }

    })

    return count
}
const left = (time, array, idx) => {
    const leftIdx = idx - 1 < 0 ? array.length - 1 : idx - 1
    if ( time === 0 ) return 0
    else if ( array[leftIdx] > time ) return 1 + left(0, array, idx - 1)
    else return 1 + left( time - array[leftIdx], array, idx - 1)
}

const right = (time, array, idx) => {
    const rightIdx = idx + 1 > array.length - 1 ? 0 : idx + 1
    if ( time === 0 ) return 0
    else if ( array[rightIdx] > time ) return 1 + right(0, array, idx + 1)
    else return 1 + right( time - array[rightIdx], array, idx + 1)
}




console.log(3, solution([2,3,1,4], 3))
console.log(4, solution([1,2,3,4], 4))